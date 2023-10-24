// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.modelflattening.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The wrapped produc.
 */
@Fluent
public final class WrappedProduct {
    /*
     * the product value
     */
    @JsonProperty(value = "value")
    private String value;

    /**
     * Creates an instance of WrappedProduct class.
     */
    public WrappedProduct() {}

    /**
     * Get the value property: the product value.
     * 
     * @return the value value.
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Set the value property: the product value.
     * 
     * @param value the value value to set.
     * @return the WrappedProduct object itself.
     */
    public WrappedProduct setValue(String value) {
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
