package com.azure.autorest.model.clientmodel;

import com.azure.autorest.extension.base.plugin.JavaSettings;

import java.util.List;
import java.util.Set;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


/**
 * The details of a ServiceClient.
 */
public class ServiceClient {
    private String packageName;
    /**
     * Get the name of this client's class.
     */
    private String className;
    /**
     * Get the name of this client's interface.
     */
    private String interfaceName;
    /**
     * Get the REST API that this client will send requests to.
     */
    private Proxy proxy;
    /**
     * The MethodGroupClients that belong to this ServiceClient.
     */
    private List<MethodGroupClient> methodGroupClients;
    /**
     * The properties of this ServiceClient.
     */
    private List<ServiceClientProperty> properties;
    /**
     * The constructors for this ServiceClient.
     */
    private List<Constructor> constructors;
    /**
     * The client method overloads for this ServiceClient.
     */
    private List<ClientMethod> clientMethods;
    private ClientMethodParameter azureEnvironmentParameter;
    private ClientMethodParameter tokenCredentialParameter;
    private ClientMethodParameter httpPipelineParameter;

    /**
     * Create a new ServiceClient with the provided properties.
     * @param package The package that this service client belongs to.
     * @param className The name of the client's class.
     * @param interfaceName The name of the client's interface.
     * @param proxy The REST API that the client will send requests to.
     * @param methodGroupClients The MethodGroupClients that belong to this ServiceClient.
     * @param properties The properties of this ServiceClient
     * @param constructors The constructors for this ServiceClient.
     * @param clientMethods The client method overloads for this ServiceClient.
     * @param azureEnvironmentParameter The AzureEnvironment parameter.
     * @param tokenCredentialParameter The credentials parameter.
     * @param httpPipelineParameter The HttpPipeline parameter.
     */
    public ServiceClient(String package_Keyword, String className, String interfaceName, Proxy proxy, List<MethodGroupClient> methodGroupClients, List<ServiceClientProperty> properties, List<Constructor> constructors, List<ClientMethod> clientMethods, com.azure.autorest.model.clientmodel.ClientMethodParameter azureEnvironmentParameter, com.azure.autorest.model.clientmodel.ClientMethodParameter tokenCredentialParameter, com.azure.autorest.model.clientmodel.ClientMethodParameter httpPipelineParameter) {
        packageName = package_Keyword;
        this.className = className;
        this.interfaceName = interfaceName;
        this.proxy = proxy;
        this.methodGroupClients = methodGroupClients;
        this.properties = properties;
        this.constructors = constructors;
        this.clientMethods = clientMethods;
        this.azureEnvironmentParameter = azureEnvironmentParameter;
        this.tokenCredentialParameter = tokenCredentialParameter;
        this.httpPipelineParameter = httpPipelineParameter;
    }

    public final String getPackage() {
        return packageName;
    }

    public final String getClassName() {
        return className;
    }

    public final String getInterfaceName() {
        return interfaceName;
    }

    public final Proxy getProxy() {
        return proxy;
    }

    public final List<MethodGroupClient> getMethodGroupClients() {
        return methodGroupClients;
    }

    public final List<ServiceClientProperty> getProperties() {
        return properties;
    }

    public final List<Constructor> getConstructors() {
        return constructors;
    }

    public final List<ClientMethod> getClientMethods() {
        return clientMethods;
    }

    public final com.azure.autorest.model.clientmodel.ClientMethodParameter getAzureEnvironmentParameter() {
        return azureEnvironmentParameter;
    }

    public final com.azure.autorest.model.clientmodel.ClientMethodParameter getTokenCredentialParameter() {
        return tokenCredentialParameter;
    }

    public final com.azure.autorest.model.clientmodel.ClientMethodParameter getHttpPipelineParameter() {
        return httpPipelineParameter;
    }

    /**
     * Add this property's imports to the provided ISet of imports.
     * @param imports The set of imports to add to.
     * @param includeImplementationImports Whether or not to include imports that are only necessary for method implementations.
     */
    public final void addImportsTo(Set<String> imports, boolean includeImplementationImports, boolean includeBuilderImports, JavaSettings settings) {
        for (ClientMethod clientMethod : getClientMethods()) {
            clientMethod.addImportsTo(imports, includeImplementationImports, settings);
        }

        for (ServiceClientProperty serviceClientProperty : getProperties()) {
            serviceClientProperty.addImportsTo(imports, includeImplementationImports);
        }

        if (includeImplementationImports) {
            if (settings.isAzureOrFluent()) {
                //imports.add("com.microsoft.azure.management.AzureProxy");
                imports.add("com.microsoft.azure.management.AzureServiceClient");
            } else {
                imports.add("com.azure.core.http.rest.RestProxy");
            }

            for (Constructor constructor : getConstructors()) {
                constructor.addImportsTo(imports, includeImplementationImports);
            }
        }

        if (includeBuilderImports || includeImplementationImports) {
            if (!settings.isFluent() && settings.shouldGenerateClientInterfaces()) {
                imports.add(String.format("%1$s.%2$s", settings.getPackage(), getInterfaceName()));
                for (MethodGroupClient methodGroupClient : getMethodGroupClients()) {
                    imports.add(String.format("%1$s.%2$s", settings.getPackage(), methodGroupClient.getInterfaceName()));
                }
            }

            imports.add("com.azure.core.http.HttpPipelineBuilder");
            imports.add("com.azure.core.http.policy.CookiePolicy");
            imports.add("com.azure.core.http.policy.RetryPolicy");
            imports.add("com.azure.core.http.policy.UserAgentPolicy");
        }

        Proxy proxy = getProxy();
        if (proxy != null) {
            proxy.addImportsTo(imports, includeImplementationImports, settings);
        }
    }
}