package com.azure.autorest.model.clientmodel;

import com.azure.autorest.extension.base.plugin.JavaSettings;

import java.util.List;
import java.util.Set;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


/**
 * The details of a group of methods within a ServiceClient.
 */
public class MethodGroupClient {
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
     * Get the interfaces that the client implements.
     */
    private List<String> implementedInterfaces;
    /**
     * Get the REST API that this client will send requests to.
     */
    private Proxy proxy;
    /**
     * Get the name of the ServiceClient that contains this MethodGroupClient.
     */
    private String serviceClientName;
    /**
     * Get the type of this MethodGroupClient when it is used as a variable.
     */
    private String variableType;
    /**
     * Get the variable name for any instances of this MethodGroupClient.
     */
    private String variableName;
    /**
     * The client method overloads for this MethodGroupClient.
     */
    private List<ClientMethod> clientMethods;

    /**
     * Create a new MethodGroupClient with the provided properties.
     * @param className The name of the client's class.
     * @param interfaceName The name of the client's interface.
     * @param implementedInterfaces The interfaces that the client implements.
     * @param proxy The REST API that the client will send requests to.
     * @param serviceClientName The name of the ServiceClient that contains this MethodGroupClient.
     * @param variableType The type of this MethodGroupClient when it is used as a variable.
     * @param variableName The variable name for any instances of this MethodGroupClient.
     * @param clientMethods The ClientMethods for this MethodGroupClient.
     */
    public MethodGroupClient(String package_Keyword, String className, String interfaceName, List<String> implementedInterfaces, Proxy proxy, String serviceClientName, String variableType, String variableName, List<ClientMethod> clientMethods) {
        packageName = package_Keyword;
        this.className = className;
        this.interfaceName = interfaceName;
        this.implementedInterfaces = implementedInterfaces;
        this.proxy = proxy;
        this.serviceClientName = serviceClientName;
        this.variableType = variableType;
        this.variableName = variableName;
        this.clientMethods = clientMethods;
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

    public final List<String> getImplementedInterfaces() {
        return implementedInterfaces;
    }

    public final Proxy getProxy() {
        return proxy;
    }

    public final String getServiceClientName() {
        return serviceClientName;
    }

    public final String getVariableType() {
        return variableType;
    }

    public final String getVariableName() {
        return variableName;
    }

    public final List<ClientMethod> getClientMethods() {
        return clientMethods;
    }

    /**
     * Add this property's imports to the provided ISet of imports.
     * @param imports The set of imports to add to.
     * @param includeImplementationImports Whether or not to include imports that are only necessary for method implementations.
     */
    public final void addImportsTo(Set<String> imports, boolean includeImplementationImports, JavaSettings settings) {
        if (!settings.isFluent() && settings.shouldGenerateClientInterfaces()) {
            imports.add(String.format("%1$s.%2$s", settings.getPackage(), getInterfaceName()));
        }

        if (includeImplementationImports) {
            //ClassType proxyType = settings.isAzureOrFluent() ? ClassType.AzureProxy : ClassType.RestProxy;
            ClassType proxyType = ClassType.RestProxy;
            imports.add(proxyType.getFullName());
        }

        getProxy().addImportsTo(imports, includeImplementationImports, settings);

        for (ClientMethod clientMethod : getClientMethods()) {
            clientMethod.addImportsTo(imports, includeImplementationImports, settings);
        }
    }
}