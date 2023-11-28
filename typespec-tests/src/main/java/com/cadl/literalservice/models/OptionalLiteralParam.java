// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.literalservice.models;

import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * Defines values for OptionalLiteralParam.
 */
public enum OptionalLiteralParam implements JsonSerializable<OptionalLiteralParam> {
    /**
     * Enum value optionalLiteralParam.
     */
    OPTIONAL_LITERAL_PARAM("optionalLiteralParam");

    /**
     * The actual serialized value for a OptionalLiteralParam instance.
     */
    private final String value;

    OptionalLiteralParam(String value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a OptionalLiteralParam instance.
     * 
     * @param value the serialized value to parse.
     * @return the parsed OptionalLiteralParam object, or null if unable to parse.
     */
    public static OptionalLiteralParam fromString(String value) {
        if (value == null) {
            return null;
        }
        OptionalLiteralParam[] items = OptionalLiteralParam.values();
        for (OptionalLiteralParam item : items) {
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
     * Reads a OptionalLiteralParam from the JSON stream.
     * &lt;p&gt;.
     * The passed JsonReader must be positioned at a JsonToken.STRING value.
     * 
     * @param jsonReader The JsonReader being read.
     * @return The OptionalLiteralParam that the JSON stream represented, may return null.
     * @throws java.io.IOException If a OptionalLiteralParam fails to be read from the JsonReader.
     */
    public static OptionalLiteralParam fromJson(JsonReader jsonReader) throws IOException {
        return fromString(jsonReader.getString());
    }
}
