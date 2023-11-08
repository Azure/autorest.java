// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.containers.containerregistry.implementation.models;

import com.azure.core.annotation.Fluent;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * Image layer information.
 */
@Fluent
public final class FsLayer implements JsonSerializable<FsLayer> {
    /*
     * SHA of an image layer
     */
    private String blobSum;

    /**
     * Creates an instance of FsLayer class.
     */
    public FsLayer() {
    }

    /**
     * Get the blobSum property: SHA of an image layer.
     * 
     * @return the blobSum value.
     */
    public String getBlobSum() {
        return this.blobSum;
    }

    /**
     * Set the blobSum property: SHA of an image layer.
     * 
     * @param blobSum the blobSum value to set.
     * @return the FsLayer object itself.
     */
    public FsLayer setBlobSum(String blobSum) {
        this.blobSum = blobSum;
        return this;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("blobSum", this.blobSum);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of FsLayer from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of FsLayer if the JsonReader was pointing to an instance of it, or null if it was pointing to
     * JSON null.
     * @throws IOException If an error occurs while reading the FsLayer.
     */
    public static FsLayer fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            FsLayer deserializedFsLayer = new FsLayer();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("blobSum".equals(fieldName)) {
                    deserializedFsLayer.blobSum = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedFsLayer;
        });
    }
}
