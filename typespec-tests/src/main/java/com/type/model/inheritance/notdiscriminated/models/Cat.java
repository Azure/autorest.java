// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.model.inheritance.notdiscriminated.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.json.JsonReader;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * The second level model in the normal multiple levels inheritance.
 */
@Immutable
public class Cat extends Pet {
    /*
     * The age property.
     */
    @Generated
    private final int age;

    /**
     * Creates an instance of Cat class.
     * 
     * @param name the name value to set.
     * @param age the age value to set.
     */
    @Generated
    public Cat(String name, int age) {
        super(name);
        this.age = age;
    }

    /**
     * Get the age property: The age property.
     * 
     * @return the age value.
     */
    @Generated
    public int getAge() {
        return this.age;
    }

    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("name", getName());
        jsonWriter.writeIntField("age", this.age);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of Cat from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of Cat if the JsonReader was pointing to an instance of it, or null if it was pointing to
     * JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the Cat.
     */
    @Generated
    public static Cat fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            String name = null;
            int age = 0;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("name".equals(fieldName)) {
                    name = reader.getString();
                } else if ("age".equals(fieldName)) {
                    age = reader.getInt();
                } else {
                    reader.skipChildren();
                }
            }
            return new Cat(name, age);
        });
    }
}
