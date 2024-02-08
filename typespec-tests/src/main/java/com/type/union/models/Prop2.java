// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.
package com.type.union.models;

/**
 * Defines values for Prop2.
 */
public enum Prop2 {

    /**
     * Enum value 1.
     */
    ONE(1L),
    /**
     * Enum value 2.
     */
    TWO(2L),
    /**
     * Enum value 3.
     */
    THREE(3L);

    /**
     * The actual serialized value for a Prop2 instance.
     */
    private final long value;

    Prop2(long value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a Prop2 instance.
     *
     * @param value the serialized value to parse.
     * @return the parsed Prop2 object, or null if unable to parse.
     */
    public static Prop2 fromLong(long value) {
        Prop2[] items = Prop2.values();
        for (Prop2 item : items) {
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
    public long toLong() {
        return this.value;
    }
}
