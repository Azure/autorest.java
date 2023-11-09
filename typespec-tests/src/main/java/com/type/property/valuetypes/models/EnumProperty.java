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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Model with enum properties.
 */
@Immutable
public final class EnumProperty implements JsonSerializable<EnumProperty> {
    /*
     * Property
     */
    @Generated
    private final FixedInnerEnum property;

    /**
     * Creates an instance of EnumProperty class.
     * 
     * @param property the property value to set.
     */
    @Generated
    public EnumProperty(FixedInnerEnum property) {
        this.property = property;
    }

    /**
     * Get the property property: Property.
     * 
     * @return the property value.
     */
    @Generated
    public FixedInnerEnum getProperty() {
        return this.property;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("property", Objects.toString(this.property, null));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of EnumProperty from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of EnumProperty if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the EnumProperty.
     */
    public static EnumProperty fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            boolean propertyFound = false;
            FixedInnerEnum property = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("property".equals(fieldName)) {
                    property = FixedInnerEnum.fromString(reader.getString());
                    propertyFound = true;
                } else {
                    reader.skipChildren();
                }
            }
            if (propertyFound) {
                EnumProperty deserializedEnumProperty = new EnumProperty(property);

                return deserializedEnumProperty;
            }
            List<String> missingProperties = new ArrayList<>();
            if (!propertyFound) {
                missingProperties.add("property");
            }

            throw new IllegalStateException(
                "Missing required property/properties: " + String.join(", ", missingProperties));
        });
    }
}
