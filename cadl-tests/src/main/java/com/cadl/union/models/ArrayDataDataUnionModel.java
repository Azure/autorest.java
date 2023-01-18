// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.cadl.union.models;

import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonValue;

/** The ArrayDataDataUnionModel model. */
@Immutable
public final class ArrayDataDataUnionModel extends DataUnionModelBase {
    private ArrayData value;

    /**
     * Creates an instance of ArrayDataDataUnionModel class.
     *
     * @param value the value.
     */
    public ArrayDataDataUnionModel(ArrayData value) {
        this.value = value;
    }

    /**
     * Gets the value.
     *
     * @return the value.
     */
    @JsonValue
    public ArrayData getValue() {
        return this.value;
    }
}
