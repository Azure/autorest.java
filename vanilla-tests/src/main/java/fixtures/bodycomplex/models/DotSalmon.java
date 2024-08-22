// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex.models;

import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeId;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * The DotSalmon model.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "fish.type", defaultImpl = DotSalmon.class, visible = true)
@JsonTypeName("DotSalmon")
@Immutable
public final class DotSalmon extends DotFish {
    /*
     * The fish.type property.
     */
    @JsonTypeId
    @JsonProperty(value = "fish.type", required = true)
    private String fishType = "DotSalmon";

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
     * Creates an instance of DotSalmon class.
     */
    private DotSalmon() {
    }

    /**
     * Get the fishType property: The fish.type property.
     * 
     * @return the fishType value.
     */
    public String getFishType() {
        return this.fishType;
    }

    /**
     * Get the location property: The location property.
     * 
     * @return the location value.
     */
    public String getLocation() {
        return this.location;
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
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    @Override
    public void validate() {
        super.validate();
    }
}
