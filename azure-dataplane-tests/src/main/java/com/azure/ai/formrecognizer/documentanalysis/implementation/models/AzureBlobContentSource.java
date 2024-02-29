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
 * Azure Blob Storage content.
 */
@Fluent
public final class AzureBlobContentSource implements JsonSerializable<AzureBlobContentSource> {
    /*
     * Azure Blob Storage container URL.
     */
    private String containerUrl;

    /*
     * Blob name prefix.
     */
    private String prefix;

    /**
     * Creates an instance of AzureBlobContentSource class.
     */
    public AzureBlobContentSource() {
    }

    /**
     * Get the containerUrl property: Azure Blob Storage container URL.
     * 
     * @return the containerUrl value.
     */
    public String getContainerUrl() {
        return this.containerUrl;
    }

    /**
     * Set the containerUrl property: Azure Blob Storage container URL.
     * 
     * @param containerUrl the containerUrl value to set.
     * @return the AzureBlobContentSource object itself.
     */
    public AzureBlobContentSource setContainerUrl(String containerUrl) {
        this.containerUrl = containerUrl;
        return this;
    }

    /**
     * Get the prefix property: Blob name prefix.
     * 
     * @return the prefix value.
     */
    public String getPrefix() {
        return this.prefix;
    }

    /**
     * Set the prefix property: Blob name prefix.
     * 
     * @param prefix the prefix value to set.
     * @return the AzureBlobContentSource object itself.
     */
    public AzureBlobContentSource setPrefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("containerUrl", this.containerUrl);
        jsonWriter.writeStringField("prefix", this.prefix);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of AzureBlobContentSource from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of AzureBlobContentSource if the JsonReader was pointing to an instance of it, or null if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the AzureBlobContentSource.
     */
    public static AzureBlobContentSource fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            AzureBlobContentSource deserializedAzureBlobContentSource = new AzureBlobContentSource();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("containerUrl".equals(fieldName)) {
                    deserializedAzureBlobContentSource.containerUrl = reader.getString();
                } else if ("prefix".equals(fieldName)) {
                    deserializedAzureBlobContentSource.prefix = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedAzureBlobContentSource;
        });
    }
}
