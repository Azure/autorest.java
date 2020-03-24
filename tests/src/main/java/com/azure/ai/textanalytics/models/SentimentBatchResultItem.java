package com.azure.ai.textanalytics.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The SentimentBatchResultItem model.
 */
@Fluent
public final class SentimentBatchResultItem {
    /*
     * Unique, non-empty document identifier.
     */
    @JsonProperty(value = "id")
    private String id;

    /*
     * A decimal number between 0 and 1 denoting the sentiment of the document.
     * A score above 0.7 usually refers to a positive document while a score
     * below 0.3 normally has a negative connotation. Mid values refer to
     * neutral text.
     */
    @JsonProperty(value = "score")
    private Double score;

    /*
     * (Optional) if showStats=true was specified in the request this field
     * will contain information about the document payload.
     */
    @JsonProperty(value = "statistics")
    private DocumentStatistics statistics;

    /**
     * Get the id property: Unique, non-empty document identifier.
     * 
     * @return the id value.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Set the id property: Unique, non-empty document identifier.
     * 
     * @param id the id value to set.
     * @return the SentimentBatchResultItem object itself.
     */
    public SentimentBatchResultItem setId(String id) {
        this.id = id;
        return this;
    }

    /**
     * Get the score property: A decimal number between 0 and 1 denoting the
     * sentiment of the document. A score above 0.7 usually refers to a
     * positive document while a score below 0.3 normally has a negative
     * connotation. Mid values refer to neutral text.
     * 
     * @return the score value.
     */
    public Double getScore() {
        return this.score;
    }

    /**
     * Set the score property: A decimal number between 0 and 1 denoting the
     * sentiment of the document. A score above 0.7 usually refers to a
     * positive document while a score below 0.3 normally has a negative
     * connotation. Mid values refer to neutral text.
     * 
     * @param score the score value to set.
     * @return the SentimentBatchResultItem object itself.
     */
    public SentimentBatchResultItem setScore(Double score) {
        this.score = score;
        return this;
    }

    /**
     * Get the statistics property: (Optional) if showStats=true was specified
     * in the request this field will contain information about the document
     * payload.
     * 
     * @return the statistics value.
     */
    public DocumentStatistics getStatistics() {
        return this.statistics;
    }

    /**
     * Set the statistics property: (Optional) if showStats=true was specified
     * in the request this field will contain information about the document
     * payload.
     * 
     * @param statistics the statistics value to set.
     * @return the SentimentBatchResultItem object itself.
     */
    public SentimentBatchResultItem setStatistics(DocumentStatistics statistics) {
        this.statistics = statistics;
        return this;
    }

    public void validate() {
        if (getStatistics() != null) {
            getStatistics().validate();
        }
    }
}
