/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.fluentmodel;

public class FluentBlankStage extends FluentDefinitionStage {

    public FluentBlankStage() {
        super("Blank", null);
    }

    @Override
    public String getDescription(String modelName) {
        return String.format("The first stage of the %1$s definition.", modelName);
    }
}
