// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.template;

import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.UnionModel;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.model.javamodel.JavaModifier;
import com.azure.core.annotation.Fluent;
import com.azure.core.util.CoreUtils;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
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
        final boolean isAbstractClass = CoreUtils.isNullOrEmpty(model.getParentModelName());
        final String superClassName = model.getParentModelName();

        Set<String> imports = new HashSet<>();
        model.addImportsTo(imports);

        imports.add(Fluent.class.getName());
        imports.add(JsonValue.class.getName());

        javaFile.declareImport(imports);

        List<JavaModifier> modifiers = Collections.singletonList(isAbstractClass ? JavaModifier.Abstract : JavaModifier.Final);
        String classDeclaration = isAbstractClass ? model.getName() : (model.getName() + " extends " + superClassName);
        javaFile.javadocComment(comment -> comment.description(model.getDescription()));
        if (!isAbstractClass) {
            javaFile.annotation("Fluent");
        }
        javaFile.publicClass(modifiers, classDeclaration, classBlock -> {
            // properties as member variables
            for (ClientModelProperty property : model.getProperties()) {
                classBlock.privateMemberVariable(property.getClientType() + " " + property.getName());
            }

            if (!isAbstractClass) {
                // ctor
                classBlock.javadocComment(comment ->
                        comment.description("Creates an instance of " + model.getName() + " class."));
                classBlock.publicConstructor(model.getName() + "()", ctor -> {
                });
            }

            // getter/setters
            for (ClientModelProperty property : model.getProperties()) {
                String propertyName = property.getName();
                IType clientType = property.getClientType();

                // getter
                classBlock.javadocComment(comment -> {
                    comment.description("Gets the value");
                    comment.methodReturns("the value");
                });
                classBlock.annotation("JsonValue");
                classBlock.publicMethod(property.getClientType() + " " + property.getGetterName() + "()", methodBlock -> {
                    methodBlock.methodReturn("this." + propertyName);
                });

                // setter
                classBlock.javadocComment(comment -> {
                    comment.description("Sets the value");
                    comment.param(property.getName(), "the value to set");
                    comment.methodReturns(String.format("the %s object itself.", model.getName()));
                });
                classBlock.publicMethod(String.format("%1$s %2$s(%3$s %4$s)", model.getName(), property.getSetterName(), property.getClientType(), propertyName), methodBlock -> {
                    methodBlock.line(String.format("this.%1$s = %1$s;", propertyName));
                    methodBlock.methodReturn("this");
                });
            }
        });
    }
}
