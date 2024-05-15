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
 * This is the child model to be flattened.
 */
@Immutable
public final class ChildModel implements JsonSerializable<ChildModel> {
    /*
     * A sequence of textual characters.
     */
    @Generated
    private final String description;

    /*
     * A 32-bit integer. (`-2,147,483,648` to `2,147,483,647`)
     */
    @Generated
    private final int age;

    /**
     * Creates an instance of ChildModel class.
     * 
     * @param description the description value to set.
     * @param age the age value to set.
     */
    @Generated
    public ChildModel(String description, int age) {
        this.description = description;
        this.age = age;
    }

    /**
     * Get the description property: A sequence of textual characters.
     * 
     * @return the description value.
     */
    @Generated
    public String getDescription() {
        return this.description;
    }

    /**
     * Get the age property: A 32-bit integer. (`-2,147,483,648` to `2,147,483,647`).
     * 
     * @return the age value.
     */
    @Generated
    public int getAge() {
        return this.age;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("description", this.description);
        jsonWriter.writeIntField("age", this.age);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ChildModel from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of ChildModel if the JsonReader was pointing to an instance of it, or null if it was pointing
     * to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the ChildModel.
     */
    @Generated
    public static ChildModel fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            String description = null;
            int age = 0;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("description".equals(fieldName)) {
                    description = reader.getString();
                } else if ("age".equals(fieldName)) {
                    age = reader.getInt();
                } else {
                    reader.skipChildren();
                }
            }
            return new ChildModel(description, age);
        });
    }
}
