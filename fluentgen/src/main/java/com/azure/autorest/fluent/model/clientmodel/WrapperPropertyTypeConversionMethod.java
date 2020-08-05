/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel;

import com.azure.autorest.fluent.util.FluentUtils;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ListType;
import com.azure.autorest.model.clientmodel.MapType;
import com.azure.autorest.template.prototype.MethodTemplate;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

// Implementation method template for property requires conversion.
// E.g.
//    BlobRestoreStatusInner inner = this.inner().blobRestoreStatus();
//    if (inner != null) {
//        return new BlobRestoreStatusImpl(inner);
//    } else {
//        return null;
//    }
public class WrapperPropertyTypeConversionMethod implements WrapperImplementationMethod {

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
                    block.line(String.format("%1$s inner = this.inner().%2$s();", property.getClientType().toString(), property.getGetterName()));
                    block.ifBlock("inner != null", ifBlock -> {
                        block.methodReturn(this.conversionExpression(property.getClientType(), "inner"));
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

    private String conversionExpression(IType clientType, String propertyName) {
        String expression = null;
        if (clientType instanceof ClassType) {
            ClassType type = (ClassType) clientType;
            if (FluentUtils.isInnerClassType(type)) {
                expression = String.format("new %1$s(%2$s)", FluentUtils.resourceModelInterfaceClassType(type).getName() + ModelNaming.MODEL_IMPL_SUFFIX, propertyName);
            }
        } else if (clientType instanceof ListType) {
            ListType type = (ListType) clientType;
            String nestedPropertyName = nextPropertyName(propertyName);
            expression = String.format("%1$s.stream().map(%2$s -> %3$s).collect(Collectors.toList())", propertyName, nestedPropertyName, conversionExpression(type.getElementType(), nestedPropertyName));
        } else if (clientType instanceof MapType) {
            MapType type = (MapType) clientType;
            String nestedPropertyName = nextPropertyName(propertyName);
            expression = String.format("%1$s.entrySet().stream().collect(Collectors.toMap(Entry::getKey, %2$s -> %3$s)", propertyName, nestedPropertyName, conversionExpression(type.getValueType(), nestedPropertyName));
        }
        Objects.requireNonNull(expression, "Unexpected scenario in WrapperTypeConversionMethod.conversionExpression");
        return expression;
    }

    private static String nextPropertyName(String propertyName) {
        if (propertyName.equals("inner")) {
            return "inner1";
        } else {
            return "inner" + (Integer.parseInt(propertyName.substring(5)) + 1);
        }
    }
}
