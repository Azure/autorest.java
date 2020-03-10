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
    /**
     * The package that this service client belongs to.
     */
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
    /**
     * The azure environment parameter.
     */
    private ClientMethodParameter azureEnvironmentParameter;
    /**
     * The credentials parameter.
     */
    private ClientMethodParameter tokenCredentialParameter;
    /**
     * The HttpPipeline parameter.
     */
    private ClientMethodParameter httpPipelineParameter;

    /**
     * Create a new ServiceClient with the provided properties.
     * @param packageName The package that this service client belongs to.
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
    private ServiceClient(String packageName, String className, String interfaceName, Proxy proxy, List<MethodGroupClient> methodGroupClients, List<ServiceClientProperty> properties, List<Constructor> constructors, List<ClientMethod> clientMethods, ClientMethodParameter azureEnvironmentParameter, ClientMethodParameter tokenCredentialParameter, ClientMethodParameter httpPipelineParameter) {
        this.packageName = packageName;
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

    public final ClientMethodParameter getAzureEnvironmentParameter() {
        return azureEnvironmentParameter;
    }

    public final ClientMethodParameter getTokenCredentialParameter() {
        return tokenCredentialParameter;
    }

    public final ClientMethodParameter getHttpPipelineParameter() {
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
                imports.add("com.azure.management.AzureServiceClient");
            }
            if (!getClientMethods().isEmpty()) {
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

    public static class Builder {
        private String packageName;
        private String className;
        private String interfaceName;
        private Proxy proxy;
        private List<MethodGroupClient> methodGroupClients;
        private List<ServiceClientProperty> properties;
        private List<Constructor> constructors;
        private List<ClientMethod> clientMethods;
        private ClientMethodParameter azureEnvironmentParameter;
        private ClientMethodParameter tokenCredentialParameter;
        private ClientMethodParameter httpPipelineParameter;

        /**
         * Sets the package that this service client belongs to.
         * @param packageName the package that this service client belongs to
         * @return the Builder itself
         */
        public Builder packageName(String packageName) {
            this.packageName = packageName;
            return this;
        }

        /**
         * Sets the name of this client's class.
         * @param className the name of this client's class
         * @return the Builder itself
         */
        public Builder className(String className) {
            this.className = className;
            return this;
        }

        /**
         * Sets the name of this client's interface.
         * @param interfaceName the name of this client's interface
         * @return the Builder itself
         */
        public Builder interfaceName(String interfaceName) {
            this.interfaceName = interfaceName;
            return this;
        }

        /**
         * Sets the REST API that this client will send requests to.
         * @param proxy the REST API that this client will send requests to
         * @return the Builder itself
         */
        public Builder proxy(Proxy proxy) {
            this.proxy = proxy;
            return this;
        }

        /**
         * Sets the MethodGroupClients that belong to this ServiceClient.
         * @param methodGroupClients the MethodGroupClients that belong to this ServiceClient
         * @return the Builder itself
         */
        public Builder methodGroupClients(List<MethodGroupClient> methodGroupClients) {
            this.methodGroupClients = methodGroupClients;
            return this;
        }

        /**
         * Sets the properties of this ServiceClient.
         * @param properties the properties of this ServiceClient
         * @return the Builder itself
         */
        public Builder properties(List<ServiceClientProperty> properties) {
            this.properties = properties;
            return this;
        }

        /**
         * Sets the constructors for this ServiceClient.
         * @param constructors the constructors for this ServiceClient
         * @return the Builder itself
         */
        public Builder constructors(List<Constructor> constructors) {
            this.constructors = constructors;
            return this;
        }

        /**
         * Sets the client method overloads for this ServiceClient.
         * @param clientMethods the client method overloads for this ServiceClient
         * @return the Builder itself
         */
        public Builder clientMethods(List<ClientMethod> clientMethods) {
            this.clientMethods = clientMethods;
            return this;
        }

        /**
         * Sets the azure environment parameter.
         * @param azureEnvironmentParameter the azure environment
         * @return the Builder itself
         */
        public Builder azureEnvironmentParameter(ClientMethodParameter azureEnvironmentParameter) {
            this.azureEnvironmentParameter = azureEnvironmentParameter;
            return this;
        }

        /**
         * Sets the credentials parameter.
         * @param tokenCredentialParameter the credentials parameter
         * @return the Builder itself
         */
        public Builder tokenCredentialParameter(ClientMethodParameter tokenCredentialParameter) {
            this.tokenCredentialParameter = tokenCredentialParameter;
            return this;
        }

        /**
         * Sets the HttpPipeline parameter.
         * @param httpPipelineParameter the HttpPipeline parameter
         * @return the Builder itself
         */
        public Builder httpPipelineParameter(ClientMethodParameter httpPipelineParameter) {
            this.httpPipelineParameter = httpPipelineParameter;
            return this;
        }

        public ServiceClient build() {
            return new ServiceClient(packageName,
                    className,
                    interfaceName,
                    proxy,
                    methodGroupClients,
                    properties,
                    constructors,
                    clientMethods,
                    azureEnvironmentParameter,
                    tokenCredentialParameter,
                    httpPipelineParameter);
        }
    }
}
