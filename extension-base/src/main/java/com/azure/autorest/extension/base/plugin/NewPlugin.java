package com.azure.autorest.extension.base.plugin;

import com.azure.autorest.extension.base.jsonrpc.Connection;
import com.azure.autorest.extension.base.model.Message;
import com.azure.autorest.extension.base.model.MessageChannel;
import com.azure.autorest.extension.base.model.codemodel.CodeModelCustomConstructor;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.representer.Representer;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class NewPlugin {
    protected final ObjectMapper jsonMapper;
    protected final Yaml yamlMapper;

    protected Connection connection;
    private String plugin;
    private String sessionId;

    public String readFile(String fileName) {
        return connection.request(jsonMapper.constructType(String.class), "ReadFile", sessionId, fileName);
    }

    public <T> T getValue(Type type, String key) {
        return connection.request(jsonMapper.constructType(type), "GetValue", sessionId, key);
    }

    public String getStringValue(String key) {
        return getValue(String.class, key);
    }

    public String getStringValue(String key, String defaultValue) {
        String ret = getStringValue(key);
        if (ret == null) {
            return defaultValue;
        } else {
            return ret;
        }
    }

    public String getStringValue(String[] keys, String defaultValue) {
        String ret = null;
        for (String key : keys) {
            ret = getStringValue(key);
            if (ret != null) {
                break;
            }
        }
        if (ret == null) {
            return defaultValue;
        } else {
            return ret;
        }
    }

    public Boolean getBooleanValue(String key) {
        return getValue(Boolean.class, key);
    }

    public boolean getBooleanValue(String key, boolean defaultValue) {
        Boolean ret = getBooleanValue(key);
        if (ret == null) {
            return defaultValue;
        } else {
            return ret;
        }
    }

    public List<String> listInputs() {
        return connection.request(jsonMapper.getTypeFactory().constructCollectionLikeType(List.class, String.class), "ListInputs", sessionId, null);
    }

    public List<String> listInputs(String artifactType) {
        return connection.request(jsonMapper.getTypeFactory().constructCollectionLikeType(List.class, String.class), "ListInputs", sessionId, artifactType);
    }

    public void message(Message message) {
        connection.notify("Message", sessionId, message);
    }

    public void message(MessageChannel channel, String text, Throwable error, List<String> keys) {
        Message message = new Message();
        message.setChannel(channel);
        message.setKey(keys);
        message.setSource(Collections.emptyList());
        if (error != null) {
            text += "\n" + formatThrowableMessage(error);
        }
        message.setText(text);
        message(message);
    }

    public void writeFile(String fileName, String content, List<Object> sourceMap) {
        connection.notify("WriteFile", sessionId, fileName, content, sourceMap);
    }

    public void writeFile(String fileName, String content, List<Object> sourceMap, String artifactType) {
        Message message = new Message();
        message.setChannel(MessageChannel.FILE);
        message.setDetails(new HashMap<String, Object>() {{
            put("content", content);
            put("type", artifactType);
            put("uri", fileName);
            put("sourceMap", sourceMap);
        }});
        message.setText(content);
        message.setKey(Arrays.asList(artifactType, fileName));
        connection.notify("Message", sessionId, message);
    }

    public void protectFiles(String path) {
        List<String> items = listInputs(path);
        if (items != null && items.size() > 0) {
            for (String item : items) {
                String content = readFile(item);
                writeFile(item, content, null, "preserved-files");
            }
        }
        String contentSingle = readFile(path);
        writeFile(path, contentSingle, null, "preserved-files");
    }

    public String getConfigurationFile(String fileName) {
        Map<String,String> configurations = getValue(new ParameterizedType() {
            @Override
            public Type[] getActualTypeArguments() {
                return new Type[] { String.class, String.class };
            }

            @Override
            public Type getRawType() {
                return Map.class;
            }

            @Override
            public Type getOwnerType() {
                return null;
            }
        }, "configurationFiles");
        if (configurations != null) {
            Iterator<String> it = configurations.keySet().iterator();
            if (it.hasNext()) {
                String first = it.next();
                first = first.substring(0, first.lastIndexOf('/'));
                for (String configFile : configurations.keySet()) {
                    if (String.format("%s/%s", first, fileName).equals(configFile)) {
                        return configurations.get(configFile);
                    }
                }
            }
        }
        return "";
    }

    public void updateConfigurationFile(String filename, String content) {
        Message message = new Message();
        message.setChannel(MessageChannel.CONFIGURATION);
        message.setKey(Arrays.asList(filename));
        message.setText(content);
        connection.notify("Message", sessionId, message);
    }

    public NewPlugin(Connection connection, String plugin, String sessionId)
    {
        this.connection = connection;
        this.plugin = plugin;
        this.sessionId = sessionId;
        this.jsonMapper = new ObjectMapper()
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                .configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        this.jsonMapper.setVisibility(jsonMapper.getSerializationConfig().getDefaultVisibilityChecker()
                .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
                .withSetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withIsGetterVisibility(JsonAutoDetect.Visibility.NONE));
        Representer representer = new Representer();
        representer.getPropertyUtils().setSkipMissingProperties(true);
        LoaderOptions loaderOptions = new LoaderOptions();
        loaderOptions.setMaxAliasesForCollections(Integer.MAX_VALUE);
        Constructor constructor = new CodeModelCustomConstructor(loaderOptions);
        yamlMapper = new Yaml(constructor, representer, new DumperOptions(), loaderOptions);
    }

    public boolean process() {
        try {
            JavaSettings.setHost(this);
            return processInternal();
        } catch (Throwable t) {
            message(MessageChannel.FATAL, "Unhandled error: " + t.getMessage(), t, Arrays.asList(getClass().getSimpleName()));
            return false;
        }
    }

    public abstract boolean processInternal();

    private String formatThrowableMessage(Throwable t) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        t.printStackTrace(printWriter);
        return stringWriter.toString();
    }
}
