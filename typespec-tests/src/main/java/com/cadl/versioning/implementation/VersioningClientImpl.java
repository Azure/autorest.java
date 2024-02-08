// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.
package com.cadl.versioning.implementation;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.util.serializer.JacksonAdapter;
import com.azure.core.util.serializer.SerializerAdapter;
import com.cadl.versioning.VersioningServiceVersion;

/**
 * Initializes a new instance of the VersioningClient type.
 */
public final class VersioningClientImpl {

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
     * Service version.
     */
    private final VersioningServiceVersion serviceVersion;

    /**
     * Gets Service version.
     *
     * @return the serviceVersion value.
     */
    public VersioningServiceVersion getServiceVersion() {
        return this.serviceVersion;
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
     * The VersioningOpsImpl object to access its operations.
     */
    private final VersioningOpsImpl versioningOps;

    /**
     * Gets the VersioningOpsImpl object to access its operations.
     *
     * @return the VersioningOpsImpl object.
     */
    public VersioningOpsImpl getVersioningOps() {
        return this.versioningOps;
    }

    /**
     * Initializes an instance of VersioningClient client.
     *
     * @param endpoint Server parameter.
     * @param serviceVersion Service version.
     */
    public VersioningClientImpl(String endpoint, VersioningServiceVersion serviceVersion) {
        this(new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy()).build(),
            JacksonAdapter.createDefaultSerializerAdapter(), endpoint, serviceVersion);
    }

    /**
     * Initializes an instance of VersioningClient client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param endpoint Server parameter.
     * @param serviceVersion Service version.
     */
    public VersioningClientImpl(HttpPipeline httpPipeline, String endpoint, VersioningServiceVersion serviceVersion) {
        this(httpPipeline, JacksonAdapter.createDefaultSerializerAdapter(), endpoint, serviceVersion);
    }

    /**
     * Initializes an instance of VersioningClient client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param serializerAdapter The serializer to serialize an object into a string.
     * @param endpoint Server parameter.
     * @param serviceVersion Service version.
     */
    public VersioningClientImpl(HttpPipeline httpPipeline, SerializerAdapter serializerAdapter, String endpoint,
        VersioningServiceVersion serviceVersion) {
        this.httpPipeline = httpPipeline;
        this.serializerAdapter = serializerAdapter;
        this.endpoint = endpoint;
        this.serviceVersion = serviceVersion;
        this.versioningOps = new VersioningOpsImpl(this);
    }
}
