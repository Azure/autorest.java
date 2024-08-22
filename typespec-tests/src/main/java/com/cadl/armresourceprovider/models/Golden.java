// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.armresourceprovider.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeId;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Golden dog model.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "kind", defaultImpl = Golden.class, visible = true)
@JsonTypeName("golden")
@Fluent
public final class Golden extends Dog {
    /*
     * discriminator property
     */
    @JsonTypeId
    @JsonProperty(value = "kind", required = true)
    private DogKind kind = DogKind.GOLDEN;

    /**
     * Creates an instance of Golden class.
     */
    public Golden() {
    }

    /**
     * Get the kind property: discriminator property.
     * 
     * @return the kind value.
     */
    public DogKind kind() {
        return this.kind;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Golden withWeight(int weight) {
        super.withWeight(weight);
        return this;
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
