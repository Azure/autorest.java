// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.fluent.model.clientmodel.fluentmodel.method;

import com.azure.autorest.fluent.model.clientmodel.FluentResourceModel;
import com.azure.autorest.fluent.model.clientmodel.immutablemodel.ImmutableMethod;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.ReturnValue;
import com.azure.autorest.model.javamodel.JavaJavadocComment;
import com.azure.autorest.template.prototype.MethodTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public abstract class FluentMethod implements ImmutableMethod {

    protected String name;
    protected String description;
    protected ReturnValue interfaceReturnValue;
    protected ReturnValue implementationReturnValue;
    protected List<ClientMethodParameter> parameters = new ArrayList<>();

    protected FluentResourceModel fluentResourceModel;

    protected FluentMethodType type;

    protected MethodTemplate implementationMethodTemplate;

    public FluentMethod(FluentResourceModel fluentResourceModel, FluentMethodType type) {
        this.fluentResourceModel = fluentResourceModel;
        this.type = type;
    }

    public String getInterfaceMethodSignature() {
        return String.format("%1$s %2$s", this.interfaceReturnValue.getType().toString(), this.getBaseMethodSignature());
    }

    public String getImplementationMethodSignature() {
        return String.format("%1$s %2$s", this.implementationReturnValue.getType().toString(), this.getBaseMethodSignature());
    }

    protected abstract String getBaseMethodSignature();

    public abstract void writeJavadoc(JavaJavadocComment commentBlock);

    public abstract void addImportsTo(Set<String> imports, boolean includeImplementationImports);

    public MethodTemplate getMethodTemplate() {
        return implementationMethodTemplate;
    }

    public String getName() {
        return name;
    }

    public FluentMethodType getType() {
        return type;
    }

    public FluentResourceModel getFluentResourceModel() {
        return fluentResourceModel;
    }

    public List<ClientMethodParameter> getParameters() {
        return parameters;
    }
}
