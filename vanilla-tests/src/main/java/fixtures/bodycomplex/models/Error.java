// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex.models;

import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Error model.
 */
@Immutable
public final class Error {
    /*
     * The status property.
     */
    @JsonProperty(value = "status")
    private Integer status;

    /*
     * The message property.
     */
    @JsonProperty(value = "message")
    private String message;

    /**
     * Creates an instance of Error class.
     */
    private Error() {}

    /**
     * Get the status property: The status property.
     * 
     * @return the status value.
     */
    public Integer getStatus() {
        return this.status;
    }

    /**
     * Get the message property: The message property.
     * 
     * @return the message value.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {}
}
