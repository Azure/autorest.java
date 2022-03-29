// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.template;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.javamodel.JavaFile;
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
        imports.add(ClassType.BinaryData.getFullName());
        imports.add("org.junit.jupiter.api.Test");
        javaFile.declareImport(imports);
//        BinaryData.fromString().toObject(ClientModel.class);

        String jsonStr;
        try {
            Map<String, Object> jsonObject = ModelTestCaseUtil.jsonFromModel(model);
            jsonStr = SERIALIZER.serialize(jsonObject, SerializerEncoding.JSON);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to serialize Map to JSON string", e);
        }

        javaFile.publicFinalClass(model.getName() + "Tests", classBlock -> {
            classBlock.annotation("Test");
            classBlock.publicMethod("void testSerialization()", methodBlock -> {
                methodBlock.line(String.format("%1$s model = BinaryData.fromString(%2$s).toObject(%1$s.class);",
                        model.getName(), ClassType.String.defaultValueExpression(jsonStr)));
                // TODO assert
            });
        });
    }
}
