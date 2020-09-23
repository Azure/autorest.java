/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.immutablemodel;

import com.azure.autorest.fluent.model.clientmodel.FluentCollectionMethod;
import com.azure.autorest.fluent.model.clientmodel.ModelNaming;
import com.azure.autorest.fluent.util.TypeConversionUtils;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.PrimitiveType;
import com.azure.autorest.template.prototype.MethodTemplate;

// Implementation method template for simple return type
// E.g. "return this.inner().checkExistence(...)"
public class CollectionMethodTemplate implements ImmutableMethod {

    private final MethodTemplate implementationMethodTemplate;

    public CollectionMethodTemplate(FluentCollectionMethod fluentMethod, IType innerType) {
        implementationMethodTemplate = MethodTemplate.builder()
                .methodSignature(fluentMethod.getMethodSignature())
                .method(block -> {
                    if (innerType == PrimitiveType.Void || innerType == PrimitiveType.Void.asNullable()) {
                        block.line(String.format("this.%1$s().%2$s;", ModelNaming.METHOD_SERVICE_CLIENT, fluentMethod.getMethodInvocation()));
                    } else {
                        String expression = String.format("this.%1$s().%2$s", ModelNaming.METHOD_SERVICE_CLIENT, fluentMethod.getMethodInvocation());
                        block.methodReturn(TypeConversionUtils.unmodifiableCollection(innerType, expression));
                    }
                })
                .build();
    }

    @Override
    public MethodTemplate getMethodTemplate() {
        return implementationMethodTemplate;
    }
}
