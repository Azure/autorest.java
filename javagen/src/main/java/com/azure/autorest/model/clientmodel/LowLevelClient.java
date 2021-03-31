package com.azure.autorest.model.clientmodel;

import com.azure.autorest.extension.base.plugin.JavaSettings;

import java.util.List;
import java.util.Set;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


/**
 * The details of a ServiceClient.
 */
public class LowLevelClient {
    /**
     * The package that this service client belongs to.
     */
    private String packageName;
    /**
     * Get the name of this client's class.
     */
    private String className;
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
     * The HttpPipeline parameter.
     */
    private ClientMethodParameter httpPipelineParameter;

    private ClientMethodParameter objectSerializerParameter;

    private String builderName;

    /**
     * Create a new ServiceClient with the provided properties.
     * @param packageName The package that this service client belongs to.
     * @param className The name of the client's class.
     * @param properties The properties of this ServiceClient
     * @param constructors The constructors for this ServiceClient.
     * @param clientMethods The client method overloads for this ServiceClient.
     * @param httpPipelineParameter The HttpPipeline parameter.
     * @param objectSerializerParameter The SerializerAdapter parameter.
     */
    private LowLevelClient(String packageName, String className, List<ServiceClientProperty> properties, List<Constructor> constructors, List<ClientMethod> clientMethods,
                           ClientMethodParameter httpPipelineParameter, ClientMethodParameter objectSerializerParameter, String builderName) {
        this.packageName = packageName;
        this.className = className;
        this.properties = properties;
        this.constructors = constructors;
        this.clientMethods = clientMethods;
        this.httpPipelineParameter = httpPipelineParameter;
        this.objectSerializerParameter = objectSerializerParameter;
        this.builderName = builderName;
    }

    public final String getPackageName() {
        return packageName;
    }

    public final String getClassName() {
        return className;
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

    public final ClientMethodParameter getHttpPipelineParameter() {
        return httpPipelineParameter;
    }

    public final ClientMethodParameter getObjectSerializerParameter() {
        return objectSerializerParameter;
    }

    public final String getBuilderName() {
        return builderName;
    }

    /**
     * Add this property's imports to the provided ISet of imports.
     * @param imports The set of imports to add to.
     * @param includeImplementationImports Whether or not to include imports that are only necessary for method implementations.
     */
    public final void addImportsTo(Set<String> imports, boolean includeImplementationImports, boolean includeBuilderImports, JavaSettings settings) {
        if (!includeBuilderImports) {
            for (ClientMethod clientMethod : getClientMethods()) {
                clientMethod.addImportsTo(imports, includeImplementationImports, settings);
            }
        }

        for (ServiceClientProperty serviceClientProperty : getProperties()) {
            serviceClientProperty.addImportsTo(imports, includeImplementationImports);
        }

        if (includeImplementationImports) {
            if (settings.isFluentPremium()) {
                imports.add("com.azure.resourcemanager.resources.fluentcore.AzureServiceClient");
            }
            if (!getClientMethods().isEmpty()) {
                imports.add("com.azure.core.http.rest.RestProxy");
            }

            for (Constructor constructor : getConstructors()) {
                constructor.addImportsTo(imports, includeImplementationImports);
            }
        }

        if (includeBuilderImports || includeImplementationImports) {
            imports.add("com.azure.core.http.HttpPipelineBuilder");
            imports.add("com.azure.core.http.policy.CookiePolicy");
            imports.add("com.azure.core.http.policy.RetryPolicy");
            imports.add("com.azure.core.http.policy.UserAgentPolicy");
        }

        if (includeBuilderImports) {
            imports.add(String.format("%1$s.%2$s", getPackageName(), getClassName()));
        }
    }

    public static class Builder {
        private String packageName;
        private String className;
        private List<ServiceClientProperty> properties;
        private List<Constructor> constructors;
        private List<ClientMethod> clientMethods;
        private ClientMethodParameter httpPipelineParameter;
        private ClientMethodParameter objectSerializerAdapter;
        private String builderName;

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
         * Sets the serializer adapter parameter.
         * @param serializerAdapterParameter the serializer adapter
         * @return the Builder itself
         */
        public Builder objectSerializerAdapter(ClientMethodParameter serializerAdapterParameter) {
            this.objectSerializerAdapter = serializerAdapterParameter;
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

        public Builder builderName(String builderName) {
            this.builderName = builderName;
            return this;
        }

        public LowLevelClient build() {
            return new LowLevelClient(packageName,
                    className,
                    properties,
                    constructors,
                    clientMethods,
                    httpPipelineParameter,
                    objectSerializerAdapter,
                    builderName);
        }
    }
}
