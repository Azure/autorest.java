/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.template;

import com.azure.autorest.fluent.model.clientmodel.FluentResourceModel;
import com.azure.autorest.fluent.util.FluentUtils;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.template.IJavaTemplate;
import com.azure.core.annotation.Immutable;

import java.util.HashSet;
import java.util.Set;

public class FluentResourceModelInterfaceTemplate implements IJavaTemplate<FluentResourceModel, JavaFile> {

    private static final FluentResourceModelInterfaceTemplate INSTANCE = new FluentResourceModelInterfaceTemplate();

    public static FluentResourceModelInterfaceTemplate getInstance() {
        return INSTANCE;
    }

    @Override
    public void write(FluentResourceModel model, JavaFile javaFile) {
        Set<String> imports = new HashSet<>();
        imports.add(Immutable.class.getName());
        imports.add(model.getInnerModel().getFullName());
        model.getProperties().forEach(p -> p.getClientType().addImportsTo(imports, false));
        javaFile.declareImport(imports);

        javaFile.javadocComment(comment ->
        {
            comment.description(model.getDescription());
        });

        javaFile.annotation("Immutable");
        javaFile.publicInterface(model.getResourceInterfaceClassType().getName(), interfaceBlock -> {
            model.getProperties().forEach(property -> {
                interfaceBlock.javadocComment(comment -> {
                    comment.description(String.format("Get the %1$s property: %2$s", property.getName(), property.getDescription()));
                    comment.methodReturns(String.format("the %1$s value", property.getName()));
                });
                interfaceBlock.publicMethod(String.format("%1$s %2$s()", property.getClientType(), property.getGetterName()));
            });

            interfaceBlock.javadocComment(comment -> {
                comment.description(String.format("Get the inner %s object", model.getInnerModel().getFullName()));
                comment.methodReturns("the inner object");
            });
            interfaceBlock.publicMethod(String.format("%1$s %2$s()", model.getInnerModel().getName(), FluentUtils.getGetterName("inner")));
        });
    }

}
