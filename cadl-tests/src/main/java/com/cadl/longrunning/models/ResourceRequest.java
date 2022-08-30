// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.cadl.longrunning.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The template for adding optional properties. */
@Fluent
public final class ResourceRequest {
    /*
     * The type property.
     */
    @JsonProperty(value = "type")
    private String type;

    /**
     * Get the type property: The type property.
     *
     * @return the type value.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Set the type property: The type property.
     *
     * @param type the type value to set.
     * @return the ResourceRequest object itself.
     */
    public ResourceRequest setType(String type) {
        this.type = type;
        return this;
    }
}
