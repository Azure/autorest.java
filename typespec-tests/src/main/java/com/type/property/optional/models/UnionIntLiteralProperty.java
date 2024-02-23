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
 * Model with union of int literal property.
 */
@Fluent
public final class UnionIntLiteralProperty implements JsonSerializable<UnionIntLiteralProperty> {
    /*
     * Property
     */
    @Generated
    private UnionIntLiteralPropertyProperty property;

    /**
     * Creates an instance of UnionIntLiteralProperty class.
     */
    @Generated
    public UnionIntLiteralProperty() {
    }

    /**
     * Get the property property: Property.
     * 
     * @return the property value.
     */
    @Generated
    public UnionIntLiteralPropertyProperty getProperty() {
        return this.property;
    }

    /**
     * Set the property property: Property.
     * 
     * @param property the property value to set.
     * @return the UnionIntLiteralProperty object itself.
     */
    @Generated
    public UnionIntLiteralProperty setProperty(UnionIntLiteralPropertyProperty property) {
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
        jsonWriter.writeNumberField("property", this.property == null ? null : this.property.toLong());
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of UnionIntLiteralProperty from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of UnionIntLiteralProperty if the JsonReader was pointing to an instance of it, or null if it
     * was pointing to JSON null.
     * @throws IOException If an error occurs while reading the UnionIntLiteralProperty.
     */
    @Generated
    public static UnionIntLiteralProperty fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            UnionIntLiteralProperty deserializedUnionIntLiteralProperty = new UnionIntLiteralProperty();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("property".equals(fieldName)) {
                    deserializedUnionIntLiteralProperty.property
                        = UnionIntLiteralPropertyProperty.fromLong(reader.getLong());
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedUnionIntLiteralProperty;
        });
    }
}
