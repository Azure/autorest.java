// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.model.clientmodel;

/**
 * The details needed to create a package-info class for the client.
 */
public class PackageInfo {
    private String packageName;
    private String description;

    public PackageInfo(String package_Keyword, String description) {
        packageName = package_Keyword;
        this.description = description;
    }

    public String getPackage() {
        return packageName;
    }

    public final String getDescription() {
        return description;
    }
}
