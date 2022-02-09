// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.model.clientmodel;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ClientBuilder {

    private final String className;
    private final ServiceClient serviceClient;

    // There is naturally ClientBuilder to Client reference, via "buildClient" method and via "@ServiceClientBuilder(serviceClients = {Client.class, AsyncClient.class})"
    // syncClients and asyncClients can be empty. In this case, ClientBuilder build serviceClient directly. Note this usually is only used for internal implementation, as this pattern does not match Java guidelines.
    private final List<AsyncSyncClient> syncClients;
    private final List<AsyncSyncClient> asyncClients;

    public ClientBuilder(String className, ServiceClient serviceClient) {
        this.className = className;
        this.serviceClient = serviceClient;
        this.syncClients = Collections.emptyList();
        this.asyncClients = Collections.emptyList();
    }

    public ClientBuilder(String className, ServiceClient serviceClient,
                         List<AsyncSyncClient> syncClients, List<AsyncSyncClient> asyncClients) {
        this.className = className;
        this.serviceClient = Objects.requireNonNull(serviceClient);
        this.syncClients = Objects.requireNonNull(syncClients);
        this.asyncClients = Objects.requireNonNull(asyncClients);
    }

    public String getClassName() {
        return className;
    }

    public ServiceClient getServiceClient() {
        return serviceClient;
    }

    public List<AsyncSyncClient> getSyncClients() {
        return syncClients;
    }

    public List<AsyncSyncClient> getAsyncClients() {
        return asyncClients;
    }

    public String getBuilderMethodNameForSyncClient(AsyncSyncClient syncClient) {
        boolean singleClient = asyncClients.size() == 1;
        return singleClient
                ? "buildClient"
                : ("build" + syncClient.getClassName());
    }

    public String getBuilderMethodNameForAsyncClient(AsyncSyncClient asyncClient) {
        boolean singleClient = asyncClients.size() == 1;
        return singleClient
                ? "buildAsyncClient"
                : ("build" + asyncClient.getClassName());
    }
}
