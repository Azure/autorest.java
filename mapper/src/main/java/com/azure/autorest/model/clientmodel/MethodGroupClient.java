package com.azure.autorest.model.clientmodel;

import com.azure.autorest.extension.base.plugin.JavaSettings;

import java.util.List;
import java.util.Set;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


/** 
 The details of a group of methods within a ServiceClient.
*/
public class MethodGroupClient
{
    /**
     Create a new MethodGroupClient with the provided properties.

     @param package The package of the client's class.
     @param className The name of the client's class.
     @param interfaceName The name of the client's interface.
     @param implementedInterfaces The interfaces that the client implements.
     @param proxy The REST API that the client will send requests to.
     @param serviceClientName The name of the ServiceClient that contains this MethodGroupClient.
     @param variableType The type of this MethodGroupClient when it is used as a variable.
     @param variableName The variable name for any instances of this MethodGroupClient.
     @param clientMethods The ClientMethods for this MethodGroupClient.
    */
    public MethodGroupClient(String package_Keyword, String className, String interfaceName, List<String> implementedInterfaces, Proxy proxy, String serviceClientName, String variableType, String variableName, List<ClientMethod> clientMethods)
    {
        Package = package_Keyword;
        ClassName = className;
        InterfaceName = interfaceName;
        ImplementedInterfaces = implementedInterfaces;
        Proxy = proxy;
        ServiceClientName = serviceClientName;
        VariableType = variableType;
        VariableName = variableName;
        ClientMethods = clientMethods;
    }

    private String Package;
    public final String getPackage()
    {
        return Package;
    }

    /**
     Get the name of this client's class.
    */
    private String ClassName;
    public final String getClassName()
    {
        return ClassName;
    }

    /**
     Get the name of this client's interface.
    */
    private String InterfaceName;
    public final String getInterfaceName()
    {
        return InterfaceName;
    }

    /**
     Get the interfaces that the client implements.
    */
    private List<String> ImplementedInterfaces;
    public final List<String> getImplementedInterfaces()
    {
        return ImplementedInterfaces;
    }

    /**
     Get the REST API that this client will send requests to.
    */
    private Proxy Proxy;
    public final Proxy getProxy()
    {
        return Proxy;
    }

    /**
     Get the name of the ServiceClient that contains this MethodGroupClient.
    */
    private String ServiceClientName;
    public final String getServiceClientName()
    {
        return ServiceClientName;
    }

    /**
     Get the type of this MethodGroupClient when it is used as a variable.
    */
    private String VariableType;
    public final String getVariableType()
    {
        return VariableType;
    }

    /**
     Get the variable name for any instances of this MethodGroupClient.
    */
    private String VariableName;
    public final String getVariableName()
    {
        return VariableName;
    }

    /**
     The client method overloads for this MethodGroupClient.
    */
    private List<ClientMethod> ClientMethods;
    public final List<ClientMethod> getClientMethods()
    {
        return ClientMethods;
    }

    /**
     Add this property's imports to the provided ISet of imports.

     @param imports The set of imports to add to.
     @param includeImplementationImports Whether or not to include imports that are only necessary for method implementations.
    */
    public final void AddImportsTo(Set<String> imports, boolean includeImplementationImports, JavaSettings settings)
    {
        if (!settings.IsFluent && settings.GenerateClientInterfaces)
        {
            imports.add(String.format("%1$s.%2$s", settings.Package, getInterfaceName()));
        }

        if (includeImplementationImports)
        {
            ClassType proxyType = settings.IsAzureOrFluent ? ClassType.AzureProxy : ClassType.RestProxy;
            imports.add(proxyType.getFullName());
        }

        getProxy().AddImportsTo(imports, includeImplementationImports, settings);

        for (ClientMethod clientMethod : getClientMethods())
        {
            clientMethod.AddImportsTo(imports, includeImplementationImports, settings);
        }
    }
}