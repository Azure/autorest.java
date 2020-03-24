package com.azure.ai.textanalytics.models;

import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * The EntitiesBatchResult model.
 */
@Immutable
public final class EntitiesBatchResult {
    /*
     * Response by document
     */
    @JsonProperty(value = "documents", access = JsonProperty.Access.WRITE_ONLY)
    private List<EntitiesBatchResultItem> documents;

    /*
     * Errors and Warnings by document
     */
    @JsonProperty(value = "errors", access = JsonProperty.Access.WRITE_ONLY)
    private List<ErrorRecord> errors;

    /*
     * (Optional) if showStats=true was specified in the request this field
     * will contain information about the request payload.
     */
    @JsonProperty(value = "statistics", access = JsonProperty.Access.WRITE_ONLY)
    private RequestStatistics statistics;

    /**
     * Get the documents property: Response by document.
     * 
     * @return the documents value.
     */
    public List<EntitiesBatchResultItem> getDocuments() {
        return this.documents;
    }

    /**
     * Get the errors property: Errors and Warnings by document.
     * 
     * @return the errors value.
     */
    public List<ErrorRecord> getErrors() {
        return this.errors;
    }

    /**
     * Get the statistics property: (Optional) if showStats=true was specified
     * in the request this field will contain information about the request
     * payload.
     * 
     * @return the statistics value.
     */
    public RequestStatistics getStatistics() {
        return this.statistics;
    }

    public void validate() {
        if (getDocuments() != null) {
            getDocuments().forEach(e -> e.validate());
        }
        if (getErrors() != null) {
            getErrors().forEach(e -> e.validate());
        }
        if (getStatistics() != null) {
            getStatistics().validate();
        }
    }
}
