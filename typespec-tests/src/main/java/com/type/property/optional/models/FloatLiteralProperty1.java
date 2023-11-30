// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.property.optional.models;

import com.azure.core.annotation.Generated;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * Defines values for FloatLiteralProperty1.
 */
public enum FloatLiteralProperty1 implements JsonSerializable<FloatLiteralProperty1> {
    /**
     * Enum value 1.2.
     */
    ONE_TWO(1.2);

    /**
     * The actual serialized value for a FloatLiteralProperty1 instance.
     */
    private final double value;

    FloatLiteralProperty1(double value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a FloatLiteralProperty1 instance.
     * 
     * @param value the serialized value to parse.
     * @return the parsed FloatLiteralProperty1 object, or null if unable to parse.
     */
    public static FloatLiteralProperty1 fromDouble(double value) {
        FloatLiteralProperty1[] items = FloatLiteralProperty1.values();
        for (FloatLiteralProperty1 item : items) {
            if (Double.doubleToLongBits(item.toDouble()) == Double.doubleToLongBits(value)) {
                return item;
            }
        }
        return null;
    }

    /**
     * De-serializes the instance to double value.
     * 
     * @return the double value.
     */
    public double toDouble() {
        return this.value;
    }

    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        return jsonWriter.writeDouble(value);
    }

    /**
     * Reads a FloatLiteralProperty1 from the JSON stream.
     * <p>
     * The passed JsonReader must be positioned at a JsonToken.NUMBER value.
     * 
     * @param jsonReader The JsonReader being read.
     * @return The FloatLiteralProperty1 that the JSON stream represented, may return null.
     * @throws java.io.IOException If a FloatLiteralProperty1 fails to be read from the JsonReader.
     */
    @Generated
    public static FloatLiteralProperty1 fromJson(JsonReader jsonReader) throws IOException {
        return fromDouble(jsonReader.getDouble());
    }
}
