/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.immutablemodel;

import com.azure.autorest.fluent.model.clientmodel.FluentModelProperty;
import com.azure.autorest.fluent.model.clientmodel.ModelNaming;
import com.azure.autorest.fluent.util.TypeConversionUtils;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.ListType;
import com.azure.autorest.model.clientmodel.MapType;
import com.azure.autorest.template.prototype.MethodTemplate;

import java.util.Collections;
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
public class PropertyTypeConversionTemplate implements ImmutableMethod {

    private final MethodTemplate conversionMethodTemplate;

    public PropertyTypeConversionTemplate(FluentModelProperty fluentProperty, ClientModelProperty property) {
        Set<String> imports = new HashSet<>();
        fluentProperty.getFluentType().addImportsTo(imports, false);
        // Type inner = ...
        property.getClientType().addImportsTo(imports, false);
        if (property.getClientType() instanceof ListType || property.getClientType() instanceof MapType) {
            // Collectors.toList
            imports.add(Collectors.class.getName());

            // Collections.unmodifiableList
            imports.add(Collections.class.getName());
        }

        conversionMethodTemplate = MethodTemplate.builder()
                .imports(imports)
                .methodSignature(fluentProperty.getMethodSignature())
                .method(block -> {
                    block.line(String.format("%1$s %2$s = this.%3$s().%4$s();", property.getClientType().toString(), TypeConversionUtils.tempPropertyName(), ModelNaming.METHOD_INNER_MODEL, property.getGetterName()));
                    block.ifBlock(String.format("%1$s != null", TypeConversionUtils.tempPropertyName()), ifBlock -> {
                        String expression = TypeConversionUtils.conversionExpression(property.getClientType(), TypeConversionUtils.tempPropertyName());
                        block.methodReturn(TypeConversionUtils.unmodifiableCollection(property.getClientType(), expression));
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
