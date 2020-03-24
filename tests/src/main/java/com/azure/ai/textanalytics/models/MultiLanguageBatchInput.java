package com.azure.ai.textanalytics.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * The MultiLanguageBatchInput model.
 */
@Fluent
public final class MultiLanguageBatchInput {
    /*
     * The documents property.
     */
    @JsonProperty(value = "documents")
    private List<MultiLanguageInput> documents;

    /**
     * Get the documents property: The documents property.
     * 
     * @return the documents value.
     */
    public List<MultiLanguageInput> getDocuments() {
        return this.documents;
    }

    /**
     * Set the documents property: The documents property.
     * 
     * @param documents the documents value to set.
     * @return the MultiLanguageBatchInput object itself.
     */
    public MultiLanguageBatchInput setDocuments(List<MultiLanguageInput> documents) {
        this.documents = documents;
        return this;
    }

    public void validate() {
        if (getDocuments() != null) {
            getDocuments().forEach(e -> e.validate());
        }
    }
}
