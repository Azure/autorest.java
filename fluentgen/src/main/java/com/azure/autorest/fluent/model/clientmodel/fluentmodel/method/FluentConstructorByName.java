/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.fluentmodel.method;

import com.azure.autorest.fluent.model.clientmodel.FluentResourceModel;
import com.azure.autorest.fluent.model.clientmodel.ModelNaming;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ReturnValue;
import com.azure.autorest.model.javamodel.JavaJavadocComment;
import com.azure.autorest.template.prototype.MethodTemplate;

import java.util.Set;

public class FluentConstructorByName extends FluentMethod {

    private final IType resourceNameType;
    private final IType managerType;

    public FluentConstructorByName(FluentResourceModel model, FluentMethodType type,
                                   IType resourceNameType, String propertyNameForResourceName, IType managerType) {
        super(model, type);

        this.resourceNameType = resourceNameType;
        this.managerType = managerType;

        this.implementationReturnValue = new ReturnValue("", model.getImplementationType());

        this.implementationMethodTemplate = MethodTemplate.builder()
                .methodSignature(this.getImplementationMethodSignature())
                .method(block -> {
                    block.line(String.format("this.%1$s = new %2$s();", ModelNaming.MODEL_PROPERTY_INNER, model.getInnerModel().getName()));
                    block.line(String.format("this.%1$s = %2$s;", ModelNaming.MODEL_PROPERTY_MANAGER, ModelNaming.MODEL_PROPERTY_MANAGER));
                    block.line(String.format("this.%1$s = name;", propertyNameForResourceName));
                })
                .build();
    }

    @Override
    public String getImplementationMethodSignature() {
        return String.format("%1$s(%2$s name, %3$s %4$s)",
                implementationReturnValue.getType().toString(),
                resourceNameType.toString(),
                managerType.toString(), ModelNaming.MODEL_PROPERTY_MANAGER);
    }

    @Override
    protected String getBaseMethodSignature() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeJavadoc(JavaJavadocComment commentBlock) {
        // NOOP
    }

    @Override
    public void addImportsTo(Set<String> imports, boolean includeImplementationImports) {
        if (includeImplementationImports) {
            resourceNameType.addImportsTo(imports, false);
            managerType.addImportsTo(imports, false);
        }
    }
}
