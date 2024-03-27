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
 * List of tag details.
 */
@Fluent
public final class TagList implements JsonSerializable<TagList> {
    /*
     * Registry login server name. This is likely to be similar to {registry-name}.azurecr.io.
     */
    private String registryLoginServer;

    /*
     * Image name
     */
    private String repository;

    /*
     * List of tag attribute details
     */
    private List<TagAttributesBase> tagAttributeBases;

    /*
     * The link property.
     */
    private String link;

    /**
     * Creates an instance of TagList class.
     */
    public TagList() {
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
     * @return the TagList object itself.
     */
    public TagList setRegistryLoginServer(String registryLoginServer) {
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
     * @return the TagList object itself.
     */
    public TagList setRepository(String repository) {
        this.repository = repository;
        return this;
    }

    /**
     * Get the tagAttributeBases property: List of tag attribute details.
     * 
     * @return the tagAttributeBases value.
     */
    public List<TagAttributesBase> getTagAttributeBases() {
        return this.tagAttributeBases;
    }

    /**
     * Set the tagAttributeBases property: List of tag attribute details.
     * 
     * @param tagAttributeBases the tagAttributeBases value to set.
     * @return the TagList object itself.
     */
    public TagList setTagAttributeBases(List<TagAttributesBase> tagAttributeBases) {
        this.tagAttributeBases = tagAttributeBases;
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
     * @return the TagList object itself.
     */
    public TagList setLink(String link) {
        this.link = link;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("registry", this.registryLoginServer);
        jsonWriter.writeStringField("imageName", this.repository);
        jsonWriter.writeArrayField("tags", this.tagAttributeBases, (writer, element) -> writer.writeJson(element));
        jsonWriter.writeStringField("link", this.link);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of TagList from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of TagList if the JsonReader was pointing to an instance of it, or null if it was pointing
     * to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the TagList.
     */
    public static TagList fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            TagList deserializedTagList = new TagList();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("registry".equals(fieldName)) {
                    deserializedTagList.registryLoginServer = reader.getString();
                } else if ("imageName".equals(fieldName)) {
                    deserializedTagList.repository = reader.getString();
                } else if ("tags".equals(fieldName)) {
                    List<TagAttributesBase> tagAttributeBases
                        = reader.readArray(reader1 -> TagAttributesBase.fromJson(reader1));
                    deserializedTagList.tagAttributeBases = tagAttributeBases;
                } else if ("link".equals(fieldName)) {
                    deserializedTagList.link = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedTagList;
        });
    }
}
