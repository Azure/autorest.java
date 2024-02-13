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
import java.util.List;
import java.util.Map;

/**
 * Request body to create a composed document model from component document models.
 */
@Fluent
public final class ComposeDocumentModelRequest implements JsonSerializable<ComposeDocumentModelRequest> {
    /*
     * Unique document model name.
     */
    private String modelId;

    /*
     * Document model description.
     */
    private String description;

    /*
     * List of component document models to compose.
     */
    private List<ComponentDocumentModelDetails> componentModels;

    /*
     * List of key-value tag attributes associated with the document model.
     */
    private Map<String, String> tags;

    /**
     * Creates an instance of ComposeDocumentModelRequest class.
     */
    public ComposeDocumentModelRequest() {
    }

    /**
     * Get the modelId property: Unique document model name.
     * 
     * @return the modelId value.
     */
    public String getModelId() {
        return this.modelId;
    }

    /**
     * Set the modelId property: Unique document model name.
     * 
     * @param modelId the modelId value to set.
     * @return the ComposeDocumentModelRequest object itself.
     */
    public ComposeDocumentModelRequest setModelId(String modelId) {
        this.modelId = modelId;
        return this;
    }

    /**
     * Get the description property: Document model description.
     * 
     * @return the description value.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Set the description property: Document model description.
     * 
     * @param description the description value to set.
     * @return the ComposeDocumentModelRequest object itself.
     */
    public ComposeDocumentModelRequest setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Get the componentModels property: List of component document models to compose.
     * 
     * @return the componentModels value.
     */
    public List<ComponentDocumentModelDetails> getComponentModels() {
        return this.componentModels;
    }

    /**
     * Set the componentModels property: List of component document models to compose.
     * 
     * @param componentModels the componentModels value to set.
     * @return the ComposeDocumentModelRequest object itself.
     */
    public ComposeDocumentModelRequest setComponentModels(List<ComponentDocumentModelDetails> componentModels) {
        this.componentModels = componentModels;
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
     * @return the ComposeDocumentModelRequest object itself.
     */
    public ComposeDocumentModelRequest setTags(Map<String, String> tags) {
        this.tags = tags;
        return this;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("modelId", this.modelId);
        jsonWriter.writeArrayField("componentModels", this.componentModels,
            (writer, element) -> writer.writeJson(element));
        jsonWriter.writeStringField("description", this.description);
        jsonWriter.writeMapField("tags", this.tags, (writer, element) -> writer.writeString(element));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ComposeDocumentModelRequest from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of ComposeDocumentModelRequest if the JsonReader was pointing to an instance of it, or null
     * if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the ComposeDocumentModelRequest.
     */
    public static ComposeDocumentModelRequest fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ComposeDocumentModelRequest deserializedComposeDocumentModelRequest = new ComposeDocumentModelRequest();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("modelId".equals(fieldName)) {
                    deserializedComposeDocumentModelRequest.modelId = reader.getString();
                } else if ("componentModels".equals(fieldName)) {
                    List<ComponentDocumentModelDetails> componentModels
                        = reader.readArray(reader1 -> ComponentDocumentModelDetails.fromJson(reader1));
                    deserializedComposeDocumentModelRequest.componentModels = componentModels;
                } else if ("description".equals(fieldName)) {
                    deserializedComposeDocumentModelRequest.description = reader.getString();
                } else if ("tags".equals(fieldName)) {
                    Map<String, String> tags = reader.readMap(reader1 -> reader1.getString());
                    deserializedComposeDocumentModelRequest.tags = tags;
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedComposeDocumentModelRequest;
        });
    }
}
