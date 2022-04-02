// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.template.example;

import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ModelProperty;
import com.azure.autorest.model.clientmodel.examplemodel.ClientModelNode;
import com.azure.autorest.model.clientmodel.examplemodel.ExampleNode;
import com.azure.autorest.model.clientmodel.examplemodel.ListNode;
import com.azure.autorest.model.clientmodel.examplemodel.LiteralNode;
import com.azure.autorest.model.clientmodel.examplemodel.MapNode;
import com.azure.autorest.model.clientmodel.examplemodel.ObjectNode;
import com.azure.autorest.model.javamodel.JavaBlock;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public class ModelExampleWriter {

    private final Set<String> imports = new HashSet<>();

    private final Consumer<JavaBlock> assertionWriter;

    public ModelExampleWriter(ExampleNode exampleNode, String modelVariableName) {
        this.imports.add("org.junit.jupiter.api.Assertions");

        ExampleNodeVisitor visitor = new ExampleNodeVisitor();
        visitor.accept(exampleNode, modelVariableName);
        imports.addAll(visitor.imports);

        this.assertionWriter = methodBlock -> {
            visitor.assertions.forEach(methodBlock::line);
        };
    }

    public Set<String> getImports() {
        return imports;
    }

    public void writeAssertion(JavaBlock methodBlock) {
        assertionWriter.accept(methodBlock);
    }

    private static class ExampleNodeVisitor {

        private final Set<String> imports = new HashSet<>();
//        private final Set<HelperFeature> helperFeatures = new HashSet<>();

        private final List<String> assertions = new ArrayList<>();

        private void addEqualsAssertion(String expected, String code) {
            assertions.add(String.format("Assertions.assertEquals(%1$s, %2$s);", expected, code));
        }

        private void accept(ExampleNode node, String getterCode) {
            if (node instanceof LiteralNode) {
                node.getClientType().addImportsTo(imports, false);

                addEqualsAssertion(
                        node.getClientType().defaultValueExpression(((LiteralNode) node).getLiteralsValue()),
                        getterCode);
            } else if (node instanceof ObjectNode) {
                // additionalProperties
            } else if (node instanceof ListNode) {
                if (!node.getChildNodes().isEmpty()) {
                    node = node.getChildNodes().get(0);
                    getterCode += ".get(0)";
                    accept(node, getterCode);
                }
            } else if (node instanceof MapNode) {
                if (!node.getChildNodes().isEmpty()) {
                    String key = ((MapNode) node).getKeys().get(0);
                    node = node.getChildNodes().get(0);
                    getterCode += String.format(".get(%s)", ClassType.String.defaultValueExpression(key));
                    accept(node, getterCode);
                }
            } else if (node instanceof ClientModelNode) {
                ClientModelNode clientModelNode = ((ClientModelNode) node);

                ClientModel model = clientModelNode.getClientModel();

                imports.add(model.getFullName());

                for (ExampleNode childNode : node.getChildNodes()) {
                    ModelProperty modelProperty = clientModelNode.getClientModelProperties().get(childNode);
                    String childGetterCode = getterCode + String.format(".%s()", modelProperty.getGetterName());
                    accept(childNode, childGetterCode);
                }
            }
        }
    }
}
