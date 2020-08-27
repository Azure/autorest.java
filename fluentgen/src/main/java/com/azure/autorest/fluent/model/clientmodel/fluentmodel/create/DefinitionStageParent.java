/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.fluentmodel.create;

import com.azure.autorest.model.clientmodel.ClientMethod;

import java.util.Arrays;
import java.util.List;

public class DefinitionStageParent extends DefinitionStage {

    private ClientMethod existingParentMethod;

    public DefinitionStageParent(String name) {
        super(name, null);
    }

    @Override
    public String getDescription(String modelName) {
        return String.format("The stage of the %1$s definition allowing to specify parent resource.", modelName);
    }

    @Override
    public List<ClientMethod> getMethods() {
        return Arrays.asList(existingParentMethod);
    }

    public void setExistingParentMethod(ClientMethod existingParentMethod) {
        this.existingParentMethod = existingParentMethod;
    }
}
