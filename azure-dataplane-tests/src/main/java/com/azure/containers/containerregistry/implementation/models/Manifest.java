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

/** Returns the requested manifest file. */
@Fluent
public class Manifest implements JsonSerializable<Manifest> {
    /*
     * Schema version
     */
    private Integer schemaVersion;

    /** Creates an instance of Manifest class. */
    public Manifest() {}

    /**
     * Get the schemaVersion property: Schema version.
     *
     * @return the schemaVersion value.
     */
    public Integer getSchemaVersion() {
        return this.schemaVersion;
    }

    /**
     * Set the schemaVersion property: Schema version.
     *
     * @param schemaVersion the schemaVersion value to set.
     * @return the Manifest object itself.
     */
    public Manifest setSchemaVersion(Integer schemaVersion) {
        this.schemaVersion = schemaVersion;
        return this;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeNumberField("schemaVersion", this.schemaVersion);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of Manifest from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of Manifest if the JsonReader was pointing to an instance of it, or null if it was pointing
     *     to JSON null.
     * @throws IOException If an error occurs while reading the Manifest.
     */
    public static Manifest fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(
                reader -> {
                    Manifest deserializedManifest = new Manifest();
                    while (reader.nextToken() != JsonToken.END_OBJECT) {
                        String fieldName = reader.getFieldName();
                        reader.nextToken();

                        if ("schemaVersion".equals(fieldName)) {
                            deserializedManifest.schemaVersion = reader.getNullable(JsonReader::getInt);
                        } else {
                            reader.skipChildren();
                        }
                    }

                    return deserializedManifest;
                });
    }
}
