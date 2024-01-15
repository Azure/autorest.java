// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.payload.multipart.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.core.util.BinaryData;

/**
 * The JsonPartRequest model.
 */
@Fluent
public final class JsonPartRequest {
    /*
     * The address property.
     */
    @Generated
    private final Address address;

    /*
     * The profileImage property.
     */
    @Generated
    private final BinaryData profileImage;

    /*
     * The filename for profileImage
     */
    @Generated
    private String profileImageFilename = "profileImage";

    /**
     * Creates an instance of JsonPartRequest class.
     * 
     * @param address the address value to set.
     * @param profileImage the profileImage value to set.
     */
    @Generated
    public JsonPartRequest(Address address, BinaryData profileImage) {
        this.address = address;
        this.profileImage = profileImage;
    }

    /**
     * Get the address property: The address property.
     * 
     * @return the address value.
     */
    @Generated
    public Address getAddress() {
        return this.address;
    }

    /**
     * Get the profileImage property: The profileImage property.
     * 
     * @return the profileImage value.
     */
    @Generated
    public BinaryData getProfileImage() {
        return this.profileImage;
    }

    /**
     * Get the profileImageFilename property: The filename for profileImage.
     * 
     * @return the profileImageFilename value.
     */
    @Generated
    public String getProfileImageFilename() {
        return this.profileImageFilename;
    }

    /**
     * Set the profileImageFilename property: The filename for profileImage.
     * 
     * @param profileImageFilename the profileImageFilename value to set.
     * @return the JsonPartRequest object itself.
     */
    @Generated
    public JsonPartRequest setProfileImageFilename(String profileImageFilename) {
        this.profileImageFilename = profileImageFilename;
        return this;
    }
}
