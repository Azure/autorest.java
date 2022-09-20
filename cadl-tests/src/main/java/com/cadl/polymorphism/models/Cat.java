// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.cadl.polymorphism.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

/** The Cat model. */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "kind")
@JsonTypeName("cat")
@Fluent
public final class Cat extends Pet {
    /*
     * The meow property.
     */
    @JsonProperty(value = "meow", required = true)
    private int meow;

    /**
     * Creates an instance of Cat class.
     *
     * @param name the name value to set.
     * @param meow the meow value to set.
     */
    @JsonCreator
    private Cat(
            @JsonProperty(value = "name", required = true) String name,
            @JsonProperty(value = "meow", required = true) int meow) {
        super(name);
        this.meow = meow;
    }

    /**
     * Get the meow property: The meow property.
     *
     * @return the meow value.
     */
    public int getMeow() {
        return this.meow;
    }
}
