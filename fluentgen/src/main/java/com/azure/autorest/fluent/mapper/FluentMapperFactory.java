/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.mapper;

import com.azure.autorest.mapper.DefaultMapperFactory;
import com.azure.autorest.mapper.MethodGroupMapper;
import com.azure.autorest.mapper.ObjectMapper;
import com.azure.autorest.mapper.ProxyMethodMapper;

public class FluentMapperFactory extends DefaultMapperFactory {

    @Override
    public ObjectMapper getObjectMapper() {
        return FluentObjectMapper.getInstance();
    }

    @Override
    public MethodGroupMapper getMethodGroupMapper() {
        return FluentMethodGroupMapper.getInstance();
    }

    @Override
    public ProxyMethodMapper getProxyMethodMapper() {
        return FluentProxyMethodMapper.getInstance();
    }
}
