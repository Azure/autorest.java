package com.azure.autorest.mapper;

import com.azure.autorest.mapper.ModelMapper;
import com.azure.autorest.model.clientmodel.AndroidClientModel;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModel.Builder;

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