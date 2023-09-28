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

/** Empty model used in operation return type. */
@Immutable
public final class EmptyOutput implements JsonSerializable<EmptyOutput> {
    /** Creates an instance of EmptyOutput class. */
    @Generated
    private EmptyOutput() {}

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of EmptyOutput from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of EmptyOutput if the JsonReader was pointing to an instance of it, or null if it was
     *     pointing to JSON null.
     * @throws IOException If an error occurs while reading the EmptyOutput.
     */
    public static EmptyOutput fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(
                reader -> {
                    EmptyOutput deserializedEmptyOutput = new EmptyOutput();
                    while (reader.nextToken() != JsonToken.END_OBJECT) {
                        String fieldName = reader.getFieldName();
                        reader.nextToken();

                        reader.skipChildren();
                    }

                    return deserializedEmptyOutput;
                });
    }
}
