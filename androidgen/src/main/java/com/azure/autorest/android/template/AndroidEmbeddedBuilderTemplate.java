// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

package com.azure.autorest.android.template;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ServiceClient;
import com.azure.autorest.model.clientmodel.ServiceClientProperty;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.model.javamodel.JavaVisibility;
import com.azure.autorest.template.IJavaTemplate;
import com.azure.autorest.util.ClientModelUtil;
import com.azure.autorest.util.CodeNamer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AndroidEmbeddedBuilderTemplate implements IJavaTemplate<ServiceClient, JavaFile> {
    private static final AndroidEmbeddedBuilderTemplate _instance = new AndroidEmbeddedBuilderTemplate();

    private final ClassType androidServiceClientBuilder = new ClassType.Builder()
            .packageName("com.azure.android.core.http").name("ServiceClient.Builder").build();
    private final ServiceClientProperty serviceClientBuilderProperty = new ServiceClientProperty("The ServiceClient.Builder to build the actual client",
            androidServiceClientBuilder, "serviceClientBuilder", false, "new ServiceClient.Builder()");

    private AndroidEmbeddedBuilderTemplate() {
    }

    public static AndroidEmbeddedBuilderTemplate getInstance() {
        return _instance;
    }

    public void addImportsTo(HashSet<String> imports) {
        imports.add("com.azure.android.core.http.interceptor.AddDateInterceptor");
        imports.add("com.azure.android.core.internal.util.serializer.SerializerFormat");
        imports.add("com.azure.android.core.http.ServiceClient.Builder");
    }

    @Override
    public void write(ServiceClient serviceClient, JavaFile javaFile) {
        JavaSettings settings = JavaSettings.getInstance();

        ArrayList<ServiceClientProperty> commonProperties = new ArrayList<ServiceClientProperty>();
        commonProperties.add(serviceClientBuilderProperty);

        javaFile.javadocComment(comment ->
        {
            String serviceClientTypeName = settings.isFluent() ? serviceClient.getClassName() : serviceClient.getInterfaceName();
            comment.description(String.format("A builder for creating a new instance of the %1$s type.", serviceClientTypeName));
        });

        String serviceClientBuilderName = ClientModelUtil.getBuilderSuffix();
        javaFile.publicFinalClass(serviceClientBuilderName, classBlock -> {
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

            String buildMethodName = "build";
            JavaVisibility visibility = JavaVisibility.Public;
            String buildReturnType = serviceClient.getClassName();

            // build method
            classBlock.javadocComment(comment ->
            {
                comment.description(String.format("Builds an instance of %1$s with the provided parameters", buildReturnType));
                comment.methodReturns(String.format("an instance of %1$s", buildReturnType));
            });
            classBlock.method(visibility, null, String.format("%1$s %2$s()", buildReturnType, buildMethodName), function -> {
                for (ServiceClientProperty serviceClientProperty : commonProperties.stream().collect(Collectors.toList())) {
                    if (serviceClientProperty.getDefaultValueExpression() != null) {
                        function.ifBlock(String.format("this.%1$s == null", serviceClientProperty.getName()), ifBlock ->
                        {
                            function.line(String.format("this.%1$s = %2$s;", serviceClientProperty.getName(), serviceClientProperty.getDefaultValueExpression()));
                        });
                    }
                }

                function.line(String.format("this.%1$s.addInterceptor(new AddDateInterceptor())", serviceClientBuilderProperty.getName()));
                function.line(".setBaseUrl(this.endpoint)");
                function.line(".setSerializationFormat(SerializerFormat.JSON);");
                function.line(String.format("%1$s client = new %1$s(this.%2$s.build());", serviceClient.getClassName(), serviceClientBuilderProperty.getName()));
                function.line("return client;");
            });
        });
    }

}
