// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.model.clientmodel;

/**
 * The details needed to create a package-info class for the client.
 */
public class PackageInfo {
    private final String packageName;
    private final String description;

    public PackageInfo(String packageKeyword, String description) {
        packageName = packageKeyword;
        this.description = description;
    }

    public String getPackage() {
        return packageName;
    }

    public final String getDescription() {
        return description;
    }
}
