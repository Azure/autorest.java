// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.naming.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The RequestParameters model.
 */
@Immutable
public final class RequestParameters {
    /*
     * The type property.
     */
    @Generated
    @JsonProperty(value = "type")
    private RequestType type;

    /**
     * Creates an instance of RequestParameters class.
     * 
     * @param type the type value to set.
     */
    @Generated
    @JsonCreator
    public RequestParameters(@JsonProperty(value = "type") RequestType type) {
        this.type = type;
    }

    /**
     * Get the type property: The type property.
     * 
     * @return the type value.
     */
    @Generated
    public RequestType getType() {
        return this.type;
    }
}
