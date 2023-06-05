// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.type.model.inheritance.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

/** The third level model GoblinShark in polymorphic multiple levels inheritance. */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "sharktype")
@JsonTypeName("goblin")
@Immutable
public final class GoblinShark extends Shark {
    /**
     * Creates an instance of GoblinShark class.
     *
     * @param age the age value to set.
     */
    @Generated
    @JsonCreator
    public GoblinShark(@JsonProperty(value = "age", required = true) int age) {
        super(age);
    }
}
