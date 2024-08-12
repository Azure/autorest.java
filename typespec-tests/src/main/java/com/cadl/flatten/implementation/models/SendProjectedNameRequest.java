// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.flatten.implementation.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * The SendProjectedNameRequest model.
 */
@Immutable
public final class SendProjectedNameRequest implements JsonSerializable<SendProjectedNameRequest> {
    /*
     * The file_id property.
     */
    @Generated
    private final String fileIdentifier;

    /**
     * Creates an instance of SendProjectedNameRequest class.
     * 
     * @param fileIdentifier the fileIdentifier value to set.
     */
    @Generated
    public SendProjectedNameRequest(String fileIdentifier) {
        this.fileIdentifier = fileIdentifier;
    }

    /**
     * Get the fileIdentifier property: The file_id property.
     * 
     * @return the fileIdentifier value.
     */
    @Generated
    public String getFileIdentifier() {
        return this.fileIdentifier;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("file_id", this.fileIdentifier);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of SendProjectedNameRequest from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of SendProjectedNameRequest if the JsonReader was pointing to an instance of it, or null if
     * it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the SendProjectedNameRequest.
     */
    @Generated
    public static SendProjectedNameRequest fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            long foundTracker = 0;
            String fileIdentifier = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("file_id".equals(fieldName)) {
                    fileIdentifier = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }
            return new SendProjectedNameRequest(fileIdentifier);
        });
    }
}
