// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.visibility.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.regex.Pattern;

/**
 * The WriteDog model.
 */
@Immutable
public final class WriteDog {
    /*
     * The name property.
     */
    @Generated
    @JsonProperty(value = "name")
    private String name;

    /**
     * Creates an instance of WriteDog class.
     * 
     * @param name the name value to set.
     */
    @Generated
    @JsonCreator
    public WriteDog(@JsonProperty(value = "name") String name) {
        this.name = name;
    }

    /**
     * Get the name property: The name property.
     * 
     * @return the name value.
     */
    @Generated
    public String getName() {
        return this.name;
    }
}
