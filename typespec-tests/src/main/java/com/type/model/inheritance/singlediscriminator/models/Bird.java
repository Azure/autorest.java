// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.model.inheritance.singlediscriminator.models;

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
 * This is base model for polymorphic single level inheritance with a discriminator.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "kind", defaultImpl = Bird.class)
@JsonTypeName("Bird")
@JsonSubTypes({
    @JsonSubTypes.Type(name = "seagull", value = SeaGull.class),
    @JsonSubTypes.Type(name = "sparrow", value = Sparrow.class),
    @JsonSubTypes.Type(name = "goose", value = Goose.class),
    @JsonSubTypes.Type(name = "eagle", value = Eagle.class)
})
@Immutable
public class Bird {
    /*
     * The wingspan property.
     */
    @Generated
    @JsonProperty(value = "wingspan")
    private int wingspan;

    /**
     * Creates an instance of Bird class.
     * 
     * @param wingspan the wingspan value to set.
     */
    @Generated
    @JsonCreator
    public Bird(@JsonProperty(value = "wingspan") int wingspan) {
        this.wingspan = wingspan;
    }

    /**
     * Get the wingspan property: The wingspan property.
     * 
     * @return the wingspan value.
     */
    @Generated
    public int getWingspan() {
        return this.wingspan;
    }
}
