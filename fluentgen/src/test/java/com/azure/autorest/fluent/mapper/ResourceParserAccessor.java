/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.mapper;

import com.azure.autorest.fluent.model.clientmodel.FluentResourceCollection;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceModel;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.create.ResourceCreate;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.update.ResourceUpdate;
import com.azure.autorest.model.clientmodel.ClientModel;

import java.util.List;
import java.util.Optional;

public class ResourceParserAccessor {

    public static List<ResourceCreate> resolveResourceCreate(
            FluentResourceCollection collection,
            List<FluentResourceModel> availableFluentModels,
            List<ClientModel> availableModels) {
        return ResourceParser.resolveResourceCreate(collection, availableFluentModels, availableModels);
    }

    public static Optional<ResourceUpdate> resolveResourceUpdate(
            FluentResourceCollection collection,
            ResourceCreate resourceCreate,
            List<ClientModel> availableModels) {
        return ResourceParser.resolveResourceUpdate(collection, resourceCreate, availableModels);
    }
}
