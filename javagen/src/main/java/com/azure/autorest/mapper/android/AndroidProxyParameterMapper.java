// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.mapper.android;

import com.azure.autorest.mapper.ProxyParameterMapper;
import com.azure.autorest.model.clientmodel.ProxyMethodParameter;
import com.azure.autorest.model.clientmodel.android.AndroidProxyMethodParameter;

public class AndroidProxyParameterMapper extends ProxyParameterMapper {
    private static final ProxyParameterMapper INSTANCE = new AndroidProxyParameterMapper();

    protected AndroidProxyParameterMapper() {
    }

    public static ProxyParameterMapper getInstance() {
        return INSTANCE;
    }

    @Override
    protected ProxyMethodParameter.Builder createProxyMethodParameterBuilder() {
        return new AndroidProxyMethodParameter.Builder();
    }
}
