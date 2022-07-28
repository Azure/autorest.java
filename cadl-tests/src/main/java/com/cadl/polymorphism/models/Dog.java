// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.cadl.polymorphism.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

/** The Dog model. */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "kind")
@JsonTypeName("dog")
@Fluent
public final class Dog extends Pet {
    /*
     * The bark property.
     */
    @JsonProperty(value = "bark", required = true)
    private String bark;

    /**
     * Creates an instance of Dog class.
     *
     * @param name the name value to set.
     * @param bark the bark value to set.
     */
    @JsonCreator
    public Dog(
            @JsonProperty(value = "name", required = true) String name,
            @JsonProperty(value = "bark", required = true) String bark) {
        super(name);
        this.bark = bark;
    }

    /**
     * Get the bark property: The bark property.
     *
     * @return the bark value.
     */
    public String getBark() {
        return this.bark;
    }

    /** {@inheritDoc} */
    @Override
    public Dog setWeight(Double weight) {
        super.setWeight(weight);
        return this;
    }
}
