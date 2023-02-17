// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.unions.models;

import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonValue;

/** The Model2NamedUnionModel model. */
@Immutable
public final class Model2NamedUnionModel extends NamedUnionModelBase {
    private final Model2 value;

    /**
     * Creates an instance of Model2NamedUnionModel class.
     *
     * @param value the value.
     */
    public Model2NamedUnionModel(Model2 value) {
        this.value = value;
    }

    /**
     * Gets the value.
     *
     * @return the value.
     */
    @JsonValue
    public Model2 getValue() {
        return this.value;
    }
}
