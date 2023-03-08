// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.models.property.types.models;

import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/** Inner model. Will be a property type for ModelWithModelProperties. */
@Immutable
public final class InnerModel {
    /*
     * Required string property
     */
    @JsonProperty(value = "property", required = true)
    private String property;

    /**
     * Creates an instance of InnerModel class.
     *
     * @param property the property value to set.
     */
    @JsonCreator
    public InnerModel(@JsonProperty(value = "property", required = true) String property) {
        this.property = property;
    }

    /**
     * Get the property property: Required string property.
     *
     * @return the property value.
     */
    public String getProperty() {
        return this.property;
    }
}
