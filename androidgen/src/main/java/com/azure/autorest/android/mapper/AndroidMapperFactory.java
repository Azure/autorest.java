package com.azure.autorest.android.mapper;

import com.azure.autorest.mapper.ClientMethodMapper;
import com.azure.autorest.mapper.DefaultMapperFactory;
import com.azure.autorest.mapper.MethodGroupMapper;
import com.azure.autorest.mapper.ProxyMethodMapper;
import com.azure.autorest.mapper.ProxyParameterMapper;

public class AndroidMapperFactory extends DefaultMapperFactory {
    @Override
    public ClientMethodMapper getClientMethodMapper() {
        return AndroidClientMethodMapper.getInstance();
    }

    @Override
    public ProxyMethodMapper getProxyMethodMapper() {
        return AndroidProxyMethodMapper.getInstance();
    }

    @Override
    public ProxyParameterMapper getProxyParameterMapper() {
        return AndroidProxyParameterMapper.getInstance();
    }

    @Override
    public MethodGroupMapper getMethodGroupMapper() {
        return AndroidMethodGroupMapper.getInstance();
    }
}
