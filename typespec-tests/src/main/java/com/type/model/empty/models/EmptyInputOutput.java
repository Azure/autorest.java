// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.model.empty.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * Empty model used in both parameter and return type.
 */
@Immutable
public final class EmptyInputOutput implements JsonSerializable<EmptyInputOutput> {
    /**
     * Creates an instance of EmptyInputOutput class.
     */
    @Generated
    public EmptyInputOutput() {
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of EmptyInputOutput from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of EmptyInputOutput if the JsonReader was pointing to an instance of it, or null if it was pointing to JSON null.
     * @throws IOException If an error occurs while reading the EmptyInputOutput.
     */
    @Generated
    public static EmptyInputOutput fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            EmptyInputOutput deserializedEmptyInputOutput = new EmptyInputOutput();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                reader.skipChildren();
            }

            return deserializedEmptyInputOutput;
        });
    }
}
