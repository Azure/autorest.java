package com.azure.ai.textanalytics.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The RequestStatistics model.
 */
@Fluent
public final class RequestStatistics {
    /*
     * Number of documents submitted in the request.
     */
    @JsonProperty(value = "documentsCount")
    private Integer documentsCount;

    /*
     * Number of valid documents. This excludes empty, over-size limit or
     * non-supported languages documents.
     */
    @JsonProperty(value = "validDocumentsCount")
    private Integer validDocumentsCount;

    /*
     * Number of invalid documents. This includes empty, over-size limit or
     * non-supported languages documents.
     */
    @JsonProperty(value = "erroneousDocumentsCount")
    private Integer erroneousDocumentsCount;

    /*
     * Number of transactions for the request.
     */
    @JsonProperty(value = "transactionsCount")
    private Long transactionsCount;

    /**
     * Get the documentsCount property: Number of documents submitted in the
     * request.
     * 
     * @return the documentsCount value.
     */
    public Integer getDocumentsCount() {
        return this.documentsCount;
    }

    /**
     * Set the documentsCount property: Number of documents submitted in the
     * request.
     * 
     * @param documentsCount the documentsCount value to set.
     * @return the RequestStatistics object itself.
     */
    public RequestStatistics setDocumentsCount(Integer documentsCount) {
        this.documentsCount = documentsCount;
        return this;
    }

    /**
     * Get the validDocumentsCount property: Number of valid documents. This
     * excludes empty, over-size limit or non-supported languages documents.
     * 
     * @return the validDocumentsCount value.
     */
    public Integer getValidDocumentsCount() {
        return this.validDocumentsCount;
    }

    /**
     * Set the validDocumentsCount property: Number of valid documents. This
     * excludes empty, over-size limit or non-supported languages documents.
     * 
     * @param validDocumentsCount the validDocumentsCount value to set.
     * @return the RequestStatistics object itself.
     */
    public RequestStatistics setValidDocumentsCount(Integer validDocumentsCount) {
        this.validDocumentsCount = validDocumentsCount;
        return this;
    }

    /**
     * Get the erroneousDocumentsCount property: Number of invalid documents.
     * This includes empty, over-size limit or non-supported languages
     * documents.
     * 
     * @return the erroneousDocumentsCount value.
     */
    public Integer getErroneousDocumentsCount() {
        return this.erroneousDocumentsCount;
    }

    /**
     * Set the erroneousDocumentsCount property: Number of invalid documents.
     * This includes empty, over-size limit or non-supported languages
     * documents.
     * 
     * @param erroneousDocumentsCount the erroneousDocumentsCount value to set.
     * @return the RequestStatistics object itself.
     */
    public RequestStatistics setErroneousDocumentsCount(Integer erroneousDocumentsCount) {
        this.erroneousDocumentsCount = erroneousDocumentsCount;
        return this;
    }

    /**
     * Get the transactionsCount property: Number of transactions for the
     * request.
     * 
     * @return the transactionsCount value.
     */
    public Long getTransactionsCount() {
        return this.transactionsCount;
    }

    /**
     * Set the transactionsCount property: Number of transactions for the
     * request.
     * 
     * @param transactionsCount the transactionsCount value to set.
     * @return the RequestStatistics object itself.
     */
    public RequestStatistics setTransactionsCount(Long transactionsCount) {
        this.transactionsCount = transactionsCount;
        return this;
    }

    public void validate() {
    }
}
