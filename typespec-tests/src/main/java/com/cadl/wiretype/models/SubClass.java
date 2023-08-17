// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.cadl.wiretype.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.core.util.DateTimeRfc1123;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;

/** The SubClass model. */
@Immutable
public final class SubClass extends SuperClassMismatch {
    /*
     * The dateTime property.
     */
    @Generated
    @JsonProperty(value = "dateTime")
    private OffsetDateTime dateTime;

    /**
     * Creates an instance of SubClass class.
     *
     * @param dateTimeRfc7231 the dateTimeRfc7231 value to set.
     * @param dateTime the dateTime value to set.
     */
    @Generated
    public SubClass(OffsetDateTime dateTimeRfc7231, OffsetDateTime dateTime) {
        super(dateTimeRfc7231);
        this.dateTime = dateTime;
    }

    @Generated
    @JsonCreator
    private SubClass(
            @JsonProperty(value = "dateTimeRfc7231") DateTimeRfc1123 dateTimeRfc7231,
            @JsonProperty(value = "dateTime") OffsetDateTime dateTime) {
        this(dateTimeRfc7231.getDateTime(), dateTime);
    }

    /**
     * Get the dateTime property: The dateTime property.
     *
     * @return the dateTime value.
     */
    @Generated
    public OffsetDateTime getDateTime() {
        return this.dateTime;
    }
}
