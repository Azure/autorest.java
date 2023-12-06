// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.property.optional.models;

/**
 * Defines values for UnionIntLiteralPropertyProperty.
 */
public enum UnionIntLiteralPropertyProperty {
    /**
     * Enum value 1.
     */
    ONE(1L),

    /**
     * Enum value 2.
     */
    TWO(2L);

    /**
     * The actual serialized value for a UnionIntLiteralPropertyProperty instance.
     */
    private final long value;

    UnionIntLiteralPropertyProperty(long value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a UnionIntLiteralPropertyProperty instance.
     * 
     * @param value the serialized value to parse.
     * @return the parsed UnionIntLiteralPropertyProperty object, or null if unable to parse.
     */
    public static UnionIntLiteralPropertyProperty fromLong(long value) {
        UnionIntLiteralPropertyProperty[] items = UnionIntLiteralPropertyProperty.values();
        for (UnionIntLiteralPropertyProperty item : items) {
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
