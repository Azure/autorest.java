// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.model.clientmodel;

import java.util.Set;

public class AsyncSyncClient {

    private final String className;
    private final String packageName;

    private final MethodGroupClient methodGroupClient;

    private final ServiceClient serviceClient;

    // There is also reference from Client to ClientBuilder via "@ServiceClient(builder = ClientBuilder.class)"
    // clientBuilder can be null, if builder is disabled via "disable-client-builder"
    private ClientBuilder clientBuilder;

    private AsyncSyncClient(String packageName, String className, MethodGroupClient methodGroupClient, ServiceClient serviceClient) {
        this.packageName = packageName;
        this.className = className;
        this.methodGroupClient = methodGroupClient;
        this.serviceClient = serviceClient;
    }

    /**
     * Get the package name.
     * @return the package name.
     */
    public String getPackageName() {
        return packageName;
    }

    /**
     * Get the class name.
     * @return the class name.
     */
    public String getClassName() {
        return className;
    }

    /**
     * Get the method group client.
     * @return the method group client.
     */
    public MethodGroupClient getMethodGroupClient() {
        return methodGroupClient;
    }

    /**
     * Get the service client.
     * @return the service cleint.
     */
    public ServiceClient getServiceClient() {
        return serviceClient;
    }

    public void addImportsTo(Set<String> imports, boolean includeImplementationImports) {
        imports.add(String.format("%1$s.%2$s", getPackageName(), getClassName()));
    }

    public ClientBuilder getClientBuilder() {
        return clientBuilder;
    }

    public void setClientBuilder(ClientBuilder clientBuilder) {
        this.clientBuilder = clientBuilder;
    }

    public static class Builder {

        private String className;
        private String packageName;

        private MethodGroupClient methodGroupClient;

        private ServiceClient serviceClient;

        public Builder className(String className) {
            this.className = className;
            return this;
        }

        public Builder packageName(String packageName) {
            this.packageName = packageName;
            return this;
        }

        public Builder methodGroupClient(MethodGroupClient methodGroupClient) {
            this.methodGroupClient = methodGroupClient;
            return this;
        }

        public Builder serviceClient(ServiceClient serviceClient) {
            this.serviceClient = serviceClient;
            return this;
        }

        public AsyncSyncClient build() {
            return new AsyncSyncClient(packageName, className, methodGroupClient, serviceClient);
        }
    }
}
