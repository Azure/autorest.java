// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.cadl.longrunning.models;

import com.azure.core.annotation.Immutable;
import com.azure.core.models.ResponseError;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/** Provides status details for long running operations. */
@Immutable
public final class ResourceOperationStatusResourceError {
    /*
     * The unique ID of the operation.
     */
    @JsonProperty(value = "id", required = true, access = JsonProperty.Access.WRITE_ONLY)
    private String id;

    /*
     * The status of the operation
     */
    @JsonProperty(value = "status", required = true)
    private OperationState status;

    /*
     * Error object that describes the error when status is "Failed".
     */
    @JsonProperty(value = "error")
    private ResponseError error;

    /**
     * Creates an instance of ResourceOperationStatusResourceError class.
     *
     * @param status the status value to set.
     */
    @JsonCreator
    private ResourceOperationStatusResourceError(
            @JsonProperty(value = "status", required = true) OperationState status) {
        this.status = status;
    }

    /**
     * Get the id property: The unique ID of the operation.
     *
     * @return the id value.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Get the status property: The status of the operation.
     *
     * @return the status value.
     */
    public OperationState getStatus() {
        return this.status;
    }

    /**
     * Get the error property: Error object that describes the error when status is "Failed".
     *
     * @return the error value.
     */
    public ResponseError getError() {
        return this.error;
    }
}
