package com.azure.autorest.customization.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The Java modifier for a class, property or a method.
 */
public enum Modifier {
    PUBLIC("public"),
    PRIVATE("private"),
    PACKAGE_PRIVATE("");

    private String value;

    Modifier(String value) {
        this.value = value;
    }

    /**
     * Parses a string into a Modifier enum.
     * @param value the string value
     * @return a Modifier enum if the input is valid, null otherwise
     */
    @JsonCreator
    public static Modifier fromString(String value) {
        Modifier[] items = Modifier.values();
        for (Modifier item : items) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        return null;
    }

    @JsonValue
    @Override
    public String toString() {
        return value;
    }
}
