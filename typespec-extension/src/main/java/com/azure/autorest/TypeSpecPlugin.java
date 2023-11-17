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
import com.azure.autorest.preprocessor.Preprocessor;
import com.azure.autorest.preprocessor.tranformer.Transformer;
import com.azure.core.util.CoreUtils;
import com.azure.typespec.mapper.TypeSpecMapperFactory;
import com.azure.typespec.model.EmitterOptions;
import com.azure.typespec.util.FileUtil;
import com.azure.typespec.util.ModelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TypeSpecPlugin extends Javagen {

    private static final Logger LOGGER = LoggerFactory.getLogger(TypeSpecPlugin.class);

    private final EmitterOptions emitterOptions;

    public Client processClient(CodeModel codeModel) {
        // transform code model
        codeModel = Preprocessor.convertOptionalConstantsToEnum(codeModel);
        codeModel = new Transformer().transform(codeModel);

        // map to client model
        return Mappers.getClientMapper().map(codeModel);
    }

    public JavaPackage processTemplates(CodeModel codeModel, Client client, JavaSettings settings) {
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

        // Union
        client.getUnionModels().stream()
                .filter(ModelUtil::isGeneratingModel)
                .forEach(model -> javaPackage.addUnionModel(model));
    }

    @Override
    public void writeFile(String fileName, String content, List<Object> sourceMap) {
        File outputFile = FileUtil.writeToFile(emitterOptions.getOutputDir(), fileName, content);
        LOGGER.info("Write file: {}", outputFile.getAbsolutePath());
    }

    public String handlePartialUpdate(String filePath, String generatedContent) {
        if (filePath.endsWith(".java")) { // only handle for .java file
            // check if existingFile exists, if not, no need to handle partial update
            Path absoluteFilePath = Paths.get(emitterOptions.getOutputDir(), filePath);
            if (Files.exists(absoluteFilePath)) {
                try {
                    String existingFileContent = Files.readString(absoluteFilePath);
                    return PartialUpdateHandler.handlePartialUpdateForFile(generatedContent, existingFileContent);
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

        SETTINGS_MAP.put("license-header", "MICROSOFT_MIT_SMALL_TYPESPEC");
        SETTINGS_MAP.put("generate-client-interfaces", false);
        SETTINGS_MAP.put("generate-client-as-impl", true);
        SETTINGS_MAP.put("generate-sync-async-clients", true);
        SETTINGS_MAP.put("generate-builder-per-client", false);
        SETTINGS_MAP.put("sync-methods", "all");
        SETTINGS_MAP.put("enable-sync-stack", true);
        SETTINGS_MAP.put("enable-page-size", true);

        SETTINGS_MAP.put("use-default-http-status-code-to-exception-type-mapping", true);
        SETTINGS_MAP.put("polling", new HashMap<String, Object>());

        SETTINGS_MAP.put("models-subpackage", "models");
        SETTINGS_MAP.put("client-logger", true);
        SETTINGS_MAP.put("required-fields-as-ctor-args", true);
        SETTINGS_MAP.put("required-parameter-client-methods", true);
        SETTINGS_MAP.put("generic-response-type", true);
        SETTINGS_MAP.put("output-model-immutable", true);
        SETTINGS_MAP.put("disable-required-property-annotation", true);
        // Defaulting to KeyCredential and not providing TypeSpec services to generate with AzureKeyCredential.
        SETTINGS_MAP.put("use-key-credential", true);
    }

    public static class MockConnection extends Connection {
        public MockConnection() {
            super(new OutputStream() {
                @Override
                public void write(int b) {
                    // NO-OP
                }
            }, null);
        }
    }

    public TypeSpecPlugin(EmitterOptions options, boolean sdkIntegration) {
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
        if (options.getGenerateSamples() != null) {
            SETTINGS_MAP.put("generate-samples", options.getGenerateSamples());
        }
        if (options.getGenerateTests() != null) {
            SETTINGS_MAP.put("generate-tests", options.getGenerateTests());
        }
        if (options.getEnableSyncStack() != null) {
            SETTINGS_MAP.put("enable-sync-stack", options.getEnableSyncStack());
        }
        if (options.getStreamStyleSerialization() != null) {
            SETTINGS_MAP.put("stream-style-serialization", options.getStreamStyleSerialization());
        }

        SETTINGS_MAP.put("sdk-integration", sdkIntegration);
        SETTINGS_MAP.put("regenerate-pom", sdkIntegration);

        if (options.getCustomTypes() != null) {
            SETTINGS_MAP.put("custom-types", options.getCustomTypes());
        }

        if (options.getCustomTypeSubpackage() != null) {
            SETTINGS_MAP.put("custom-types-subpackage", options.getCustomTypeSubpackage());
        }
        if (options.getCustomizationClass() != null) {
            SETTINGS_MAP.put("customization-class",
                Paths.get(options.getOutputDir()).resolve(options.getCustomizationClass()).toAbsolutePath().toString());
        }
        if (emitterOptions.getPolling() != null) {
            SETTINGS_MAP.put("polling", options.getPolling());
        }

        if (options.getBranded() == Boolean.FALSE) {
            SETTINGS_MAP.put("branded", options.getBranded());

            SETTINGS_MAP.put("sdk-integration", false);
            SETTINGS_MAP.put("license-header", "SMALL_TYPESPEC");

            SETTINGS_MAP.put("sync-methods", "sync-only");
            SETTINGS_MAP.put("stream-style-serialization", true);
            SETTINGS_MAP.put("generate-samples", false);
            SETTINGS_MAP.put("generate-tests", false);
        }

        JavaSettingsAccessor.setHost(this);
        LOGGER.info("Output folder: {}", options.getOutputDir());
        LOGGER.info("Namespace: {}", JavaSettings.getInstance().getPackage());

        Mappers.setFactory(new TypeSpecMapperFactory());
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
