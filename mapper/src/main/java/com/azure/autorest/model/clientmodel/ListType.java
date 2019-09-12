package com.azure.autorest.model.clientmodel;

import java.util.*;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


/** 
 A sequence type used by a client.
*/
public class ListType extends GenericType
{
    /**
     Create a new ListType from the provided properties.

     @param elementType The type of elements that are stored in this sequence.
    */
    public ListType(IType elementType)
    {
        super("java.util", "List", elementType);
    }

    /**
     The type of elements that are stored in this sequence.
    */
    public final IType getElementType()
    {
        return getTypeArguments()[0];
    }

    @Override
    public void AddImportsTo(Set<String> imports, boolean includeImplementationImports)
    {
        super.AddImportsTo(imports, includeImplementationImports);
    }
}