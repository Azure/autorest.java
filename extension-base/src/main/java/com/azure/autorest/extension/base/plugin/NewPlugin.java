package com.azure.autorest.extension.base.plugin;

import com.azure.autorest.extension.base.jsonrpc.Connection;
import com.azure.autorest.extension.base.models.Message;
import com.azure.core.implementation.util.TypeUtil;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.lang.reflect.Type;
import java.util.*;

public abstract class NewPlugin {
    protected final ObjectMapper jsonMapper;

    protected Connection connection;
    private String plugin;
    private String sessionId;

    public String readFile(String fileName) {
        return connection.request(jsonMapper.constructType(String.class), "ReadFile", sessionId, fileName);
    }

    public <T> T getValue(Type type, String key) {
        return connection.request(jsonMapper.constructType(type), "GetValue", sessionId, key);
    }

    public String getValue(String key) {
        return getValue(String.class, key);
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

    public void writeFile(String fileName, String content, List<Object> sourceMap) {
        connection.notify("WriteFile", sessionId, fileName, content, sourceMap);
    }

    public void writeFile(String fileName, String content, List<Object> sourceMap, String artifactType) {
        Message message = new Message();
        message.setChannel("file");
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
        Map<String,String> configurations = getValue(TypeUtil.createParameterizedType(Map.class, String.class, String.class), "configurationFiles");
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
        message.setChannel("configuration");
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
    }

    public boolean process() {
        try {
            return processInternal();
        } catch (Throwable t) {
            Message message = new Message();
            message.setChannel("fatal");
            message.setText(t.getMessage());
            message(message);
            return false;
        }
    }

    public abstract boolean processInternal();
}
