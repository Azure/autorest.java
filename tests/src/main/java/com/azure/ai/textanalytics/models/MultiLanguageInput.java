package com.azure.ai.textanalytics.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The MultiLanguageInput model.
 */
@Fluent
public final class MultiLanguageInput {
    /*
     * This is the 2 letter ISO 639-1 representation of a language. For
     * example, use "en" for English; "es" for Spanish etc.,
     */
    @JsonProperty(value = "language")
    private String language;

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
     * Get the language property: This is the 2 letter ISO 639-1 representation
     * of a language. For example, use "en" for English; "es" for Spanish
     * etc.,.
     * 
     * @return the language value.
     */
    public String getLanguage() {
        return this.language;
    }

    /**
     * Set the language property: This is the 2 letter ISO 639-1 representation
     * of a language. For example, use "en" for English; "es" for Spanish
     * etc.,.
     * 
     * @param language the language value to set.
     * @return the MultiLanguageInput object itself.
     */
    public MultiLanguageInput setLanguage(String language) {
        this.language = language;
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
     * @return the MultiLanguageInput object itself.
     */
    public MultiLanguageInput setId(String id) {
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
     * @return the MultiLanguageInput object itself.
     */
    public MultiLanguageInput setText(String text) {
        this.text = text;
        return this;
    }

    public void validate() {
    }
}
