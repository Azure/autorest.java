package com.azure.autorest.models.clientmodel;

import java.util.*;
import java.util.stream.Collectors;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


/** 
 A parameter for a method.
*/
public class ClientMethodParameter
{
    /**
     Create a new Parameter with the provided properties.

     @param description The description of this parameter.
     @param isFinal Whether or not this parameter is final.
     @param wireType The type of this parameter.
     @param name The name of this parameter.
     @param isRequired Whether or not this parameter is required.
     @param isConstant Whether or not this parameter has a constant value.
     @param fromClient Whether or not this parameter is from a client property.
     @param annotations The annotations that should be part of this Parameter's declaration.
    */
    public ClientMethodParameter(String description, boolean isFinal, IType wireType, String name, boolean isRequired, boolean isConstant, boolean fromClient, String defaultValue, List<ClassType> annotations)
    {
        Description = description;
        IsFinal = isFinal;
        WireType = wireType;
        Name = name;
        IsRequired = isRequired;
        IsConstant = isConstant;
        FromClient = fromClient;
        DefaultValue = defaultValue;
        Annotations = annotations;
    }

    /**
     The description of this parameter.
    */
    private String Description;
    public final String getDescription()
    {
        return Description;
    }

    /**
     Whether or not this parameter is final.
    */
    private boolean IsFinal;
    public final boolean getIsFinal()
    {
        return IsFinal;
    }

    /**
     The type of this parameter.
    */
    public final IType getClientType()
    {
        return getWireType().getClientType();
    }

    /**
     The type of this parameter.
    */
    private IType WireType;
    public final IType getWireType()
    {
        return WireType;
    }

    /**
     The name of this parameter.
    */
    private String Name;
    public final String getName()
    {
        return Name;
    }

    /**
     Whether or not this parameter is required.
    */
    private boolean IsRequired;
    public final boolean getIsRequired()
    {
        return IsRequired;
    }

    /**
     Whether or not this parameter has a constant value.
    */
    private boolean IsConstant;
    public final boolean getIsConstant()
    {
        return IsConstant;
    }

    /**
     Whether or not this parameter is from a client property.
    */
    private boolean FromClient;
    public final boolean getFromClient()
    {
        return FromClient;
    }

    private String DefaultValue;
    public final String getDefaultValue()
    {
        return DefaultValue;
    }

    /**
     The annotations that should be part of this Parameter's declaration.
    */
    private List<ClassType> Annotations;
    public final List<ClassType> getAnnotations()
    {
        return Annotations;
    }

    /**
     The full declaration of this parameter as it appears in a method signature.
    */
    public final String getDeclaration()
    {
        return getAnnotations().stream().map((ClassType annotation) -> String.format("@%1$s ", annotation.getName())).collect(Collectors.joining("")) + (getIsFinal() ? "final " : "") + String.format("%1$s %2$s", getClientType(), getName());
    }

    /**
     Add this parameter's imports to the provided ISet of imports.

     @param imports The set of imports to add to.
     @param includeImplementationImports Whether or not to include imports that are only necessary for method implementations.
    */
    public void AddImportsTo(Set<String> imports, boolean includeImplementationImports)
    {
        for (ClassType annotation : getAnnotations())
        {
            annotation.AddImportsTo(imports, includeImplementationImports);
        }
        getClientType().AddImportsTo(imports, includeImplementationImports);
    }
}