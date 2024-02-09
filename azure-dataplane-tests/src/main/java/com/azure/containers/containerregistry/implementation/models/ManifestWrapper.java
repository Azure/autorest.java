// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.containers.containerregistry.implementation.models;

import com.azure.containers.containerregistry.models.OciAnnotations;
import com.azure.containers.containerregistry.models.OciDescriptor;
import com.azure.core.annotation.Fluent;

import com.azure.json.JsonReader;

import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

import java.util.List;

/**
 * Returns the requested manifest file.
 */
@Fluent
public final class ManifestWrapper extends Manifest {
    /*
     * Media type for this Manifest
     */
    private String mediaType;

    /*
     * (ManifestList, OCIIndex) List of V2 image layer information
     */
    private List<ManifestListAttributes> manifests;

    /*
     * (V2, OCI) Image config descriptor
     */
    private OciDescriptor config;

    /*
     * (V2, OCI) List of V2 image layer information
     */
    private List<OciDescriptor> layers;

    /*
     * (OCI, OCIIndex) Additional metadata
     */
    private OciAnnotations annotations;

    /*
     * (V1) CPU architecture
     */
    private String architecture;

    /*
     * (V1) Image name
     */
    private String name;

    /*
     * (V1) Image tag
     */
    private String tag;

    /*
     * (V1) List of layer information
     */
    private List<FsLayer> fsLayers;

    /*
     * (V1) Image history
     */
    private List<History> history;

    /*
     * (V1) Image signature
     */
    private List<ImageSignature> signatures;

    /**
     * Creates an instance of ManifestWrapper class.
     */
    public ManifestWrapper() {
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
     * @return the ManifestWrapper object itself.
     */
    public ManifestWrapper setMediaType(String mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    /**
     * Get the manifests property: (ManifestList, OCIIndex) List of V2 image layer information.
     * 
     * @return the manifests value.
     */
    public List<ManifestListAttributes> getManifests() {
        return this.manifests;
    }

    /**
     * Set the manifests property: (ManifestList, OCIIndex) List of V2 image layer information.
     * 
     * @param manifests the manifests value to set.
     * @return the ManifestWrapper object itself.
     */
    public ManifestWrapper setManifests(List<ManifestListAttributes> manifests) {
        this.manifests = manifests;
        return this;
    }

    /**
     * Get the config property: (V2, OCI) Image config descriptor.
     * 
     * @return the config value.
     */
    public OciDescriptor getConfig() {
        return this.config;
    }

    /**
     * Set the config property: (V2, OCI) Image config descriptor.
     * 
     * @param config the config value to set.
     * @return the ManifestWrapper object itself.
     */
    public ManifestWrapper setConfig(OciDescriptor config) {
        this.config = config;
        return this;
    }

    /**
     * Get the layers property: (V2, OCI) List of V2 image layer information.
     * 
     * @return the layers value.
     */
    public List<OciDescriptor> getLayers() {
        return this.layers;
    }

    /**
     * Set the layers property: (V2, OCI) List of V2 image layer information.
     * 
     * @param layers the layers value to set.
     * @return the ManifestWrapper object itself.
     */
    public ManifestWrapper setLayers(List<OciDescriptor> layers) {
        this.layers = layers;
        return this;
    }

    /**
     * Get the annotations property: (OCI, OCIIndex) Additional metadata.
     * 
     * @return the annotations value.
     */
    public OciAnnotations getAnnotations() {
        return this.annotations;
    }

    /**
     * Set the annotations property: (OCI, OCIIndex) Additional metadata.
     * 
     * @param annotations the annotations value to set.
     * @return the ManifestWrapper object itself.
     */
    public ManifestWrapper setAnnotations(OciAnnotations annotations) {
        this.annotations = annotations;
        return this;
    }

    /**
     * Get the architecture property: (V1) CPU architecture.
     * 
     * @return the architecture value.
     */
    public String getArchitecture() {
        return this.architecture;
    }

    /**
     * Set the architecture property: (V1) CPU architecture.
     * 
     * @param architecture the architecture value to set.
     * @return the ManifestWrapper object itself.
     */
    public ManifestWrapper setArchitecture(String architecture) {
        this.architecture = architecture;
        return this;
    }

    /**
     * Get the name property: (V1) Image name.
     * 
     * @return the name value.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the name property: (V1) Image name.
     * 
     * @param name the name value to set.
     * @return the ManifestWrapper object itself.
     */
    public ManifestWrapper setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get the tag property: (V1) Image tag.
     * 
     * @return the tag value.
     */
    public String getTag() {
        return this.tag;
    }

    /**
     * Set the tag property: (V1) Image tag.
     * 
     * @param tag the tag value to set.
     * @return the ManifestWrapper object itself.
     */
    public ManifestWrapper setTag(String tag) {
        this.tag = tag;
        return this;
    }

    /**
     * Get the fsLayers property: (V1) List of layer information.
     * 
     * @return the fsLayers value.
     */
    public List<FsLayer> getFsLayers() {
        return this.fsLayers;
    }

    /**
     * Set the fsLayers property: (V1) List of layer information.
     * 
     * @param fsLayers the fsLayers value to set.
     * @return the ManifestWrapper object itself.
     */
    public ManifestWrapper setFsLayers(List<FsLayer> fsLayers) {
        this.fsLayers = fsLayers;
        return this;
    }

    /**
     * Get the history property: (V1) Image history.
     * 
     * @return the history value.
     */
    public List<History> getHistory() {
        return this.history;
    }

    /**
     * Set the history property: (V1) Image history.
     * 
     * @param history the history value to set.
     * @return the ManifestWrapper object itself.
     */
    public ManifestWrapper setHistory(List<History> history) {
        this.history = history;
        return this;
    }

    /**
     * Get the signatures property: (V1) Image signature.
     * 
     * @return the signatures value.
     */
    public List<ImageSignature> getSignatures() {
        return this.signatures;
    }

    /**
     * Set the signatures property: (V1) Image signature.
     * 
     * @param signatures the signatures value to set.
     * @return the ManifestWrapper object itself.
     */
    public ManifestWrapper setSignatures(List<ImageSignature> signatures) {
        this.signatures = signatures;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ManifestWrapper setSchemaVersion(Integer schemaVersion) {
        super.setSchemaVersion(schemaVersion);
        return this;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeNumberField("schemaVersion", getSchemaVersion());
        jsonWriter.writeStringField("mediaType", this.mediaType);
        jsonWriter.writeArrayField("manifests", this.manifests, (writer, element) -> writer.writeJson(element));
        jsonWriter.writeJsonField("config", this.config);
        jsonWriter.writeArrayField("layers", this.layers, (writer, element) -> writer.writeJson(element));
        jsonWriter.writeJsonField("annotations", this.annotations);
        jsonWriter.writeStringField("architecture", this.architecture);
        jsonWriter.writeStringField("name", this.name);
        jsonWriter.writeStringField("tag", this.tag);
        jsonWriter.writeArrayField("fsLayers", this.fsLayers, (writer, element) -> writer.writeJson(element));
        jsonWriter.writeArrayField("history", this.history, (writer, element) -> writer.writeJson(element));
        jsonWriter.writeArrayField("signatures", this.signatures, (writer, element) -> writer.writeJson(element));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ManifestWrapper from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of ManifestWrapper if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IOException If an error occurs while reading the ManifestWrapper.
     */
    public static ManifestWrapper fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ManifestWrapper deserializedManifestWrapper = new ManifestWrapper();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("schemaVersion".equals(fieldName)) {
                    deserializedManifestWrapper.setSchemaVersion(reader.getNullable(JsonReader::getInt));
                } else if ("mediaType".equals(fieldName)) {
                    deserializedManifestWrapper.mediaType = reader.getString();
                } else if ("manifests".equals(fieldName)) {
                    List<ManifestListAttributes> manifests
                        = reader.readArray(reader1 -> ManifestListAttributes.fromJson(reader1));
                    deserializedManifestWrapper.manifests = manifests;
                } else if ("config".equals(fieldName)) {
                    deserializedManifestWrapper.config = OciDescriptor.fromJson(reader);
                } else if ("layers".equals(fieldName)) {
                    List<OciDescriptor> layers = reader.readArray(reader1 -> OciDescriptor.fromJson(reader1));
                    deserializedManifestWrapper.layers = layers;
                } else if ("annotations".equals(fieldName)) {
                    deserializedManifestWrapper.annotations = OciAnnotations.fromJson(reader);
                } else if ("architecture".equals(fieldName)) {
                    deserializedManifestWrapper.architecture = reader.getString();
                } else if ("name".equals(fieldName)) {
                    deserializedManifestWrapper.name = reader.getString();
                } else if ("tag".equals(fieldName)) {
                    deserializedManifestWrapper.tag = reader.getString();
                } else if ("fsLayers".equals(fieldName)) {
                    List<FsLayer> fsLayers = reader.readArray(reader1 -> FsLayer.fromJson(reader1));
                    deserializedManifestWrapper.fsLayers = fsLayers;
                } else if ("history".equals(fieldName)) {
                    List<History> history = reader.readArray(reader1 -> History.fromJson(reader1));
                    deserializedManifestWrapper.history = history;
                } else if ("signatures".equals(fieldName)) {
                    List<ImageSignature> signatures = reader.readArray(reader1 -> ImageSignature.fromJson(reader1));
                    deserializedManifestWrapper.signatures = signatures;
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedManifestWrapper;
        });
    }
}
