/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.fluentmodel;

public class FluentParentStage extends FluentDefinitionStage {

    public FluentParentStage(String name) {
        super(name, null);
    }

    @Override
    public String getDescription(String modelName) {
        return String.format("The stage of the %1$s definition allowing to specify parent resource.", modelName);
    }
}
