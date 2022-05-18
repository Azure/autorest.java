// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.constants.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The NoModelAsStringNoRequiredTwoValueNoDefault model. */
@Fluent
public final class NoModelAsStringNoRequiredTwoValueNoDefault {
    /*
     * The parameter property.
     */
    @JsonProperty(value = "parameter")
    private NoModelAsStringNoRequiredTwoValueNoDefaultEnum parameter;

    /**
     * Get the parameter property: The parameter property.
     *
     * @return the parameter value.
     */
    public NoModelAsStringNoRequiredTwoValueNoDefaultEnum getParameter() {
        return this.parameter;
    }

    /**
     * Set the parameter property: The parameter property.
     *
     * @param parameter the parameter value to set.
     * @return the NoModelAsStringNoRequiredTwoValueNoDefault object itself.
     */
    public NoModelAsStringNoRequiredTwoValueNoDefault setParameter(
            NoModelAsStringNoRequiredTwoValueNoDefaultEnum parameter) {
        this.parameter = parameter;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {}
}
