// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.models.property.types.models;

import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

/** Model with dictionary string properties. */
@Immutable
public final class DictionaryStringProperty {
    /*
     * Property
     */
    @JsonProperty(value = "property", required = true)
    private Map<String, String> property;

    /**
     * Creates an instance of DictionaryStringProperty class.
     *
     * @param property the property value to set.
     */
    @JsonCreator
    public DictionaryStringProperty(@JsonProperty(value = "property", required = true) Map<String, String> property) {
        this.property = property;
    }

    /**
     * Get the property property: Property.
     *
     * @return the property value.
     */
    public Map<String, String> getProperty() {
        return this.property;
    }
}
