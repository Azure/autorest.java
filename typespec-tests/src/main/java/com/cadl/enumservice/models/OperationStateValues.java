// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.enumservice.models;

/** Defines values for OperationStateValues. */
public enum OperationStateValues {
    /** Enum value Running. */
    RUNNING("Running"),

    /** Enum value Completed. */
    COMPLETED("Completed"),

    /** Enum value Failed. */
    FAILED("Failed");

    /** The actual serialized value for a OperationStateValues instance. */
    private final String value;

    OperationStateValues(String value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a OperationStateValues instance.
     *
     * @param value the serialized value to parse.
     * @return the parsed OperationStateValues object, or null if unable to parse.
     */
    public static OperationStateValues fromString(String value) {
        if (value == null) {
            return null;
        }
        OperationStateValues[] items = OperationStateValues.values();
        for (OperationStateValues item : items) {
            if (item.toString().equalsIgnoreCase(value)) {
                return item;
            }
        }
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return this.value;
    }
}
