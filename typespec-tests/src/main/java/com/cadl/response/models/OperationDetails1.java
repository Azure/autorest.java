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
 * The OperationDetails1 model.
 */
@Immutable
public final class OperationDetails1 implements JsonSerializable<OperationDetails1> {

    /*
     * Operation ID
     */
    @Generated
    private final String operationId;

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
     * The result property.
     */
    @Generated
    private Resource result;

    /**
     * Creates an instance of OperationDetails1 class.
     *
     * @param operationId the operationId value to set.
     * @param status the status value to set.
     */
    @Generated
    private OperationDetails1(String operationId, OperationState status) {
        this.operationId = operationId;
        this.status = status;
    }

    /**
     * Get the operationId property: Operation ID.
     *
     * @return the operationId value.
     */
    @Generated
    public String getOperationId() {
        return this.operationId;
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
     * Get the result property: The result property.
     *
     * @return the result value.
     */
    @Generated
    public Resource getResult() {
        return this.result;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("operationId", this.operationId);
        jsonWriter.writeStringField("status", this.status == null ? null : this.status.toString());
        jsonWriter.writeFieldName("error");
        CoreToCodegenBridgeUtils.responseErrorToJson(jsonWriter, this.error);
        jsonWriter.writeJsonField("result", this.result);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of OperationDetails1 from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of OperationDetails1 if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the OperationDetails1.
     */
    public static OperationDetails1 fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            String operationId = null;
            OperationState status = null;
            ResponseError error = null;
            Resource result = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();
                if ("operationId".equals(fieldName)) {
                    operationId = reader.getString();
                } else if ("status".equals(fieldName)) {
                    status = OperationState.fromString(reader.getString());
                } else if ("error".equals(fieldName)) {
                    error = CoreToCodegenBridgeUtils.responseErrorFromJson(reader);
                } else if ("result".equals(fieldName)) {
                    result = Resource.fromJson(reader);
                } else {
                    reader.skipChildren();
                }
            }
            OperationDetails1 deserializedOperationDetails1 = new OperationDetails1(operationId, status);
            deserializedOperationDetails1.error = error;
            deserializedOperationDetails1.result = result;
            return deserializedOperationDetails1;
        });
    }
}
