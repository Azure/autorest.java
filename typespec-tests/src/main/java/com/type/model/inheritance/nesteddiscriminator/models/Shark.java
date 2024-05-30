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
 * The second level model in polymorphic multiple levels inheritance and it defines a new discriminator.
 */
@Immutable
public class Shark extends Fish {
    /*
     * The kind property.
     */
    @Generated
    private String kind = "shark";

    /*
     * The sharktype property.
     */
    @Generated
    private String sharktype = "shark";

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
     * Get the kind property: The kind property.
     * 
     * @return the kind value.
     */
    @Generated
    @Override
    public String getKind() {
        return this.kind;
    }

    /**
     * Get the sharktype property: The sharktype property.
     * 
     * @return the sharktype value.
     */
    @Generated
    public String getSharktype() {
        return this.sharktype;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("kind", this.kind);
        jsonWriter.writeIntField("age", getAge());
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
    @Generated
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

    @Generated
    static Shark fromJsonKnownDiscriminator(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            int age = 0;
            String sharktype = "shark";
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("age".equals(fieldName)) {
                    age = reader.getInt();
                } else if ("sharktype".equals(fieldName)) {
                    sharktype = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }
            Shark deserializedShark = new Shark(age);
            deserializedShark.sharktype = sharktype;

            return deserializedShark;
        });
    }
}
