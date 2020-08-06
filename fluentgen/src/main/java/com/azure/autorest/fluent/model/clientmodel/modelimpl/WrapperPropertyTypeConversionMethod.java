/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.modelimpl;

import com.azure.autorest.fluent.model.clientmodel.FluentModelProperty;
import com.azure.autorest.fluent.model.clientmodel.ModelNaming;
import com.azure.autorest.fluent.util.TypeConversionUtils;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.ListType;
import com.azure.autorest.model.clientmodel.MapType;
import com.azure.autorest.template.prototype.MethodTemplate;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

// Implementation method template for property requires conversion.
// E.g.
//    BlobRestoreStatusInner inner = this.inner().blobRestoreStatus();
//    if (inner != null) {
//        return new BlobRestoreStatusImpl(inner, this.manager());
//    } else {
//        return null;
//    }
public class WrapperPropertyTypeConversionMethod implements WrapperMethod {

    private final MethodTemplate conversionMethodTemplate;

    public WrapperPropertyTypeConversionMethod(FluentModelProperty fluentProperty, ClientModelProperty property) {
        Set<String> imports = new HashSet<>();
        fluentProperty.getFluentType().addImportsTo(imports, false);
        // Type inner = ...
        property.getClientType().addImportsTo(imports, false);
        // Collectors.toList
        if (property.getClientType() instanceof ListType || property.getClientType() instanceof MapType) {
            imports.add(Collectors.class.getName());
        }

        conversionMethodTemplate = MethodTemplate.builder()
                .imports(imports)
                .methodSignature(fluentProperty.getMethodSignature())
                .method(block -> {
                    block.line(String.format("%1$s inner = this.%2$s().%3$s();", property.getClientType().toString(), ModelNaming.METHOD_INNER, property.getGetterName()));
                    block.ifBlock("inner != null", ifBlock -> {
                        block.methodReturn(TypeConversionUtils.conversionExpression(property.getClientType(), "inner"));
                    }).elseBlock(elseBlock -> {
                        block.methodReturn("null");
                    });
                })
                .build();
    }

    @Override
    public MethodTemplate getMethodTemplate() {
        return conversionMethodTemplate;
    }
}
