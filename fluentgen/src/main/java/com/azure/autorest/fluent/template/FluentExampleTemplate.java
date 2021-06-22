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
import com.azure.autorest.util.CodeNamer;
import org.slf4j.Logger;

import java.util.stream.Collectors;

public class FluentExampleTemplate {

    private static final Logger logger = new PluginLogger(FluentGen.getPluginInstance(), FluentExampleTemplate.class);

    public String writeSnippet(FluentCollectionMethodExample collectionMethodExample) {
        ExampleNodeVisitor visitor = new ExampleNodeVisitor();
        String parameterInvocations = collectionMethodExample.getParameters().stream()
                .map(p -> visitor.accept(p.getExampleNode()))
                .collect(Collectors.joining(", "));

        return String.format("%1$s.%2$s().%3$s(%4$s);",
                CodeNamer.toCamelCase(FluentStatic.getFluentManager().getType().getName()),
                CodeNamer.toCamelCase(collectionMethodExample.getResourceCollection().getInterfaceType().getName()),
                collectionMethodExample.getCollectionMethod().getMethodName(),
                parameterInvocations);
    }

    private static class ExampleNodeVisitor {

        private String accept(ExampleNode node) {
            if (node instanceof LiteralNode) {
                return node.getClientType().defaultValueExpression(((LiteralNode) node).getLiteralsValue());
            } else if (node instanceof ListNode) {
                StringBuilder builder = new StringBuilder();

                builder.append("Arrays.asList(")
                        .append(node.getChildNodes().stream().map(this::accept).collect(Collectors.joining(", ")))
                        .append(")");

                return builder.toString();
            } else if (node instanceof ClientModelNode) {
                ClientModelNode clientModelNode = ((ClientModelNode) node);

                StringBuilder builder = new StringBuilder();

                ClientModel model = clientModelNode.getClientModel();
                builder.append("new ").append(model.getName()).append("()");
                for (ExampleNode childNode : node.getChildNodes()) {
                    ClientModelProperty modelProperty = clientModelNode.getClientModelProperties().get(childNode);
                    builder.append(".").append(modelProperty.getSetterName())
                            .append("(").append(this.accept(childNode)).append(")");
                }
                return builder.toString();
            }
            return null;
        }
    }
}
