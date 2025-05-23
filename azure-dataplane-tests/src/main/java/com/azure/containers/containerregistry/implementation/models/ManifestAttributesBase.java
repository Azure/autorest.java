// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.containers.containerregistry.implementation.models;

import com.azure.containers.containerregistry.models.ArtifactArchitecture;
import com.azure.containers.containerregistry.models.ArtifactManifestPlatform;
import com.azure.containers.containerregistry.models.ArtifactOperatingSystem;
import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.core.util.CoreUtils;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Manifest details.
 */
@Fluent
public class ManifestAttributesBase implements JsonSerializable<ManifestAttributesBase> {
    /*
     * Manifest
     */
    @Generated
    private String digest;

    /*
     * Image size
     */
    @Generated
    private Long sizeInBytes;

    /*
     * Created time
     */
    @Generated
    private OffsetDateTime createdOn;

    /*
     * Last update time
     */
    @Generated
    private OffsetDateTime lastUpdatedOn;

    /*
     * CPU architecture
     */
    @Generated
    private ArtifactArchitecture architecture;

    /*
     * Operating system
     */
    @Generated
    private ArtifactOperatingSystem operatingSystem;

    /*
     * List of artifacts that are referenced by this manifest list, with information about the platform each supports.
     * This list will be empty if this is a leaf manifest and not a manifest list.
     */
    @Generated
    private List<ArtifactManifestPlatform> relatedArtifacts;

    /*
     * List of tags
     */
    @Generated
    private List<String> tags;

    /*
     * Delete enabled
     */
    @Generated
    private Boolean deleteEnabled;

    /*
     * Write enabled
     */
    @Generated
    private Boolean writeEnabled;

    /*
     * List enabled
     */
    @Generated
    private Boolean listEnabled;

    /*
     * Read enabled
     */
    @Generated
    private Boolean readEnabled;

    /**
     * Creates an instance of ManifestAttributesBase class.
     */
    @Generated
    public ManifestAttributesBase() {
    }

    /**
     * Get the digest property: Manifest.
     * 
     * @return the digest value.
     */
    @Generated
    public String getDigest() {
        return this.digest;
    }

    /**
     * Set the digest property: Manifest.
     * 
     * @param digest the digest value to set.
     * @return the ManifestAttributesBase object itself.
     */
    @Generated
    public ManifestAttributesBase setDigest(String digest) {
        this.digest = digest;
        return this;
    }

    /**
     * Get the sizeInBytes property: Image size.
     * 
     * @return the sizeInBytes value.
     */
    @Generated
    public Long getSizeInBytes() {
        return this.sizeInBytes;
    }

    /**
     * Set the sizeInBytes property: Image size.
     * 
     * @param sizeInBytes the sizeInBytes value to set.
     * @return the ManifestAttributesBase object itself.
     */
    @Generated
    public ManifestAttributesBase setSizeInBytes(Long sizeInBytes) {
        this.sizeInBytes = sizeInBytes;
        return this;
    }

    /**
     * Get the createdOn property: Created time.
     * 
     * @return the createdOn value.
     */
    @Generated
    public OffsetDateTime getCreatedOn() {
        return this.createdOn;
    }

    /**
     * Set the createdOn property: Created time.
     * 
     * @param createdOn the createdOn value to set.
     * @return the ManifestAttributesBase object itself.
     */
    @Generated
    public ManifestAttributesBase setCreatedOn(OffsetDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    /**
     * Get the lastUpdatedOn property: Last update time.
     * 
     * @return the lastUpdatedOn value.
     */
    @Generated
    public OffsetDateTime getLastUpdatedOn() {
        return this.lastUpdatedOn;
    }

    /**
     * Set the lastUpdatedOn property: Last update time.
     * 
     * @param lastUpdatedOn the lastUpdatedOn value to set.
     * @return the ManifestAttributesBase object itself.
     */
    @Generated
    public ManifestAttributesBase setLastUpdatedOn(OffsetDateTime lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
        return this;
    }

    /**
     * Get the architecture property: CPU architecture.
     * 
     * @return the architecture value.
     */
    @Generated
    public ArtifactArchitecture getArchitecture() {
        return this.architecture;
    }

    /**
     * Set the architecture property: CPU architecture.
     * 
     * @param architecture the architecture value to set.
     * @return the ManifestAttributesBase object itself.
     */
    @Generated
    public ManifestAttributesBase setArchitecture(ArtifactArchitecture architecture) {
        this.architecture = architecture;
        return this;
    }

    /**
     * Get the operatingSystem property: Operating system.
     * 
     * @return the operatingSystem value.
     */
    @Generated
    public ArtifactOperatingSystem getOperatingSystem() {
        return this.operatingSystem;
    }

    /**
     * Set the operatingSystem property: Operating system.
     * 
     * @param operatingSystem the operatingSystem value to set.
     * @return the ManifestAttributesBase object itself.
     */
    @Generated
    public ManifestAttributesBase setOperatingSystem(ArtifactOperatingSystem operatingSystem) {
        this.operatingSystem = operatingSystem;
        return this;
    }

    /**
     * Get the relatedArtifacts property: List of artifacts that are referenced by this manifest list, with information
     * about the platform each supports. This list will be empty if this is a leaf manifest and not a manifest list.
     * 
     * @return the relatedArtifacts value.
     */
    @Generated
    public List<ArtifactManifestPlatform> getRelatedArtifacts() {
        return this.relatedArtifacts;
    }

    /**
     * Set the relatedArtifacts property: List of artifacts that are referenced by this manifest list, with information
     * about the platform each supports. This list will be empty if this is a leaf manifest and not a manifest list.
     * 
     * @param relatedArtifacts the relatedArtifacts value to set.
     * @return the ManifestAttributesBase object itself.
     */
    @Generated
    public ManifestAttributesBase setRelatedArtifacts(List<ArtifactManifestPlatform> relatedArtifacts) {
        this.relatedArtifacts = relatedArtifacts;
        return this;
    }

    /**
     * Get the tags property: List of tags.
     * 
     * @return the tags value.
     */
    @Generated
    public List<String> getTags() {
        return this.tags;
    }

    /**
     * Set the tags property: List of tags.
     * 
     * @param tags the tags value to set.
     * @return the ManifestAttributesBase object itself.
     */
    @Generated
    public ManifestAttributesBase setTags(List<String> tags) {
        this.tags = tags;
        return this;
    }

    /**
     * Get the deleteEnabled property: Delete enabled.
     * 
     * @return the deleteEnabled value.
     */
    @Generated
    public Boolean isDeleteEnabled() {
        return this.deleteEnabled;
    }

    /**
     * Set the deleteEnabled property: Delete enabled.
     * 
     * @param deleteEnabled the deleteEnabled value to set.
     * @return the ManifestAttributesBase object itself.
     */
    @Generated
    public ManifestAttributesBase setDeleteEnabled(Boolean deleteEnabled) {
        this.deleteEnabled = deleteEnabled;
        return this;
    }

    /**
     * Get the writeEnabled property: Write enabled.
     * 
     * @return the writeEnabled value.
     */
    @Generated
    public Boolean isWriteEnabled() {
        return this.writeEnabled;
    }

    /**
     * Set the writeEnabled property: Write enabled.
     * 
     * @param writeEnabled the writeEnabled value to set.
     * @return the ManifestAttributesBase object itself.
     */
    @Generated
    public ManifestAttributesBase setWriteEnabled(Boolean writeEnabled) {
        this.writeEnabled = writeEnabled;
        return this;
    }

    /**
     * Get the listEnabled property: List enabled.
     * 
     * @return the listEnabled value.
     */
    @Generated
    public Boolean isListEnabled() {
        return this.listEnabled;
    }

    /**
     * Set the listEnabled property: List enabled.
     * 
     * @param listEnabled the listEnabled value to set.
     * @return the ManifestAttributesBase object itself.
     */
    @Generated
    public ManifestAttributesBase setListEnabled(Boolean listEnabled) {
        this.listEnabled = listEnabled;
        return this;
    }

    /**
     * Get the readEnabled property: Read enabled.
     * 
     * @return the readEnabled value.
     */
    @Generated
    public Boolean isReadEnabled() {
        return this.readEnabled;
    }

    /**
     * Set the readEnabled property: Read enabled.
     * 
     * @param readEnabled the readEnabled value to set.
     * @return the ManifestAttributesBase object itself.
     */
    @Generated
    public ManifestAttributesBase setReadEnabled(Boolean readEnabled) {
        this.readEnabled = readEnabled;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("digest", this.digest);
        jsonWriter.writeStringField("createdTime",
            this.createdOn == null ? null : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(this.createdOn));
        jsonWriter.writeStringField("lastUpdateTime",
            this.lastUpdatedOn == null ? null : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(this.lastUpdatedOn));
        jsonWriter.writeNumberField("imageSize", this.sizeInBytes);
        jsonWriter.writeStringField("architecture", this.architecture == null ? null : this.architecture.toString());
        jsonWriter.writeStringField("os", this.operatingSystem == null ? null : this.operatingSystem.toString());
        jsonWriter.writeArrayField("references", this.relatedArtifacts, (writer, element) -> writer.writeJson(element));
        jsonWriter.writeArrayField("tags", this.tags, (writer, element) -> writer.writeString(element));
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
     * Reads an instance of ManifestAttributesBase from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of ManifestAttributesBase if the JsonReader was pointing to an instance of it, or null if it
     * was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the ManifestAttributesBase.
     */
    @Generated
    public static ManifestAttributesBase fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ManifestAttributesBase deserializedManifestAttributesBase = new ManifestAttributesBase();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("digest".equals(fieldName)) {
                    deserializedManifestAttributesBase.digest = reader.getString();
                } else if ("createdTime".equals(fieldName)) {
                    deserializedManifestAttributesBase.createdOn = reader
                        .getNullable(nonNullReader -> CoreUtils.parseBestOffsetDateTime(nonNullReader.getString()));
                } else if ("lastUpdateTime".equals(fieldName)) {
                    deserializedManifestAttributesBase.lastUpdatedOn = reader
                        .getNullable(nonNullReader -> CoreUtils.parseBestOffsetDateTime(nonNullReader.getString()));
                } else if ("imageSize".equals(fieldName)) {
                    deserializedManifestAttributesBase.sizeInBytes = reader.getNullable(JsonReader::getLong);
                } else if ("architecture".equals(fieldName)) {
                    deserializedManifestAttributesBase.architecture
                        = ArtifactArchitecture.fromString(reader.getString());
                } else if ("os".equals(fieldName)) {
                    deserializedManifestAttributesBase.operatingSystem
                        = ArtifactOperatingSystem.fromString(reader.getString());
                } else if ("references".equals(fieldName)) {
                    List<ArtifactManifestPlatform> relatedArtifacts
                        = reader.readArray(reader1 -> ArtifactManifestPlatform.fromJson(reader1));
                    deserializedManifestAttributesBase.relatedArtifacts = relatedArtifacts;
                } else if ("tags".equals(fieldName)) {
                    List<String> tags = reader.readArray(reader1 -> reader1.getString());
                    deserializedManifestAttributesBase.tags = tags;
                } else if ("changeableAttributes".equals(fieldName)
                    && reader.currentToken() == JsonToken.START_OBJECT) {
                    while (reader.nextToken() != JsonToken.END_OBJECT) {
                        fieldName = reader.getFieldName();
                        reader.nextToken();

                        if ("deleteEnabled".equals(fieldName)) {
                            deserializedManifestAttributesBase.deleteEnabled
                                = reader.getNullable(JsonReader::getBoolean);
                        } else if ("writeEnabled".equals(fieldName)) {
                            deserializedManifestAttributesBase.writeEnabled
                                = reader.getNullable(JsonReader::getBoolean);
                        } else if ("listEnabled".equals(fieldName)) {
                            deserializedManifestAttributesBase.listEnabled = reader.getNullable(JsonReader::getBoolean);
                        } else if ("readEnabled".equals(fieldName)) {
                            deserializedManifestAttributesBase.readEnabled = reader.getNullable(JsonReader::getBoolean);
                        } else {
                            reader.skipChildren();
                        }
                    }
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedManifestAttributesBase;
        });
    }
}
