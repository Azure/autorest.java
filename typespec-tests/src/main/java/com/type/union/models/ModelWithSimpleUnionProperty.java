// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.type.union.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The ModelWithSimpleUnionProperty model. */
@Immutable
public final class ModelWithSimpleUnionProperty {
    /*
     * The simpleUnion property.
     */
    @Generated
    @JsonProperty(value = "simpleUnion", required = true)
    private SimpleUnionModelBase simpleUnion;

    /**
     * Creates an instance of ModelWithSimpleUnionProperty class.
     *
     * @param simpleUnion the simpleUnion value to set.
     */
    @Generated
    @JsonCreator
    public ModelWithSimpleUnionProperty(
            @JsonProperty(value = "simpleUnion", required = true) SimpleUnionModelBase simpleUnion) {
        this.simpleUnion = simpleUnion;
    }

    /**
     * Get the simpleUnion property: The simpleUnion property.
     *
     * @return the simpleUnion value.
     */
    @Generated
    public SimpleUnionModelBase getSimpleUnion() {
        return this.simpleUnion;
    }
}
