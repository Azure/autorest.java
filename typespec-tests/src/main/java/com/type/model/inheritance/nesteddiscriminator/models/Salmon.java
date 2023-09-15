// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.model.inheritance.nesteddiscriminator.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.List;
import java.util.Map;

/**
 * The second level model in polymorphic multiple levels inheritance which contains references to other polymorphic
 * instances.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "kind")
@JsonTypeName("salmon")
@Fluent
public final class Salmon extends Fish {
    /*
     * The friends property.
     */
    @Generated
    @JsonProperty(value = "friends")
    private List<Fish> friends;

    /*
     * The hate property.
     */
    @Generated
    @JsonProperty(value = "hate")
    private Map<String, Fish> hate;

    /*
     * The partner property.
     */
    @Generated
    @JsonProperty(value = "partner")
    private Fish partner;

    /**
     * Creates an instance of Salmon class.
     *
     * @param age the age value to set.
     */
    @Generated
    @JsonCreator
    public Salmon(@JsonProperty(value = "age") int age) {
        super(age);
    }

    /**
     * Get the friends property: The friends property.
     *
     * @return the friends value.
     */
    @Generated
    public List<Fish> getFriends() {
        return this.friends;
    }

    /**
     * Set the friends property: The friends property.
     *
     * @param friends the friends value to set.
     * @return the Salmon object itself.
     */
    @Generated
    public Salmon setFriends(List<Fish> friends) {
        this.friends = friends;
        return this;
    }

    /**
     * Get the hate property: The hate property.
     *
     * @return the hate value.
     */
    @Generated
    public Map<String, Fish> getHate() {
        return this.hate;
    }

    /**
     * Set the hate property: The hate property.
     *
     * @param hate the hate value to set.
     * @return the Salmon object itself.
     */
    @Generated
    public Salmon setHate(Map<String, Fish> hate) {
        this.hate = hate;
        return this;
    }

    /**
     * Get the partner property: The partner property.
     *
     * @return the partner value.
     */
    @Generated
    public Fish getPartner() {
        return this.partner;
    }

    /**
     * Set the partner property: The partner property.
     *
     * @param partner the partner value to set.
     * @return the Salmon object itself.
     */
    @Generated
    public Salmon setPartner(Fish partner) {
        this.partner = partner;
        return this;
    }
}
