// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.util.logging.ClientLogger;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The ReadonlyObj model. */
@Fluent
public final class ReadonlyObj {
    private static final ClientLogger LOGGER = new ClientLogger(ReadonlyObj.class);

    /*
     * The id property.
     */
    @JsonProperty(value = "id", access = JsonProperty.Access.WRITE_ONLY)
    private String id;

    /*
     * The size property.
     */
    @JsonProperty(value = "size")
    private Integer size;

    /**
     * Get the id property: The id property.
     *
     * @return the id value.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Get the size property: The size property.
     *
     * @return the size value.
     */
    public Integer getSize() {
        return this.size;
    }

    /**
     * Set the size property: The size property.
     *
     * @param size the size value to set.
     * @return the ReadonlyObj object itself.
     */
    public ReadonlyObj setSize(Integer size) {
        this.size = size;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {}
}
