/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.fluentmodel;

import com.azure.autorest.fluent.model.clientmodel.FluentResourceModel;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.create.DefinitionStage;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.ReturnValue;

import java.util.List;

public class FluentMethod {

    private String name;
    private String description;
    private ReturnValue interfaceReturnValue;
    private ReturnValue implementationReturnValue;
    private List<ClientMethodParameter> parameters;

    private FluentResourceModel fluentResourceModel;

    private FluentMethodType type;

    // valid for method in create flow
    private DefinitionStage definitionStage;

    // valid if method operates on property of model, can be inner(), createParameter, or updateParameter
    private ClientModel model;
    private ClientModelProperty modelProperty;

    // valid if method operates on create/update method parameter
    private ClientMethod method;
    private ClientMethodParameter methodParameter;
}
