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
 * Test extensible enum type for discriminator.
 */
@Immutable
public class Dog implements JsonSerializable<Dog> {
    /*
     * discriminator property
     */
    @Generated
    private DogKind kind;

    /*
     * Weight of the dog
     */
    @Generated
    private final int weight;

    /**
     * Creates an instance of Dog class.
     * 
     * @param weight the weight value to set.
     */
    @Generated
    public Dog(int weight) {
        this.kind = DogKind.fromString("Dog");
        this.weight = weight;
    }

    /**
     * Get the kind property: discriminator property.
     * 
     * @return the kind value.
     */
    @Generated
    public DogKind getKind() {
        return this.kind;
    }

    /**
     * Get the weight property: Weight of the dog.
     * 
     * @return the weight value.
     */
    @Generated
    public int getWeight() {
        return this.weight;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeIntField("weight", this.weight);
        jsonWriter.writeStringField("kind", this.kind == null ? null : this.kind.toString());
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of Dog from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of Dog if the JsonReader was pointing to an instance of it, or null if it was pointing to
     * JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the Dog.
     */
    @Generated
    public static Dog fromJson(JsonReader jsonReader) throws IOException {
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
                if ("golden".equals(discriminatorValue)) {
                    return Golden.fromJson(readerToUse.reset());
                } else {
                    return fromJsonKnownDiscriminator(readerToUse.reset());
                }
            }
        });
    }

    @Generated
    static Dog fromJsonKnownDiscriminator(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            int weight = 0;
            DogKind kind = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("weight".equals(fieldName)) {
                    weight = reader.getInt();
                } else if ("kind".equals(fieldName)) {
                    kind = DogKind.fromString(reader.getString());
                } else {
                    reader.skipChildren();
                }
            }
            Dog deserializedDog = new Dog(weight);
            deserializedDog.kind = kind;

            return deserializedDog;
        });
    }
}
