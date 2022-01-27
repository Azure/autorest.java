// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.android.model.clientmodel;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.Proxy;
import com.azure.autorest.model.clientmodel.ProxyMethod;

import java.util.List;
import java.util.Set;

public class AndroidProxy extends Proxy {
    protected AndroidProxy(String name, String clientTypeName, String baseURL, List<ProxyMethod> methods) {
        super(name,
                clientTypeName,
                baseURL,
                methods);
    }

    @Override
    public void addImportsTo(Set<String> imports, boolean includeImplementationImports, JavaSettings settings) {
        if (includeImplementationImports) {
            imports.add("com.azure.android.core.rest.annotation.Host");
            imports.add("com.azure.android.core.rest.annotation.ServiceInterface");
        }

        for (ProxyMethod method : getMethods()) {
            method.addImportsTo(imports, includeImplementationImports, settings);
        }
    }

    public static final class Builder extends Proxy.Builder {
        @Override
        public Proxy build() {
            return new AndroidProxy(name,
                    clientTypeName,
                    baseURL,
                    methods);
        }
    }
}
