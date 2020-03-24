package com.azure.ai.textanalytics.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The DocumentStatistics model.
 */
@Fluent
public final class DocumentStatistics {
    /*
     * Number of text elements recognized in the document.
     */
    @JsonProperty(value = "charactersCount")
    private Integer charactersCount;

    /*
     * Number of transactions for the document.
     */
    @JsonProperty(value = "transactionsCount")
    private Integer transactionsCount;

    /**
     * Get the charactersCount property: Number of text elements recognized in
     * the document.
     * 
     * @return the charactersCount value.
     */
    public Integer getCharactersCount() {
        return this.charactersCount;
    }

    /**
     * Set the charactersCount property: Number of text elements recognized in
     * the document.
     * 
     * @param charactersCount the charactersCount value to set.
     * @return the DocumentStatistics object itself.
     */
    public DocumentStatistics setCharactersCount(Integer charactersCount) {
        this.charactersCount = charactersCount;
        return this;
    }

    /**
     * Get the transactionsCount property: Number of transactions for the
     * document.
     * 
     * @return the transactionsCount value.
     */
    public Integer getTransactionsCount() {
        return this.transactionsCount;
    }

    /**
     * Set the transactionsCount property: Number of transactions for the
     * document.
     * 
     * @param transactionsCount the transactionsCount value to set.
     * @return the DocumentStatistics object itself.
     */
    public DocumentStatistics setTransactionsCount(Integer transactionsCount) {
        this.transactionsCount = transactionsCount;
        return this;
    }

    public void validate() {
    }
}
