/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.javamodel;

import com.azure.autorest.fluent.model.clientmodel.FluentManager;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceCollection;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceModel;
import com.azure.autorest.fluent.template.FluentManagerTemplate;
import com.azure.autorest.fluent.template.FluentResourceCollectionImplementationTemplate;
import com.azure.autorest.fluent.template.FluentResourceCollectionInterfaceTemplate;
import com.azure.autorest.fluent.template.FluentResourceModelImplementationTemplate;
import com.azure.autorest.fluent.template.FluentResourceModelInterfaceTemplate;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.model.javamodel.JavaPackage;

public class FluentJavaPackage extends JavaPackage {

    public final void addFluentResourceModel(FluentResourceModel model) {
        JavaFile javaFile = getJavaFileFactory().createSourceFile(
                model.getResourceInterfaceClassType().getPackage(),
                model.getResourceInterfaceClassType().getName());
        FluentResourceModelInterfaceTemplate.getInstance().write(model, javaFile);
        getJavaFiles().add(javaFile);

        javaFile = getJavaFileFactory().createSourceFile(
                model.getResourceImplementationClassType().getPackage(),
                model.getResourceImplementationClassType().getName());
        FluentResourceModelImplementationTemplate.getInstance().write(model, javaFile);
        getJavaFiles().add(javaFile);
    }

    public final void addFluentResourceCollection(FluentResourceCollection collection) {
        JavaFile javaFile = getJavaFileFactory().createSourceFile(
                collection.getCollectionInterfaceClassType().getPackage(),
                collection.getCollectionInterfaceClassType().getName());
        FluentResourceCollectionInterfaceTemplate.getInstance().write(collection, javaFile);
        getJavaFiles().add(javaFile);

//        javaFile = getJavaFileFactory().createSourceFile(
//                collection.getCollectionImplementationClassType().getPackage(),
//                collection.getCollectionImplementationClassType().getName());
//        FluentResourceCollectionImplementationTemplate.getInstance().write(collection, javaFile);
//        getJavaFiles().add(javaFile);
    }

    public final void addFluentManager(FluentManager model) {
        JavaFile javaFile = getJavaFileFactory().createSourceFile(
                model.getClassType().getPackage(),
                model.getClassType().getName());
        FluentManagerTemplate.getInstance().write(model, javaFile);
        getJavaFiles().add(javaFile);
    }
}
