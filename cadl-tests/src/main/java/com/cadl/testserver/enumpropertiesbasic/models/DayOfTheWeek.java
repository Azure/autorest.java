// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.cadl.testserver.enumpropertiesbasic.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/** Represents the days of the week using a standard, non-string enum. */
public enum DayOfTheWeek {
    /** Enum value Monday. */
    MONDAY("Monday"),

    /** Enum value Tuesday. */
    TUESDAY("Tuesday"),

    /** Enum value Wednesday. */
    WEDNESDAY("Wednesday"),

    /** Enum value Thursday. */
    THURSDAY("Thursday"),

    /** Enum value Friday. */
    FRIDAY("Friday"),

    /** Enum value Saturday. */
    SATURDAY("Saturday"),

    /** Enum value Sunday. */
    SUNDAY("Sunday");

    /** The actual serialized value for a DayOfTheWeek instance. */
    private final String value;

    DayOfTheWeek(String value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a DayOfTheWeek instance.
     *
     * @param value the serialized value to parse.
     * @return the parsed DayOfTheWeek object, or null if unable to parse.
     */
    @JsonCreator
    public static DayOfTheWeek fromString(String value) {
        if (value == null) {
            return null;
        }
        DayOfTheWeek[] items = DayOfTheWeek.values();
        for (DayOfTheWeek item : items) {
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
