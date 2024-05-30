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
 * Model with float literal property.
 */
@Fluent
public final class FloatLiteralProperty implements JsonSerializable<FloatLiteralProperty> {
    /*
     * Property
     */
    @Generated
    private FloatLiteralPropertyProperty125 property;

    /**
     * Creates an instance of FloatLiteralProperty class.
     */
    @Generated
    public FloatLiteralProperty() {
    }

    /**
     * Get the property property: Property.
     * 
     * @return the property value.
     */
    @Generated
    public FloatLiteralPropertyProperty125 getProperty() {
        return this.property;
    }

    /**
     * Set the property property: Property.
     * 
     * @param property the property value to set.
     * @return the FloatLiteralProperty object itself.
     */
    @Generated
    public FloatLiteralProperty setProperty(FloatLiteralPropertyProperty125 property) {
        this.property = property;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeNumberField("property", this.property == null ? null : this.property.toDouble());
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of FloatLiteralProperty from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of FloatLiteralProperty if the JsonReader was pointing to an instance of it, or null if it
     * was pointing to JSON null.
     * @throws IOException If an error occurs while reading the FloatLiteralProperty.
     */
    @Generated
    public static FloatLiteralProperty fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            FloatLiteralProperty deserializedFloatLiteralProperty = new FloatLiteralProperty();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("property".equals(fieldName)) {
                    deserializedFloatLiteralProperty.property
                        = FloatLiteralPropertyProperty125.fromDouble(reader.getDouble());
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedFloatLiteralProperty;
        });
    }
}
