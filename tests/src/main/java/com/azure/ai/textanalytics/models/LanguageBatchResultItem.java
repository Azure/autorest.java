package com.azure.ai.textanalytics.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * The LanguageBatchResultItem model.
 */
@Fluent
public final class LanguageBatchResultItem {
    /*
     * Unique, non-empty document identifier.
     */
    @JsonProperty(value = "id")
    private String id;

    /*
     * A list of extracted languages.
     */
    @JsonProperty(value = "detectedLanguages")
    private List<DetectedLanguage> detectedLanguages;

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
     * @return the LanguageBatchResultItem object itself.
     */
    public LanguageBatchResultItem setId(String id) {
        this.id = id;
        return this;
    }

    /**
     * Get the detectedLanguages property: A list of extracted languages.
     * 
     * @return the detectedLanguages value.
     */
    public List<DetectedLanguage> getDetectedLanguages() {
        return this.detectedLanguages;
    }

    /**
     * Set the detectedLanguages property: A list of extracted languages.
     * 
     * @param detectedLanguages the detectedLanguages value to set.
     * @return the LanguageBatchResultItem object itself.
     */
    public LanguageBatchResultItem setDetectedLanguages(List<DetectedLanguage> detectedLanguages) {
        this.detectedLanguages = detectedLanguages;
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
     * @return the LanguageBatchResultItem object itself.
     */
    public LanguageBatchResultItem setStatistics(DocumentStatistics statistics) {
        this.statistics = statistics;
        return this;
    }

    public void validate() {
        if (getDetectedLanguages() != null) {
            getDetectedLanguages().forEach(e -> e.validate());
        }
        if (getStatistics() != null) {
            getStatistics().validate();
        }
    }
}
