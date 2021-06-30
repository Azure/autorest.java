/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.template;

import com.azure.autorest.extension.base.plugin.PluginLogger;
import com.azure.autorest.fluent.FluentGen;
import com.azure.autorest.fluent.model.clientmodel.FluentExample;
import com.azure.autorest.fluent.model.clientmodel.FluentStatic;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.ClientModelNode;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.ExampleNode;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.FluentCollectionMethodExample;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.FluentResourceCreateExample;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.FluentResourceUpdateExample;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.ListNode;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.LiteralNode;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.MapNode;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.ObjectNode;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.PrimitiveType;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.model.javamodel.JavaModifier;
import com.azure.autorest.model.javamodel.JavaVisibility;
import com.azure.autorest.util.CodeNamer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FluentExampleTemplate {

    private static final Logger logger = new PluginLogger(FluentGen.getPluginInstance(), FluentExampleTemplate.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static final FluentExampleTemplate INSTANCE = new FluentExampleTemplate();

    public static FluentExampleTemplate getInstance() {
        return INSTANCE;
    }

    public final void write(FluentExample example, JavaFile javaFile) {
        String className = example.getClassName();

        List<ExampleMethod> exampleMethods = new ArrayList<>();
        exampleMethods.addAll(
                example.getResourceCreateExamples().stream()
                        .map(this::generateExampleMethod)
                        .collect(Collectors.toList()));
        exampleMethods.addAll(
                example.getResourceUpdateExamples().stream()
                        .map(this::generateExampleMethod)
                        .collect(Collectors.toList()));
        exampleMethods.addAll(
                example.getCollectionMethodExamples().stream()
                        .map(this::generateExampleMethod)
                        .collect(Collectors.toList()));

        Set<String> imports = exampleMethods.stream().flatMap(em -> em.getImports().stream()).collect(Collectors.toSet());
        javaFile.declareImport(imports);

        Set<HelperFeature> helperFeatures = exampleMethods.stream().flatMap(em -> em.getHelperFeatures().stream()).collect(Collectors.toSet());

        javaFile.publicFinalClass(className, classBlock -> {
            for (ExampleMethod exampleMethod : exampleMethods) {
                String methodSignature = exampleMethod.getMethodSignature();
                if (exampleMethod.getHelperFeatures().contains(HelperFeature.ThrowsIOException)) {
                    methodSignature += " throws IOException";
                }
                classBlock.publicStaticMethod(methodSignature, methodBlock -> {
                    methodBlock.line(exampleMethod.getMethodContent());
                });
            }

            if (helperFeatures.contains(HelperFeature.MapOfMethod)) {
                classBlock.annotation("SuppressWarnings(\"unchecked\")");
                classBlock.method(JavaVisibility.Private, Arrays.asList(JavaModifier.Static), "<T> Map<String, T> mapOf(Object... inputs)", methodBlock -> {
                    methodBlock.line("Map<String, T> map = new HashMap<>();");
                    methodBlock.line("for (int i = 0; i < inputs.length; i += 2) {");
                    methodBlock.indent(() -> {
                        methodBlock.line("String key = (String) inputs[i];");
                        methodBlock.line("T value = (T) inputs[i + 1];");
                        methodBlock.line("map.put(key, value);");
                    });
                    methodBlock.line("}");
                    methodBlock.line("return map;");
                });
            }
        });
    }

    private ExampleMethod generateExampleMethod(FluentCollectionMethodExample collectionMethodExample) {
        String methodName = CodeNamer.toCamelCase(CodeNamer.removeInvalidCharacters(collectionMethodExample.getName()));
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

        ExampleMethod exampleMethod = new ExampleMethod()
                .setImports(visitor.imports)
                .setMethodSignature(String.format("void %1$s(%2$s %3$s)", methodName, FluentStatic.getFluentManager().getType().getFullName(), managerName))
                .setMethodContent(snippet)
                .setHelperFeatures(visitor.helperFeatures);
        return exampleMethod;
    }

    private ExampleMethod generateExampleMethod(FluentResourceCreateExample resourceCreateExample) {
        String methodName = CodeNamer.toCamelCase(CodeNamer.removeInvalidCharacters(resourceCreateExample.getName()));
        String managerName = CodeNamer.toCamelCase(resourceCreateExample.getManager().getType().getName());

        ExampleNodeVisitor visitor = new ExampleNodeVisitor();
        StringBuilder sb = new StringBuilder(managerName)
                .append(".").append(CodeNamer.toCamelCase(resourceCreateExample.getResourceCollection().getInterfaceType().getName())).append("()");
        for (FluentResourceCreateExample.ParameterExample parameter : resourceCreateExample.getParameters()) {
            String parameterInvocations = parameter.getExampleNodes().stream()
                    .map(visitor::accept)
                    .collect(Collectors.joining(", "));
            if (parameter.getExampleNodes().size() == 1 && parameterInvocations.equals("null")) {
                // avoid ambiguous type on "null"
                parameterInvocations = String.format("(%1$s) %2$s",
                        parameter.getExampleNodes().iterator().next().getClientType().toString(),
                        parameterInvocations);
            }
            sb.append(".").append(parameter.getFluentMethod().getName())
                    .append("(").append(parameterInvocations).append(")");
        }
        sb.append(".create();");

        ExampleMethod exampleMethod = new ExampleMethod()
                .setImports(visitor.imports)
                .setMethodSignature(String.format("void %1$s(%2$s %3$s)", methodName, FluentStatic.getFluentManager().getType().getFullName(), managerName))
                .setMethodContent(sb.toString())
                .setHelperFeatures(visitor.helperFeatures);
        return exampleMethod;
    }

    private ExampleMethod generateExampleMethod(FluentResourceUpdateExample resourceUpdateExample) {
        String methodName = CodeNamer.toCamelCase(CodeNamer.removeInvalidCharacters(resourceUpdateExample.getName()));
        String managerName = CodeNamer.toCamelCase(resourceUpdateExample.getManager().getType().getName());

        ExampleNodeVisitor visitor = new ExampleNodeVisitor();

        FluentCollectionMethodExample resourceGetExample = resourceUpdateExample.getResourceGetExample();
        String parameterInvocations = resourceGetExample.getParameters().stream()
                .map(p -> visitor.accept(p.getExampleNode()))
                .collect(Collectors.joining(", "));

        String resourceGetSnippet = String.format("%1$s %2$s = %3$s.%4$s().%5$s(%6$s).getValue();\n",
                resourceUpdateExample.getResourceUpdate().getResourceModel().getInterfaceType().getName(),
                "resource",
                managerName,
                CodeNamer.toCamelCase(resourceGetExample.getResourceCollection().getInterfaceType().getName()),
                resourceGetExample.getCollectionMethod().getMethodName(),
                parameterInvocations);

        StringBuilder sb = new StringBuilder(resourceGetSnippet);
        sb.append("resource").append(".update()");
        for (FluentResourceCreateExample.ParameterExample parameter : resourceUpdateExample.getParameters()) {
            parameterInvocations = parameter.getExampleNodes().stream()
                    .map(visitor::accept)
                    .collect(Collectors.joining(", "));
            sb.append(".").append(parameter.getFluentMethod().getName())
                    .append("(").append(parameterInvocations).append(")");
        }
        sb.append(".apply();");

        resourceUpdateExample.getResourceUpdate().getResourceModel().getInterfaceType().addImportsTo(visitor.imports, false);

        ExampleMethod exampleMethod = new ExampleMethod()
                .setImports(visitor.imports)
                .setMethodSignature(String.format("void %1$s(%2$s %3$s)", methodName, FluentStatic.getFluentManager().getType().getFullName(), managerName))
                .setMethodContent(sb.toString())
                .setHelperFeatures(visitor.helperFeatures);
        return exampleMethod;
    }

    private static class ExampleNodeVisitor {

        private final Set<String> imports = new HashSet<>();
        private final Set<HelperFeature> helperFeatures = new HashSet<>();

        private String accept(ExampleNode node) {
            if (node instanceof LiteralNode) {
                node.getClientType().addImportsTo(imports, false);

                return node.getClientType().defaultValueExpression(((LiteralNode) node).getLiteralsValue());
            } else if (node instanceof ObjectNode) {
                PrimitiveType primitiveType = null;
                if (node.getObjectValue() instanceof Integer) {
                    primitiveType = PrimitiveType.Int;
                } else if (node.getObjectValue() instanceof Long) {
                    primitiveType = PrimitiveType.Long;
                } else if (node.getObjectValue() instanceof Float) {
                    primitiveType = PrimitiveType.Float;
                } else if (node.getObjectValue() instanceof Double) {
                    primitiveType = PrimitiveType.Double;
                } else if (node.getObjectValue() instanceof Boolean) {
                    primitiveType = PrimitiveType.Boolean;
                }

                if (primitiveType != null) {
                    return primitiveType.defaultValueExpression(node.getObjectValue().toString());
                } else {
                    imports.add(com.azure.core.management.serializer.SerializerFactory.class.getName());
                    imports.add(com.azure.core.util.serializer.SerializerEncoding.class.getName());
                    imports.add(java.io.IOException.class.getName());

                    helperFeatures.add(HelperFeature.ThrowsIOException);

                    try {
                        String jsonStr = OBJECT_MAPPER.writeValueAsString(node.getObjectValue());

                        return String.format("SerializerFactory.createDefaultManagementSerializerAdapter().deserialize(%s, Object.class, SerializerEncoding.JSON)",
                                ClassType.String.defaultValueExpression(jsonStr));
                    } catch (JsonProcessingException e) {
                        logger.error("Failed to write JSON {}", node.getObjectValue());
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

                helperFeatures.add(HelperFeature.MapOfMethod);

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

    private enum HelperFeature {
        // 'mapOf(...)' method in class
        MapOfMethod,

        // 'throws IOException' in method signature
        ThrowsIOException
    }

    private static class ExampleMethod {
        private Set<String> imports;
        private String methodSignature;
        private String methodContent;
        private Set<HelperFeature> helperFeatures;

        private Set<String> getImports() {
            return imports;
        }

        public ExampleMethod setImports(Set<String> imports) {
            this.imports = imports;
            return this;
        }

        private String getMethodSignature() {
            return methodSignature;
        }

        private ExampleMethod setMethodSignature(String methodSignature) {
            this.methodSignature = methodSignature;
            return this;
        }

        private String getMethodContent() {
            return methodContent;
        }

        private ExampleMethod setMethodContent(String methodContent) {
            this.methodContent = methodContent;
            return this;
        }

        public Set<HelperFeature> getHelperFeatures() {
            return helperFeatures;
        }

        public ExampleMethod setHelperFeatures(Set<HelperFeature> helperFeatures) {
            this.helperFeatures = helperFeatures;
            return this;
        }
    }
}
