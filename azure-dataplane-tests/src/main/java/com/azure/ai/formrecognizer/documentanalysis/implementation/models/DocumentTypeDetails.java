// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.ai.formrecognizer.documentanalysis.implementation.models;

import com.azure.core.annotation.Fluent;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

/**
 * Document type info.
 */
@Fluent
public final class DocumentTypeDetails {
    /*
     * Document model description.
     */
    @JsonProperty(value = "description")
    private String description;

    /*
     * Custom document model build mode.
     */
    @JsonProperty(value = "buildMode")
    private DocumentBuildMode buildMode;

    /*
     * Description of the document semantic schema using a JSON Schema style syntax.
     */
    @JsonProperty(value = "fieldSchema", required = true)
    private Map<String, DocumentFieldSchema> fieldSchema;

    /*
     * Estimated confidence for each field.
     */
    @JsonProperty(value = "fieldConfidence")
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
}
