package com.azure.androidtest.fixtures.errorresponse.models;

import com.azure.android.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * The PetHungryOrThirstyError model.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "errorType")
@JsonTypeName("PetHungryOrThirstyError")
@Fluent
public final class PetHungryOrThirstyError extends PetSadError {
    /*
     * is the pet hungry or thirsty or both
     */
    @JsonProperty(value = "hungryOrThirsty")
    private String hungryOrThirsty;

    /**
     * Get the hungryOrThirsty property: is the pet hungry or thirsty or both.
     * 
     * @return the hungryOrThirsty value.
     */
    public String getHungryOrThirsty() {
        return this.hungryOrThirsty;
    }

    /**
     * Set the hungryOrThirsty property: is the pet hungry or thirsty or both.
     * 
     * @param hungryOrThirsty the hungryOrThirsty value to set.
     * @return the PetHungryOrThirstyError object itself.
     */
    public PetHungryOrThirstyError setHungryOrThirsty(String hungryOrThirsty) {
        this.hungryOrThirsty = hungryOrThirsty;
        return this;
    }
}
