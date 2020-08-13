package com.azure.autorest.android.mapper;

import com.azure.autorest.android.model.AndroidProxyMethod;
import com.azure.autorest.mapper.ProxyMethodMapper;
import com.azure.autorest.model.clientmodel.ProxyMethod;

public class AndroidProxyMethodMapper extends ProxyMethodMapper {
    private static AndroidProxyMethodMapper instance = new AndroidProxyMethodMapper();

    public static AndroidProxyMethodMapper getInstance() {
        return instance;
    }

    @Override
    protected ProxyMethod.Builder createProxyMethodBuilder() {
        return new AndroidProxyMethod.Builder();
    }
}
