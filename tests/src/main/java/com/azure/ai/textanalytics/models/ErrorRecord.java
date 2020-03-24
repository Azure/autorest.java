package com.azure.ai.textanalytics.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The ErrorRecord model.
 */
@Fluent
public final class ErrorRecord {
    /*
     * Input document unique identifier the error refers to.
     */
    @JsonProperty(value = "id")
    private String id;

    /*
     * Error message.
     */
    @JsonProperty(value = "message")
    private String message;

    /**
     * Get the id property: Input document unique identifier the error refers
     * to.
     * 
     * @return the id value.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Set the id property: Input document unique identifier the error refers
     * to.
     * 
     * @param id the id value to set.
     * @return the ErrorRecord object itself.
     */
    public ErrorRecord setId(String id) {
        this.id = id;
        return this;
    }

    /**
     * Get the message property: Error message.
     * 
     * @return the message value.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Set the message property: Error message.
     * 
     * @param message the message value to set.
     * @return the ErrorRecord object itself.
     */
    public ErrorRecord setMessage(String message) {
        this.message = message;
        return this;
    }

    public void validate() {
    }
}
