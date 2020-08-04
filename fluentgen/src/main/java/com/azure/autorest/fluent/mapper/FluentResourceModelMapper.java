/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.mapper;

import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.fluent.model.arm.ResourceClientModel;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceModel;
import com.azure.autorest.fluent.util.FluentUtils;
import com.azure.autorest.mapper.IMapper;
import com.azure.autorest.mapper.Mappers;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.core.util.CoreUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
                ClientModel parentModel = null;
                for (ClientModel model : FluentMapper.getClient().getModels()) {
                    if (parentModelName.equals(model.getName())) {
                        parentModel = model;
                        break;
                    }
                }

                if (parentModel == null) {
                    Optional<ClientModel> modelOpt = ResourceClientModel.getResourceClientModel(parentModelName);
                    if (modelOpt.isPresent()) {
                        parentModel = modelOpt.get();
                    }
                }

                if (parentModel != null) {
                    parentModels.add(parentModel);
                }
                parentModelName = parentModel == null ? null :parentModel.getParentModelName();
            }

            fluentResourceModel = new FluentResourceModel(clientModel, parentModels);
        }

        return fluentResourceModel;
    }
}
