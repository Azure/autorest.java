/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.fluentmodel;

import com.azure.autorest.fluent.model.clientmodel.FluentCollectionMethod;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceCollection;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceModel;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;

import java.util.List;

public class ResourceCreate {

    private FluentResourceModel resourceModel;
    private FluentResourceCollection resourceCollection;

    private FluentCollectionMethod methodReference;

    private boolean hasResourceGroup;
    private boolean hasLocation;
    private boolean hasTags;

    private List<ClientMethodParameter> pathParameters;
    private ClientMethodParameter bodyParameter;
}
