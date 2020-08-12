package com.azure.autorest.android.model;

import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientException;
import com.azure.autorest.model.clientmodel.IType;

public class AndroidClientException extends ClientException {
    protected AndroidClientException(String package_Keyword, String name, String errorName, IType parentType) {
        super(package_Keyword, name, errorName, parentType);
    }

    public static class Builder extends ClientException.Builder {
        public ClientException build() {
            IType parentType;
            if (super.parentType == ClassType.HttpResponseException) {
                parentType = ClassType.AndroidHttpResponseException;
            } else {
                parentType = super.parentType;
            }
            return new AndroidClientException(super.packageName,
                    super.name,
                    super.errorName,
                    parentType);
        }
    }
}
