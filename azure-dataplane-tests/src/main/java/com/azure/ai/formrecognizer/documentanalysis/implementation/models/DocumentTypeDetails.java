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
import java.util.Map;

/**
 * Document type info.
 */
@Fluent
public final class DocumentTypeDetails implements JsonSerializable<DocumentTypeDetails> {
    /*
     * Document model description.
     */
    private String description;

    /*
     * Custom document model build mode.
     */
    private DocumentBuildMode buildMode;

    /*
     * Description of the document semantic schema using a JSON Schema style syntax.
     */
    private Map<String, DocumentFieldSchema> fieldSchema;

    /*
     * Estimated confidence for each field.
     */
    private Map<String, Float> fieldConfidence;

    /**
     * Creates an instance of DocumentTypeDetails class.
     */
    public DocumentTypeDetails() {
    }

    /**
     * Get the description property: Document model description.
     * 
     * @return the description value.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Set the description property: Document model description.
     * 
     * @param description the description value to set.
     * @return the DocumentTypeDetails object itself.
     */
    public DocumentTypeDetails setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Get the buildMode property: Custom document model build mode.
     * 
     * @return the buildMode value.
     */
    public DocumentBuildMode getBuildMode() {
        return this.buildMode;
    }

    /**
     * Set the buildMode property: Custom document model build mode.
     * 
     * @param buildMode the buildMode value to set.
     * @return the DocumentTypeDetails object itself.
     */
    public DocumentTypeDetails setBuildMode(DocumentBuildMode buildMode) {
        this.buildMode = buildMode;
        return this;
    }

    /**
     * Get the fieldSchema property: Description of the document semantic schema using a JSON Schema style syntax.
     * 
     * @return the fieldSchema value.
     */
    public Map<String, DocumentFieldSchema> getFieldSchema() {
        return this.fieldSchema;
    }

    /**
     * Set the fieldSchema property: Description of the document semantic schema using a JSON Schema style syntax.
     * 
     * @param fieldSchema the fieldSchema value to set.
     * @return the DocumentTypeDetails object itself.
     */
    public DocumentTypeDetails setFieldSchema(Map<String, DocumentFieldSchema> fieldSchema) {
        this.fieldSchema = fieldSchema;
        return this;
    }

    /**
     * Get the fieldConfidence property: Estimated confidence for each field.
     * 
     * @return the fieldConfidence value.
     */
    public Map<String, Float> getFieldConfidence() {
        return this.fieldConfidence;
    }

    /**
     * Set the fieldConfidence property: Estimated confidence for each field.
     * 
     * @param fieldConfidence the fieldConfidence value to set.
     * @return the DocumentTypeDetails object itself.
     */
    public DocumentTypeDetails setFieldConfidence(Map<String, Float> fieldConfidence) {
        this.fieldConfidence = fieldConfidence;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeMapField("fieldSchema", this.fieldSchema, (writer, element) -> writer.writeJson(element));
        jsonWriter.writeStringField("description", this.description);
        jsonWriter.writeStringField("buildMode", this.buildMode == null ? null : this.buildMode.toString());
        jsonWriter.writeMapField("fieldConfidence", this.fieldConfidence,
            (writer, element) -> writer.writeFloat(element));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of DocumentTypeDetails from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of DocumentTypeDetails if the JsonReader was pointing to an instance of it, or null if it
     * was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the DocumentTypeDetails.
     */
    public static DocumentTypeDetails fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            DocumentTypeDetails deserializedDocumentTypeDetails = new DocumentTypeDetails();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("fieldSchema".equals(fieldName)) {
                    Map<String, DocumentFieldSchema> fieldSchema
                        = reader.readMap(reader1 -> DocumentFieldSchema.fromJson(reader1));
                    deserializedDocumentTypeDetails.fieldSchema = fieldSchema;
                } else if ("description".equals(fieldName)) {
                    deserializedDocumentTypeDetails.description = reader.getString();
                } else if ("buildMode".equals(fieldName)) {
                    deserializedDocumentTypeDetails.buildMode = DocumentBuildMode.fromString(reader.getString());
                } else if ("fieldConfidence".equals(fieldName)) {
                    Map<String, Float> fieldConfidence = reader.readMap(reader1 -> reader1.getFloat());
                    deserializedDocumentTypeDetails.fieldConfidence = fieldConfidence;
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedDocumentTypeDetails;
        });
    }
}
