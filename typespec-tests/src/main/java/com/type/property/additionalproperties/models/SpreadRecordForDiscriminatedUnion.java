// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.property.additionalproperties.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.core.util.BinaryData;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The model spread Record&lt;WidgetData&gt;.
 */
@Fluent
public final class SpreadRecordForDiscriminatedUnion implements JsonSerializable<SpreadRecordForDiscriminatedUnion> {
    /*
     * The name property
     */
    @Generated
    private final String name;

    /*
     * The model spread Record<WidgetData>
     * 
     * Additional properties
     */
    @Generated
    private Map<String, BinaryData> additionalProperties;

    /**
     * Creates an instance of SpreadRecordForDiscriminatedUnion class.
     * 
     * @param name the name value to set.
     */
    @Generated
    public SpreadRecordForDiscriminatedUnion(String name) {
        this.name = name;
    }

    /**
     * Get the name property: The name property.
     * 
     * @return the name value.
     */
    @Generated
    public String getName() {
        return this.name;
    }

    /**
     * Get the additionalProperties property: The model spread Record&lt;WidgetData&gt;
     * 
     * Additional properties.
     * 
     * @return the additionalProperties value.
     */
    @Generated
    public Map<String, BinaryData> getAdditionalProperties() {
        return this.additionalProperties;
    }

    /**
     * Set the additionalProperties property: The model spread Record&lt;WidgetData&gt;
     * 
     * Additional properties.
     * 
     * @param additionalProperties the additionalProperties value to set.
     * @return the SpreadRecordForDiscriminatedUnion object itself.
     */
    @Generated
    public SpreadRecordForDiscriminatedUnion setAdditionalProperties(Map<String, BinaryData> additionalProperties) {
        this.additionalProperties = additionalProperties;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("name", this.name);
        if (additionalProperties != null) {
            for (Map.Entry<String, BinaryData> additionalProperty : additionalProperties.entrySet()) {
                jsonWriter.writeUntypedField(additionalProperty.getKey(),
                    additionalProperty.getValue() == null
                        ? null
                        : additionalProperty.getValue().toObject(Object.class));
            }
        }
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of SpreadRecordForDiscriminatedUnion from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of SpreadRecordForDiscriminatedUnion if the JsonReader was pointing to an instance of it, or
     * null if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the SpreadRecordForDiscriminatedUnion.
     */
    @Generated
    public static SpreadRecordForDiscriminatedUnion fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            String name = null;
            Map<String, BinaryData> additionalProperties = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("name".equals(fieldName)) {
                    name = reader.getString();
                } else {
                    if (additionalProperties == null) {
                        additionalProperties = new LinkedHashMap<>();
                    }

                    additionalProperties.put(fieldName,
                        reader.getNullable(nonNullReader -> BinaryData.fromObject(nonNullReader.readUntyped())));
                }
            }
            SpreadRecordForDiscriminatedUnion deserializedSpreadRecordForDiscriminatedUnion
                = new SpreadRecordForDiscriminatedUnion(name);
            deserializedSpreadRecordForDiscriminatedUnion.additionalProperties = additionalProperties;

            return deserializedSpreadRecordForDiscriminatedUnion;
        });
    }
}
