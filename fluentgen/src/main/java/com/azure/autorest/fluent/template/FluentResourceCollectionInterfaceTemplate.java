/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.template;

import com.azure.autorest.fluent.model.clientmodel.FluentResourceCollection;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.template.ClientMethodTemplate;
import com.azure.autorest.template.IJavaTemplate;

import java.util.HashSet;
import java.util.Set;

public class FluentResourceCollectionInterfaceTemplate implements IJavaTemplate<FluentResourceCollection, JavaFile> {

    private static final FluentResourceCollectionInterfaceTemplate INSTANCE = new FluentResourceCollectionInterfaceTemplate();

    public static FluentResourceCollectionInterfaceTemplate getInstance() {
        return INSTANCE;
    }

    @Override
    public void write(FluentResourceCollection collection, JavaFile javaFile) {
        Set<String> imports = new HashSet<>();
        collection.addImportsTo(imports, false);
        javaFile.declareImport(imports);

        javaFile.javadocComment(comment -> {
            comment.description(collection.getDescription());
        });

        javaFile.publicInterface(collection.getInterfaceType().getName(), interfaceBlock -> {
            // methods
            collection.getMethods().forEach(method -> {
                ClientMethodTemplate.generateJavadoc(method.getInnerClientMethod(), interfaceBlock, method.getInnerProxyMethod(), true);

                interfaceBlock.publicMethod(method.getMethodSignature());
            });

            // method for inner client
            interfaceBlock.javadocComment(comment -> {
                comment.description(String.format("Gets the inner %s client", collection.getInnerClientType().getFullName()));
                comment.methodReturns("the inner client");
            });
            interfaceBlock.publicMethod(collection.getInnerMethodSignature());
        });
    }
}
