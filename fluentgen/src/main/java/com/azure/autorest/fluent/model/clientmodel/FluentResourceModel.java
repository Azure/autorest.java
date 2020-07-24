/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel;

import com.azure.autorest.fluent.util.FluentUtils;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientModel;

import java.util.ArrayList;
import java.util.List;

public class FluentResourceModel {

    private final ClientModel model;

    private ModelType modelType = ModelType.WRAPPER;

    private ClassType resourceInterfaceClassType;
    private ClassType resourceImplementationClassType;

    private final List<FluentModelProperty> properties = new ArrayList<>();

    public FluentResourceModel(ClientModel model) {
        this.model = model;

        resourceInterfaceClassType = FluentUtils.resourceModelInterfaceClassType(model.getName());

        this.model.getProperties().forEach(p -> {
            properties.add(new FluentModelProperty(p));
        });
    }

    public ModelType getModelType() {
        return modelType;
    }

    public ClientModel getInnerModel() {
        return model;
    }

    public ClassType getResourceInterfaceClassType() {
        return resourceInterfaceClassType;
    }

    public ClassType getResourceImplementationClassType() {
        return resourceImplementationClassType;
    }

    public List<FluentModelProperty> getProperties() {
        return properties;
    }

    public final String getDescription() {
        return this.model.getDescription();
    }
}
