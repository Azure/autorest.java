// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex.implementation.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

/** The DotSalmon model. */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "fish.type")
@JsonTypeName("DotSalmon")
@Fluent
public final class DotSalmon extends DotFish {
    /*
     * The location property.
     */
    @JsonProperty(value = "location")
    private String location;

    /*
     * The iswild property.
     */
    @JsonProperty(value = "iswild")
    private Boolean isWild;

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
    public boolean isWild() {
        Boolean returnValue = this.isWild;
        return returnValue;
    }

    /**
     * Set the iswild property: The iswild property.
     *
     * @param iswild the iswild value to set.
     * @return the DotSalmon object itself.
     */
    public void setWild(Boolean iswild) {
        this.isWild = iswild;
    }
}
