package com.azure.androidtest.fixtures.errorresponse.models;

import com.azure.android.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Animal model.
 */
@Fluent
public class Animal {
    /*
     * The aniType property.
     */
    @JsonProperty(value = "aniType")
    private String aniType;

    /**
     * Get the aniType property: The aniType property.
     * 
     * @return the aniType value.
     */
    public String getAniType() {
        return this.aniType;
    }

    /**
     * Set the aniType property: The aniType property.
     * 
     * @param aniType the aniType value to set.
     * @return the Animal object itself.
     */
    public Animal setAniType(String aniType) {
        this.aniType = aniType;
        return this;
    }
}
