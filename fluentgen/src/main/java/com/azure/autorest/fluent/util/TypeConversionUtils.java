/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.util;

import com.azure.autorest.fluent.model.clientmodel.ModelNaming;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.GenericType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ListType;
import com.azure.autorest.model.clientmodel.MapType;
import com.azure.core.http.rest.PagedIterable;

import java.util.Objects;

public class TypeConversionUtils {

    private TypeConversionUtils() {

    }

    public static String conversionExpression(IType clientType, String propertyName) {
        String expression = null;
        if (clientType instanceof ClassType) {
            ClassType type = (ClassType) clientType;
            if (FluentUtils.isInnerClassType(type)) {
                expression = String.format("new %1$s(%2$s, this.%3$s())", getModelImplName(type), propertyName, ModelNaming.METHOD_MANAGER);
            }
        } else if (clientType instanceof ListType) {
            ListType type = (ListType) clientType;
            String nestedPropertyName = nextPropertyName(propertyName);
            expression = String.format("%1$s.stream().map(%2$s -> %3$s).collect(Collectors.toList())", propertyName, nestedPropertyName, conversionExpression(type.getElementType(), nestedPropertyName));
        } else if (clientType instanceof MapType) {
            MapType type = (MapType) clientType;
            String nestedPropertyName = nextPropertyName(propertyName);
            expression = String.format("%1$s.entrySet().stream().collect(Collectors.toMap(Entry::getKey, %2$s -> %3$s)", propertyName, nestedPropertyName, conversionExpression(type.getValueType(), nestedPropertyName));
        } else if (clientType instanceof GenericType) {
            GenericType type = (GenericType) clientType;
            if (PagedIterable.class.getSimpleName().equals(type.getName())) {
                IType valueType = type.getTypeArguments()[0];
                if (valueType instanceof ClassType) {
                    String nestedPropertyName = nextPropertyName(propertyName);
                    expression = String.format("%1$s.mapPage(%2$s -> new %3$s(%4$s, this.%5$s()))", propertyName, nestedPropertyName, getModelImplName((ClassType) valueType), nestedPropertyName, ModelNaming.METHOD_MANAGER);
                }
            }
        }
        Objects.requireNonNull(expression, "Unexpected scenario in WrapperTypeConversionMethod.conversionExpression. ClientType is " + clientType);
        return expression;
    }

    public static String unmodifiableCollection(IType clientType, String expression) {
        String unmodifiableMethodName = null;
        if (clientType instanceof ListType) {
            unmodifiableMethodName = "unmodifiableList";
        } else if (clientType instanceof MapType) {
            unmodifiableMethodName = "unmodifiableMap";
        }
        return (unmodifiableMethodName == null)
                ? expression
                : String.format("Collections.%1$s(%2$s)", unmodifiableMethodName, expression);
    }

    public static boolean isPagedIterable(IType clientType) {
        boolean ret = false;
        if (clientType instanceof GenericType) {
            GenericType type = (GenericType) clientType;
            if (PagedIterable.class.getSimpleName().equals(type.getName())) {
                ret = true;
            }
        }
        return ret;
    }

    private static String nextPropertyName(String propertyName) {
        if (propertyName.equals("inner")) {
            return "inner1";
        } else {
            return "inner" + (Integer.parseInt(propertyName.substring(5)) + 1);
        }
    }

    private static String getModelImplName(ClassType classType) {
        return FluentUtils.resourceModelInterfaceClassType(classType).getName() + ModelNaming.MODEL_IMPL_SUFFIX;
    }
}
