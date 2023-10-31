// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.containers.containerregistry.implementation.models;

/**
 * Grant type is expected to be refresh_token.
 */
public enum TokenGrantType {
    /**
     * Enum value refresh_token.
     */
    REFRESH_TOKEN("refresh_token"),

    /**
     * Enum value password.
     */
    PASSWORD("password");

    /**
     * The actual serialized value for a TokenGrantType instance.
     */
    private final String value;

    TokenGrantType(String value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a TokenGrantType instance.
     * 
     * @param value the serialized value to parse.
     * @return the parsed TokenGrantType object, or null if unable to parse.
     */
    public static TokenGrantType fromString(String value) {
        if (value == null) {
            return null;
        }
        TokenGrantType[] items = TokenGrantType.values();
        for (TokenGrantType item : items) {
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
