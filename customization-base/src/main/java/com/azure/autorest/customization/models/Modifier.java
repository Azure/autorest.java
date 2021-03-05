package com.azure.autorest.customization.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The Java modifier for a class, property or a method.
 */
public enum Modifier {
    // Scope modifiers
    PUBLIC("public"),
    PROTECTED("protected"),
    PACKAGE_PRIVATE(""),
    PRIVATE("private"),

    // Non-scope modifiers
    ABSTRACT("abstract"),
    FINAL("final"),
    STATIC("static"),
    SYNCHRONIZED("synchronized");

    private final String value;

    Modifier(String value) {
        this.value = value;
    }

    private static final Map<String, Modifier> VALUE_TO_MODIFIER;
    static {
        VALUE_TO_MODIFIER = new ConcurrentHashMap<>();
        VALUE_TO_MODIFIER.put(PUBLIC.value, PUBLIC);
        VALUE_TO_MODIFIER.put(PROTECTED.value, PROTECTED);
        VALUE_TO_MODIFIER.put(PACKAGE_PRIVATE.value, PACKAGE_PRIVATE);
        VALUE_TO_MODIFIER.put(PRIVATE.value, PRIVATE);
        VALUE_TO_MODIFIER.put(ABSTRACT.value, ABSTRACT);
        VALUE_TO_MODIFIER.put(FINAL.value, FINAL);
        VALUE_TO_MODIFIER.put(STATIC.value, STATIC);
        VALUE_TO_MODIFIER.put(SYNCHRONIZED.value, SYNCHRONIZED);
    }

    /**
     * Parses a string into a Modifier enum.
     * @param value the string value
     * @return a Modifier enum if the input is valid, null otherwise
     */
    @JsonCreator
    public static Modifier fromString(String value) {
        return VALUE_TO_MODIFIER.get(value);
    }

    @JsonValue
    @Override
    public String toString() {
        return value;
    }
}
