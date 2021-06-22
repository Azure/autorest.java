/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.examplemodel;

import com.azure.autorest.fluent.model.clientmodel.MethodParameter;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.IType;

import java.util.ArrayList;
import java.util.List;

public class ExampleNode {

    // the full Object at and below this node
    private final Object objectValue;

    private final IType clientType;

    // only valid when it is a ClientModel
    private ClientModel model;
    private ClientModelProperty modelProperty;

    // only valid when the node it the terminal (e.g. String, Enum)
    private String literalsValue;

    private final List<ExampleNode> childNodes = new ArrayList<>();

    public ExampleNode(IType clientType, Object objectValue) {
        this.clientType = clientType;
        this.objectValue = objectValue;
    }

    public List<ExampleNode> getChildNodes() {
        return childNodes;
    }

    public Object getObjectValue() {
        return objectValue;
    }

    public IType getClientType() {
        return clientType;
    }

    public String getLiteralsValue() {
        return literalsValue;
    }

    public ExampleNode setLiteralsValue(String literalsValue) {
        this.literalsValue = literalsValue;
        return this;
    }

    public ClientModel getClientModel() {
        return model;
    }

    public ExampleNode setClientModel(ClientModel model) {
        this.model = model;
        return this;
    }

    public ClientModelProperty getClientModelProperty() {
        return modelProperty;
    }

    public ExampleNode setClientModelProperty(ClientModelProperty modelProperty) {
        this.modelProperty = modelProperty;
        return this;
    }
}
