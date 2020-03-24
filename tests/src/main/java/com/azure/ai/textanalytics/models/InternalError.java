package com.azure.ai.textanalytics.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The InternalError model.
 */
@Fluent
public final class InternalError {
    /*
     * The code property.
     */
    @JsonProperty(value = "code")
    private String code;

    /*
     * The message property.
     */
    @JsonProperty(value = "message")
    private String message;

    /*
     * The innerError property.
     */
    @JsonProperty(value = "innerError")
    private InternalError innerError;

    /**
     * Get the code property: The code property.
     * 
     * @return the code value.
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Set the code property: The code property.
     * 
     * @param code the code value to set.
     * @return the InternalError object itself.
     */
    public InternalError setCode(String code) {
        this.code = code;
        return this;
    }

    /**
     * Get the message property: The message property.
     * 
     * @return the message value.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Set the message property: The message property.
     * 
     * @param message the message value to set.
     * @return the InternalError object itself.
     */
    public InternalError setMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * Get the innerError property: The innerError property.
     * 
     * @return the innerError value.
     */
    public InternalError getInnerError() {
        return this.innerError;
    }

    /**
     * Set the innerError property: The innerError property.
     * 
     * @param innerError the innerError value to set.
     * @return the InternalError object itself.
     */
    public InternalError setInnerError(InternalError innerError) {
        this.innerError = innerError;
        return this;
    }

    public void validate() {
        if (getInnerError() != null) {
            getInnerError().validate();
        }
    }
}
