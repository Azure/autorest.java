// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.
package com.azure.data.schemaregistry.implementation.models;

import com.azure.core.annotation.Fluent;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * An error response returned from Azure Schema Registry service.
 */
@Fluent
public final class Error implements JsonSerializable<Error> {

    /*
     * Error response returned from Azure Schema Registry service.
     */
    private ErrorDetail error;

    /**
     * Creates an instance of Error class.
     */
    public Error() {
    }

    /**
     * Get the error property: Error response returned from Azure Schema Registry service.
     *
     * @return the error value.
     */
    public ErrorDetail getError() {
        return this.error;
    }

    /**
     * Set the error property: Error response returned from Azure Schema Registry service.
     *
     * @param error the error value to set.
     * @return the Error object itself.
     */
    public Error setError(ErrorDetail error) {
        this.error = error;
        return this;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeJsonField("error", this.error);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of Error from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of Error if the JsonReader was pointing to an instance of it, or null if it was pointing to
     * JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the Error.
     */
    public static Error fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            Error deserializedError = new Error();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();
                if ("error".equals(fieldName)) {
                    deserializedError.error = ErrorDetail.fromJson(reader);
                } else {
                    reader.skipChildren();
                }
            }
            return deserializedError;
        });
    }
}
