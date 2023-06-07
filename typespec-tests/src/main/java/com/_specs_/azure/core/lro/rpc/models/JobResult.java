// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com._specs_.azure.core.lro.rpc.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/** Result of the job. */
@Immutable
public final class JobResult {
    /*
     * A processing job identifier.
     */
    @Generated
    @JsonProperty(value = "jobId", access = JsonProperty.Access.WRITE_ONLY)
    private String jobId;

    /*
     * Comment.
     */
    @Generated
    @JsonProperty(value = "comment", access = JsonProperty.Access.WRITE_ONLY)
    private String comment;

    /*
     * The status of the processing job.
     */
    @Generated
    @JsonProperty(value = "status", access = JsonProperty.Access.WRITE_ONLY)
    private OperationState status;

    /*
     * Error objects that describes the error when status is "Failed".
     */
    @Generated
    @JsonProperty(value = "errors", access = JsonProperty.Access.WRITE_ONLY)
    private List<ErrorResponse> errors;

    /*
     * The results.
     */
    @Generated
    @JsonProperty(value = "results", access = JsonProperty.Access.WRITE_ONLY)
    private List<String> results;

    /** Creates an instance of JobResult class. */
    @Generated
    private JobResult() {}

    /**
     * Get the jobId property: A processing job identifier.
     *
     * @return the jobId value.
     */
    @Generated
    public String getJobId() {
        return this.jobId;
    }

    /**
     * Get the comment property: Comment.
     *
     * @return the comment value.
     */
    @Generated
    public String getComment() {
        return this.comment;
    }

    /**
     * Get the status property: The status of the processing job.
     *
     * @return the status value.
     */
    @Generated
    public OperationState getStatus() {
        return this.status;
    }

    /**
     * Get the errors property: Error objects that describes the error when status is "Failed".
     *
     * @return the errors value.
     */
    @Generated
    public List<ErrorResponse> getErrors() {
        return this.errors;
    }

    /**
     * Get the results property: The results.
     *
     * @return the results value.
     */
    @Generated
    public List<String> getResults() {
        return this.results;
    }
}
