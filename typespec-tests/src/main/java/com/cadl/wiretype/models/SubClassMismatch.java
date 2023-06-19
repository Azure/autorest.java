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

/** The SubClassMismatch model. */
@Immutable
public final class SubClassMismatch extends SuperClass {
    /*
     * The dateTimeRfc7231 property.
     */
    @Generated
    @JsonProperty(value = "dateTimeRfc7231")
    private DateTimeRfc1123 dateTimeRfc7231;

    /**
     * Creates an instance of SubClassMismatch class.
     *
     * @param dateTime the dateTime value to set.
     * @param dateTimeRfc7231 the dateTimeRfc7231 value to set.
     */
    @Generated
    @JsonCreator
    public SubClassMismatch(
            @JsonProperty(value = "dateTime") OffsetDateTime dateTime,
            @JsonProperty(value = "dateTimeRfc7231") OffsetDateTime dateTimeRfc7231) {
        super(dateTime);
        this.dateTimeRfc7231 = new DateTimeRfc1123(dateTimeRfc7231);
    }

    /**
     * Get the dateTimeRfc7231 property: The dateTimeRfc7231 property.
     *
     * @return the dateTimeRfc7231 value.
     */
    @Generated
    public OffsetDateTime getDateTimeRfc7231() {
        if (this.dateTimeRfc7231 == null) {
            return null;
        }
        return this.dateTimeRfc7231.getDateTime();
    }
}
