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
 * The model spread Record&lt;string&gt; with the different known property type.
 */
@Fluent
public class DifferentSpreadStringRecord implements JsonSerializable<DifferentSpreadStringRecord> {
    /*
     * The name property
     */
    @Generated
    private final double id;

    /*
     * Additional properties
     */
    @Generated
    private Map<String, String> additionalProperties;

    /**
     * Creates an instance of DifferentSpreadStringRecord class.
     * 
     * @param id the id value to set.
     */
    @Generated
    public DifferentSpreadStringRecord(double id) {
        this.id = id;
    }

    /**
     * Get the id property: The name property.
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
    public Map<String, String> getAdditionalProperties() {
        return this.additionalProperties;
    }

    /**
     * Set the additionalProperties property: Additional properties.
     * 
     * @param additionalProperties the additionalProperties value to set.
     * @return the DifferentSpreadStringRecord object itself.
     */
    @Generated
    public DifferentSpreadStringRecord setAdditionalProperties(Map<String, String> additionalProperties) {
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
        jsonWriter.writeDoubleField("id", this.id);
        if (additionalProperties != null) {
            for (Map.Entry<String, String> additionalProperty : additionalProperties.entrySet()) {
                jsonWriter.writeUntypedField(additionalProperty.getKey(), additionalProperty.getValue());
            }
        }
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of DifferentSpreadStringRecord from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of DifferentSpreadStringRecord if the JsonReader was pointing to an instance of it, or null
     * if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the DifferentSpreadStringRecord.
     */
    @Generated
    public static DifferentSpreadStringRecord fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            long foundTracker = 0;
            double id = 0.0;
            Map<String, String> additionalProperties = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("id".equals(fieldName)) {
                    id = reader.getDouble();
                } else {
                    if (additionalProperties == null) {
                        additionalProperties = new LinkedHashMap<>();
                    }

                    additionalProperties.put(fieldName, reader.getString());
                }
            }
            DifferentSpreadStringRecord deserializedDifferentSpreadStringRecord = new DifferentSpreadStringRecord(id);
            deserializedDifferentSpreadStringRecord.additionalProperties = additionalProperties;

            return deserializedDifferentSpreadStringRecord;
        });
    }
}
