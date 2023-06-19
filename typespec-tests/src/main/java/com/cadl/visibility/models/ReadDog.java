// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.cadl.visibility.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The ReadDog model. */
@Immutable
public final class ReadDog {
    /*
     * The id property.
     */
    @Generated
    @JsonProperty(value = "id", access = JsonProperty.Access.WRITE_ONLY)
    private int id;

    /*
     * The name property.
     */
    @Generated
    @JsonProperty(value = "name")
    private String name;

    /**
     * Creates an instance of ReadDog class.
     *
     * @param name the name value to set.
     */
    @Generated
    @JsonCreator
    public ReadDog(@JsonProperty(value = "name") String name) {
        this.name = name;
    }

    /**
     * Get the id property: The id property.
     *
     * @return the id value.
     */
    @Generated
    public int getId() {
        return this.id;
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
