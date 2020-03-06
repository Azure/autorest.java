/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 *
 */

package com.azure.autorest.fluent.model;

public enum WellKnownMethodName {
    LIST("list"),
    LIST_BY_RESOURCE_GROUP("listByResourceGroup"),
    GET_BY_RESOURCE_GROUP("getByResourceGroup"),
    DELETE("delete");

    private final String methodName;

    WellKnownMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodName() {
        return methodName;
    }

    public static WellKnownMethodName fromMethodName(String methodName) {
        for (WellKnownMethodName name : WellKnownMethodName.values()) {
            if (name.getMethodName().equals(methodName)) {
                return name;
            }
        }
        return null;
    }
}
