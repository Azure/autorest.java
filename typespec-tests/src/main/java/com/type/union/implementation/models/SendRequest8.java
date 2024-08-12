// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.union.implementation.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import com.type.union.models.MixedLiteralsCases;
import java.io.IOException;

/**
 * The SendRequest8 model.
 */
@Immutable
public final class SendRequest8 implements JsonSerializable<SendRequest8> {
    /*
     * The prop property.
     */
    @Generated
    private final MixedLiteralsCases prop;

    /**
     * Creates an instance of SendRequest8 class.
     * 
     * @param prop the prop value to set.
     */
    @Generated
    public SendRequest8(MixedLiteralsCases prop) {
        this.prop = prop;
    }

    /**
     * Get the prop property: The prop property.
     * 
     * @return the prop value.
     */
    @Generated
    public MixedLiteralsCases getProp() {
        return this.prop;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeJsonField("prop", this.prop);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of SendRequest8 from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of SendRequest8 if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the SendRequest8.
     */
    @Generated
    public static SendRequest8 fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            long foundTracker = 0;
            MixedLiteralsCases prop = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("prop".equals(fieldName)) {
                    prop = MixedLiteralsCases.fromJson(reader);
                } else {
                    reader.skipChildren();
                }
            }
            return new SendRequest8(prop);
        });
    }
}
