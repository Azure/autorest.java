package com.azure.androidtest.fixtures.errorresponse.models;

import com.azure.android.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * The PetSadError model.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "errorType", defaultImpl = PetSadError.class)
@JsonTypeName("PetSadError")
@JsonSubTypes({
    @JsonSubTypes.Type(name = "PetHungryOrThirstyError", value = PetHungryOrThirstyError.class)
})
@Fluent
public class PetSadError extends PetActionError {
    /*
     * why is the pet sad
     */
    @JsonProperty(value = "reason")
    private String reason;

    /**
     * Get the reason property: why is the pet sad.
     * 
     * @return the reason value.
     */
    public String getReason() {
        return this.reason;
    }

    /**
     * Set the reason property: why is the pet sad.
     * 
     * @param reason the reason value to set.
     * @return the PetSadError object itself.
     */
    public PetSadError setReason(String reason) {
        this.reason = reason;
        return this;
    }
}
