// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.encode.duration.models;

import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Duration;
import java.util.List;

/** The FloatSecondsDurationArrayProperty model. */
@Immutable
public final class FloatSecondsDurationArrayProperty {
    /*
     * The value property.
     */
    @JsonProperty(value = "value", required = true)
    private List<Double> value;

    /**
     * Creates an instance of FloatSecondsDurationArrayProperty class.
     *
     * @param value the value value to set.
     */
    @JsonCreator
    public FloatSecondsDurationArrayProperty(@JsonProperty(value = "value", required = true) List<Duration> value) {
        this.value =
                value.stream()
                        .map(el -> (double) el.toNanos() / 1000_000_000L)
                        .collect(java.util.stream.Collectors.toList());
    }

    /**
     * Get the value property: The value property.
     *
     * @return the value value.
     */
    public List<Duration> getValue() {
        if (this.value == null) {
            return null;
        }
        return this.value.stream()
                .map(el -> Duration.ofNanos((long) (el * 1000_000_000L)))
                .collect(java.util.stream.Collectors.toList());
    }
}
