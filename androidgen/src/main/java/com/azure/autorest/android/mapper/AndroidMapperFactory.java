package com.azure.autorest.android.mapper;

import com.azure.autorest.mapper.ClientMethodMapper;
import com.azure.autorest.mapper.DefaultMapperFactory;
import com.azure.autorest.mapper.ExceptionMapper;
import com.azure.autorest.mapper.MethodGroupMapper;
import com.azure.autorest.mapper.ModelMapper;
import com.azure.autorest.mapper.ProxyMethodMapper;
import com.azure.autorest.mapper.ProxyParameterMapper;
import com.azure.autorest.mapper.ServiceClientMapper;

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

    @Override
    public ServiceClientMapper getServiceClientMapper() {
        return AndroidServiceClientMapper.getInstance();
    }

    @Override
    public ModelMapper getModelMapper() {
        return AndroidModelMapper.getInstance();
    }

    @Override
    public ExceptionMapper getExceptionMapper() {
        return AndroidExceptionMapper.getInstance();
    }
}
