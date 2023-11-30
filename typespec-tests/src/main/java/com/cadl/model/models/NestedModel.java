// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.model.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * The NestedModel model.
 */
@Immutable
public final class NestedModel implements JsonSerializable<NestedModel> {
    /*
     * The nested1 property.
     */
    @Generated
    private final NestedModel1 nested1;

    /**
     * Creates an instance of NestedModel class.
     * 
     * @param nested1 the nested1 value to set.
     */
    @Generated
    public NestedModel(NestedModel1 nested1) {
        this.nested1 = nested1;
    }

    /**
     * Get the nested1 property: The nested1 property.
     * 
     * @return the nested1 value.
     */
    @Generated
    public NestedModel1 getNested1() {
        return this.nested1;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeJsonField("nested1", this.nested1);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of NestedModel from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of NestedModel if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the NestedModel.
     */
    public static NestedModel fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            NestedModel1 nested1 = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("nested1".equals(fieldName)) {
                    nested1 = NestedModel1.fromJson(reader);
                } else {
                    reader.skipChildren();
                }
            }
            return new NestedModel(nested1);
        });
    }
}
