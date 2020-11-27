package com.azure.androidtest.fixtures.bodycomplex.models;

import com.azure.android.core.annotation.Fluent;
import com.azure.android.core.util.DateTimeRfc1123;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Datetimerfc1123Wrapper model.
 */
@Fluent
public final class Datetimerfc1123Wrapper {
    /*
     * The field property.
     */
    @JsonProperty(value = "field")
    private DateTimeRfc1123 field;

    /*
     * The now property.
     */
    @JsonProperty(value = "now")
    private DateTimeRfc1123 now;

    /**
     * Get the field property: The field property.
     * 
     * @return the field value.
     */
    public DateTimeRfc1123 getField() {
        return this.field;
    }

    /**
     * Set the field property: The field property.
     * 
     * @param field the field value to set.
     * @return the Datetimerfc1123Wrapper object itself.
     */
    public Datetimerfc1123Wrapper setField(DateTimeRfc1123 field) {
        this.field = field;
        return this;
    }

    /**
     * Get the now property: The now property.
     * 
     * @return the now value.
     */
    public DateTimeRfc1123 getNow() {
        return this.now;
    }

    /**
     * Set the now property: The now property.
     * 
     * @param now the now value to set.
     * @return the Datetimerfc1123Wrapper object itself.
     */
    public Datetimerfc1123Wrapper setNow(DateTimeRfc1123 now) {
        this.now = now;
        return this;
    }
}
