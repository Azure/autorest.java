// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.property.optional.models;

/**
 * Defines values for UnionFloatLiteralPropertyProperty.
 */
public enum UnionFloatLiteralPropertyProperty {
    /**
     * Enum value 1.2.
     */
    ONE2(1.2),

    /**
     * Enum value 2.3.
     */
    TWO3(2.3);

    /**
     * The actual serialized value for a UnionFloatLiteralPropertyProperty instance.
     */
    private final double value;

    UnionFloatLiteralPropertyProperty(double value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a UnionFloatLiteralPropertyProperty instance.
     * 
     * @param value the serialized value to parse.
     * @return the parsed UnionFloatLiteralPropertyProperty object, or null if unable to parse.
     */
    public static UnionFloatLiteralPropertyProperty fromDouble(double value) {
        UnionFloatLiteralPropertyProperty[] items = UnionFloatLiteralPropertyProperty.values();
        for (UnionFloatLiteralPropertyProperty item : items) {
            if (Double.doubleToLongBits(item.toDouble()) == Double.doubleToLongBits(value)) {
                return item;
            }
        }
        return null;
    }

    /**
     * De-serializes the instance to double value.
     * 
     * @return the double value.
     */
    public double toDouble() {
        return this.value;
    }
}
