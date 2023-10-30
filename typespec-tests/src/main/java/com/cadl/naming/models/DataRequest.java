// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.naming.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * summary of Request
 * 
 * description of Request.
 */
@Fluent
public final class DataRequest {
    /*
     * The parameters property.
     */
    @Generated
    @JsonProperty(value = "parameters")
    private RequestParameters parameters;

    /**
     * Creates an instance of DataRequest class.
     */
    @Generated
    public DataRequest() {
    }

    /**
     * Get the parameters property: The parameters property.
     * 
     * @return the parameters value.
     */
    @Generated
    public RequestParameters getParameters() {
        return this.parameters;
    }

    /**
     * Set the parameters property: The parameters property.
     * 
     * @param parameters the parameters value to set.
     * @return the DataRequest object itself.
     */
    @Generated
    public DataRequest setParameters(RequestParameters parameters) {
        this.parameters = parameters;
        return this;
    }
}
