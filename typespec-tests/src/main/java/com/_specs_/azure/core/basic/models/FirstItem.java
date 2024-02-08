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
 * First item.
 */
@Immutable
public final class FirstItem implements JsonSerializable<FirstItem> {

    /*
     * The id of the item.
     */
    @Generated
    private int id;

    /**
     * Creates an instance of FirstItem class.
     */
    @Generated
    private FirstItem() {
    }

    /**
     * Get the id property: The id of the item.
     *
     * @return the id value.
     */
    @Generated
    public int getId() {
        return this.id;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of FirstItem from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of FirstItem if the JsonReader was pointing to an instance of it, or null if it was pointing
     * to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the FirstItem.
     */
    public static FirstItem fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            int id = 0;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();
                if ("id".equals(fieldName)) {
                    id = reader.getInt();
                } else {
                    reader.skipChildren();
                }
            }
            FirstItem deserializedFirstItem = new FirstItem();
            deserializedFirstItem.id = id;
            return deserializedFirstItem;
        });
    }
}
