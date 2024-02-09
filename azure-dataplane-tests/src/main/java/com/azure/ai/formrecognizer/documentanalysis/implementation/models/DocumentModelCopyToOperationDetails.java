// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.ai.formrecognizer.documentanalysis.implementation.models;

import com.azure.core.annotation.Fluent;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.OffsetDateTime;

import java.util.Map;

/**
 * Get Operation response object.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "kind")
@JsonTypeName("documentModelCopyTo")
@Fluent
public final class DocumentModelCopyToOperationDetails extends OperationDetails {
    /*
     * Operation result upon success.
     */
    @JsonProperty(value = "result")
    private DocumentModelDetails result;

    /**
     * Creates an instance of DocumentModelCopyToOperationDetails class.
     */
    public DocumentModelCopyToOperationDetails() {
    }

    /**
     * Get the result property: Operation result upon success.
     * 
     * @return the result value.
     */
    public DocumentModelDetails getResult() {
        return this.result;
    }

    /**
     * Set the result property: Operation result upon success.
     * 
     * @param result the result value to set.
     * @return the DocumentModelCopyToOperationDetails object itself.
     */
    public DocumentModelCopyToOperationDetails setResult(DocumentModelDetails result) {
        this.result = result;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DocumentModelCopyToOperationDetails setOperationId(String operationId) {
        super.setOperationId(operationId);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DocumentModelCopyToOperationDetails setStatus(OperationStatus status) {
        super.setStatus(status);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DocumentModelCopyToOperationDetails setPercentCompleted(Integer percentCompleted) {
        super.setPercentCompleted(percentCompleted);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DocumentModelCopyToOperationDetails setCreatedDateTime(OffsetDateTime createdDateTime) {
        super.setCreatedDateTime(createdDateTime);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DocumentModelCopyToOperationDetails setLastUpdatedDateTime(OffsetDateTime lastUpdatedDateTime) {
        super.setLastUpdatedDateTime(lastUpdatedDateTime);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DocumentModelCopyToOperationDetails setResourceLocation(String resourceLocation) {
        super.setResourceLocation(resourceLocation);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DocumentModelCopyToOperationDetails setApiVersion(String apiVersion) {
        super.setApiVersion(apiVersion);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DocumentModelCopyToOperationDetails setTags(Map<String, String> tags) {
        super.setTags(tags);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DocumentModelCopyToOperationDetails setError(Error error) {
        super.setError(error);
        return this;
    }
}
