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
 * Model with union of float literal property.
 */
@Fluent
public final class UnionFloatLiteralProperty implements JsonSerializable<UnionFloatLiteralProperty> {

    /*
     * Property
     */
    @Generated
    private UnionFloatLiteralPropertyProperty property;

    /**
     * Creates an instance of UnionFloatLiteralProperty class.
     */
    @Generated
    public UnionFloatLiteralProperty() {
    }

    /**
     * Get the property property: Property.
     *
     * @return the property value.
     */
    @Generated
    public UnionFloatLiteralPropertyProperty getProperty() {
        return this.property;
    }

    /**
     * Set the property property: Property.
     *
     * @param property the property value to set.
     * @return the UnionFloatLiteralProperty object itself.
     */
    @Generated
    public UnionFloatLiteralProperty setProperty(UnionFloatLiteralPropertyProperty property) {
        this.property = property;
        return this;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeNumberField("property", this.property == null ? null : this.property.toDouble());
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of UnionFloatLiteralProperty from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of UnionFloatLiteralProperty if the JsonReader was pointing to an instance of it, or null if
     * it was pointing to JSON null.
     * @throws IOException If an error occurs while reading the UnionFloatLiteralProperty.
     */
    public static UnionFloatLiteralProperty fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            UnionFloatLiteralProperty deserializedUnionFloatLiteralProperty = new UnionFloatLiteralProperty();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();
                if ("property".equals(fieldName)) {
                    deserializedUnionFloatLiteralProperty.property
                        = UnionFloatLiteralPropertyProperty.fromDouble(reader.getDouble());
                } else {
                    reader.skipChildren();
                }
            }
            return deserializedUnionFloatLiteralProperty;
        });
    }
}
