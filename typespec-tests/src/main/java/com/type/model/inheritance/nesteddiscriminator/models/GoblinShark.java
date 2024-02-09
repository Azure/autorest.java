// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.model.inheritance.nesteddiscriminator.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;

import com.azure.json.JsonReader;

import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;

import java.io.IOException;

/**
 * The third level model GoblinShark in polymorphic multiple levels inheritance.
 */
@Immutable
public final class GoblinShark extends Shark {
    /**
     * Creates an instance of GoblinShark class.
     * 
     * @param age the age value to set.
     * @param sharktype the sharktype value to set.
     */
    @Generated
    public GoblinShark(int age, String sharktype) {
        super(age, sharktype);
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeIntField("age", getAge());
        jsonWriter.writeStringField("sharktype", getSharktype());
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of GoblinShark from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of GoblinShark if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties or the
     * polymorphic discriminator.
     * @throws IOException If an error occurs while reading the GoblinShark.
     */
    public static GoblinShark fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            int age = 0;
            String sharktype = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("sharktype".equals(fieldName)) {
                    sharktype = reader.getString();
                    if (!"goblin".equals(sharktype)) {
                        throw new IllegalStateException(
                            "'sharktype' was expected to be non-null and equal to 'goblin'. The found 'sharktype' was '"
                                + sharktype + "'.");
                    }
                } else if ("age".equals(fieldName)) {
                    age = reader.getInt();
                } else {
                    reader.skipChildren();
                }
            }
            return new GoblinShark(age, sharktype);
        });
    }
}
