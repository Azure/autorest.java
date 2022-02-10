// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.model.clientmodel;

import java.util.List;
import java.util.Objects;

public class TestContext {

    private final ServiceClient serviceClient;
    private final List<AsyncSyncClient> syncClients;

    public TestContext(ServiceClient serviceClient, List<AsyncSyncClient> syncClients) {
        this.serviceClient = Objects.requireNonNull(serviceClient);
        this.syncClients = Objects.requireNonNull(syncClients);
    }

    public ServiceClient getServiceClient() {
        return serviceClient;
    }

    public List<AsyncSyncClient> getSyncClients() {
        return syncClients;
    }
}
