// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.template;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.examplemodel.ExampleHelperFeature;
import com.azure.autorest.model.clientmodel.examplemodel.ExampleNode;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.template.example.ModelExampleWriter;
import com.azure.autorest.util.ModelExampleUtil;
import com.azure.autorest.util.ModelTestCaseUtil;
import com.azure.core.util.serializer.JacksonAdapter;
import com.azure.core.util.serializer.SerializerAdapter;
import com.azure.core.util.serializer.SerializerEncoding;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class ModelTestTemplate implements IJavaTemplate<ClientModel, JavaFile> {

    private static final ModelTestTemplate INSTANCE = new ModelTestTemplate();

    private static final SerializerAdapter SERIALIZER = JacksonAdapter.createDefaultSerializerAdapter();

    private ModelTestTemplate() {
    }

    public static ModelTestTemplate getInstance() {
        return INSTANCE;
    }

    @Override
    public void write(ClientModel model, JavaFile javaFile) {

        final boolean immutableOutputModel = JavaSettings.getInstance().isOutputModelImmutable()
                && model.getImplementationDetails() != null && !model.getImplementationDetails().isInput();

        Set<String> imports = new HashSet<>();
        model.addImportsTo(imports, JavaSettings.getInstance());
        ClassType.BinaryData.addImportsTo(imports, false);

        String jsonStr;
        ExampleNode exampleNode;
        try {
            Map<String, Object> jsonObject = ModelTestCaseUtil.jsonFromModel(model);
            jsonStr = SERIALIZER.serialize(jsonObject, SerializerEncoding.JSON);

            exampleNode = ModelExampleUtil.parseNode(model.getType(), jsonObject);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to serialize Map to JSON string", e);
        }

        ModelExampleWriter writer = new ModelExampleWriter(exampleNode, "model");
        imports.addAll(writer.getImports());

        boolean containsTest = containsTestInImports(imports);
        if (!containsTest) {
            imports.add("org.junit.jupiter.api.Test");
        }

        javaFile.declareImport(imports);

        javaFile.publicFinalClass(model.getName() + "Tests", classBlock -> {
            // testDeserialize
            if (containsTest) {
                classBlock.annotation("org.junit.jupiter.api.Test");
            } else {
                classBlock.annotation("Test");
            }
            classBlock.publicMethod("void testDeserialize() throws Exception", methodBlock -> {
                methodBlock.line(String.format("%1$s model = BinaryData.fromString(%2$s).toObject(%1$s.class);",
                        model.getName(), ClassType.String.defaultValueExpression(jsonStr)));
                writer.writeAssertion(methodBlock);
            });

            if (!immutableOutputModel) {
                // testSerialize
                if (containsTest) {
                    classBlock.annotation("org.junit.jupiter.api.Test");
                } else {
                    classBlock.annotation("Test");
                }
                String methodSignature = "void testSerialize() throws Exception";
                classBlock.publicMethod(methodSignature, methodBlock -> {
                    methodBlock.line(String.format("%1$s model = %2$s;",
                            model.getName(), writer.getModelInitializationCode()));
                    methodBlock.line(String.format("model = BinaryData.fromObject(model).toObject(%1$s.class);",
                            model.getName()));
                    writer.writeAssertion(methodBlock);
                });

                if (writer.getHelperFeatures().contains(ExampleHelperFeature.MapOfMethod)) {
                    ModelExampleWriter.writeMapOfMethod(classBlock);
                }
            }
        });
    }

    private boolean containsTestInImports(Set<String> imports) {
        return imports.stream().anyMatch(imp -> !imp.equals("org.junit.jupiter.api.Test") && (imp.endsWith(".Test") || imp.equals("Test")));
    }
}
