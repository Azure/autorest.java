// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.property.valuetypes.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * Model with a string property.
 */
@Immutable
public final class StringProperty implements JsonSerializable<StringProperty> {
    /*
     * A sequence of textual characters.
     * 
     * Property
     */
    @Generated
    private final String property;

    /**
     * Creates an instance of StringProperty class.
     * 
     * @param property the property value to set.
     */
    @Generated
    public StringProperty(String property) {
        this.property = property;
    }

    /**
     * Get the property property: A sequence of textual characters.
     * 
     * Property.
     * 
     * @return the property value.
     */
    @Generated
    public String getProperty() {
        return this.property;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("property", this.property);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of StringProperty from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of StringProperty if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the StringProperty.
     */
    @Generated
    public static StringProperty fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            String property = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("property".equals(fieldName)) {
                    property = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }
            return new StringProperty(property);
        });
    }
}
