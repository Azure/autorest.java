/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.model.clientmodel;

public class ClientModelPropertyReference {

    /*
    Usage of the ClientModelPropertyReference
    1. reference to property (or propertyReference) from superclass, which has non-null referenceProperty, i.e., super.referenceProperty
    2. reference to property from a flattened client model (targetModel), which has non-null referenceProperty and targetProperty, i.e., targetProperty.referenceProperty
     */

    private final ClientModelProperty referenceProperty;
    private final ClientModel targetModel;
    private final ClientModelProperty targetProperty;

    private ClientModelPropertyReference(ClientModelProperty targetProperty,
                                         ClientModel targetModel,
                                         ClientModelProperty referenceProperty) {
        this.targetProperty = targetProperty;
        this.targetModel = targetModel;
        this.referenceProperty = referenceProperty;
    }

    public static ClientModelPropertyReference ofParentProperty(ClientModelProperty property) {
        return new ClientModelPropertyReference(null, null, property);
    }

    public static ClientModelPropertyReference ofParentProperty(ClientModelPropertyReference referenceProperty) {
        return new ClientModelPropertyReference(null, null, referenceProperty.getReferenceProperty());
    }

    public static ClientModelPropertyReference ofFlattenProperty(ClientModelProperty targetProperty,
                                                                 ClientModel targetModel,
                                                                 ClientModelProperty referenceProperty) {
        return new ClientModelPropertyReference(targetProperty, targetModel, referenceProperty);
    }

    public boolean isFromFlattenedProperty() {
        return this.targetProperty != null;
    }

    public boolean isFromParentModel() {
        return this.targetProperty == null;
    }

    public ClientModelProperty getReferenceProperty() {
        return referenceProperty;
    }

    public ClientModelProperty getTargetProperty() {
        return targetProperty;
    }

    public ClientModel getTargetModel() {
        return targetModel;
    }
}
