// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.model.inheritance.singlediscriminator.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/** This is base model for polymorphic single level inheritance with a discriminator. */
@Immutable
public class Bird implements JsonSerializable<Bird> {
    /*
     * The wingspan property.
     */
    @Generated private final int wingspan;

    /**
     * Creates an instance of Bird class.
     *
     * @param wingspan the wingspan value to set.
     */
    @Generated
    public Bird(int wingspan) {
        this.wingspan = wingspan;
    }

    /**
     * Get the wingspan property: The wingspan property.
     *
     * @return the wingspan value.
     */
    @Generated
    public int getWingspan() {
        return this.wingspan;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeIntField("wingspan", this.wingspan);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of Bird from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of Bird if the JsonReader was pointing to an instance of it, or null if it was pointing to
     *     JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties or the
     *     polymorphic discriminator.
     * @throws IOException If an error occurs while reading the Bird.
     */
    public static Bird fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(
                reader -> {
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
                    if ("seagull".equals(discriminatorValue)) {
                        return SeaGull.fromJson(readerToUse);
                    } else if ("sparrow".equals(discriminatorValue)) {
                        return Sparrow.fromJson(readerToUse);
                    } else if ("goose".equals(discriminatorValue)) {
                        return Goose.fromJson(readerToUse);
                    } else if ("eagle".equals(discriminatorValue)) {
                        return Eagle.fromJson(readerToUse);
                    } else {
                        throw new IllegalStateException(
                                "Discriminator field 'kind' didn't match one of the expected values 'seagull', 'sparrow', 'goose', or 'eagle'. It was: '"
                                        + discriminatorValue
                                        + "'.");
                    }
                });
    }
}
