package com.azure.autorest.template;


// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.MethodGroupClient;
import com.azure.autorest.model.javamodel.*;
import com.azure.autorest.util.ClientModelUtil;
import com.azure.core.util.BinaryData;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Writes a MethodGroupClient to a JavaFile.
 */
public class MethodGroupTemplate implements IJavaTemplate<MethodGroupClient, JavaFile> {
    private static MethodGroupTemplate _instance = new MethodGroupTemplate();

    protected MethodGroupTemplate() {
    }

    public static MethodGroupTemplate getInstance() {
        return _instance;
    }

    public final void write(MethodGroupClient methodGroupClient, JavaFile javaFile) {
        JavaSettings settings = JavaSettings.getInstance();
        Set<String> imports = new HashSet<String>();
        if (settings.shouldClientLogger()) {
            ClassType.ClientLogger.addImportsTo(imports, false);
        }

        methodGroupClient.addImportsTo(imports, true, settings);

        String serviceClientPackageName =
            ClientModelUtil.getServiceClientPackageName(methodGroupClient.getServiceClientName());
        imports.add(String.format("%1$s.%2$s", serviceClientPackageName, methodGroupClient.getServiceClientName()));

        javaFile.declareImport(imports);

        List<String> interfaces = methodGroupClient.getSupportedInterfaces().stream()
                .map(IType::toString).collect(Collectors.toList());
        interfaces.addAll(methodGroupClient.getImplementedInterfaces());
        String parentDeclaration = !interfaces.isEmpty() ? String.format(" implements %1$s", String.join(", ", interfaces)) : "";

        final JavaVisibility visibility = methodGroupClient.getPackage().equals(serviceClientPackageName)
            ? JavaVisibility.PackagePrivate
            : JavaVisibility.Public;

        javaFile.javadocComment(settings.getMaximumJavadocCommentWidth(), comment ->
        {
            comment.description(String.format("An instance of this class provides access to all the operations defined in %1$s.", methodGroupClient.getInterfaceName()));
        });
        javaFile.publicFinalClass(String.format("%1$s%2$s", methodGroupClient.getClassName(), parentDeclaration), classBlock ->
        {
            if (settings.shouldClientLogger()) {
                classBlock.privateFinalMemberVariable(ClassType.ClientLogger.toString(), String.format("logger = new ClientLogger(%1$s.class)", methodGroupClient.getClassName()));
            }

            classBlock.javadocComment(String.format("The proxy service used to perform REST calls."));
            classBlock.privateFinalMemberVariable(methodGroupClient.getProxy().getName(), "service");

            classBlock.javadocComment("The service client containing this operation class.");
            classBlock.privateFinalMemberVariable(methodGroupClient.getServiceClientName(), "client");

            classBlock.javadocComment(comment ->
            {
                comment.description(String.format("Initializes an instance of %1$s.", methodGroupClient.getClassName()));
                comment.param("client", "the instance of the service client containing this operation class.");
            });
            classBlock.constructor(visibility, String.format("%1$s(%2$s client)", methodGroupClient.getClassName(), methodGroupClient.getServiceClientName()), constructor ->
            {
                if (methodGroupClient.getProxy() != null) {
                    writeServiceProxyConstruction(constructor, methodGroupClient);
                }
                constructor.line("this.client = client;");
            });

            Templates.getProxyTemplate().write(methodGroupClient.getProxy(), classBlock);

            for (ClientMethod clientMethod : methodGroupClient.getClientMethods()) {
                Templates.getClientMethodTemplate().write(clientMethod, classBlock);
            }

            if (settings.isLowLevelClient() &&
                    methodGroupClient.getClientMethods().stream().anyMatch(m -> m.getMethodPageDetails() != null)) {
                writePagingHelperMethods(methodGroupClient, classBlock);
            }

            writeAdditionalClassBlock(classBlock);
        });
    }

    protected void writePagingHelperMethods(MethodGroupClient methodGroupClient, JavaClass classBlock) {
        classBlock.privateMethod("List<BinaryData> getValues(BinaryData binaryData, String path)", block -> {
            block.line("try {");
            block.line("Map<?, ?> obj = binaryData.toObject(Map.class);");
            block.line("List<?> values = (List<?>) obj.get(path);");
            block.line("return values.stream().map(BinaryData::fromObject).collect(Collectors.toList());");
            block.line("} catch (RuntimeException e) { return null; }");
        });
        classBlock.privateMethod("String getNextLink(BinaryData binaryData, String path)", block -> {
            block.line("try {");
            block.line("Map<?, ?> obj = binaryData.toObject(Map.class);");
            block.line("return (String) obj.get(path);");
            block.line("} catch (RuntimeException e) { return null; }");
        });
    }

    protected void writeAdditionalClassBlock(JavaClass classBlock) {
    }

    protected void writeServiceProxyConstruction(JavaBlock constructor, MethodGroupClient methodGroupClient) {
        ClassType proxyType = ClassType.RestProxy;
        constructor.line(String.format("this.service = %1$s.create(%2$s.class, client.getHttpPipeline(), client.getSerializerAdapter());",
                proxyType.getName(), methodGroupClient.getProxy().getName()));
    }
}
