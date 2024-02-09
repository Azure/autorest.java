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
 * Model with a boolean property.
 */
@Immutable
public final class BooleanProperty implements JsonSerializable<BooleanProperty> {
    /*
     * Property
     */
    @Generated
    private final boolean property;

    /**
     * Creates an instance of BooleanProperty class.
     * 
     * @param property the property value to set.
     */
    @Generated
    public BooleanProperty(boolean property) {
        this.property = property;
    }

    /**
     * Get the property property: Property.
     * 
     * @return the property value.
     */
    @Generated
    public boolean isProperty() {
        return this.property;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeBooleanField("property", this.property);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of BooleanProperty from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of BooleanProperty if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the BooleanProperty.
     */
    public static BooleanProperty fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            boolean property = false;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("property".equals(fieldName)) {
                    property = reader.getBoolean();
                } else {
                    reader.skipChildren();
                }
            }
            return new BooleanProperty(property);
        });
    }
}
