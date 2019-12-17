package com.azure.autorest.template;


// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ServiceClient;
import com.azure.autorest.model.clientmodel.ServiceClientProperty;
import com.azure.autorest.model.javamodel.JavaFile;
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

    private ServiceClientBuilderTemplate() {
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
            ;
        }

        Set<String> imports = new HashSet<String>();
        serviceClient.addImportsTo(imports, false, true, settings);
        imports.remove("com.microsoft.azure.management.AzureServiceClient");
        imports.add("com.azure.core.annotation.ServiceClientBuilder");
        javaFile.declareImport(imports);

        javaFile.javadocComment(comment ->
        {
            String serviceClientTypeName = settings.isFluent() ? serviceClient.getClassName() : serviceClient.getInterfaceName();
            comment.description(String.format("A builder for creating a new instance of the %1$s type.", serviceClientTypeName));
        });
        javaFile.annotation(String.format("ServiceClientBuilder(serviceClients = %1$s.class)", buildReturnType));
        javaFile.publicFinalClass(serviceClientBuilderName, classBlock ->
        {
            // Add ServiceClient client property variables, getters, and setters
            for (ServiceClientProperty serviceClientProperty : Stream
                    .concat(serviceClient.getProperties().stream().filter(p -> !p.isReadOnly()), commonProperties.stream()).collect(Collectors.toList())) {
                classBlock.blockComment(comment ->
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

            // build method
            classBlock.javadocComment(comment ->
            {
                comment.description(String.format("Builds an instance of %1$s with the provided parameters", buildReturnType));
                comment.methodReturns(String.format("an instance of %1$s", buildReturnType));
            });
            classBlock.publicMethod(String.format("%1$s build()", buildReturnType), function ->
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
        });
    }
}