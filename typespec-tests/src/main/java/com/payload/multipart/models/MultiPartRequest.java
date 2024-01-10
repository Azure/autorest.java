// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.payload.multipart.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.core.util.BinaryData;

/**
 * The MultiPartRequest model.
 */
@Fluent
public final class MultiPartRequest {
    /*
     * The id property.
     */
    @Generated
    private final String id;

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
     * Creates an instance of MultiPartRequest class.
     * 
     * @param id the id value to set.
     * @param profileImage the profileImage value to set.
     */
    @Generated
    public MultiPartRequest(String id, BinaryData profileImage) {
        this.id = id;
        this.profileImage = profileImage;
    }

    /**
     * Get the id property: The id property.
     * 
     * @return the id value.
     */
    @Generated
    public String getId() {
        return this.id;
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
     * @return the MultiPartRequest object itself.
     */
    @Generated
    public MultiPartRequest setProfileImageFilename(String profileImageFilename) {
        this.profileImageFilename = profileImageFilename;
        return this;
    }
}
