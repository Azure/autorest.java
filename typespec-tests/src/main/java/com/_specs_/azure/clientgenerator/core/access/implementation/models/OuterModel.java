// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com._specs_.azure.clientgenerator.core.access.implementation.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/** Used in internal operations, should be generated but not exported. */
@Immutable
public final class OuterModel extends BaseModel {
    /*
     * The inner property.
     */
    @Generated
    @JsonProperty(value = "inner")
    private InnerModel inner;

    /**
     * Creates an instance of OuterModel class.
     *
     * @param name the name value to set.
     * @param inner the inner value to set.
     */
    @Generated
    @JsonCreator
    private OuterModel(@JsonProperty(value = "name") String name, @JsonProperty(value = "inner") InnerModel inner) {
        super(name);
        this.inner = inner;
    }

    /**
     * Get the inner property: The inner property.
     *
     * @return the inner value.
     */
    @Generated
    public InnerModel getInner() {
        return this.inner;
    }
}
