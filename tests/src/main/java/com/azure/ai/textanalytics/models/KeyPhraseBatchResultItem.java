package com.azure.ai.textanalytics.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * The KeyPhraseBatchResultItem model.
 */
@Fluent
public final class KeyPhraseBatchResultItem {
    /*
     * Unique, non-empty document identifier.
     */
    @JsonProperty(value = "id")
    private String id;

    /*
     * A list of representative words or phrases. The number of key phrases
     * returned is proportional to the number of words in the input document.
     */
    @JsonProperty(value = "keyPhrases", access = JsonProperty.Access.WRITE_ONLY)
    private List<String> keyPhrases;

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
     * @return the KeyPhraseBatchResultItem object itself.
     */
    public KeyPhraseBatchResultItem setId(String id) {
        this.id = id;
        return this;
    }

    /**
     * Get the keyPhrases property: A list of representative words or phrases.
     * The number of key phrases returned is proportional to the number of
     * words in the input document.
     * 
     * @return the keyPhrases value.
     */
    public List<String> getKeyPhrases() {
        return this.keyPhrases;
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
     * @return the KeyPhraseBatchResultItem object itself.
     */
    public KeyPhraseBatchResultItem setStatistics(DocumentStatistics statistics) {
        this.statistics = statistics;
        return this;
    }

    public void validate() {
        if (getStatistics() != null) {
            getStatistics().validate();
        }
    }
}
