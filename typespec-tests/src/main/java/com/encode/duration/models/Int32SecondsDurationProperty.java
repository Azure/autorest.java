// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.encode.duration.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Duration;

/** The Int32SecondsDurationProperty model. */
@Immutable
public final class Int32SecondsDurationProperty {
    /*
     * The value property.
     */
    @Generated
    @JsonProperty(value = "value")
    private long value;

    /**
     * Creates an instance of Int32SecondsDurationProperty class.
     *
     * @param value the value value to set.
     */
    @Generated
    @JsonCreator
    public Int32SecondsDurationProperty(@JsonProperty(value = "value") Duration value) {
        this.value = value.getSeconds();
    }

    /**
     * Get the value property: The value property.
     *
     * @return the value value.
     */
    @Generated
    public Duration getValue() {
        return Duration.ofSeconds(this.value);
    }
}
