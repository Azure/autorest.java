// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.longrunning.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.core.models.ResponseError;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.time.OffsetDateTime;

/**
 * The JobResult model.
 */
@Immutable
public final class JobResult implements JsonSerializable<JobResult> {
    /*
     * Universally Unique Identifier
     */
    @Generated
    private String id;

    /*
     * The status property.
     */
    @Generated
    private JobStatus status;

    /*
     * The createdDateTime property.
     */
    @Generated
    private OffsetDateTime createdDateTime;

    /*
     * The expirationDateTime property.
     */
    @Generated
    private OffsetDateTime expirationDateTime;

    /*
     * The lastUpdateDateTime property.
     */
    @Generated
    private OffsetDateTime lastUpdateDateTime;

    /*
     * The error property.
     */
    @Generated
    private ResponseError error;

    /*
     * The result property.
     */
    @Generated
    private JobResultResult result;

    /**
     * Creates an instance of JobResult class.
     */
    @Generated
    private JobResult() {
    }

    /**
     * Get the id property: Universally Unique Identifier.
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
    public JobStatus getStatus() {
        return this.status;
    }

    /**
     * Get the createdDateTime property: The createdDateTime property.
     * 
     * @return the createdDateTime value.
     */
    @Generated
    public OffsetDateTime getCreatedDateTime() {
        return this.createdDateTime;
    }

    /**
     * Get the expirationDateTime property: The expirationDateTime property.
     * 
     * @return the expirationDateTime value.
     */
    @Generated
    public OffsetDateTime getExpirationDateTime() {
        return this.expirationDateTime;
    }

    /**
     * Get the lastUpdateDateTime property: The lastUpdateDateTime property.
     * 
     * @return the lastUpdateDateTime value.
     */
    @Generated
    public OffsetDateTime getLastUpdateDateTime() {
        return this.lastUpdateDateTime;
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
    public JobResultResult getResult() {
        return this.result;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeJsonField("error", this.error);
        jsonWriter.writeJsonField("result", this.result);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of JobResult from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of JobResult if the JsonReader was pointing to an instance of it, or null if it was pointing
     * to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the JobResult.
     */
    @Generated
    public static JobResult fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            String id = null;
            JobStatus status = null;
            OffsetDateTime createdDateTime = null;
            OffsetDateTime expirationDateTime = null;
            OffsetDateTime lastUpdateDateTime = null;
            ResponseError error = null;
            JobResultResult result = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("id".equals(fieldName)) {
                    id = reader.getString();
                } else if ("status".equals(fieldName)) {
                    status = JobStatus.fromString(reader.getString());
                } else if ("createdDateTime".equals(fieldName)) {
                    createdDateTime
                        = reader.getNullable(nonNullReader -> OffsetDateTime.parse(nonNullReader.getString()));
                } else if ("expirationDateTime".equals(fieldName)) {
                    expirationDateTime
                        = reader.getNullable(nonNullReader -> OffsetDateTime.parse(nonNullReader.getString()));
                } else if ("lastUpdateDateTime".equals(fieldName)) {
                    lastUpdateDateTime
                        = reader.getNullable(nonNullReader -> OffsetDateTime.parse(nonNullReader.getString()));
                } else if ("error".equals(fieldName)) {
                    error = ResponseError.fromJson(reader);
                } else if ("result".equals(fieldName)) {
                    result = JobResultResult.fromJson(reader);
                } else {
                    reader.skipChildren();
                }
            }
            JobResult deserializedJobResult = new JobResult();
            deserializedJobResult.id = id;
            deserializedJobResult.status = status;
            deserializedJobResult.createdDateTime = createdDateTime;
            deserializedJobResult.expirationDateTime = expirationDateTime;
            deserializedJobResult.lastUpdateDateTime = lastUpdateDateTime;
            deserializedJobResult.error = error;
            deserializedJobResult.result = result;

            return deserializedJobResult;
        });
    }
}
