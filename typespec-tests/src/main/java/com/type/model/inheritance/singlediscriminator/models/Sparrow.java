// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.model.inheritance.singlediscriminator.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.regex.Pattern;

/**
 * The second level model in polymorphic single level inheritance.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "kind")
@JsonTypeName("sparrow")
@Immutable
public final class Sparrow extends Bird {
    /**
     * Creates an instance of Sparrow class.
     * 
     * @param wingspan the wingspan value to set.
     */
    @Generated
    @JsonCreator
    public Sparrow(@JsonProperty(value = "wingspan") int wingspan) {
        super(wingspan);
    }
}
