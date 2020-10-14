/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.fluentmodel.method;

import com.azure.autorest.fluent.model.clientmodel.FluentCollectionMethod;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceCollection;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceModel;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.ResourceLocalVariables;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;

import java.util.List;

public class FluentCreateMethod extends FluentBaseMethod {

    public FluentCreateMethod(FluentResourceModel model, FluentMethodType type,
                              List<ClientMethodParameter> parameters, ResourceLocalVariables resourceLocalVariables,
                              FluentResourceCollection collection, FluentCollectionMethod collectionMethod) {

        super(model, type, "create", "Executes the create request.", "the created resource.",
                parameters, resourceLocalVariables, collection, collectionMethod, false);
    }
}
