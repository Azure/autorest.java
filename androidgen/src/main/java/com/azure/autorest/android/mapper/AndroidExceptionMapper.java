package com.azure.autorest.android.mapper;

import com.azure.autorest.android.model.clientmodel.AndroidClientException;
import com.azure.autorest.mapper.ExceptionMapper;
import com.azure.autorest.model.clientmodel.ClientException;

public class AndroidExceptionMapper extends ExceptionMapper {
    private static ExceptionMapper instance = new AndroidExceptionMapper();

    protected AndroidExceptionMapper() {
    }

    public static ExceptionMapper getInstance() {
        return instance;
    }

    @Override
    protected ClientException.Builder createClientExceptionBuilder() {
        return new AndroidClientException.Builder();
    }
}
