// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest;

import com.azure.autorest.extension.base.jsonrpc.Connection;
import com.azure.autorest.extension.base.model.Message;
import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.extension.base.plugin.NewPlugin;
import com.azure.autorest.preprocessor.Preprocessor;
import com.azure.autorest.preprocessor.tranformer.Transformer;
import com.azure.json.JsonProviders;
import com.azure.json.JsonReader;
import com.azure.json.ReadValueCallback;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.inspector.TrustedTagInspector;
import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class MockPreprocessor extends NewPlugin {

    private static final Map<String, Object> SETTINGS_MAP = new HashMap<>();
    static {
        SETTINGS_MAP.put("namespace", "com.azure.mock");
    }

    public static class MockConnection extends Connection {

        public MockConnection() {
            super(null, null);
        }
    }

    public MockPreprocessor() {
        super(new MockConnection(), "dummy", "dummy");

        JavaSettingsAccessor.setHost(this);
    }

    public CodeModel loadCodeModel(String fileName) {
        String file = readFile(fileName);
        CodeModel codeModel;
        try {
            if (!file.startsWith("{")) {
                // YAML
                codeModel = yamlMapper.loadAs(file, CodeModel.class);
            } else {
                try (JsonReader jsonReader = JsonProviders.createReader(fileName)) {
                    codeModel = CodeModel.fromJson(jsonReader);
                }
            }
        } catch (Exception e) {
            System.err.println("Got an error " + e.getMessage());
            throw new RuntimeException(e);
        }

        return codeModel;
    }

    public CodeModel transform(CodeModel codeModel) {
        performPretransformUpdates(codeModel);
        CodeModel result = new Transformer().transform(codeModel);
        performPosttransformUpdates(codeModel);
        return result;
    }

    public String dump(CodeModel codeModel) {
        Representer representer = new Representer(new DumperOptions()) {
            @Override
            protected NodeTuple representJavaBeanProperty(Object javaBean, Property property, Object propertyValue,
                                                          Tag customTag) {
                // if value of property is null, ignore it.
                if (propertyValue == null) {
                    return null;
                }
                else {
                    return super.representJavaBeanProperty(javaBean, property, propertyValue, customTag);
                }
            }
        };
        LoaderOptions loaderOptions = new LoaderOptions();
        loaderOptions.setCodePointLimit(50 * 1024 * 1024);
        loaderOptions.setMaxAliasesForCollections(Integer.MAX_VALUE);
        loaderOptions.setNestingDepthLimit(Integer.MAX_VALUE);
        loaderOptions.setTagInspector(new TrustedTagInspector());
        Yaml newYaml = new Yaml(new Constructor(loaderOptions), representer, new DumperOptions(), loaderOptions);
        return newYaml.dump(codeModel);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getValue(String key, ReadValueCallback<String, T> converter) {
        return (T) SETTINGS_MAP.get(key);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getValueWithJsonReader(String key, ReadValueCallback<JsonReader, T> converter) {
        return (T) SETTINGS_MAP.get(key);
    }

    @Override
    public void message(Message message) {
    }

    @Override
    public boolean processInternal() {
        return false;
    }

    @Override
    public String readFile(String filename) {
        try (InputStream fis = MockPreprocessor.class.getClassLoader().getResourceAsStream(filename)) {
            return new String(fis.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public CodeModel performPosttransformUpdates(CodeModel codeModel) {
        if (JavaSettings.getInstance().isOptionalConstantAsEnum()) {
            return Preprocessor.convertOptionalConstantsToEnum(codeModel);
        }
        return codeModel;
    }

    public CodeModel performPretransformUpdates(CodeModel codeModel) {
        if (JavaSettings.getInstance().isOptionalConstantAsEnum()) {
            return Preprocessor.convertOptionalConstantsToEnum(codeModel);
        }
        return codeModel;
    }

}
