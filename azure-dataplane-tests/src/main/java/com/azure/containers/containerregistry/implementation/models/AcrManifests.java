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
import java.util.List;

/**
 * Manifest attributes.
 */
@Fluent
public final class AcrManifests implements JsonSerializable<AcrManifests> {
    /*
     * Registry login server name. This is likely to be similar to {registry-name}.azurecr.io.
     */
    private String registryLoginServer;

    /*
     * Image name
     */
    private String repository;

    /*
     * List of manifests
     */
    private List<ManifestAttributesBase> manifests;

    /*
     * The link property.
     */
    private String link;

    /**
     * Creates an instance of AcrManifests class.
     */
    public AcrManifests() {
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
     * @return the AcrManifests object itself.
     */
    public AcrManifests setRegistryLoginServer(String registryLoginServer) {
        this.registryLoginServer = registryLoginServer;
        return this;
    }

    /**
     * Get the repository property: Image name.
     * 
     * @return the repository value.
     */
    public String getRepository() {
        return this.repository;
    }

    /**
     * Set the repository property: Image name.
     * 
     * @param repository the repository value to set.
     * @return the AcrManifests object itself.
     */
    public AcrManifests setRepository(String repository) {
        this.repository = repository;
        return this;
    }

    /**
     * Get the manifests property: List of manifests.
     * 
     * @return the manifests value.
     */
    public List<ManifestAttributesBase> getManifests() {
        return this.manifests;
    }

    /**
     * Set the manifests property: List of manifests.
     * 
     * @param manifests the manifests value to set.
     * @return the AcrManifests object itself.
     */
    public AcrManifests setManifests(List<ManifestAttributesBase> manifests) {
        this.manifests = manifests;
        return this;
    }

    /**
     * Get the link property: The link property.
     * 
     * @return the link value.
     */
    public String getLink() {
        return this.link;
    }

    /**
     * Set the link property: The link property.
     * 
     * @param link the link value to set.
     * @return the AcrManifests object itself.
     */
    public AcrManifests setLink(String link) {
        this.link = link;
        return this;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("registry", this.registryLoginServer);
        jsonWriter.writeStringField("imageName", this.repository);
        jsonWriter.writeArrayField("manifests", this.manifests, (writer, element) -> writer.writeJson(element));
        jsonWriter.writeStringField("link", this.link);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of AcrManifests from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of AcrManifests if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IOException If an error occurs while reading the AcrManifests.
     */
    public static AcrManifests fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            AcrManifests deserializedAcrManifests = new AcrManifests();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("registry".equals(fieldName)) {
                    deserializedAcrManifests.registryLoginServer = reader.getString();
                } else if ("imageName".equals(fieldName)) {
                    deserializedAcrManifests.repository = reader.getString();
                } else if ("manifests".equals(fieldName)) {
                    List<ManifestAttributesBase> manifests
                        = reader.readArray(reader1 -> ManifestAttributesBase.fromJson(reader1));
                    deserializedAcrManifests.manifests = manifests;
                } else if ("link".equals(fieldName)) {
                    deserializedAcrManifests.link = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedAcrManifests;
        });
    }
}
