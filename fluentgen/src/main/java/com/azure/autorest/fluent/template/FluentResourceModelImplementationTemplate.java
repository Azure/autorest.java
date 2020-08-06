/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.template;

import com.azure.autorest.fluent.model.clientmodel.FluentResourceModel;
import com.azure.autorest.fluent.model.clientmodel.FluentStatic;
import com.azure.autorest.fluent.model.clientmodel.ModelNaming;
import com.azure.autorest.fluent.util.FluentUtils;
import com.azure.autorest.model.clientmodel.ClassType;
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
        ClassType managerType = FluentStatic.getFluentManager().getType();

        List<MethodTemplate> methodTemplates = new ArrayList<>();
        model.getProperties().forEach(p -> methodTemplates.add(p.getImplementationMethodTemplate()));

        Set<String> imports = new HashSet<>();
        imports.add(managerType.getFullName());
        model.addImportsTo(imports, true);
        javaFile.declareImport(imports);

        javaFile.publicFinalClass(String.format("%1$s implements %2$s", model.getImplementationType().getName(), model.getInterfaceType().getName()), classBlock -> {
            // variable for inner model
            classBlock.privateFinalMemberVariable(model.getInnerModel().getName(), ModelNaming.MODEL_PROPERTY_INNER);

            // variable for manager
            classBlock.privateFinalMemberVariable(managerType.getName(), ModelNaming.MODEL_PROPERTY_MANAGER);

            // constructor
            classBlock.publicConstructor(String.format("%1$s(%2$s %3$s, %4$s %5$s)", model.getImplementationType().getName(), model.getInnerModel().getName(), ModelNaming.MODEL_PROPERTY_INNER, managerType.getName(), ModelNaming.MODEL_PROPERTY_MANAGER), methodBlock -> {
                methodBlock.line(String.format("this.%1$s = %2$s;", ModelNaming.MODEL_PROPERTY_INNER, ModelNaming.MODEL_PROPERTY_INNER));
                methodBlock.line(String.format("this.%1$s = %2$s;", ModelNaming.MODEL_PROPERTY_MANAGER, ModelNaming.MODEL_PROPERTY_MANAGER));
            });

            // method for properties
            methodTemplates.forEach(m -> m.writeMethod(classBlock));

            // method for inner model
            classBlock.publicMethod(model.getInnerMethodSignature(), methodBlock -> {
                methodBlock.methodReturn(String.format("this.%s", ModelNaming.MODEL_PROPERTY_INNER));
            });

            // method for manager
            classBlock.privateMethod(String.format("%1$s %2$s()", managerType.getName(), FluentUtils.getGetterName(ModelNaming.METHOD_MANAGER)), methodBlock -> {
                methodBlock.methodReturn(String.format("this.%s", ModelNaming.MODEL_PROPERTY_MANAGER));
            });
        });
    }
}
