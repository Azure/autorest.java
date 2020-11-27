package com.azure.autorest.android.mapper;

import com.azure.autorest.android.model.AndroidClientModelProperty;
import com.azure.autorest.mapper.ModelPropertyMapper;
import com.azure.autorest.model.clientmodel.ClientModelProperty;

public class AndroidModelPropertyMapper extends ModelPropertyMapper {

    private static AndroidModelPropertyMapper instance = new AndroidModelPropertyMapper();

    public static AndroidModelPropertyMapper getInstance() {
        return instance;
    }

    @Override
    protected ClientModelProperty.Builder getModelPropertyBuilder() {
        return new AndroidClientModelProperty.Builder();
    }
}
