/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.template;

import com.azure.autorest.fluent.model.clientmodel.FluentManager;
import com.azure.autorest.model.javamodel.JavaFile;

import java.util.HashSet;
import java.util.Set;

public class FluentManagerTemplate {

    private static final FluentManagerTemplate INSTANCE = new FluentManagerTemplate();

    public static FluentManagerTemplate getInstance() {
        return INSTANCE;
    }

    public void write(FluentManager manager, JavaFile javaFile) {
        Set<String> imports = new HashSet<>();
        manager.getProperties().forEach(property -> imports.add(property.getFluentType().getFullName()));
        javaFile.declareImport(imports);

        javaFile.javadocComment(comment -> {
            comment.description(manager.getDescription());
        });

        javaFile.publicFinalClass(manager.getType().getName(), classBlock -> {
            manager.getProperties().forEach(property -> {
                classBlock.privateMemberVariable(property.getFluentType().getName(), property.getName());

                classBlock.publicMethod(String.format("%1$s %2$s()", property.getFluentType().getName(), property.getMethodName()), methodBlock -> {
                    methodBlock.methodReturn(property.getName());
                });
            });
        });
    }
}
