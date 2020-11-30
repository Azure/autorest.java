/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.fluent.model.clientmodel.immutablemodel.CollectionMethodTemplate;
import com.azure.autorest.fluent.model.clientmodel.immutablemodel.CollectionMethodTypeConversionTemplate;
import com.azure.autorest.fluent.model.clientmodel.immutablemodel.ImmutableMethod;
import com.azure.autorest.fluent.util.FluentUtils;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ProxyMethod;
import com.azure.autorest.template.prototype.MethodTemplate;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FluentCollectionMethod {

    private final ClientMethod method;

    private final IType fluentReturnType;

    private final ImmutableMethod immutableMethod;

    public FluentCollectionMethod(ClientMethod method) {
        this.method = method;
        this.fluentReturnType = FluentUtils.getFluentWrapperType(method.getReturnValue().getType());

        this.immutableMethod = this.fluentReturnType == method.getReturnValue().getType()
                ? new CollectionMethodTemplate(this, method.getReturnValue().getType())
                : new CollectionMethodTypeConversionTemplate(this, method.getReturnValue().getType());
    }

    public IType getFluentReturnType() {
        return fluentReturnType;
    }

    // method signature
    public String getMethodSignature() {
        return String.format("%1$s %2$s(%3$s)", this.getFluentReturnType(), method.getName(), method.getParametersDeclaration());
    }

    // method invocation
    public String getMethodInvocation() {
        List<ClientMethodParameter> methodParameters = method.getOnlyRequiredParameters() ? method.getMethodRequiredParameters() : method.getMethodParameters();
        String argumentsLine = methodParameters.stream().map(ClientMethodParameter::getName).collect(Collectors.joining(", "));
        return String.format("%1$s(%2$s)", method.getName(), argumentsLine);
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

    public MethodTemplate getImplementationMethodTemplate() {
        return immutableMethod.getMethodTemplate();
    }

    public void addImportsTo(Set<String> imports, boolean includeImplementationImports) {
        this.getFluentReturnType().addImportsTo(imports, false);
        method.addImportsTo(imports, includeImplementationImports, JavaSettings.getInstance());

        if (includeImplementationImports) {
            immutableMethod.getMethodTemplate().addImportsTo(imports);
        }
    }
}
