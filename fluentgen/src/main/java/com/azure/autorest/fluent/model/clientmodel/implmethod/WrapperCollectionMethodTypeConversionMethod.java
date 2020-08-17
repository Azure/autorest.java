/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.implmethod;

import com.azure.autorest.fluent.model.clientmodel.FluentCollectionMethod;
import com.azure.autorest.fluent.model.clientmodel.ModelNaming;
import com.azure.autorest.fluent.util.TypeConversionUtils;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ListType;
import com.azure.autorest.model.clientmodel.MapType;
import com.azure.autorest.template.prototype.MethodTemplate;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

// Implementation method template for return type requires conversion.
// E.g.
//    PagedIterable<StorageAccountInner> inner = this.inner().list();
//    return inner.mapPage(inner1 -> new StorageAccountImpl(inner1, this.manager()));
public class WrapperCollectionMethodTypeConversionMethod implements WrapperMethod {

    private final MethodTemplate conversionMethodTemplate;

    public WrapperCollectionMethodTypeConversionMethod(FluentCollectionMethod fluentMethod, IType innerType) {
        Set<String> imports = new HashSet<>();
        fluentMethod.getFluentReturnType().addImportsTo(imports, false);
        // Type inner = ...
        innerType.addImportsTo(imports, false);
        // Collectors.toList
        if (innerType instanceof ListType || innerType instanceof MapType) {
            imports.add(Collectors.class.getName());
        }

        conversionMethodTemplate = MethodTemplate.builder()
                .imports(imports)
                .methodSignature(fluentMethod.getMethodSignature())
                .method(block -> {
                    block.line(String.format("%1$s inner = this.%2$s().%3$s;", innerType, ModelNaming.METHOD_INNER, fluentMethod.getMethodInvocation()));
                    if (TypeConversionUtils.isPagedIterable(innerType)) {
                        block.methodReturn(TypeConversionUtils.conversionExpression(innerType, "inner"));
                    } else {
                        block.ifBlock("inner != null", ifBlock -> {
                            String expression = TypeConversionUtils.conversionExpression(innerType, "inner");
                            block.methodReturn(TypeConversionUtils.unmodifiableCollection(innerType, expression));
                        }).elseBlock(elseBlock -> {
                            block.methodReturn("null");
                        });
                    }
                })
                .build();;
    }

    @Override
    public MethodTemplate getMethodTemplate() {
        return conversionMethodTemplate;
    }
}
