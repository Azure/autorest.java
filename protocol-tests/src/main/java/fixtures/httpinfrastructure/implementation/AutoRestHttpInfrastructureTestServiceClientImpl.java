// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.httpinfrastructure.implementation;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.util.serializer.JacksonAdapter;
import com.azure.core.util.serializer.SerializerAdapter;

/** Initializes a new instance of the AutoRestHttpInfrastructureTestServiceClient type. */
public final class AutoRestHttpInfrastructureTestServiceClientImpl {
    /** server parameter. */
    private final String host;

    /**
     * Gets server parameter.
     *
     * @return the host value.
     */
    public String getHost() {
        return this.host;
    }

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

    /** The HttpFailuresImpl object to access its operations. */
    private final HttpFailuresImpl httpFailures;

    /**
     * Gets the HttpFailuresImpl object to access its operations.
     *
     * @return the HttpFailuresImpl object.
     */
    public HttpFailuresImpl getHttpFailures() {
        return this.httpFailures;
    }

    /** The HttpSuccessImpl object to access its operations. */
    private final HttpSuccessImpl httpSuccess;

    /**
     * Gets the HttpSuccessImpl object to access its operations.
     *
     * @return the HttpSuccessImpl object.
     */
    public HttpSuccessImpl getHttpSuccess() {
        return this.httpSuccess;
    }

    /** The HttpRedirectsImpl object to access its operations. */
    private final HttpRedirectsImpl httpRedirects;

    /**
     * Gets the HttpRedirectsImpl object to access its operations.
     *
     * @return the HttpRedirectsImpl object.
     */
    public HttpRedirectsImpl getHttpRedirects() {
        return this.httpRedirects;
    }

    /** The HttpClientFailuresImpl object to access its operations. */
    private final HttpClientFailuresImpl httpClientFailures;

    /**
     * Gets the HttpClientFailuresImpl object to access its operations.
     *
     * @return the HttpClientFailuresImpl object.
     */
    public HttpClientFailuresImpl getHttpClientFailures() {
        return this.httpClientFailures;
    }

    /** The HttpServerFailuresImpl object to access its operations. */
    private final HttpServerFailuresImpl httpServerFailures;

    /**
     * Gets the HttpServerFailuresImpl object to access its operations.
     *
     * @return the HttpServerFailuresImpl object.
     */
    public HttpServerFailuresImpl getHttpServerFailures() {
        return this.httpServerFailures;
    }

    /** The HttpRetriesImpl object to access its operations. */
    private final HttpRetriesImpl httpRetries;

    /**
     * Gets the HttpRetriesImpl object to access its operations.
     *
     * @return the HttpRetriesImpl object.
     */
    public HttpRetriesImpl getHttpRetries() {
        return this.httpRetries;
    }

    /** The MultipleResponsesImpl object to access its operations. */
    private final MultipleResponsesImpl multipleResponses;

    /**
     * Gets the MultipleResponsesImpl object to access its operations.
     *
     * @return the MultipleResponsesImpl object.
     */
    public MultipleResponsesImpl getMultipleResponses() {
        return this.multipleResponses;
    }

    /**
     * Initializes an instance of AutoRestHttpInfrastructureTestServiceClient client.
     *
     * @param host server parameter.
     */
    public AutoRestHttpInfrastructureTestServiceClientImpl(String host) {
        this(
                new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy()).build(),
                JacksonAdapter.createDefaultSerializerAdapter(),
                host);
    }

    /**
     * Initializes an instance of AutoRestHttpInfrastructureTestServiceClient client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param host server parameter.
     */
    public AutoRestHttpInfrastructureTestServiceClientImpl(HttpPipeline httpPipeline, String host) {
        this(httpPipeline, JacksonAdapter.createDefaultSerializerAdapter(), host);
    }

    /**
     * Initializes an instance of AutoRestHttpInfrastructureTestServiceClient client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param serializerAdapter The serializer to serialize an object into a string.
     * @param host server parameter.
     */
    public AutoRestHttpInfrastructureTestServiceClientImpl(
            HttpPipeline httpPipeline, SerializerAdapter serializerAdapter, String host) {
        this.httpPipeline = httpPipeline;
        this.serializerAdapter = serializerAdapter;
        this.host = host;
        this.httpFailures = new HttpFailuresImpl(this);
        this.httpSuccess = new HttpSuccessImpl(this);
        this.httpRedirects = new HttpRedirectsImpl(this);
        this.httpClientFailures = new HttpClientFailuresImpl(this);
        this.httpServerFailures = new HttpServerFailuresImpl(this);
        this.httpRetries = new HttpRetriesImpl(this);
        this.multipleResponses = new MultipleResponsesImpl(this);
    }
}
