// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.template;

import com.azure.autorest.model.clientmodel.ClientModelProperty;
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
                    methodBlock.ifBlock("value == null", ifBlock -> {
                        methodBlock.line("value = this." + property.getName() + ";");
                    });
                }
                methodBlock.methodReturn("value");
            });

            // getter/setters
            for (ClientModelProperty property : model.getProperties()) {
                // getter
                classBlock.javadocComment(comment -> {
                    comment.description(String.format("Get the %1$s property: %2$s", property.getName(), property.getDescription()));
                    comment.methodReturns(String.format("the %1$s value", property.getName()));
                });
                classBlock.publicMethod(property.getClientType() + " " + property.getGetterName() + "()", methodBlock -> {
                    methodBlock.methodReturn("this." + property.getName());
                });

                // setter
                classBlock.javadocComment(comment -> {
                    comment.description(String.format("Set the %s property: %s", property.getName(), property.getDescription()));
                    comment.param(property.getName(), String.format("the %s value to set", property.getName()));
                });
                classBlock.publicMethod(String.format("void %1$s(%2$s %3$s)", property.getSetterName(), property.getClientType(), property.getName()), methodBlock -> {
                    methodBlock.line(String.format("this.%1$s = %1$s;", property.getName()));
                });
            }
        });
    }
}
