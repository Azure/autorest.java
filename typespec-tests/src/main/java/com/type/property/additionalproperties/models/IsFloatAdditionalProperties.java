// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.property.additionalproperties.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;

import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;

import java.io.IOException;

import java.util.LinkedHashMap;

import java.util.Map;

/**
 * The model is from Record&lt;float32&gt; type.
 */
@Fluent
public final class IsFloatAdditionalProperties implements JsonSerializable<IsFloatAdditionalProperties> {
    /*
     * The id property
     */
    @Generated
    private final double id;

    /*
     * Additional properties
     */
    @Generated
    private Map<String, Double> additionalProperties;

    /**
     * Creates an instance of IsFloatAdditionalProperties class.
     * 
     * @param id the id value to set.
     */
    @Generated
    public IsFloatAdditionalProperties(double id) {
        this.id = id;
    }

    /**
     * Get the id property: The id property.
     * 
     * @return the id value.
     */
    @Generated
    public double getId() {
        return this.id;
    }

    /**
     * Get the additionalProperties property: Additional properties.
     * 
     * @return the additionalProperties value.
     */
    @Generated
    public Map<String, Double> getAdditionalProperties() {
        return this.additionalProperties;
    }

    /**
     * Set the additionalProperties property: Additional properties.
     * 
     * @param additionalProperties the additionalProperties value to set.
     * @return the IsFloatAdditionalProperties object itself.
     */
    @Generated
    public IsFloatAdditionalProperties setAdditionalProperties(Map<String, Double> additionalProperties) {
        this.additionalProperties = additionalProperties;
        return this;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeDoubleField("id", this.id);
        if (additionalProperties != null) {
            for (Map.Entry<String, Double> additionalProperty : additionalProperties.entrySet()) {
                jsonWriter.writeUntypedField(additionalProperty.getKey(), additionalProperty.getValue());
            }
        }
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of IsFloatAdditionalProperties from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of IsFloatAdditionalProperties if the JsonReader was pointing to an instance of it, or null
     * if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the IsFloatAdditionalProperties.
     */
    public static IsFloatAdditionalProperties fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            double id = 0.0;
            Map<String, Double> additionalProperties = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("id".equals(fieldName)) {
                    id = reader.getDouble();
                } else {
                    if (additionalProperties == null) {
                        additionalProperties = new LinkedHashMap<>();
                    }

                    additionalProperties.put(fieldName, reader.getDouble());
                }
            }
            IsFloatAdditionalProperties deserializedIsFloatAdditionalProperties = new IsFloatAdditionalProperties(id);
            deserializedIsFloatAdditionalProperties.additionalProperties = additionalProperties;

            return deserializedIsFloatAdditionalProperties;
        });
    }
}
