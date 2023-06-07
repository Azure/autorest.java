// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.cadl.naming.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.core.util.CoreUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * summary of Data
 *
 * <p>description of Data.
 */
@Immutable
public final class BinaryData {
    /*
     * summary of data property
     *
     * description of data property
     */
    @Generated
    @JsonProperty(value = "data")
    private byte[] data;

    /**
     * Creates an instance of BinaryData class.
     *
     * @param data the data value to set.
     */
    @Generated
    @JsonCreator
    private BinaryData(@JsonProperty(value = "data") byte[] data) {
        this.data = data;
    }

    /**
     * Get the data property: summary of data property
     *
     * <p>description of data property.
     *
     * @return the data value.
     */
    @Generated
    public byte[] getData() {
        return CoreUtils.clone(this.data);
    }
}
