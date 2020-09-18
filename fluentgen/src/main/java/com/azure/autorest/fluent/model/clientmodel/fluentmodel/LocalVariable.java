/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.fluentmodel;

import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.IType;

public class LocalVariable {
    private final String name;
    private final IType variableType;
    private final ClientMethodParameter methodParameterReference;
    private boolean initializeRequired = false;
    private String initializeExpression;

    public LocalVariable(String name, IType variableType, ClientMethodParameter methodParameterReference) {
        this.name = name;
        this.variableType = variableType;
        this.methodParameterReference = methodParameterReference;
    }

    public String getName() {
        return name;
    }

    public IType getVariableType() {
        return variableType;
    }

    public ClientMethodParameter getMethodParameterReference() {
        return methodParameterReference;
    }

    public boolean isInitializeRequired() {
        return initializeRequired;
    }

    public String getInitializeExpression() {
        return initializeExpression;
    }

    public void setInitializeExpression(String initializeExpression) {
        this.initializeRequired = true;
        this.initializeExpression = initializeExpression;
    }
}
