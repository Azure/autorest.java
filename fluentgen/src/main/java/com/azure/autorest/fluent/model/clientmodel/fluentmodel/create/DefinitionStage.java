/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.fluentmodel.create;

import com.azure.autorest.fluent.model.clientmodel.fluentmodel.FluentInterfaceStage;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.method.FluentMethod;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientModelProperty;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class DefinitionStage extends FluentInterfaceStage {

    public DefinitionStage(String name, ClientModelProperty property) {
        super(name, property);
    }

    public String getDescription(String modelName) {
        return property == null
                ? String.format("The stage of the %1$s definition.", modelName)
                : String.format("The stage of the %1$s definition allowing to specify %2$s.", modelName, property.getName());
    }

    public List<ClientMethod> getMethods() {
        return Collections.emptyList();
    }

    public void addImportsTo(Set<String> imports, boolean includeImplementationImports) {
        if (property != null) {
            property.addImportsTo(imports, false);
        }
        this.getMethods().stream()
                .flatMap(m -> m.getParameters().stream())
                .forEach(p -> p.addImportsTo(imports, false));
    }
}
