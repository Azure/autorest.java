// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex.implementation.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.util.CoreUtils;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The ByteWrapper model. */
@Fluent
public final class ByteWrapper {
    /*
     * The field property.
     */
    @JsonProperty(value = "field")
    private byte[] field;

    /** Creates an instance of ByteWrapper class. */
    public ByteWrapper() {}

    /**
     * Get the field property: The field property.
     *
     * @return the field value.
     */
    public byte[] getField() {
        return CoreUtils.clone(this.field);
    }

    /**
     * Set the field property: The field property.
     *
     * @param field the field value to set.
     * @return the ByteWrapper object itself.
     */
    public ByteWrapper setField(byte[] field) {
        this.field = CoreUtils.clone(field);
        return this;
    }
}
