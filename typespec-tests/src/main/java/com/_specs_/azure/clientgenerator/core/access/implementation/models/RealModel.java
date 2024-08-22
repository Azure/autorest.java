// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com._specs_.azure.clientgenerator.core.access.implementation.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.json.JsonReader;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * Used in internal operations, should be generated but not exported.
 */
@Immutable
public final class RealModel extends AbstractModel {
    /*
     * Discriminator property for AbstractModel.
     */
    @Generated
    private String kind = "real";

    /**
     * Creates an instance of RealModel class.
     * 
     * @param name the name value to set.
     */
    @Generated
    private RealModel(String name) {
        super(name);
    }

    /**
     * Get the kind property: Discriminator property for AbstractModel.
     * 
     * @return the kind value.
     */
    @Generated
    public String getKind() {
        return this.kind;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("name", getName());
        jsonWriter.writeStringField("kind", this.kind);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of RealModel from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of RealModel if the JsonReader was pointing to an instance of it, or null if it was pointing
     * to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the RealModel.
     */
    @Generated
    public static RealModel fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            String name = null;
            String kind = "real";
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("name".equals(fieldName)) {
                    name = reader.getString();
                } else if ("kind".equals(fieldName)) {
                    kind = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }
            RealModel deserializedRealModel = new RealModel(name);
            deserializedRealModel.kind = kind;

            return deserializedRealModel;
        });
    }
}
