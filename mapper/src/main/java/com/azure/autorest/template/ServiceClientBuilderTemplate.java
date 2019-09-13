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
 Writes a ServiceClient to a JavaFile.
*/
public class ServiceClientBuilderTemplate implements IJavaTemplate<ServiceClient, JavaFile>
{
    private static ServiceClientBuilderTemplate _instance = new ServiceClientBuilderTemplate();
    public static ServiceClientBuilderTemplate getInstance()
    {
        return _instance;
    }

    private ServiceClientBuilderTemplate()
    {
    }

    public final void Write(ServiceClient serviceClient, JavaFile javaFile)
    {
        JavaSettings settings = JavaSettings.getInstance();
        String serviceClientBuilderName = String.format("%1$sBuilder", serviceClient.getInterfaceName());

        ArrayList<ServiceClientProperty> commonProperties = new ArrayList<ServiceClientProperty>();
        if (settings.getIsAzureOrFluent())
        {
            commonProperties.add(new ServiceClientProperty("The environment to connect to", ClassType.AzureEnvironment, "environment", false, "${ClassType.AzureEnvironment.Name}.AZURE"));
            commonProperties.add(new ServiceClientProperty("The HTTP pipeline to send requests through", ClassType.HttpPipeline, "pipeline", false, String.format("%1$s.createDefaultPipeline(%2$s.class)", ClassType.AzureProxy.getName(), serviceClient.getClassName())));
        }
        else
        {
            commonProperties.add(new ServiceClientProperty("The HTTP pipeline to send requests through", ClassType.HttpPipeline, "pipeline", false, String.format("%1$s.createDefaultPipeline()", ClassType.RestProxy.getName())));
        }

        String buildReturnType;
        if (!settings.getIsFluent() && settings.getGenerateClientInterfaces())
        {
            buildReturnType = serviceClient.getInterfaceName();
        } else {
            buildReturnType = serviceClient.getClassName();;
        }

        Set<String> imports = new HashSet<String>();
        serviceClient.AddImportsTo(imports, true, settings);
        imports.remove("com.azure.core.AzureServiceClient");
        imports.add("com.azure.core.implementation.annotation.ServiceClientBuilder");
        javaFile.Import(imports);

        javaFile.JavadocComment(comment ->
        {
                String serviceClientTypeName = settings.getIsFluent() ? serviceClient.getClassName() : serviceClient.getInterfaceName();
                comment.Description(String.format("A builder for creating a new instance of the %1$s type.", serviceClientTypeName));
        });
        javaFile.Annotation(String.format("ServiceClientBuilder(serviceClients = %1$s.class)", buildReturnType));
        javaFile.PublicFinalClass(serviceClientBuilderName, classBlock ->
        {
                // Add ServiceClient client property variables, getters, and setters
                for (ServiceClientProperty serviceClientProperty : Stream
                        .concat(serviceClient.getProperties().stream().filter(p -> !p.getIsReadOnly()), commonProperties.stream()).collect(Collectors.toList()))
                {
                    classBlock.BlockComment(comment ->
                    {
                        comment.Line(serviceClientProperty.getDescription());
                    });
                    classBlock.PrivateMemberVariable(String.format("%1$s %2$s", serviceClientProperty.getType(), serviceClientProperty.getName()));

                    classBlock.JavadocComment(comment ->
                    {
                        comment.Description(String.format("Sets %1$s", serviceClientProperty.getDescription()));
                        comment.Param(serviceClientProperty.getName(), String.format("the %1$s value.", serviceClientProperty.getName()));
                        comment.Return(String.format("the %1$s", serviceClientBuilderName));
                    });
                    classBlock.PublicMethod(String.format("%1$s %2$s(%3$s %4$s)", serviceClientBuilderName, CodeNamer.toCamelCase(serviceClientProperty.getName()), serviceClientProperty.getType(), serviceClientProperty.getName()),function ->
                    {
                        function.Line(String.format("this.%1$s = %2$s;", serviceClientProperty.getName(), serviceClientProperty.getName()));
                        function.Return("this");
                    });
                }

                // build method
                classBlock.JavadocComment(comment ->
                {
                    comment.Description(String.format("Builds an instance of %1$s with the provided parameters", buildReturnType));
                    comment.Return(String.format("an instance of %1$s", buildReturnType));
                });
                classBlock.PublicMethod(String.format("%1$s build()", buildReturnType), function ->
                {
                    for (ServiceClientProperty serviceClientProperty : Stream.concat(serviceClient.getProperties().stream().filter(p -> !p.getIsReadOnly()), commonProperties.stream()).collect(Collectors.toList()))
                    {
                        if (serviceClientProperty.getDefaultValueExpression() != null)
                        {
                            function.If(String.format("%1$s == null", serviceClientProperty.getName()), ifBlock ->
                            {
                                function.Line(String.format("this.%1$s = %2$s;", serviceClientProperty.getName(), serviceClientProperty.getDefaultValueExpression()));
                            });
                        }
                    }
                    if (settings.getIsAzureOrFluent())
                    {
                        function.Line(String.format("%1$s client = new %2$s(pipeline, environment);", serviceClient.getClassName(), serviceClient.getClassName()));
                    }
                    else
                    {
                        function.Line(String.format("%1$s client = new %2$s(pipeline);", serviceClient.getClassName(), serviceClient.getClassName()));
                    }
                    for (ServiceClientProperty serviceClientProperty : serviceClient.getProperties().stream().filter(p -> !p.getIsReadOnly()).collect(Collectors.toList()))
                    {
                        function.If(String.format("this.%1$s != null", serviceClientProperty.getName()), ifBlock ->
                        {
                            ifBlock.Line(String.format("client.set%1$s(this.%2$s);", CodeNamer.toPascalCase(serviceClientProperty.getName()), serviceClientProperty.getName()));
                        });
                    }
                    function.Line("return client;");
                });
        });
    }
}