// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.multipleinheritance.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Horse model.
 */
@Fluent
public final class Horse extends Pet {
    /*
     * The isAShowHorse property.
     */
    @JsonProperty(value = "isAShowHorse")
    private Boolean isAShowHorse;

    /**
     * Creates an instance of Horse class.
     */
    public Horse() {}

    /**
     * Get the isAShowHorse property: The isAShowHorse property.
     * 
     * @return the isAShowHorse value.
     */
    public Boolean isAShowHorse() {
        return this.isAShowHorse;
    }

    /**
     * Set the isAShowHorse property: The isAShowHorse property.
     * 
     * @param isAShowHorse the isAShowHorse value to set.
     * @return the Horse object itself.
     */
    public Horse setIsAShowHorse(Boolean isAShowHorse) {
        this.isAShowHorse = isAShowHorse;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Horse setName(String name) {
        super.setName(name);
        return this;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    @Override
    public void validate() {
        super.validate();
    }
}
