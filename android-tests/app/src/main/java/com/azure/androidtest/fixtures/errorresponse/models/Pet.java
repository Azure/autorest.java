package com.azure.androidtest.fixtures.errorresponse.models;

import com.azure.android.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Pet model.
 */
@Immutable
public final class Pet extends Animal {
    /*
     * Gets the Pet by id.
     */
    @JsonProperty(value = "name", access = JsonProperty.Access.WRITE_ONLY)
    private String name;

    /**
     * Get the name property: Gets the Pet by id.
     * 
     * @return the name value.
     */
    public String getName() {
        return this.name;
    }
}
