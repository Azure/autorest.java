// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.
package fixtures.constants.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The ModelAsStringNoRequiredOneValueDefault model.
 */
@Fluent
public final class ModelAsStringNoRequiredOneValueDefault {

    /*
     * The parameter property.
     */
    @JsonProperty(value = "parameter")
    private ModelAsStringNoRequiredOneValueDefaultEnum parameter = ModelAsStringNoRequiredOneValueDefaultEnum.VALUE1;

    /**
     * Creates an instance of ModelAsStringNoRequiredOneValueDefault class.
     */
    public ModelAsStringNoRequiredOneValueDefault() {
    }

    /**
     * Get the parameter property: The parameter property.
     *
     * @return the parameter value.
     */
    public ModelAsStringNoRequiredOneValueDefaultEnum getParameter() {
        return this.parameter;
    }

    /**
     * Set the parameter property: The parameter property.
     *
     * @param parameter the parameter value to set.
     * @return the ModelAsStringNoRequiredOneValueDefault object itself.
     */
    public ModelAsStringNoRequiredOneValueDefault setParameter(ModelAsStringNoRequiredOneValueDefaultEnum parameter) {
        this.parameter = parameter;
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
