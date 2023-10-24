// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.naming.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.regex.Pattern;

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
    private ParametersModel parameters;

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
    public ParametersModel getParameters() {
        return this.parameters;
    }

    /**
     * Set the parameters property: The parameters property.
     * 
     * @param parameters the parameters value to set.
     * @return the DataRequest object itself.
     */
    @Generated
    public DataRequest setParameters(ParametersModel parameters) {
        this.parameters = parameters;
        return this;
    }
}
