// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.model.codemodel;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a language property.
 */
@JsonInclude
@JsonPropertyOrder
public class LanguageProperty {
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    /**
     * Creates a new instance of the LanguageProperty class.
     */
    public LanguageProperty() {
    }

    /**
     * Gets the additional properties of the language property.
     *
     * @return The additional properties of the language property.
     */
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    /**
     * Sets ad additional property of the language property.
     *
     * @param name The name of the additional property.
     * @param value The value of the additional property.
     */
    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
