// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.
package com.type.union.implementation.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.core.util.BinaryData;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * The SendRequest5 model.
 */
@Immutable
public final class SendRequest5 implements JsonSerializable<SendRequest5> {

    /*
     * The prop property.
     */
    @Generated
    private final BinaryData prop;

    /**
     * Creates an instance of SendRequest5 class.
     *
     * @param prop the prop value to set.
     */
    @Generated
    public SendRequest5(BinaryData prop) {
        this.prop = prop;
    }

    /**
     * Get the prop property: The prop property.
     *
     * @return the prop value.
     */
    @Generated
    public BinaryData getProp() {
        return this.prop;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeUntypedField("prop", this.prop.toObject(Object.class));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of SendRequest5 from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of SendRequest5 if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the SendRequest5.
     */
    public static SendRequest5 fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            BinaryData prop = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();
                if ("prop".equals(fieldName)) {
                    prop = reader.getNullable(nonNullReader -> BinaryData.fromObject(nonNullReader.readUntyped()));
                } else {
                    reader.skipChildren();
                }
            }
            return new SendRequest5(prop);
        });
    }
}
