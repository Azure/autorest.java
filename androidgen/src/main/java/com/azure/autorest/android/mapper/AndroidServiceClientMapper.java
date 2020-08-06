package com.azure.autorest.android.mapper;

import com.azure.autorest.android.model.clientmodel.AndroidServiceClient;
import com.azure.autorest.mapper.ServiceClientMapper;
import com.azure.autorest.model.clientmodel.ServiceClient;

public class AndroidServiceClientMapper extends ServiceClientMapper {
    private static final AndroidServiceClientMapper instance = new AndroidServiceClientMapper();

    public static AndroidServiceClientMapper getInstance(){
        return instance;
    }

    @Override
    protected ServiceClient.Builder createClientBuilder() {
        return new AndroidServiceClient.Builder();
    }
}
