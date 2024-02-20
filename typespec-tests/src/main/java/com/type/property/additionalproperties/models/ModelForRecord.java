// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.property.additionalproperties.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * model for record.
 */
@Immutable
public final class ModelForRecord implements JsonSerializable<ModelForRecord> {
    /*
     * The state property
     */
    @Generated
    private final String state;

    /**
     * Creates an instance of ModelForRecord class.
     * 
     * @param state the state value to set.
     */
    @Generated
    public ModelForRecord(String state) {
        this.state = state;
    }

    /**
     * Get the state property: The state property.
     * 
     * @return the state value.
     */
    @Generated
    public String getState() {
        return this.state;
    }

    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("state", this.state);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ModelForRecord from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of ModelForRecord if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the ModelForRecord.
     */
    @Generated
    public static ModelForRecord fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            String state = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("state".equals(fieldName)) {
                    state = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }
            return new ModelForRecord(state);
        });
    }
}
