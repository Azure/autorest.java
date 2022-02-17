// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.model.clientmodel;

import com.azure.autorest.extension.base.plugin.JavaSettings;

import java.util.List;
import java.util.Objects;

public class TestContext {

    private final ServiceClient serviceClient;
    private final List<AsyncSyncClient> syncClients;

    private final ProtocolExample testCase;

    public TestContext(ServiceClient serviceClient, List<AsyncSyncClient> syncClients) {
        this.serviceClient = Objects.requireNonNull(serviceClient);
        this.syncClients = Objects.requireNonNull(syncClients);
        this.testCase = null;
    }

    /**
     * Appends an example as test case to the test context.
     *
     * @param testContext test context
     * @param testCase an example as test case
     */
    public TestContext(TestContext testContext, ProtocolExample testCase) {
        this.serviceClient = Objects.requireNonNull(testContext.getServiceClient());
        this.syncClients = Objects.requireNonNull(testContext.getSyncClients());
        this.testCase = testCase;
    }

    public ServiceClient getServiceClient() {
        return serviceClient;
    }

    public List<AsyncSyncClient> getSyncClients() {
        return syncClients;
    }

    public ProtocolExample getTestCase() {
        return testCase;
    }

    public String getPackageName() {
        return JavaSettings.getInstance().getPackage("generated");
    }

    public String getTestBaseClassName() {
        return serviceClient.getInterfaceName() + "TestBase";
    }
}
