// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.model.inheritance.singlediscriminator.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.json.JsonReader;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * The second level legacy model in polymorphic single level inheritance.
 */
@Immutable
public final class TRex extends Dinosaur {
    /**
     * Creates an instance of TRex class.
     * 
     * @param size the size value to set.
     */
    @Generated
    private TRex(int size) {
        super(size);
        setKind("t-rex");
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeIntField("size", getSize());
        jsonWriter.writeStringField("kind", getKind());
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of TRex from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of TRex if the JsonReader was pointing to an instance of it, or null if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the TRex.
     */
    @Generated
    public static TRex fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            int size = 0;
            String kind = "t-rex";
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("size".equals(fieldName)) {
                    size = reader.getInt();
                } else if ("kind".equals(fieldName)) {
                    kind = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }
            TRex deserializedTRex = new TRex(size);
            deserializedTRex.setKind(kind);

            return deserializedTRex;
        });
    }
}
