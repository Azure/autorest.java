// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.mapper.android;

import com.azure.autorest.mapper.ModelMapper;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.android.AndroidClientModel;

public class AndroidModelMapper extends ModelMapper {
    private static final ModelMapper INSTANCE = new AndroidModelMapper();

    protected AndroidModelMapper() {
    }

    public static ModelMapper getInstance() {
        return INSTANCE;
    }

    @Override
    protected ClientModel.Builder createModelBuilder() {
        return new AndroidClientModel.Builder();
    }
}
