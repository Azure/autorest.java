// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.model.usage.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Record used in operation parameters.
 */
@Immutable
public final class InputRecord {
    /*
     * The requiredProp property.
     */
    @Generated
    @JsonProperty(value = "requiredProp")
    private String requiredProp;

    /**
     * Creates an instance of InputRecord class.
     * 
     * @param requiredProp the requiredProp value to set.
     */
    @Generated
    @JsonCreator
    public InputRecord(@JsonProperty(value = "requiredProp") String requiredProp) {
        this.requiredProp = requiredProp;
    }

    /**
     * Get the requiredProp property: The requiredProp property.
     * 
     * @return the requiredProp value.
     */
    @Generated
    public String getRequiredProp() {
        return this.requiredProp;
    }
}
