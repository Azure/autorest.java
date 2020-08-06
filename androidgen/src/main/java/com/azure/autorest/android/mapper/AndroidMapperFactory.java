package com.azure.autorest.android.mapper;

import com.azure.autorest.mapper.*;

public class AndroidMapperFactory extends DefaultMapperFactory {

    @Override
    public ModelMapper getModelMapper() {
        return AndroidModelMapper.getInstance();
    }

    @Override
    public ClientMethodMapper getClientMethodMapper() {
        return AndroidClientMethodMapper.getInstance();
    }

    @Override
    public ServiceClientMapper getServiceClientMapper() {
        return AndroidServiceClientMapper.getInstance();
    }

    @Override
    public ProxyMethodMapper getProxyMethodMapper() {
        return AndroidProxyMethodMapper.getInstance();
    }
}