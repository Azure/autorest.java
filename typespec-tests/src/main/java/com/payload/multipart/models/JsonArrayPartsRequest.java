// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.payload.multipart.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.core.util.BinaryData;
import java.util.List;

/**
 * The JsonArrayPartsRequest model.
 */
@Fluent
public final class JsonArrayPartsRequest {
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

    /*
     * The previousAddresses property.
     */
    @Generated
    private final List<Address> previousAddresses;

    /**
     * Creates an instance of JsonArrayPartsRequest class.
     * 
     * @param profileImage the profileImage value to set.
     * @param previousAddresses the previousAddresses value to set.
     */
    @Generated
    public JsonArrayPartsRequest(BinaryData profileImage, List<Address> previousAddresses) {
        this.profileImage = profileImage;
        this.previousAddresses = previousAddresses;
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
     * @return the JsonArrayPartsRequest object itself.
     */
    @Generated
    public JsonArrayPartsRequest setProfileImageFilename(String profileImageFilename) {
        this.profileImageFilename = profileImageFilename;
        return this;
    }

    /**
     * Get the previousAddresses property: The previousAddresses property.
     * 
     * @return the previousAddresses value.
     */
    @Generated
    public List<Address> getPreviousAddresses() {
        return this.previousAddresses;
    }
}
