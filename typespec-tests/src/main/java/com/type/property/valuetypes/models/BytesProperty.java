// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.property.valuetypes.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.core.util.CoreUtils;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Model with a bytes property.
 */
@Immutable
public final class BytesProperty implements JsonSerializable<BytesProperty> {
    /*
     * Property
     */
    @Generated
    private final byte[] property;

    /**
     * Creates an instance of BytesProperty class.
     * 
     * @param property the property value to set.
     */
    @Generated
    public BytesProperty(byte[] property) {
        this.property = property;
    }

    /**
     * Get the property property: Property.
     * 
     * @return the property value.
     */
    @Generated
    public byte[] getProperty() {
        return CoreUtils.clone(this.property);
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeBinaryField("property", this.property);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of BytesProperty from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of BytesProperty if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the BytesProperty.
     */
    public static BytesProperty fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            boolean propertyFound = false;
            byte[] property = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("property".equals(fieldName)) {
                    property = reader.getBinary();
                    propertyFound = true;
                } else {
                    reader.skipChildren();
                }
            }
            if (propertyFound) {
                BytesProperty deserializedBytesProperty = new BytesProperty(property);

                return deserializedBytesProperty;
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
