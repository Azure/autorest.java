// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.enums.fixed.models;

import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * Days of the week.
 */
public enum DaysOfWeekEnum implements JsonSerializable<DaysOfWeekEnum> {
    /**
     * Monday.
     */
    MONDAY("Monday"),

    /**
     * Tuesday.
     */
    TUESDAY("Tuesday"),

    /**
     * Wednesday.
     */
    WEDNESDAY("Wednesday"),

    /**
     * Thursday.
     */
    THURSDAY("Thursday"),

    /**
     * Friday.
     */
    FRIDAY("Friday"),

    /**
     * Saturday.
     */
    SATURDAY("Saturday"),

    /**
     * Sunday.
     */
    SUNDAY("Sunday");

    /**
     * The actual serialized value for a DaysOfWeekEnum instance.
     */
    private final String value;

    DaysOfWeekEnum(String value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a DaysOfWeekEnum instance.
     * 
     * @param value the serialized value to parse.
     * @return the parsed DaysOfWeekEnum object, or null if unable to parse.
     */
    public static DaysOfWeekEnum fromString(String value) {
        if (value == null) {
            return null;
        }
        DaysOfWeekEnum[] items = DaysOfWeekEnum.values();
        for (DaysOfWeekEnum item : items) {
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

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        return jsonWriter.writeString(value);
    }

    /**
     * Reads a DaysOfWeekEnum from the JSON stream.
     * &lt;p&gt;.
     * The passed JsonReader must be positioned at a JsonToken.STRING value.
     * 
     * @param jsonReader The JsonReader being read.
     * @return The DaysOfWeekEnum that the JSON stream represented, may return null.
     * @throws java.io.IOException If a DaysOfWeekEnum fails to be read from the JsonReader.
     */
    public static DaysOfWeekEnum fromJson(JsonReader jsonReader) throws IOException {
        return fromString(jsonReader.getString());
    }
}
