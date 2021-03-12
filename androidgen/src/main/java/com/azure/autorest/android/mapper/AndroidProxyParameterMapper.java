package com.azure.autorest.android.mapper;

import com.azure.autorest.android.model.clientmodel.AndroidProxyMethodParameter;
import com.azure.autorest.mapper.ProxyParameterMapper;
import com.azure.autorest.model.clientmodel.ProxyMethodParameter;

public class AndroidProxyParameterMapper extends ProxyParameterMapper {
    private static ProxyParameterMapper instance = new AndroidProxyParameterMapper();

    protected AndroidProxyParameterMapper() {
    }

    public static ProxyParameterMapper getInstance() {
        return instance;
    }

    @Override
    protected ProxyMethodParameter.Builder createProxyMethodParameterBuilder() {
        return new AndroidProxyMethodParameter.Builder();
    }
}
