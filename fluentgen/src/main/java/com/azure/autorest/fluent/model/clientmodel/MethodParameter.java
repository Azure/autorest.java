/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel;

import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.ProxyMethodParameter;

public class MethodParameter {

    private final ProxyMethodParameter proxyMethodParameter;
    private final ClientMethodParameter clientMethodParameter;

    public MethodParameter(ProxyMethodParameter proxyMethodParameter, ClientMethodParameter clientMethodParameter) {
        this.proxyMethodParameter = proxyMethodParameter;
        this.clientMethodParameter = clientMethodParameter;
    }

    public ProxyMethodParameter getProxyMethodParameter() {
        return proxyMethodParameter;
    }

    public ClientMethodParameter getClientMethodParameter() {
        return clientMethodParameter;
    }

    public String getSerializedName() {
        return this.getProxyMethodParameter().getRequestParameterName();
    }
}
