// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.internal.implementation.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/** Defines values for Color. */
public enum Color {
    /** Enum value Red. */
    RED("Red"),

    /** Enum value Blue. */
    BLUE("Blue"),

    /** Enum value Green. */
    GREEN("Green");

    /** The actual serialized value for a Color instance. */
    private final String value;

    Color(String value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a Color instance.
     *
     * @param value the serialized value to parse.
     * @return the parsed Color object, or null if unable to parse.
     */
    @JsonCreator
    public static Color fromString(String value) {
        if (value == null) {
            return null;
        }
        Color[] items = Color.values();
        for (Color item : items) {
            if (item.toString().equalsIgnoreCase(value)) {
                return item;
            }
        }
        return null;
    }

    /** {@inheritDoc} */
    @JsonValue
    @Override
    public String toString() {
        return this.value;
    }
}
