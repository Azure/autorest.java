/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.fluentmodel;

import com.azure.autorest.fluent.model.clientmodel.fluentmodel.method.FluentMethod;
import com.azure.autorest.model.clientmodel.ClientModelProperty;

import java.util.ArrayList;
import java.util.List;

public class FluentInterfaceStage {

    protected final String name;
    protected final ClientModelProperty property;
    protected FluentInterfaceStage nextStage;
    protected String extendStages;

    protected final List<FluentMethod> methods = new ArrayList<>();

    public FluentInterfaceStage(String name, ClientModelProperty property) {
        this.name = name;
        this.property = property;
    }

    public String getName() {
        return name;
    }

    public ClientModelProperty getProperty() {
        return property;
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

    public List<FluentMethod> getMethods1() {
        return methods;
    }
}
