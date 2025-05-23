// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.ai.formrecognizer.documentanalysis.implementation.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * Details regarding custom document models.
 */
@Fluent
public final class CustomDocumentModelsDetails implements JsonSerializable<CustomDocumentModelsDetails> {
    /*
     * Number of custom document models in the current resource.
     */
    @Generated
    private int count;

    /*
     * Maximum number of custom document models supported in the current resource.
     */
    @Generated
    private int limit;

    /**
     * Creates an instance of CustomDocumentModelsDetails class.
     */
    @Generated
    public CustomDocumentModelsDetails() {
    }

    /**
     * Get the count property: Number of custom document models in the current resource.
     * 
     * @return the count value.
     */
    @Generated
    public int getCount() {
        return this.count;
    }

    /**
     * Set the count property: Number of custom document models in the current resource.
     * 
     * @param count the count value to set.
     * @return the CustomDocumentModelsDetails object itself.
     */
    @Generated
    public CustomDocumentModelsDetails setCount(int count) {
        this.count = count;
        return this;
    }

    /**
     * Get the limit property: Maximum number of custom document models supported in the current resource.
     * 
     * @return the limit value.
     */
    @Generated
    public int getLimit() {
        return this.limit;
    }

    /**
     * Set the limit property: Maximum number of custom document models supported in the current resource.
     * 
     * @param limit the limit value to set.
     * @return the CustomDocumentModelsDetails object itself.
     */
    @Generated
    public CustomDocumentModelsDetails setLimit(int limit) {
        this.limit = limit;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeIntField("count", this.count);
        jsonWriter.writeIntField("limit", this.limit);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of CustomDocumentModelsDetails from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of CustomDocumentModelsDetails if the JsonReader was pointing to an instance of it, or null
     * if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the CustomDocumentModelsDetails.
     */
    @Generated
    public static CustomDocumentModelsDetails fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            CustomDocumentModelsDetails deserializedCustomDocumentModelsDetails = new CustomDocumentModelsDetails();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("count".equals(fieldName)) {
                    deserializedCustomDocumentModelsDetails.count = reader.getInt();
                } else if ("limit".equals(fieldName)) {
                    deserializedCustomDocumentModelsDetails.limit = reader.getInt();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedCustomDocumentModelsDetails;
        });
    }
}
