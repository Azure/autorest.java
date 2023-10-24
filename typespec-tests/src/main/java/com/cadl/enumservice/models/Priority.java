// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.enumservice.models;

import com.azure.core.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Defines values for Priority.
 */
public enum Priority {
    /**
     * Enum value 100.
     */
    HIGH(100L),

    /**
     * Enum value 0.
     */
    LOW(0L);

    /**
     * The actual serialized value for a Priority instance.
     */
    private final long value;

    Priority(long value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a Priority instance.
     * 
     * @param value the serialized value to parse.
     * @return the parsed Priority object, or null if unable to parse.
     */
    @JsonCreator
    public static Priority fromLong(long value) {
        Priority[] items = Priority.values();
        for (Priority item : items) {
            if (item.toLong() == value) {
                return item;
            }
        }
        return null;
    }

    /**
     * De-serializes the instance to long value.
     * 
     * @return the long value.
     */
    @JsonValue
    public long toLong() {
        return this.value;
    }
}
