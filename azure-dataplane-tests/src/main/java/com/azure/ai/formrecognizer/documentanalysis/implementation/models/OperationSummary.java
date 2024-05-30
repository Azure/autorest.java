// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.ai.formrecognizer.documentanalysis.implementation.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.util.CoreUtils;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * Operation info.
 */
@Fluent
public final class OperationSummary implements JsonSerializable<OperationSummary> {
    /*
     * Operation ID
     */
    private String operationId;

    /*
     * Operation status.
     */
    private OperationStatus status;

    /*
     * Operation progress (0-100).
     */
    private Integer percentCompleted;

    /*
     * Date and time (UTC) when the operation was created.
     */
    private OffsetDateTime createdDateTime;

    /*
     * Date and time (UTC) when the status was last updated.
     */
    private OffsetDateTime lastUpdatedDateTime;

    /*
     * Type of operation.
     */
    private OperationKind kind;

    /*
     * URL of the resource targeted by this operation.
     */
    private String resourceLocation;

    /*
     * API version used to create this operation.
     */
    private String apiVersion;

    /*
     * List of key-value tag attributes associated with the document model.
     */
    private Map<String, String> tags;

    /**
     * Creates an instance of OperationSummary class.
     */
    public OperationSummary() {
    }

    /**
     * Get the operationId property: Operation ID.
     * 
     * @return the operationId value.
     */
    public String getOperationId() {
        return this.operationId;
    }

    /**
     * Set the operationId property: Operation ID.
     * 
     * @param operationId the operationId value to set.
     * @return the OperationSummary object itself.
     */
    public OperationSummary setOperationId(String operationId) {
        this.operationId = operationId;
        return this;
    }

    /**
     * Get the status property: Operation status.
     * 
     * @return the status value.
     */
    public OperationStatus getStatus() {
        return this.status;
    }

    /**
     * Set the status property: Operation status.
     * 
     * @param status the status value to set.
     * @return the OperationSummary object itself.
     */
    public OperationSummary setStatus(OperationStatus status) {
        this.status = status;
        return this;
    }

    /**
     * Get the percentCompleted property: Operation progress (0-100).
     * 
     * @return the percentCompleted value.
     */
    public Integer getPercentCompleted() {
        return this.percentCompleted;
    }

    /**
     * Set the percentCompleted property: Operation progress (0-100).
     * 
     * @param percentCompleted the percentCompleted value to set.
     * @return the OperationSummary object itself.
     */
    public OperationSummary setPercentCompleted(Integer percentCompleted) {
        this.percentCompleted = percentCompleted;
        return this;
    }

    /**
     * Get the createdDateTime property: Date and time (UTC) when the operation was created.
     * 
     * @return the createdDateTime value.
     */
    public OffsetDateTime getCreatedDateTime() {
        return this.createdDateTime;
    }

    /**
     * Set the createdDateTime property: Date and time (UTC) when the operation was created.
     * 
     * @param createdDateTime the createdDateTime value to set.
     * @return the OperationSummary object itself.
     */
    public OperationSummary setCreatedDateTime(OffsetDateTime createdDateTime) {
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
     * @return the OperationSummary object itself.
     */
    public OperationSummary setLastUpdatedDateTime(OffsetDateTime lastUpdatedDateTime) {
        this.lastUpdatedDateTime = lastUpdatedDateTime;
        return this;
    }

    /**
     * Get the kind property: Type of operation.
     * 
     * @return the kind value.
     */
    public OperationKind getKind() {
        return this.kind;
    }

    /**
     * Set the kind property: Type of operation.
     * 
     * @param kind the kind value to set.
     * @return the OperationSummary object itself.
     */
    public OperationSummary setKind(OperationKind kind) {
        this.kind = kind;
        return this;
    }

    /**
     * Get the resourceLocation property: URL of the resource targeted by this operation.
     * 
     * @return the resourceLocation value.
     */
    public String getResourceLocation() {
        return this.resourceLocation;
    }

    /**
     * Set the resourceLocation property: URL of the resource targeted by this operation.
     * 
     * @param resourceLocation the resourceLocation value to set.
     * @return the OperationSummary object itself.
     */
    public OperationSummary setResourceLocation(String resourceLocation) {
        this.resourceLocation = resourceLocation;
        return this;
    }

    /**
     * Get the apiVersion property: API version used to create this operation.
     * 
     * @return the apiVersion value.
     */
    public String getApiVersion() {
        return this.apiVersion;
    }

    /**
     * Set the apiVersion property: API version used to create this operation.
     * 
     * @param apiVersion the apiVersion value to set.
     * @return the OperationSummary object itself.
     */
    public OperationSummary setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
        return this;
    }

    /**
     * Get the tags property: List of key-value tag attributes associated with the document model.
     * 
     * @return the tags value.
     */
    public Map<String, String> getTags() {
        return this.tags;
    }

    /**
     * Set the tags property: List of key-value tag attributes associated with the document model.
     * 
     * @param tags the tags value to set.
     * @return the OperationSummary object itself.
     */
    public OperationSummary setTags(Map<String, String> tags) {
        this.tags = tags;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("operationId", this.operationId);
        jsonWriter.writeStringField("status", this.status == null ? null : this.status.toString());
        jsonWriter.writeStringField("createdDateTime",
            this.createdDateTime == null ? null : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(this.createdDateTime));
        jsonWriter.writeStringField("lastUpdatedDateTime",
            this.lastUpdatedDateTime == null
                ? null
                : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(this.lastUpdatedDateTime));
        jsonWriter.writeStringField("kind", this.kind == null ? null : this.kind.toString());
        jsonWriter.writeStringField("resourceLocation", this.resourceLocation);
        jsonWriter.writeNumberField("percentCompleted", this.percentCompleted);
        jsonWriter.writeStringField("apiVersion", this.apiVersion);
        jsonWriter.writeMapField("tags", this.tags, (writer, element) -> writer.writeString(element));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of OperationSummary from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of OperationSummary if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the OperationSummary.
     */
    public static OperationSummary fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            OperationSummary deserializedOperationSummary = new OperationSummary();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("operationId".equals(fieldName)) {
                    deserializedOperationSummary.operationId = reader.getString();
                } else if ("status".equals(fieldName)) {
                    deserializedOperationSummary.status = OperationStatus.fromString(reader.getString());
                } else if ("createdDateTime".equals(fieldName)) {
                    deserializedOperationSummary.createdDateTime = reader
                        .getNullable(nonNullReader -> CoreUtils.parseBestOffsetDateTime(nonNullReader.getString()));
                } else if ("lastUpdatedDateTime".equals(fieldName)) {
                    deserializedOperationSummary.lastUpdatedDateTime = reader
                        .getNullable(nonNullReader -> CoreUtils.parseBestOffsetDateTime(nonNullReader.getString()));
                } else if ("kind".equals(fieldName)) {
                    deserializedOperationSummary.kind = OperationKind.fromString(reader.getString());
                } else if ("resourceLocation".equals(fieldName)) {
                    deserializedOperationSummary.resourceLocation = reader.getString();
                } else if ("percentCompleted".equals(fieldName)) {
                    deserializedOperationSummary.percentCompleted = reader.getNullable(JsonReader::getInt);
                } else if ("apiVersion".equals(fieldName)) {
                    deserializedOperationSummary.apiVersion = reader.getString();
                } else if ("tags".equals(fieldName)) {
                    Map<String, String> tags = reader.readMap(reader1 -> reader1.getString());
                    deserializedOperationSummary.tags = tags;
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedOperationSummary;
        });
    }
}
