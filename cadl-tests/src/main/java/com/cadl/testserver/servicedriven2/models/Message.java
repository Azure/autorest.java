// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.cadl.testserver.servicedriven2.models;

import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The Message model. */
@Immutable
public final class Message {
    /*
     * The message property.
     */
    @JsonProperty(value = "message", required = true)
    private String message;

    /**
     * Creates an instance of Message class.
     *
     * @param message the message value to set.
     */
    @JsonCreator
    private Message(@JsonProperty(value = "message", required = true) String message) {
        this.message = message;
    }

    /**
     * Get the message property: The message property.
     *
     * @return the message value.
     */
    public String getMessage() {
        return this.message;
    }
}
