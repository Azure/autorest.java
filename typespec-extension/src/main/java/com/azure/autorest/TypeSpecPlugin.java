// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest;

import com.azure.autorest.customization.Customization;
import com.azure.autorest.extension.base.jsonrpc.Connection;
import com.azure.autorest.extension.base.model.Message;
import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.mapper.Mappers;
import com.azure.autorest.model.clientmodel.AsyncSyncClient;
import com.azure.autorest.model.clientmodel.Client;
import com.azure.autorest.model.clientmodel.ConvenienceMethod;
import com.azure.autorest.model.javamodel.JavaPackage;
import com.azure.autorest.partialupdate.util.PartialUpdateHandler;
import com.azure.autorest.postprocessor.Postprocessor;
import com.azure.autorest.preprocessor.Preprocessor;
import com.azure.autorest.preprocessor.tranformer.Transformer;
import com.azure.core.util.CoreUtils;
import com.azure.typespec.mapper.TypeSpecMapperFactory;
import com.azure.typespec.model.EmitterOptions;
import com.azure.typespec.util.ModelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TypeSpecPlugin extends Javagen {

    private static final Logger LOGGER = LoggerFactory.getLogger(TypeSpecPlugin.class);

    private final EmitterOptions emitterOptions;

    private final Map<String, String> crossLanguageDefinitionsMap = new TreeMap<>();

    public Client processClient(CodeModel codeModel) {
        // transform code model
        codeModel = Preprocessor.convertOptionalConstantsToEnum(codeModel);
        codeModel = new Transformer().transform(codeModel);

        // map to client model
        Client client = Mappers.getClientMapper().map(codeModel);

        client.getAsyncClients()
                .forEach(asyncClient -> crossLanguageDefinitionsMap
                        .put(asyncClient.getPackageName() + "." + asyncClient.getClassName(), asyncClient.getCrossLanguageDefinitionId()));

        client.getSyncClients()
                .forEach(syncClient -> crossLanguageDefinitionsMap
                        .put(syncClient.getPackageName() + "." + syncClient.getClassName(), syncClient.getCrossLanguageDefinitionId()));

        client.getClientBuilders()
                .forEach(clientBuilder -> crossLanguageDefinitionsMap
                        .put(clientBuilder.getPackageName() + "." + clientBuilder.getClassName(), clientBuilder.getCrossLanguageDefinitionId()));

        for (AsyncSyncClient asyncClient : client.getAsyncClients()) {
            List<ConvenienceMethod> convenienceMethods = asyncClient.getConvenienceMethods();
            for (ConvenienceMethod convenienceMethod : convenienceMethods) {
                convenienceMethod.getConvenienceMethods()
                        .stream()
                        .filter(method -> !method.getName().endsWith("Async"))
                        .forEach(method -> crossLanguageDefinitionsMap.put(asyncClient.getPackageName() + "." + asyncClient.getClassName() + "." + method.getName(), method.getCrossLanguageDefinitionId()));
                if (!convenienceMethod.getProtocolMethod().getName().endsWith("Async")) {
                    crossLanguageDefinitionsMap.put(asyncClient.getPackageName() + "." + asyncClient.getClassName() + "." + convenienceMethod.getProtocolMethod().getName(),
                            convenienceMethod.getProtocolMethod().getCrossLanguageDefinitionId());
                }
            }
        }

        for (AsyncSyncClient syncClient : client.getSyncClients()) {
            List<ConvenienceMethod> convenienceMethods = syncClient.getConvenienceMethods();
            for (ConvenienceMethod convenienceMethod : convenienceMethods) {
                convenienceMethod.getConvenienceMethods()
                        .stream()
                        .filter(method -> !method.getName().endsWith("Async"))
                        .forEach(method -> crossLanguageDefinitionsMap.put(syncClient.getPackageName() + "." + syncClient.getClassName() + "." + method.getName(), method.getCrossLanguageDefinitionId()));

                if (!convenienceMethod.getProtocolMethod().getName().endsWith("Async")) {
                    crossLanguageDefinitionsMap.put(syncClient.getPackageName() + "." + syncClient.getClassName() + "." + convenienceMethod.getProtocolMethod().getName(),
                            convenienceMethod.getProtocolMethod().getCrossLanguageDefinitionId());
                }

            }
        }
        return client;
    }

    public JavaPackage processTemplates(CodeModel codeModel, Client client, JavaSettings settings) {
        return super.writeToTemplates(codeModel, client, settings, false);
    }

    @Override
    protected void writeClientModels(Client client, JavaPackage javaPackage, JavaSettings settings) {
        // Client model
        client.getModels().stream()
                .filter(ModelUtil::isGeneratingModel)
                .forEach(model -> {
                    crossLanguageDefinitionsMap.put(model.getPackage() + "." + model.getName(), model.getCrossLanguageDefinitionId());
                    javaPackage.addModel(model.getPackage(), model.getName(), model);
                });

        // Enum
        client.getEnums().stream()
                .filter(ModelUtil::isGeneratingModel)
                .forEach(model -> {
                    crossLanguageDefinitionsMap.put(model.getPackage() + "." + model.getName(), model.getCrossLanguageDefinitionId());
                    javaPackage.addEnum(model.getPackage(), model.getName(), model);
                });

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
        File outputFile = Paths.get(emitterOptions.getOutputDir(), fileName).toAbsolutePath().toFile();
        File parentFile = outputFile.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        try (OutputStreamWriter writer = new OutputStreamWriter(Files.newOutputStream(outputFile.toPath()), StandardCharsets.UTF_8)) {
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
                    String existingFileContent = new String(Files.readAllBytes(absoluteFilePath), StandardCharsets.UTF_8);
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

    public Map<String, String> customizeGeneratedCode(Map<String, String> fileContents, String outputDir) {
        String className = JavaSettings.getInstance().getCustomizationClass();

        if (className == null) {
            return fileContents;
        }

        Class<? extends Customization> customizationClass = null;
        if (className.endsWith(".java")) {
            customizationClass = Postprocessor.loadCustomizationClassFromJavaCode(className, outputDir, LOGGER);
        } else {
            LOGGER.warn("Invalid customization class. No customizations are applied to the generated code."
                    + " The customization java file should end with .java but was " + className);

            return fileContents;
        }
        try {
            Customization customization = customizationClass.getConstructor().newInstance();
            LOGGER.info("Running customization, this may take a while...");
            fileContents = customization.run(fileContents, LOGGER);
            return fileContents;
        } catch (Exception e) {
            LOGGER.error("Unable to complete customization", e);
            throw new RuntimeException(e);
        }
    }

    public Map<String, String> getCrossLanguageDefinitionMap() {
        return this.crossLanguageDefinitionsMap;
    }

    public static class MockConnection extends Connection {
        public MockConnection() {
            super(null, null);
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

        SETTINGS_MAP.put("sdk-integration", sdkIntegration);
        SETTINGS_MAP.put("regenerate-pom", sdkIntegration);

        if (options.getCustomTypes() != null) {
            SETTINGS_MAP.put("custom-types", options.getCustomTypes());
        }

        if (options.getCustomTypeSubpackage() != null) {
            SETTINGS_MAP.put("custom-types-subpackage", options.getCustomTypeSubpackage());
        }

        if (options.getCustomizationClass() != null) {
            SETTINGS_MAP.put("customization-class", options.getCustomizationClass());
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
