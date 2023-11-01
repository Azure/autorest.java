// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.visibility.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The RoundTripModel model.
 */
@Immutable
public final class RoundTripModel {
    /*
     * The name property.
     */
    @Generated
    @JsonProperty(value = "name")
    private String name;

    /*
     * The secretName property.
     */
    @Generated
    @JsonProperty(value = "secretName")
    private String secretName;

    /**
     * Creates an instance of RoundTripModel class.
     * 
     * @param name the name value to set.
     * @param secretName the secretName value to set.
     */
    @Generated
    @JsonCreator
    public RoundTripModel(@JsonProperty(value = "name") String name,
        @JsonProperty(value = "secretName") String secretName) {
        this.name = name;
        this.secretName = secretName;
    }

    /**
     * Get the name property: The name property.
     * 
     * @return the name value.
     */
    @Generated
    public String getName() {
        return this.name;
    }

    /**
     * Get the secretName property: The secretName property.
     * 
     * @return the secretName value.
     */
    @Generated
    public String getSecretName() {
        return this.secretName;
    }
}
