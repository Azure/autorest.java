/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.examplemodel;

import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.IType;

import java.util.HashMap;
import java.util.Map;

public class ClientModelNode extends ExampleNode {

    private ClientModel model;
    private final Map<ExampleNode, ClientModelProperty> modelProperties = new HashMap<>();

    public ClientModelNode(IType clientType, Object objectValue) {
        super(clientType, objectValue);
    }

    public ClientModel getClientModel() {
        return model;
    }

    public ClientModelNode setClientModel(ClientModel model) {
        this.model = model;
        return this;
    }

    public Map<ExampleNode, ClientModelProperty> getClientModelProperties() {
        return modelProperties;
    }
}
