// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.typespec.mapper;

import com.azure.autorest.mapper.ClientMapper;
import com.azure.autorest.mapper.DefaultMapperFactory;
import com.azure.autorest.mapper.ModelMapper;
import com.azure.autorest.mapper.ModelPropertyMapper;
import com.azure.autorest.mapper.ObjectMapper;
import com.azure.autorest.mapper.PrimitiveMapper;

public class TypeSpecMapperFactory extends DefaultMapperFactory {

    @Override
    public ClientMapper getClientMapper() {
        return TypeSpecClientMapper.getInstance();
    }

    @Override
    public PrimitiveMapper getPrimitiveMapper() {
        return TypeSpecPrimitiveMapper.getInstance();
    }

    @Override
    public ObjectMapper getObjectMapper() {
        return TypeSpecObjectMapper.getInstance();
    }

    @Override
    public ModelPropertyMapper getModelPropertyMapper() {
        return TypeSpecModelPropertyMapper.getInstance();
    }

    @Override
    public ModelMapper getModelMapper() {
        return TypeSpecModelMapper.getInstance();
    }
}
