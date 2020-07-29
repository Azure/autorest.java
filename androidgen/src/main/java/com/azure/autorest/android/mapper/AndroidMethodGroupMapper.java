package com.azure.autorest.android.mapper;

import com.azure.autorest.android.model.AndroidMethodGroupClient;
import com.azure.autorest.mapper.MethodGroupMapper;
import com.azure.autorest.model.clientmodel.MethodGroupClient;

public class AndroidMethodGroupMapper extends MethodGroupMapper {
    private static AndroidMethodGroupMapper instance = new AndroidMethodGroupMapper();

    public static AndroidMethodGroupMapper getInstance() {
        return instance;
    }

    protected MethodGroupClient.Builder createClientMethodBuilder() {
        return new AndroidMethodGroupClient.Builder();
    }
}
