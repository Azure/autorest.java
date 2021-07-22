/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.arm;

import com.azure.autorest.fluent.model.ResourceTypeName;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.MapType;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

public class ResourceClientModel {

    private ResourceClientModel() {

    }

    private static final ClientModel MODEL_SUB_RESOURCE = new ClientModel.Builder()
            .name(ResourceTypeName.SUB_RESOURCE)
            .packageName("com.azure.core.management")
            .properties(Collections.singletonList(
                    new ClientModelProperty.Builder()
                            .name(ResourceTypeName.FIELD_ID)
                            .serializedName(ResourceTypeName.FIELD_ID)
                            .description("Fully qualified resource Id for the resource.")
                            .clientType(ClassType.String)
                            .build()
            ))
            .build();
    private static final ClientModel MODEL_PROXY_RESOURCE = new ClientModel.Builder()
            .name(ResourceTypeName.PROXY_RESOURCE)
            .packageName("com.azure.core.management")
            .properties(Arrays.asList(
                    new ClientModelProperty.Builder()
                            .name(ResourceTypeName.FIELD_ID)
                            .serializedName(ResourceTypeName.FIELD_ID)
                            .description("Fully qualified resource Id for the resource.")
                            .isRequired(true)
                            .isReadOnly(true)
                            .clientType(ClassType.String)
                            .build(),
                    new ClientModelProperty.Builder()
                            .name(ResourceTypeName.FIELD_NAME)
                            .serializedName(ResourceTypeName.FIELD_NAME)
                            .description("The name of the resource.")
                            .isRequired(true)
                            .isReadOnly(true)
                            .clientType(ClassType.String)
                            .build(),
                    new ClientModelProperty.Builder()
                            .name(ResourceTypeName.FIELD_TYPE)
                            .serializedName(ResourceTypeName.FIELD_TYPE)
                            .description("The type of the resource.")
                            .isRequired(true)
                            .isReadOnly(true)
                            .clientType(ClassType.String)
                            .build()
            ))
            .build();

    private static final ClientModel MODEL_RESOURCE = new ClientModel.Builder()
            .name(ResourceTypeName.RESOURCE)
            .packageName("com.azure.core.management")
            .parentModelName(ResourceTypeName.PROXY_RESOURCE)
            .properties(Arrays.asList(
                    new ClientModelProperty.Builder()
                            .name(ResourceTypeName.FIELD_LOCATION)
                            .serializedName(ResourceTypeName.FIELD_LOCATION)
                            .description("The geo-location where the resource lives.")
                            .isRequired(true)
                            .isReadOnly(false)
                            .clientType(ClassType.String)
                            .mutabilities(Arrays.asList(ClientModelProperty.Mutability.CREATE, ClientModelProperty.Mutability.READ))
                            .build(),
                    new ClientModelProperty.Builder()
                            .name(ResourceTypeName.FIELD_TAGS)
                            .serializedName(ResourceTypeName.FIELD_TAGS)
                            .description("Resource tags.")
                            .isRequired(false)
                            .isReadOnly(false)
                            .clientType(new MapType(ClassType.String))
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
