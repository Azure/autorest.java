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
import java.util.Map;

/**
 * Request body to authorize document model copy.
 */
@Fluent
public final class AuthorizeCopyRequest implements JsonSerializable<AuthorizeCopyRequest> {
    /*
     * Unique document model name.
     */
    private String modelId;

    /*
     * Document model description.
     */
    private String description;

    /*
     * List of key-value tag attributes associated with the document model.
     */
    private Map<String, String> tags;

    /**
     * Creates an instance of AuthorizeCopyRequest class.
     */
    public AuthorizeCopyRequest() {
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
     * @return the AuthorizeCopyRequest object itself.
     */
    public AuthorizeCopyRequest setModelId(String modelId) {
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
     * @return the AuthorizeCopyRequest object itself.
     */
    public AuthorizeCopyRequest setDescription(String description) {
        this.description = description;
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
     * @return the AuthorizeCopyRequest object itself.
     */
    public AuthorizeCopyRequest setTags(Map<String, String> tags) {
        this.tags = tags;
        return this;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("modelId", this.modelId);
        jsonWriter.writeStringField("description", this.description);
        jsonWriter.writeMapField("tags", this.tags, (writer, element) -> writer.writeString(element));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of AuthorizeCopyRequest from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of AuthorizeCopyRequest if the JsonReader was pointing to an instance of it, or null if it
     * was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the AuthorizeCopyRequest.
     */
    public static AuthorizeCopyRequest fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            AuthorizeCopyRequest deserializedAuthorizeCopyRequest = new AuthorizeCopyRequest();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("modelId".equals(fieldName)) {
                    deserializedAuthorizeCopyRequest.modelId = reader.getString();
                } else if ("description".equals(fieldName)) {
                    deserializedAuthorizeCopyRequest.description = reader.getString();
                } else if ("tags".equals(fieldName)) {
                    Map<String, String> tags = reader.readMap(reader1 -> reader1.getString());
                    deserializedAuthorizeCopyRequest.tags = tags;
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedAuthorizeCopyRequest;
        });
    }
}
