// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.client.structure.service.implementation;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.util.logging.ClientLogger;
import com.azure.core.util.serializer.JacksonAdapter;
import com.azure.core.util.serializer.SerializerAdapter;

/**
 * Initializes a new instance of the TwoOperationGroupClient type.
 */
public final class TwoOperationGroupClientImpl {
    /**
     * Need to be set as 'http://localhost:3000' in client.
     */
    private final String endpoint;

    /**
     * Gets Need to be set as 'http://localhost:3000' in client.
     * 
     * @return the endpoint value.
     */
    public String getEndpoint() {
        return this.endpoint;
    }

    /**
     * Need to be set as 'default', 'multi-client', 'renamed-operation', 'two-operation-group' in client.
     */
    private final String client;

    /**
     * Gets Need to be set as 'default', 'multi-client', 'renamed-operation', 'two-operation-group' in client.
     * 
     * @return the client value.
     */
    public String getClient() {
        return this.client;
    }

    /**
     * The HTTP pipeline to send requests through.
     */
    private final HttpPipeline httpPipeline;

    /**
     * Gets The HTTP pipeline to send requests through.
     * 
     * @return the httpPipeline value.
     */
    public HttpPipeline getHttpPipeline() {
        return this.httpPipeline;
    }

    /**
     * The serializer to serialize an object into a string.
     */
    private final SerializerAdapter serializerAdapter;

    /**
     * Gets The serializer to serialize an object into a string.
     * 
     * @return the serializerAdapter value.
     */
    public SerializerAdapter getSerializerAdapter() {
        return this.serializerAdapter;
    }

    /**
     * The Group1sImpl object to access its operations.
     */
    private final Group1sImpl group1s;

    /**
     * Gets the Group1sImpl object to access its operations.
     * 
     * @return the Group1sImpl object.
     */
    public Group1sImpl getGroup1s() {
        return this.group1s;
    }

    /**
     * The Group2sImpl object to access its operations.
     */
    private final Group2sImpl group2s;

    /**
     * Gets the Group2sImpl object to access its operations.
     * 
     * @return the Group2sImpl object.
     */
    public Group2sImpl getGroup2s() {
        return this.group2s;
    }

    /**
     * Initializes an instance of TwoOperationGroupClient client.
     * 
     * @param endpoint Need to be set as 'http://localhost:3000' in client.
     * @param client Need to be set as 'default', 'multi-client', 'renamed-operation', 'two-operation-group' in client.
     */
    public TwoOperationGroupClientImpl(String endpoint, String client) {
        this(new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy()).build(), JacksonAdapter.createDefaultSerializerAdapter(), endpoint, client);
    }

    /**
     * Initializes an instance of TwoOperationGroupClient client.
     * 
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param endpoint Need to be set as 'http://localhost:3000' in client.
     * @param client Need to be set as 'default', 'multi-client', 'renamed-operation', 'two-operation-group' in client.
     */
    public TwoOperationGroupClientImpl(HttpPipeline httpPipeline, String endpoint, String client) {
        this(httpPipeline, JacksonAdapter.createDefaultSerializerAdapter(), endpoint, client);
    }

    /**
     * Initializes an instance of TwoOperationGroupClient client.
     * 
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param serializerAdapter The serializer to serialize an object into a string.
     * @param endpoint Need to be set as 'http://localhost:3000' in client.
     * @param client Need to be set as 'default', 'multi-client', 'renamed-operation', 'two-operation-group' in client.
     */
    public TwoOperationGroupClientImpl(HttpPipeline httpPipeline, SerializerAdapter serializerAdapter, String endpoint, String client) {
        this.httpPipeline = httpPipeline;
        this.serializerAdapter = serializerAdapter;
        this.endpoint = endpoint;
        this.client = client;
        this.group1s = new Group1sImpl(this);
        this.group2s = new Group2sImpl(this);
    }
}
