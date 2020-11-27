package com.azure.androidtest.fixtures.bodycomplex.models;

import com.azure.android.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The BooleanWrapper model.
 */
@Fluent
public final class BooleanWrapper {
    /*
     * The field_true property.
     */
    @JsonProperty(value = "field_true")
    private Boolean fieldTrue;

    /*
     * The field_false property.
     */
    @JsonProperty(value = "field_false")
    private Boolean fieldFalse;

    /**
     * Get the fieldTrue property: The field_true property.
     * 
     * @return the fieldTrue value.
     */
    public Boolean isFieldTrue() {
        return this.fieldTrue;
    }

    /**
     * Set the fieldTrue property: The field_true property.
     * 
     * @param fieldTrue the fieldTrue value to set.
     * @return the BooleanWrapper object itself.
     */
    public BooleanWrapper setFieldTrue(Boolean fieldTrue) {
        this.fieldTrue = fieldTrue;
        return this;
    }

    /**
     * Get the fieldFalse property: The field_false property.
     * 
     * @return the fieldFalse value.
     */
    public Boolean isFieldFalse() {
        return this.fieldFalse;
    }

    /**
     * Set the fieldFalse property: The field_false property.
     * 
     * @param fieldFalse the fieldFalse value to set.
     * @return the BooleanWrapper object itself.
     */
    public BooleanWrapper setFieldFalse(Boolean fieldFalse) {
        this.fieldFalse = fieldFalse;
        return this;
    }
}
