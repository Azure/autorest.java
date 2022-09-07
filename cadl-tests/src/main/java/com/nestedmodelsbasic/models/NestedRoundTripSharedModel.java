// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.nestedmodelsbasic.models;

import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/** Model to illustrate a nested model that appears as a nested model on input, output, and round-trip models. */
@Immutable
public final class NestedRoundTripSharedModel {
    /*
     * Required string, illustrating a reference type property.
     */
    @JsonProperty(value = "requiredString", required = true)
    private String requiredString;

    /*
     * Required int, illustrating a value type property.
     */
    @JsonProperty(value = "requiredInt", required = true)
    private int requiredInt;

    /*
     * Required collection of strings, illustrating a collection of reference types.
     */
    @JsonProperty(value = "requiredStringList", required = true)
    private List<String> requiredStringList;

    /*
     * Required collection of ints, illustrating a collection of value types.
     */
    @JsonProperty(value = "requiredIntList", required = true)
    private List<Integer> requiredIntList;

    /**
     * Creates an instance of NestedRoundTripSharedModel class.
     *
     * @param requiredString the requiredString value to set.
     * @param requiredInt the requiredInt value to set.
     * @param requiredStringList the requiredStringList value to set.
     * @param requiredIntList the requiredIntList value to set.
     */
    @JsonCreator
    public NestedRoundTripSharedModel(
            @JsonProperty(value = "requiredString", required = true) String requiredString,
            @JsonProperty(value = "requiredInt", required = true) int requiredInt,
            @JsonProperty(value = "requiredStringList", required = true) List<String> requiredStringList,
            @JsonProperty(value = "requiredIntList", required = true) List<Integer> requiredIntList) {
        this.requiredString = requiredString;
        this.requiredInt = requiredInt;
        this.requiredStringList = requiredStringList;
        this.requiredIntList = requiredIntList;
    }

    /**
     * Get the requiredString property: Required string, illustrating a reference type property.
     *
     * @return the requiredString value.
     */
    public String getRequiredString() {
        return this.requiredString;
    }

    /**
     * Get the requiredInt property: Required int, illustrating a value type property.
     *
     * @return the requiredInt value.
     */
    public int getRequiredInt() {
        return this.requiredInt;
    }

    /**
     * Get the requiredStringList property: Required collection of strings, illustrating a collection of reference
     * types.
     *
     * @return the requiredStringList value.
     */
    public List<String> getRequiredStringList() {
        return this.requiredStringList;
    }

    /**
     * Get the requiredIntList property: Required collection of ints, illustrating a collection of value types.
     *
     * @return the requiredIntList value.
     */
    public List<Integer> getRequiredIntList() {
        return this.requiredIntList;
    }
}
