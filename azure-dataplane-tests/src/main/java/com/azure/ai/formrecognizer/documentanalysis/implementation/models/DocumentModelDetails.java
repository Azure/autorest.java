// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.ai.formrecognizer.documentanalysis.implementation.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;
import java.util.Map;

/**
 * Document model info.
 */
@Fluent
public final class DocumentModelDetails {
    /*
     * Unique document model name.
     */
    @JsonProperty(value = "modelId", required = true)
    private String modelId;

    /*
     * Document model description.
     */
    @JsonProperty(value = "description")
    private String description;

    /*
     * Date and time (UTC) when the document model was created.
     */
    @JsonProperty(value = "createdDateTime", required = true)
    private OffsetDateTime createdDateTime;

    /*
     * API version used to create this document model.
     */
    @JsonProperty(value = "apiVersion")
    private String apiVersion;

    /*
     * List of key-value tag attributes associated with the document model.
     */
    @JsonProperty(value = "tags")
    private Map<String, String> tags;

    /*
     * Supported document types.
     */
    @JsonProperty(value = "docTypes")
    private Map<String, DocumentTypeDetails> docTypes;

    /**
     * Creates an instance of DocumentModelDetails class.
     */
    public DocumentModelDetails() {}

    /**
     * Get the modelId property: Unique document model name.
     * 
     * @return the modelId value.
     */
    public String getModelId() {
        return this.modelId;
    }

    /**
     * Set the modelId property: Unique document model name.
     * 
     * @param modelId the modelId value to set.
     * @return the DocumentModelDetails object itself.
     */
    public DocumentModelDetails setModelId(String modelId) {
        this.modelId = modelId;
        return this;
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
     * @return the DocumentModelDetails object itself.
     */
    public DocumentModelDetails setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Get the createdDateTime property: Date and time (UTC) when the document model was created.
     * 
     * @return the createdDateTime value.
     */
    public OffsetDateTime getCreatedDateTime() {
        return this.createdDateTime;
    }

    /**
     * Set the createdDateTime property: Date and time (UTC) when the document model was created.
     * 
     * @param createdDateTime the createdDateTime value to set.
     * @return the DocumentModelDetails object itself.
     */
    public DocumentModelDetails setCreatedDateTime(OffsetDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
        return this;
    }

    /**
     * Get the apiVersion property: API version used to create this document model.
     * 
     * @return the apiVersion value.
     */
    public String getApiVersion() {
        return this.apiVersion;
    }

    /**
     * Set the apiVersion property: API version used to create this document model.
     * 
     * @param apiVersion the apiVersion value to set.
     * @return the DocumentModelDetails object itself.
     */
    public DocumentModelDetails setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
        return this;
    }

    /**
     * Get the tags property: List of key-value tag attributes associated with the document model.
     * 
     * @return the tags value.
     */
    public Map<String, String> getTags() {
        return this.tags;
    }

    /**
     * Set the tags property: List of key-value tag attributes associated with the document model.
     * 
     * @param tags the tags value to set.
     * @return the DocumentModelDetails object itself.
     */
    public DocumentModelDetails setTags(Map<String, String> tags) {
        this.tags = tags;
        return this;
    }

    /**
     * Get the docTypes property: Supported document types.
     * 
     * @return the docTypes value.
     */
    public Map<String, DocumentTypeDetails> getDocTypes() {
        return this.docTypes;
    }

    /**
     * Set the docTypes property: Supported document types.
     * 
     * @param docTypes the docTypes value to set.
     * @return the DocumentModelDetails object itself.
     */
    public DocumentModelDetails setDocTypes(Map<String, DocumentTypeDetails> docTypes) {
        this.docTypes = docTypes;
        return this;
    }
}
