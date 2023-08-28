// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.type.property.valuetypes.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.core.util.CoreUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/** Model with a bytes property. */
@Immutable
public final class BytesProperty {
    /*
     * Property
     */
    @Generated
    @JsonProperty(value = "property")
    private byte[] property;

    /**
     * Creates an instance of BytesProperty class.
     *
     * @param property the property value to set.
     */
    @Generated
    @JsonCreator
    public BytesProperty(@JsonProperty(value = "property") byte[] property) {
        this.property = property;
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
}
