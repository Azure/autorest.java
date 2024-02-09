// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.patch.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;

import com.azure.json.JsonReader;

import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;

import com.cadl.patch.implementation.JsonMergePatchHelper;
import java.io.IOException;

import java.util.HashSet;

import java.util.Set;

/**
 * The second level model in polymorphic multiple levels inheritance and it defines a new discriminator.
 */
@Fluent
public final class Shark extends Fish {
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
        JsonMergePatchHelper.setSharkAccessor((model, jsonMergePatchEnabled) -> {
            model.serializeAsJsonMergePatch(jsonMergePatchEnabled);
            return model;
        });
    }

    /**
     * Creates an instance of Shark class.
     * 
     * @param age the age value to set.
     */
    @Generated
    public Shark(int age) {
        super(age);
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public Shark setColor(String color) {
        super.setColor(color);
        this.updatedProperties.add("color");
        return this;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        if (jsonMergePatch) {
            return toJsonMergePatch(jsonWriter);
        } else {
            jsonWriter.writeStartObject();
            jsonWriter.writeStringField("kind", "shark");
            jsonWriter.writeIntField("age", getAge());
            jsonWriter.writeStringField("color", getColor());
            return jsonWriter.writeEndObject();
        }
    }

    public JsonWriter toJsonMergePatch(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("kind", "shark");
        jsonWriter.writeIntField("age", getAge());
        if (getColor() != null) {
            jsonWriter.writeStringField("color", getColor());
        } else if (updatedProperties.contains("color")) {
            jsonWriter.writeNullField("color");
        }
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of Shark from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of Shark if the JsonReader was pointing to an instance of it, or null if it was pointing to
     * JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties or the
     * polymorphic discriminator.
     * @throws IOException If an error occurs while reading the Shark.
     */
    public static Shark fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            String id = null;
            String name = null;
            int age = 0;
            String color = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("kind".equals(fieldName)) {
                    String kind = reader.getString();
                    if (!"shark".equals(kind)) {
                        throw new IllegalStateException(
                            "'kind' was expected to be non-null and equal to 'shark'. The found 'kind' was '" + kind
                                + "'.");
                    }
                } else if ("id".equals(fieldName)) {
                    id = reader.getString();
                } else if ("name".equals(fieldName)) {
                    name = reader.getString();
                } else if ("age".equals(fieldName)) {
                    age = reader.getInt();
                } else if ("color".equals(fieldName)) {
                    color = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }
            Shark deserializedShark = new Shark(age);
            deserializedShark.setId(id);
            deserializedShark.setName(name);
            deserializedShark.setColor(color);

            return deserializedShark;
        });
    }
}
