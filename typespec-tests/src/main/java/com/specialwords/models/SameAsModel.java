// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.specialwords.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The SameAsModel model.
 */
@Immutable
public final class SameAsModel {
    /*
     * The SameAsModel property.
     */
    @Generated
    @JsonProperty(value = "SameAsModel")
    private String sameAsModel;

    /**
     * Creates an instance of SameAsModel class.
     * 
     * @param sameAsModel the sameAsModel value to set.
     */
    @Generated
    @JsonCreator
    public SameAsModel(@JsonProperty(value = "SameAsModel") String sameAsModel) {
        this.sameAsModel = sameAsModel;
    }

    /**
     * Get the sameAsModel property: The SameAsModel property.
     * 
     * @return the sameAsModel value.
     */
    @Generated
    public String getSameAsModel() {
        return this.sameAsModel;
    }
}
