// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.property.valuetypes.models;

import com.azure.core.annotation.Generated;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * Defines values for UnionFloatLiteralPropertyProperty.
 */
public enum UnionFloatLiteralPropertyProperty implements JsonSerializable<UnionFloatLiteralPropertyProperty> {
    /**
     * Enum value 42.42.
     */
    FORTY_TWO42(42.42),

    /**
     * Enum value 43.43.
     */
    FORTY_THREE43(43.43);

    /**
     * The actual serialized value for a UnionFloatLiteralPropertyProperty instance.
     */
    private final double value;

    UnionFloatLiteralPropertyProperty(double value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a UnionFloatLiteralPropertyProperty instance.
     * 
     * @param value the serialized value to parse.
     * @return the parsed UnionFloatLiteralPropertyProperty object, or null if unable to parse.
     */
    public static UnionFloatLiteralPropertyProperty fromDouble(double value) {
        UnionFloatLiteralPropertyProperty[] items = UnionFloatLiteralPropertyProperty.values();
        for (UnionFloatLiteralPropertyProperty item : items) {
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
     * Reads a UnionFloatLiteralPropertyProperty from the JSON stream.
     * <p>
     * The passed JsonReader must be positioned at a JsonToken.NUMBER value.
     * 
     * @param jsonReader The JsonReader being read.
     * @return The UnionFloatLiteralPropertyProperty that the JSON stream represented, may return null.
     * @throws java.io.IOException If a UnionFloatLiteralPropertyProperty fails to be read from the JsonReader.
     */
    @Generated
    public static UnionFloatLiteralPropertyProperty fromJson(JsonReader jsonReader) throws IOException {
        return fromDouble(jsonReader.getDouble());
    }
}
