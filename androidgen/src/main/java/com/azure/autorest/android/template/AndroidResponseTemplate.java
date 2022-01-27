// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.android.template;

import com.azure.autorest.model.clientmodel.ClientResponse;
import com.azure.autorest.model.clientmodel.GenericType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.template.ResponseTemplate;

public class AndroidResponseTemplate extends ResponseTemplate {
    private static AndroidResponseTemplate _instance = new AndroidResponseTemplate();

    protected AndroidResponseTemplate() {
    }

    public static ResponseTemplate getInstance() {
        return _instance;
    }

    @Override
    protected IType getRestResponseType(ClientResponse response) {
        return new GenericType("com.azure.android.core.rest", "ResponseBase", response.getHeadersType(), response.getBodyType());
    }

    @Override
    protected void addRequestAndHeaderImports(java.util.Set<String> imports) {
        imports.add("com.azure.android.core.http.HttpRequest");
        imports.add("com.azure.android.core.http.HttpHeaders");
    }
}
