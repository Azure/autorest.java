package com.azure.autorest.model.clientmodel;

import com.azure.autorest.extension.base.plugin.JavaSettings;

import java.util.List;
import java.util.Set;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


/** 
 The details of a ServiceClient.
*/
public class ServiceClient
{
    /**
     Create a new ServiceClient with the provided properties.

     @param package The package that this service client belongs to.
     @param className The name of the client's class.
     @param interfaceName The name of the client's interface.
     @param restAPI The REST API that the client will send requests to.
     @param methodGroupClients The MethodGroupClients that belong to this ServiceClient.
     @param properties The properties of this ServiceClient
     @param constructors The constructors for this ServiceClient.
     @param clientMethods The client method overloads for this ServiceClient.
     @param azureEnvironmentParameter The AzureEnvironment parameter.
     @param serviceClientCredentialsParameter The credentials parameter.
     @param httpPipelineParameter The HttpPipeline parameter.
    */
    public ServiceClient(String package_Keyword, String className, String interfaceName, Proxy restAPI, List<MethodGroupClient> methodGroupClients, List<ServiceClientProperty> properties, List<Constructor> constructors, List<ClientMethod> clientMethods, com.azure.autorest.model.clientmodel.ClientMethodParameter azureEnvironmentParameter, com.azure.autorest.model.clientmodel.ClientMethodParameter serviceClientCredentialsParameter, com.azure.autorest.model.clientmodel.ClientMethodParameter httpPipelineParameter)
    {
        Package = package_Keyword;
        ClassName = className;
        InterfaceName = interfaceName;
        RestAPI = restAPI;
        MethodGroupClients = methodGroupClients;
        Properties = properties;
        Constructors = constructors;
        ClientMethods = clientMethods;
        AzureEnvironmentParameter = azureEnvironmentParameter;
        ServiceClientCredentialsParameter = serviceClientCredentialsParameter;
        HttpPipelineParameter = httpPipelineParameter;
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
     Get the REST API that this client will send requests to.
    */
    private Proxy RestAPI;
    public final Proxy getRestAPI()
    {
        return RestAPI;
    }

    /**
     The MethodGroupClients that belong to this ServiceClient.
    */
    private List<MethodGroupClient> MethodGroupClients;
    public final List<MethodGroupClient> getMethodGroupClients()
    {
        return MethodGroupClients;
    }

    /**
     The properties of this ServiceClient.
    */
    private List<ServiceClientProperty> Properties;
    public final List<ServiceClientProperty> getProperties()
    {
        return Properties;
    }

    /**
     The constructors for this ServiceClient.
    */
    private List<Constructor> Constructors;
    public final List<Constructor> getConstructors()
    {
        return Constructors;
    }

    /**
     The client method overloads for this ServiceClient.
    */
    private List<ClientMethod> ClientMethods;
    public final List<ClientMethod> getClientMethods()
    {
        return ClientMethods;
    }

    private com.azure.autorest.model.clientmodel.ClientMethodParameter AzureEnvironmentParameter;
    public final com.azure.autorest.model.clientmodel.ClientMethodParameter getAzureEnvironmentParameter()
    {
        return AzureEnvironmentParameter;
    }

    private com.azure.autorest.model.clientmodel.ClientMethodParameter ServiceClientCredentialsParameter;
    public final com.azure.autorest.model.clientmodel.ClientMethodParameter getServiceClientCredentialsParameter()
    {
        return ServiceClientCredentialsParameter;
    }

    private com.azure.autorest.model.clientmodel.ClientMethodParameter HttpPipelineParameter;
    public final com.azure.autorest.model.clientmodel.ClientMethodParameter getHttpPipelineParameter()
    {
        return HttpPipelineParameter;
    }

    /**
     Add this property's imports to the provided ISet of imports.

     @param imports The set of imports to add to.
     @param includeImplementationImports Whether or not to include imports that are only necessary for method implementations.
    */
    public final void AddImportsTo(Set<String> imports, boolean includeImplementationImports, JavaSettings settings)
    {
        for (ClientMethod clientMethod : getClientMethods())
        {
            clientMethod.AddImportsTo(imports, includeImplementationImports, settings);
        }

        for (ServiceClientProperty serviceClientProperty : getProperties())
        {
            serviceClientProperty.AddImportsTo(imports, includeImplementationImports);
        }

        if (includeImplementationImports)
        {
            if (settings.getIsAzureOrFluent())
            {
                imports.add("com.microsoft.azure.v3.AzureServiceClient");
                imports.add("com.microsoft.azure.v3.AzureProxy");
            }
            else
            {
                imports.add("com.azure.core.implementation.RestProxy");
            }

            if (!settings.getIsFluent() && settings.getGenerateClientInterfaces())
            {
                imports.add(String.format("%1$s.%2$s", settings.getPackage(), getInterfaceName()));
                for (MethodGroupClient methodGroupClient : getMethodGroupClients())
                {
                    imports.add(String.format("%1$s.%2$s", settings.getPackage(), methodGroupClient.getInterfaceName()));
                }
            }

            for (Constructor constructor : getConstructors())
            {
                constructor.AddImportsTo(imports, includeImplementationImports);
            }
        }

        Proxy proxy = getRestAPI();
        if (proxy != null) {
            proxy.AddImportsTo(imports, includeImplementationImports, settings);
        }
    }
}