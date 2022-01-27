// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest;

import com.azure.autorest.extension.base.jsonrpc.Connection;
import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.preprocessor.Preprocessor;
import com.azure.autorest.preprocessor.tranformer.Transformer;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class MockPreprocessor extends Preprocessor {


    private static final Map<String, Object> SETTINGS_MAP = new HashMap<>();

    public MockPreprocessor(Connection connection, String plugin, String sessionId) {
        super(connection, plugin, sessionId);

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
                codeModel = jsonMapper.readValue(file, CodeModel.class);
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
        Representer representer = new Representer() {
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
        loaderOptions.setMaxAliasesForCollections(Integer.MAX_VALUE);
        Yaml newYaml = new Yaml(new Constructor(loaderOptions), representer, new DumperOptions(), loaderOptions);
        return newYaml.dump(codeModel);
    }

    /**
     * override default behavior from fetching settings through json-rpc to read locally
     * @param type
     * @param key
     * @param <T>
     * @return
     */
    @Override
    public <T> T getValue(Type type, String key) {
        return (T) SETTINGS_MAP.get(key);
    }

    @Override
    public String readFile(String filename) {
        try {
            InputStream fis = MockPreprocessor.class.getClassLoader().getResourceAsStream(filename);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            return sb.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public  CodeModel performPosttransformUpdates(CodeModel codeModel) {
        if (JavaSettings.getInstance().isOptionalConstantAsEnum()) {
            return convertOptionalConstantsToEnum(codeModel);
        }
        return codeModel;
    }

    public CodeModel performPretransformUpdates(CodeModel codeModel) {
        if (JavaSettings.getInstance().isOptionalConstantAsEnum()) {
            return convertOptionalConstantsToEnum(codeModel);
        }
        return codeModel;
    }

}
