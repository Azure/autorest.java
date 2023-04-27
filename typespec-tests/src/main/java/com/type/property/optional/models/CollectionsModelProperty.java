// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.type.property.optional.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/** Model with collection models properties. */
@Fluent
public final class CollectionsModelProperty {
    /*
     * Property
     */
    @Generated
    @JsonProperty(value = "property")
    private List<StringProperty> property;

    /** Creates an instance of CollectionsModelProperty class. */
    @Generated
    public CollectionsModelProperty() {}

    /**
     * Get the property property: Property.
     *
     * @return the property value.
     */
    @Generated
    public List<StringProperty> getProperty() {
        return this.property;
    }

    /**
     * Set the property property: Property.
     *
     * @param property the property value to set.
     * @return the CollectionsModelProperty object itself.
     */
    @Generated
    public CollectionsModelProperty setProperty(List<StringProperty> property) {
        this.property = property;
        return this;
    }
}
