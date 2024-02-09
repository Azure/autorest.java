// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.containers.containerregistry.implementation.models;

import com.azure.containers.containerregistry.models.ArtifactArchitecture;
import com.azure.containers.containerregistry.models.ArtifactManifestPlatform;
import com.azure.containers.containerregistry.models.ArtifactOperatingSystem;
import com.azure.core.annotation.Fluent;

import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import java.util.List;

/**
 * Manifest attributes details.
 */
@Fluent
public class ArtifactManifestPropertiesInternal implements JsonSerializable<ArtifactManifestPropertiesInternal> {
    /*
     * Registry login server name. This is likely to be similar to {registry-name}.azurecr.io.
     */
    private String registryLoginServer;

    /*
     * Repository name
     */
    private String repositoryName;

    /*
     * Manifest
     */
    private String digest;

    /*
     * Image size
     */
    private Long sizeInBytes;

    /*
     * Created time
     */
    private OffsetDateTime createdOn;

    /*
     * Last update time
     */
    private OffsetDateTime lastUpdatedOn;

    /*
     * CPU architecture
     */
    private ArtifactArchitecture architecture;

    /*
     * Operating system
     */
    private ArtifactOperatingSystem operatingSystem;

    /*
     * List of artifacts that are referenced by this manifest list, with information about the platform each supports.
     * This list will be empty if this is a leaf manifest and not a manifest list.
     */
    private List<ArtifactManifestPlatform> relatedArtifacts;

    /*
     * List of tags
     */
    private List<String> tags;

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
     * Creates an instance of ArtifactManifestPropertiesInternal class.
     */
    public ArtifactManifestPropertiesInternal() {
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
     * @return the ArtifactManifestPropertiesInternal object itself.
     */
    public ArtifactManifestPropertiesInternal setRegistryLoginServer(String registryLoginServer) {
        this.registryLoginServer = registryLoginServer;
        return this;
    }

    /**
     * Get the repositoryName property: Repository name.
     * 
     * @return the repositoryName value.
     */
    public String getRepositoryName() {
        return this.repositoryName;
    }

    /**
     * Set the repositoryName property: Repository name.
     * 
     * @param repositoryName the repositoryName value to set.
     * @return the ArtifactManifestPropertiesInternal object itself.
     */
    public ArtifactManifestPropertiesInternal setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
        return this;
    }

    /**
     * Get the digest property: Manifest.
     * 
     * @return the digest value.
     */
    public String getDigest() {
        return this.digest;
    }

    /**
     * Set the digest property: Manifest.
     * 
     * @param digest the digest value to set.
     * @return the ArtifactManifestPropertiesInternal object itself.
     */
    public ArtifactManifestPropertiesInternal setDigest(String digest) {
        this.digest = digest;
        return this;
    }

    /**
     * Get the sizeInBytes property: Image size.
     * 
     * @return the sizeInBytes value.
     */
    public Long getSizeInBytes() {
        return this.sizeInBytes;
    }

    /**
     * Set the sizeInBytes property: Image size.
     * 
     * @param sizeInBytes the sizeInBytes value to set.
     * @return the ArtifactManifestPropertiesInternal object itself.
     */
    public ArtifactManifestPropertiesInternal setSizeInBytes(Long sizeInBytes) {
        this.sizeInBytes = sizeInBytes;
        return this;
    }

    /**
     * Get the createdOn property: Created time.
     * 
     * @return the createdOn value.
     */
    public OffsetDateTime getCreatedOn() {
        return this.createdOn;
    }

    /**
     * Set the createdOn property: Created time.
     * 
     * @param createdOn the createdOn value to set.
     * @return the ArtifactManifestPropertiesInternal object itself.
     */
    public ArtifactManifestPropertiesInternal setCreatedOn(OffsetDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    /**
     * Get the lastUpdatedOn property: Last update time.
     * 
     * @return the lastUpdatedOn value.
     */
    public OffsetDateTime getLastUpdatedOn() {
        return this.lastUpdatedOn;
    }

    /**
     * Set the lastUpdatedOn property: Last update time.
     * 
     * @param lastUpdatedOn the lastUpdatedOn value to set.
     * @return the ArtifactManifestPropertiesInternal object itself.
     */
    public ArtifactManifestPropertiesInternal setLastUpdatedOn(OffsetDateTime lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
        return this;
    }

    /**
     * Get the architecture property: CPU architecture.
     * 
     * @return the architecture value.
     */
    public ArtifactArchitecture getArchitecture() {
        return this.architecture;
    }

    /**
     * Set the architecture property: CPU architecture.
     * 
     * @param architecture the architecture value to set.
     * @return the ArtifactManifestPropertiesInternal object itself.
     */
    public ArtifactManifestPropertiesInternal setArchitecture(ArtifactArchitecture architecture) {
        this.architecture = architecture;
        return this;
    }

    /**
     * Get the operatingSystem property: Operating system.
     * 
     * @return the operatingSystem value.
     */
    public ArtifactOperatingSystem getOperatingSystem() {
        return this.operatingSystem;
    }

    /**
     * Set the operatingSystem property: Operating system.
     * 
     * @param operatingSystem the operatingSystem value to set.
     * @return the ArtifactManifestPropertiesInternal object itself.
     */
    public ArtifactManifestPropertiesInternal setOperatingSystem(ArtifactOperatingSystem operatingSystem) {
        this.operatingSystem = operatingSystem;
        return this;
    }

    /**
     * Get the relatedArtifacts property: List of artifacts that are referenced by this manifest list, with information
     * about the platform each supports. This list will be empty if this is a leaf manifest and not a manifest list.
     * 
     * @return the relatedArtifacts value.
     */
    public List<ArtifactManifestPlatform> getRelatedArtifacts() {
        return this.relatedArtifacts;
    }

    /**
     * Set the relatedArtifacts property: List of artifacts that are referenced by this manifest list, with information
     * about the platform each supports. This list will be empty if this is a leaf manifest and not a manifest list.
     * 
     * @param relatedArtifacts the relatedArtifacts value to set.
     * @return the ArtifactManifestPropertiesInternal object itself.
     */
    public ArtifactManifestPropertiesInternal setRelatedArtifacts(List<ArtifactManifestPlatform> relatedArtifacts) {
        this.relatedArtifacts = relatedArtifacts;
        return this;
    }

    /**
     * Get the tags property: List of tags.
     * 
     * @return the tags value.
     */
    public List<String> getTags() {
        return this.tags;
    }

    /**
     * Set the tags property: List of tags.
     * 
     * @param tags the tags value to set.
     * @return the ArtifactManifestPropertiesInternal object itself.
     */
    public ArtifactManifestPropertiesInternal setTags(List<String> tags) {
        this.tags = tags;
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
     * @return the ArtifactManifestPropertiesInternal object itself.
     */
    public ArtifactManifestPropertiesInternal setDeleteEnabled(Boolean deleteEnabled) {
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
     * @return the ArtifactManifestPropertiesInternal object itself.
     */
    public ArtifactManifestPropertiesInternal setWriteEnabled(Boolean writeEnabled) {
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
     * @return the ArtifactManifestPropertiesInternal object itself.
     */
    public ArtifactManifestPropertiesInternal setListEnabled(Boolean listEnabled) {
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
     * @return the ArtifactManifestPropertiesInternal object itself.
     */
    public ArtifactManifestPropertiesInternal setReadEnabled(Boolean readEnabled) {
        this.readEnabled = readEnabled;
        return this;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("registry", this.registryLoginServer);
        jsonWriter.writeStringField("imageName", this.repositoryName);
        if (digest != null || sizeInBytes != null || createdOn != null || lastUpdatedOn != null || architecture != null
            || operatingSystem != null || relatedArtifacts != null || tags != null || deleteEnabled != null
            || writeEnabled != null || listEnabled != null || readEnabled != null) {
            jsonWriter.writeStartObject("manifest");
            jsonWriter.writeStringField("digest", this.digest);
            jsonWriter.writeNumberField("imageSize", this.sizeInBytes);
            jsonWriter.writeStringField("createdTime",
                this.createdOn == null ? null : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(this.createdOn));
            jsonWriter.writeStringField("lastUpdateTime",
                this.lastUpdatedOn == null ? null : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(this.lastUpdatedOn));
            jsonWriter.writeStringField("architecture",
                this.architecture == null ? null : this.architecture.toString());
            jsonWriter.writeStringField("os", this.operatingSystem == null ? null : this.operatingSystem.toString());
            jsonWriter.writeArrayField("references", this.relatedArtifacts,
                (writer, element) -> writer.writeJson(element));
            jsonWriter.writeArrayField("tags", this.tags, (writer, element) -> writer.writeString(element));
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
     * Reads an instance of ArtifactManifestPropertiesInternal from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of ArtifactManifestPropertiesInternal if the JsonReader was pointing to an instance of it, or
     * null if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the ArtifactManifestPropertiesInternal.
     */
    public static ArtifactManifestPropertiesInternal fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ArtifactManifestPropertiesInternal deserializedArtifactManifestPropertiesInternal
                = new ArtifactManifestPropertiesInternal();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("registry".equals(fieldName)) {
                    deserializedArtifactManifestPropertiesInternal.registryLoginServer = reader.getString();
                } else if ("imageName".equals(fieldName)) {
                    deserializedArtifactManifestPropertiesInternal.repositoryName = reader.getString();
                } else if ("manifest".equals(fieldName) && reader.currentToken() == JsonToken.START_OBJECT) {
                    while (reader.nextToken() != JsonToken.END_OBJECT) {
                        fieldName = reader.getFieldName();
                        reader.nextToken();

                        if ("digest".equals(fieldName)) {
                            deserializedArtifactManifestPropertiesInternal.digest = reader.getString();
                        } else if ("imageSize".equals(fieldName)) {
                            deserializedArtifactManifestPropertiesInternal.sizeInBytes
                                = reader.getNullable(JsonReader::getLong);
                        } else if ("createdTime".equals(fieldName)) {
                            deserializedArtifactManifestPropertiesInternal.createdOn
                                = reader.getNullable(nonNullReader -> OffsetDateTime.parse(nonNullReader.getString()));
                        } else if ("lastUpdateTime".equals(fieldName)) {
                            deserializedArtifactManifestPropertiesInternal.lastUpdatedOn
                                = reader.getNullable(nonNullReader -> OffsetDateTime.parse(nonNullReader.getString()));
                        } else if ("architecture".equals(fieldName)) {
                            deserializedArtifactManifestPropertiesInternal.architecture
                                = ArtifactArchitecture.fromString(reader.getString());
                        } else if ("os".equals(fieldName)) {
                            deserializedArtifactManifestPropertiesInternal.operatingSystem
                                = ArtifactOperatingSystem.fromString(reader.getString());
                        } else if ("references".equals(fieldName)) {
                            List<ArtifactManifestPlatform> relatedArtifacts
                                = reader.readArray(reader1 -> ArtifactManifestPlatform.fromJson(reader1));
                            deserializedArtifactManifestPropertiesInternal.relatedArtifacts = relatedArtifacts;
                        } else if ("tags".equals(fieldName)) {
                            List<String> tags = reader.readArray(reader1 -> reader1.getString());
                            deserializedArtifactManifestPropertiesInternal.tags = tags;
                        } else if ("changeableAttributes".equals(fieldName)
                            && reader.currentToken() == JsonToken.START_OBJECT) {
                            while (reader.nextToken() != JsonToken.END_OBJECT) {
                                fieldName = reader.getFieldName();
                                reader.nextToken();

                                if ("deleteEnabled".equals(fieldName)) {
                                    deserializedArtifactManifestPropertiesInternal.deleteEnabled
                                        = reader.getNullable(JsonReader::getBoolean);
                                } else if ("writeEnabled".equals(fieldName)) {
                                    deserializedArtifactManifestPropertiesInternal.writeEnabled
                                        = reader.getNullable(JsonReader::getBoolean);
                                } else if ("listEnabled".equals(fieldName)) {
                                    deserializedArtifactManifestPropertiesInternal.listEnabled
                                        = reader.getNullable(JsonReader::getBoolean);
                                } else if ("readEnabled".equals(fieldName)) {
                                    deserializedArtifactManifestPropertiesInternal.readEnabled
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

            return deserializedArtifactManifestPropertiesInternal;
        });
    }
}
