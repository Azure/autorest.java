// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.constants.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The ModelAsStringNoRequiredTwoValueNoDefault model.
 */
@Fluent
public final class ModelAsStringNoRequiredTwoValueNoDefault {
    /*
     * The parameter property.
     */
    @JsonProperty(value = "parameter")
    private ModelAsStringNoRequiredTwoValueNoDefaultEnum parameter;

    /**
     * Creates an instance of ModelAsStringNoRequiredTwoValueNoDefault class.
     */
    public ModelAsStringNoRequiredTwoValueNoDefault() {}

    /**
     * Get the parameter property: The parameter property.
     * 
     * @return the parameter value.
     */
    public ModelAsStringNoRequiredTwoValueNoDefaultEnum getParameter() {
        return this.parameter;
    }

    /**
     * Set the parameter property: The parameter property.
     * 
     * @param parameter the parameter value to set.
     * @return the ModelAsStringNoRequiredTwoValueNoDefault object itself.
     */
    public ModelAsStringNoRequiredTwoValueNoDefault
        setParameter(ModelAsStringNoRequiredTwoValueNoDefaultEnum parameter) {
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
