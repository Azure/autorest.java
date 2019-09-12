package com.azure.autorest.model.clientmodel;

import com.azure.autorest.extension.base.plugin.JavaSettings;

import java.util.List;
import java.util.Set;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


/** 
 Details that describe the dynamic proxy.
*/
public class Proxy
{
    /**
     Create a new Proxy using the provided properties.

     @param name The name of the REST API interface.
     @param clientTypeName The name of the method group.
     @param baseURL The base URL that will be used for each REST API method.
     @param methods The methods of this REST API.
    */
    public Proxy(String name, String clientTypeName, String baseURL, List<ProxyMethod> methods)
    {
        Name = name;
        ClientTypeName = clientTypeName;
        BaseURL = baseURL;
        Methods = methods;
    }

    /**
     Get the name of the REST API interface.
    */
    private String Name;
    public final String getName()
    {
        return Name;
    }

    /**
     Get the name of the method group.
    */
    private String ClientTypeName;
    public final String getClientTypeName()
    {
        return ClientTypeName;
    }

    /**
     Get the base URL that will be used for each REST API method.
    */
    private String BaseURL;
    public final String getBaseURL()
    {
        return BaseURL;
    }

    /**
     Get the methods of this REST API.
    */
    private List<ProxyMethod> Methods;
    public final List<ProxyMethod> getMethods()
    {
        return Methods;
    }

    /**
     Add this property's imports to the provided ISet of imports.

     @param imports The set of imports to add to.
     @param includeImplementationImports Whether or not to include imports that are only necessary for method implementations.
    */
    public final void AddImportsTo(Set<String> imports, boolean includeImplementationImports, JavaSettings settings)
    {
        if (includeImplementationImports)
        {
            imports.add("com.azure.core.implementation.annotation.Host");
            imports.add("com.azure.core.implementation.annotation.ServiceInterface");
        }

        for (ProxyMethod method : getMethods())
        {
            method.AddImportsTo(imports, includeImplementationImports, settings);
        }
    }
}