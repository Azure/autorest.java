/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.template;

import com.azure.autorest.extension.base.plugin.PluginLogger;
import com.azure.autorest.fluent.FluentGen;
import com.azure.autorest.fluent.model.clientmodel.FluentStatic;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.ClientModelNode;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.ExampleNode;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.FluentCollectionMethodExample;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.ListNode;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.LiteralNode;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.util.CodeNamer;
import org.slf4j.Logger;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class FluentExampleTemplate {

    private static final Logger logger = new PluginLogger(FluentGen.getPluginInstance(), FluentExampleTemplate.class);

    private static final FluentExampleTemplate INSTANCE = new FluentExampleTemplate();

    public static FluentExampleTemplate getInstance() {
        return INSTANCE;
    }

    public final void write(FluentCollectionMethodExample collectionMethodExample, JavaFile javaFile) {
        String className = collectionMethodExample.getClassName();
        String methodName = CodeNamer.toCamelCase(collectionMethodExample.getName());
        String managerName = CodeNamer.toCamelCase(collectionMethodExample.getManager().getType().getName());

        ExampleNodeVisitor visitor = new ExampleNodeVisitor();
        String parameterInvocations = collectionMethodExample.getParameters().stream()
                .map(p -> visitor.accept(p.getExampleNode()))
                .collect(Collectors.joining(", "));

        String snippet = String.format("%1$s.%2$s().%3$s(%4$s);",
                managerName,
                CodeNamer.toCamelCase(collectionMethodExample.getResourceCollection().getInterfaceType().getName()),
                collectionMethodExample.getCollectionMethod().getMethodName(),
                parameterInvocations);

        Set<String> imports = new HashSet<>(visitor.imports);
        //imports.add(FluentStatic.getFluentManager().getType().getFullName());
        javaFile.declareImport(imports);

        javaFile.publicFinalClass(className, classBlock -> {
            classBlock.publicMethod(String.format("void %1$s(%2$s %3$s)", methodName, FluentStatic.getFluentManager().getType().getFullName(), managerName), methodBlock -> {
                methodBlock.line(snippet);
            });
        });
    }

    public String writeSnippet(FluentCollectionMethodExample collectionMethodExample) {
        String managerName = CodeNamer.toCamelCase(collectionMethodExample.getManager().getType().getName());

        ExampleNodeVisitor visitor = new ExampleNodeVisitor();
        String parameterInvocations = collectionMethodExample.getParameters().stream()
                .map(p -> visitor.accept(p.getExampleNode()))
                .collect(Collectors.joining(", "));

        return String.format("%1$s.%2$s().%3$s(%4$s);",
                managerName,
                CodeNamer.toCamelCase(collectionMethodExample.getResourceCollection().getInterfaceType().getName()),
                collectionMethodExample.getCollectionMethod().getMethodName(),
                parameterInvocations);
    }

    private static class ExampleNodeVisitor {

        private final Set<String> imports = new HashSet<>();

        private String accept(ExampleNode node) {
            if (node instanceof LiteralNode) {
                node.getClientType().addImportsTo(imports, false);

                return node.getClientType().defaultValueExpression(((LiteralNode) node).getLiteralsValue());
            } else if (node instanceof ListNode) {
                imports.add(java.util.Arrays.class.getName());

                StringBuilder builder = new StringBuilder();
                // Arrays.asList(...)
                builder.append("Arrays.asList(")
                        .append(node.getChildNodes().stream().map(this::accept).collect(Collectors.joining(", ")))
                        .append(")");

                return builder.toString();
            } else if (node instanceof ClientModelNode) {
                ClientModelNode clientModelNode = ((ClientModelNode) node);

                ClientModel model = clientModelNode.getClientModel();

                imports.add(model.getFullName());

                StringBuilder builder = new StringBuilder();
                builder.append("new ").append(model.getName()).append("()");
                for (ExampleNode childNode : node.getChildNodes()) {
                    ClientModelProperty modelProperty = clientModelNode.getClientModelProperties().get(childNode);
                    // .withProperty(...)
                    builder.append(".").append(modelProperty.getSetterName())
                            .append("(").append(this.accept(childNode)).append(")");
                }
                return builder.toString();
            }
            return null;
        }
    }
}
