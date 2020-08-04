/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.arm;

import com.azure.autorest.fluent.model.ResourceType;
import com.azure.autorest.fluent.model.ResourceTypeName;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.ListType;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

public class ResourceClientModel {

    private ResourceClientModel() {

    }

    private static final ClientModel MODEL_SUB_RESOURCE = new ClientModel.Builder()
            .name(ResourceTypeName.SUB_RESOURCE)
            .properties(Collections.singletonList(
                    new ClientModelProperty.Builder()
                            .name(ResourceTypeName.FIELD_ID)
                            .description("Resource ID.")
                            .clientType(ClassType.String)
                            .build()
            ))
            .build();
    private static final ClientModel MODEL_PROXY_RESOURCE = new ClientModel.Builder()
            .name(ResourceTypeName.PROXY_RESOURCE)
            .properties(Arrays.asList(
                    new ClientModelProperty.Builder()
                            .name(ResourceTypeName.FIELD_ID)
                            .description("Resource ID.")
                            .isRequired(true)
                            .isReadOnly(true)
                            .clientType(ClassType.String)
                            .build(),
                    new ClientModelProperty.Builder()
                            .name(ResourceTypeName.FIELD_NAME)
                            .description("Resource name.")
                            .isRequired(true)
                            .isReadOnly(true)
                            .clientType(ClassType.String)
                            .build(),
                    new ClientModelProperty.Builder()
                            .name(ResourceTypeName.FIELD_TYPE)
                            .description("Resource type.")
                            .isRequired(true)
                            .isReadOnly(true)
                            .clientType(ClassType.String)
                            .build()
            ))
            .build();

    private static final ClientModel MODEL_RESOURCE = new ClientModel.Builder()
            .name(ResourceTypeName.RESOURCE)
            .parentModelName(ResourceTypeName.PROXY_RESOURCE)
            .properties(Arrays.asList(
                    new ClientModelProperty.Builder()
                            .name(ResourceTypeName.FIELD_LOCATION)
                            .description("Resource location.")
                            .isRequired(true)
                            .isReadOnly(true)
                            .clientType(ClassType.String)
                            .build(),
                    new ClientModelProperty.Builder()
                            .name(ResourceTypeName.FIELD_TAGS)
                            .description("Resource tags.")
                            .isRequired(false)
                            .isReadOnly(false)
                            .clientType(new ListType(ClassType.String))
                            .build()
            ))
            .build();

    public static Optional<ClientModel> getResourceClientModel(String modelName) {
        ClientModel model = null;

        switch (modelName) {
            case ResourceTypeName.RESOURCE:
                model = MODEL_RESOURCE;
                break;

            case ResourceTypeName.PROXY_RESOURCE:
                model = MODEL_PROXY_RESOURCE;
                break;

            case ResourceTypeName.SUB_RESOURCE:
                model = MODEL_SUB_RESOURCE;
                break;
        }

        return Optional.ofNullable(model);
    }
}
