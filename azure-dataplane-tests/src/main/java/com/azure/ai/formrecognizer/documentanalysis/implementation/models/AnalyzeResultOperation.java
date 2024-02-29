// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.ai.formrecognizer.documentanalysis.implementation.models;

import com.azure.core.annotation.Fluent;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Status and result of the analyze operation.
 */
@Fluent
public final class AnalyzeResultOperation implements JsonSerializable<AnalyzeResultOperation> {
    /*
     * Operation status.
     */
    private AnalyzeResultOperationStatus status;

    /*
     * Date and time (UTC) when the analyze operation was submitted.
     */
    private OffsetDateTime createdDateTime;

    /*
     * Date and time (UTC) when the status was last updated.
     */
    private OffsetDateTime lastUpdatedDateTime;

    /*
     * Encountered error during document analysis.
     */
    private Error error;

    /*
     * Document analysis result.
     */
    private AnalyzeResult analyzeResult;

    /**
     * Creates an instance of AnalyzeResultOperation class.
     */
    public AnalyzeResultOperation() {
    }

    /**
     * Get the status property: Operation status.
     * 
     * @return the status value.
     */
    public AnalyzeResultOperationStatus getStatus() {
        return this.status;
    }

    /**
     * Set the status property: Operation status.
     * 
     * @param status the status value to set.
     * @return the AnalyzeResultOperation object itself.
     */
    public AnalyzeResultOperation setStatus(AnalyzeResultOperationStatus status) {
        this.status = status;
        return this;
    }

    /**
     * Get the createdDateTime property: Date and time (UTC) when the analyze operation was submitted.
     * 
     * @return the createdDateTime value.
     */
    public OffsetDateTime getCreatedDateTime() {
        return this.createdDateTime;
    }

    /**
     * Set the createdDateTime property: Date and time (UTC) when the analyze operation was submitted.
     * 
     * @param createdDateTime the createdDateTime value to set.
     * @return the AnalyzeResultOperation object itself.
     */
    public AnalyzeResultOperation setCreatedDateTime(OffsetDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
        return this;
    }

    /**
     * Get the lastUpdatedDateTime property: Date and time (UTC) when the status was last updated.
     * 
     * @return the lastUpdatedDateTime value.
     */
    public OffsetDateTime getLastUpdatedDateTime() {
        return this.lastUpdatedDateTime;
    }

    /**
     * Set the lastUpdatedDateTime property: Date and time (UTC) when the status was last updated.
     * 
     * @param lastUpdatedDateTime the lastUpdatedDateTime value to set.
     * @return the AnalyzeResultOperation object itself.
     */
    public AnalyzeResultOperation setLastUpdatedDateTime(OffsetDateTime lastUpdatedDateTime) {
        this.lastUpdatedDateTime = lastUpdatedDateTime;
        return this;
    }

    /**
     * Get the error property: Encountered error during document analysis.
     * 
     * @return the error value.
     */
    public Error getError() {
        return this.error;
    }

    /**
     * Set the error property: Encountered error during document analysis.
     * 
     * @param error the error value to set.
     * @return the AnalyzeResultOperation object itself.
     */
    public AnalyzeResultOperation setError(Error error) {
        this.error = error;
        return this;
    }

    /**
     * Get the analyzeResult property: Document analysis result.
     * 
     * @return the analyzeResult value.
     */
    public AnalyzeResult getAnalyzeResult() {
        return this.analyzeResult;
    }

    /**
     * Set the analyzeResult property: Document analysis result.
     * 
     * @param analyzeResult the analyzeResult value to set.
     * @return the AnalyzeResultOperation object itself.
     */
    public AnalyzeResultOperation setAnalyzeResult(AnalyzeResult analyzeResult) {
        this.analyzeResult = analyzeResult;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("status", this.status == null ? null : this.status.toString());
        jsonWriter.writeStringField("createdDateTime",
            this.createdDateTime == null ? null : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(this.createdDateTime));
        jsonWriter.writeStringField("lastUpdatedDateTime",
            this.lastUpdatedDateTime == null
                ? null
                : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(this.lastUpdatedDateTime));
        jsonWriter.writeJsonField("error", this.error);
        jsonWriter.writeJsonField("analyzeResult", this.analyzeResult);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of AnalyzeResultOperation from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of AnalyzeResultOperation if the JsonReader was pointing to an instance of it, or null if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the AnalyzeResultOperation.
     */
    public static AnalyzeResultOperation fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            AnalyzeResultOperation deserializedAnalyzeResultOperation = new AnalyzeResultOperation();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("status".equals(fieldName)) {
                    deserializedAnalyzeResultOperation.status
                        = AnalyzeResultOperationStatus.fromString(reader.getString());
                } else if ("createdDateTime".equals(fieldName)) {
                    deserializedAnalyzeResultOperation.createdDateTime
                        = reader.getNullable(nonNullReader -> OffsetDateTime.parse(nonNullReader.getString()));
                } else if ("lastUpdatedDateTime".equals(fieldName)) {
                    deserializedAnalyzeResultOperation.lastUpdatedDateTime
                        = reader.getNullable(nonNullReader -> OffsetDateTime.parse(nonNullReader.getString()));
                } else if ("error".equals(fieldName)) {
                    deserializedAnalyzeResultOperation.error = Error.fromJson(reader);
                } else if ("analyzeResult".equals(fieldName)) {
                    deserializedAnalyzeResultOperation.analyzeResult = AnalyzeResult.fromJson(reader);
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedAnalyzeResultOperation;
        });
    }
}
