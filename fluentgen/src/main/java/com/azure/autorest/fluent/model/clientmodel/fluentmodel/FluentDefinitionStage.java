/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.fluentmodel;

import com.azure.autorest.model.clientmodel.ClientModelProperty;

public class FluentDefinitionStage {
    private final String name;
    private final ClientModelProperty property;
    private FluentDefinitionStage nextStage;
    private String extendStages;

    public FluentDefinitionStage(String name, ClientModelProperty property) {
        this.name = name;
        this.property = property;
    }

    public String getName() {
        return name;
    }

    public ClientModelProperty getProperty() {
        return property;
    }

    public FluentDefinitionStage getNextStage() {
        return nextStage;
    }

    public void setNextStage(FluentDefinitionStage nextStage) {
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
}
