// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.requiredoptional.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The StringOptionalWrapper model.
 */
@Fluent
public final class StringOptionalWrapper {
    /*
     * The value property.
     */
    @JsonProperty(value = "value")
    private String value;

    /**
     * Creates an instance of StringOptionalWrapper class.
     */
    public StringOptionalWrapper() {
    }

    /**
     * Get the value property: The value property.
     * 
     * @return the value value.
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Set the value property: The value property.
     * 
     * @param value the value value to set.
     * @return the StringOptionalWrapper object itself.
     */
    public StringOptionalWrapper setValue(String value) {
        this.value = value;
        return this;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
    }
}
