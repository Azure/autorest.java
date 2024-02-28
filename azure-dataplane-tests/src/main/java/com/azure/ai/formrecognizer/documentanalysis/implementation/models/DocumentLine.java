// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.ai.formrecognizer.documentanalysis.implementation.models;

import com.azure.core.annotation.Fluent;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.util.List;

/**
 * A content line object consisting of an adjacent sequence of content elements, such as words and selection marks.
 */
@Fluent
public final class DocumentLine implements JsonSerializable<DocumentLine> {
    /*
     * Concatenated content of the contained elements in reading order.
     */
    private String content;

    /*
     * Bounding polygon of the line.
     */
    private List<Float> polygon;

    /*
     * Location of the line in the reading order concatenated content.
     */
    private List<DocumentSpan> spans;

    /**
     * Creates an instance of DocumentLine class.
     */
    public DocumentLine() {
    }

    /**
     * Get the content property: Concatenated content of the contained elements in reading order.
     * 
     * @return the content value.
     */
    public String getContent() {
        return this.content;
    }

    /**
     * Set the content property: Concatenated content of the contained elements in reading order.
     * 
     * @param content the content value to set.
     * @return the DocumentLine object itself.
     */
    public DocumentLine setContent(String content) {
        this.content = content;
        return this;
    }

    /**
     * Get the polygon property: Bounding polygon of the line.
     * 
     * @return the polygon value.
     */
    public List<Float> getPolygon() {
        return this.polygon;
    }

    /**
     * Set the polygon property: Bounding polygon of the line.
     * 
     * @param polygon the polygon value to set.
     * @return the DocumentLine object itself.
     */
    public DocumentLine setPolygon(List<Float> polygon) {
        this.polygon = polygon;
        return this;
    }

    /**
     * Get the spans property: Location of the line in the reading order concatenated content.
     * 
     * @return the spans value.
     */
    public List<DocumentSpan> getSpans() {
        return this.spans;
    }

    /**
     * Set the spans property: Location of the line in the reading order concatenated content.
     * 
     * @param spans the spans value to set.
     * @return the DocumentLine object itself.
     */
    public DocumentLine setSpans(List<DocumentSpan> spans) {
        this.spans = spans;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("content", this.content);
        jsonWriter.writeArrayField("spans", this.spans, (writer, element) -> writer.writeJson(element));
        jsonWriter.writeArrayField("polygon", this.polygon, (writer, element) -> writer.writeFloat(element));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of DocumentLine from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of DocumentLine if the JsonReader was pointing to an instance of it, or null if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the DocumentLine.
     */
    public static DocumentLine fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            DocumentLine deserializedDocumentLine = new DocumentLine();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("content".equals(fieldName)) {
                    deserializedDocumentLine.content = reader.getString();
                } else if ("spans".equals(fieldName)) {
                    List<DocumentSpan> spans = reader.readArray(reader1 -> DocumentSpan.fromJson(reader1));
                    deserializedDocumentLine.spans = spans;
                } else if ("polygon".equals(fieldName)) {
                    List<Float> polygon = reader.readArray(reader1 -> reader1.getFloat());
                    deserializedDocumentLine.polygon = polygon;
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedDocumentLine;
        });
    }
}
