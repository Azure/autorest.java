// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.requiredoptional.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The IntOptionalWrapper model.
 */
@Fluent
public final class IntOptionalWrapper {
    /*
     * The value property.
     */
    @JsonProperty(value = "value")
    private Integer value;

    /**
     * Creates an instance of IntOptionalWrapper class.
     */
    public IntOptionalWrapper() {}

    /**
     * Get the value property: The value property.
     * 
     * @return the value value.
     */
    public Integer getValue() {
        return this.value;
    }

    /**
     * Set the value property: The value property.
     * 
     * @param value the value value to set.
     * @return the IntOptionalWrapper object itself.
     */
    public IntOptionalWrapper setValue(Integer value) {
        this.value = value;
        return this;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {}
}
