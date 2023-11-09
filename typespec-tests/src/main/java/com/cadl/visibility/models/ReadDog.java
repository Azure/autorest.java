// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.visibility.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The ReadDog model.
 */
@Immutable
public final class ReadDog implements JsonSerializable<ReadDog> {
    /*
     * The id property.
     */
    @Generated
    private final int id;

    /*
     * The name property.
     */
    @Generated
    private final String name;

    /**
     * Creates an instance of ReadDog class.
     * 
     * @param id the id value to set.
     * @param name the name value to set.
     */
    @Generated
    public ReadDog(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Get the id property: The id property.
     * 
     * @return the id value.
     */
    @Generated
    public int getId() {
        return this.id;
    }

    /**
     * Get the name property: The name property.
     * 
     * @return the name value.
     */
    @Generated
    public String getName() {
        return this.name;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeIntField("id", this.id);
        jsonWriter.writeStringField("name", this.name);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ReadDog from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of ReadDog if the JsonReader was pointing to an instance of it, or null if it was pointing to
     * JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the ReadDog.
     */
    public static ReadDog fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            boolean idFound = false;
            int id = 0;
            boolean nameFound = false;
            String name = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("id".equals(fieldName)) {
                    id = reader.getInt();
                    idFound = true;
                } else if ("name".equals(fieldName)) {
                    name = reader.getString();
                    nameFound = true;
                } else {
                    reader.skipChildren();
                }
            }
            if (idFound && nameFound) {
                ReadDog deserializedReadDog = new ReadDog(id, name);

                return deserializedReadDog;
            }
            List<String> missingProperties = new ArrayList<>();
            if (!idFound) {
                missingProperties.add("id");
            }
            if (!nameFound) {
                missingProperties.add("name");
            }

            throw new IllegalStateException(
                "Missing required property/properties: " + String.join(", ", missingProperties));
        });
    }
}
