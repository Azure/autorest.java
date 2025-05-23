// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The ReadonlyObj model.
 */
@Fluent
public final class ReadonlyObj {
    /*
     * The id property.
     */
    @Generated
    @JsonProperty(value = "id", access = JsonProperty.Access.WRITE_ONLY)
    private String id;

    /*
     * The size property.
     */
    @Generated
    @JsonProperty(value = "size")
    private Integer size;

    /**
     * Creates an instance of ReadonlyObj class.
     */
    @Generated
    public ReadonlyObj() {
    }

    /**
     * Get the id property: The id property.
     * 
     * @return the id value.
     */
    @Generated
    public String getId() {
        return this.id;
    }

    /**
     * Get the size property: The size property.
     * 
     * @return the size value.
     */
    @Generated
    public Integer getSize() {
        return this.size;
    }

    /**
     * Set the size property: The size property.
     * 
     * @param size the size value to set.
     * @return the ReadonlyObj object itself.
     */
    @Generated
    public ReadonlyObj setSize(Integer size) {
        this.size = size;
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
