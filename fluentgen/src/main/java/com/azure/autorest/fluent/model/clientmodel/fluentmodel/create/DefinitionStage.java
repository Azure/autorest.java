/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.fluentmodel.create;

import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientModelProperty;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class DefinitionStage {
    private final String name;
    private final ClientModelProperty property;
    private DefinitionStage nextStage;
    private String extendStages;

    public DefinitionStage(String name, ClientModelProperty property) {
        this.name = name;
        this.property = property;
    }

    public String getName() {
        return name;
    }

    public ClientModelProperty getProperty() {
        return property;
    }

    public DefinitionStage getNextStage() {
        return nextStage;
    }

    public void setNextStage(DefinitionStage nextStage) {
        this.nextStage = nextStage;
    }

    public String getExtendStages() {
        return extendStages;
    }

    public void setExtendStages(String extendStages) {
        this.extendStages = extendStages;
    }

    public String getMethodSignature() {
        return String.format("%1$s %2$s(%3$s %4$s)",
                this.getNextStage().getName(),
                this.getProperty().getSetterName(),
                this.getProperty().getClientType().toString(), this.getProperty().getName());
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
