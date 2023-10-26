// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.literalservice.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Defines values for OptionalLiteralModel.
 */
public enum OptionalLiteralModel {
    /**
     * Enum value optionalLiteral.
     */
    OPTIONAL_LITERAL("optionalLiteral");

    /**
     * The actual serialized value for a OptionalLiteralModel instance.
     */
    private final String value;

    OptionalLiteralModel(String value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a OptionalLiteralModel instance.
     * 
     * @param value the serialized value to parse.
     * @return the parsed OptionalLiteralModel object, or null if unable to parse.
     */
    @JsonCreator
    public static OptionalLiteralModel fromString(String value) {
        if (value == null) {
            return null;
        }
        OptionalLiteralModel[] items = OptionalLiteralModel.values();
        for (OptionalLiteralModel item : items) {
            if (item.toString().equalsIgnoreCase(value)) {
                return item;
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @JsonValue
    @Override
    public String toString() {
        return this.value;
    }
}
