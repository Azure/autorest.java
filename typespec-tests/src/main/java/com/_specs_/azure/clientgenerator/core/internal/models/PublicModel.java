// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com._specs_.azure.clientgenerator.core.internal.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/** This is a model only used by public operation. It should be generated and exported. */
@Immutable
public final class PublicModel {
    /*
     * The name property.
     */
    @Generated
    @JsonProperty(value = "name")
    private String name;

    /**
     * Creates an instance of PublicModel class.
     *
     * @param name the name value to set.
     */
    @Generated
    @JsonCreator
    private PublicModel(@JsonProperty(value = "name") String name) {
        this.name = name;
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
}
