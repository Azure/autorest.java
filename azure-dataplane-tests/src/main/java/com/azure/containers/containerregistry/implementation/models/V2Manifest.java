// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.containers.containerregistry.implementation.models;

import com.azure.containers.containerregistry.models.OciDescriptor;
import com.azure.core.annotation.Fluent;

import com.azure.json.JsonReader;

import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

import java.util.List;

/**
 * Returns the requested Docker V2 Manifest file.
 */
@Fluent
public final class V2Manifest extends Manifest {
    /*
     * Media type for this Manifest
     */
    private String mediaType;

    /*
     * V2 image config descriptor
     */
    private OciDescriptor config;

    /*
     * List of V2 image layer information
     */
    private List<OciDescriptor> layers;

    /**
     * Creates an instance of V2Manifest class.
     */
    public V2Manifest() {
    }

    /**
     * Get the mediaType property: Media type for this Manifest.
     * 
     * @return the mediaType value.
     */
    public String getMediaType() {
        return this.mediaType;
    }

    /**
     * Set the mediaType property: Media type for this Manifest.
     * 
     * @param mediaType the mediaType value to set.
     * @return the V2Manifest object itself.
     */
    public V2Manifest setMediaType(String mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    /**
     * Get the config property: V2 image config descriptor.
     * 
     * @return the config value.
     */
    public OciDescriptor getConfig() {
        return this.config;
    }

    /**
     * Set the config property: V2 image config descriptor.
     * 
     * @param config the config value to set.
     * @return the V2Manifest object itself.
     */
    public V2Manifest setConfig(OciDescriptor config) {
        this.config = config;
        return this;
    }

    /**
     * Get the layers property: List of V2 image layer information.
     * 
     * @return the layers value.
     */
    public List<OciDescriptor> getLayers() {
        return this.layers;
    }

    /**
     * Set the layers property: List of V2 image layer information.
     * 
     * @param layers the layers value to set.
     * @return the V2Manifest object itself.
     */
    public V2Manifest setLayers(List<OciDescriptor> layers) {
        this.layers = layers;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V2Manifest setSchemaVersion(Integer schemaVersion) {
        super.setSchemaVersion(schemaVersion);
        return this;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeNumberField("schemaVersion", getSchemaVersion());
        jsonWriter.writeStringField("mediaType", this.mediaType);
        jsonWriter.writeJsonField("config", this.config);
        jsonWriter.writeArrayField("layers", this.layers, (writer, element) -> writer.writeJson(element));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of V2Manifest from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of V2Manifest if the JsonReader was pointing to an instance of it, or null if it was pointing
     * to JSON null.
     * @throws IOException If an error occurs while reading the V2Manifest.
     */
    public static V2Manifest fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            V2Manifest deserializedV2Manifest = new V2Manifest();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("schemaVersion".equals(fieldName)) {
                    deserializedV2Manifest.setSchemaVersion(reader.getNullable(JsonReader::getInt));
                } else if ("mediaType".equals(fieldName)) {
                    deserializedV2Manifest.mediaType = reader.getString();
                } else if ("config".equals(fieldName)) {
                    deserializedV2Manifest.config = OciDescriptor.fromJson(reader);
                } else if ("layers".equals(fieldName)) {
                    List<OciDescriptor> layers = reader.readArray(reader1 -> OciDescriptor.fromJson(reader1));
                    deserializedV2Manifest.layers = layers;
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedV2Manifest;
        });
    }
}
