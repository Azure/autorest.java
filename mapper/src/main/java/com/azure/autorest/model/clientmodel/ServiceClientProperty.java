package com.azure.autorest.model.clientmodel;

import java.util.*;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


/** 
 A property that exists within a service's client.
*/
public class ServiceClientProperty
{
    /**
     Create a new ServiceClientProperty with the provided properties.

     @param description The description of this property.
     @param type The type of this property that is exposed via the client.
     @param name The name of this property.
     @param isReadOnly Whether or not this property's value can be changed by the client library.
     @param defaultValueExpression The expression that evaluates to this property's default value.
    */
    public ServiceClientProperty(String description, IType type, String name, boolean isReadOnly, String defaultValueExpression)
    {
        Description = description;
        Type = type;
        Name = name;
        IsReadOnly = isReadOnly;
        DefaultValueExpression = defaultValueExpression;
    }

    /**
     The description of this property.
    */
    private String Description;
    public final String getDescription()
    {
        return Description;
    }

    /**
     The type of this property that is exposed via the client.
    */
    private IType Type;
    public final IType getType()
    {
        return Type;
    }

    /**
     The name of this property.
    */
    private String Name;
    public final String getName()
    {
        return Name;
    }

    /**
     Get whether or not this property's value can be changed by the client library.
    */
    private boolean IsReadOnly;
    public final boolean getIsReadOnly()
    {
        return IsReadOnly;
    }

    /**
     Get the expression that evaluates to this property's default value.
    */
    private String DefaultValueExpression;
    public final String getDefaultValueExpression()
    {
        return DefaultValueExpression;
    }

    /**
     Add this property's imports to the provided ISet of imports.

     @param imports The set of imports to add to.
     @param includeImplementationImports Whether or not to include imports that are only necessary for method implementations.
    */
    public final void AddImportsTo(Set<String> imports, boolean includeImplementationImports)
    {
        getType().AddImportsTo(imports, includeImplementationImports);
    }
}