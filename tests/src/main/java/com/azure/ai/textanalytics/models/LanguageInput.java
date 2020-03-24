package com.azure.ai.textanalytics.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The LanguageInput model.
 */
@Fluent
public final class LanguageInput {
    /*
     * The countryHint property.
     */
    @JsonProperty(value = "countryHint")
    private String countryHint;

    /*
     * Unique, non-empty document identifier.
     */
    @JsonProperty(value = "id")
    private String id;

    /*
     * The text property.
     */
    @JsonProperty(value = "text")
    private String text;

    /**
     * Get the countryHint property: The countryHint property.
     * 
     * @return the countryHint value.
     */
    public String getCountryHint() {
        return this.countryHint;
    }

    /**
     * Set the countryHint property: The countryHint property.
     * 
     * @param countryHint the countryHint value to set.
     * @return the LanguageInput object itself.
     */
    public LanguageInput setCountryHint(String countryHint) {
        this.countryHint = countryHint;
        return this;
    }

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
     * @return the LanguageInput object itself.
     */
    public LanguageInput setId(String id) {
        this.id = id;
        return this;
    }

    /**
     * Get the text property: The text property.
     * 
     * @return the text value.
     */
    public String getText() {
        return this.text;
    }

    /**
     * Set the text property: The text property.
     * 
     * @param text the text value to set.
     * @return the LanguageInput object itself.
     */
    public LanguageInput setText(String text) {
        this.text = text;
        return this;
    }

    public void validate() {
    }
}
