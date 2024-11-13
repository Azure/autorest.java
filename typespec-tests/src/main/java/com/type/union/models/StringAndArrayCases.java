// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.union.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.core.util.BinaryData;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * The StringAndArrayCases model.
 */
@Immutable
public final class StringAndArrayCases implements JsonSerializable<StringAndArrayCases> {
    /*
     * This should be receive/send the string variant
     */
    @Generated
    private final BinaryData string;

    /*
     * This should be receive/send the array variant
     */
    @Generated
    private final BinaryData array;

    /**
     * Creates an instance of StringAndArrayCases class.
     * 
     * @param string the string value to set.
     * @param array the array value to set.
     */
    @Generated
    public StringAndArrayCases(BinaryData string, BinaryData array) {
        this.string = string;
        this.array = array;
    }

    /**
     * Get the string property: This should be receive/send the string variant.
     * 
     * @return the string value.
     */
    @Generated
    public BinaryData getString() {
        return this.string;
    }

    /**
     * Get the array property: This should be receive/send the array variant.
     * 
     * @return the array value.
     */
    @Generated
    public BinaryData getArray() {
        return this.array;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeFieldName("string");
        this.string.writeTo(jsonWriter);
        jsonWriter.writeFieldName("array");
        this.array.writeTo(jsonWriter);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of StringAndArrayCases from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of StringAndArrayCases if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the StringAndArrayCases.
     */
    @Generated
    public static StringAndArrayCases fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            BinaryData string = null;
            BinaryData array = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("string".equals(fieldName)) {
                    string = reader.getNullable(nonNullReader -> BinaryData.fromObject(nonNullReader.readUntyped()));
                } else if ("array".equals(fieldName)) {
                    array = reader.getNullable(nonNullReader -> BinaryData.fromObject(nonNullReader.readUntyped()));
                } else {
                    reader.skipChildren();
                }
            }
            return new StringAndArrayCases(string, array);
        });
    }
}
