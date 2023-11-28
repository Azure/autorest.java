// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.property.valuetypes.models;

import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * Enum that will be used as a property for model EnumProperty. Non-extensible.
 */
public enum FixedInnerEnum implements JsonSerializable<FixedInnerEnum> {
    /**
     * First value.
     */
    VALUE_ONE("ValueOne"),

    /**
     * Second value.
     */
    VALUE_TWO("ValueTwo");

    /**
     * The actual serialized value for a FixedInnerEnum instance.
     */
    private final String value;

    FixedInnerEnum(String value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a FixedInnerEnum instance.
     * 
     * @param value the serialized value to parse.
     * @return the parsed FixedInnerEnum object, or null if unable to parse.
     */
    public static FixedInnerEnum fromString(String value) {
        if (value == null) {
            return null;
        }
        FixedInnerEnum[] items = FixedInnerEnum.values();
        for (FixedInnerEnum item : items) {
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
     * Reads a FixedInnerEnum from the JSON stream.
     * <p>
     * The passed JsonReader must be positioned at a JsonToken.STRING value.
     * 
     * @param jsonReader The JsonReader being read.
     * @return The FixedInnerEnum that the JSON stream represented, may return null.
     * @throws java.io.IOException If a FixedInnerEnum fails to be read from the JsonReader.
     */
    public static FixedInnerEnum fromJson(JsonReader jsonReader) throws IOException {
        return fromString(jsonReader.getString());
    }
}
