package com.azure.androidtest.fixtures.bodydictionary.models;

import com.azure.android.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Widget model.
 */
@Fluent
public final class Widget {
    /*
     * The integer property.
     */
    @JsonProperty(value = "integer")
    private Integer integer;

    /*
     * The string property.
     */
    @JsonProperty(value = "string")
    private String string;

    /**
     * Get the integer property: The integer property.
     * 
     * @return the integer value.
     */
    public Integer getInteger() {
        return this.integer;
    }

    /**
     * Set the integer property: The integer property.
     * 
     * @param integer the integer value to set.
     * @return the Widget object itself.
     */
    public Widget setInteger(Integer integer) {
        this.integer = integer;
        return this;
    }

    /**
     * Get the string property: The string property.
     * 
     * @return the string value.
     */
    public String getString() {
        return this.string;
    }

    /**
     * Set the string property: The string property.
     * 
     * @param string the string value to set.
     * @return the Widget object itself.
     */
    public Widget setString(String string) {
        this.string = string;
        return this;
    }
}
