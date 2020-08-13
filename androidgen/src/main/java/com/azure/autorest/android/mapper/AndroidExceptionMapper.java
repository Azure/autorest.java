package com.azure.autorest.android.mapper;

import com.azure.autorest.android.model.AndroidClientException;
import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.mapper.ExceptionMapper;
import com.azure.autorest.model.clientmodel.ClientException;

import java.util.HashMap;
import java.util.Map;

public class AndroidExceptionMapper extends ExceptionMapper {
    private static ExceptionMapper instance = new AndroidExceptionMapper();

    protected AndroidExceptionMapper() {
    }

    public static ExceptionMapper getInstance() {
        return instance;
    }

    protected ClientException.Builder createExceptionBuilder() {
        return new AndroidClientException.Builder();
    }
}
