// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.ai.formrecognizer.documentanalysis.implementation.models;

import com.azure.core.annotation.Fluent;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Error response object.
 */
@Fluent
public final class ErrorResponse {
    /*
     * Error info.
     */
    @JsonProperty(value = "error", required = true)
    private Error error;

    /**
     * Creates an instance of ErrorResponse class.
     */
    public ErrorResponse() {
    }

    /**
     * Get the error property: Error info.
     * 
     * @return the error value.
     */
    public Error getError() {
        return this.error;
    }

    /**
     * Set the error property: Error info.
     * 
     * @param error the error value to set.
     * @return the ErrorResponse object itself.
     */
    public ErrorResponse setError(Error error) {
        this.error = error;
        return this;
    }
}
