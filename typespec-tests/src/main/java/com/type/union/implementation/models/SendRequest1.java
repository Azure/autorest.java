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

import com.type.union.models.Prop;
import java.io.IOException;

/**
 * The SendRequest1 model.
 */
@Immutable
public final class SendRequest1 implements JsonSerializable<SendRequest1> {
    /*
     * The prop property.
     */
    @Generated
    private final Prop prop;

    /**
     * Creates an instance of SendRequest1 class.
     * 
     * @param prop the prop value to set.
     */
    @Generated
    public SendRequest1(Prop prop) {
        this.prop = prop;
    }

    /**
     * Get the prop property: The prop property.
     * 
     * @return the prop value.
     */
    @Generated
    public Prop getProp() {
        return this.prop;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("prop", this.prop == null ? null : this.prop.toString());
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of SendRequest1 from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of SendRequest1 if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the SendRequest1.
     */
    public static SendRequest1 fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            Prop prop = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("prop".equals(fieldName)) {
                    prop = Prop.fromString(reader.getString());
                } else {
                    reader.skipChildren();
                }
            }
            return new SendRequest1(prop);
        });
    }
}
