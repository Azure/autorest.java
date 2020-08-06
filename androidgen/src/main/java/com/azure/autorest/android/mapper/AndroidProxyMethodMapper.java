package com.azure.autorest.android.mapper;

import com.azure.autorest.android.model.clientmodel.AndroidProxyMethod;
import com.azure.autorest.mapper.ProxyMethodMapper;
import com.azure.autorest.model.clientmodel.GenericType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ProxyMethod;

public class AndroidProxyMethodMapper extends ProxyMethodMapper {
    private static AndroidProxyMethodMapper instance = new AndroidProxyMethodMapper();

    public static AndroidProxyMethodMapper getInstance() {
        return instance;
    }

    @Override
    protected ProxyMethod.Builder createProxyMethodBuilder() {
        return new AndroidProxyMethod.Builder();
    }

    @Override
    protected GenericType createResponseType(IType nestedType ) {
        return new GenericType("com.azure.android.core.http", "Response", nestedType);
    }
}
