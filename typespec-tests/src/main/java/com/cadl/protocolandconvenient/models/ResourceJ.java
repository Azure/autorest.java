// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.protocolandconvenient.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.regex.Pattern;

/**
 * The ResourceJ model.
 */
@Immutable
public final class ResourceJ {
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
     * The type property.
     */
    @Generated
    @JsonProperty(value = "type")
    private String type;

    /**
     * Creates an instance of ResourceJ class.
     * 
     * @param type the type value to set.
     */
    @Generated
    @JsonCreator
    private ResourceJ(@JsonProperty(value = "type") String type) {
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
     * Get the type property: The type property.
     * 
     * @return the type value.
     */
    @Generated
    public String getType() {
        return this.type;
    }
}
