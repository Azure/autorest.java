// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

package com.azure.autorest.model.clientmodel;

public class ProtocolExample {
    private ClientMethod clientMethod;

    private AsyncSyncClient client;

    private String builderName;

    private String filename;

    private ProxyMethodExample proxyMethodExample;

    private String hostName;

    public ProtocolExample(
            ClientMethod clientMethod,
            AsyncSyncClient client,
            String builderName,
            String filename,
            ProxyMethodExample proxyMethodExample,
            String hostName) {
        this.clientMethod = clientMethod;
        this.client = client;
        this.builderName = builderName;
        this.filename = filename;
        this.proxyMethodExample = proxyMethodExample;
        this.hostName = hostName;
    }

    public ClientMethod getClientMethod() {
        return clientMethod;
    }

    public AsyncSyncClient getClient() {
        return client;
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

    public String getHostName() {
        return hostName;
    }
}
