// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.constants.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The NoModelAsStringRequiredOneValueNoDefault model.
 */
@Fluent
public final class NoModelAsStringRequiredOneValueNoDefault {
    /*
     * The parameter property.
     */
    @JsonProperty(value = "parameter", required = true)
    private String parameter = "value1";

    /**
     * Creates an instance of NoModelAsStringRequiredOneValueNoDefault class.
     */
    public NoModelAsStringRequiredOneValueNoDefault() {
        parameter = "value1";
    }

    /**
     * Get the parameter property: The parameter property.
     * 
     * @return the parameter value.
     */
    public String getParameter() {
        return this.parameter;
    }

    /**
     * Set the parameter property: The parameter property.
     * 
     * @param parameter the parameter value to set.
     * @return the NoModelAsStringRequiredOneValueNoDefault object itself.
     */
    public NoModelAsStringRequiredOneValueNoDefault setParameter(String parameter) {
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
