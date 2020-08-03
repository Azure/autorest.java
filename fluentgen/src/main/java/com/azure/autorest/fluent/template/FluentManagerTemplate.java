/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.template;

import com.azure.autorest.fluent.model.clientmodel.FluentManager;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.core.annotation.Immutable;

import java.util.HashSet;
import java.util.Set;

public class FluentManagerTemplate {

    private static final FluentManagerTemplate INSTANCE = new FluentManagerTemplate();

    public static FluentManagerTemplate getInstance() {
        return INSTANCE;
    }

    public void write(FluentManager model, JavaFile javaFile) {
        Set<String> imports = new HashSet<>();
        imports.add(Immutable.class.getName());
        javaFile.declareImport(imports);

        javaFile.javadocComment(comment -> {
            comment.description(model.getDescription());
        });

        javaFile.annotation("Immutable");
        javaFile.publicFinalClass(model.getClassType().getName(), classBlock -> {

        });
    }
}
