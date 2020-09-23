/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.fluentmodel;

import com.azure.autorest.fluent.model.clientmodel.fluentmodel.create.ResourceCreate;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.util.CodeNamer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResourceLocalVariables {

    private final Map<ClientMethodParameter, LocalVariable> localVariablesMap = new HashMap<>();

    public ResourceLocalVariables(ResourceOperation resourceOperation) {
        String prefix = resourceOperation instanceof ResourceCreate ? "create" : "update";

        List<ClientMethodParameter> pathParameters = resourceOperation.getPathParameters();
        pathParameters.forEach(p -> localVariablesMap.put(p, new LocalVariable(p.getName(), p.getClientType(), p)));

        List<ClientMethodParameter> miscParameters = resourceOperation.getMiscParameters();
        miscParameters.forEach(p -> {
            LocalVariable var = new LocalVariable(prefix + CodeNamer.toPascalCase(p.getName()), p.getClientType(), p);
            var.setInitializeExpression("null");
            localVariablesMap.put(p, var);
        });

        ClientMethodParameter bodyParameter = resourceOperation.getBodyParameter();
        if (!bodyParameter.getClientType().toString().equals(resourceOperation.getResourceModel().getInnerModel().getName())) {
            LocalVariable var = new LocalVariable(prefix + CodeNamer.toPascalCase(bodyParameter.getName()), bodyParameter.getClientType(), bodyParameter);
            var.setInitializeExpression(String.format("new %1$s()", bodyParameter.getClientType().toString()));
            localVariablesMap.put(bodyParameter, var);
        }
    }

    public Map<ClientMethodParameter, LocalVariable> getLocalVariablesMap() {
        return this.localVariablesMap;
    }

    public LocalVariable getLocalVariableByMethodParameter(ClientMethodParameter methodParameter) {
        return this.localVariablesMap.get(methodParameter);
    }
}
