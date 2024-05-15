// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.model.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * The OutputData3 model.
 */
@Immutable
public final class OutputData3 implements JsonSerializable<OutputData3> {
    /*
     * A sequence of textual characters.
     */
    @Generated
    private final String data;

    /**
     * Creates an instance of OutputData3 class.
     * 
     * @param data the data value to set.
     */
    @Generated
    private OutputData3(String data) {
        this.data = data;
    }

    /**
     * Get the data property: A sequence of textual characters.
     * 
     * @return the data value.
     */
    @Generated
    public String getData() {
        return this.data;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("data", this.data);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of OutputData3 from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of OutputData3 if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the OutputData3.
     */
    @Generated
    public static OutputData3 fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            String data = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("data".equals(fieldName)) {
                    data = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }
            return new OutputData3(data);
        });
    }
}
