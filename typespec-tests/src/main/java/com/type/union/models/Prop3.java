// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.union.models;

/**
 * Defines values for Prop3.
 */
public enum Prop3 {
    /**
     * Enum value 1.1.
     */
    ONE_ONE(1.1),

    /**
     * Enum value 2.2.
     */
    TWO_TWO(2.2),

    /**
     * Enum value 3.3.
     */
    THREE_THREE(3.3);

    /**
     * The actual serialized value for a Prop3 instance.
     */
    private final double value;

    Prop3(double value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a Prop3 instance.
     * 
     * @param value the serialized value to parse.
     * @return the parsed Prop3 object, or null if unable to parse.
     */
    public static Prop3 fromDouble(double value) {
        Prop3[] items = Prop3.values();
        for (Prop3 item : items) {
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
