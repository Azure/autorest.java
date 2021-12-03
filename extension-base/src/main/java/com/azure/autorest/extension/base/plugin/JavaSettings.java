// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

package com.azure.autorest.extension.base.plugin;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


/**
 * Settings that are used by the Java AutoRest Generator.
 */
public class JavaSettings {
    private static final String VERSION = "4.0.0";

    private static final Pattern LEADING_PERIOD = Pattern.compile("^\\.");
    private static final Pattern TRAILING_PERIOD = Pattern.compile("\\.$");

    private static JavaSettings _instance;

    private static NewPlugin host;

    private static String _header;

    private static final Map<String, String> simpleJavaSettings = new HashMap<>();

    static void setHeader(String value) {
        if ("MICROSOFT_MIT".equals(value)) {
            _header = MicrosoftMitLicenseHeader + "\n" + String.format(DefaultCodeGenerationHeader, VERSION);
        } else if ("MICROSOFT_APACHE".equals(value)) {
            _header = MicrosoftApacheLicenseHeader + "\n" + String.format(DefaultCodeGenerationHeader, VERSION);
        } else if ("MICROSOFT_MIT_NO_VERSION".equals(value)) {
            _header = MicrosoftMitLicenseHeader + "\n" + DefaultCodeGenerationHeaderWithoutVersion;
        } else if ("MICROSOFT_MIT_SMALL_NO_VERSION".equals(value)) {
            _header = MicrosoftMitSmallLicenseHeader + "\n" + DefaultCodeGenerationHeaderWithoutVersion;
        } else if ("MICROSOFT_APACHE_NO_VERSION".equals(value)) {
            _header = MicrosoftApacheLicenseHeader + "\n" + DefaultCodeGenerationHeaderWithoutVersion;
        } else if ("MICROSOFT_MIT_NO_CODEGEN".equals(value)) {
            _header = MicrosoftMitLicenseHeader + "\n" + "Code generated by Microsoft (R) AutoRest Code Generator.";
        } else if ("NONE".equals(value)) {
            _header = "";
        } else if ("MICROSOFT_MIT_SMALL".equals(value)) {
            _header = MicrosoftMitSmallLicenseHeader + "Code generated by Microsoft (R) AutoRest Code Generator.";
        } else if ("MICROSOFT_MIT_SMALL_NO_CODEGEN".equals(value)) {
            _header = MicrosoftMitSmallLicenseHeader;
        } else {
            _header = value;
        }
    }

    static void setHost(NewPlugin host) {
        JavaSettings.host = host;
    }

    public static JavaSettings getInstance() {
        if (_instance == null) {
            AutorestSettings autorestSettings = new AutorestSettings();
            loadStringSetting("tag", autorestSettings::setTag);
            loadStringSetting("base-folder", autorestSettings::setBaseFolder);
            loadStringSetting("output-folder", autorestSettings::setOutputFolder);
            loadStringSetting("azure-libraries-for-java-folder", autorestSettings::setAzureLibrariesForJavaFolder);
            List<Object> inputFiles = host.getValue(List.class, "input-file");
            if (inputFiles != null) {
                autorestSettings.getInputFiles().addAll(
                    inputFiles.stream().map(Object::toString).collect(Collectors.toList()));
            }

            setHeader(getStringValue(host, "license-header"));
            _instance = new JavaSettings(
                autorestSettings,
                host.getValue(new TypeReference<Map<String, Object>>() {
                }.getType(), "pipeline.modelerfour"),
                getBooleanValue(host, "azure-arm", false),
                getBooleanValue(host, "sdk-integration", false),
                getStringValue(host, "fluent"),
                getBooleanValue(host, "regenerate-pom", false),
                _header,
                80,
                getStringValue(host, "service-name"),
                getStringValue(host, "namespace", "").toLowerCase(),
                getBooleanValue(host, "enable-xml", false),
                getBooleanValue(host, "non-null-annotations", false),
                getBooleanValue(host, "client-side-validations", false),
                getStringValue(host, "client-type-prefix"),
                getBooleanValue(host, "generate-client-interfaces", false),
                getBooleanValue(host, "generate-client-as-impl", false),
                getStringValue(host, "implementation-subpackage", "implementation"),
                getStringValue(host, "models-subpackage", "models"),
                getStringValue(host, "custom-types", ""),
                getStringValue(host, "custom-types-subpackage", ""),
                getStringValue(host, "fluent-subpackage", "fluent"),
                getBooleanValue(host, "required-parameter-client-methods", false),
                getBooleanValue(host, "add-context-parameter", false),
                getBooleanValue(host, "context-client-method-parameter", false),
                getBooleanValue(host, "generate-sync-async-clients", false),
                getStringValue(host, "sync-methods", "essential"),
                getBooleanValue(host, "client-logger", false),
                getBooleanValue(host, "required-fields-as-ctor-args", false),
                getBooleanValue(host, "service-interface-as-public", false),
                getStringValue(host, "artifact-id", ""),
                getStringValue(host, "credential-types", "none"),
                getStringValue(host, "credential-scopes"),
                getStringValue(host, "customization-jar-path"),
                getStringValue(host, "customization-class"),
                getBooleanValue(host, "model-override-setter-from-superclass", false),
                getBooleanValue(host, "optional-constant-as-enum", false),
                getBooleanValue(host, "low-level-client", false),
                getBooleanValue(host, "use-iterable", false),
                host.getValue(List.class, "service-versions"),
                getBooleanValue(host, "require-x-ms-flattened-to-flatten", false),
                getStringValue(host, "client-flattened-annotation-target", ""),
                getStringValue(host, "key-credential-header-name", ""),
                getBooleanValue(host, "disable-client-builder", false),
                getBooleanValue(host, "skip-formatting", false),
                host.getValue(new TypeReference<Map<String, PollingDetails>>() {
                }.getType(), "polling"),
                getBooleanValue(host, "generate-samples", false),
                getBooleanValue(host, "pass-discriminator-to-child-deserialization", false),
                getBooleanValue(host, "annotate-getters-and-setters-for-serialization", false),
                getStringValue(host, "default-http-exception-type"),
                getBooleanValue(host, "use-default-http-status-code-to-exception-type-mapping", false),
                host.getValue(new TypeReference<Map<Integer, String>>() {}.getType(),
                    "http-status-code-to-exception-type-mapping"),
                getBooleanValue(host, "handle-partial-update", false)
            );
        }
        return _instance;
    }

    /**
     * Create a new JavaSettings object with the provided properties.
     *
     * @param azure
     * @param fluent
     * @param regeneratePom
     * @param fileHeaderText
     * @param maximumJavadocCommentWidth
     * @param serviceName
     * @param shouldGenerateXmlSerialization
     * @param nonNullAnnotations Whether to add the @NotNull annotation to required parameters in client methods.
     * @param clientTypePrefix The prefix that will be added to each generated client type.
     * @param generateClientInterfaces Whether interfaces will be generated for Service and Method Group clients.
     * @param implementationSubpackage The sub-package that the Service and Method Group client implementation classes
     * will be put into.
     * @param modelsSubpackage The sub-package that Enums, Exceptions, and Model types will be put into.
     * @param requiredParameterClientMethods Whether Service and Method Group client method overloads that omit optional
     * parameters will be created.
     * @param serviceInterfaceAsPublic If set to true, proxy method service interface will be marked as public.
     * @param requireXMsFlattenedToFlatten If set to true, a model must have x-ms-flattened to be annotated with
     * JsonFlatten.
     * @param passDiscriminatorToChildDeserialization If set to true, Jackson sub-type deserialization will be passed
     * the discriminator field.
     * @param annotateGettersAndSettersForSerialization If set to true, Jackson JsonGetter and JsonSetter will annotate
     * getters and setters in generated models to handle serialization and deserialization. For now, fields will
     * continue being annotated to ensure that there are no backwards compatibility breaks.
     * @param defaultHttpExceptionType The fully-qualified class that should be used as the default exception type. This
     * class must extend from HttpResponseException.
     * @param useDefaultHttpStatusCodeToExceptionTypeMapping Determines whether a well-known HTTP status code to exception type mapping
     * should be used if an HTTP status code-exception mapping isn't provided.
     * @param httpStatusCodeToExceptionTypeMapping A mapping of HTTP response status code to the exception type that should be
     * thrown if that status code is seen. All exception types must be fully-qualified and extend from
     * HttpResponseException.
     */
    private JavaSettings(AutorestSettings autorestSettings,
        Map<String, Object> modelerSettings,
        boolean azure,
        boolean sdkIntegration,
        String fluent,
        boolean regeneratePom,
        String fileHeaderText,
        int maximumJavadocCommentWidth,
        String serviceName,
        String package_Keyword,
        boolean shouldGenerateXmlSerialization,
        boolean nonNullAnnotations,
        boolean clientSideValidations,
        String clientTypePrefix,
        boolean generateClientInterfaces,
        boolean generateClientAsImpl,
        String implementationSubpackage,
        String modelsSubpackage,
        String customTypes,
        String customTypesSubpackage,
        String fluentSubpackage,
        boolean requiredParameterClientMethods,
        boolean addContextParameter,
        boolean contextClientMethodParameter,
        boolean generateSyncAsyncClients,
        String syncMethods,
        boolean clientLogger,
        boolean requiredFieldsAsConstructorArgs,
        boolean serviceInterfaceAsPublic,
        String artifactId,
        String credentialType,
        String credentialScopes,
        String customizationJarPath,
        String customizationClass,
        boolean overrideSetterFromSuperclass,
        boolean optionalConstantAsEnum,
        boolean lowLevelClient,
        boolean useIterable,
        List<String> serviceVersions,
        boolean requireXMsFlattenedToFlatten,
        String clientFlattenAnnotationTarget,
        String keyCredentialHeaderName,
        boolean clientBuilderDisabled,
        boolean skipFormatting,
        Map<String, PollingDetails> pollingConfig,
        boolean generateSamples,
        boolean passDiscriminatorToChildDeserialization,
        boolean annotateGettersAndSettersForSerialization,
        String defaultHttpExceptionType,
        boolean useDefaultHttpStatusCodeToExceptionTypeMapping,
        Map<Integer, String> httpStatusCodeToExceptionTypeMapping,
        boolean handlePartialUpdate) {

        this.autorestSettings = autorestSettings;
        this.modelerSettings = new ModelerSettings(modelerSettings);
        this.azure = azure;
        this.sdkIntegration = sdkIntegration;
        this.fluent = fluent == null ? Fluent.NONE : (fluent.isEmpty() || fluent.equalsIgnoreCase("true") ? Fluent.PREMIUM : Fluent.valueOf(fluent.toUpperCase(Locale.ROOT)));
        this.regeneratePom = regeneratePom;
        this.fileHeaderText = fileHeaderText;
        this.maximumJavadocCommentWidth = maximumJavadocCommentWidth;
        this.serviceName = serviceName;
        this.packageName = package_Keyword;
        this.shouldGenerateXmlSerialization = shouldGenerateXmlSerialization;
        this.nonNullAnnotations = nonNullAnnotations;
        this.clientSideValidations = clientSideValidations;
        this.clientTypePrefix = clientTypePrefix;
        this.generateClientInterfaces = generateClientInterfaces;
        this.generateClientAsImpl = generateClientAsImpl || generateSyncAsyncClients || generateClientInterfaces;
        this.implementationSubpackage = implementationSubpackage;
        this.modelsSubpackage = modelsSubpackage;
        this.customTypes = (customTypes == null || customTypes.isEmpty()) ? new ArrayList<>() : Arrays.asList(customTypes.split(","));
        this.customTypesSubpackage = customTypesSubpackage;
        this.fluentSubpackage = fluentSubpackage;
        this.requiredParameterClientMethods = requiredParameterClientMethods;
        this.addContextParameter = addContextParameter || contextClientMethodParameter;
        this.contextClientMethodParameter = contextClientMethodParameter;
        this.generateSyncAsyncClients = generateSyncAsyncClients;
        this.syncMethods = SyncMethodsGeneration.fromValue(syncMethods);
        this.clientLogger = clientLogger;
        this.requiredFieldsAsConstructorArgs = requiredFieldsAsConstructorArgs;
        this.serviceInterfaceAsPublic = serviceInterfaceAsPublic;
        this.artifactId = artifactId;
        this.overrideSetterFromParent = overrideSetterFromSuperclass;
        this.optionalConstantAsEnum = optionalConstantAsEnum;
        this.lowLevelClient = lowLevelClient;
        this.useIterable = useIterable;
        this.serviceVersions = serviceVersions;
        this.requireXMsFlattenedToFlatten = requireXMsFlattenedToFlatten;
        this.clientFlattenAnnotationTarget = (clientFlattenAnnotationTarget == null || clientFlattenAnnotationTarget.isEmpty())
            ? ClientFlattenAnnotationTarget.TYPE
            : ClientFlattenAnnotationTarget.valueOf(clientFlattenAnnotationTarget.toUpperCase(Locale.ROOT));

        if (credentialType != null) {
            String[] splits = credentialType.split(",");
            this.credentialTypes = Arrays.stream(splits)
                .map(String::trim)
                .map(CredentialType::fromValue)
                .collect(Collectors.toSet());
        }
        if (credentialScopes != null) {
            String[] splits = credentialScopes.split(",");
            this.credentialScopes = Arrays.stream(splits)
                .map(String::trim)
                .map(split -> {
                    if (!split.startsWith("\"")) {
                        split = "\"" + split + "\"";
                    }
                    return split;
                })
                .collect(Collectors.toSet());
        }
        this.customizationJarPath = customizationJarPath;
        this.customizationClass = customizationClass;
        this.keyCredentialHeaderName = keyCredentialHeaderName;
        this.clientBuilderDisabled = clientBuilderDisabled;
        this.skipFormatting = skipFormatting;
        if (pollingConfig != null) {
            if (!pollingConfig.containsKey("default")) {
                pollingConfig.put("default", new PollingDetails());
            }
        }
        this.pollingConfig = pollingConfig;
        this.generateSamples = generateSamples;
        this.passDiscriminatorToChildDeserialization = passDiscriminatorToChildDeserialization;
        this.annotateGettersAndSettersForSerialization = annotateGettersAndSettersForSerialization;

        // Error HTTP status code exception type handling.
        this.defaultHttpExceptionType = defaultHttpExceptionType;
        this.useDefaultHttpStatusCodeToExceptionTypeMapping = useDefaultHttpStatusCodeToExceptionTypeMapping;
        this.httpStatusCodeToExceptionTypeMapping = httpStatusCodeToExceptionTypeMapping;

        this.handlePartialUpdate = handlePartialUpdate;
    }

    private String keyCredentialHeaderName;

    public String getKeyCredentialHeaderName() {
        return this.keyCredentialHeaderName;
    }

    private Set<CredentialType> credentialTypes;

    public Set<CredentialType> getCredentialTypes() {
        return credentialTypes;
    }

    private Set<String> credentialScopes;

    public Set<String> getCredentialScopes() {
        return credentialScopes;
    }

    private boolean azure;

    public final boolean isAzure() {
        return azure;
    }

    private String artifactId;

    public String getArtifactId() {
        return artifactId;
    }

    public enum Fluent {
        NONE, LITE, PREMIUM
    }

    private final Fluent fluent;

    public final boolean isFluent() {
        return fluent != Fluent.NONE;
    }

    public final boolean isFluentLite() {
        return fluent == Fluent.LITE;
    }

    public final boolean isFluentPremium() {
        return fluent == Fluent.PREMIUM;
    }

    public final boolean isAzureOrFluent() {
        return isAzure() || isFluent();
    }

    public enum ClientFlattenAnnotationTarget {
        TYPE, FIELD, NONE
    }

    // target for @JsonFlatten annotation for x-ms-client-flatten
    private final ClientFlattenAnnotationTarget clientFlattenAnnotationTarget;

    /**
     * @return When flatten client mode, where to put the <code>@JsonFlatten</code> annotation. If NONE, flatten at
     * getter/setter methods via codegen.
     */
    public ClientFlattenAnnotationTarget getClientFlattenAnnotationTarget() {
        return this.clientFlattenAnnotationTarget;
    }

    public static class ModelerSettings {
        private Map<String, Object> settings;

        public ModelerSettings(Map<String, Object> settings) {
            this.settings = settings == null ? Collections.emptyMap() : settings;
        }

        public Map<String, Object> getSettings() {
            return settings;
        }

        /**
         * If false, use client-flattened-annotation-target = TYPE for no flatten; client-flattened-annotation-target =
         * NONE for flatten at getter/setter methods via codegen.
         *
         * If true, use client-flattened-annotation-target = TYPE for <code>@JsonFlatten</code> on type (i.e. on class);
         * client-flattened-annotation-target = FIELD for <code>@JsonFlatten</code> on field.
         *
         * modelerfour.flatten-models = false and client-flattened-annotation-target = NONE would require
         * modelerfour.flatten-payloads = false.
         *
         * @return value of modelerfour.flatten-models
         */
        public boolean isFlattenModel() {
            return settings.containsKey("flatten-models") && (boolean) settings.get("flatten-models");
        }
    }

    private final ModelerSettings modelerSettings;

    public ModelerSettings getModelerSettings() {
        return modelerSettings;
    }

    private final AutorestSettings autorestSettings;

    public AutorestSettings getAutorestSettings() {
        return autorestSettings;
    }

    public Map<String, String> getSimpleJavaSettings() {
        return simpleJavaSettings;
    }

    private final boolean sdkIntegration;

    public boolean isSdkIntegration() {
        return sdkIntegration;
    }

    private boolean regeneratePom;

    public final boolean shouldRegeneratePom() {
        return regeneratePom;
    }

    private String fileHeaderText;

    public final String getFileHeaderText() {
        return fileHeaderText;
    }

    private int maximumJavadocCommentWidth;

    public final int getMaximumJavadocCommentWidth() {
        return maximumJavadocCommentWidth;
    }

    private String serviceName;

    public final String getServiceName() {
        return serviceName;
    }

    private String packageName;

    public final String getPackage() {
        return packageName;
    }

    public final String getPackage(String... packageSuffixes) {
        StringBuilder packageBuilder = new StringBuilder(packageName);
        if (packageSuffixes != null) {
            for (String packageSuffix : packageSuffixes) {
                if (packageSuffix != null && !packageSuffix.isEmpty()) {
                    // Cleanse the package suffix to remove leading and trailing periods.
                    String cleansedPackageSuffix = LEADING_PERIOD.matcher(packageSuffix).replaceAll("");
                    cleansedPackageSuffix = TRAILING_PERIOD.matcher(cleansedPackageSuffix).replaceAll("");

                    packageBuilder.append(".").append(cleansedPackageSuffix);
                }
            }
        }
        return packageBuilder.toString();
    }

    private boolean shouldGenerateXmlSerialization;

    public final boolean shouldGenerateXmlSerialization() {
        return shouldGenerateXmlSerialization;
    }

    /**
     * Whether to add the @NotNull annotation to required parameters in client methods.
     */
    private boolean nonNullAnnotations;

    public final boolean shouldNonNullAnnotations() {
        return nonNullAnnotations;
    }

    private boolean clientSideValidations;

    public final boolean shouldClientSideValidations() {
        return clientSideValidations;
    }

    /**
     * The prefix that will be added to each generated client type.
     */
    private String clientTypePrefix;

    public final String getClientTypePrefix() {
        return clientTypePrefix;
    }

    /**
     * Whether interfaces will be generated for Service and Method Group clients.
     */
    private boolean generateClientInterfaces;

    public final boolean shouldGenerateClientInterfaces() {
        return generateClientInterfaces;
    }

    /**
     * Whether interfaces will be generated for Service and Method Group clients.
     */
    private boolean generateClientAsImpl;

    public final boolean shouldGenerateClientAsImpl() {
        return generateClientAsImpl;
    }

    /**
     * The sub-package that the Service and Method Group client implementation classes will be put into.
     */
    private String implementationSubpackage;

    public final String getImplementationSubpackage() {
        return implementationSubpackage;
    }

    /**
     * The sub-package that Enums, Exceptions, and Model types will be put into.
     */
    private String modelsSubpackage;

    public final String getModelsSubpackage() {
        return modelsSubpackage;
    }

    private String fluentSubpackage;

    /**
     * @return The sub-package for Fluent SDK, that contains Client and Builder types, which is not recommended to be
     * used directly.
     */
    public final String getFluentSubpackage() {
        return fluentSubpackage;
    }

    /**
     * @return The sub-package for Fluent SDK, that contains Enums, Exceptions, and Model types, which is not
     * recommended being used directly.
     */
    public final String getFluentModelsSubpackage() {
        if (modelsSubpackage.contains(".")) {
            return fluentSubpackage + "." + modelsSubpackage.substring(modelsSubpackage.lastIndexOf(".") + 1);
        } else {
            return fluentSubpackage + "." + modelsSubpackage;
        }
    }

    /**
     * Whether Service and Method Group client method overloads that omit optional parameters will be created.
     */
    private boolean requiredParameterClientMethods;

    public final boolean getRequiredParameterClientMethods() {
        return requiredParameterClientMethods;
    }

    /**
     * Indicates whether the leading com.microsoft.rest.v3.Context parameter should be included in generated methods.
     */
    private boolean addContextParameter;

    public final boolean getAddContextParameter() {
        return addContextParameter;
    }

    private boolean contextClientMethodParameter;

    public final boolean isContextClientMethodParameter() {
        return contextClientMethodParameter;
    }

    private boolean generateSyncAsyncClients;

    public final boolean shouldGenerateSyncAsyncClients() {
        return generateSyncAsyncClients;
    }

    private SyncMethodsGeneration syncMethods = SyncMethodsGeneration.NONE; // no sync methods are generated by default

    public final SyncMethodsGeneration getSyncMethods() {
        return syncMethods;
    }

    private boolean requiredFieldsAsConstructorArgs;

    public boolean isRequiredFieldsAsConstructorArgs() {
        return requiredFieldsAsConstructorArgs;
    }

    private boolean serviceInterfaceAsPublic;

    public boolean isServiceInterfaceAsPublic() {
        return serviceInterfaceAsPublic;
    }

    public enum SyncMethodsGeneration {
        ALL,
        ESSENTIAL,
        NONE;

        public static SyncMethodsGeneration fromValue(String value) {
            if (value == null) {
                return null;
            } else if (value.equals("all")) {
                return ALL;
            } else if (value.equals("essential")) {
                return ESSENTIAL;
            } else if (value.equals("none")) {
                return NONE;
            }
            return null;
        }
    }

    private List<String> customTypes;

    public List<String> getCustomTypes() {
        return customTypes;
    }

    public boolean isCustomType(String typeName) {
        return customTypes.contains(typeName);
    }

    private String customTypesSubpackage;

    public final String getCustomTypesSubpackage() {
        return customTypesSubpackage;
    }

    public enum CredentialType {
        TOKEN_CREDENTIAL,
        AZURE_KEY_CREDENTIAL,
        NONE;

        public static CredentialType fromValue(String value) {
            if (value == null) {
                return null;
            } else if (value.equals("tokencredential")) {
                return TOKEN_CREDENTIAL;
            } else if (value.equals("azurekeycredential")) {
                return AZURE_KEY_CREDENTIAL;
            } else if (value.equals("none")) {
                return NONE;
            }
            return NONE;
        }
    }

    private boolean clientLogger;

    public final boolean shouldClientLogger() {
        return clientLogger;
    }

    private String customizationJarPath;

    public final String getCustomizationJarPath() {
        return customizationJarPath;
    }

    private String customizationClass;

    public final String getCustomizationClass() {
        return customizationClass;
    }

    boolean overrideSetterFromParent;
    boolean skipFormatting;

    /**
     * @return whether to override superclass setter method in model.
     */
    public boolean isOverrideSetterFromSuperclass() {
        return overrideSetterFromParent;
    }

    /**
     * @return whether to skip formatting java files.
     */
    public boolean isSkipFormatting() {
        return skipFormatting;
    }

    private final boolean optionalConstantAsEnum;

    public boolean isOptionalConstantAsEnum() {
        return optionalConstantAsEnum;
    }

    private final boolean lowLevelClient;

    public boolean isLowLevelClient() {
        return lowLevelClient;
    }

    private final boolean useIterable;

    public boolean shouldUseIterable() {
        return useIterable;
    }

    /**
     * Service version list. It maps to api-version parameter in swagger. Last one is the latest version, also default
     * version
     */
    private final List<String> serviceVersions;

    public List<String> getServiceVersions() {
        return serviceVersions;
    }

    private final boolean requireXMsFlattenedToFlatten;

    public boolean requireXMsFlattenedToFlatten() {
        return requireXMsFlattenedToFlatten;
    }

    private final boolean generateSamples;

    public boolean isGenerateSamples() {
        return generateSamples;
    }

    private final boolean clientBuilderDisabled;

    public boolean clientBuilderDisabled() {
        return clientBuilderDisabled;
    }

    public static class PollingDetails {
        @JsonProperty("strategy")
        private String strategy;
        @JsonProperty("intermediate-type")
        private String intermediateType;
        @JsonProperty("final-type")
        private String finalType;
        @JsonProperty("poll-interval")
        private String pollInterval;

        public String getStrategy() {
            if (strategy == null || "default".equalsIgnoreCase(strategy)) {
                return "new DefaultPollingStrategy<>({httpPipeline})";
            } else {
                return strategy;
            }
        }

        public String getIntermediateType() {
            return intermediateType;
        }

        public String getFinalType() {
            return finalType;
        }

        public int getPollIntervalInSeconds() {
            return pollInterval != null ? Integer.parseInt(pollInterval) : 1;
        }
    }

    private final Map<String, PollingDetails> pollingConfig;

    public PollingDetails getPollingConfig(String operation) {
        if (pollingConfig == null) {
            return null;
        }
        for (String key : pollingConfig.keySet()) {
            if (key.equalsIgnoreCase(operation)) {
                return pollingConfig.get(key);
            }
        }
        return pollingConfig.get("default");
    }

    private final boolean passDiscriminatorToChildDeserialization;

    public boolean isDiscriminatorPassedToChildDeserialization() {
        return passDiscriminatorToChildDeserialization;
    }

    private final boolean annotateGettersAndSettersForSerialization;

    /**
     * Whether model getters and setters should be annotated with Jackson JsonGetter and JsonSetter to handle
     * serialization.
     * <p>
     * For now, Fields will continue to be annotated with JsonProperty to ensure there are no backwards compatibility
     * breaking changes.
     *
     * @return Whether model getters and setters should be annotated to handle serialization.
     */
    public boolean isGettersAndSettersAnnotatedForSerialization() {
        return annotateGettersAndSettersForSerialization;
    }

    private final String defaultHttpExceptionType;

    /**
     * Gets the fully-qualified exception type that is used for error HTTP status codes.
     *
     * @return The fully-qualified exception type.
     */
    public String getDefaultHttpExceptionType() {
        return defaultHttpExceptionType;
    }

    private final boolean useDefaultHttpStatusCodeToExceptionTypeMapping;

    /**
     * Whether to use the default error HTTP status code to exception type mapping.
     *
     * @return Whether to use the default error HTTP status code to exception type mapping.
     */
    public boolean isUseDefaultHttpStatusCodeToExceptionTypeMapping() {
        return useDefaultHttpStatusCodeToExceptionTypeMapping;
    }

    private final Map<Integer, String> httpStatusCodeToExceptionTypeMapping;

    /**
     * Gets a read-only view of the custom error HTTP status code to exception type mapping.
     *
     * @return A read-only view of the custom error HTTP status code to exception type mapping.
     */
    public Map<Integer, String> getHttpStatusCodeToExceptionTypeMapping() {
        return httpStatusCodeToExceptionTypeMapping == null
            ? null : Collections.unmodifiableMap(httpStatusCodeToExceptionTypeMapping);
    }

    private final boolean handlePartialUpdate;

    public boolean isHandlePartialUpdate() {
        return handlePartialUpdate;
    }

    public static final String DefaultCodeGenerationHeader = String.join("\r\n",
        "Code generated by Microsoft (R) AutoRest Code Generator %s",
        "Changes may cause incorrect behavior and will be lost if the code is regenerated.");

    public static final String DefaultCodeGenerationHeaderWithoutVersion = String.join("\r\n",
        "Code generated by Microsoft (R) AutoRest Code Generator.",
        "Changes may cause incorrect behavior and will be lost if the code is regenerated.");

    public static final String MicrosoftApacheLicenseHeader = String.join("\r\n",
        "Copyright (c) Microsoft and contributors.  All rights reserved.\r\n",
        "Licensed under the Apache License, Version 2.0 (the \"License\");",
        "you may not use this file except in compliance with the License.",
        "You may obtain a copy of the License at",
        "  http://www.apache.org/licenses/LICENSE-2.0\r\n",
        "Unless required by applicable law or agreed to in writing, software",
        "distributed under the License is distributed on an \"AS IS\" BASIS,",
        "WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\r\n",
        "See the License for the specific language governing permissions and",
        "limitations under the License.",
        "");

    public static final String MicrosoftMitLicenseHeader = String.join("\r\n",
        "Copyright (c) Microsoft Corporation. All rights reserved.",
        "Licensed under the MIT License. See License.txt in the project root for license information.",
        "");

    public static final String MicrosoftMitSmallLicenseHeader = String.join("\r\n",
        "Copyright (c) Microsoft Corporation. All rights reserved.",
        "Licensed under the MIT License.",
        "");

    private static void loadStringSetting(String settingName, Consumer<String> action) {
        String settingValue = host.getStringValue(settingName);
        if (settingValue != null) {
            action.accept(settingValue);
        }
    }

    private static String getStringValue(NewPlugin host, String settingName) {
        String value = host.getStringValue(settingName);
        if (value != null) {
            simpleJavaSettings.put(settingName, value);
        }
        return value;
    }

    private static String getStringValue(NewPlugin host, String settingName, String defaultValue) {
        String ret = host.getStringValue(settingName);
        if (ret == null) {
            return defaultValue;
        } else {
            simpleJavaSettings.put(settingName, ret);
            return ret;
        }
    }

    private static boolean getBooleanValue(NewPlugin host, String settingName, boolean defaultValue) {
        Boolean ret = host.getBooleanValue(settingName);
        if (ret == null) {
            return defaultValue;
        } else {
            simpleJavaSettings.put(settingName, String.valueOf(ret));
            return ret;
        }
    }
}
