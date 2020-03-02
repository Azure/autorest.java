/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 *
 */

package com.azure.autorest.fluent.template;

import com.azure.autorest.model.clientmodel.ProxyMethod;
import com.azure.autorest.model.javamodel.JavaInterface;
import com.azure.autorest.template.ProxyTemplate;

public class FluentProxyTemplate extends ProxyTemplate {

    private static FluentProxyTemplate _instance = new FluentProxyTemplate();

    public static FluentProxyTemplate getInstance() {
        return _instance;
    }

    @Override
    protected void writeProxyMethodHeaders(ProxyMethod restAPIMethod, JavaInterface interfaceBlock) {
        interfaceBlock.annotation(String.format("Headers({ \"Content-Type: %s\", \"Accept: %s\" })",
                restAPIMethod.getRequestContentType(),
                String.join(",", restAPIMethod.getResponseContentTypes())));
    }
}
