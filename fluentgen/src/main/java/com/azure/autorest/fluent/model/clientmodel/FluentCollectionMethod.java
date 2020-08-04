/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.fluent.util.FluentUtils;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ProxyMethod;

import java.util.Set;

public class FluentCollectionMethod {

    private final ClientMethod method;

    private final IType fluentReturnType;

    public FluentCollectionMethod(ClientMethod method) {
        this.method = method;
        this.fluentReturnType = FluentUtils.getFluentWrapperType(method.getReturnValue().getType());
    }

    public IType getFluentReturnType() {
        return fluentReturnType;
    }

    public String getMethodSignature() {
        return String.format("%1$s %2$s(%3$s)", this.getFluentReturnType(), method.getName(), method.getParametersDeclaration());
    }

    public String getDescription() {
        return method.getDescription();
    }

    public ClientMethod getInnerClientMethod() {
        return method;
    }

    public ProxyMethod getInnerProxyMethod() {
        return method.getProxyMethod();
    }

    public void addImportsTo(Set<String> imports, boolean includeImplementationImports) {
        this.getFluentReturnType().addImportsTo(imports, false);
        method.addImportsTo(imports, includeImplementationImports, JavaSettings.getInstance());
    }
}
