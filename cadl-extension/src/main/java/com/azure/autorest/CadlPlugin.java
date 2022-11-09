// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest;

import com.azure.autorest.extension.base.jsonrpc.Connection;
import com.azure.autorest.extension.base.model.Message;
import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.mapper.Mappers;
import com.azure.autorest.model.clientmodel.Client;
import com.azure.autorest.model.javamodel.JavaPackage;
import com.azure.autorest.partialupdate.util.PartialUpdateHandler;
import com.azure.cadl.model.EmitterOptions;
import com.azure.cadl.mapper.CadlMapperFactory;
import com.azure.cadl.util.ModelUtil;
import com.azure.core.util.Configuration;
import com.azure.core.util.CoreUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CadlPlugin extends Javagen {

    private static final Logger LOGGER = LoggerFactory.getLogger(CadlPlugin.class);

    private final EmitterOptions emitterOptions;

    public JavaPackage writeToTemplates(CodeModel codeModel, Client client, JavaSettings settings) {
        return super.writeToTemplates(codeModel, client, settings, false);
    }

    @Override
    protected void writeClientModels(Client client, JavaPackage javaPackage, JavaSettings settings) {
        // Client model
        client.getModels().stream()
                .filter(ModelUtil::isGeneratingModel)
                .forEach(model -> javaPackage.addModel(model.getPackage(), model.getName(), model));

        // Enum
        client.getEnums().stream()
                .filter(ModelUtil::isGeneratingModel)
                .forEach(model -> javaPackage.addEnum(model.getPackage(), model.getName(), model));

        // Response
        client.getResponseModels().stream()
                .filter(ModelUtil::isGeneratingModel)
                .forEach(model -> javaPackage.addClientResponse(model.getPackage(), model.getName(), model));
    }

    @Override
    public void writeFile(String fileName, String content, List<Object> sourceMap) {
        File outputFile = Paths.get(emitterOptions.getOutputDir(), fileName).toAbsolutePath().toFile();
        File parentFile = outputFile.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        try (FileWriter writer = new FileWriter(outputFile)) {
            writer.write(content);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        LOGGER.info("Write file: {}", outputFile.getAbsolutePath());
    }

    public String handlePartialUpdate(String filePath, String generatedContent) {
        if (filePath.endsWith(".java")) { // only handle for .java file
            // check if existingFile exists, if not, no need to handle partial update
            Path absoluteFilePath = Paths.get(emitterOptions.getOutputDir(), filePath);
            if (Files.exists(absoluteFilePath)) {
                try {
                    String existingFileContent = new String(Files.readAllBytes(absoluteFilePath));
                    String updatedContent = PartialUpdateHandler.handlePartialUpdateForFile(generatedContent, existingFileContent);
                    return updatedContent;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return generatedContent;
    }

    private static final Map<String, Object> SETTINGS_MAP = new HashMap<>();

    static {
        SETTINGS_MAP.put("data-plane", true);

        SETTINGS_MAP.put("sdk-integration", true);
        SETTINGS_MAP.put("regenerate-pom", true);

        SETTINGS_MAP.put("license-header", "MICROSOFT_MIT_SMALL");
        SETTINGS_MAP.put("generate-client-interfaces", false);
        SETTINGS_MAP.put("generate-client-as-impl", true);
        SETTINGS_MAP.put("generate-sync-async-clients", true);
        SETTINGS_MAP.put("generate-builder-per-client", false);
        SETTINGS_MAP.put("sync-methods", "all");

        SETTINGS_MAP.put("use-default-http-status-code-to-exception-type-mapping", true);
        SETTINGS_MAP.put("polling", new HashMap<String, Object>());

        SETTINGS_MAP.put("models-subpackage", "models");
        SETTINGS_MAP.put("client-logger", true);
        SETTINGS_MAP.put("required-fields-as-ctor-args", true);
        SETTINGS_MAP.put("required-parameter-client-methods", true);
        SETTINGS_MAP.put("generic-response-type", true);
        SETTINGS_MAP.put("output-model-immutable", true);

        SETTINGS_MAP.put("generate-models", Configuration.getGlobalConfiguration().get("GENERATE_MODELS", false));
    }

    public static class MockConnection extends Connection {

        public MockConnection() {
            super(null, null);
        }

    }

    public CadlPlugin(EmitterOptions options) {
        super(new MockConnection(), "dummy", "dummy");
        this.emitterOptions = options;
        SETTINGS_MAP.put("namespace", options.getNamespace());
        if (!CoreUtils.isNullOrEmpty(options.getOutputDir())) {
            SETTINGS_MAP.put("output-folder", options.getOutputDir());
        }
        if (!CoreUtils.isNullOrEmpty(options.getServiceName())) {
            SETTINGS_MAP.put("service-name", options.getServiceName());
        }
        if (options.getPartialUpdate() != null) {
            SETTINGS_MAP.put("partial-update", options.getPartialUpdate());
        }
        if (!CoreUtils.isNullOrEmpty(options.getServiceVersions())) {
            SETTINGS_MAP.put("service-versions", options.getServiceVersions());
        }

        JavaSettingsAccessor.setHost(this);
        LOGGER.info("Output folder: {}", options.getOutputDir());
        LOGGER.info("Namespace: {}", JavaSettings.getInstance().getPackage());

        Mappers.setFactory(new CadlMapperFactory());
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
