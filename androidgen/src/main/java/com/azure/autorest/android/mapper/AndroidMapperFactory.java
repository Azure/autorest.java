package com.azure.autorest.android.mapper;

import com.azure.autorest.mapper.ClientMethodMapper;
import com.azure.autorest.mapper.DefaultMapperFactory;
import com.azure.autorest.mapper.ModelMapper;

public class AndroidMapperFactory extends DefaultMapperFactory {

    @Override
    public ModelMapper getModelMapper() {
        return AndroidModelMapper.getInstance();
    }

    @Override
    public ClientMethodMapper getClientMethodMapper() { return AndroidClientMethodMapper.getInstance();}
}