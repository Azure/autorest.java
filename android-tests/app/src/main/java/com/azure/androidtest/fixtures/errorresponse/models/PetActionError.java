package com.azure.androidtest.fixtures.errorresponse.models;

import com.azure.android.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * The PetActionError model.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "errorType", defaultImpl = PetActionError.class)
@JsonTypeName("PetActionError")
@JsonSubTypes({
    @JsonSubTypes.Type(name = "PetSadError", value = PetSadError.class)
})
@Fluent
public class PetActionError extends PetAction {
    /*
     * the error message
     */
    @JsonProperty(value = "errorMessage")
    private String errorMessage;

    /**
     * Get the errorMessage property: the error message.
     * 
     * @return the errorMessage value.
     */
    public String getErrorMessage() {
        return this.errorMessage;
    }

    /**
     * Set the errorMessage property: the error message.
     * 
     * @param errorMessage the errorMessage value to set.
     * @return the PetActionError object itself.
     */
    public PetActionError setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }
}
