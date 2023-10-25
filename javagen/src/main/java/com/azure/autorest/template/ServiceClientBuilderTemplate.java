// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.template;

import com.azure.autorest.Javagen;
import com.azure.autorest.extension.base.model.codemodel.Scheme;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.extension.base.plugin.PluginLogger;
import com.azure.autorest.model.clientmodel.Annotation;
import com.azure.autorest.model.clientmodel.AsyncSyncClient;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientBuilder;
import com.azure.autorest.model.clientmodel.ClientBuilderTraitMethod;
import com.azure.autorest.model.clientmodel.PipelinePolicyDetails;
import com.azure.autorest.model.clientmodel.PrimitiveType;
import com.azure.autorest.model.clientmodel.SecurityInfo;
import com.azure.autorest.model.clientmodel.ServiceClient;
import com.azure.autorest.model.clientmodel.ServiceClientProperty;
import com.azure.autorest.model.javamodel.JavaBlock;
import com.azure.autorest.model.javamodel.JavaClass;
import com.azure.autorest.model.javamodel.JavaContext;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.model.javamodel.JavaVisibility;
import com.azure.autorest.util.ClientModelUtil;
import com.azure.autorest.util.CodeNamer;
import com.azure.autorest.util.TemplateUtil;
import com.azure.core.annotation.Generated;
import com.azure.core.http.HttpPipelinePosition;
import com.azure.core.http.policy.AddDatePolicy;
import com.azure.core.http.policy.AddHeadersFromContextPolicy;
import com.azure.core.http.policy.AddHeadersPolicy;
import com.azure.core.http.policy.AzureKeyCredentialPolicy;
import com.azure.core.http.policy.BearerTokenAuthenticationPolicy;
import com.azure.core.http.policy.RequestIdPolicy;
import com.azure.core.util.CoreUtils;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Writes a ServiceClient to a JavaFile.
 */
public class ServiceClientBuilderTemplate implements IJavaTemplate<ClientBuilder, JavaFile> {

    private final Logger logger = new PluginLogger(Javagen.getPluginInstance(), ServiceClientBuilderTemplate.class);

    private static final String LOCAL_VARIABLE_PREFIX = "local";
    private static final ServiceClientBuilderTemplate INSTANCE = new ServiceClientBuilderTemplate();

    private static final String JACKSON_SERIALIZER = "JacksonAdapter.createDefaultSerializerAdapter()";

    protected ServiceClientBuilderTemplate() {
    }

    public static ServiceClientBuilderTemplate getInstance() {
        return INSTANCE;
    }

    public final void write(ClientBuilder clientBuilder, JavaFile javaFile) {
        JavaSettings settings = JavaSettings.getInstance();
        ServiceClient serviceClient = clientBuilder.getServiceClient();
        String serviceClientBuilderName = clientBuilder.getClassName();

        ArrayList<ServiceClientProperty> commonProperties = addCommonClientProperties(settings, serviceClient.getSecurityInfo());

        String buildReturnType;
        if (!settings.isFluent() && settings.isGenerateClientInterfaces()) {
            buildReturnType = serviceClient.getInterfaceName();
        } else {
            buildReturnType = serviceClient.getClassName();
        }

        Set<String> imports = new HashSet<>();
        serviceClient.addImportsTo(imports, false, true, settings);
        commonProperties.forEach(p -> p.addImportsTo(imports, false));
        imports.add("java.util.List");
        imports.add("java.util.Map");
        imports.add("java.util.HashMap");
        imports.add("java.util.ArrayList");
        ClassType.HttpHeaders.addImportsTo(imports, false);
        ClassType.HTTP_HEADER_NAME.addImportsTo(imports, false);
        imports.add("java.util.Objects");
        if (settings.isUseClientLogger()) {
            ClassType.ClientLogger.addImportsTo(imports, false);
        }
        addServiceClientBuilderAnnotationImport(imports);
        addHttpPolicyImports(imports);
        addImportForCoreUtils(imports);
        addSerializerImport(imports, settings);
        addGeneratedImport(imports);
        addTraitsImports(clientBuilder, imports);

        List<AsyncSyncClient> asyncClients = clientBuilder.getAsyncClients();
        List<AsyncSyncClient> syncClients = clientBuilder.getSyncClients();

        StringBuilder builderTypes = new StringBuilder();
        builderTypes.append("{");
        if (JavaSettings.getInstance().isGenerateSyncAsyncClients()) {
            List<AsyncSyncClient> clients = new ArrayList<>(syncClients);
            if (!settings.isFluentLite()) {
                clients.addAll(asyncClients);
            }
            boolean first = true;
            for (AsyncSyncClient client : clients) {
                if (first) {
                    first = false;
                } else {
                    builderTypes.append(", ");
                }
                builderTypes.append(client.getClassName()).append(".class");

                client.addImportsTo(imports, false);
            }
        } else {
            builderTypes.append(serviceClient.getClassName()).append(".class");
        }
        builderTypes.append("}");
        javaFile.declareImport(imports);

        javaFile.javadocComment(comment -> {
            String clientTypeName = settings.isFluent() ? serviceClient.getClassName() : serviceClient.getInterfaceName();
            if (settings.isGenerateBuilderPerClient() && clientBuilder.getSyncClients().size() == 1) {
                clientTypeName = clientBuilder.getSyncClients().iterator().next().getClassName();
            }
            comment.description(String.format("A builder for creating a new instance of the %1$s type.", clientTypeName));
        });

        javaFile.annotation(String.format("ServiceClientBuilder(serviceClients = %1$s)", builderTypes));
        String classDefinition = serviceClientBuilderName;

        if (!settings.isAzureOrFluent()) {
            String serviceClientBuilderGeneric = "<" + serviceClientBuilderName + ">";

            String interfaces = clientBuilder.getBuilderTraits().stream()
                    .map(trait -> trait.getTraitInterfaceName() + serviceClientBuilderGeneric)
                    .collect(Collectors.joining(", "));

            classDefinition = serviceClientBuilderName + " implements " + interfaces;
        }

        javaFile.publicFinalClass(classDefinition, classBlock ->
        {
            if (!settings.isAzureOrFluent()) {
                // sdk name
                addGeneratedAnnotation(classBlock);
                classBlock.privateStaticFinalVariable("String SDK_NAME = \"name\"");

                // sdk version
                addGeneratedAnnotation(classBlock);
                classBlock.privateStaticFinalVariable("String SDK_VERSION = \"version\"");

                // default scope
                Set<String> scopes = serviceClient.getSecurityInfo() != null ? serviceClient.getSecurityInfo().getScopes() : null;
                if (scopes != null && !scopes.isEmpty()) {
                    addGeneratedAnnotation(classBlock);
                    classBlock.privateStaticFinalVariable(String.format("String[] DEFAULT_SCOPES = new String[] {%s}",
                            String.join(", ", scopes)));
                }

                // properties for sdk name and version
                String propertiesValue = "new HashMap<>()";
                String artifactId = ClientModelUtil.getArtifactId();
                if (!CoreUtils.isNullOrEmpty(artifactId)) {
                    propertiesValue = "CoreUtils.getProperties" + "(\"" + artifactId + ".properties\")";
                }
                addGeneratedAnnotation(classBlock);
                classBlock.privateStaticFinalVariable(String.format("Map<String, String> PROPERTIES = %s", propertiesValue));

                addGeneratedAnnotation(classBlock);
                classBlock.privateFinalMemberVariable("List<HttpPipelinePolicy>", "pipelinePolicies");

                // constructor
                classBlock.javadocComment(String.format("Create an instance of the %s.", serviceClientBuilderName));
                addGeneratedAnnotation(classBlock);
                classBlock.publicConstructor(String.format("%1$s()", serviceClientBuilderName), javaBlock -> {
                    javaBlock.line("this.pipelinePolicies = new ArrayList<>();");
                });
            }

            Stream<ServiceClientProperty> serviceClientPropertyStream = serviceClient.getProperties().stream()
                    .filter(p -> !p.isReadOnly());
            if (!settings.isAzureOrFluent()) {
                addTraitMethods(clientBuilder, settings, serviceClientBuilderName, classBlock);
                serviceClientPropertyStream = serviceClientPropertyStream
                        .filter(property -> !(clientBuilder.getBuilderTraits().stream()
                        .flatMap(trait -> trait.getTraitMethods().stream().filter(traitMethod -> traitMethod.getProperty() != null))
                        .anyMatch(traitMethod -> property.getName().equals(traitMethod.getProperty().getName()))));
            }

            // Add ServiceClient client property variables, getters, and setters
            List<ServiceClientProperty> clientProperties = Stream
                    .concat(serviceClientPropertyStream,
                            commonProperties.stream()).collect(Collectors.toList());

            for (ServiceClientProperty serviceClientProperty : clientProperties) {
                classBlock.blockComment(settings.getMaximumJavadocCommentWidth(), comment ->
                {
                    comment.line(serviceClientProperty.getDescription());
                });
                addGeneratedAnnotation(classBlock);
                String propertyVariableInit = String.format("%1$s%2$s %3$s",
                        serviceClientProperty.isReadOnly() ? "final " : "",
                        serviceClientProperty.getType(),
                        serviceClientProperty.getName());
                if (serviceClientProperty.getDefaultValueExpression() != null
                        && serviceClientProperty.getType() instanceof PrimitiveType) {
                    // init to default value
                    propertyVariableInit += String.format(" = %1$s", serviceClientProperty.getDefaultValueExpression());
                }
                classBlock.privateMemberVariable(propertyVariableInit);

                if (!serviceClientProperty.isReadOnly()) {
                    classBlock.javadocComment(comment ->
                    {
                        comment.description(String.format("Sets %1$s", serviceClientProperty.getDescription()));
                        comment.param(serviceClientProperty.getName(), String.format("the %1$s value.", serviceClientProperty.getName()));
                        comment.methodReturns(String.format("the %1$s", serviceClientBuilderName));
                    });
                    addGeneratedAnnotation(classBlock);
                    classBlock.publicMethod(String.format("%1$s %2$s(%3$s %4$s)", serviceClientBuilderName,
                            CodeNamer.toCamelCase(serviceClientProperty.getAccessorMethodSuffix()), serviceClientProperty.getType(),
                            serviceClientProperty.getName()), function ->
                    {
                        function.line(String.format("this.%1$s = %2$s;", serviceClientProperty.getName(), serviceClientProperty.getName()));
                        function.methodReturn("this");
                    });
                }
            }

            String buildMethodName = this.primaryBuildMethodName(settings);

            JavaVisibility visibility = settings.isGenerateSyncAsyncClients() ? JavaVisibility.Private : JavaVisibility.Public;

            // build method
            classBlock.javadocComment(comment ->
            {
                comment.description(String.format("Builds an instance of %1$s with the provided parameters", buildReturnType));
                comment.methodReturns(String.format("an instance of %1$s", buildReturnType));
            });
            addGeneratedAnnotation(classBlock);
            classBlock.method(visibility, null, String.format("%1$s %2$s()", buildReturnType, buildMethodName), function ->
            {
                List<ServiceClientProperty> allProperties = new ArrayList<>();
                if (!settings.isAzureOrFluent()) {
                    allProperties.addAll(clientBuilder.getBuilderTraits()
                            .stream()
                            .flatMap(trait -> trait.getTraitMethods().stream())
                            .map(ClientBuilderTraitMethod::getProperty)
                            .filter(Objects::nonNull)
                            .collect(Collectors.toList()));
                }
                allProperties.addAll(clientProperties);

                for (ServiceClientProperty serviceClientProperty : allProperties) {
                    if (serviceClientProperty.getDefaultValueExpression() != null
                            && !(serviceClientProperty.getType() instanceof PrimitiveType)) {
                        function.line(String.format("%1$s %2$s = (%3$s != null) ? %4$s : %5$s;",
                                serviceClientProperty.getType(),
                                getLocalBuildVariableName(serviceClientProperty.getName()),
                                serviceClientProperty.getName(),
                                serviceClientProperty.getName(),
                                serviceClientProperty.getDefaultValueExpression()));

                    }
                }

                // additional service client properties in constructor arguments
                String constructorArgs = serviceClient.getProperties().stream()
                        .filter(p -> !p.isReadOnly())
                        .map(this::getClientConstructorArgName)
                        .collect(Collectors.joining(", "));
                if (!constructorArgs.isEmpty()) {
                    constructorArgs = ", " + constructorArgs;
                }

                final String serializerExpression;
                if (settings.isDataPlaneClient()) {
                    serializerExpression = JACKSON_SERIALIZER;
                } else {
                    serializerExpression = getLocalBuildVariableName(getSerializerMemberName());
                }

                if (settings.isFluent()) {
                    function.line(String.format("%1$s client = new %2$s(%3$s, %4$s, %5$s, %6$s%7$s);",
                            serviceClient.getClassName(),
                            serviceClient.getClassName(),
                            getLocalBuildVariableName("pipeline"),
                            serializerExpression,
                            getLocalBuildVariableName("defaultPollInterval"),
                            getLocalBuildVariableName("environment"),
                            constructorArgs));
                } else {
                    function.line(String.format("%1$s client = new %2$s(%3$s, %4$s%5$s);",
                            serviceClient.getClassName(), serviceClient.getClassName(),
                            getLocalBuildVariableName("pipeline"), serializerExpression, constructorArgs));
                }
                function.line("return client;");
            });

            if (!settings.isAzureOrFluent()) {
                addCreateHttpPipelineMethod(settings, classBlock, serviceClient.getDefaultCredentialScopes(), serviceClient.getSecurityInfo(), serviceClient.getPipelinePolicyDetails());
            }

            if (JavaSettings.getInstance().isGenerateSyncAsyncClients()) {
                if (!settings.isFluentLite()) {
                    for (AsyncSyncClient asyncClient : asyncClients) {
                        final boolean wrapServiceClient = asyncClient.getMethodGroupClient() == null;

                        classBlock.javadocComment(comment ->
                        {
                            comment.description(String
                                    .format("Builds an instance of %1$s class", asyncClient.getClassName()));
                            comment.methodReturns(String.format("an instance of %1$s", asyncClient.getClassName()));
                        });
                        addGeneratedAnnotation(classBlock);
                        classBlock.publicMethod(String.format("%1$s %2$s()", asyncClient.getClassName(), clientBuilder.getBuilderMethodNameForAsyncClient(asyncClient)),
                                function -> {
                                    if (wrapServiceClient) {
                                        function.line("return new %1$s(%2$s());", asyncClient.getClassName(), buildMethodName);
                                    } else {
                                        function.line("return new %1$s(%2$s().get%3$s());", asyncClient.getClassName(), buildMethodName,
                                                CodeNamer.toPascalCase(asyncClient.getMethodGroupClient().getVariableName()));
                                    }
                                });
                    }
                }

                int syncClientIndex = 0;
                for (AsyncSyncClient syncClient : syncClients) {
                    final boolean wrapServiceClient = syncClient.getMethodGroupClient() == null;

                    AsyncSyncClient asyncClient = (asyncClients.size() == syncClients.size()) ? asyncClients.get(syncClientIndex) : null;

                    classBlock.javadocComment(comment ->
                    {
                        comment.description(String
                                .format("Builds an instance of %1$s class", syncClient.getClassName()));
                        comment.methodReturns(String.format("an instance of %1$s", syncClient.getClassName()));
                    });
                    addGeneratedAnnotation(classBlock);
                    classBlock.publicMethod(String.format("%1$s %2$s()", syncClient.getClassName(), clientBuilder.getBuilderMethodNameForSyncClient(syncClient)),
                            function -> {
                                writeSyncClientBuildMethod(syncClient, asyncClient, function, buildMethodName, wrapServiceClient);
                            });

                    ++syncClientIndex;
                }
            }

            TemplateUtil.addClientLogger(classBlock, serviceClientBuilderName, javaFile.getContents());
        });
    }

    /**
     * Renames the provided variable name to localize it to the method
     * @param baseName The base variable name.
     * @return The name of the local variable.
     */
    private String getLocalBuildVariableName(String baseName) {
        return LOCAL_VARIABLE_PREFIX + CodeNamer.toPascalCase(baseName);
    }

    private String getClientConstructorArgName(ServiceClientProperty property) {
        if (property.getDefaultValueExpression() != null
                && !(property.getType() instanceof PrimitiveType)) {
            return getLocalBuildVariableName((property.getName()));
        }
        return "this." + property.getName();
    }

    private void addTraitMethods(ClientBuilder clientBuilder, JavaSettings settings, String serviceClientBuilderName, JavaClass classBlock) {
        clientBuilder.getBuilderTraits().stream().flatMap(trait -> trait.getTraitMethods().stream())
                .forEach(traitMethod -> {
                    ServiceClientProperty serviceClientProperty = traitMethod.getProperty();
                    if (serviceClientProperty != null) {
                        classBlock.blockComment(settings.getMaximumJavadocCommentWidth(), comment ->
                        {
                            comment.line(serviceClientProperty.getDescription());
                        });
                        addGeneratedAnnotation(classBlock);
                        classBlock.privateMemberVariable(String.format("%1$s%2$s %3$s",
                                serviceClientProperty.isReadOnly() ? "final " : "",
                                serviceClientProperty.getType(),
                                serviceClientProperty.getName()));
                    }
                    classBlock.javadocComment(comment -> comment.description(traitMethod.getDocumentation()));
                    addGeneratedAnnotation(classBlock);
                    addOverrideAnnotation(classBlock);
                    classBlock.publicMethod(String.format("%1$s %2$s(%3$s %4$s)", serviceClientBuilderName,
                            traitMethod.getMethodName(), traitMethod.getMethodParamType(),
                            traitMethod.getMethodParamName()), traitMethod.getMethodImpl());
                });
    }

    /**
     * Extension to write sync client build method invocation
     *
     * @param syncClient the sync client
     * @param asyncClient the async client
     * @param function the method block to write method invocation
     * @param buildMethodName the name of build method
     * @param wrapServiceClient whether the sync client wraps a service client implementation or method group implementation
     */
    protected void writeSyncClientBuildMethod(AsyncSyncClient syncClient, AsyncSyncClient asyncClient, JavaBlock function,
                                              String buildMethodName, boolean wrapServiceClient) {
        JavaSettings settings = JavaSettings.getInstance();
        boolean syncClientWrapAsync = settings.isSyncClientWrapAsyncClient()
                && settings.isDataPlaneClient()
                && asyncClient != null;
        if (syncClientWrapAsync) {
            writeSyncClientBuildMethodFromAsyncClient(syncClient, asyncClient, function, buildMethodName, wrapServiceClient);
        } else {
            writeSyncClientBuildMethodFromInnerClient(syncClient, function, buildMethodName, wrapServiceClient);
        }
    }

    protected void writeSyncClientBuildMethodFromInnerClient(AsyncSyncClient syncClient, JavaBlock function,
                                                             String buildMethodName, boolean wrapServiceClient) {
        if (wrapServiceClient) {
            function.line("return new %1$s(%2$s());", syncClient.getClassName(), buildMethodName);
        } else {
            function.line("return new %1$s(%2$s().get%3$s());", syncClient.getClassName(), buildMethodName,
                    CodeNamer.toPascalCase(syncClient.getMethodGroupClient().getVariableName()));
        }
    }

    protected void writeSyncClientBuildMethodFromAsyncClient(AsyncSyncClient syncClient, AsyncSyncClient asyncClient, JavaBlock function,
                                                             String buildMethodName, boolean wrapServiceClient) {
        if (wrapServiceClient) {
            function.line("return new %1$s(new %2$s(%3$s()));", syncClient.getClassName(), asyncClient.getClassName(),
                    buildMethodName);
        } else {
            function.line("return new %1$s(new %2$s(%3$s().get%4$s()));", syncClient.getClassName(), asyncClient.getClassName(),
                    buildMethodName, CodeNamer.toPascalCase(syncClient.getMethodGroupClient().getVariableName()));
        }
    }

    protected String getSerializerMemberName() {
        return "serializerAdapter";
    }

    protected void addSerializerImport(Set<String> imports, JavaSettings settings) {
        imports.add(settings.isFluent() ? "com.azure.core.management.serializer.SerializerFactory" : "com.azure.core.util.serializer.JacksonAdapter");
    }

    protected void addImportForCoreUtils(Set<String> imports) {
        ClassType.CORE_UTILS.addImportsTo(imports, false);
        imports.add("com.azure.core.util.builder.ClientBuilderUtil");
    }

    protected void addHttpPolicyImports(Set<String> imports) {
        imports.add(BearerTokenAuthenticationPolicy.class.getName());

        // one of the key credential policy imports will be removed by the formatter depending
        // on which one is used
        imports.add(AzureKeyCredentialPolicy.class.getName());
        ClassType.KEY_CREDENTIAL_POLICY.addImportsTo(imports, false);

        ClassType.HTTP_POLICY_PROVIDERS.addImportsTo(imports, false);
        ClassType.HttpPipelinePolicy.addImportsTo(imports, false);
        ClassType.HTTP_LOGGING_POLICY.addImportsTo(imports, false);
        imports.add(AddHeadersPolicy.class.getName());
        imports.add(RequestIdPolicy.class.getName());
        imports.add(AddHeadersFromContextPolicy.class.getName());
        imports.add(AddDatePolicy.class.getName());
        imports.add(HttpPipelinePosition.class.getName());
        imports.add(Collectors.class.getName());
    }

    protected void addTraitsImports(ClientBuilder clientBuilder, Set<String> imports) {
        clientBuilder.getBuilderTraits().stream().forEach(trait -> imports.addAll(trait.getImportPackages()));
    }

    protected void addServiceClientBuilderAnnotationImport(Set<String> imports) {
        Annotation.SERVICE_CLIENT_BUILDER.addImportsTo(imports);
    }

    protected void addCreateHttpPipelineMethod(JavaSettings settings, JavaClass classBlock,
                                               String defaultCredentialScopes, SecurityInfo securityInfo,
                                               PipelinePolicyDetails pipelinePolicyDetails) {
        addGeneratedAnnotation(classBlock);
        classBlock.privateMethod("HttpPipeline createHttpPipeline()", function -> {
            function.line("Configuration buildConfiguration = (configuration == null) ? Configuration"
                    + ".getGlobalConfiguration() : configuration;");


            String localHttpOptionsName = getLocalBuildVariableName("httpLogOptions");
            String localClientOptionsName = getLocalBuildVariableName("ClientOptions");
            function.line(String.format("HttpLogOptions %s = this.httpLogOptions == null ? new HttpLogOptions() : this.httpLogOptions;", localHttpOptionsName));
            function.line(String.format("ClientOptions %s = this.clientOptions == null ? new ClientOptions() : this.clientOptions;", localClientOptionsName));

            function.line("List<HttpPipelinePolicy> policies = new ArrayList<>();");

            function.line("String clientName = PROPERTIES.getOrDefault(SDK_NAME, \"UnknownName\");");
            function.line("String clientVersion = PROPERTIES.getOrDefault(SDK_VERSION, \"UnknownVersion\");");

            function.line(String.format("String applicationId = CoreUtils.getApplicationId(%s, %s);", localClientOptionsName, localHttpOptionsName));
            function.line("policies.add(new UserAgentPolicy(applicationId, clientName, "
                    + "clientVersion, buildConfiguration));");

            if (pipelinePolicyDetails != null && !CoreUtils.isNullOrEmpty(pipelinePolicyDetails.getRequestIdHeaderName())) {
                function.line(String.format("policies.add(new RequestIdPolicy(\"%s\"));", pipelinePolicyDetails.getRequestIdHeaderName()));
            } else {
                function.line("policies.add(new RequestIdPolicy());");
            }
            function.line("policies.add(new AddHeadersFromContextPolicy());");

            // clientOptions header
            function.line(String.format("%1$s headers = new %1$s();", ClassType.HttpHeaders.getName()));
            function.line(String.format("%s.getHeaders().forEach(header -> headers.set(HttpHeaderName.fromString(header.getName()), header.getValue()));", localClientOptionsName));
            function.ifBlock("headers.getSize() > 0", block -> block.line("policies.add(new AddHeadersPolicy(headers));"));

            function.line("this.pipelinePolicies.stream()" +
                    ".filter(p -> p.getPipelinePosition() == HttpPipelinePosition.PER_CALL)" +
                    ".forEach(p -> policies.add(p));");
            function.line("HttpPolicyProviders.addBeforeRetryPolicies(policies);");
            function.line("policies.add(ClientBuilderUtil.validateAndGetRetryPolicy(retryPolicy, retryOptions, new " +
                    "RetryPolicy()));");
            function.line("policies.add(new AddDatePolicy());");

            if (securityInfo.getSecurityTypes().contains(Scheme.SecuritySchemeType.KEY)) {
                if (CoreUtils.isNullOrEmpty(securityInfo.getHeaderName())) {
                    logger.error("key-credential-header-name is required for " +
                            "key-based credential type");
                    throw new IllegalStateException("key-credential-header-name is required for " +
                            "key-based credential type");
                }

                if (settings.isUseKeyCredential()) {
                    function.ifBlock("keyCredential != null", action -> {
                        if (CoreUtils.isNullOrEmpty(securityInfo.getHeaderValuePrefix())) {
                            function.line("policies.add(new KeyCredentialPolicy(\""
                                    + securityInfo.getHeaderName()
                                    + "\", keyCredential));");
                        } else {
                            function.line("policies.add(new KeyCredentialPolicy(\""
                                    + securityInfo.getHeaderName()
                                    + "\", keyCredential, \""
                                    + securityInfo.getHeaderValuePrefix()
                                    + "\"));");
                        }
                    });
                } else {
                    function.ifBlock("azureKeyCredential != null", action -> {
                        if (CoreUtils.isNullOrEmpty(securityInfo.getHeaderValuePrefix())) {
                            function.line("policies.add(new AzureKeyCredentialPolicy(\""
                                    + securityInfo.getHeaderName()
                                    + "\", azureKeyCredential));");
                        } else {
                            function.line("policies.add(new AzureKeyCredentialPolicy(\""
                                    + securityInfo.getHeaderName()
                                    + "\", azureKeyCredential, \""
                                    + securityInfo.getHeaderValuePrefix()
                                    + "\"));");
                        }
                    });
                }
            }
            if (securityInfo.getSecurityTypes().contains(Scheme.SecuritySchemeType.OAUTH2)) {
                function.ifBlock("tokenCredential != null", action -> {
                    function.line("policies.add(new BearerTokenAuthenticationPolicy(tokenCredential, %s));", defaultCredentialScopes);
                });
            }
            function.line("this.pipelinePolicies.stream()" +
                    ".filter(p -> p.getPipelinePosition() == HttpPipelinePosition.PER_RETRY)" +
                    ".forEach(p -> policies.add(p));");
            function.line("HttpPolicyProviders.addAfterRetryPolicies(policies);");

            function.line("policies.add(new HttpLoggingPolicy(httpLogOptions));");

            function.line("HttpPipeline httpPipeline = new HttpPipelineBuilder()" +
                    ".policies(policies.toArray(new HttpPipelinePolicy[0]))" +
                    ".httpClient(httpClient)" +
                    String.format(".clientOptions(%s)", localClientOptionsName) +
                    ".build();");
            function.methodReturn("httpPipeline");
        });
    }

    protected ArrayList<ServiceClientProperty> addCommonClientProperties(JavaSettings settings, SecurityInfo securityInfo) {
        ArrayList<ServiceClientProperty> commonProperties = new ArrayList<ServiceClientProperty>();
        if (settings.isAzureOrFluent()) {
            commonProperties.add(new ServiceClientProperty("The environment to connect to", ClassType.AzureEnvironment, "environment", false, "AzureEnvironment.AZURE"));
            commonProperties.add(new ServiceClientProperty("The HTTP pipeline to send requests through", ClassType.HttpPipeline, "pipeline", false,
                            "new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy()).build()"));
        }
        if (settings.isFluent()) {
            commonProperties.add(new ServiceClientProperty("The default poll interval for long-running operation", ClassType.Duration, "defaultPollInterval", false, "Duration.ofSeconds(30)"));
        }

        // Low-level client does not need serializer. It returns BinaryData.
        if (!settings.isDataPlaneClient()) {
            commonProperties.add(new ServiceClientProperty("The serializer to serialize an object into a string",
                    ClassType.SerializerAdapter, getSerializerMemberName(), false,
                    settings.isFluent() ? "SerializerFactory.createDefaultManagementSerializerAdapter()" : JACKSON_SERIALIZER));
        }

        if (!settings.isAzureOrFluent()) {
            commonProperties.add(new ServiceClientProperty("The retry policy that will attempt to retry failed "
                    + "requests, if applicable.", ClassType.RETRY_POLICY, "retryPolicy", false, null));
        }
        return commonProperties;
    }

    /**
     * Extension for the name of build method.
     *
     * @return The name of build method.
     */
    protected String primaryBuildMethodName(JavaSettings settings) {
        return settings.isGenerateSyncAsyncClients()
                ? "buildInnerClient"
                : "buildClient";
    }

    protected void addGeneratedImport(Set<String> imports) {
        Annotation.GENERATED.addImportsTo(imports);
    }

    protected void addGeneratedAnnotation(JavaContext classBlock) {
        classBlock.annotation(Generated.class.getSimpleName());
    }

    protected void addOverrideAnnotation(JavaContext classBlock) {
        classBlock.annotation("Override");
    }
}
