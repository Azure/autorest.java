// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.model.clientmodel;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The constructor in a ServiceClient.
 */
public class Constructor {

    public Constructor() {
    }

    public void setParameters(List<ClientMethodParameter> parameters) {
        this.parameters = parameters;
    }

    /**
     * The parameters of this constructor.
     */
    private List<ClientMethodParameter> parameters = Collections.emptyList();

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
