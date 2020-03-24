package com.azure.ai.textanalytics.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The MatchRecord model.
 */
@Fluent
public final class MatchRecord {
    /*
     * (optional) If a well-known item with Wikipedia link is recognized, a
     * decimal number denoting the confidence level of the Wikipedia info will
     * be returned.
     */
    @JsonProperty(value = "wikipediaScore")
    private Double wikipediaScore;

    /*
     * (optional) If an entity type is recognized, a decimal number denoting
     * the confidence level of the entity type will be returned.
     */
    @JsonProperty(value = "entityTypeScore")
    private Double entityTypeScore;

    /*
     * Entity text as appears in the request.
     */
    @JsonProperty(value = "text")
    private String text;

    /*
     * Start position (in Unicode characters) for the entity match text.
     */
    @JsonProperty(value = "offset")
    private Integer offset;

    /*
     * Length (in Unicode characters) for the entity match text.
     */
    @JsonProperty(value = "length")
    private Integer length;

    /**
     * Get the wikipediaScore property: (optional) If a well-known item with
     * Wikipedia link is recognized, a decimal number denoting the confidence
     * level of the Wikipedia info will be returned.
     * 
     * @return the wikipediaScore value.
     */
    public Double getWikipediaScore() {
        return this.wikipediaScore;
    }

    /**
     * Set the wikipediaScore property: (optional) If a well-known item with
     * Wikipedia link is recognized, a decimal number denoting the confidence
     * level of the Wikipedia info will be returned.
     * 
     * @param wikipediaScore the wikipediaScore value to set.
     * @return the MatchRecord object itself.
     */
    public MatchRecord setWikipediaScore(Double wikipediaScore) {
        this.wikipediaScore = wikipediaScore;
        return this;
    }

    /**
     * Get the entityTypeScore property: (optional) If an entity type is
     * recognized, a decimal number denoting the confidence level of the entity
     * type will be returned.
     * 
     * @return the entityTypeScore value.
     */
    public Double getEntityTypeScore() {
        return this.entityTypeScore;
    }

    /**
     * Set the entityTypeScore property: (optional) If an entity type is
     * recognized, a decimal number denoting the confidence level of the entity
     * type will be returned.
     * 
     * @param entityTypeScore the entityTypeScore value to set.
     * @return the MatchRecord object itself.
     */
    public MatchRecord setEntityTypeScore(Double entityTypeScore) {
        this.entityTypeScore = entityTypeScore;
        return this;
    }

    /**
     * Get the text property: Entity text as appears in the request.
     * 
     * @return the text value.
     */
    public String getText() {
        return this.text;
    }

    /**
     * Set the text property: Entity text as appears in the request.
     * 
     * @param text the text value to set.
     * @return the MatchRecord object itself.
     */
    public MatchRecord setText(String text) {
        this.text = text;
        return this;
    }

    /**
     * Get the offset property: Start position (in Unicode characters) for the
     * entity match text.
     * 
     * @return the offset value.
     */
    public Integer getOffset() {
        return this.offset;
    }

    /**
     * Set the offset property: Start position (in Unicode characters) for the
     * entity match text.
     * 
     * @param offset the offset value to set.
     * @return the MatchRecord object itself.
     */
    public MatchRecord setOffset(Integer offset) {
        this.offset = offset;
        return this;
    }

    /**
     * Get the length property: Length (in Unicode characters) for the entity
     * match text.
     * 
     * @return the length value.
     */
    public Integer getLength() {
        return this.length;
    }

    /**
     * Set the length property: Length (in Unicode characters) for the entity
     * match text.
     * 
     * @param length the length value to set.
     * @return the MatchRecord object itself.
     */
    public MatchRecord setLength(Integer length) {
        this.length = length;
        return this;
    }

    public void validate() {
    }
}
