/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.fluentmodel.method;

import com.azure.autorest.fluent.model.clientmodel.FluentResourceModel;
import com.azure.autorest.fluent.model.clientmodel.ModelNaming;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ReturnValue;
import com.azure.autorest.model.javamodel.JavaJavadocComment;
import com.azure.autorest.template.prototype.MethodTemplate;

import java.util.Set;

public class FluentDefineMethod extends FluentMethod {

    private final IType resourceNameType;

    public FluentDefineMethod(FluentResourceModel model, FluentMethodType type,
                              String resourceName, IType resourceNameType) {
        super(model, type);

        this.resourceNameType = resourceNameType;

        this.name = "define" + resourceName;
        String interfaceTypeName = model.getInterfaceType().getName();
        this.description = String.format("Begins definition for a new %1$s resource.", interfaceTypeName);

        this.interfaceReturnValue = new ReturnValue(String.format("the first stage of the new %1$s definition.", interfaceTypeName),
                new ClassType.Builder()
                        .name(String.format("%1$s.%2$s.Blank", model.getInterfaceType().getName(), ModelNaming.MODEL_FLUENT_INTERFACE_DEFINITION_STAGES))
                        .build());
        this.implementationReturnValue = new ReturnValue("", model.getImplementationType());
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public MethodTemplate getMethodTemplate() {
        if (this.implementationMethodTemplate == null) {
            this.implementationMethodTemplate = MethodTemplate.builder()
                    .methodSignature(this.getImplementationMethodSignature())
                    .method(block -> {
                        block.methodReturn(String.format("new %1$s(name, this.%2$s())",
                                fluentResourceModel.getImplementationType().toString(),
                                ModelNaming.METHOD_MANAGER));
                    })
                    .build();
        }
        return this.implementationMethodTemplate;
    }

    @Override
    protected String getBaseMethodSignature() {
        return String.format("%1$s(%2$s name)", this.name, resourceNameType.toString());
    }

    @Override
    public void writeJavadoc(JavaJavadocComment commentBlock) {
        commentBlock.description(description);
        commentBlock.param("name", "resource name.");
        commentBlock.methodReturns(interfaceReturnValue.getDescription());
    }

    @Override
    public void addImportsTo(Set<String> imports, boolean includeImplementationImports) {
        if (includeImplementationImports) {
            fluentResourceModel.getInterfaceType().addImportsTo(imports, false);
        } else {
            fluentResourceModel.getImplementationType().addImportsTo(imports, false);
        }
        resourceNameType.addImportsTo(imports, false);
    }
}
