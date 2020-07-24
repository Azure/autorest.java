/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.javamodel;

import com.azure.autorest.fluent.model.clientmodel.FluentResourceModel;
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
}
