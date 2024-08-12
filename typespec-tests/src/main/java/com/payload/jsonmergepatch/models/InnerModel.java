// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.payload.jsonmergepatch.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import com.payload.jsonmergepatch.implementation.JsonMergePatchHelper;
import java.io.IOException;

/**
 * It is the model used by Resource model.
 */
@Fluent
public final class InnerModel implements JsonSerializable<InnerModel> {
    /*
     * The name property.
     */
    @Generated
    private String name;

    /*
     * The description property.
     */
    @Generated
    private String description;

    /**
     * Stores updated model property, the value is property name, not serialized name.
     */
    @Generated
    private long updatedProperties = 0L;

    @Generated
    private boolean jsonMergePatch;

    @Generated
    private void serializeAsJsonMergePatch(boolean jsonMergePatch) {
        this.jsonMergePatch = jsonMergePatch;
    }

    static {
        JsonMergePatchHelper.setInnerModelAccessor(new JsonMergePatchHelper.InnerModelAccessor() {
            @Override
            public InnerModel prepareModelForJsonMergePatch(InnerModel model, boolean jsonMergePatchEnabled) {
                model.serializeAsJsonMergePatch(jsonMergePatchEnabled);
                return model;
            }

            @Override
            public boolean isJsonMergePatch(InnerModel model) {
                return model.jsonMergePatch;
            }
        });
    }

    /**
     * Creates an instance of InnerModel class.
     */
    @Generated
    public InnerModel() {
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
     * Set the name property: The name property.
     * 
     * @param name the name value to set.
     * @return the InnerModel object itself.
     */
    @Generated
    public InnerModel setName(String name) {
        this.name = name;
        this.updatedProperties |= 1;
        return this;
    }

    /**
     * Get the description property: The description property.
     * 
     * @return the description value.
     */
    @Generated
    public String getDescription() {
        return this.description;
    }

    /**
     * Set the description property: The description property.
     * 
     * @param description the description value to set.
     * @return the InnerModel object itself.
     */
    @Generated
    public InnerModel setDescription(String description) {
        this.description = description;
        this.updatedProperties |= 2;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        if (jsonMergePatch) {
            return toJsonMergePatch(jsonWriter);
        } else {
            jsonWriter.writeStartObject();
            jsonWriter.writeStringField("name", this.name);
            jsonWriter.writeStringField("description", this.description);
            return jsonWriter.writeEndObject();
        }
    }

    @Generated
    private JsonWriter toJsonMergePatch(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        if ((this.updatedProperties & 1) == 1) {
            if (this.name == null) {
                jsonWriter.writeNullField("name");
            } else {
                jsonWriter.writeStringField("name", this.name);
            }
        }
        if ((this.updatedProperties & 2) == 2) {
            if (this.description == null) {
                jsonWriter.writeNullField("description");
            } else {
                jsonWriter.writeStringField("description", this.description);
            }
        }
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of InnerModel from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of InnerModel if the JsonReader was pointing to an instance of it, or null if it was pointing
     * to JSON null.
     * @throws IOException If an error occurs while reading the InnerModel.
     */
    @Generated
    public static InnerModel fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            InnerModel deserializedInnerModel = new InnerModel();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("name".equals(fieldName)) {
                    deserializedInnerModel.name = reader.getString();
                } else if ("description".equals(fieldName)) {
                    deserializedInnerModel.description = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedInnerModel;
        });
    }
}
