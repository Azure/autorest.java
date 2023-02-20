// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.cadl.mapper;

import com.azure.autorest.mapper.ClientMapper;
import com.azure.autorest.mapper.DefaultMapperFactory;
import com.azure.autorest.mapper.PrimitiveMapper;

public class CadlMapperFactory extends DefaultMapperFactory {

    @Override
    public ClientMapper getClientMapper() {
        return CadlClientMapper.getInstance();
    }

    @Override
    public PrimitiveMapper getPrimitiveMapper() {
        return CadlPrimitiveMapper.getInstance();
    }
}
