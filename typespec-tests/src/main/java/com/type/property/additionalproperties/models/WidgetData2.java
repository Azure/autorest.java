// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.property.additionalproperties.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * The WidgetData2 model.
 */
@Immutable
public final class WidgetData2 implements JsonSerializable<WidgetData2> {
    /*
     * The kind property.
     */
    @Generated
    private final String kind = "kind1";

    /*
     * A sequence of textual characters.
     */
    @Generated
    private final String start;

    /**
     * Creates an instance of WidgetData2 class.
     * 
     * @param start the start value to set.
     */
    @Generated
    public WidgetData2(String start) {
        this.start = start;
    }

    /**
     * Get the kind property: The kind property.
     * 
     * @return the kind value.
     */
    @Generated
    public String getKind() {
        return this.kind;
    }

    /**
     * Get the start property: A sequence of textual characters.
     * 
     * @return the start value.
     */
    @Generated
    public String getStart() {
        return this.start;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("kind", this.kind);
        jsonWriter.writeStringField("start", this.start);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of WidgetData2 from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of WidgetData2 if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the WidgetData2.
     */
    @Generated
    public static WidgetData2 fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            String start = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("start".equals(fieldName)) {
                    start = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }
            return new WidgetData2(start);
        });
    }
}
