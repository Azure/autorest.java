package com.azure.androidtest.fixtures.errorresponse.models;

import com.azure.android.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * The AnimalNotFound model.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "whatNotFound")
@JsonTypeName("AnimalNotFound")
@Fluent
public final class AnimalNotFound extends NotFoundErrorBase {
    /*
     * The name property.
     */
    @JsonProperty(value = "name")
    private String name;

    /**
     * Get the name property: The name property.
     * 
     * @return the name value.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the name property: The name property.
     * 
     * @param name the name value to set.
     * @return the AnimalNotFound object itself.
     */
    public AnimalNotFound setName(String name) {
        this.name = name;
        return this;
    }
}
