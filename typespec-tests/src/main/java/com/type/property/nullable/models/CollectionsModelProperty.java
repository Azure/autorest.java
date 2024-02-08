// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.
package com.type.property.nullable.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import com.type.property.nullable.implementation.JsonMergePatchHelper;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Model with collection models properties.
 */
@Immutable
public final class CollectionsModelProperty implements JsonSerializable<CollectionsModelProperty> {

    /*
     * Required property
     */
    @Generated
    private final String requiredProperty;

    /*
     * Property
     */
    @Generated
    private final List<InnerModel> nullableProperty;

    @Generated
    private boolean jsonMergePatch;

    /**
     * Stores updated model property, the value is property name, not serialized name.
     */
    @Generated
    private final Set<String> updatedProperties = new HashSet<>();

    @Generated
    void serializeAsJsonMergePatch(boolean jsonMergePatch) {
        this.jsonMergePatch = jsonMergePatch;
    }

    static {
        JsonMergePatchHelper.setCollectionsModelPropertyAccessor((model, jsonMergePatchEnabled) -> {
            model.serializeAsJsonMergePatch(jsonMergePatchEnabled);
            return model;
        });
    }

    /**
     * Creates an instance of CollectionsModelProperty class.
     *
     * @param requiredProperty the requiredProperty value to set.
     * @param nullableProperty the nullableProperty value to set.
     */
    @Generated
    public CollectionsModelProperty(String requiredProperty, List<InnerModel> nullableProperty) {
        this.requiredProperty = requiredProperty;
        this.nullableProperty = nullableProperty;
    }

    /**
     * Get the requiredProperty property: Required property.
     *
     * @return the requiredProperty value.
     */
    @Generated
    public String getRequiredProperty() {
        return this.requiredProperty;
    }

    /**
     * Get the nullableProperty property: Property.
     *
     * @return the nullableProperty value.
     */
    @Generated
    public List<InnerModel> getNullableProperty() {
        return this.nullableProperty;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        if (jsonMergePatch) {
            return toJsonMergePatch(jsonWriter);
        } else {
            jsonWriter.writeStartObject();
            jsonWriter.writeStringField("requiredProperty", this.requiredProperty);
            jsonWriter.writeArrayField("nullableProperty", this.nullableProperty,
                (writer, element) -> writer.writeJson(element));
            return jsonWriter.writeEndObject();
        }
    }

    public JsonWriter toJsonMergePatch(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        if (this.requiredProperty != null) {
            jsonWriter.writeStringField("requiredProperty", this.requiredProperty);
        } else if (updatedProperties.contains("requiredProperty")) {
            jsonWriter.writeNullField("requiredProperty");
        }
        if (this.nullableProperty != null) {
            jsonWriter.writeArrayField("nullableProperty", this.nullableProperty,
                (writer, element) -> writer.writeJson(element));
        } else if (updatedProperties.contains("nullableProperty")) {
            jsonWriter.writeNullField("nullableProperty");
        }
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of CollectionsModelProperty from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of CollectionsModelProperty if the JsonReader was pointing to an instance of it, or null if
     * it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the CollectionsModelProperty.
     */
    public static CollectionsModelProperty fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            String requiredProperty = null;
            List<InnerModel> nullableProperty = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();
                if ("requiredProperty".equals(fieldName)) {
                    requiredProperty = reader.getString();
                } else if ("nullableProperty".equals(fieldName)) {
                    nullableProperty = reader.readArray(reader1 -> InnerModel.fromJson(reader1));
                } else {
                    reader.skipChildren();
                }
            }
            return new CollectionsModelProperty(requiredProperty, nullableProperty);
        });
    }
}
