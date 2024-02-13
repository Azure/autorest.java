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
import java.util.Map;

/**
 * Get Operation response object.
 */
@Fluent
public class OperationDetails implements JsonSerializable<OperationDetails> {
    /*
     * Type of operation.
     */
    private String kind;

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

    /*
     * Encountered error.
     */
    private Error error;

    /**
     * Creates an instance of OperationDetails class.
     */
    public OperationDetails() {
    }

    /**
     * Get the kind property: Type of operation.
     * 
     * @return the kind value.
     */
    public String getKind() {
        return this.kind;
    }

    /**
     * Set the kind property: Type of operation.
     * 
     * @param kind the kind value to set.
     * @return the OperationDetails object itself.
     */
    OperationDetails setKind(String kind) {
        this.kind = kind;
        return this;
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
     * @return the OperationDetails object itself.
     */
    public OperationDetails setOperationId(String operationId) {
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
     * @return the OperationDetails object itself.
     */
    public OperationDetails setStatus(OperationStatus status) {
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
     * @return the OperationDetails object itself.
     */
    public OperationDetails setPercentCompleted(Integer percentCompleted) {
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
     * @return the OperationDetails object itself.
     */
    public OperationDetails setCreatedDateTime(OffsetDateTime createdDateTime) {
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
     * @return the OperationDetails object itself.
     */
    public OperationDetails setLastUpdatedDateTime(OffsetDateTime lastUpdatedDateTime) {
        this.lastUpdatedDateTime = lastUpdatedDateTime;
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
     * @return the OperationDetails object itself.
     */
    public OperationDetails setResourceLocation(String resourceLocation) {
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
     * @return the OperationDetails object itself.
     */
    public OperationDetails setApiVersion(String apiVersion) {
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
     * @return the OperationDetails object itself.
     */
    public OperationDetails setTags(Map<String, String> tags) {
        this.tags = tags;
        return this;
    }

    /**
     * Get the error property: Encountered error.
     * 
     * @return the error value.
     */
    public Error getError() {
        return this.error;
    }

    /**
     * Set the error property: Encountered error.
     * 
     * @param error the error value to set.
     * @return the OperationDetails object itself.
     */
    public OperationDetails setError(Error error) {
        this.error = error;
        return this;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("operationId", this.operationId);
        jsonWriter.writeStringField("status", this.status == null ? null : this.status.toString());
        jsonWriter.writeStringField("createdDateTime",
            this.createdDateTime == null ? null : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(this.createdDateTime));
        jsonWriter.writeStringField("lastUpdatedDateTime", this.lastUpdatedDateTime == null ? null
            : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(this.lastUpdatedDateTime));
        jsonWriter.writeStringField("resourceLocation", this.resourceLocation);
        jsonWriter.writeStringField("kind", this.kind);
        jsonWriter.writeNumberField("percentCompleted", this.percentCompleted);
        jsonWriter.writeStringField("apiVersion", this.apiVersion);
        jsonWriter.writeMapField("tags", this.tags, (writer, element) -> writer.writeString(element));
        jsonWriter.writeJsonField("error", this.error);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of OperationDetails from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of OperationDetails if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the OperationDetails.
     */
    public static OperationDetails fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            String discriminatorValue = null;
            try (JsonReader readerToUse = reader.bufferObject()) {
                readerToUse.nextToken(); // Prepare for reading
                while (readerToUse.nextToken() != JsonToken.END_OBJECT) {
                    String fieldName = readerToUse.getFieldName();
                    readerToUse.nextToken();
                    if ("kind".equals(fieldName)) {
                        discriminatorValue = readerToUse.getString();
                        break;
                    } else {
                        readerToUse.skipChildren();
                    }
                }
                // Use the discriminator value to determine which subtype should be deserialized.
                if ("documentModelBuild".equals(discriminatorValue)) {
                    return DocumentModelBuildOperationDetails.fromJson(readerToUse.reset());
                } else if ("documentModelCompose".equals(discriminatorValue)) {
                    return DocumentModelComposeOperationDetails.fromJson(readerToUse.reset());
                } else if ("documentModelCopyTo".equals(discriminatorValue)) {
                    return DocumentModelCopyToOperationDetails.fromJson(readerToUse.reset());
                } else {
                    return fromJsonKnownDiscriminator(readerToUse.reset());
                }
            }
        });
    }

    static OperationDetails fromJsonKnownDiscriminator(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            OperationDetails deserializedOperationDetails = new OperationDetails();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("operationId".equals(fieldName)) {
                    deserializedOperationDetails.operationId = reader.getString();
                } else if ("status".equals(fieldName)) {
                    deserializedOperationDetails.status = OperationStatus.fromString(reader.getString());
                } else if ("createdDateTime".equals(fieldName)) {
                    deserializedOperationDetails.createdDateTime
                        = reader.getNullable(nonNullReader -> OffsetDateTime.parse(nonNullReader.getString()));
                } else if ("lastUpdatedDateTime".equals(fieldName)) {
                    deserializedOperationDetails.lastUpdatedDateTime
                        = reader.getNullable(nonNullReader -> OffsetDateTime.parse(nonNullReader.getString()));
                } else if ("resourceLocation".equals(fieldName)) {
                    deserializedOperationDetails.resourceLocation = reader.getString();
                } else if ("kind".equals(fieldName)) {
                    deserializedOperationDetails.kind = reader.getString();
                } else if ("percentCompleted".equals(fieldName)) {
                    deserializedOperationDetails.percentCompleted = reader.getNullable(JsonReader::getInt);
                } else if ("apiVersion".equals(fieldName)) {
                    deserializedOperationDetails.apiVersion = reader.getString();
                } else if ("tags".equals(fieldName)) {
                    Map<String, String> tags = reader.readMap(reader1 -> reader1.getString());
                    deserializedOperationDetails.tags = tags;
                } else if ("error".equals(fieldName)) {
                    deserializedOperationDetails.error = Error.fromJson(reader);
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedOperationDetails;
        });
    }
}
