/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.template;

import com.azure.autorest.fluent.model.clientmodel.FluentResourceModel;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.template.IJavaTemplate;
import com.azure.autorest.template.prototype.MethodTemplate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FluentResourceModelImplementationTemplate implements IJavaTemplate<FluentResourceModel, JavaFile> {

    private static final FluentResourceModelImplementationTemplate INSTANCE = new FluentResourceModelImplementationTemplate();

    public static FluentResourceModelImplementationTemplate getInstance() {
        return INSTANCE;
    }

    @Override
    public void write(FluentResourceModel model, JavaFile javaFile) {
        List<MethodTemplate> methodTemplates = new ArrayList<>();
        model.getProperties().forEach(p -> methodTemplates.add(p.getImplementationMethodTemplate()));

        Set<String> imports = new HashSet<>();
        model.addImportsTo(imports, true);
        javaFile.declareImport(imports);

        javaFile.publicFinalClass(String.format("%1$s implements %2$s", model.getResourceImplementationClassType().getName(), model.getResourceInterfaceClassType().getName()), classBlock -> {
            // variable for inner model
            classBlock.privateFinalMemberVariable(model.getInnerModel().getName(), "innerObject");

            // constructor
            classBlock.publicConstructor(String.format("%1$s(%2$s innerObject)", model.getResourceImplementationClassType().getName(), model.getInnerModel().getName()), methodBlock -> {
                methodBlock.line("this.innerObject = innerObject;");
            });

            // method for properties
            methodTemplates.forEach(m -> m.writeMethod(classBlock));

            // method for inner model
            classBlock.publicMethod(model.getInnerMethodSignature(), methodBlock -> {
                methodBlock.methodReturn("this.innerObject");
            });
        });
    }
}
