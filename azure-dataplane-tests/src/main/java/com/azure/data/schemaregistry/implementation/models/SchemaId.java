// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.data.schemaregistry.implementation.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * Object received from the registry containing schema identifiers.
 */
@Fluent
public final class SchemaId implements JsonSerializable<SchemaId> {
    /*
     * Schema ID that uniquely identifies a schema in the registry namespace.
     */
    @Generated
    private String id;

    /**
     * Creates an instance of SchemaId class.
     */
    @Generated
    public SchemaId() {
    }

    /**
     * Get the id property: Schema ID that uniquely identifies a schema in the registry namespace.
     * 
     * @return the id value.
     */
    @Generated
    public String getId() {
        return this.id;
    }

    /**
     * Set the id property: Schema ID that uniquely identifies a schema in the registry namespace.
     * 
     * @param id the id value to set.
     * @return the SchemaId object itself.
     */
    @Generated
    public SchemaId setId(String id) {
        this.id = id;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("id", this.id);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of SchemaId from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of SchemaId if the JsonReader was pointing to an instance of it, or null if it was pointing
     * to JSON null.
     * @throws IOException If an error occurs while reading the SchemaId.
     */
    @Generated
    public static SchemaId fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            SchemaId deserializedSchemaId = new SchemaId();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("id".equals(fieldName)) {
                    deserializedSchemaId.id = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedSchemaId;
        });
    }
}
