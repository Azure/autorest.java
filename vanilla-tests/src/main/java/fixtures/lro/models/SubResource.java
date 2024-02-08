// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.
package fixtures.lro.models;

import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The SubResource model.
 */
@Immutable
public class SubResource {

    /*
     * Sub Resource Id
     */
    @JsonProperty(value = "id", access = JsonProperty.Access.WRITE_ONLY)
    private String id;

    /**
     * Creates an instance of SubResource class.
     */
    public SubResource() {
    }

    /**
     * Get the id property: Sub Resource Id.
     *
     * @return the id value.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
    }
}
