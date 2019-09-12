package com.azure.autorest.models.clientmodel;

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

	public String Package;

	private String Description;
	public final String getDescription()
	{
		return Description;
	}
}