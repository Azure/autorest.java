// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.model.extensionmodel;

import java.util.List;

/**
 * Represents the details of an ARM ID.
 */
public class XmsArmIdDetails {
    private List<AllowedResource> allowedResources;

    /**
     * Gets the resources that are allowed to be accessed.
     *
     * @return The resources that are allowed to be accessed.
     */
    public List<AllowedResource> getAllowedResources() {
        return allowedResources;
    }

    /**
     * Sets the resources that are allowed to be accessed.
     *
     * @param allowedResources The resources that are allowed to be accessed.
     */
    public void setAllowedResources(List<AllowedResource> allowedResources) {
        this.allowedResources = allowedResources;
    }
}
