/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.modelimpl;

import com.azure.autorest.fluent.model.clientmodel.FluentModelProperty;
import com.azure.autorest.fluent.model.clientmodel.ModelNaming;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.ListType;
import com.azure.autorest.model.clientmodel.MapType;
import com.azure.autorest.template.prototype.MethodTemplate;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

// Implementation method template for simple property
// E.g. "return this.inner().sku()"
public class WrapperPropertyImplementationMethod implements WrapperMethod {

    private final MethodTemplate implementationMethodTemplate;

    public WrapperPropertyImplementationMethod(FluentModelProperty fluentProperty, ClientModelProperty property) {
        Set<String> imports = new HashSet<>();
        fluentProperty.getFluentType().addImportsTo(imports, false);
        if (property.getClientType() instanceof ListType || property.getClientType() instanceof MapType) {
            // Type inner = ...
            property.getClientType().addImportsTo(imports, false);

            // Collections.unmodifiableList
            imports.add(Collections.class.getName());
        }

        implementationMethodTemplate = MethodTemplate.builder()
                .imports(imports)
                .methodSignature(fluentProperty.getMethodSignature())
                .method(block -> {
                    if (property.getClientType() instanceof ListType || property.getClientType() instanceof MapType) {
                        String unmodifiableMethodName = property.getClientType() instanceof ListType
                                ? "unmodifiableList" : "unmodifiableMap";

                        block.line(String.format("%1$s inner = this.%2$s().%3$s();", property.getClientType().toString(), ModelNaming.METHOD_INNER, property.getGetterName()));
                        block.ifBlock("inner != null", ifBlock -> {
                            block.methodReturn(String.format("Collections.%1$s(inner)", unmodifiableMethodName));
                        }).elseBlock(elseBlock -> {
                            block.methodReturn("null");
                        });
                    } else {
                        block.methodReturn(String.format("this.inner().%1$s()", property.getGetterName()));
                    }
                })
                .build();
    }

    @Override
    public MethodTemplate getMethodTemplate() {
        return implementationMethodTemplate;
    }
}
