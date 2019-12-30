package com.azure.autorest.fluent.mapper;

import com.azure.autorest.mapper.DefaultMapperFactory;
import com.azure.autorest.mapper.ObjectMapper;

public class FluentMapperFactory extends DefaultMapperFactory {

    @Override
    public ObjectMapper getObjectMapper() {
        return FluentObjectMapper.getInstance();
    }
}
