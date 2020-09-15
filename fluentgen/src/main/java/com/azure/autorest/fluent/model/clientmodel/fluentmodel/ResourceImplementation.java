/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.fluentmodel;

import com.azure.autorest.fluent.model.clientmodel.fluentmodel.method.FluentMethod;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ResourceImplementation {

    private List<FluentMethod> methods = new ArrayList<>();

    public ResourceImplementation(Collection<FluentMethod> methods) {
        this.methods.addAll(methods);
    }

    public List<FluentMethod> getMethods() {
        return methods;
    }
}
