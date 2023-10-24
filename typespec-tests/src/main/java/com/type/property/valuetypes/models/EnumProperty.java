// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.property.valuetypes.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Model with enum properties.
 */
@Immutable
public final class EnumProperty {
    /*
     * Property
     */
    @Generated
    @JsonProperty(value = "property")
    private FixedInnerEnum property;

    /**
     * Creates an instance of EnumProperty class.
     * 
     * @param property the property value to set.
     */
    @Generated
    @JsonCreator
    public EnumProperty(@JsonProperty(value = "property") FixedInnerEnum property) {
        this.property = property;
    }

    /**
     * Get the property property: Property.
     * 
     * @return the property value.
     */
    @Generated
    public FixedInnerEnum getProperty() {
        return this.property;
    }
}
