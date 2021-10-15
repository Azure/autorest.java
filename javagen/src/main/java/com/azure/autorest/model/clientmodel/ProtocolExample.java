// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

package com.azure.autorest.model.clientmodel;

public class ProtocolExample {

    private final ClientMethod clientMethod;

    private final AsyncSyncClient client;

    private final ServiceClient serviceClient;

    private final String builderName;

    private final String filename;

    private final ProxyMethodExample proxyMethodExample;

    public ProtocolExample(
            ClientMethod clientMethod,
            AsyncSyncClient client,
            ServiceClient serviceClient,
            String builderName,
            String filename,
            ProxyMethodExample proxyMethodExample) {
        this.clientMethod = clientMethod;
        this.client = client;
        this.serviceClient = serviceClient;
        this.builderName = builderName;
        this.filename = filename;
        this.proxyMethodExample = proxyMethodExample;
    }

    public ClientMethod getClientMethod() {
        return clientMethod;
    }

    public AsyncSyncClient getClient() {
        return client;
    }

    public ServiceClient getServiceClient() {
        return serviceClient;
    }

    public String getBuilderName() {
        return builderName;
    }

    public String getFilename() {
        return filename;
    }

    public ProxyMethodExample getProxyMethodExample() {
        return proxyMethodExample;
    }
}
