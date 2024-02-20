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
 * Model with string literal property.
 */
@Fluent
public final class StringLiteralProperty implements JsonSerializable<StringLiteralProperty> {
    /*
     * Property
     */
    @Generated
    private StringLiteralPropertyProperty property;

    /**
     * Creates an instance of StringLiteralProperty class.
     */
    @Generated
    public StringLiteralProperty() {
    }

    /**
     * Get the property property: Property.
     * 
     * @return the property value.
     */
    @Generated
    public StringLiteralPropertyProperty getProperty() {
        return this.property;
    }

    /**
     * Set the property property: Property.
     * 
     * @param property the property value to set.
     * @return the StringLiteralProperty object itself.
     */
    @Generated
    public StringLiteralProperty setProperty(StringLiteralPropertyProperty property) {
        this.property = property;
        return this;
    }

    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("property", this.property == null ? null : this.property.toString());
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of StringLiteralProperty from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of StringLiteralProperty if the JsonReader was pointing to an instance of it, or null if it
     * was pointing to JSON null.
     * @throws IOException If an error occurs while reading the StringLiteralProperty.
     */
    @Generated
    public static StringLiteralProperty fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            StringLiteralProperty deserializedStringLiteralProperty = new StringLiteralProperty();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("property".equals(fieldName)) {
                    deserializedStringLiteralProperty.property
                        = StringLiteralPropertyProperty.fromString(reader.getString());
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedStringLiteralProperty;
        });
    }
}
