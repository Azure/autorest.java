// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.constants.models;

/**
 * Defines values for NoModelAsStringNoRequiredTwoValueNoDefaultOpEnum.
 */
public enum NoModelAsStringNoRequiredTwoValueNoDefaultOpEnum {
    /**
     * Enum value value1.
     */
    VALUE1("value1"),

    /**
     * Enum value value2.
     */
    VALUE2("value2");

    /**
     * The actual serialized value for a NoModelAsStringNoRequiredTwoValueNoDefaultOpEnum instance.
     */
    private final String value;

    NoModelAsStringNoRequiredTwoValueNoDefaultOpEnum(String value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a NoModelAsStringNoRequiredTwoValueNoDefaultOpEnum instance.
     * 
     * @param value the serialized value to parse.
     * @return the parsed NoModelAsStringNoRequiredTwoValueNoDefaultOpEnum object, or null if unable to parse.
     */
    public static NoModelAsStringNoRequiredTwoValueNoDefaultOpEnum fromString(String value) {
        if (value == null) {
            return null;
        }
        NoModelAsStringNoRequiredTwoValueNoDefaultOpEnum[] items
            = NoModelAsStringNoRequiredTwoValueNoDefaultOpEnum.values();
        for (NoModelAsStringNoRequiredTwoValueNoDefaultOpEnum item : items) {
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
