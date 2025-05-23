// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.core.util.CoreUtils;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The ByteWrapper model.
 */
@Fluent
public final class ByteWrapper {
    /*
     * The field property.
     */
    @Generated
    @JsonProperty(value = "field")
    private byte[] field;

    /**
     * Creates an instance of ByteWrapper class.
     */
    @Generated
    public ByteWrapper() {
    }

    /**
     * Get the field property: The field property.
     * 
     * @return the field value.
     */
    @Generated
    public byte[] getField() {
        return CoreUtils.clone(this.field);
    }

    /**
     * Set the field property: The field property.
     * 
     * @param field the field value to set.
     * @return the ByteWrapper object itself.
     */
    @Generated
    public ByteWrapper setField(byte[] field) {
        this.field = CoreUtils.clone(field);
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
