// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

package com.azure.autorest.template;

import com.azure.autorest.Javagen;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.extension.base.plugin.JavaSettings.CredentialType;
import com.azure.autorest.model.clientmodel.*;
import com.azure.autorest.model.javamodel.JavaClass;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.model.javamodel.JavaVisibility;
import com.azure.autorest.util.ClientModelUtil;
import com.azure.autorest.util.CodeNamer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Writes a ServiceClient to a JavaFile.
 */
public class ServiceClientBuilderTemplate implements IJavaTemplate<ServiceClient, JavaFile> {
    private static ServiceClientBuilderTemplate _instance = new ServiceClientBuilderTemplate();

    protected ServiceClientBuilderTemplate() {
    }

    public static ServiceClientBuilderTemplate getInstance() {
        return _instance;
    }

    public final void write(ServiceClient serviceClient, JavaFile javaFile) {
        JavaSettings settings = JavaSettings.getInstance();
        String serviceClientBuilderName = serviceClient.getInterfaceName() + ClientModelUtil.getBuilderSuffix();

        ArrayList<ServiceClientProperty> commonProperties = addCommonClientProperties(settings);

        String buildReturnType;
        if (!settings.isFluent() && settings.shouldGenerateClientInterfaces()) {
            buildReturnType = serviceClient.getInterfaceName();
        } else {
            buildReturnType = serviceClient.getClassName();
        }

        Set<String> imports = new HashSet<String>();
        serviceClient.addImportsTo(imports, false, true, settings);
        commonProperties.forEach(p -> p.addImportsTo(imports, false));
        imports.add("java.util.List");
        imports.add("java.util.Map");
        imports.add("java.util.HashMap");
        imports.add("java.util.ArrayList");
        addServiceClientBuilderAnnotationImport(imports);
        addHttpPolicyImports(imports);
        addImportForCoreUtils(imports);
        addSerializerImport(imports, settings);

        List<AsyncSyncClient> asyncClients = new ArrayList<>();
        List<AsyncSyncClient> syncClients = new ArrayList<>();
        if (JavaSettings.getInstance().shouldGenerateSyncAsyncClients()) {
            ClientModelUtil.getAsyncSyncClients(serviceClient, asyncClients, syncClients);
        }
        final boolean singleBuilder = asyncClients.size() == 1;

        StringBuilder builderTypes = new StringBuilder();
        builderTypes.append("{");
        if (JavaSettings.getInstance().shouldGenerateSyncAsyncClients()) {
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

        javaFile.javadocComment(comment ->
        {
            String serviceClientTypeName = settings.isFluent() ? serviceClient.getClassName() : serviceClient.getInterfaceName();
            comment.description(String.format("A builder for creating a new instance of the %1$s type.", serviceClientTypeName));
        });

        javaFile.annotation(String.format("ServiceClientBuilder(serviceClients = %1$s)", builderTypes.toString()));

        javaFile.publicFinalClass(serviceClientBuilderName, classBlock ->
        {
            if (!settings.isAzureOrFluent()) {
                classBlock.privateStaticFinalVariable("String SDK_NAME = \"name\"");
                classBlock.privateStaticFinalVariable("String SDK_VERSION = \"version\"");
                Set<String> scopes = JavaSettings.getInstance().getCredentialScopes();
                if (scopes != null && !scopes.isEmpty()) {
                    classBlock.packagePrivateStaticFinalVariable(String.format("String[] DEFAULT_SCOPES = new String[] {%s}",
                            String.join(", ", scopes)));
                }
                String propertiesValue = "new HashMap<>()";
                if (!settings.getArtifactId().isEmpty()) {
                    propertiesValue = "CoreUtils.getProperties" + "(\"" + settings.getArtifactId() + ".properties\")";
                }
                classBlock.privateFinalMemberVariable("Map<String, String>", "properties", propertiesValue);
                classBlock.javadocComment(String.format("Create an instance of the %s.", serviceClientBuilderName));
                classBlock.publicConstructor(String.format("%1$s()", serviceClientBuilderName), javaBlock -> {
                    javaBlock.line("this.pipelinePolicies = new ArrayList<>();");
                });
            }
            // Add ServiceClient client property variables, getters, and setters
            List<ServiceClientProperty> clientProperties = Stream
                    .concat(serviceClient.getProperties().stream().filter(p -> !p.isReadOnly()),
                            commonProperties.stream()).collect(Collectors.toList());

            for (ServiceClientProperty serviceClientProperty : clientProperties) {
                classBlock.blockComment(settings.getMaximumJavadocCommentWidth(), comment ->
                {
                    comment.line(serviceClientProperty.getDescription());
                });
                classBlock.privateMemberVariable(String.format("%1$s%2$s %3$s",
                        serviceClientProperty.isReadOnly() ? "final " : "",
                        serviceClientProperty.getType(),
                        serviceClientProperty.getName()));

                if (!serviceClientProperty.isReadOnly()) {
                    classBlock.javadocComment(comment ->
                    {
                        comment.description(String.format("Sets %1$s", serviceClientProperty.getDescription()));
                        comment.param(serviceClientProperty.getName(), String.format("the %1$s value.", serviceClientProperty.getName()));
                        comment.methodReturns(String.format("the %1$s", serviceClientBuilderName));
                    });

                    classBlock.publicMethod(String.format("%1$s %2$s(%3$s %4$s)", serviceClientBuilderName,
                            CodeNamer.toCamelCase(serviceClientProperty.getAccessorMethodSuffix()), serviceClientProperty.getType(),
                            serviceClientProperty.getName()), function ->
                    {
                        function.line(String.format("this.%1$s = %2$s;", serviceClientProperty.getName(), serviceClientProperty.getName()));
                        function.methodReturn("this");
                    });
                }
            }

            if (!settings.isAzureOrFluent()) {
                classBlock.javadocComment(comment ->
                {
                    comment.description("Adds a custom Http pipeline policy.");
                    comment.param("customPolicy", "The custom Http pipeline policy to add.");
                    comment.methodReturns(String.format("the %1$s", serviceClientBuilderName));
                });
                classBlock.publicMethod(String.format("%1$s %2$s(%3$s %4$s)", serviceClientBuilderName,
                        "addPolicy", "HttpPipelinePolicy", "customPolicy"), function -> {
                    function.line("pipelinePolicies.add(customPolicy);");
                    function.methodReturn("this");
                });
            }

            String buildMethodName = this.primaryBuildMethodName(settings);
            JavaVisibility visibility = settings.shouldGenerateSyncAsyncClients() ? JavaVisibility.Private : JavaVisibility.Public;

            // build method
            classBlock.javadocComment(comment ->
            {
                comment.description(String.format("Builds an instance of %1$s with the provided parameters", buildReturnType));
                comment.methodReturns(String.format("an instance of %1$s", buildReturnType));
            });
            classBlock.method(visibility, null, String.format("%1$s %2$s()", buildReturnType, buildMethodName), function ->
            {
                for (ServiceClientProperty serviceClientProperty : clientProperties) {
                    if (serviceClientProperty.getDefaultValueExpression() != null) {
                        function.ifBlock(String.format("%1$s == null", serviceClientProperty.getName()), ifBlock ->
                        {
                            function.line(String.format("this.%1$s = %2$s;", serviceClientProperty.getName(), serviceClientProperty.getDefaultValueExpression()));
                        });
                    }
                }

                // additional service client properties in constructor arguments
                String constructorArgs = serviceClient.getProperties().stream()
                        .filter(p -> !p.isReadOnly())
                        .map(ServiceClientProperty::getName)
                        .collect(Collectors.joining(", "));
                if (!constructorArgs.isEmpty()) {
                    constructorArgs = ", " + constructorArgs;
                }

                final String serializerMemberName = getSerializerMemberName();

                if (settings.isFluent()) {
                    function.line(String.format("%1$s client = new %2$s(pipeline, %3$s, defaultPollInterval, environment%4$s);",
                            serviceClient.getClassName(),
                            serviceClient.getClassName(),
                            serializerMemberName,
                            constructorArgs));
                } else {
                    function.line(String.format("%1$s client = new %2$s(pipeline, %3$s%4$s);",
                        serviceClient.getClassName(), serviceClient.getClassName(), serializerMemberName, constructorArgs));
                }
                function.line("return client;");
            });

            if (!settings.isAzureOrFluent()) {
                addCreateHttpPipelineMethod(settings, buildReturnType, classBlock, clientProperties, buildMethodName, serviceClient.getDefaultCredentialScopes());
            }

            if (JavaSettings.getInstance().shouldGenerateSyncAsyncClients()) {
                if (!settings.isFluentLite()) {
                    for (AsyncSyncClient asyncClient : asyncClients) {
                        final boolean wrapServiceClient = asyncClient.getMethodGroupClient() == null;

                        classBlock.javadocComment(comment ->
                        {
                            comment.description(String
                                    .format("Builds an instance of %1$s async client", asyncClient.getClassName()));
                            comment.methodReturns(String.format("an instance of %1$s", asyncClient.getClassName()));
                        });
                        classBlock.publicMethod(String.format("%1$s build%2$s()", asyncClient.getClassName(), singleBuilder ? "AsyncClient" : asyncClient.getClassName()),
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

                for (AsyncSyncClient syncClient : syncClients) {
                    final boolean wrapServiceClient = syncClient.getMethodGroupClient() == null;

                    classBlock.javadocComment(comment ->
                    {
                        comment.description(String
                                .format("Builds an instance of %1$s sync client", syncClient.getClassName()));
                        comment.methodReturns(String.format("an instance of %1$s", syncClient.getClassName()));
                    });
                    classBlock.publicMethod(String.format("%1$s build%2$s()", syncClient.getClassName(), singleBuilder ? "Client" : syncClient.getClassName()),
                            function -> {
                                if (wrapServiceClient) {
                                    function.line("return new %1$s(%2$s());", syncClient.getClassName(), buildMethodName);
                                } else {
                                    function.line("return new %1$s(%2$s().get%3$s());", syncClient.getClassName(), buildMethodName,
                                            CodeNamer.toPascalCase(syncClient.getMethodGroupClient().getVariableName()));
                                }
                            });
                }
            }
        });

    }

    protected String getSerializerMemberName() {
        return "serializerAdapter";
    }

    protected void addSerializerImport(Set<String> imports, JavaSettings settings) {
        imports.add(settings.isFluent() ? "com.azure.core.management.serializer.SerializerFactory" : "com.azure.core.util.serializer.JacksonAdapter");
    }

    protected void addImportForCoreUtils(Set<String> imports) {
        imports.add("com.azure.core.util.CoreUtils");
    }

    protected void addHttpPolicyImports(Set<String> imports) {
        imports.add("com.azure.core.http.policy.BearerTokenAuthenticationPolicy");
        imports.add("com.azure.core.http.policy.AzureKeyCredentialPolicy");
        imports.add("com.azure.core.http.policy.HttpPolicyProviders");
        imports.add("com.azure.core.http.policy.HttpLoggingPolicy");
        imports.add("com.azure.core.http.policy.HttpPipelinePolicy");
    }

    protected void addServiceClientBuilderAnnotationImport(Set<String> imports) {
        imports.add("com.azure.core.annotation.ServiceClientBuilder");
    }

    protected void addCreateHttpPipelineMethod(JavaSettings settings, String buildReturnType, JavaClass classBlock, List<ServiceClientProperty> clientProperties, String buildMethodName, String defaultCredentialScopes) {
        classBlock.privateMethod(String.format("HttpPipeline createHttpPipeline()", buildReturnType,
                buildMethodName), function -> {
            function.line("Configuration buildConfiguration = (configuration == null) ? Configuration"
                    + ".getGlobalConfiguration() : configuration;");

            function.ifBlock("httpLogOptions == null", action -> {
                function.line("httpLogOptions = new HttpLogOptions();");
            });

            function.line("List<HttpPipelinePolicy> policies = new ArrayList<>();");


            function.line("String clientName = properties.getOrDefault(SDK_NAME, \"UnknownName\");");
            function.line("String clientVersion = properties.getOrDefault(SDK_VERSION, \"UnknownVersion\");");

            function.line("policies.add(new UserAgentPolicy(httpLogOptions.getApplicationId(), clientName, "
                    + "clientVersion, buildConfiguration));");

            function.line("HttpPolicyProviders.addBeforeRetryPolicies(policies);");
            function.line("policies.add(retryPolicy == null ? new RetryPolicy() : retryPolicy);");
            function.line("policies.add(new CookiePolicy());");

            if (settings.getCredentialTypes().contains(CredentialType.AZURE_KEY_CREDENTIAL)) {
                if (JavaSettings.getInstance().getKeyCredentialHeaderName() == null
                    || JavaSettings.getInstance().getKeyCredentialHeaderName().isEmpty()) {
                    Javagen.getPluginInstance().getLogger().error("key-credential-header-name is required for " +
                            "azurekeycredential credential type");
                    throw new IllegalStateException("key-credential-header-name is required for " +
                            "azurekeycredential credential type");
                }
                function.ifBlock("azureKeyCredential != null", action -> {
                    function.line("policies.add(new AzureKeyCredentialPolicy(\""
                            + JavaSettings.getInstance().getKeyCredentialHeaderName()
                            + "\", azureKeyCredential));");
                });
            }
            if (settings.getCredentialTypes().contains(CredentialType.TOKEN_CREDENTIAL)) {
                function.ifBlock("tokenCredential != null", action -> {
                    function.line("policies.add(new BearerTokenAuthenticationPolicy(tokenCredential, %s));", defaultCredentialScopes);
                });
            }
            function.line("policies.addAll(this.pipelinePolicies);");
            function.line("HttpPolicyProviders.addAfterRetryPolicies(policies);");

            function.line("policies.add(new HttpLoggingPolicy(httpLogOptions));");

            function.line("HttpPipeline httpPipeline = new HttpPipelineBuilder().policies(policies.toArray(new "
                    + "HttpPipelinePolicy[0])).httpClient(httpClient).build();");
            function.methodReturn("httpPipeline");
        });
    }

    protected ArrayList<ServiceClientProperty> addCommonClientProperties(JavaSettings settings) {
        ArrayList<ServiceClientProperty> commonProperties = new ArrayList<ServiceClientProperty>();
        if (settings.isAzureOrFluent()) {
            commonProperties.add(new ServiceClientProperty("The environment to connect to", ClassType.AzureEnvironment, "environment", false, "AzureEnvironment.AZURE"));
        }
        if (settings.isFluent()) {
            commonProperties.add(new ServiceClientProperty("The default poll interval for long-running operation", ClassType.Duration, "defaultPollInterval", false, "Duration.ofSeconds(30)"));
        }

        commonProperties.add(new ServiceClientProperty("The HTTP pipeline to send requests through", ClassType.HttpPipeline, "pipeline", false,
                settings.isAzureOrFluent()
                        ? "new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy()).build()"
                        : "createHttpPipeline()"));

        commonProperties.add(new ServiceClientProperty("The serializer to serialize an object into a string",
          ClassType.SerializerAdapter, getSerializerMemberName(), false,
          settings.isFluent() ? "SerializerFactory.createDefaultManagementSerializerAdapter()" : "JacksonAdapter.createDefaultSerializerAdapter()"));

        if (!settings.isAzureOrFluent()) {

            commonProperties.add(new ServiceClientProperty("The HTTP client used to send the request.",
                    ClassType.HttpClient, "httpClient", false, null));
            commonProperties.add(new ServiceClientProperty("The configuration store that is used during "
                    + "construction of the service client.", ClassType.Configuration, "configuration", false, null));

            if (settings.getCredentialTypes().contains(CredentialType.AZURE_KEY_CREDENTIAL)) {
                commonProperties.add(new ServiceClientProperty("The Azure Key Credential used for authentication.",
                        ClassType.AzureKeyCredential, "azureKeyCredential", "credential", false, null));
            }
            if (settings.getCredentialTypes().contains(CredentialType.TOKEN_CREDENTIAL)) {
                commonProperties.add(new ServiceClientProperty("The TokenCredential used for authentication.",
                        ClassType.TokenCredential, "tokenCredential", "credential", false, null));
            }

            commonProperties.add(new ServiceClientProperty("The logging configuration for HTTP requests and "
                    + "responses.", ClassType.HttpLogOptions, "httpLogOptions", false, null));
            commonProperties.add(new ServiceClientProperty("The retry policy that will attempt to retry failed "
                    + "requests, if applicable.", ClassType.RetryPolicy, "retryPolicy", false, null));

            commonProperties.add(new ServiceClientProperty("The list of Http pipeline policies to add.",
                    new ListType(ClassType.HttpPipelinePolicy), "pipelinePolicies", true, null));
        }

        return commonProperties;
    }

    /**
     * Extension for the name of build method.
     *
     * @return The name of build method.
     */
    protected String primaryBuildMethodName(JavaSettings settings) {
        return settings.shouldGenerateSyncAsyncClients()
                ? "buildInnerClient"
                : "buildClient";
    }
}
