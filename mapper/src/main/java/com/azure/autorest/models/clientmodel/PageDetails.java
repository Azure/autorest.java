package com.azure.autorest.models.clientmodel;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

/** 
 A page class that contains results that are received from a service request.
*/
public class PageDetails
{
	public PageDetails(String package_Keyword, String nextLinkName, String itemName, String className)
	{
		Package = package_Keyword;
		NextLinkName = nextLinkName;
		ItemName = itemName;
		ClassName = className;
	}

	public String Package;

	private String NextLinkName;
	public final String getNextLinkName()
	{
		return NextLinkName;
	}

	private String ItemName;
	public final String getItemName()
	{
		return ItemName;
	}

	private String ClassName;
	public final String getClassName()
	{
		return ClassName;
	}
}