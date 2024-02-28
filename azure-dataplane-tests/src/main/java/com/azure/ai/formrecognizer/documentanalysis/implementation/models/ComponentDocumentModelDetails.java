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

/**
 * A component of a composed document model.
 */
@Fluent
public final class ComponentDocumentModelDetails implements JsonSerializable<ComponentDocumentModelDetails> {
    /*
     * Unique document model name.
     */
    private String modelId;

    /**
     * Creates an instance of ComponentDocumentModelDetails class.
     */
    public ComponentDocumentModelDetails() {
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
     * @return the ComponentDocumentModelDetails object itself.
     */
    public ComponentDocumentModelDetails setModelId(String modelId) {
        this.modelId = modelId;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("modelId", this.modelId);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ComponentDocumentModelDetails from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of ComponentDocumentModelDetails if the JsonReader was pointing to an instance of it, or null if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the ComponentDocumentModelDetails.
     */
    public static ComponentDocumentModelDetails fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ComponentDocumentModelDetails deserializedComponentDocumentModelDetails
                = new ComponentDocumentModelDetails();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("modelId".equals(fieldName)) {
                    deserializedComponentDocumentModelDetails.modelId = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedComponentDocumentModelDetails;
        });
    }
}
