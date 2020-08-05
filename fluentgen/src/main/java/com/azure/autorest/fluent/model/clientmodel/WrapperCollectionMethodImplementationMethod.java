/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel;

import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.template.prototype.MethodTemplate;

public class WrapperCollectionMethodImplementationMethod implements WrapperImplementationMethod {

    private final MethodTemplate implementationMethodTemplate;

    public WrapperCollectionMethodImplementationMethod(FluentCollectionMethod fluentMethod, IType innerType) {
        implementationMethodTemplate = MethodTemplate.builder()
                .methodSignature(fluentMethod.getMethodSignature())
                .method(block -> {
                    block.methodReturn(String.format("this.%1$s().%2$s", ModelNaming.METHOD_INNER, fluentMethod.getMethodCall()));
                })
                .build();
    }

    @Override
    public MethodTemplate getMethodTemplate() {
        return implementationMethodTemplate;
    }
}
