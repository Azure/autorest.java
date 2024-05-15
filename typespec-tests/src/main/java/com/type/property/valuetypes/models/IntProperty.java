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
 * Model with a int property.
 */
@Immutable
public final class IntProperty implements JsonSerializable<IntProperty> {
    /*
     * A 32-bit integer. (`-2,147,483,648` to `2,147,483,647`)
     * 
     * Property
     */
    @Generated
    private final int property;

    /**
     * Creates an instance of IntProperty class.
     * 
     * @param property the property value to set.
     */
    @Generated
    public IntProperty(int property) {
        this.property = property;
    }

    /**
     * Get the property property: A 32-bit integer. (`-2,147,483,648` to `2,147,483,647`)
     * 
     * Property.
     * 
     * @return the property value.
     */
    @Generated
    public int getProperty() {
        return this.property;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeIntField("property", this.property);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of IntProperty from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of IntProperty if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the IntProperty.
     */
    @Generated
    public static IntProperty fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            int property = 0;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("property".equals(fieldName)) {
                    property = reader.getInt();
                } else {
                    reader.skipChildren();
                }
            }
            return new IntProperty(property);
        });
    }
}
