// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.parameters.spread.implementation.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * The SpreadAsRequestBodyRequest model.
 */
@Immutable
public final class SpreadAsRequestBodyRequest implements JsonSerializable<SpreadAsRequestBodyRequest> {
    /*
     * The name property.
     */
    @Generated
    private final String name;

    /**
     * Creates an instance of SpreadAsRequestBodyRequest class.
     * 
     * @param name the name value to set.
     */
    @Generated
    public SpreadAsRequestBodyRequest(String name) {
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
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("name", this.name);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of SpreadAsRequestBodyRequest from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of SpreadAsRequestBodyRequest if the JsonReader was pointing to an instance of it, or null if
     * it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the SpreadAsRequestBodyRequest.
     */
    @Generated
    public static SpreadAsRequestBodyRequest fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            long foundTracker = 0;
            String name = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("name".equals(fieldName)) {
                    name = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }
            return new SpreadAsRequestBodyRequest(name);
        });
    }
}
