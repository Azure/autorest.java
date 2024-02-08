// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.
package fixtures.bodycomplex.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Duration;

/**
 * The DurationWrapper model.
 */
@Fluent
public final class DurationWrapper {

    /*
     * The field property.
     */
    @JsonProperty(value = "field")
    private Duration field;

    /**
     * Creates an instance of DurationWrapper class.
     */
    public DurationWrapper() {
    }

    /**
     * Get the field property: The field property.
     *
     * @return the field value.
     */
    public Duration getField() {
        return this.field;
    }

    /**
     * Set the field property: The field property.
     *
     * @param field the field value to set.
     * @return the DurationWrapper object itself.
     */
    public DurationWrapper setField(Duration field) {
        this.field = field;
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
