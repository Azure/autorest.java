// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.property.optional.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;

import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;

import java.io.IOException;

import java.util.List;

/**
 * Model with collection models properties.
 */
@Fluent
public final class CollectionsModelProperty implements JsonSerializable<CollectionsModelProperty> {
    /*
     * Property
     */
    @Generated
    private List<StringProperty> property;

    /**
     * Creates an instance of CollectionsModelProperty class.
     */
    @Generated
    public CollectionsModelProperty() {
    }

    /**
     * Get the property property: Property.
     * 
     * @return the property value.
     */
    @Generated
    public List<StringProperty> getProperty() {
        return this.property;
    }

    /**
     * Set the property property: Property.
     * 
     * @param property the property value to set.
     * @return the CollectionsModelProperty object itself.
     */
    @Generated
    public CollectionsModelProperty setProperty(List<StringProperty> property) {
        this.property = property;
        return this;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeArrayField("property", this.property, (writer, element) -> writer.writeJson(element));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of CollectionsModelProperty from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of CollectionsModelProperty if the JsonReader was pointing to an instance of it, or null if
     * it was pointing to JSON null.
     * @throws IOException If an error occurs while reading the CollectionsModelProperty.
     */
    public static CollectionsModelProperty fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            CollectionsModelProperty deserializedCollectionsModelProperty = new CollectionsModelProperty();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("property".equals(fieldName)) {
                    List<StringProperty> property = reader.readArray(reader1 -> StringProperty.fromJson(reader1));
                    deserializedCollectionsModelProperty.property = property;
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedCollectionsModelProperty;
        });
    }
}
