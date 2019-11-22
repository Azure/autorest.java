package com.azure.autorest.model.clientmodel;

import java.util.List;
import java.util.Set;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


/**
 * The constructor in a ServiceClient.
 */
public class Constructor {
    /**
     * The parameters of this constructor.
     */
    private List<ClientMethodParameter> parameters;

    public Constructor(List<ClientMethodParameter> parameters) {
        this.parameters = parameters;
    }

    public final List<ClientMethodParameter> getParameters() {
        return parameters;
    }

    public final void addImportsTo(Set<String> imports, boolean includeImplementationImports) {
        for (ClientMethodParameter parameter : getParameters()) {
            parameter.addImportsTo(imports, includeImplementationImports);
        }
    }
}