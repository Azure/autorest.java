package com.azure.autorest.template;


// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


import com.azure.autorest.extension.base.plugin.JavaSettings.SyncMethodsGeneration;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ServiceClient;
import com.azure.autorest.model.clientmodel.ServiceClientProperty;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.model.javamodel.JavaVisibility;
import com.azure.autorest.util.CodeNamer;

import java.util.ArrayList;
import java.util.HashSet;
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
        String serviceClientBuilderName = String.format("%1$sBuilder", serviceClient.getInterfaceName());

        ArrayList<ServiceClientProperty> commonProperties = new ArrayList<ServiceClientProperty>();
        if (settings.isAzureOrFluent()) {
            commonProperties.add(new ServiceClientProperty("The environment to connect to", ClassType.AzureEnvironment, "environment", false, "AzureEnvironment.AZURE"));
            commonProperties.add(new ServiceClientProperty("The HTTP pipeline to send requests through", ClassType.HttpPipeline, "pipeline", false, "new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy()).build()"));
        } else {
            commonProperties.add(new ServiceClientProperty("The HTTP pipeline to send requests through", ClassType.HttpPipeline, "pipeline", false, "new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy()).build()"));
        }

        String buildReturnType;
        if (!settings.isFluent() && settings.shouldGenerateClientInterfaces()) {
            buildReturnType = serviceClient.getInterfaceName();
        } else {
            buildReturnType = serviceClient.getClassName();
        }

        Set<String> imports = new HashSet<String>();
        serviceClient.addImportsTo(imports, false, true, settings);
        commonProperties.stream().forEach(p -> p.addImportsTo(imports, false));
        imports.remove("com.azure.management.AzureServiceClient");
        imports.add("com.azure.core.annotation.ServiceClientBuilder");

        StringBuilder builderTypes = new StringBuilder();
        builderTypes.append("{");
        if (JavaSettings.getInstance().shouldGenerateSyncAsyncClients()) {
            imports.add(serviceClient.getPackage() + "." + serviceClient.getClassName());
            String asyncClassName =
                serviceClient.getClientBaseName().endsWith("Client") ? serviceClient.getClientBaseName()
                    .replace("Client", "AsyncClient") : serviceClient.getClientBaseName() + "AsyncClient";
            builderTypes.append(asyncClassName).append(".class");
            if (SyncMethodsGeneration.ALL.equals(JavaSettings.getInstance().getSyncMethods())) {
                String syncClassName =
                    serviceClient.getClientBaseName().endsWith("Client") ? serviceClient.getClientBaseName()
                        : serviceClient.getClientBaseName() + "Client";
                builderTypes.append(", ").append(syncClassName).append(".class");
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
            // Add ServiceClient client property variables, getters, and setters
            for (ServiceClientProperty serviceClientProperty : Stream
                    .concat(serviceClient.getProperties().stream().filter(p -> !p.isReadOnly()), commonProperties.stream()).collect(Collectors.toList())) {
                classBlock.blockComment(settings.getMaximumJavadocCommentWidth(), comment ->
                {
                    comment.line(serviceClientProperty.getDescription());
                });
                classBlock.privateMemberVariable(String.format("%1$s %2$s", serviceClientProperty.getType(), serviceClientProperty.getName()));

                classBlock.javadocComment(comment ->
                {
                    comment.description(String.format("Sets %1$s", serviceClientProperty.getDescription()));
                    comment.param(serviceClientProperty.getName(), String.format("the %1$s value.", serviceClientProperty.getName()));
                    comment.methodReturns(String.format("the %1$s", serviceClientBuilderName));
                });
                classBlock.publicMethod(String.format("%1$s %2$s(%3$s %4$s)", serviceClientBuilderName, CodeNamer.toCamelCase(serviceClientProperty.getName()), serviceClientProperty.getType(), serviceClientProperty.getName()), function ->
                {
                    function.line(String.format("this.%1$s = %2$s;", serviceClientProperty.getName(), serviceClientProperty.getName()));
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
                for (ServiceClientProperty serviceClientProperty : Stream.concat(serviceClient.getProperties().stream().filter(p -> !p.isReadOnly()), commonProperties.stream()).collect(Collectors.toList())) {
                    if (serviceClientProperty.getDefaultValueExpression() != null) {
                        function.ifBlock(String.format("%1$s == null", serviceClientProperty.getName()), ifBlock ->
                        {
                            function.line(String.format("this.%1$s = %2$s;", serviceClientProperty.getName(), serviceClientProperty.getDefaultValueExpression()));
                        });
                    }
                }
                if (settings.isAzureOrFluent()) {
                    function.line(String.format("%1$s client = new %2$s(pipeline, environment);", serviceClient.getClassName(), serviceClient.getClassName()));
                } else {
                    function.line(String.format("%1$s client = new %2$s(pipeline);", serviceClient.getClassName(), serviceClient.getClassName()));
                }
                for (ServiceClientProperty serviceClientProperty : serviceClient.getProperties().stream()
                        .filter(p -> !p.isReadOnly()).collect(Collectors.toList())) {
                    function.line("client.set%1$s(this.%2$s);", CodeNamer.toPascalCase(serviceClientProperty.getName()), serviceClientProperty.getName());
                }
                function.line("return client;");
            });

            if (JavaSettings.getInstance().shouldGenerateSyncAsyncClients()) {
                String asyncClassName = serviceClient.getClientBaseName().endsWith("Client") ? serviceClient.getClientBaseName()
                    .replace("Client", "AsyncClient") : serviceClient.getClientBaseName() + "AsyncClient";

                classBlock.javadocComment(comment ->
                {
                    comment.description(String
                        .format("Builds an instance of %1$s async client", asyncClassName));
                    comment.methodReturns(String.format("an instance of %1$s", asyncClassName));
                });
                classBlock.publicMethod(String.format("%1$s buildAsyncClient()", asyncClassName),
                    function -> {
                        if (serviceClient.getProxy() != null) {
                            function.line("return new %1$s(%2$s());", asyncClassName, buildMethodName);
                        } else {
                            function.line("return new %1$s(%2$s().%3$s());", asyncClassName, buildMethodName,
                                CodeNamer.getModelNamer().modelPropertyGetterName(serviceClient.getMethodGroupClients().get(0).getClassBaseName()));
                        }
                    });

                if (SyncMethodsGeneration.ALL.equals(JavaSettings.getInstance().getSyncMethods())) {
                    String syncClassName =
                        serviceClient.getClientBaseName().endsWith("Client") ? serviceClient.getClientBaseName()
                            : serviceClient.getClientBaseName() + "Client";

                    classBlock.javadocComment(comment ->
                    {
                        comment.description(String
                            .format("Builds an instance of %1$s sync client", syncClassName));
                        comment.methodReturns(String.format("an instance of %1$s", syncClassName));
                    });
                    classBlock.publicMethod(String.format("%1$s buildClient()", syncClassName),
                        function -> {
                            if (serviceClient.getProxy() != null) {
                                function.line("return new %1$s(%2$s());", syncClassName, buildMethodName);
                            } else {
                                function.line("return new %1$s(%2$s().%3$s());", syncClassName, buildMethodName,
                                    CodeNamer.getModelNamer().modelPropertyGetterName(serviceClient.getMethodGroupClients().get(0).getClassBaseName()));
                            }
                        });
                }
            }
        });

    }

    /**
     * Extension for the name of build method.
     *
     * @return The name of build method.
     */
    protected String primaryBuildMethodName(JavaSettings settings) {
        return settings.shouldGenerateSyncAsyncClients()
                ? "buildInnerClient"
                : "buildClient";    }
}
