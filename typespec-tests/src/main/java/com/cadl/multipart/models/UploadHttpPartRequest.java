// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.multipart.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;

/**
 * The UploadHttpPartRequest model.
 */
@Immutable
public final class UploadHttpPartRequest {
    /*
     * The fileData1 property.
     */
    @Generated
    private final Inherit2FileDetails fileData1;

    /*
     * The fileData2 property.
     */
    @Generated
    private final FileDetails fileData2;

    /*
     * The size property.
     */
    @Generated
    private final Size size;

    /**
     * Creates an instance of UploadHttpPartRequest class.
     * 
     * @param fileData1 the fileData1 value to set.
     * @param fileData2 the fileData2 value to set.
     * @param size the size value to set.
     */
    @Generated
    public UploadHttpPartRequest(Inherit2FileDetails fileData1, FileDetails fileData2, Size size) {
        this.fileData1 = fileData1;
        this.fileData2 = fileData2;
        this.size = size;
    }

    /**
     * Get the fileData1 property: The fileData1 property.
     * 
     * @return the fileData1 value.
     */
    @Generated
    public Inherit2FileDetails getFileData1() {
        return this.fileData1;
    }

    /**
     * Get the fileData2 property: The fileData2 property.
     * 
     * @return the fileData2 value.
     */
    @Generated
    public FileDetails getFileData2() {
        return this.fileData2;
    }

    /**
     * Get the size property: The size property.
     * 
     * @return the size value.
     */
    @Generated
    public Size getSize() {
        return this.size;
    }
}
