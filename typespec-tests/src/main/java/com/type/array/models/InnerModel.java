// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.array.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.util.List;

/**
 * Array inner model.
 */
@Fluent
public final class InnerModel implements JsonSerializable<InnerModel> {
    /*
     * Required string property
     */
    @Generated
    private final String property;

    /*
     * The children property.
     */
    @Generated
    private List<InnerModel> children;

    /**
     * Creates an instance of InnerModel class.
     * 
     * @param property the property value to set.
     */
    @Generated
    public InnerModel(String property) {
        this.property = property;
    }

    /**
     * Get the property property: Required string property.
     * 
     * @return the property value.
     */
    @Generated
    public String getProperty() {
        return this.property;
    }

    /**
     * Get the children property: The children property.
     * 
     * @return the children value.
     */
    @Generated
    public List<InnerModel> getChildren() {
        return this.children;
    }

    /**
     * Set the children property: The children property.
     * 
     * @param children the children value to set.
     * @return the InnerModel object itself.
     */
    @Generated
    public InnerModel setChildren(List<InnerModel> children) {
        this.children = children;
        return this;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("property", this.property);
        jsonWriter.writeArrayField("children", this.children, (writer, element) -> writer.writeJson(element));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of InnerModel from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of InnerModel if the JsonReader was pointing to an instance of it, or null if it was pointing
     * to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the InnerModel.
     */
    public static InnerModel fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            String property = null;
            List<InnerModel> children = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("property".equals(fieldName)) {
                    property = reader.getString();
                } else if ("children".equals(fieldName)) {
                    children = reader.readArray(reader1 -> InnerModel.fromJson(reader1));
                } else {
                    reader.skipChildren();
                }
            }
            InnerModel deserializedInnerModel = new InnerModel(property);
            deserializedInnerModel.children = children;

            return deserializedInnerModel;
        });
    }
}
