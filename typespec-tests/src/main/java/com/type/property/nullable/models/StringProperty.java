// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.property.nullable.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Template type for testing models with nullable property. Pass in the type of the property you are looking for.
 */
@Immutable
public final class StringProperty implements JsonSerializable<StringProperty> {
    /*
     * Required property
     */
    @Generated
    private final String requiredProperty;

    /*
     * Property
     */
    @Generated
    private final String nullableProperty;

    /**
     * Creates an instance of StringProperty class.
     * 
     * @param requiredProperty the requiredProperty value to set.
     * @param nullableProperty the nullableProperty value to set.
     */
    @Generated
    public StringProperty(String requiredProperty, String nullableProperty) {
        this.requiredProperty = requiredProperty;
        this.nullableProperty = nullableProperty;
    }

    /**
     * Get the requiredProperty property: Required property.
     * 
     * @return the requiredProperty value.
     */
    @Generated
    public String getRequiredProperty() {
        return this.requiredProperty;
    }

    /**
     * Get the nullableProperty property: Property.
     * 
     * @return the nullableProperty value.
     */
    @Generated
    public String getNullableProperty() {
        return this.nullableProperty;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("requiredProperty", this.requiredProperty);
        jsonWriter.writeStringField("nullableProperty", this.nullableProperty);
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
    public static StringProperty fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            boolean requiredPropertyFound = false;
            String requiredProperty = null;
            boolean nullablePropertyFound = false;
            String nullableProperty = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("requiredProperty".equals(fieldName)) {
                    requiredProperty = reader.getString();
                    requiredPropertyFound = true;
                } else if ("nullableProperty".equals(fieldName)) {
                    nullableProperty = reader.getString();
                    nullablePropertyFound = true;
                } else {
                    reader.skipChildren();
                }
            }
            if (requiredPropertyFound && nullablePropertyFound) {
                StringProperty deserializedStringProperty = new StringProperty(requiredProperty, nullableProperty);

                return deserializedStringProperty;
            }
            List<String> missingProperties = new ArrayList<>();
            if (!requiredPropertyFound) {
                missingProperties.add("requiredProperty");
            }
            if (!nullablePropertyFound) {
                missingProperties.add("nullableProperty");
            }

            throw new IllegalStateException(
                "Missing required property/properties: " + String.join(", ", missingProperties));
        });
    }
}
