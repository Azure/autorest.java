package com.azure.autorest.preprocessor.model.clientmodel;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

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
