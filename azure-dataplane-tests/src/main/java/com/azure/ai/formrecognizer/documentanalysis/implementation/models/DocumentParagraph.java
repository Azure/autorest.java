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
 * A paragraph object consisting with contiguous lines generally with common alignment and spacing.
 */
@Fluent
public final class DocumentParagraph implements JsonSerializable<DocumentParagraph> {
    /*
     * Semantic role of the paragraph.
     */
    private ParagraphRole role;

    /*
     * Concatenated content of the paragraph in reading order.
     */
    private String content;

    /*
     * Bounding regions covering the paragraph.
     */
    private List<BoundingRegion> boundingRegions;

    /*
     * Location of the paragraph in the reading order concatenated content.
     */
    private List<DocumentSpan> spans;

    /**
     * Creates an instance of DocumentParagraph class.
     */
    public DocumentParagraph() {
    }

    /**
     * Get the role property: Semantic role of the paragraph.
     * 
     * @return the role value.
     */
    public ParagraphRole getRole() {
        return this.role;
    }

    /**
     * Set the role property: Semantic role of the paragraph.
     * 
     * @param role the role value to set.
     * @return the DocumentParagraph object itself.
     */
    public DocumentParagraph setRole(ParagraphRole role) {
        this.role = role;
        return this;
    }

    /**
     * Get the content property: Concatenated content of the paragraph in reading order.
     * 
     * @return the content value.
     */
    public String getContent() {
        return this.content;
    }

    /**
     * Set the content property: Concatenated content of the paragraph in reading order.
     * 
     * @param content the content value to set.
     * @return the DocumentParagraph object itself.
     */
    public DocumentParagraph setContent(String content) {
        this.content = content;
        return this;
    }

    /**
     * Get the boundingRegions property: Bounding regions covering the paragraph.
     * 
     * @return the boundingRegions value.
     */
    public List<BoundingRegion> getBoundingRegions() {
        return this.boundingRegions;
    }

    /**
     * Set the boundingRegions property: Bounding regions covering the paragraph.
     * 
     * @param boundingRegions the boundingRegions value to set.
     * @return the DocumentParagraph object itself.
     */
    public DocumentParagraph setBoundingRegions(List<BoundingRegion> boundingRegions) {
        this.boundingRegions = boundingRegions;
        return this;
    }

    /**
     * Get the spans property: Location of the paragraph in the reading order concatenated content.
     * 
     * @return the spans value.
     */
    public List<DocumentSpan> getSpans() {
        return this.spans;
    }

    /**
     * Set the spans property: Location of the paragraph in the reading order concatenated content.
     * 
     * @param spans the spans value to set.
     * @return the DocumentParagraph object itself.
     */
    public DocumentParagraph setSpans(List<DocumentSpan> spans) {
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
        jsonWriter.writeStringField("role", this.role == null ? null : this.role.toString());
        jsonWriter.writeArrayField("boundingRegions", this.boundingRegions,
            (writer, element) -> writer.writeJson(element));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of DocumentParagraph from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of DocumentParagraph if the JsonReader was pointing to an instance of it, or null if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the DocumentParagraph.
     */
    public static DocumentParagraph fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            DocumentParagraph deserializedDocumentParagraph = new DocumentParagraph();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("content".equals(fieldName)) {
                    deserializedDocumentParagraph.content = reader.getString();
                } else if ("spans".equals(fieldName)) {
                    List<DocumentSpan> spans = reader.readArray(reader1 -> DocumentSpan.fromJson(reader1));
                    deserializedDocumentParagraph.spans = spans;
                } else if ("role".equals(fieldName)) {
                    deserializedDocumentParagraph.role = ParagraphRole.fromString(reader.getString());
                } else if ("boundingRegions".equals(fieldName)) {
                    List<BoundingRegion> boundingRegions
                        = reader.readArray(reader1 -> BoundingRegion.fromJson(reader1));
                    deserializedDocumentParagraph.boundingRegions = boundingRegions;
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedDocumentParagraph;
        });
    }
}
