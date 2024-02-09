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
 * Changeable attributes.
 */
@Fluent
public final class ManifestWriteableProperties implements JsonSerializable<ManifestWriteableProperties> {
    /*
     * Delete enabled
     */
    private Boolean deleteEnabled;

    /*
     * Write enabled
     */
    private Boolean writeEnabled;

    /*
     * List enabled
     */
    private Boolean listEnabled;

    /*
     * Read enabled
     */
    private Boolean readEnabled;

    /**
     * Creates an instance of ManifestWriteableProperties class.
     */
    public ManifestWriteableProperties() {
    }

    /**
     * Get the deleteEnabled property: Delete enabled.
     * 
     * @return the deleteEnabled value.
     */
    public Boolean isDeleteEnabled() {
        return this.deleteEnabled;
    }

    /**
     * Set the deleteEnabled property: Delete enabled.
     * 
     * @param deleteEnabled the deleteEnabled value to set.
     * @return the ManifestWriteableProperties object itself.
     */
    public ManifestWriteableProperties setDeleteEnabled(Boolean deleteEnabled) {
        this.deleteEnabled = deleteEnabled;
        return this;
    }

    /**
     * Get the writeEnabled property: Write enabled.
     * 
     * @return the writeEnabled value.
     */
    public Boolean isWriteEnabled() {
        return this.writeEnabled;
    }

    /**
     * Set the writeEnabled property: Write enabled.
     * 
     * @param writeEnabled the writeEnabled value to set.
     * @return the ManifestWriteableProperties object itself.
     */
    public ManifestWriteableProperties setWriteEnabled(Boolean writeEnabled) {
        this.writeEnabled = writeEnabled;
        return this;
    }

    /**
     * Get the listEnabled property: List enabled.
     * 
     * @return the listEnabled value.
     */
    public Boolean isListEnabled() {
        return this.listEnabled;
    }

    /**
     * Set the listEnabled property: List enabled.
     * 
     * @param listEnabled the listEnabled value to set.
     * @return the ManifestWriteableProperties object itself.
     */
    public ManifestWriteableProperties setListEnabled(Boolean listEnabled) {
        this.listEnabled = listEnabled;
        return this;
    }

    /**
     * Get the readEnabled property: Read enabled.
     * 
     * @return the readEnabled value.
     */
    public Boolean isReadEnabled() {
        return this.readEnabled;
    }

    /**
     * Set the readEnabled property: Read enabled.
     * 
     * @param readEnabled the readEnabled value to set.
     * @return the ManifestWriteableProperties object itself.
     */
    public ManifestWriteableProperties setReadEnabled(Boolean readEnabled) {
        this.readEnabled = readEnabled;
        return this;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeBooleanField("deleteEnabled", this.deleteEnabled);
        jsonWriter.writeBooleanField("writeEnabled", this.writeEnabled);
        jsonWriter.writeBooleanField("listEnabled", this.listEnabled);
        jsonWriter.writeBooleanField("readEnabled", this.readEnabled);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ManifestWriteableProperties from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of ManifestWriteableProperties if the JsonReader was pointing to an instance of it, or null
     * if it was pointing to JSON null.
     * @throws IOException If an error occurs while reading the ManifestWriteableProperties.
     */
    public static ManifestWriteableProperties fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ManifestWriteableProperties deserializedManifestWriteableProperties = new ManifestWriteableProperties();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("deleteEnabled".equals(fieldName)) {
                    deserializedManifestWriteableProperties.deleteEnabled = reader.getNullable(JsonReader::getBoolean);
                } else if ("writeEnabled".equals(fieldName)) {
                    deserializedManifestWriteableProperties.writeEnabled = reader.getNullable(JsonReader::getBoolean);
                } else if ("listEnabled".equals(fieldName)) {
                    deserializedManifestWriteableProperties.listEnabled = reader.getNullable(JsonReader::getBoolean);
                } else if ("readEnabled".equals(fieldName)) {
                    deserializedManifestWriteableProperties.readEnabled = reader.getNullable(JsonReader::getBoolean);
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedManifestWriteableProperties;
        });
    }
}
