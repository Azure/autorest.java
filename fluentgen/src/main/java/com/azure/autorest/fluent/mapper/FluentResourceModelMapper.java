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
import com.azure.core.util.CoreUtils;

import java.util.ArrayList;
import java.util.List;

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
            List<ClientModel> parentModels = new ArrayList<>();
            String parentModelName = clientModel.getParentModelName();
            while (!CoreUtils.isNullOrEmpty(parentModelName)) {
                ClientModel parentModel = FluentUtils.getClientModel(parentModelName);
                if (parentModel != null) {
                    parentModels.add(parentModel);
                }
                parentModelName = parentModel == null ? null : parentModel.getParentModelName();
            }

            fluentResourceModel = new FluentResourceModel(clientModel, parentModels);
        }

        return fluentResourceModel;
    }
}
