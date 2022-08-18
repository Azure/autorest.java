// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.cadl.mapper;

import com.azure.autorest.mapper.ClientMapper;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientResponse;
import com.azure.autorest.model.clientmodel.EnumType;
import com.azure.cadl.util.ModelUtil;

import java.util.List;

public class CadlClientMapper extends ClientMapper {

    private static final ClientMapper INSTANCE = new CadlClientMapper();

    public static ClientMapper getInstance() {
        return INSTANCE;
    }

    protected CadlClientMapper() {
    }

    @Override
    protected boolean hasModelsPackage(List<ClientModel> clientModels, List<EnumType> enumTypes, List<ClientResponse> responseModels) {

        return clientModels.stream().anyMatch(ModelUtil::isGeneratingModel)
                || enumTypes.stream().anyMatch(ModelUtil::isGeneratingModel)
                || responseModels.stream().anyMatch(ModelUtil::isGeneratingModel);
    }
}
