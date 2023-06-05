// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.type.property.nullable.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/** Model with collection bytes properties. */
@Immutable
public final class CollectionsByteProperty {
    /*
     * Required property
     */
    @Generated
    @JsonProperty(value = "requiredProperty", required = true)
    private String requiredProperty;

    /*
     * Property
     */
    @Generated
    @JsonProperty(value = "nullableProperty", required = true)
    private List<byte[]> nullableProperty;

    /**
     * Creates an instance of CollectionsByteProperty class.
     *
     * @param requiredProperty the requiredProperty value to set.
     * @param nullableProperty the nullableProperty value to set.
     */
    @Generated
    @JsonCreator
    public CollectionsByteProperty(
            @JsonProperty(value = "requiredProperty", required = true) String requiredProperty,
            @JsonProperty(value = "nullableProperty", required = true) List<byte[]> nullableProperty) {
        this.requiredProperty = requiredProperty;
        this.nullableProperty = nullableProperty;
    }

    /**
     * Get the requiredProperty property: Required property.
     *
     * @return the requiredProperty value.
     */
    @Generated
    public String getRequiredProperty() {
        return this.requiredProperty;
    }

    /**
     * Get the nullableProperty property: Property.
     *
     * @return the nullableProperty value.
     */
    @Generated
    public List<byte[]> getNullableProperty() {
        return this.nullableProperty;
    }
}
