/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel;

public class ModelNaming {

    public static final String METHOD_INNER = "inner";
    public static final String METHOD_MANAGER = "manager";

    public static final String MODEL_IMPL_SUFFIX = "Impl";

    public static final String MODEL_PROPERTY_INNER = "innerObject";
    public static final String MODEL_PROPERTY_MANAGER = "serviceManager";

    public static final String COLLECTION_IMPL_SUFFIX = "Impl";

    public static final String COLLECTION_PROPERTY_INNER = "innerClient";
    public static final String COLLECTION_PROPERTY_MANAGER = "serviceManager";

    public static final String MANAGER_PROPERTY_BUILDER = "clientBuilder";

    public static final String MODEL_FLUENT_INTERFACE_DEFINITION = "Definition";
    public static final String MODEL_FLUENT_INTERFACE_DEFINITION_STAGES = "DefinitionStages";

    public static final String MODEL_FLUENT_INTERFACE_UPDATE = "Update";
    public static final String MODEL_FLUENT_INTERFACE_UPDATE_STAGES = "UpdateStages";

    private ModelNaming() {
    }
}
