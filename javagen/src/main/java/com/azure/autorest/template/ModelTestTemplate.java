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
        Set<String> imports = new HashSet<>();
        model.addImportsTo(imports, JavaSettings.getInstance());
        ClassType.BinaryData.addImportsTo(imports, false);
        imports.add("org.junit.jupiter.api.Test");

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

        javaFile.declareImport(imports);

        javaFile.publicFinalClass(model.getName() + "Tests", classBlock -> {
            // testDeserialize
            classBlock.annotation("Test");
            classBlock.publicMethod("void testDeserialize()", methodBlock -> {
                methodBlock.line(String.format("%1$s model = BinaryData.fromString(%2$s).toObject(%1$s.class);",
                        model.getName(), ClassType.String.defaultValueExpression(jsonStr)));
                writer.writeAssertion(methodBlock);
            });

            // testSerialize
            classBlock.annotation("Test");
            String methodSignature = "void testSerialize()";
            if (writer.getHelperFeatures().contains(ExampleHelperFeature.ThrowsIOException)) {
                methodSignature += " throws IOException";
            }
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
        });
    }
}
