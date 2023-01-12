// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.template;

import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.UnionModel;
import com.azure.autorest.model.javamodel.JavaFile;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashSet;
import java.util.Set;

public class UnionModelTemplate implements IJavaTemplate<UnionModel, JavaFile> {

    private static final UnionModelTemplate INSTANCE = new UnionModelTemplate();

    protected UnionModelTemplate() {
    }

    public static UnionModelTemplate getInstance() {
        return INSTANCE;
    }

    @Override
    public void write(UnionModel model, JavaFile javaFile) {
        Set<String> imports = new HashSet<>();
        model.addImportsTo(imports);

        imports.add(JsonValue.class.getName());

        javaFile.declareImport(imports);

        javaFile.javadocComment(comment -> comment.description(model.getDescription()));
        javaFile.publicFinalClass(model.getName(), classBlock -> {
            // properties as member variables
            for (ClientModelProperty property : model.getProperties()) {
                classBlock.privateMemberVariable(property.getClientType() + " " + property.getName());
            }

            // ctor
            classBlock.javadocComment(comment ->
                    comment.description("Creates an instance of " + model.getName() + " class."));
            classBlock.publicConstructor(model.getName() + "()", ctor -> {
            });

            // getValue
            classBlock.annotation("JsonValue");
            classBlock.privateMethod("Object getValue()", methodBlock -> {
                methodBlock.line("Object value = null;");
                for (ClientModelProperty property : model.getProperties()) {
                    String propertyExpression = "this." + property.getName();
                    methodBlock.ifBlock(propertyExpression + " != null", ifBlock -> {
                        methodBlock.line("value = " + property.getWireType().convertFromClientType(propertyExpression) + ";");
                    });
                }
                methodBlock.methodReturn("value");
            });

            // getter/setters
            for (ClientModelProperty property : model.getProperties()) {
                String propertyName = property.getName();
                IType clientType = property.getClientType();

                // getter
                classBlock.javadocComment(comment -> {
                    comment.description(String.format("Get the value if type is %s", clientType));
                    comment.methodReturns(String.format("the value if type is %s", clientType));
                });
                classBlock.publicMethod(property.getClientType() + " " + property.getGetterName() + "()", methodBlock -> {
                    methodBlock.methodReturn("this." + propertyName);
                });

                // setter
                classBlock.javadocComment(comment -> {
                    comment.description(String.format("Set the value as %s\n\nDo not set more than one type to the value.", clientType));
                    comment.param(property.getName(), String.format("the value to set as %s", clientType));
                });
                classBlock.publicMethod(String.format("void %1$s(%2$s %3$s)", property.getSetterName(), property.getClientType(), propertyName), methodBlock -> {
                    methodBlock.line(String.format("this.%1$s = %1$s;", propertyName));
                });
            }
        });
    }
}
