// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com._specs_.azure.core.lro.rpc.legacy.implementation;

import com._specs_.azure.core.lro.rpc.legacy.LegacyServiceVersion;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;

import com.azure.core.util.serializer.JacksonAdapter;
import com.azure.core.util.serializer.SerializerAdapter;

/**
 * Initializes a new instance of the LegacyClient type.
 */
public final class LegacyClientImpl {
    /**
     * Service version.
     */
    private final LegacyServiceVersion serviceVersion;

    /**
     * Gets Service version.
     * 
     * @return the serviceVersion value.
     */
    public LegacyServiceVersion getServiceVersion() {
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
     * The CreateResourcePollViaOperationLocationsImpl object to access its operations.
     */
    private final CreateResourcePollViaOperationLocationsImpl createResourcePollViaOperationLocations;

    /**
     * Gets the CreateResourcePollViaOperationLocationsImpl object to access its operations.
     * 
     * @return the CreateResourcePollViaOperationLocationsImpl object.
     */
    public CreateResourcePollViaOperationLocationsImpl getCreateResourcePollViaOperationLocations() {
        return this.createResourcePollViaOperationLocations;
    }

    /**
     * Initializes an instance of LegacyClient client.
     * 
     * @param serviceVersion Service version.
     */
    public LegacyClientImpl(LegacyServiceVersion serviceVersion) {
        this(new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy()).build(),
            JacksonAdapter.createDefaultSerializerAdapter(), serviceVersion);
    }

    /**
     * Initializes an instance of LegacyClient client.
     * 
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param serviceVersion Service version.
     */
    public LegacyClientImpl(HttpPipeline httpPipeline, LegacyServiceVersion serviceVersion) {
        this(httpPipeline, JacksonAdapter.createDefaultSerializerAdapter(), serviceVersion);
    }

    /**
     * Initializes an instance of LegacyClient client.
     * 
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param serializerAdapter The serializer to serialize an object into a string.
     * @param serviceVersion Service version.
     */
    public LegacyClientImpl(HttpPipeline httpPipeline, SerializerAdapter serializerAdapter,
        LegacyServiceVersion serviceVersion) {
        this.httpPipeline = httpPipeline;
        this.serializerAdapter = serializerAdapter;
        this.serviceVersion = serviceVersion;
        this.createResourcePollViaOperationLocations = new CreateResourcePollViaOperationLocationsImpl(this);
    }
}
