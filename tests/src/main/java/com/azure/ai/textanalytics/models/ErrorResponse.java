package com.azure.ai.textanalytics.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The ErrorResponse model.
 */
@Fluent
public final class ErrorResponse {
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
     * The target property.
     */
    @JsonProperty(value = "target")
    private String target;

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
     * @return the ErrorResponse object itself.
     */
    public ErrorResponse setCode(String code) {
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
     * @return the ErrorResponse object itself.
     */
    public ErrorResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * Get the target property: The target property.
     * 
     * @return the target value.
     */
    public String getTarget() {
        return this.target;
    }

    /**
     * Set the target property: The target property.
     * 
     * @param target the target value to set.
     * @return the ErrorResponse object itself.
     */
    public ErrorResponse setTarget(String target) {
        this.target = target;
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
     * @return the ErrorResponse object itself.
     */
    public ErrorResponse setInnerError(InternalError innerError) {
        this.innerError = innerError;
        return this;
    }

    public void validate() {
        if (getInnerError() != null) {
            getInnerError().validate();
        }
    }
}
