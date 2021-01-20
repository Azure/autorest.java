package com.azure.androidtest.fixtures.bodycomplex.models;

import com.azure.android.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.threeten.bp.Duration;

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
}
