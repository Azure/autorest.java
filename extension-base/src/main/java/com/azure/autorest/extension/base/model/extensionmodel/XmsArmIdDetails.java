// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.model.extensionmodel;

import java.util.List;

public class XmsArmIdDetails {

    List<AllowedResource> allowedResources;

    public List<AllowedResource> getAllowedResources() {
        return allowedResources;
    }

    public void setAllowedResources(List<AllowedResource> allowedResources) {
        this.allowedResources = allowedResources;
    }
}
