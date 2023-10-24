// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.xmlservice.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The JsonInput model.
 */
@Fluent
public final class JsonInput {
    /*
     * The id property.
     */
    @JsonProperty(value = "id")
    private Integer id;

    /**
     * Creates an instance of JsonInput class.
     */
    public JsonInput() {}

    /**
     * Get the id property: The id property.
     * 
     * @return the id value.
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Set the id property: The id property.
     * 
     * @param id the id value to set.
     * @return the JsonInput object itself.
     */
    public JsonInput setId(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {}
}
