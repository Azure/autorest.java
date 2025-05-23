// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.ai.formrecognizer.documentanalysis.implementation.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
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
 * Document model summary.
 */
@Fluent
public final class DocumentModelSummary implements JsonSerializable<DocumentModelSummary> {
    /*
     * Unique document model name.
     */
    @Generated
    private String modelId;

    /*
     * Document model description.
     */
    @Generated
    private String description;

    /*
     * Date and time (UTC) when the document model was created.
     */
    @Generated
    private OffsetDateTime createdDateTime;

    /*
     * API version used to create this document model.
     */
    @Generated
    private String apiVersion;

    /*
     * List of key-value tag attributes associated with the document model.
     */
    @Generated
    private Map<String, String> tags;

    /**
     * Creates an instance of DocumentModelSummary class.
     */
    @Generated
    public DocumentModelSummary() {
    }

    /**
     * Get the modelId property: Unique document model name.
     * 
     * @return the modelId value.
     */
    @Generated
    public String getModelId() {
        return this.modelId;
    }

    /**
     * Set the modelId property: Unique document model name.
     * 
     * @param modelId the modelId value to set.
     * @return the DocumentModelSummary object itself.
     */
    @Generated
    public DocumentModelSummary setModelId(String modelId) {
        this.modelId = modelId;
        return this;
    }

    /**
     * Get the description property: Document model description.
     * 
     * @return the description value.
     */
    @Generated
    public String getDescription() {
        return this.description;
    }

    /**
     * Set the description property: Document model description.
     * 
     * @param description the description value to set.
     * @return the DocumentModelSummary object itself.
     */
    @Generated
    public DocumentModelSummary setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Get the createdDateTime property: Date and time (UTC) when the document model was created.
     * 
     * @return the createdDateTime value.
     */
    @Generated
    public OffsetDateTime getCreatedDateTime() {
        return this.createdDateTime;
    }

    /**
     * Set the createdDateTime property: Date and time (UTC) when the document model was created.
     * 
     * @param createdDateTime the createdDateTime value to set.
     * @return the DocumentModelSummary object itself.
     */
    @Generated
    public DocumentModelSummary setCreatedDateTime(OffsetDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
        return this;
    }

    /**
     * Get the apiVersion property: API version used to create this document model.
     * 
     * @return the apiVersion value.
     */
    @Generated
    public String getApiVersion() {
        return this.apiVersion;
    }

    /**
     * Set the apiVersion property: API version used to create this document model.
     * 
     * @param apiVersion the apiVersion value to set.
     * @return the DocumentModelSummary object itself.
     */
    @Generated
    public DocumentModelSummary setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
        return this;
    }

    /**
     * Get the tags property: List of key-value tag attributes associated with the document model.
     * 
     * @return the tags value.
     */
    @Generated
    public Map<String, String> getTags() {
        return this.tags;
    }

    /**
     * Set the tags property: List of key-value tag attributes associated with the document model.
     * 
     * @param tags the tags value to set.
     * @return the DocumentModelSummary object itself.
     */
    @Generated
    public DocumentModelSummary setTags(Map<String, String> tags) {
        this.tags = tags;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("modelId", this.modelId);
        jsonWriter.writeStringField("createdDateTime",
            this.createdDateTime == null ? null : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(this.createdDateTime));
        jsonWriter.writeStringField("description", this.description);
        jsonWriter.writeStringField("apiVersion", this.apiVersion);
        jsonWriter.writeMapField("tags", this.tags, (writer, element) -> writer.writeString(element));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of DocumentModelSummary from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of DocumentModelSummary if the JsonReader was pointing to an instance of it, or null if it
     * was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the DocumentModelSummary.
     */
    @Generated
    public static DocumentModelSummary fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            DocumentModelSummary deserializedDocumentModelSummary = new DocumentModelSummary();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("modelId".equals(fieldName)) {
                    deserializedDocumentModelSummary.modelId = reader.getString();
                } else if ("createdDateTime".equals(fieldName)) {
                    deserializedDocumentModelSummary.createdDateTime = reader
                        .getNullable(nonNullReader -> CoreUtils.parseBestOffsetDateTime(nonNullReader.getString()));
                } else if ("description".equals(fieldName)) {
                    deserializedDocumentModelSummary.description = reader.getString();
                } else if ("apiVersion".equals(fieldName)) {
                    deserializedDocumentModelSummary.apiVersion = reader.getString();
                } else if ("tags".equals(fieldName)) {
                    Map<String, String> tags = reader.readMap(reader1 -> reader1.getString());
                    deserializedDocumentModelSummary.tags = tags;
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedDocumentModelSummary;
        });
    }
}
