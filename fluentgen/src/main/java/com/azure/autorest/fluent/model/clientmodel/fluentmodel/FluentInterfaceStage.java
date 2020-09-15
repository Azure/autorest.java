/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.fluentmodel;

import com.azure.autorest.fluent.model.clientmodel.fluentmodel.method.FluentMethod;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.ClientModelProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FluentInterfaceStage {

    protected final String name;
    protected FluentInterfaceStage nextStage;
    protected String extendStages;
    protected ClientModelProperty property;
    protected ClientMethodParameter parameter;

    protected final List<FluentMethod> methods = new ArrayList<>();

    protected FluentInterfaceStage(String name) {
        this.name = name;
    }

    protected FluentInterfaceStage(String name, ClientModelProperty property) {
        this.name = name;
        this.property = property;
    }

    protected FluentInterfaceStage(String name, ClientMethodParameter parameter) {
        this.name = name;
        this.parameter = parameter;
    }

    public String getName() {
        return name;
    }

    public boolean isMandatoryStage() {
        return (parameter == null) && (property == null || property.isRequired());
    }

    public FluentInterfaceStage getNextStage() {
        return nextStage;
    }

    public void setNextStage(FluentInterfaceStage nextStage) {
        this.nextStage = nextStage;
    }

    public String getExtendStages() {
        return extendStages;
    }

    public void setExtendStages(String extendStages) {
        this.extendStages = extendStages;
    }

    public List<FluentMethod> getMethods() {
        return methods;
    }

    public void addImportsTo(Set<String> imports, boolean includeImplementationImports) {
        this.getMethods().forEach(m -> m.addImportsTo(imports, includeImplementationImports));
    }
}
