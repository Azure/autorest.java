package com.azure.autorest.fluent;

import com.azure.autorest.JavaSettingsAccessor;
import com.azure.autorest.TypeSpecPlugin;
import com.azure.autorest.extension.base.model.Message;
import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.fluent.model.javamodel.FluentJavaPackage;
import com.azure.autorest.fluent.namer.FluentNamerFactory;
import com.azure.autorest.fluent.transformer.FluentTransformer;
import com.azure.autorest.fluent.util.FluentJavaSettings;
import com.azure.autorest.mapper.Mappers;
import com.azure.autorest.model.clientmodel.Client;
import com.azure.autorest.preprocessor.Preprocessor;
import com.azure.autorest.preprocessor.tranformer.Transformer;
import com.azure.autorest.util.CodeNamer;
import com.azure.core.util.CoreUtils;
import com.azure.typespec.model.EmitterOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class TypeSpecFluentPlugin extends FluentGen {
    private static final Logger LOGGER = LoggerFactory.getLogger(TypeSpecFluentPlugin.class);
    private final EmitterOptions options;

    public TypeSpecFluentPlugin(EmitterOptions options, boolean sdkIntegration) {
        super(new TypeSpecPlugin.MockConnection(), "dummy", "dummy");
        this.options = options;
        SETTINGS_MAP.put("namespace", options.getNamespace());
        if (!CoreUtils.isNullOrEmpty(options.getOutputDir())) {
            SETTINGS_MAP.put("output-folder", options.getOutputDir());
        }
        if (!CoreUtils.isNullOrEmpty(options.getServiceName())) {
            SETTINGS_MAP.put("service-name", options.getServiceName());
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

        JavaSettingsAccessor.setHost(this);
        LOGGER.info("Output folder: {}", options.getOutputDir());
        LOGGER.info("Namespace: {}", JavaSettings.getInstance().getPackage());
    }

    public Client processClient(CodeModel codeModel) {
        // transform code model
        LOGGER.info("Load fluent settings");
        CodeNamer.setFactory(new FluentNamerFactory(getFluentJavaSettings()));

        LOGGER.info("Transform code model");
        FluentTransformer transformer = new FluentTransformer(getFluentJavaSettings());
        codeModel = transformer.preTransform(codeModel);

        codeModel = new Transformer().transform(codeModel);

        codeModel = transformer.postTransform(codeModel);

        // call FluentGen.handleMap
        Client client = handleMap(codeModel);

        return client;
    }

    public FluentJavaPackage processTemplates(CodeModel codeModel, Client client) {
        FluentJavaPackage javaPackage = handleTemplate(client);
        handleFluentLite(codeModel, client, javaPackage);
        return javaPackage;
    }

    private static final Map<String, Object> SETTINGS_MAP = new HashMap<>();

    static {
        SETTINGS_MAP.put("data-plane", false);

        SETTINGS_MAP.put("sdk-integration", true);
        SETTINGS_MAP.put("regenerate-pom", true);

        SETTINGS_MAP.put("license-header", "MICROSOFT_MIT_SMALL_TYPESPEC");
        SETTINGS_MAP.put("enable-sync-stack", true);
        SETTINGS_MAP.put("enable-page-size", true);

        SETTINGS_MAP.put("use-default-http-status-code-to-exception-type-mapping", true);

        SETTINGS_MAP.put("client-logger", true);
        SETTINGS_MAP.put("required-fields-as-ctor-args", true);
        SETTINGS_MAP.put("required-parameter-client-methods", true);
        SETTINGS_MAP.put("output-model-immutable", true);
        SETTINGS_MAP.put("disable-required-property-annotation", true);
        // Defaulting to KeyCredential and not providing TypeSpec services to generate with AzureKeyCredential.
        SETTINGS_MAP.put("use-key-credential", true);
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
