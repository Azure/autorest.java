// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com._specs_.azure.core.basic.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * Second item.
 */
@Immutable
public final class SecondItem implements JsonSerializable<SecondItem> {
    /*
     * The name of the item.
     */
    @Generated
    private String name;

    /**
     * Creates an instance of SecondItem class.
     */
    @Generated
    private SecondItem() {
    }

    /**
     * Get the name property: The name of the item.
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
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of SecondItem from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of SecondItem if the JsonReader was pointing to an instance of it, or null if it was pointing
     * to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the SecondItem.
     */
    public static SecondItem fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            String name = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("name".equals(fieldName)) {
                    name = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }
            SecondItem deserializedSecondItem = new SecondItem();
            deserializedSecondItem.name = name;

            return deserializedSecondItem;
        });
    }
}
