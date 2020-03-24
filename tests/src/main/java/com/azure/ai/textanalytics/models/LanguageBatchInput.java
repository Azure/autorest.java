package com.azure.ai.textanalytics.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * The LanguageBatchInput model.
 */
@Fluent
public final class LanguageBatchInput {
    /*
     * The documents property.
     */
    @JsonProperty(value = "documents")
    private List<LanguageInput> documents;

    /**
     * Get the documents property: The documents property.
     * 
     * @return the documents value.
     */
    public List<LanguageInput> getDocuments() {
        return this.documents;
    }

    /**
     * Set the documents property: The documents property.
     * 
     * @param documents the documents value to set.
     * @return the LanguageBatchInput object itself.
     */
    public LanguageBatchInput setDocuments(List<LanguageInput> documents) {
        this.documents = documents;
        return this;
    }

    public void validate() {
        if (getDocuments() != null) {
            getDocuments().forEach(e -> e.validate());
        }
    }
}
