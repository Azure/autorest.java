// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.
package com._specs_.azure.core.lro.rpc.legacy.models;

import com._specs_.azure.core.lro.rpc.legacy.implementation.CoreToCodegenBridgeUtils;
import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.core.models.ResponseError;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * A response containing error details.
 */
@Immutable
public final class ErrorResponse implements JsonSerializable<ErrorResponse> {

    /*
     * The error object.
     */
    @Generated
    private final ResponseError error;

    /**
     * Creates an instance of ErrorResponse class.
     *
     * @param error the error value to set.
     */
    @Generated
    private ErrorResponse(ResponseError error) {
        this.error = error;
    }

    /**
     * Get the error property: The error object.
     *
     * @return the error value.
     */
    @Generated
    public ResponseError getError() {
        return this.error;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeFieldName("error");
        CoreToCodegenBridgeUtils.responseErrorToJson(jsonWriter, this.error);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ErrorResponse from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of ErrorResponse if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the ErrorResponse.
     */
    public static ErrorResponse fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ResponseError error = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();
                if ("error".equals(fieldName)) {
                    error = CoreToCodegenBridgeUtils.responseErrorFromJson(reader);
                } else {
                    reader.skipChildren();
                }
            }
            return new ErrorResponse(error);
        });
    }
}
