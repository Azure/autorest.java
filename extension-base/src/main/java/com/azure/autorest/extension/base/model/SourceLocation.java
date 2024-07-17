// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.model;

import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;

import java.io.IOException;

/**
 * Represents a source location.
 */
public class SourceLocation implements JsonSerializable<SourceLocation> {
    private String document;
    private SmartLocation position;

    /**
     * Creates a new instance of the SourceLocation class.
     */
    public SourceLocation() {
    }

    /**
     * Gets the position of the location.
     *
     * @return The position of the location.
     */
    public SmartLocation getPosition() {
        return position;
    }

    /**
     * Gets the document of the location.
     *
     * @return The document of the location.
     */
    public String getDocument() {
        return document;
    }

    /**
     * Sets the document of the location.
     *
     * @param document The document of the location.
     */
    public void setDocument(String document) {
        this.document = document;
    }

    /**
     * Sets the position of the location.
     *
     * @param position The position of the location.
     */
    public void setPosition(SmartLocation position) {
        this.position = position;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        return jsonWriter.writeStartObject()
            .writeStringField("document", document)
            .writeJsonField("position", position)
            .writeEndObject();
    }

    public static SourceLocation fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            SourceLocation sourceLocation = new SourceLocation();

            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("document".equals(fieldName)) {
                    sourceLocation.document = reader.getString();
                } else if ("position".equals(fieldName)) {
                    sourceLocation.position = SmartLocation.fromJson(reader);
                } else {
                    reader.skipChildren();
                }
            }

            return sourceLocation;
        });
    }
}
