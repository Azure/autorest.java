// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.model.inheritance.nesteddiscriminator.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * This is base model for polymorphic multiple levels inheritance with a discriminator.
 */
@Immutable
public class Fish implements JsonSerializable<Fish> {
    /*
     * The age property.
     */
    @Generated
    private final int age;

    /**
     * Creates an instance of Fish class.
     * 
     * @param age the age value to set.
     */
    @Generated
    public Fish(int age) {
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

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeIntField("age", this.age);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of Fish from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of Fish if the JsonReader was pointing to an instance of it, or null if it was pointing to
     * JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties or the
     * polymorphic discriminator.
     * @throws IOException If an error occurs while reading the Fish.
     */
    public static Fish fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            String discriminatorValue = null;
            JsonReader readerToUse = reader.bufferObject();

            readerToUse.nextToken(); // Prepare for reading
            while (readerToUse.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = readerToUse.getFieldName();
                readerToUse.nextToken();
                if ("kind".equals(fieldName)) {
                    discriminatorValue = readerToUse.getString();
                    break;
                } else {
                    readerToUse.skipChildren();
                }
            }

            if (discriminatorValue != null) {
                readerToUse = readerToUse.reset();
            }
            // Use the discriminator value to determine which subtype should be deserialized.
            if ("shark".equals(discriminatorValue)) {
                return Shark.fromJsonKnownDiscriminator(readerToUse);
            } else if ("saw".equals(discriminatorValue)) {
                return SawShark.fromJson(readerToUse);
            } else if ("goblin".equals(discriminatorValue)) {
                return GoblinShark.fromJson(readerToUse);
            } else if ("salmon".equals(discriminatorValue)) {
                return Salmon.fromJson(readerToUse);
            } else {
                throw new IllegalStateException(
                    "Discriminator field 'kind' didn't match one of the expected values 'shark', 'saw', 'goblin', or 'salmon'. It was: '"
                        + discriminatorValue + "'.");
            }
        });
    }
}
