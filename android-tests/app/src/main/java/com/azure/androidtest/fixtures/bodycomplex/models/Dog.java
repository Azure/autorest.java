package com.azure.androidtest.fixtures.bodycomplex.models;

import com.azure.android.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Dog model.
 */
@Fluent
public final class Dog extends Pet {
    /*
     * The food property.
     */
    @JsonProperty(value = "food")
    private String food;

    /**
     * Get the food property: The food property.
     * 
     * @return the food value.
     */
    public String getFood() {
        return this.food;
    }

    /**
     * Set the food property: The food property.
     * 
     * @param food the food value to set.
     * @return the Dog object itself.
     */
    public Dog setFood(String food) {
        this.food = food;
        return this;
    }
}
