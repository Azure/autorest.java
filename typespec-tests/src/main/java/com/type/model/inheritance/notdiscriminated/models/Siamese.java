// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.model.inheritance.notdiscriminated.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.regex.Pattern;

/**
 * The third level model in the normal multiple levels inheritance.
 */
@Immutable
public final class Siamese extends Cat {
    /*
     * The smart property.
     */
    @Generated
    @JsonProperty(value = "smart")
    private boolean smart;

    /**
     * Creates an instance of Siamese class.
     * 
     * @param name the name value to set.
     * @param age the age value to set.
     * @param smart the smart value to set.
     */
    @Generated
    @JsonCreator
    public Siamese(@JsonProperty(value = "name") String name, @JsonProperty(value = "age") int age, @JsonProperty(value = "smart") boolean smart) {
        super(name, age);
        this.smart = smart;
    }

    /**
     * Get the smart property: The smart property.
     * 
     * @return the smart value.
     */
    @Generated
    public boolean isSmart() {
        return this.smart;
    }
}
