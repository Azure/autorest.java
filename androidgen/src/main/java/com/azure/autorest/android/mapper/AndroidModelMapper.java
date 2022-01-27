// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.android.mapper;

import com.azure.autorest.android.model.clientmodel.AndroidClientModel;
import com.azure.autorest.mapper.ModelMapper;
import com.azure.autorest.model.clientmodel.ClientModel;

public class AndroidModelMapper extends ModelMapper {
    private static ModelMapper instance = new AndroidModelMapper();

    protected AndroidModelMapper() {
    }

    public static ModelMapper getInstance() {
        return instance;
    }

    @Override
    protected ClientModel.Builder createModelBuilder() {
        return new AndroidClientModel.Builder();
    }
}
