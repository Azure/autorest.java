// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.fluent.model.clientmodel.fluentmodel.method;

import com.azure.autorest.fluent.model.clientmodel.FluentCollectionMethod;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceCollection;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceModel;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.ResourceLocalVariables;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;

import java.util.List;

public class FluentRefreshMethod extends FluentBaseMethod {

    public FluentRefreshMethod(FluentResourceModel model, FluentMethodType type,
                               List<ClientMethodParameter> parameters, ResourceLocalVariables resourceLocalVariables,
                               FluentResourceCollection collection, FluentCollectionMethod collectionMethod,
                               ResourceLocalVariables resourceLocalVariablesDefinedInClass) {

        super(model, type, "refresh", "Refreshes the resource to sync with Azure.", "the refreshed resource.",
                parameters, resourceLocalVariables, collection, collectionMethod, resourceLocalVariablesDefinedInClass, true);
    }
}
