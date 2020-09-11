/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.fluentmodel.method;

import com.azure.autorest.fluent.model.clientmodel.FluentResourceModel;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.ReturnValue;
import com.azure.autorest.model.javamodel.JavaJavadocComment;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public abstract class FluentMethod {

    protected String name;
    protected String description;
    protected ReturnValue interfaceReturnValue;
    protected ReturnValue implementationReturnValue;
    protected List<ClientMethodParameter> parameters = new ArrayList<>();

    protected FluentResourceModel fluentResourceModel;

    protected FluentMethodType type;

    public FluentMethod(FluentResourceModel fluentResourceModel, FluentMethodType type) {
        this.fluentResourceModel = fluentResourceModel;
        this.type = type;
    }

    public abstract String getInterfaceMethodSignature();

    public abstract void writeJavadoc(JavaJavadocComment commentBlock);

    public abstract void addImportsTo(Set<String> imports, boolean includeImplementationImports);
}
