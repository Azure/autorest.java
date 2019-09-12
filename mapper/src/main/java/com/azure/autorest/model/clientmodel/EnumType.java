package com.azure.autorest.model.clientmodel;

import java.util.List;
import java.util.Set;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


/** 
 The details of an enumerated type that is used by a service.
*/
public class EnumType implements IType
{
    /**
     Create a new Enum with the provided properties.

     @param name The name of the new Enum.
     @param expandable Whether or not this will be an ExpandableStringEnum type.
     @param values The values of the Enum.
    */
    public EnumType(String package_Keyword, String name, boolean expandable, List<ClientEnumValue> values)
    {
        Name = name;
        Package = package_Keyword;
        Expandable = expandable;
        Values = values;
    }

    /**
     The name of the new Enum.
    */
    private String Name;
    public final String getName()
    {
        return Name;
    }

    /**
     The package that this enumeration belongs to.
    */
    private String Package;
    public final String getPackage()
    {
        return Package;
    }

    /**
     Whether or not this will be an ExpandableStringEnum type.
    */
    private boolean Expandable;
    public final boolean getExpandable()
    {
        return Expandable;
    }

    /**
     The values of the Enum.
    */
    private List<ClientEnumValue> Values;
    public final List<ClientEnumValue> getValues()
    {
        return Values;
    }

    public final void AddImportsTo(Set<String> imports, boolean includeImplementationImports)
    {
        imports.add(String.format("%1$s.%2$s", getPackage(), getName()));
    }

    public final IType AsNullable()
    {
        return this;
    }

    public final boolean Contains(IType type)
    {
        return this == type;
    }

    public final String DefaultValueExpression(String sourceExpression)
    {
        if (sourceExpression == null)
        {
            return null;
        }
        return String.format("%1$s.%2$s", getName(), sourceExpression.toUpperCase());
    }

    public final IType getClientType()
    {
        return this;
    }

    public final String ConvertToClientType(String expression)
    {
        return expression;
    }

    public final String ConvertFromClientType(String expression)
    {
        return expression;
    }

    @Override
    public String toString()
    {
        return getName();
    }
}