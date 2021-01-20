package com.azure.androidtest.fixtures.bodycomplex.models;

import com.azure.android.core.annotation.Fluent;
import com.azure.android.core.annotation.JsonFlatten;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * The DotSalmon model.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "fish\\.type")
@JsonTypeName("DotSalmon")
@JsonFlatten
@Fluent
public class DotSalmon extends DotFish {
    /*
     * The location property.
     */
    @JsonProperty(value = "location")
    private String location;

    /*
     * The iswild property.
     */
    @JsonProperty(value = "iswild")
    private Boolean iswild;

    /**
     * Get the location property: The location property.
     * 
     * @return the location value.
     */
    public String getLocation() {
        return this.location;
    }

    /**
     * Set the location property: The location property.
     * 
     * @param location the location value to set.
     * @return the DotSalmon object itself.
     */
    public DotSalmon setLocation(String location) {
        this.location = location;
        return this;
    }

    /**
     * Get the iswild property: The iswild property.
     * 
     * @return the iswild value.
     */
    public Boolean iswild() {
        return this.iswild;
    }

    /**
     * Set the iswild property: The iswild property.
     * 
     * @param iswild the iswild value to set.
     * @return the DotSalmon object itself.
     */
    public DotSalmon setIswild(Boolean iswild) {
        this.iswild = iswild;
        return this;
    }
}
