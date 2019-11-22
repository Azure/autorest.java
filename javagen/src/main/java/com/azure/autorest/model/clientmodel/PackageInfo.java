package com.azure.autorest.model.clientmodel;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

/** 
 The details needed to create a package-info class for the client.
*/
public class PackageInfo
{
    public PackageInfo(String package_Keyword, String description)
    {
        Package = package_Keyword;
        Description = description;
    }

    private String Package;

    private String Description;

    public String getPackage() {
        return Package;
    }

    public final String getDescription()
    {
        return Description;
    }
}