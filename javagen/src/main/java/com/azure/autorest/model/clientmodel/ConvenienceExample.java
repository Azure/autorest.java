// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.model.clientmodel;

public class ConvenienceExample {
    private final ClientMethod clientMethod;

    private final ConvenienceMethod convenienceMethod;

    private final AsyncSyncClient syncClient;

    private final ClientBuilder clientBuilder;

    private final String filename;

    private final ProxyMethodExample proxyMethodExample;

    public ConvenienceExample(
            ClientMethod clientMethod,
            ConvenienceMethod convenienceMethod,
            AsyncSyncClient syncClient,
            ClientBuilder clientBuilder,
            String filename,
            ProxyMethodExample proxyMethodExample) {
        this.clientMethod = clientMethod;
        this.convenienceMethod = convenienceMethod;
        this.syncClient = syncClient;
        this.clientBuilder = clientBuilder;
        this.filename = filename;
        this.proxyMethodExample = proxyMethodExample;
    }

    public ClientMethod getClientMethod() {
        return clientMethod;
    }

    public AsyncSyncClient getSyncClient() {
        return syncClient;
    }

    public ClientBuilder getClientBuilder() {
        return clientBuilder;
    }

    public String getFilename() {
        return filename;
    }

    public ProxyMethodExample getProxyMethodExample() {
        return proxyMethodExample;
    }

    public ConvenienceMethod getConvenienceMethod() {
        return convenienceMethod;
    }
}
