/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.model.clientmodel;

public class AsyncSyncClient {

    private final String className;

    private final MethodGroupClient methodGroupClient;

    private final ServiceClient serviceClient;

    private AsyncSyncClient(String className, MethodGroupClient methodGroupClient, ServiceClient serviceClient) {
        this.className = className;
        this.methodGroupClient = methodGroupClient;
        this.serviceClient = serviceClient;
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

    public static class Builder {

        private String className;

        private MethodGroupClient methodGroupClient;

        private ServiceClient serviceClient;

        public Builder className(String className) {
            this.className = className;
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
            return new AsyncSyncClient(className, methodGroupClient, serviceClient);
        }
    }
}
