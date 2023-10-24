// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.model.inheritance.enumdiscriminator.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.regex.Pattern;

/**
 * Test fixed enum type for discriminator.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "kind", defaultImpl = Snake.class)
@JsonTypeName("Snake")
@JsonSubTypes({
    @JsonSubTypes.Type(name = "cobra", value = Cobra.class)
})
@Immutable
public class Snake {
    /*
     * Length of the snake
     */
    @Generated
    @JsonProperty(value = "length")
    private int length;

    /**
     * Creates an instance of Snake class.
     * 
     * @param length the length value to set.
     */
    @Generated
    @JsonCreator
    public Snake(@JsonProperty(value = "length") int length) {
        this.length = length;
    }

    /**
     * Get the length property: Length of the snake.
     * 
     * @return the length value.
     */
    @Generated
    public int getLength() {
        return this.length;
    }
}
