package com.azure.androidtest.fixtures.bodycomplex.models;

import com.azure.android.core.annotation.Fluent;
import com.azure.android.core.util.CoreUtil;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The ByteWrapper model.
 */
@Fluent
public final class ByteWrapper {
    /*
     * The field property.
     */
    @JsonProperty(value = "field")
    private byte[] field;

    /**
     * Get the field property: The field property.
     * 
     * @return the field value.
     */
    public byte[] getField() {
        return CoreUtil.clone(this.field);
    }

    /**
     * Set the field property: The field property.
     * 
     * @param field the field value to set.
     * @return the ByteWrapper object itself.
     */
    public ByteWrapper setField(byte[] field) {
        this.field = CoreUtil.clone(field);
        return this;
    }
}
