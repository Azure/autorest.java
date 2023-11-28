// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.android.model.clientmodel;

import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientException;
import com.azure.autorest.model.clientmodel.IType;

public class AndroidClientException extends ClientException {
    protected AndroidClientException(String packageKeyword, String name, String errorName, IType parentType) {
        super(packageKeyword,
                name,
                errorName,
                parentType);
    }

    public static class Builder extends ClientException.Builder{

        @Override
        public ClientException build() {
            if (parentType.equals(ClassType.HTTP_RESPONSE_EXCEPTION)) {
                parentType = ClassType.ANDROID_HTTP_RESPONSE_EXCEPTION;
            }
            return new AndroidClientException(packageName,
                    name,
                    errorName,
                    parentType);
        }
    }
}
