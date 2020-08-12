package com.azure.autorest.android.mapper;

import com.azure.autorest.mapper.ModelMapper;
import com.azure.autorest.android.model.AndroidClientModel;
import com.azure.autorest.model.clientmodel.ClientModel;

public class AndroidModelMapper extends ModelMapper {
    private static AndroidModelMapper instance = new AndroidModelMapper();

    public static AndroidModelMapper getInstance() {
        return instance;
    }

    @Override
    protected ClientModel.Builder createClientModelBuilder() {
        return new AndroidClientModel.Builder();
    }
}