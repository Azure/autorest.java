/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.model.clientmodel;

public class ClientModelPropertyReference {

    private final ClientModelProperty referenceProperty;

    public ClientModelPropertyReference(ClientModelProperty referenceProperty) {
        this.referenceProperty = referenceProperty;
    }

    public ClientModelProperty getReferenceProperty() {
        return referenceProperty;
    }
}
