// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.encode.bytes.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.core.util.Base64Url;

import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;

import java.io.IOException;

import java.util.Objects;

/**
 * The Base64urlBytesProperty model.
 */
@Immutable
public final class Base64urlBytesProperty implements JsonSerializable<Base64urlBytesProperty> {
    /*
     * The value property.
     */
    @Generated
    private final Base64Url value;

    /**
     * Creates an instance of Base64urlBytesProperty class.
     * 
     * @param value the value value to set.
     */
    @Generated
    public Base64urlBytesProperty(byte[] value) {
        this.value = Base64Url.encode(value);
    }

    /**
     * Get the value property: The value property.
     * 
     * @return the value value.
     */
    @Generated
    public byte[] getValue() {
        if (this.value == null) {
            return null;
        }
        return this.value.decodedBytes();
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("value", Objects.toString(this.value, null));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of Base64urlBytesProperty from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of Base64urlBytesProperty if the JsonReader was pointing to an instance of it, or null if it
     * was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the Base64urlBytesProperty.
     */
    public static Base64urlBytesProperty fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            byte[] value = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("value".equals(fieldName)) {
                    Base64Url valueHolder
                        = reader.getNullable(nonNullReader -> new Base64Url(nonNullReader.getString()));
                    if (valueHolder != null) {
                        value = valueHolder.decodedBytes();
                    }
                } else {
                    reader.skipChildren();
                }
            }
            return new Base64urlBytesProperty(value);
        });
    }
}
