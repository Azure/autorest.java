// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.
package fixtures.constants.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Defines values for NoModelAsStringNoRequiredTwoValueDefaultEnum.
 */
public enum NoModelAsStringNoRequiredTwoValueDefaultEnum {

    /**
     * Enum value value1.
     */
    VALUE1("value1"),
    /**
     * Enum value value2.
     */
    VALUE2("value2");

    /**
     * The actual serialized value for a NoModelAsStringNoRequiredTwoValueDefaultEnum instance.
     */
    private final String value;

    NoModelAsStringNoRequiredTwoValueDefaultEnum(String value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a NoModelAsStringNoRequiredTwoValueDefaultEnum instance.
     *
     * @param value the serialized value to parse.
     * @return the parsed NoModelAsStringNoRequiredTwoValueDefaultEnum object, or null if unable to parse.
     */
    @JsonCreator
    public static NoModelAsStringNoRequiredTwoValueDefaultEnum fromString(String value) {
        if (value == null) {
            return null;
        }
        NoModelAsStringNoRequiredTwoValueDefaultEnum[] items = NoModelAsStringNoRequiredTwoValueDefaultEnum.values();
        for (NoModelAsStringNoRequiredTwoValueDefaultEnum item : items) {
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
