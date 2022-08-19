// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.cadl.testserver.modelcollectionproperties.implementation;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.util.serializer.JacksonAdapter;
import com.azure.core.util.serializer.SerializerAdapter;

/** Initializes a new instance of the ModelCollectionPropertiesClient type. */
public final class ModelCollectionPropertiesClientImpl {
    /** The HTTP pipeline to send requests through. */
    private final HttpPipeline httpPipeline;

    /**
     * Gets The HTTP pipeline to send requests through.
     *
     * @return the httpPipeline value.
     */
    public HttpPipeline getHttpPipeline() {
        return this.httpPipeline;
    }

    /** The serializer to serialize an object into a string. */
    private final SerializerAdapter serializerAdapter;

    /**
     * Gets The serializer to serialize an object into a string.
     *
     * @return the serializerAdapter value.
     */
    public SerializerAdapter getSerializerAdapter() {
        return this.serializerAdapter;
    }

    /** The ModelCollectionPropertiesImpl object to access its operations. */
    private final ModelCollectionPropertiesImpl modelCollectionProperties;

    /**
     * Gets the ModelCollectionPropertiesImpl object to access its operations.
     *
     * @return the ModelCollectionPropertiesImpl object.
     */
    public ModelCollectionPropertiesImpl getModelCollectionProperties() {
        return this.modelCollectionProperties;
    }

    /** Initializes an instance of ModelCollectionPropertiesClient client. */
    public ModelCollectionPropertiesClientImpl() {
        this(
                new HttpPipelineBuilder()
                        .policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy())
                        .build(),
                JacksonAdapter.createDefaultSerializerAdapter());
    }

    /**
     * Initializes an instance of ModelCollectionPropertiesClient client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    public ModelCollectionPropertiesClientImpl(HttpPipeline httpPipeline) {
        this(httpPipeline, JacksonAdapter.createDefaultSerializerAdapter());
    }

    /**
     * Initializes an instance of ModelCollectionPropertiesClient client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param serializerAdapter The serializer to serialize an object into a string.
     */
    public ModelCollectionPropertiesClientImpl(HttpPipeline httpPipeline, SerializerAdapter serializerAdapter) {
        this.httpPipeline = httpPipeline;
        this.serializerAdapter = serializerAdapter;
        this.modelCollectionProperties = new ModelCollectionPropertiesImpl(this);
    }
}
