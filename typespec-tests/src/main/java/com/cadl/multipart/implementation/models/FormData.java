// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.multipart.implementation.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.core.util.CoreUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The FormData model.
 */
@Immutable
public final class FormData {
    /*
     * The name property.
     */
    @Generated
    @JsonProperty(value = "name")
    private String name;

    /*
     * The image property.
     */
    @Generated
    @JsonProperty(value = "image")
    private byte[] image;

    /**
     * Creates an instance of FormData class.
     * 
     * @param name the name value to set.
     * @param image the image value to set.
     */
    @Generated
    @JsonCreator
    public FormData(@JsonProperty(value = "name") String name, @JsonProperty(value = "image") byte[] image) {
        this.name = name;
        this.image = image;
    }

    /**
     * Get the name property: The name property.
     * 
     * @return the name value.
     */
    @Generated
    public String getName() {
        return this.name;
    }

    /**
     * Get the image property: The image property.
     * 
     * @return the image value.
     */
    @Generated
    public byte[] getImage() {
        return CoreUtils.clone(this.image);
    }
}
