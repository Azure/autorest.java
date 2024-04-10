// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.naming.models;

/**
 * Defines values for RunObjectCode.
 */
public enum RunObjectCode {
    /**
     * Enum value server_error.
     */
    SERVER_ERROR("server_error"),

    /**
     * Enum value rate_limit_exceeded.
     */
    RATE_LIMIT_EXCEEDED("rate_limit_exceeded"),

    /**
     * Enum value invalid_prompt.
     */
    INVALID_PROMPT("invalid_prompt");

    /**
     * The actual serialized value for a RunObjectCode instance.
     */
    private final String value;

    RunObjectCode(String value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a RunObjectCode instance.
     * 
     * @param value the serialized value to parse.
     * @return the parsed RunObjectCode object, or null if unable to parse.
     */
    public static RunObjectCode fromString(String value) {
        if (value == null) {
            return null;
        }
        RunObjectCode[] items = RunObjectCode.values();
        for (RunObjectCode item : items) {
            if (item.toString().equalsIgnoreCase(value)) {
                return item;
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.value;
    }
}
