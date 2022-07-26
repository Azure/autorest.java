// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest;

import com.azure.autorest.extension.base.jsonrpc.Connection;
import com.azure.autorest.extension.base.model.Message;
import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.Client;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientResponse;
import com.azure.autorest.model.clientmodel.EnumType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.javamodel.JavaPackage;
import com.azure.autorest.util.ClientModelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CadlPlugin extends Javagen {

    private static final Logger LOGGER = LoggerFactory.getLogger(CadlPlugin.class);

    @Override
    public JavaPackage writeToTemplates(CodeModel codeModel, Client client, JavaSettings settings) {
        return super.writeToTemplates(codeModel, client, settings);
    }

    @Override
    protected void writeClientModels(Client client, JavaPackage javaPackage, JavaSettings settings) {
        if (settings.isGenerateModels()) {
            // Client model
            client.getModels().stream().
                    filter(CadlPlugin::isGeneratingModel)
                    .forEach(model -> javaPackage.addModel(model.getPackage(), model.getName(), model));

            // Enum
            client.getEnums().stream()
                    .filter(CadlPlugin::isGeneratingModel)
                    .forEach(model -> javaPackage.addEnum(model.getPackage(), model.getName(), model));

            // Response
            client.getResponseModels().stream()
                    .filter(CadlPlugin::isGeneratingModel)
                    .forEach(model -> javaPackage.addClientResponse(model.getPackage(), model.getName(), model));
        }
    }

    @Override
    public void writeFile(String fileName, String content, List<Object> sourceMap) {
        new File(fileName).getParentFile().mkdirs();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        LOGGER.info("Write file: {}", fileName);
    }

    private static boolean isGeneratingModel(ClientModel model) {
        return model.getImplementationDetails() != null && model.getImplementationDetails().isConvenienceMethod();
    }

    private static boolean isGeneratingModel(EnumType enumType) {
        return true;
    }

    private static boolean isGeneratingModel(ClientResponse response) {
        IType bodyType = response.getBodyType();
        boolean ret = ClientModelUtil.isClientModel(bodyType);
        if (ret) {
            ClassType classType = (ClassType) bodyType;
            ClientModel model = ClientModelUtil.getClientModel(classType.getName());
            if (model != null) {
                ret = isGeneratingModel(model);
            }
        }
        return ret;
    }

    private static final Map<String, Object> SETTINGS_MAP = new HashMap<>();
    static {
        SETTINGS_MAP.put("data-plane", true);

        SETTINGS_MAP.put("license-header", "MICROSOFT_MIT_SMALL");
        SETTINGS_MAP.put("generate-client-interfaces", false);
        SETTINGS_MAP.put("generate-client-as-impl", true);
        SETTINGS_MAP.put("generate-sync-async-clients", true);
        SETTINGS_MAP.put("generate-builder-per-client", true);
        SETTINGS_MAP.put("add-context-parameter", true);
        SETTINGS_MAP.put("context-client-method-parameter", true);
        SETTINGS_MAP.put("sync-methods", "all");

        SETTINGS_MAP.put("use-default-http-status-code-to-exception-type-mapping", true);
        SETTINGS_MAP.put("polling", new HashMap<String, Object>());

        SETTINGS_MAP.put("models-subpackage", "models");
        SETTINGS_MAP.put("client-logger", true);
        SETTINGS_MAP.put("model-override-setter-from-superclass", true);
        SETTINGS_MAP.put("custom-strongly-typed-header-deserialization", true);
        SETTINGS_MAP.put("required-fields-as-ctor-args", true);

        SETTINGS_MAP.put("regenerate-pom", true);
        SETTINGS_MAP.put("generate-models", true);
    }

    public static class MockConnection extends Connection {

        public MockConnection() {
            super(null, null);
        }
    }

    public CadlPlugin(String namespace) {
        super(new MockConnection(), "dummy", "dummy");
        SETTINGS_MAP.put("namespace", namespace);
        JavaSettingsAccessor.setHost(this);
        JavaSettings.getInstance();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getValue(Type type, String key) {
        return (T) SETTINGS_MAP.get(key);
    }

    @Override
    public void message(Message message) {
        String log = message.getText();
        switch (message.getChannel()) {
            case INFORMATION:
                LOGGER.info(log);
                break;

            case WARNING:
                LOGGER.warn(log);
                break;

            case ERROR:
            case FATAL:
                LOGGER.error(log);
                break;

            case DEBUG:
                LOGGER.debug(log);
                break;

            default:
                LOGGER.info(log);
                break;
        }
    }
}
