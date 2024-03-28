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
 * Model with a property unknown, and the data is an array.
 */
@Immutable
public final class UnknownArrayProperty implements JsonSerializable<UnknownArrayProperty> {
    /*
     * Property
     */
    @Generated
    private final Object property;

    /**
     * Creates an instance of UnknownArrayProperty class.
     * 
     * @param property the property value to set.
     */
    @Generated
    public UnknownArrayProperty(Object property) {
        this.property = property;
    }

    /**
     * Get the property property: Property.
     * 
     * @return the property value.
     */
    @Generated
    public Object getProperty() {
        return this.property;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeUntypedField("property", this.property);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of UnknownArrayProperty from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of UnknownArrayProperty if the JsonReader was pointing to an instance of it, or null if it
     * was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the UnknownArrayProperty.
     */
    @Generated
    public static UnknownArrayProperty fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            Object property = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("property".equals(fieldName)) {
                    property = reader.readUntyped();
                } else {
                    reader.skipChildren();
                }
            }
            return new UnknownArrayProperty(property);
        });
    }
}
