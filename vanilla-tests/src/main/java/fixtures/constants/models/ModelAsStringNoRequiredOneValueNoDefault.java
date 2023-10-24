// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.constants.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The ModelAsStringNoRequiredOneValueNoDefault model.
 */
@Fluent
public final class ModelAsStringNoRequiredOneValueNoDefault {
    /*
     * The parameter property.
     */
    @JsonProperty(value = "parameter")
    private ModelAsStringNoRequiredOneValueNoDefaultEnum parameter;

    /**
     * Creates an instance of ModelAsStringNoRequiredOneValueNoDefault class.
     */
    public ModelAsStringNoRequiredOneValueNoDefault() {}

    /**
     * Get the parameter property: The parameter property.
     * 
     * @return the parameter value.
     */
    public ModelAsStringNoRequiredOneValueNoDefaultEnum getParameter() {
        return this.parameter;
    }

    /**
     * Set the parameter property: The parameter property.
     * 
     * @param parameter the parameter value to set.
     * @return the ModelAsStringNoRequiredOneValueNoDefault object itself.
     */
    public ModelAsStringNoRequiredOneValueNoDefault
        setParameter(ModelAsStringNoRequiredOneValueNoDefaultEnum parameter) {
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
