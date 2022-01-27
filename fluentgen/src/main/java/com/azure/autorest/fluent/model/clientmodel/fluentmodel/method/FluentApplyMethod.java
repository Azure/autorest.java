// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.fluent.model.clientmodel.fluentmodel.method;

import com.azure.autorest.fluent.model.clientmodel.FluentCollectionMethod;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceCollection;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceModel;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.ResourceLocalVariables;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;

import java.util.List;

public class FluentApplyMethod extends FluentBaseMethod {

    public FluentApplyMethod(FluentResourceModel model, FluentMethodType type,
                             List<ClientMethodParameter> parameters, ResourceLocalVariables resourceLocalVariables,
                             FluentResourceCollection collection, FluentCollectionMethod collectionMethod,
                             ResourceLocalVariables resourceLocalVariablesDefinedInClass) {

        super(model, type, "apply", "Executes the update request.", "the updated resource.",
                parameters, resourceLocalVariables, collection, collectionMethod, resourceLocalVariablesDefinedInClass, false);
    }
}
