/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.fluentmodel;

import com.azure.autorest.model.clientmodel.ClientMethod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FluentCreateStage extends FluentDefinitionStage {

    private ClientMethod createMethod;
    private ClientMethod createMethodWithContext;

    public FluentCreateStage() {
        super("WithCreate", null);
    }

    @Override
    public String getDescription(String modelName) {
        return String.format("The stage of the %1$s definition which contains all the minimum required properties for the resource to be created, but also allows for any other optional properties to be specified.", modelName);
    }

    @Override
    public List<ClientMethod> getMethods() {
        List<ClientMethod> clientMethods = new ArrayList<>();
        clientMethods.add(createMethod);
        if (createMethodWithContext != null) {
            clientMethods.add(createMethodWithContext);
        }
        return clientMethods;
    }

    public void setCreateMethod(ClientMethod createMethod) {
        this.createMethod = createMethod;
    }

    public void setCreateMethodWithContext(ClientMethod createMethodWithContext) {
        this.createMethodWithContext = createMethodWithContext;
    }
}
