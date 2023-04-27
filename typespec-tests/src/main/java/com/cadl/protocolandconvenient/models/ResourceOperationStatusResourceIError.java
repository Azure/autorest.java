// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.cadl.protocolandconvenient.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.core.models.ResponseError;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/** Provides status details for long running operations. */
@Immutable
public final class ResourceOperationStatusResourceIError {
    /*
     * The unique ID of the operation.
     */
    @Generated
    @JsonProperty(value = "id", required = true, access = JsonProperty.Access.WRITE_ONLY)
    private String id;

    /*
     * The status of the operation
     */
    @Generated
    @JsonProperty(value = "status", required = true)
    private OperationState status;

    /*
     * Error object that describes the error when status is "Failed".
     */
    @Generated
    @JsonProperty(value = "error")
    private ResponseError error;

    /**
     * Creates an instance of ResourceOperationStatusResourceIError class.
     *
     * @param status the status value to set.
     */
    @Generated
    @JsonCreator
    private ResourceOperationStatusResourceIError(
            @JsonProperty(value = "status", required = true) OperationState status) {
        this.status = status;
    }

    /**
     * Get the id property: The unique ID of the operation.
     *
     * @return the id value.
     */
    @Generated
    public String getId() {
        return this.id;
    }

    /**
     * Get the status property: The status of the operation.
     *
     * @return the status value.
     */
    @Generated
    public OperationState getStatus() {
        return this.status;
    }

    /**
     * Get the error property: Error object that describes the error when status is "Failed".
     *
     * @return the error value.
     */
    @Generated
    public ResponseError getError() {
        return this.error;
    }
}
