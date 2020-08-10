package com.azure.autorest.android.mapper;

import com.azure.autorest.android.model.clientmodel.AndroidServiceClient;
import com.azure.autorest.mapper.ServiceClientMapper;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.ServiceClient;

import java.util.ArrayList;

public class AndroidServiceClientMapper extends ServiceClientMapper {
    private static final AndroidServiceClientMapper instance = new AndroidServiceClientMapper();

    public static AndroidServiceClientMapper getInstance(){
        return instance;
    }

    @Override
    protected ServiceClient.Builder createClientBuilder() {
        return new AndroidServiceClient.Builder();
    }

    @Override
    protected ClientMethodParameter createConstructorParameterForHttp() {
        return new ClientMethodParameter.Builder()
                .description("The HTTP Client to send requests through")
                .isFinal(false)
                .wireType(new AdHocType("com.azure.android.core.http", "ServiceClient"))
                .name("serviceClient")
                .isRequired(true)
                .isConstant(false)
                .fromClient(false)
                .defaultValue(null)
                .annotations(new ArrayList<>())
                .build();
    }
}
