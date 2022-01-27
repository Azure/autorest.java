// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.fluent.mapper;

import com.azure.autorest.fluent.model.arm.ModelCategory;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceCollection;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceModel;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.action.ResourceActions;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.create.ResourceCreate;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.delete.ResourceDelete;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.get.ResourceRefresh;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.update.ResourceUpdate;
import com.azure.autorest.model.clientmodel.ClientModel;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class ResourceParserAccessor {

    public static List<ResourceCreate> resolveResourceCreate(
            FluentResourceCollection collection,
            List<FluentResourceModel> availableFluentModels,
            List<ClientModel> availableModels) {
        return ResourceParser.resolveResourceCreate(collection, availableFluentModels, availableModels);
    }

    public static List<ResourceCreate> resolveResourceCreate(
            FluentResourceCollection collection,
            List<FluentResourceModel> availableFluentModels,
            List<ClientModel> availableModels,
            List<ModelCategory> categories) {
        return ResourceParser.resolveResourceCreate(collection, availableFluentModels, availableModels, categories);
    }

    public static Optional<ResourceUpdate> resolveResourceUpdate(
            FluentResourceCollection collection,
            ResourceCreate resourceCreate,
            List<ClientModel> availableModels) {
        return ResourceParser.resolveResourceUpdate(collection, resourceCreate, availableModels);
    }

    static Optional<ResourceRefresh> resolveResourceRefresh(
            FluentResourceCollection collection,
            ResourceCreate resourceCreate) {
        return ResourceParser.resolveResourceRefresh(collection, resourceCreate);
    }

    public static Optional<ResourceDelete> resolveResourceDelete(
            FluentResourceCollection collection,
            ResourceCreate resourceCreate) {
        return ResourceParser.resolveResourceDelete(collection, resourceCreate);
    }

    public static Optional<ResourceActions> resourceResourceActions(
            FluentResourceCollection collection,
            ResourceCreate resourceCreate) {
        return ResourceParser.resourceResourceActions(collection, resourceCreate);
    }
}
