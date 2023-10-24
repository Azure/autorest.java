// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.constants.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The NoModelAsStringNoRequiredOneValueDefault model.
 */
@Fluent
public final class NoModelAsStringNoRequiredOneValueDefault {
    /*
     * The parameter property.
     */
    @JsonProperty(value = "parameter")
    private String parameter = "value1";

    /**
     * Creates an instance of NoModelAsStringNoRequiredOneValueDefault class.
     */
    public NoModelAsStringNoRequiredOneValueDefault() {
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
     * @return the NoModelAsStringNoRequiredOneValueDefault object itself.
     */
    public NoModelAsStringNoRequiredOneValueDefault setParameter(String parameter) {
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
