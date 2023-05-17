// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.encode.duration.models;

import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Duration;

/** The FloatSecondsDurationProperty model. */
@Immutable
public final class FloatSecondsDurationProperty {
    /*
     * The value property.
     */
    @JsonProperty(value = "value", required = true)
    private double value;

    /**
     * Creates an instance of FloatSecondsDurationProperty class.
     *
     * @param value the value value to set.
     */
    @JsonCreator
    public FloatSecondsDurationProperty(@JsonProperty(value = "value", required = true) Duration value) {
        this.value = (double) value.toNanos() / 1000_000_000L;
    }

    /**
     * Get the value property: The value property.
     *
     * @return the value value.
     */
    public Duration getValue() {
        return Duration.ofNanos((long) (this.value * 1000_000_000L));
    }
}
