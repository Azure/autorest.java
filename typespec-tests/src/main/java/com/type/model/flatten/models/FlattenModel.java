// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.model.flatten.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;

import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;

import java.io.IOException;

/**
 * This is the model with one level of flattening.
 */
@Immutable
public final class FlattenModel implements JsonSerializable<FlattenModel> {
    /*
     * The name property.
     */
    @Generated
    private final String name;

    /*
     * The properties property.
     */
    @Generated
    private final ChildModel properties;

    /**
     * Creates an instance of FlattenModel class.
     * 
     * @param name the name value to set.
     * @param properties the properties value to set.
     */
    @Generated
    public FlattenModel(String name, ChildModel properties) {
        this.name = name;
        this.properties = properties;
    }

    /**
     * Get the name property: The name property.
     * 
     * @return the name value.
     */
    @Generated
    public String getName() {
        return this.name;
    }

    /**
     * Get the properties property: The properties property.
     * 
     * @return the properties value.
     */
    @Generated
    public ChildModel getProperties() {
        return this.properties;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("name", this.name);
        jsonWriter.writeJsonField("properties", this.properties);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of FlattenModel from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of FlattenModel if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the FlattenModel.
     */
    public static FlattenModel fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            String name = null;
            ChildModel properties = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("name".equals(fieldName)) {
                    name = reader.getString();
                } else if ("properties".equals(fieldName)) {
                    properties = ChildModel.fromJson(reader);
                } else {
                    reader.skipChildren();
                }
            }
            return new FlattenModel(name, properties);
        });
    }
}
