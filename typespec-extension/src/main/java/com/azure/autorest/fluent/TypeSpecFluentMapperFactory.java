// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.fluent;

import com.azure.autorest.fluent.mapper.FluentMapperFactory;
import com.azure.autorest.mapper.ClientMapper;
import com.azure.autorest.mapper.ModelMapper;
import com.azure.autorest.mapper.ModelPropertyMapper;
import com.azure.autorest.mapper.ObjectMapper;
import com.azure.autorest.mapper.PrimitiveMapper;
import com.azure.typespec.mapper.TypeSpecClientMapper;
import com.azure.typespec.mapper.TypeSpecModelPropertyMapper;
import com.azure.typespec.mapper.TypeSpecPrimitiveMapper;

public class TypeSpecFluentMapperFactory extends FluentMapperFactory {
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
        return TypeSpecFluentObjectMapper.getInstance();
    }

    @Override
    public ModelMapper getModelMapper() {
        return TypeSpecFluentModelMapper.getInstance();
    }

    @Override
    public ModelPropertyMapper getModelPropertyMapper() {
        return TypeSpecModelPropertyMapper.getInstance();
    }
}
