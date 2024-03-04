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
 * This is the model with two levels of flattening.
 */
@Immutable
public final class NestedFlattenModel implements JsonSerializable<NestedFlattenModel> {
    /*
     * The name property.
     */
    @Generated
    private final String name;

    /*
     * The properties property.
     */
    @Generated
    private final ChildFlattenModel properties;

    /**
     * Creates an instance of NestedFlattenModel class.
     * 
     * @param name the name value to set.
     * @param properties the properties value to set.
     */
    @Generated
    public NestedFlattenModel(String name, ChildFlattenModel properties) {
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
    public ChildFlattenModel getProperties() {
        return this.properties;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("name", this.name);
        jsonWriter.writeJsonField("properties", this.properties);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of NestedFlattenModel from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of NestedFlattenModel if the JsonReader was pointing to an instance of it, or null if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the NestedFlattenModel.
     */
    @Generated
    public static NestedFlattenModel fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            String name = null;
            ChildFlattenModel properties = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("name".equals(fieldName)) {
                    name = reader.getString();
                } else if ("properties".equals(fieldName)) {
                    properties = ChildFlattenModel.fromJson(reader);
                } else {
                    reader.skipChildren();
                }
            }
            return new NestedFlattenModel(name, properties);
        });
    }
}
