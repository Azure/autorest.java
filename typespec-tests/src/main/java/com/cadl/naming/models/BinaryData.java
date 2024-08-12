// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.naming.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * summary of Data
 * 
 * description of Data.
 */
@Immutable
public final class BinaryData implements JsonSerializable<BinaryData> {
    /*
     * summary of data property
     * 
     * description of data property
     */
    @Generated
    private final Data data;

    /**
     * Creates an instance of BinaryData class.
     * 
     * @param data the data value to set.
     */
    @Generated
    private BinaryData(Data data) {
        this.data = data;
    }

    /**
     * Get the data property: summary of data property
     * 
     * description of data property.
     * 
     * @return the data value.
     */
    @Generated
    public Data getData() {
        return this.data;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeJsonField("data", this.data);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of BinaryData from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of BinaryData if the JsonReader was pointing to an instance of it, or null if it was pointing
     * to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the BinaryData.
     */
    @Generated
    public static BinaryData fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            long foundTracker = 0;
            Data data = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("data".equals(fieldName)) {
                    data = Data.fromJson(reader);
                } else {
                    reader.skipChildren();
                }
            }
            return new BinaryData(data);
        });
    }
}
