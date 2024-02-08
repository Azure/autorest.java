// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.
package com.cadl.builtin.implementation;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.util.serializer.JacksonAdapter;
import com.azure.core.util.serializer.SerializerAdapter;

/**
 * Initializes a new instance of the BuiltinClient type.
 */
public final class BuiltinClientImpl {

    /**
     * Server parameter.
     */
    private final String endpoint;

    /**
     * Gets Server parameter.
     *
     * @return the endpoint value.
     */
    public String getEndpoint() {
        return this.endpoint;
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
     * The BuiltinOpsImpl object to access its operations.
     */
    private final BuiltinOpsImpl builtinOps;

    /**
     * Gets the BuiltinOpsImpl object to access its operations.
     *
     * @return the BuiltinOpsImpl object.
     */
    public BuiltinOpsImpl getBuiltinOps() {
        return this.builtinOps;
    }

    /**
     * Initializes an instance of BuiltinClient client.
     *
     * @param endpoint Server parameter.
     */
    public BuiltinClientImpl(String endpoint) {
        this(new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy()).build(),
            JacksonAdapter.createDefaultSerializerAdapter(), endpoint);
    }

    /**
     * Initializes an instance of BuiltinClient client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param endpoint Server parameter.
     */
    public BuiltinClientImpl(HttpPipeline httpPipeline, String endpoint) {
        this(httpPipeline, JacksonAdapter.createDefaultSerializerAdapter(), endpoint);
    }

    /**
     * Initializes an instance of BuiltinClient client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param serializerAdapter The serializer to serialize an object into a string.
     * @param endpoint Server parameter.
     */
    public BuiltinClientImpl(HttpPipeline httpPipeline, SerializerAdapter serializerAdapter, String endpoint) {
        this.httpPipeline = httpPipeline;
        this.serializerAdapter = serializerAdapter;
        this.endpoint = endpoint;
        this.builtinOps = new BuiltinOpsImpl(this);
    }
}
