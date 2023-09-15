// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.internal.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The StandAloneData model. */
@Immutable
public final class StandAloneData {
    /*
     * The property property.
     */
    @Generated
    @JsonProperty(value = "property")
    private StandAloneDataInner property;

    /**
     * Creates an instance of StandAloneData class.
     *
     * @param property the property value to set.
     */
    @Generated
    @JsonCreator
    private StandAloneData(@JsonProperty(value = "property") StandAloneDataInner property) {
        this.property = property;
    }

    /**
     * Get the property property: The property property.
     *
     * @return the property value.
     */
    @Generated
    public StandAloneDataInner getProperty() {
        return this.property;
    }
}
