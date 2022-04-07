// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.template.example;

import com.azure.autorest.Javagen;
import com.azure.autorest.extension.base.plugin.PluginLogger;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ModelProperty;
import com.azure.autorest.model.clientmodel.PrimitiveType;
import com.azure.autorest.model.clientmodel.examplemodel.ClientModelNode;
import com.azure.autorest.model.clientmodel.examplemodel.ExampleNode;
import com.azure.autorest.model.clientmodel.examplemodel.ExampleHelperFeature;
import com.azure.autorest.model.clientmodel.examplemodel.ListNode;
import com.azure.autorest.model.clientmodel.examplemodel.LiteralNode;
import com.azure.autorest.model.clientmodel.examplemodel.MapNode;
import com.azure.autorest.model.clientmodel.examplemodel.ObjectNode;
import com.azure.autorest.model.javamodel.JavaBlock;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ModelExampleWriter {

    private static final Logger LOGGER = new PluginLogger(Javagen.getPluginInstance(), ModelExampleWriter.class);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final Set<String> imports = new HashSet<>();

    private final Consumer<JavaBlock> assertionWriter;

    public ModelExampleWriter(ExampleNode exampleNode, String modelVariableName) {
        this.imports.add("org.junit.jupiter.api.Assertions");

        ExampleNodeReaderVisitor visitor = new ExampleNodeReaderVisitor();
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

    private static class ExampleNodeReaderVisitor {

        private final Set<String> imports = new HashSet<>();

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

    public static class ExampleNodeWriterVisitor {

        protected final Set<String> imports = new HashSet<>();
        protected final Set<ExampleHelperFeature> helperFeatures = new HashSet<>();

        protected void addSerializerImports(Set<String> imports) {
            imports.add(com.azure.core.util.serializer.JacksonAdapter.class.getName());
            imports.add(com.azure.core.util.serializer.SerializerEncoding.class.getName());
            imports.add(java.io.IOException.class.getName());
        }

        public Set<String> getImports() {
            return imports;
        }

        public Set<ExampleHelperFeature> getHelperFeatures() {
            return helperFeatures;
        }

        public String accept(ExampleNode node) {
            if (node instanceof LiteralNode) {
                node.getClientType().addImportsTo(imports, false);

                return node.getClientType().defaultValueExpression(((LiteralNode) node).getLiteralsValue());
            } else if (node instanceof ObjectNode) {
                IType simpleType = null;
                if (node.getObjectValue() instanceof Integer) {
                    simpleType = PrimitiveType.Int;
                } else if (node.getObjectValue() instanceof Long) {
                    simpleType = PrimitiveType.Long;
                } else if (node.getObjectValue() instanceof Float) {
                    simpleType = PrimitiveType.Float;
                } else if (node.getObjectValue() instanceof Double) {
                    simpleType = PrimitiveType.Double;
                } else if (node.getObjectValue() instanceof Boolean) {
                    simpleType = PrimitiveType.Boolean;
                } else if (node.getObjectValue() instanceof String) {
                    simpleType = ClassType.String;
                }

                if (simpleType != null) {
                    return simpleType.defaultValueExpression(node.getObjectValue().toString());
                } else {
                    addSerializerImports(imports);

                    helperFeatures.add(ExampleHelperFeature.ThrowsIOException);

                    try {
                        String jsonStr = OBJECT_MAPPER.writeValueAsString(node.getObjectValue());

                        return String.format("JacksonAdapter.createDefaultSerializerAdapter().deserialize(%s, Object.class, SerializerEncoding.JSON)",
                                ClassType.String.defaultValueExpression(jsonStr));
                    } catch (JsonProcessingException e) {
                        LOGGER.error("Failed to write JSON {}", node.getObjectValue());
                        throw new IllegalStateException(e);
                    }
                }
            } else if (node instanceof ListNode) {
                imports.add(java.util.Arrays.class.getName());

                StringBuilder builder = new StringBuilder();
                // Arrays.asList(...)
                builder.append("Arrays.asList(")
                        .append(node.getChildNodes().stream().map(this::accept).collect(Collectors.joining(", ")))
                        .append(")");

                return builder.toString();
            } else if (node instanceof MapNode) {
                imports.add(java.util.Map.class.getName());
                imports.add(java.util.HashMap.class.getName());

                helperFeatures.add(ExampleHelperFeature.MapOfMethod);

                List<String> keys = ((MapNode) node).getKeys();

                StringBuilder builder = new StringBuilder();
                // mapOf(...)
                // similar to Map.of in Java 9
                builder.append("mapOf(");
                for (int i = 0; i < keys.size(); ++i) {
                    if (i != 0) {
                        builder.append(", ");
                    }
                    String key = keys.get(i);
                    ExampleNode elementNode = node.getChildNodes().get(i);
                    builder.append(ClassType.String.defaultValueExpression(key))
                            .append(", ")
                            .append(this.accept(elementNode));
                }
                builder.append(")");

                return builder.toString();
            } else if (node instanceof ClientModelNode) {
                ClientModelNode clientModelNode = ((ClientModelNode) node);

                ClientModel model = clientModelNode.getClientModel();

                imports.add(model.getFullName());

                StringBuilder builder = new StringBuilder();
                builder.append("new ").append(model.getName()).append("()");
                for (ExampleNode childNode : node.getChildNodes()) {
                    ModelProperty modelProperty = clientModelNode.getClientModelProperties().get(childNode);
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
