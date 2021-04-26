package com.azure.autorest.android.model.clientmodel;

import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientException;
import com.azure.autorest.model.clientmodel.IType;

public class AndroidClientException extends ClientException {
    protected AndroidClientException(String package_Keyword, String name, String errorName, IType parentType) {
        super(package_Keyword,
                name,
                errorName,
                parentType);
    }

    public static class Builder extends ClientException.Builder{

        @Override
        public ClientException build() {
            if (parentType.equals(ClassType.HttpResponseException)) {
                parentType = ClassType.AndroidHttpResponseException;
            }
            return new AndroidClientException(packageName,
                    name,
                    errorName,
                    parentType);
        }
    }
}
