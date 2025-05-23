// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.ai.formrecognizer.documentanalysis.implementation.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.util.List;

/**
 * A word object consisting of a contiguous sequence of characters. For non-space delimited languages, such as Chinese,
 * Japanese, and Korean, each character is represented as its own word.
 */
@Fluent
public final class DocumentWord implements JsonSerializable<DocumentWord> {
    /*
     * Text content of the word.
     */
    @Generated
    private String content;

    /*
     * Bounding polygon of the word.
     */
    @Generated
    private List<Float> polygon;

    /*
     * Location of the word in the reading order concatenated content.
     */
    @Generated
    private DocumentSpan span;

    /*
     * Confidence of correctly extracting the word.
     */
    @Generated
    private float confidence;

    /**
     * Creates an instance of DocumentWord class.
     */
    @Generated
    public DocumentWord() {
    }

    /**
     * Get the content property: Text content of the word.
     * 
     * @return the content value.
     */
    @Generated
    public String getContent() {
        return this.content;
    }

    /**
     * Set the content property: Text content of the word.
     * 
     * @param content the content value to set.
     * @return the DocumentWord object itself.
     */
    @Generated
    public DocumentWord setContent(String content) {
        this.content = content;
        return this;
    }

    /**
     * Get the polygon property: Bounding polygon of the word.
     * 
     * @return the polygon value.
     */
    @Generated
    public List<Float> getPolygon() {
        return this.polygon;
    }

    /**
     * Set the polygon property: Bounding polygon of the word.
     * 
     * @param polygon the polygon value to set.
     * @return the DocumentWord object itself.
     */
    @Generated
    public DocumentWord setPolygon(List<Float> polygon) {
        this.polygon = polygon;
        return this;
    }

    /**
     * Get the span property: Location of the word in the reading order concatenated content.
     * 
     * @return the span value.
     */
    @Generated
    public DocumentSpan getSpan() {
        return this.span;
    }

    /**
     * Set the span property: Location of the word in the reading order concatenated content.
     * 
     * @param span the span value to set.
     * @return the DocumentWord object itself.
     */
    @Generated
    public DocumentWord setSpan(DocumentSpan span) {
        this.span = span;
        return this;
    }

    /**
     * Get the confidence property: Confidence of correctly extracting the word.
     * 
     * @return the confidence value.
     */
    @Generated
    public float getConfidence() {
        return this.confidence;
    }

    /**
     * Set the confidence property: Confidence of correctly extracting the word.
     * 
     * @param confidence the confidence value to set.
     * @return the DocumentWord object itself.
     */
    @Generated
    public DocumentWord setConfidence(float confidence) {
        this.confidence = confidence;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("content", this.content);
        jsonWriter.writeJsonField("span", this.span);
        jsonWriter.writeFloatField("confidence", this.confidence);
        jsonWriter.writeArrayField("polygon", this.polygon, (writer, element) -> writer.writeFloat(element));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of DocumentWord from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of DocumentWord if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the DocumentWord.
     */
    @Generated
    public static DocumentWord fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            DocumentWord deserializedDocumentWord = new DocumentWord();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("content".equals(fieldName)) {
                    deserializedDocumentWord.content = reader.getString();
                } else if ("span".equals(fieldName)) {
                    deserializedDocumentWord.span = DocumentSpan.fromJson(reader);
                } else if ("confidence".equals(fieldName)) {
                    deserializedDocumentWord.confidence = reader.getFloat();
                } else if ("polygon".equals(fieldName)) {
                    List<Float> polygon = reader.readArray(reader1 -> reader1.getFloat());
                    deserializedDocumentWord.polygon = polygon;
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedDocumentWord;
        });
    }
}
