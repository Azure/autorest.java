// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex.implementation.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.JsonFlatten;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * The DotFish model.
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "fish\\.type",
    defaultImpl = DotFish.class)
@JsonTypeName("DotFish")
@JsonSubTypes({
    @JsonSubTypes.Type(name = "DotSalmon", value = DotSalmon.class)
})
@JsonFlatten
@Fluent
public class DotFish {
    /*
     * The species property.
     */
    @JsonProperty(value = "species")
    private String species;

    /**
     * Creates an instance of DotFish class.
     */
    public DotFish() {
    }

    /**
     * Get the species property: The species property.
     * 
     * @return the species value.
     */
    public String getSpecies() {
        return this.species;
    }

    /**
     * Set the species property: The species property.
     * 
     * @param species the species value to set.
     * @return the DotFish object itself.
     */
    public DotFish setSpecies(String species) {
        this.species = species;
        return this;
    }
}
