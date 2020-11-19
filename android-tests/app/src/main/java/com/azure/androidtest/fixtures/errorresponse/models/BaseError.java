package com.azure.androidtest.fixtures.errorresponse.models;

import com.azure.android.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The BaseError model.
 */
@Fluent
public class BaseError {
    /*
     * The someBaseProp property.
     */
    @JsonProperty(value = "someBaseProp")
    private String someBaseProp;

    /**
     * Get the someBaseProp property: The someBaseProp property.
     * 
     * @return the someBaseProp value.
     */
    public String getSomeBaseProp() {
        return this.someBaseProp;
    }

    /**
     * Set the someBaseProp property: The someBaseProp property.
     * 
     * @param someBaseProp the someBaseProp value to set.
     * @return the BaseError object itself.
     */
    public BaseError setSomeBaseProp(String someBaseProp) {
        this.someBaseProp = someBaseProp;
        return this;
    }
}
