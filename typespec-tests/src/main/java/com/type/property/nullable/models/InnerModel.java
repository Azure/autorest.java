// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.property.nullable.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.regex.Pattern;

/**
 * Inner model used in collections model property.
 */
@Immutable
public final class InnerModel {
    /*
     * Inner model property
     */
    @Generated
    @JsonProperty(value = "property")
    private String property;

    /**
     * Creates an instance of InnerModel class.
     * 
     * @param property the property value to set.
     */
    @Generated
    @JsonCreator
    public InnerModel(@JsonProperty(value = "property") String property) {
        this.property = property;
    }

    /**
     * Get the property property: Inner model property.
     * 
     * @return the property value.
     */
    @Generated
    public String getProperty() {
        return this.property;
    }
}
