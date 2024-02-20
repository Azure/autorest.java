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
 * This is the child model to be flattened. And it has flattened property as well.
 */
@Immutable
public final class ChildFlattenModel implements JsonSerializable<ChildFlattenModel> {
    /*
     * The summary property.
     */
    @Generated
    private final String summary;

    /*
     * The properties property.
     */
    @Generated
    private final ChildModel properties;

    /**
     * Creates an instance of ChildFlattenModel class.
     * 
     * @param summary the summary value to set.
     * @param properties the properties value to set.
     */
    @Generated
    public ChildFlattenModel(String summary, ChildModel properties) {
        this.summary = summary;
        this.properties = properties;
    }

    /**
     * Get the summary property: The summary property.
     * 
     * @return the summary value.
     */
    @Generated
    public String getSummary() {
        return this.summary;
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

    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("summary", this.summary);
        jsonWriter.writeJsonField("properties", this.properties);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ChildFlattenModel from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of ChildFlattenModel if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the ChildFlattenModel.
     */
    @Generated
    public static ChildFlattenModel fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            String summary = null;
            ChildModel properties = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("summary".equals(fieldName)) {
                    summary = reader.getString();
                } else if ("properties".equals(fieldName)) {
                    properties = ChildModel.fromJson(reader);
                } else {
                    reader.skipChildren();
                }
            }
            return new ChildFlattenModel(summary, properties);
        });
    }
}
