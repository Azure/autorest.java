// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.union.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.core.util.BinaryData;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * The GetResponse5 model.
 */
@Immutable
public final class GetResponse5 implements JsonSerializable<GetResponse5> {
    /*
     * The prop property.
     */
    @Generated
    private final BinaryData prop;

    /**
     * Creates an instance of GetResponse5 class.
     * 
     * @param prop the prop value to set.
     */
    @Generated
    private GetResponse5(BinaryData prop) {
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
     * Reads an instance of GetResponse5 from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of GetResponse5 if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the GetResponse5.
     */
    public static GetResponse5 fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            BinaryData prop = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("prop".equals(fieldName)) {
                    prop = BinaryData.fromObject(reader.readUntyped());
                } else {
                    reader.skipChildren();
                }
            }
            return new GetResponse5(prop);
        });
    }
}
