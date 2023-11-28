// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.naming.models;

import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * summary of Types
 * 
 * description of Types.
 */
public enum TypesModel implements JsonSerializable<TypesModel> {
    /**
     * Enum value Blob.
     */
    BLOB("Blob"),

    /**
     * Enum value File.
     */
    FILE("File");

    /**
     * The actual serialized value for a TypesModel instance.
     */
    private final String value;

    TypesModel(String value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a TypesModel instance.
     * 
     * @param value the serialized value to parse.
     * @return the parsed TypesModel object, or null if unable to parse.
     */
    public static TypesModel fromString(String value) {
        if (value == null) {
            return null;
        }
        TypesModel[] items = TypesModel.values();
        for (TypesModel item : items) {
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
     * Reads a TypesModel from the JSON stream.
     * &lt;p&gt;.
     * The passed JsonReader must be positioned at a JsonToken.STRING value.
     * 
     * @param jsonReader The JsonReader being read.
     * @return The TypesModel that the JSON stream represented, may return null.
     * @throws java.io.IOException If a TypesModel fails to be read from the JsonReader.
     */
    public static TypesModel fromJson(JsonReader jsonReader) throws IOException {
        return fromString(jsonReader.getString());
    }
}
