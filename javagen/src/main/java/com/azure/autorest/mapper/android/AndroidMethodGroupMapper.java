// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.mapper.android;

import com.azure.autorest.mapper.MethodGroupMapper;
import com.azure.autorest.model.clientmodel.MethodGroupClient;
import com.azure.autorest.model.clientmodel.Proxy;
import com.azure.autorest.model.clientmodel.android.AndroidMethodGroupClient;
import com.azure.autorest.model.clientmodel.android.AndroidProxy;

public class AndroidMethodGroupMapper extends MethodGroupMapper {
    private static final MethodGroupMapper INSTANCE = new AndroidMethodGroupMapper();

    protected AndroidMethodGroupMapper() {
    }

    public static MethodGroupMapper getInstance() {
        return INSTANCE;
    }

    @Override
    protected Proxy.Builder createProxyBuilder() {
        return new AndroidProxy.Builder();
    }

    @Override
    protected MethodGroupClient.Builder createMethodGroupClientBuilder() {
        return new AndroidMethodGroupClient.Builder();
    }
}
