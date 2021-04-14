package com.azure.autorest.android.mapper;

import com.azure.autorest.android.model.clientmodel.AndroidMethodGroupClient;
import com.azure.autorest.android.model.clientmodel.AndroidProxy;
import com.azure.autorest.mapper.MethodGroupMapper;
import com.azure.autorest.model.clientmodel.MethodGroupClient;
import com.azure.autorest.model.clientmodel.Proxy;

public class AndroidMethodGroupMapper extends MethodGroupMapper {
    private static MethodGroupMapper instance = new AndroidMethodGroupMapper();

    protected AndroidMethodGroupMapper() {
    }

    public static MethodGroupMapper getInstance() {
        return instance;
    }

    @Override
    protected Proxy.Builder createProxyBuilder() {
        return new AndroidProxy.Builder();
    }

    @Override
    protected MethodGroupClient.Builder createMethodGroupClientBuilder() {
        return new AndroidMethodGroupClient.Builder();
    }
}
