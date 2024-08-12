// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.model.inheritance.enumdiscriminator.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * Test fixed enum type for discriminator.
 */
@Immutable
public class Snake implements JsonSerializable<Snake> {
    /*
     * discriminator property
     */
    @Generated
    private SnakeKind kind = SnakeKind.fromString("Snake");

    /*
     * Length of the snake
     */
    @Generated
    private final int length;

    /**
     * Creates an instance of Snake class.
     * 
     * @param length the length value to set.
     */
    @Generated
    public Snake(int length) {
        this.length = length;
    }

    /**
     * Get the kind property: discriminator property.
     * 
     * @return the kind value.
     */
    @Generated
    public SnakeKind getKind() {
        return this.kind;
    }

    /**
     * Get the length property: Length of the snake.
     * 
     * @return the length value.
     */
    @Generated
    public int getLength() {
        return this.length;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeIntField("length", this.length);
        jsonWriter.writeStringField("kind", this.kind == null ? null : this.kind.toString());
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of Snake from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of Snake if the JsonReader was pointing to an instance of it, or null if it was pointing to
     * JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the Snake.
     */
    @Generated
    public static Snake fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            String discriminatorValue = null;
            try (JsonReader readerToUse = reader.bufferObject()) {
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
                // Use the discriminator value to determine which subtype should be deserialized.
                if ("cobra".equals(discriminatorValue)) {
                    return Cobra.fromJson(readerToUse.reset());
                } else {
                    return fromJsonKnownDiscriminator(readerToUse.reset());
                }
            }
        });
    }

    @Generated
    static Snake fromJsonKnownDiscriminator(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            long foundTracker = 0;
            int length = 0;
            SnakeKind kind = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("length".equals(fieldName)) {
                    length = reader.getInt();
                } else if ("kind".equals(fieldName)) {
                    kind = SnakeKind.fromString(reader.getString());
                } else {
                    reader.skipChildren();
                }
            }
            Snake deserializedSnake = new Snake(length);
            deserializedSnake.kind = kind;

            return deserializedSnake;
        });
    }
}
