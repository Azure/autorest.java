package com.azure.autorest.models.clientmodel;

import java.util.*;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


/** 
 A return value from a ClientMethod.
*/
public class ReturnValue
{
	/** 
	 Create a new ReturnValue object from the provided properties.
	 
	 @param description The description of the return value.
	 @param type The type of the return value.
	*/
	public ReturnValue(String description, IType type)
	{
		Description = description;
		Type = type;
	}

	/** 
	 The description of the return value.
	*/
	private String Description;
	public final String getDescription()
	{
		return Description;
	}

	/** 
	 The type of the return value.
	*/
	private IType Type;
	public final IType getType()
	{
		return Type;
	}

	/** 
	 Add this return value's imports to the provided ISet of imports.
	 
	 @param imports The set of imports to add to.
	 @param includeImplementationImports Whether or not to include imports that are only necessary for method implementations.
	*/
	public final void AddImportsTo(Set<String> imports, boolean includeImplementationImports)
	{
		getType().AddImportsTo(imports, includeImplementationImports);
	}
}