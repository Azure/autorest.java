// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.model.inheritance.enumdiscriminator.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.json.JsonReader;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * Cobra model.
 */
@Immutable
public final class Cobra extends Snake {
    /**
     * Creates an instance of Cobra class.
     * 
     * @param length the length value to set.
     */
    @Generated
    public Cobra(int length) {
        super(length);
        setKind("cobra");
    }

    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeIntField("length", getLength());
        jsonWriter.writeStringField("kind", getKind());
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of Cobra from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of Cobra if the JsonReader was pointing to an instance of it, or null if it was pointing to
     * JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the Cobra.
     */
    @Generated
    public static Cobra fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            int length = 0;
            String kind = "cobra";
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("length".equals(fieldName)) {
                    length = reader.getInt();
                } else if ("kind".equals(fieldName)) {
                    kind = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }
            Cobra deserializedCobra = new Cobra(length);
            deserializedCobra.setKind(kind);

            return deserializedCobra;
        });
    }
}
