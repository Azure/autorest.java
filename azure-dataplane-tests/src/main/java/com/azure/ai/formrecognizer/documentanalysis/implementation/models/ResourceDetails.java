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
 * General information regarding the current resource.
 */
@Fluent
public final class ResourceDetails implements JsonSerializable<ResourceDetails> {
    /*
     * Details regarding custom document models.
     */
    private CustomDocumentModelsDetails customDocumentModels;

    /**
     * Creates an instance of ResourceDetails class.
     */
    public ResourceDetails() {
    }

    /**
     * Get the customDocumentModels property: Details regarding custom document models.
     * 
     * @return the customDocumentModels value.
     */
    public CustomDocumentModelsDetails getCustomDocumentModels() {
        return this.customDocumentModels;
    }

    /**
     * Set the customDocumentModels property: Details regarding custom document models.
     * 
     * @param customDocumentModels the customDocumentModels value to set.
     * @return the ResourceDetails object itself.
     */
    public ResourceDetails setCustomDocumentModels(CustomDocumentModelsDetails customDocumentModels) {
        this.customDocumentModels = customDocumentModels;
        return this;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeJsonField("customDocumentModels", this.customDocumentModels);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ResourceDetails from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of ResourceDetails if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the ResourceDetails.
     */
    public static ResourceDetails fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ResourceDetails deserializedResourceDetails = new ResourceDetails();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("customDocumentModels".equals(fieldName)) {
                    deserializedResourceDetails.customDocumentModels = CustomDocumentModelsDetails.fromJson(reader);
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedResourceDetails;
        });
    }
}
