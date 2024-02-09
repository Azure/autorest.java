// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.projection.projectedname.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;

import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;

import java.io.IOException;

/**
 * The ClientProjectedNameModel model.
 */
@Immutable
public final class ClientProjectedNameModel implements JsonSerializable<ClientProjectedNameModel> {
    /*
     * Pass in true
     */
    @Generated
    private final boolean clientName;

    /**
     * Creates an instance of ClientProjectedNameModel class.
     * 
     * @param clientName the clientName value to set.
     */
    @Generated
    public ClientProjectedNameModel(boolean clientName) {
        this.clientName = clientName;
    }

    /**
     * Get the clientName property: Pass in true.
     * 
     * @return the clientName value.
     */
    @Generated
    public boolean isClientName() {
        return this.clientName;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeBooleanField("defaultName", this.clientName);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ClientProjectedNameModel from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of ClientProjectedNameModel if the JsonReader was pointing to an instance of it, or null if
     * it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the ClientProjectedNameModel.
     */
    public static ClientProjectedNameModel fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            boolean clientName = false;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("defaultName".equals(fieldName)) {
                    clientName = reader.getBoolean();
                } else {
                    reader.skipChildren();
                }
            }
            return new ClientProjectedNameModel(clientName);
        });
    }
}
