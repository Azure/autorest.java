// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.
package com.type.model.usage.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * Record used in operation return type.
 */
@Immutable
public final class OutputRecord implements JsonSerializable<OutputRecord> {

    /*
     * The requiredProp property.
     */
    @Generated
    private final String requiredProp;

    /**
     * Creates an instance of OutputRecord class.
     *
     * @param requiredProp the requiredProp value to set.
     */
    @Generated
    private OutputRecord(String requiredProp) {
        this.requiredProp = requiredProp;
    }

    /**
     * Get the requiredProp property: The requiredProp property.
     *
     * @return the requiredProp value.
     */
    @Generated
    public String getRequiredProp() {
        return this.requiredProp;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("requiredProp", this.requiredProp);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of OutputRecord from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of OutputRecord if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the OutputRecord.
     */
    public static OutputRecord fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            String requiredProp = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();
                if ("requiredProp".equals(fieldName)) {
                    requiredProp = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }
            return new OutputRecord(requiredProp);
        });
    }
}
