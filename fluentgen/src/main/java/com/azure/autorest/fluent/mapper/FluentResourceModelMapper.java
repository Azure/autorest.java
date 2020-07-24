/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.mapper;

import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceModel;
import com.azure.autorest.fluent.util.FluentUtils;
import com.azure.autorest.mapper.IMapper;
import com.azure.autorest.mapper.Mappers;
import com.azure.autorest.model.clientmodel.ClientModel;

public class FluentResourceModelMapper implements IMapper<ObjectSchema, FluentResourceModel> {

    private static final FluentResourceModelMapper INSTANCE = new FluentResourceModelMapper();

    public static FluentResourceModelMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public FluentResourceModel map(ObjectSchema objectSchema) {
        FluentResourceModel fluentResourceModel = null;

        ClientModel clientModel = Mappers.getModelMapper().map(objectSchema);
        if (clientModel != null && FluentUtils.isInnerClassType(clientModel.getPackage(), clientModel.getName())) {
            fluentResourceModel = new FluentResourceModel(clientModel);
        }

        return fluentResourceModel;
    }
}
