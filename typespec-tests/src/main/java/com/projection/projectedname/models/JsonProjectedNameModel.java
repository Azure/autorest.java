// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.projection.projectedname.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The JsonProjectedNameModel model. */
@Immutable
public final class JsonProjectedNameModel {
    /*
     * Pass in true
     */
    @Generated
    @JsonProperty(value = "wireName")
    private boolean defaultName;

    /**
     * Creates an instance of JsonProjectedNameModel class.
     *
     * @param defaultName the defaultName value to set.
     */
    @Generated
    @JsonCreator
    public JsonProjectedNameModel(@JsonProperty(value = "wireName") boolean defaultName) {
        this.defaultName = defaultName;
    }

    /**
     * Get the defaultName property: Pass in true.
     *
     * @return the defaultName value.
     */
    @Generated
    public boolean isDefaultName() {
        return this.defaultName;
    }
}
