package com.azure.autorest.android.mapper;

import com.azure.autorest.mapper.DefaultMapperFactory;
import com.azure.autorest.mapper.ModelMapper;
import com.azure.autorest.android.mapper.AndroidModelMapper;

public class AndroidMapperFactory extends DefaultMapperFactory {

    @Override
    public ModelMapper getModelMapper() {
        return AndroidModelMapper.getInstance();
    }
    
}