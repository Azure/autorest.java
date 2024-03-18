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
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ModelTestTemplate implements IJavaTemplate<ClientModel, JavaFile> {

    private static final ModelTestTemplate INSTANCE = new ModelTestTemplate();

    private static final com.fasterxml.jackson.databind.ObjectMapper SERIALIZER = JsonMapper.builder()
        .enable(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS)
        .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
        .enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
        .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        .serializationInclusion(JsonInclude.Include.NON_NULL)
        .addModule(new JavaTimeModule())
        .visibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
        .visibility(PropertyAccessor.SETTER, JsonAutoDetect.Visibility.NONE)
        .visibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE)
        .visibility(PropertyAccessor.IS_GETTER, JsonAutoDetect.Visibility.NONE)
        .build();

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
        ClassType.BINARY_DATA.addImportsTo(imports, false);

        String jsonStr;
        ExampleNode exampleNode;
        try {
            Map<String, Object> jsonObject = ModelTestCaseUtil.jsonFromModel(model);
            jsonStr = SERIALIZER.writeValueAsString(jsonObject);

            exampleNode = ModelExampleUtil.parseNode(model.getType(), jsonObject);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to serialize Map to JSON string", e);
        }

        ModelExampleWriter writer = new ModelExampleWriter(exampleNode, "model");
        imports.addAll(writer.getImports());

        javaFile.declareImport(imports);

        javaFile.publicFinalClass(model.getName() + "Tests", classBlock -> {
            // testDeserialize
            classBlock.annotation("org.junit.jupiter.api.Test");
            classBlock.publicMethod("void testDeserialize() throws Exception", methodBlock -> {
                methodBlock.line(String.format("%1$s model = BinaryData.fromString(%2$s).toObject(%1$s.class);",
                        model.getName(), ClassType.STRING.defaultValueExpression(jsonStr)));
                writer.writeAssertion(methodBlock);
            });

            if (!immutableOutputModel) {
                // testSerialize
                classBlock.annotation("org.junit.jupiter.api.Test");
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
}
