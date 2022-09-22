// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.models.property.optional.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/** Model with collection bytes properties. */
@Fluent
public final class CollectionsByteProperty {
    /*
     * Property
     */
    @JsonProperty(value = "property")
    private List<byte[]> property;

    /** Creates an instance of CollectionsByteProperty class. */
    public CollectionsByteProperty() {}

    /**
     * Get the property property: Property.
     *
     * @return the property value.
     */
    public List<byte[]> getProperty() {
        return this.property;
    }

    /**
     * Set the property property: Property.
     *
     * @param property the property value to set.
     * @return the CollectionsByteProperty object itself.
     */
    public CollectionsByteProperty setProperty(List<byte[]> property) {
        this.property = property;
        return this;
    }
}
