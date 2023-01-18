// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.cadl.union.models;

import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.List;

/** The StringListInputModel model. */
@Immutable
public final class StringListInputModel extends InputModelBase {
    private final List<String> value;

    /**
     * Creates an instance of StringListInputModel class.
     *
     * @param value the value.
     */
    public StringListInputModel(List<String> value) {
        this.value = value;
    }

    /**
     * Gets the value.
     *
     * @return the value.
     */
    @JsonValue
    public List<String> getValue() {
        return this.value;
    }
}
