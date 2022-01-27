// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.fluent.model.clientmodel.fluentmodel.create;

public class DefinitionStageBlank extends DefinitionStage {

    public DefinitionStageBlank() {
        super("Blank");
    }

    @Override
    public String getDescription(String modelName) {
        return String.format("The first stage of the %1$s definition.", modelName);
    }
}
