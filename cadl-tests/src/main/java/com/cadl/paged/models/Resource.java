// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.cadl.paged.models;

import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The Resource model. */
@Immutable
public final class Resource {
    /*
     * The id property.
     */
    @JsonProperty(value = "id", required = true, access = JsonProperty.Access.WRITE_ONLY)
    private String id;

    /*
     * The name property.
     */
    @JsonProperty(value = "name", required = true, access = JsonProperty.Access.WRITE_ONLY)
    private String name;

    /*
     * The type property.
     */
    @JsonProperty(value = "type", required = true)
    private String type;

    /**
     * Creates an instance of Resource class.
     *
     * @param type the type value to set.
     */
    @JsonCreator
    public Resource(@JsonProperty(value = "type", required = true) String type) {
        this.type = type;
    }

    /**
     * Get the id property: The id property.
     *
     * @return the id value.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Get the name property: The name property.
     *
     * @return the name value.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the type property: The type property.
     *
     * @return the type value.
     */
    public String getType() {
        return this.type;
    }
}
