// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.property.optional.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;

import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;

import java.io.IOException;

/**
 * Model with boolean literal property.
 */
@Fluent
public final class BooleanLiteralProperty implements JsonSerializable<BooleanLiteralProperty> {
    /*
     * Property
     */
    @Generated
    private BooleanLiteralPropertyProperty property;

    /**
     * Creates an instance of BooleanLiteralProperty class.
     */
    @Generated
    public BooleanLiteralProperty() {
    }

    /**
     * Get the property property: Property.
     * 
     * @return the property value.
     */
    @Generated
    public BooleanLiteralPropertyProperty getProperty() {
        return this.property;
    }

    /**
     * Set the property property: Property.
     * 
     * @param property the property value to set.
     * @return the BooleanLiteralProperty object itself.
     */
    @Generated
    public BooleanLiteralProperty setProperty(BooleanLiteralPropertyProperty property) {
        this.property = property;
        return this;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeBooleanField("property", this.property == null ? null : this.property.toBoolean());
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of BooleanLiteralProperty from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of BooleanLiteralProperty if the JsonReader was pointing to an instance of it, or null if it
     * was pointing to JSON null.
     * @throws IOException If an error occurs while reading the BooleanLiteralProperty.
     */
    public static BooleanLiteralProperty fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            BooleanLiteralProperty deserializedBooleanLiteralProperty = new BooleanLiteralProperty();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("property".equals(fieldName)) {
                    deserializedBooleanLiteralProperty.property
                        = BooleanLiteralPropertyProperty.fromBoolean(reader.getBoolean());
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedBooleanLiteralProperty;
        });
    }
}
