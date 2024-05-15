// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.payload.multipart.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;

/**
 * The MultiPartRequest model.
 */
@Immutable
public final class MultiPartRequest {
    /*
     * A sequence of textual characters.
     */
    @Generated
    private final String id;

    /*
     * The profileImage property.
     */
    @Generated
    private final ProfileImageFileDetails profileImage;

    /**
     * Creates an instance of MultiPartRequest class.
     * 
     * @param id the id value to set.
     * @param profileImage the profileImage value to set.
     */
    @Generated
    public MultiPartRequest(String id, ProfileImageFileDetails profileImage) {
        this.id = id;
        this.profileImage = profileImage;
    }

    /**
     * Get the id property: A sequence of textual characters.
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
    public ProfileImageFileDetails getProfileImage() {
        return this.profileImage;
    }
}
