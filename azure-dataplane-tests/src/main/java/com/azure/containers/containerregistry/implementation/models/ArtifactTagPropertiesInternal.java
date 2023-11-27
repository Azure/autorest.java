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
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Tag attributes.
 */
@Fluent
public class ArtifactTagPropertiesInternal implements JsonSerializable<ArtifactTagPropertiesInternal> {
    private static final DateTimeFormatter ISO_8601 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");

    /*
     * Registry login server name. This is likely to be similar to {registry-name}.azurecr.io.
     */
    private String registryLoginServer;

    /*
     * Image name
     */
    private String repositoryName;

    /*
     * Tag name
     */
    private String name;

    /*
     * Tag digest
     */
    private String digest;

    /*
     * Tag created time
     */
    private OffsetDateTime createdOn;

    /*
     * Tag last update time
     */
    private OffsetDateTime lastUpdatedOn;

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
     * Creates an instance of ArtifactTagPropertiesInternal class.
     */
    public ArtifactTagPropertiesInternal() {
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
     * Set the registryLoginServer property: Registry login server name. This is likely to be similar to
     * {registry-name}.azurecr.io.
     * 
     * @param registryLoginServer the registryLoginServer value to set.
     * @return the ArtifactTagPropertiesInternal object itself.
     */
    public ArtifactTagPropertiesInternal setRegistryLoginServer(String registryLoginServer) {
        this.registryLoginServer = registryLoginServer;
        return this;
    }

    /**
     * Get the repositoryName property: Image name.
     * 
     * @return the repositoryName value.
     */
    public String getRepositoryName() {
        return this.repositoryName;
    }

    /**
     * Set the repositoryName property: Image name.
     * 
     * @param repositoryName the repositoryName value to set.
     * @return the ArtifactTagPropertiesInternal object itself.
     */
    public ArtifactTagPropertiesInternal setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
        return this;
    }

    /**
     * Get the name property: Tag name.
     * 
     * @return the name value.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the name property: Tag name.
     * 
     * @param name the name value to set.
     * @return the ArtifactTagPropertiesInternal object itself.
     */
    public ArtifactTagPropertiesInternal setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get the digest property: Tag digest.
     * 
     * @return the digest value.
     */
    public String getDigest() {
        return this.digest;
    }

    /**
     * Set the digest property: Tag digest.
     * 
     * @param digest the digest value to set.
     * @return the ArtifactTagPropertiesInternal object itself.
     */
    public ArtifactTagPropertiesInternal setDigest(String digest) {
        this.digest = digest;
        return this;
    }

    /**
     * Get the createdOn property: Tag created time.
     * 
     * @return the createdOn value.
     */
    public OffsetDateTime getCreatedOn() {
        return this.createdOn;
    }

    /**
     * Set the createdOn property: Tag created time.
     * 
     * @param createdOn the createdOn value to set.
     * @return the ArtifactTagPropertiesInternal object itself.
     */
    public ArtifactTagPropertiesInternal setCreatedOn(OffsetDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    /**
     * Get the lastUpdatedOn property: Tag last update time.
     * 
     * @return the lastUpdatedOn value.
     */
    public OffsetDateTime getLastUpdatedOn() {
        return this.lastUpdatedOn;
    }

    /**
     * Set the lastUpdatedOn property: Tag last update time.
     * 
     * @param lastUpdatedOn the lastUpdatedOn value to set.
     * @return the ArtifactTagPropertiesInternal object itself.
     */
    public ArtifactTagPropertiesInternal setLastUpdatedOn(OffsetDateTime lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
        return this;
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
     * @return the ArtifactTagPropertiesInternal object itself.
     */
    public ArtifactTagPropertiesInternal setDeleteEnabled(Boolean deleteEnabled) {
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
     * @return the ArtifactTagPropertiesInternal object itself.
     */
    public ArtifactTagPropertiesInternal setWriteEnabled(Boolean writeEnabled) {
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
     * @return the ArtifactTagPropertiesInternal object itself.
     */
    public ArtifactTagPropertiesInternal setListEnabled(Boolean listEnabled) {
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
     * @return the ArtifactTagPropertiesInternal object itself.
     */
    public ArtifactTagPropertiesInternal setReadEnabled(Boolean readEnabled) {
        this.readEnabled = readEnabled;
        return this;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("registry", this.registryLoginServer);
        jsonWriter.writeStringField("imageName", this.repositoryName);
        if (name != null || digest != null || createdOn != null || lastUpdatedOn != null || deleteEnabled != null
            || writeEnabled != null || listEnabled != null || readEnabled != null) {
            jsonWriter.writeStartObject("tag");
            jsonWriter.writeStringField("name", this.name);
            jsonWriter.writeStringField("digest", this.digest);
            jsonWriter.writeStringField("createdTime", this.createdOn == null ? null : ISO_8601.format(this.createdOn));
            jsonWriter.writeStringField("lastUpdateTime",
                this.lastUpdatedOn == null ? null : ISO_8601.format(this.lastUpdatedOn));
            if (deleteEnabled != null || writeEnabled != null || listEnabled != null || readEnabled != null) {
                jsonWriter.writeStartObject("changeableAttributes");
                jsonWriter.writeBooleanField("deleteEnabled", this.deleteEnabled);
                jsonWriter.writeBooleanField("writeEnabled", this.writeEnabled);
                jsonWriter.writeBooleanField("listEnabled", this.listEnabled);
                jsonWriter.writeBooleanField("readEnabled", this.readEnabled);
                jsonWriter.writeEndObject();
            }
            jsonWriter.writeEndObject();
        }
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ArtifactTagPropertiesInternal from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of ArtifactTagPropertiesInternal if the JsonReader was pointing to an instance of it, or null
     * if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the ArtifactTagPropertiesInternal.
     */
    public static ArtifactTagPropertiesInternal fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ArtifactTagPropertiesInternal deserializedArtifactTagPropertiesInternal
                = new ArtifactTagPropertiesInternal();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("registry".equals(fieldName)) {
                    deserializedArtifactTagPropertiesInternal.registryLoginServer = reader.getString();
                } else if ("imageName".equals(fieldName)) {
                    deserializedArtifactTagPropertiesInternal.repositoryName = reader.getString();
                } else if ("tag".equals(fieldName) && reader.currentToken() == JsonToken.START_OBJECT) {
                    while (reader.nextToken() != JsonToken.END_OBJECT) {
                        fieldName = reader.getFieldName();
                        reader.nextToken();

                        if ("name".equals(fieldName)) {
                            deserializedArtifactTagPropertiesInternal.name = reader.getString();
                        } else if ("digest".equals(fieldName)) {
                            deserializedArtifactTagPropertiesInternal.digest = reader.getString();
                        } else if ("createdTime".equals(fieldName)) {
                            deserializedArtifactTagPropertiesInternal.createdOn = reader.getNullable(
                                nonNullReader -> OffsetDateTime.parse(nonNullReader.getString(), ISO_8601));
                        } else if ("lastUpdateTime".equals(fieldName)) {
                            deserializedArtifactTagPropertiesInternal.lastUpdatedOn = reader.getNullable(
                                nonNullReader -> OffsetDateTime.parse(nonNullReader.getString(), ISO_8601));
                        } else if ("changeableAttributes".equals(fieldName)
                            && reader.currentToken() == JsonToken.START_OBJECT) {
                            while (reader.nextToken() != JsonToken.END_OBJECT) {
                                fieldName = reader.getFieldName();
                                reader.nextToken();

                                if ("deleteEnabled".equals(fieldName)) {
                                    deserializedArtifactTagPropertiesInternal.deleteEnabled
                                        = reader.getNullable(JsonReader::getBoolean);
                                } else if ("writeEnabled".equals(fieldName)) {
                                    deserializedArtifactTagPropertiesInternal.writeEnabled
                                        = reader.getNullable(JsonReader::getBoolean);
                                } else if ("listEnabled".equals(fieldName)) {
                                    deserializedArtifactTagPropertiesInternal.listEnabled
                                        = reader.getNullable(JsonReader::getBoolean);
                                } else if ("readEnabled".equals(fieldName)) {
                                    deserializedArtifactTagPropertiesInternal.readEnabled
                                        = reader.getNullable(JsonReader::getBoolean);
                                } else {
                                    reader.skipChildren();
                                }
                            }
                        } else {
                            reader.skipChildren();
                        }
                    }
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedArtifactTagPropertiesInternal;
        });
    }
}
