/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.fluentmodel.create;

import com.azure.autorest.fluent.model.clientmodel.fluentmodel.FluentInterfaceStage;
import com.azure.autorest.model.clientmodel.ClientModelProperty;

public class DefinitionStage extends FluentInterfaceStage {

    public DefinitionStage(String name, ClientModelProperty property) {
        super(name, property);
    }

    public String getDescription(String modelName) {
        return property == null
                ? String.format("The stage of the %1$s definition.", modelName)
                : String.format("The stage of the %1$s definition allowing to specify %2$s.", modelName, property.getName());
    }
}
