// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.versioning.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The ExportedResource model.
 */
@Immutable
public final class ExportedResource {
    /*
     * The id property.
     */
    @Generated
    @JsonProperty(value = "id")
    private String id;

    /*
     * The resourceUri property.
     */
    @Generated
    @JsonProperty(value = "resourceUri")
    private String resourceUri;

    /**
     * Creates an instance of ExportedResource class.
     * 
     * @param id the id value to set.
     * @param resourceUri the resourceUri value to set.
     */
    @Generated
    @JsonCreator
    private ExportedResource(@JsonProperty(value = "id") String id,
        @JsonProperty(value = "resourceUri") String resourceUri) {
        this.id = id;
        this.resourceUri = resourceUri;
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
     * Get the resourceUri property: The resourceUri property.
     * 
     * @return the resourceUri value.
     */
    @Generated
    public String getResourceUri() {
        return this.resourceUri;
    }
}
