/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.fluentmodel;

import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.fluent.model.clientmodel.MethodParameter;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.util.CodeNamer;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResourceLocalVariables {

    private final Map<ClientMethodParameter, LocalVariable> localVariablesMap = new LinkedHashMap<>();

    public ResourceLocalVariables(ResourceOperation resourceOperation) {
        String prefix = resourceOperation.getLocalVariablePrefix();

        List<ClientMethodParameter> pathParameters = resourceOperation.getPathParameters().stream().map(MethodParameter::getClientMethodParameter).collect(Collectors.toList());
        pathParameters.forEach(p -> localVariablesMap.put(p, new LocalVariable(p.getName(), p.getClientType(), RequestParameterLocation.Path, p)));

        List<ClientMethodParameter> miscParameters = resourceOperation.getMiscParameters();
        miscParameters.forEach(p -> {
            LocalVariable var = new LocalVariable(prefix + CodeNamer.toPascalCase(p.getName()), p.getClientType(), RequestParameterLocation.Query, p);
            var.setInitializeExpression("null");
            localVariablesMap.put(p, var);
        });

        ClientMethodParameter bodyParameter = resourceOperation.getBodyParameter();
        if (bodyParameter != null && !bodyParameter.getClientType().toString().equals(resourceOperation.getResourceModel().getInnerModel().getName())) {
            LocalVariable var = new LocalVariable(prefix + CodeNamer.toPascalCase(bodyParameter.getName()), bodyParameter.getClientType(), RequestParameterLocation.Body, bodyParameter);
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
