/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.model.clientmodel;

public class ClientModelPropertyReference {

    /*
    Usage of the ClientModelPropertyReference
    1. reference to property from superclass, which has non-null referenceProperty, i.e., super.referenceProperty
    2. reference to property from a flattened property, which has non-null referenceProperty and targetProperty, i.e., targetProperty.referenceProperty
     */

    private final ClientModelProperty referenceProperty;
    private final ClientModelProperty targetProperty;

    private ClientModelPropertyReference(ClientModelProperty targetProperty,
                                         ClientModelProperty referenceProperty) {
        this.targetProperty = targetProperty;
        this.referenceProperty = referenceProperty;
    }

    public static ClientModelPropertyReference referenceParentProperty(ClientModelProperty referenceProperty) {
        return new ClientModelPropertyReference(null, referenceProperty);
    }

    public static ClientModelPropertyReference referenceFlattenProperty(ClientModelProperty targetProperty,
                                                                        ClientModelProperty referenceProperty) {
        return new ClientModelPropertyReference(targetProperty, referenceProperty);
    }

    public ClientModelProperty getReferenceProperty() {
        return referenceProperty;
    }

    public ClientModelProperty getTargetProperty() {
        return targetProperty;
    }
}
