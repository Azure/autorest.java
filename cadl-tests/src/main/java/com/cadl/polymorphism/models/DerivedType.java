// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.cadl.polymorphism.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The DerivedType model. */
@Fluent
public final class DerivedType extends BaseType {
    /*
     * The description property.
     */
    @JsonProperty(value = "description", required = true)
    private String description;

    /**
     * Creates an instance of DerivedType class.
     *
     * @param name the name value to set.
     * @param description the description value to set.
     */
    @JsonCreator
    public DerivedType(
            @JsonProperty(value = "name", required = true) String name,
            @JsonProperty(value = "description", required = true) String description) {
        super(name);
        this.description = description;
    }

    /**
     * Get the description property: The description property.
     *
     * @return the description value.
     */
    public String getDescription() {
        return this.description;
    }
}
