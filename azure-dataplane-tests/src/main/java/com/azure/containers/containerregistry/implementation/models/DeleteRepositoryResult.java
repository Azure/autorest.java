// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.containers.containerregistry.implementation.models;

import com.azure.core.annotation.Immutable;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.util.List;

/**
 * Deleted repository.
 */
@Immutable
public final class DeleteRepositoryResult implements JsonSerializable<DeleteRepositoryResult> {
    /*
     * SHA of the deleted image
     */
    private List<String> deletedManifests;

    /*
     * Tag of the deleted image
     */
    private List<String> deletedTags;

    /**
     * Creates an instance of DeleteRepositoryResult class.
     */
    public DeleteRepositoryResult() {}

    /**
     * Get the deletedManifests property: SHA of the deleted image.
     * 
     * @return the deletedManifests value.
     */
    public List<String> getDeletedManifests() {
        return this.deletedManifests;
    }

    /**
     * Get the deletedTags property: Tag of the deleted image.
     * 
     * @return the deletedTags value.
     */
    public List<String> getDeletedTags() {
        return this.deletedTags;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of DeleteRepositoryResult from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of DeleteRepositoryResult if the JsonReader was pointing to an instance of it, or null if it
     * was pointing to JSON null.
     * @throws IOException If an error occurs while reading the DeleteRepositoryResult.
     */
    public static DeleteRepositoryResult fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            DeleteRepositoryResult deserializedDeleteRepositoryResult = new DeleteRepositoryResult();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("manifestsDeleted".equals(fieldName)) {
                    List<String> deletedManifests = reader.readArray(reader1 -> reader1.getString());
                    deserializedDeleteRepositoryResult.deletedManifests = deletedManifests;
                } else if ("tagsDeleted".equals(fieldName)) {
                    List<String> deletedTags = reader.readArray(reader1 -> reader1.getString());
                    deserializedDeleteRepositoryResult.deletedTags = deletedTags;
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedDeleteRepositoryResult;
        });
    }
}
