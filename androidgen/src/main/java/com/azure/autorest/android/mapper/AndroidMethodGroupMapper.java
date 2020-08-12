package com.azure.autorest.android.mapper;

import com.azure.autorest.android.model.AndroidMethodGroupClient;
import com.azure.autorest.android.model.AndroidProxy;
import com.azure.autorest.mapper.MethodGroupMapper;
import com.azure.autorest.model.clientmodel.MethodGroupClient;
import com.azure.autorest.model.clientmodel.Proxy;

public class AndroidMethodGroupMapper extends MethodGroupMapper {
    private static AndroidMethodGroupMapper instance = new AndroidMethodGroupMapper();

    public static AndroidMethodGroupMapper getInstance() {
        return instance;
    }

    @Override
    protected MethodGroupClient.Builder createClientMethodBuilder() {
        return new AndroidMethodGroupClient.Builder();
    }

    @Override
    protected Proxy.Builder createProxyBuilder() {
        return new AndroidProxy.Builder();
    }
}
