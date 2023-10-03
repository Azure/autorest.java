// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.property.optional.implementation;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.util.serializer.JacksonAdapter;
import com.azure.core.util.serializer.SerializerAdapter;

/** Initializes a new instance of the OptionalClient type. */
public final class OptionalClientImpl {
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

    /** The StringOperationsImpl object to access its operations. */
    private final StringOperationsImpl stringOperations;

    /**
     * Gets the StringOperationsImpl object to access its operations.
     *
     * @return the StringOperationsImpl object.
     */
    public StringOperationsImpl getStringOperations() {
        return this.stringOperations;
    }

    /** The BytesImpl object to access its operations. */
    private final BytesImpl bytes;

    /**
     * Gets the BytesImpl object to access its operations.
     *
     * @return the BytesImpl object.
     */
    public BytesImpl getBytes() {
        return this.bytes;
    }

    /** The DatetimeOperationsImpl object to access its operations. */
    private final DatetimeOperationsImpl datetimeOperations;

    /**
     * Gets the DatetimeOperationsImpl object to access its operations.
     *
     * @return the DatetimeOperationsImpl object.
     */
    public DatetimeOperationsImpl getDatetimeOperations() {
        return this.datetimeOperations;
    }

    /** The DurationOperationsImpl object to access its operations. */
    private final DurationOperationsImpl durationOperations;

    /**
     * Gets the DurationOperationsImpl object to access its operations.
     *
     * @return the DurationOperationsImpl object.
     */
    public DurationOperationsImpl getDurationOperations() {
        return this.durationOperations;
    }

    /** The CollectionsBytesImpl object to access its operations. */
    private final CollectionsBytesImpl collectionsBytes;

    /**
     * Gets the CollectionsBytesImpl object to access its operations.
     *
     * @return the CollectionsBytesImpl object.
     */
    public CollectionsBytesImpl getCollectionsBytes() {
        return this.collectionsBytes;
    }

    /** The CollectionsModelsImpl object to access its operations. */
    private final CollectionsModelsImpl collectionsModels;

    /**
     * Gets the CollectionsModelsImpl object to access its operations.
     *
     * @return the CollectionsModelsImpl object.
     */
    public CollectionsModelsImpl getCollectionsModels() {
        return this.collectionsModels;
    }

    /** The RequiredAndOptionalsImpl object to access its operations. */
    private final RequiredAndOptionalsImpl requiredAndOptionals;

    /**
     * Gets the RequiredAndOptionalsImpl object to access its operations.
     *
     * @return the RequiredAndOptionalsImpl object.
     */
    public RequiredAndOptionalsImpl getRequiredAndOptionals() {
        return this.requiredAndOptionals;
    }

    /** Initializes an instance of OptionalClient client. */
    public OptionalClientImpl() {
        this(
                new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy()).build(),
                JacksonAdapter.createDefaultSerializerAdapter());
    }

    /**
     * Initializes an instance of OptionalClient client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    public OptionalClientImpl(HttpPipeline httpPipeline) {
        this(httpPipeline, JacksonAdapter.createDefaultSerializerAdapter());
    }

    /**
     * Initializes an instance of OptionalClient client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param serializerAdapter The serializer to serialize an object into a string.
     */
    public OptionalClientImpl(HttpPipeline httpPipeline, SerializerAdapter serializerAdapter) {
        this.httpPipeline = httpPipeline;
        this.serializerAdapter = serializerAdapter;
        this.stringOperations = new StringOperationsImpl(this);
        this.bytes = new BytesImpl(this);
        this.datetimeOperations = new DatetimeOperationsImpl(this);
        this.durationOperations = new DurationOperationsImpl(this);
        this.collectionsBytes = new CollectionsBytesImpl(this);
        this.collectionsModels = new CollectionsModelsImpl(this);
        this.requiredAndOptionals = new RequiredAndOptionalsImpl(this);
    }
}
