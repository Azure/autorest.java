// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.property.optional.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.core.util.CoreUtils;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Template type for testing models with optional property. Pass in the type of the property you are looking for.
 */
@Fluent
public final class BytesProperty {
    /*
     * Property
     */
    @Generated
    @JsonProperty(value = "property")
    private byte[] property;

    /**
     * Creates an instance of BytesProperty class.
     */
    @Generated
    public BytesProperty() {
    }

    /**
     * Get the property property: Property.
     * 
     * @return the property value.
     */
    @Generated
    public byte[] getProperty() {
        return CoreUtils.clone(this.property);
    }

    /**
     * Set the property property: Property.
     * 
     * @param property the property value to set.
     * @return the BytesProperty object itself.
     */
    @Generated
    public BytesProperty setProperty(byte[] property) {
        this.property = CoreUtils.clone(property);
        return this;
    }
}
