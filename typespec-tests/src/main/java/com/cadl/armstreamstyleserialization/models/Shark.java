// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.armstreamstyleserialization.models;

import com.azure.core.annotation.Immutable;
import com.azure.json.JsonReader;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import com.cadl.armstreamstyleserialization.fluent.models.FishInner;
import java.io.IOException;

/**
 * The second level model in polymorphic multiple levels inheritance and it defines a new discriminator.
 */
@Immutable
public class Shark extends FishInner {
    /*
     * Discriminator property for Fish.
     */
    private String kind = "shark";

    /*
     * The sharktype property.
     */
    private String sharktype = "shark";

    /*
     * The age property.
     */
    private int age;

    /*
     * The dna property.
     */
    private String dna;

    /**
     * Creates an instance of Shark class.
     */
    protected Shark() {
    }

    /**
     * Get the kind property: Discriminator property for Fish.
     * 
     * @return the kind value.
     */
    @Override
    public String kind() {
        return this.kind;
    }

    /**
     * Get the sharktype property: The sharktype property.
     * 
     * @return the sharktype value.
     */
    public String sharktype() {
        return this.sharktype;
    }

    /**
     * Get the age property: The age property.
     * 
     * @return the age value.
     */
    @Override
    public int age() {
        return this.age;
    }

    /**
     * Get the dna property: The dna property.
     * 
     * @return the dna value.
     */
    @Override
    public String dna() {
        return this.dna;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    @Override
    public void validate() {
        super.validate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("kind", this.kind);
        jsonWriter.writeIntField("age", age());
        jsonWriter.writeStringField("dna", dna());
        jsonWriter.writeStringField("sharktype", this.sharktype);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of Shark from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of Shark if the JsonReader was pointing to an instance of it, or null if it was pointing to
     * JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the Shark.
     */
    public static Shark fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            String discriminatorValue = null;
            try (JsonReader readerToUse = reader.bufferObject()) {
                readerToUse.nextToken(); // Prepare for reading
                while (readerToUse.nextToken() != JsonToken.END_OBJECT) {
                    String fieldName = readerToUse.getFieldName();
                    readerToUse.nextToken();
                    if ("sharktype".equals(fieldName)) {
                        discriminatorValue = readerToUse.getString();
                        break;
                    } else {
                        readerToUse.skipChildren();
                    }
                }
                // Use the discriminator value to determine which subtype should be deserialized.
                if ("saw".equals(discriminatorValue)) {
                    return SawShark.fromJson(readerToUse.reset());
                } else if ("goblin".equals(discriminatorValue)) {
                    return GoblinShark.fromJson(readerToUse.reset());
                } else {
                    return fromJsonKnownDiscriminator(readerToUse.reset());
                }
            }
        });
    }

    static Shark fromJsonKnownDiscriminator(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            Shark deserializedShark = new Shark();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("age".equals(fieldName)) {
                    deserializedShark.age = reader.getInt();
                } else if ("dna".equals(fieldName)) {
                    deserializedShark.dna = reader.getString();
                } else if ("sharktype".equals(fieldName)) {
                    deserializedShark.sharktype = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedShark;
        });
    }
}
