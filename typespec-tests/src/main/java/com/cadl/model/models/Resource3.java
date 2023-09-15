// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.model.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The Resource3 model. */
@Immutable
public final class Resource3 {
    /*
     * The name property.
     */
    @Generated
    @JsonProperty(value = "name")
    private String name;

    /*
     * The outputData3 property.
     */
    @Generated
    @JsonProperty(value = "outputData3")
    private OutputData3 outputData3;

    /**
     * Creates an instance of Resource3 class.
     *
     * @param name the name value to set.
     * @param outputData3 the outputData3 value to set.
     */
    @Generated
    @JsonCreator
    private Resource3(
            @JsonProperty(value = "name") String name, @JsonProperty(value = "outputData3") OutputData3 outputData3) {
        this.name = name;
        this.outputData3 = outputData3;
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

    /**
     * Get the outputData3 property: The outputData3 property.
     *
     * @return the outputData3 value.
     */
    @Generated
    public OutputData3 getOutputData3() {
        return this.outputData3;
    }
}
