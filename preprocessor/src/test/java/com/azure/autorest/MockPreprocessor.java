/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest;

import com.azure.autorest.extension.base.jsonrpc.Connection;
import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.preprocessor.Preprocessor;
import org.yaml.snakeyaml.Yaml;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class MockPreprocessor extends Preprocessor {


    private static final Map<String, Object> SETTINGS_MAP = new HashMap<>();

    static {
        SETTINGS_MAP.put("generate-client-interfaces", false);
        SETTINGS_MAP.put("generate-client-as-impl", true);
        SETTINGS_MAP.put("generate-sync-async-clients", true);
        SETTINGS_MAP.put("add-context-parameter", true);
        SETTINGS_MAP.put("context-client-method-parameter", true);
        SETTINGS_MAP.put("sync-methods", "all");
    }

    public MockPreprocessor(Connection connection, String plugin, String sessionId) {
        super(connection, plugin, sessionId);

        JavaSettingsAccessor.setHost(this);
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

    public Yaml getYaml() {
        return yamlMapper;
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
