// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.ai.formrecognizer.documentanalysis.implementation.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.OffsetDateTime;
import java.util.Map;

/**
 * Get Operation response object.
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "kind",
    defaultImpl = OperationDetails.class)
@JsonTypeName("OperationDetails")
@JsonSubTypes({
    @JsonSubTypes.Type(name = "documentModelBuild", value = DocumentModelBuildOperationDetails.class),
    @JsonSubTypes.Type(name = "documentModelCompose", value = DocumentModelComposeOperationDetails.class),
    @JsonSubTypes.Type(name = "documentModelCopyTo", value = DocumentModelCopyToOperationDetails.class) })
@Fluent
public class OperationDetails {
    /*
     * Operation ID
     */
    @JsonProperty(value = "operationId", required = true)
    private String operationId;

    /*
     * Operation status.
     */
    @JsonProperty(value = "status", required = true)
    private OperationStatus status;

    /*
     * Operation progress (0-100).
     */
    @JsonProperty(value = "percentCompleted")
    private Integer percentCompleted;

    /*
     * Date and time (UTC) when the operation was created.
     */
    @JsonProperty(value = "createdDateTime", required = true)
    private OffsetDateTime createdDateTime;

    /*
     * Date and time (UTC) when the status was last updated.
     */
    @JsonProperty(value = "lastUpdatedDateTime", required = true)
    private OffsetDateTime lastUpdatedDateTime;

    /*
     * URL of the resource targeted by this operation.
     */
    @JsonProperty(value = "resourceLocation", required = true)
    private String resourceLocation;

    /*
     * API version used to create this operation.
     */
    @JsonProperty(value = "apiVersion")
    private String apiVersion;

    /*
     * List of key-value tag attributes associated with the document model.
     */
    @JsonProperty(value = "tags")
    private Map<String, String> tags;

    /*
     * Encountered error.
     */
    @JsonProperty(value = "error")
    private Error error;

    /**
     * Creates an instance of OperationDetails class.
     */
    public OperationDetails() {
    }

    /**
     * Get the operationId property: Operation ID.
     * 
     * @return the operationId value.
     */
    public String getOperationId() {
        return this.operationId;
    }

    /**
     * Set the operationId property: Operation ID.
     * 
     * @param operationId the operationId value to set.
     * @return the OperationDetails object itself.
     */
    public OperationDetails setOperationId(String operationId) {
        this.operationId = operationId;
        return this;
    }

    /**
     * Get the status property: Operation status.
     * 
     * @return the status value.
     */
    public OperationStatus getStatus() {
        return this.status;
    }

    /**
     * Set the status property: Operation status.
     * 
     * @param status the status value to set.
     * @return the OperationDetails object itself.
     */
    public OperationDetails setStatus(OperationStatus status) {
        this.status = status;
        return this;
    }

    /**
     * Get the percentCompleted property: Operation progress (0-100).
     * 
     * @return the percentCompleted value.
     */
    public Integer getPercentCompleted() {
        return this.percentCompleted;
    }

    /**
     * Set the percentCompleted property: Operation progress (0-100).
     * 
     * @param percentCompleted the percentCompleted value to set.
     * @return the OperationDetails object itself.
     */
    public OperationDetails setPercentCompleted(Integer percentCompleted) {
        this.percentCompleted = percentCompleted;
        return this;
    }

    /**
     * Get the createdDateTime property: Date and time (UTC) when the operation was created.
     * 
     * @return the createdDateTime value.
     */
    public OffsetDateTime getCreatedDateTime() {
        return this.createdDateTime;
    }

    /**
     * Set the createdDateTime property: Date and time (UTC) when the operation was created.
     * 
     * @param createdDateTime the createdDateTime value to set.
     * @return the OperationDetails object itself.
     */
    public OperationDetails setCreatedDateTime(OffsetDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
        return this;
    }

    /**
     * Get the lastUpdatedDateTime property: Date and time (UTC) when the status was last updated.
     * 
     * @return the lastUpdatedDateTime value.
     */
    public OffsetDateTime getLastUpdatedDateTime() {
        return this.lastUpdatedDateTime;
    }

    /**
     * Set the lastUpdatedDateTime property: Date and time (UTC) when the status was last updated.
     * 
     * @param lastUpdatedDateTime the lastUpdatedDateTime value to set.
     * @return the OperationDetails object itself.
     */
    public OperationDetails setLastUpdatedDateTime(OffsetDateTime lastUpdatedDateTime) {
        this.lastUpdatedDateTime = lastUpdatedDateTime;
        return this;
    }

    /**
     * Get the resourceLocation property: URL of the resource targeted by this operation.
     * 
     * @return the resourceLocation value.
     */
    public String getResourceLocation() {
        return this.resourceLocation;
    }

    /**
     * Set the resourceLocation property: URL of the resource targeted by this operation.
     * 
     * @param resourceLocation the resourceLocation value to set.
     * @return the OperationDetails object itself.
     */
    public OperationDetails setResourceLocation(String resourceLocation) {
        this.resourceLocation = resourceLocation;
        return this;
    }

    /**
     * Get the apiVersion property: API version used to create this operation.
     * 
     * @return the apiVersion value.
     */
    public String getApiVersion() {
        return this.apiVersion;
    }

    /**
     * Set the apiVersion property: API version used to create this operation.
     * 
     * @param apiVersion the apiVersion value to set.
     * @return the OperationDetails object itself.
     */
    public OperationDetails setApiVersion(String apiVersion) {
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
     * @return the OperationDetails object itself.
     */
    public OperationDetails setTags(Map<String, String> tags) {
        this.tags = tags;
        return this;
    }

    /**
     * Get the error property: Encountered error.
     * 
     * @return the error value.
     */
    public Error getError() {
        return this.error;
    }

    /**
     * Set the error property: Encountered error.
     * 
     * @param error the error value to set.
     * @return the OperationDetails object itself.
     */
    public OperationDetails setError(Error error) {
        this.error = error;
        return this;
    }
}
