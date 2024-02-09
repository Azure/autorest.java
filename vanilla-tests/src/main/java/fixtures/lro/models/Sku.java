// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.lro.models;

import com.azure.core.annotation.Fluent;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Sku model.
 */
@Fluent
public final class Sku {
    /*
     * The name property.
     */
    @JsonProperty(value = "name")
    private String name;

    /*
     * The id property.
     */
    @JsonProperty(value = "id")
    private String id;

    /**
     * Creates an instance of Sku class.
     */
    public Sku() {
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
     * Set the name property: The name property.
     * 
     * @param name the name value to set.
     * @return the Sku object itself.
     */
    public Sku setName(String name) {
        this.name = name;
        return this;
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
     * Set the id property: The id property.
     * 
     * @param id the id value to set.
     * @return the Sku object itself.
     */
    public Sku setId(String id) {
        this.id = id;
        return this;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
    }
}
