// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.specialheaders.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Resource model.
 */
@Fluent
public final class Resource {
    /*
     * The id property.
     */
    @Generated
    @JsonProperty(value = "id", access = JsonProperty.Access.WRITE_ONLY)
    private String id;

    /*
     * The name property.
     */
    @Generated
    @JsonProperty(value = "name", access = JsonProperty.Access.WRITE_ONLY)
    private String name;

    /*
     * The description property.
     */
    @Generated
    @JsonProperty(value = "description")
    private String description;

    /*
     * The type property.
     */
    @Generated
    @JsonProperty(value = "type")
    private String type;

    /**
     * Creates an instance of Resource class.
     * 
     * @param type the type value to set.
     */
    @Generated
    @JsonCreator
    public Resource(@JsonProperty(value = "type") String type) {
        this.type = type;
    }

    /**
     * Get the id property: The id property.
     * 
     * @return the id value.
     */
    @Generated
    public String getId() {
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

    /**
     * Get the description property: The description property.
     * 
     * @return the description value.
     */
    @Generated
    public String getDescription() {
        return this.description;
    }

    /**
     * Set the description property: The description property.
     * 
     * @param description the description value to set.
     * @return the Resource object itself.
     */
    @Generated
    public Resource setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Get the type property: The type property.
     * 
     * @return the type value.
     */
    @Generated
    public String getType() {
        return this.type;
    }
}
