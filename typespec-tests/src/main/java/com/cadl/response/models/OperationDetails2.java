// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.response.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.core.models.ResponseError;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import com.cadl.response.implementation.CoreToCodegenBridgeUtils;
import java.io.IOException;

/**
 * The OperationDetails2 model.
 */
@Immutable
public final class OperationDetails2 implements JsonSerializable<OperationDetails2> {
    /*
     * Operation ID
     */
    @Generated
    private final String id;

    /*
     * The status property.
     */
    @Generated
    private final OperationState status;

    /*
     * The error property.
     */
    @Generated
    private ResponseError error;

    /*
     * The lroResult property.
     */
    @Generated
    private Resource lroResult;

    /**
     * Creates an instance of OperationDetails2 class.
     * 
     * @param id the id value to set.
     * @param status the status value to set.
     */
    @Generated
    private OperationDetails2(String id, OperationState status) {
        this.id = id;
        this.status = status;
    }

    /**
     * Get the id property: Operation ID.
     * 
     * @return the id value.
     */
    @Generated
    public String getId() {
        return this.id;
    }

    /**
     * Get the status property: The status property.
     * 
     * @return the status value.
     */
    @Generated
    public OperationState getStatus() {
        return this.status;
    }

    /**
     * Get the error property: The error property.
     * 
     * @return the error value.
     */
    @Generated
    public ResponseError getError() {
        return this.error;
    }

    /**
     * Get the lroResult property: The lroResult property.
     * 
     * @return the lroResult value.
     */
    @Generated
    public Resource getLroResult() {
        return this.lroResult;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("id", this.id);
        jsonWriter.writeStringField("status", this.status == null ? null : this.status.toString());
        jsonWriter.writeFieldName("error");
        CoreToCodegenBridgeUtils.responseErrorToJson(jsonWriter, this.error);
        jsonWriter.writeJsonField("lroResult", this.lroResult);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of OperationDetails2 from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of OperationDetails2 if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the OperationDetails2.
     */
    public static OperationDetails2 fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            String id = null;
            OperationState status = null;
            ResponseError error = null;
            Resource lroResult = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("id".equals(fieldName)) {
                    id = reader.getString();
                } else if ("status".equals(fieldName)) {
                    status = OperationState.fromString(reader.getString());
                } else if ("error".equals(fieldName)) {
                    error = CoreToCodegenBridgeUtils.responseErrorFromJson(reader);
                } else if ("lroResult".equals(fieldName)) {
                    lroResult = Resource.fromJson(reader);
                } else {
                    reader.skipChildren();
                }
            }
            OperationDetails2 deserializedOperationDetails2 = new OperationDetails2(id, status);
            deserializedOperationDetails2.error = error;
            deserializedOperationDetails2.lroResult = lroResult;

            return deserializedOperationDetails2;
        });
    }
}
