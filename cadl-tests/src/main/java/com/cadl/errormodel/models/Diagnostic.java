// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.cadl.errormodel.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.models.ResponseError;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The Diagnostic model. */
@Fluent
public final class Diagnostic {
    /*
     * The name property.
     */
    @JsonProperty(value = "name", required = true)
    private String name;

    /*
     * The error property.
     */
    @JsonProperty(value = "error", required = true)
    private ResponseError error;

    /**
     * Creates an instance of Diagnostic class.
     *
     * @param name the name value to set.
     * @param error the error value to set.
     */
    @JsonCreator
    public Diagnostic(
            @JsonProperty(value = "name", required = true) String name,
            @JsonProperty(value = "error", required = true) ResponseError error) {
        this.name = name;
        this.error = error;
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
     * Get the error property: The error property.
     *
     * @return the error value.
     */
    public ResponseError getError() {
        return this.error;
    }
}
