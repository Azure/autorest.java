// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.ai.formrecognizer.documentanalysis.implementation.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.http.HttpHeaderName;
import com.azure.core.http.HttpHeaders;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The ComposeDocumentModelHeaders model.
 */
@Fluent
public final class ComposeDocumentModelHeaders {
    /*
     * The Operation-Location property.
     */
    @JsonProperty(value = "Operation-Location")
    private String operationLocation;

    private static final HttpHeaderName OPERATION_LOCATION = HttpHeaderName.fromString("Operation-Location");

    // HttpHeaders containing the raw property values.
    /**
     * Creates an instance of ComposeDocumentModelHeaders class.
     * 
     * @param rawHeaders The raw HttpHeaders that will be used to create the property values.
     */
    public ComposeDocumentModelHeaders(HttpHeaders rawHeaders) {
        this.operationLocation = rawHeaders.getValue(OPERATION_LOCATION);
    }

    /**
     * Get the operationLocation property: The Operation-Location property.
     * 
     * @return the operationLocation value.
     */
    public String getOperationLocation() {
        return this.operationLocation;
    }

    /**
     * Set the operationLocation property: The Operation-Location property.
     * 
     * @param operationLocation the operationLocation value to set.
     * @return the ComposeDocumentModelHeaders object itself.
     */
    public ComposeDocumentModelHeaders setOperationLocation(String operationLocation) {
        this.operationLocation = operationLocation;
        return this;
    }
}
