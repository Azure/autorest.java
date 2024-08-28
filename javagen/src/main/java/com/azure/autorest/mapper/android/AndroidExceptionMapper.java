// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.mapper.android;

import com.azure.autorest.mapper.ExceptionMapper;
import com.azure.autorest.model.clientmodel.ClientException;
import com.azure.autorest.model.clientmodel.android.AndroidClientException;

public class AndroidExceptionMapper extends ExceptionMapper {
    private static final ExceptionMapper INSTANCE = new AndroidExceptionMapper();

    protected AndroidExceptionMapper() {
    }

    public static ExceptionMapper getInstance() {
        return INSTANCE;
    }

    @Override
    protected ClientException.Builder createClientExceptionBuilder() {
        return new AndroidClientException.Builder();
    }
}
