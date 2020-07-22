package com.azure.autorest.mapper;

import com.azure.autorest.mapper.DefaultMapperFactory;
import com.azure.autorest.mapper.AndroidModelMapper;

public class AndroidMapperFactory extends DefaultMapperFactory {

    @Override
    public ModelMapper getModelMapper() {
        return AndroidModelMapper.getInstance();
    }
    
}