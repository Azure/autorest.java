// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.containers.containerregistry.models;

import com.azure.core.annotation.Fluent;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.time.OffsetDateTime;

/**
 * Properties of this repository.
 */
@Fluent
public class ContainerRepositoryProperties implements JsonSerializable<ContainerRepositoryProperties> {
    /*
     * Registry login server name. This is likely to be similar to {registry-name}.azurecr.io.
     */
    private String registryLoginServer;

    /*
     * Image name
     */
    private String name;

    /*
     * Image created time
     */
    private OffsetDateTime createdOn;

    /*
     * Image last update time
     */
    private OffsetDateTime lastUpdatedOn;

    /*
     * Number of the manifests
     */
    private int manifestCount;

    /*
     * Number of the tags
     */
    private int tagCount;

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
     * Creates an instance of ContainerRepositoryProperties class.
     */
    public ContainerRepositoryProperties() {
    }

    /**
     * Get the registryLoginServer property: Registry login server name. This is likely to be similar to
     * {registry-name}.azurecr.io.
     * 
     * @return the registryLoginServer value.
     */
    public String getRegistryLoginServer() {
        return this.registryLoginServer;
    }

    /**
     * Get the name property: Image name.
     * 
     * @return the name value.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the createdOn property: Image created time.
     * 
     * @return the createdOn value.
     */
    public OffsetDateTime getCreatedOn() {
        return this.createdOn;
    }

    /**
     * Get the lastUpdatedOn property: Image last update time.
     * 
     * @return the lastUpdatedOn value.
     */
    public OffsetDateTime getLastUpdatedOn() {
        return this.lastUpdatedOn;
    }

    /**
     * Get the manifestCount property: Number of the manifests.
     * 
     * @return the manifestCount value.
     */
    public int getManifestCount() {
        return this.manifestCount;
    }

    /**
     * Get the tagCount property: Number of the tags.
     * 
     * @return the tagCount value.
     */
    public int getTagCount() {
        return this.tagCount;
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
     * @return the ContainerRepositoryProperties object itself.
     */
    public ContainerRepositoryProperties setDeleteEnabled(Boolean deleteEnabled) {
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
     * @return the ContainerRepositoryProperties object itself.
     */
    public ContainerRepositoryProperties setWriteEnabled(Boolean writeEnabled) {
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
     * @return the ContainerRepositoryProperties object itself.
     */
    public ContainerRepositoryProperties setListEnabled(Boolean listEnabled) {
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
     * @return the ContainerRepositoryProperties object itself.
     */
    public ContainerRepositoryProperties setReadEnabled(Boolean readEnabled) {
        this.readEnabled = readEnabled;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        if (deleteEnabled != null || writeEnabled != null || listEnabled != null || readEnabled != null) {
            jsonWriter.writeStartObject("changeableAttributes");
            jsonWriter.writeBooleanField("deleteEnabled", this.deleteEnabled);
            jsonWriter.writeBooleanField("writeEnabled", this.writeEnabled);
            jsonWriter.writeBooleanField("listEnabled", this.listEnabled);
            jsonWriter.writeBooleanField("readEnabled", this.readEnabled);
            jsonWriter.writeEndObject();
        }
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ContainerRepositoryProperties from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of ContainerRepositoryProperties if the JsonReader was pointing to an instance of it, or
     * null if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the ContainerRepositoryProperties.
     */
    public static ContainerRepositoryProperties fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ContainerRepositoryProperties deserializedContainerRepositoryProperties
                = new ContainerRepositoryProperties();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("registry".equals(fieldName)) {
                    deserializedContainerRepositoryProperties.registryLoginServer = reader.getString();
                } else if ("imageName".equals(fieldName)) {
                    deserializedContainerRepositoryProperties.name = reader.getString();
                } else if ("createdTime".equals(fieldName)) {
                    deserializedContainerRepositoryProperties.createdOn
                        = reader.getNullable(nonNullReader -> OffsetDateTime.parse(nonNullReader.getString()));
                } else if ("lastUpdateTime".equals(fieldName)) {
                    deserializedContainerRepositoryProperties.lastUpdatedOn
                        = reader.getNullable(nonNullReader -> OffsetDateTime.parse(nonNullReader.getString()));
                } else if ("manifestCount".equals(fieldName)) {
                    deserializedContainerRepositoryProperties.manifestCount = reader.getInt();
                } else if ("tagCount".equals(fieldName)) {
                    deserializedContainerRepositoryProperties.tagCount = reader.getInt();
                } else if ("changeableAttributes".equals(fieldName)
                    && reader.currentToken() == JsonToken.START_OBJECT) {
                    while (reader.nextToken() != JsonToken.END_OBJECT) {
                        fieldName = reader.getFieldName();
                        reader.nextToken();

                        if ("deleteEnabled".equals(fieldName)) {
                            deserializedContainerRepositoryProperties.deleteEnabled
                                = reader.getNullable(JsonReader::getBoolean);
                        } else if ("writeEnabled".equals(fieldName)) {
                            deserializedContainerRepositoryProperties.writeEnabled
                                = reader.getNullable(JsonReader::getBoolean);
                        } else if ("listEnabled".equals(fieldName)) {
                            deserializedContainerRepositoryProperties.listEnabled
                                = reader.getNullable(JsonReader::getBoolean);
                        } else if ("readEnabled".equals(fieldName)) {
                            deserializedContainerRepositoryProperties.readEnabled
                                = reader.getNullable(JsonReader::getBoolean);
                        } else {
                            reader.skipChildren();
                        }
                    }
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedContainerRepositoryProperties;
        });
    }
}
