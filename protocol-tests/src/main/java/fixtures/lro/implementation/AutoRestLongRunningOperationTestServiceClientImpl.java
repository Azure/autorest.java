// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.lro.implementation;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.util.serializer.JacksonAdapter;
import com.azure.core.util.serializer.SerializerAdapter;

/**
 * Initializes a new instance of the AutoRestLongRunningOperationTestServiceClient type.
 */
public final class AutoRestLongRunningOperationTestServiceClientImpl {
    /**
     * server parameter.
     */
    private final String host;

    /**
     * Gets server parameter.
     * 
     * @return the host value.
     */
    public String getHost() {
        return this.host;
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
     * The LROsImpl object to access its operations.
     */
    private final LROsImpl lROs;

    /**
     * Gets the LROsImpl object to access its operations.
     * 
     * @return the LROsImpl object.
     */
    public LROsImpl getLROs() {
        return this.lROs;
    }

    /**
     * The LroRetrysImpl object to access its operations.
     */
    private final LroRetrysImpl lroRetrys;

    /**
     * Gets the LroRetrysImpl object to access its operations.
     * 
     * @return the LroRetrysImpl object.
     */
    public LroRetrysImpl getLroRetrys() {
        return this.lroRetrys;
    }

    /**
     * The LrosaDsImpl object to access its operations.
     */
    private final LrosaDsImpl lrosaDs;

    /**
     * Gets the LrosaDsImpl object to access its operations.
     * 
     * @return the LrosaDsImpl object.
     */
    public LrosaDsImpl getLrosaDs() {
        return this.lrosaDs;
    }

    /**
     * The LrosCustomHeadersImpl object to access its operations.
     */
    private final LrosCustomHeadersImpl lrosCustomHeaders;

    /**
     * Gets the LrosCustomHeadersImpl object to access its operations.
     * 
     * @return the LrosCustomHeadersImpl object.
     */
    public LrosCustomHeadersImpl getLrosCustomHeaders() {
        return this.lrosCustomHeaders;
    }

    /**
     * Initializes an instance of AutoRestLongRunningOperationTestServiceClient client.
     * 
     * @param host server parameter.
     */
    public AutoRestLongRunningOperationTestServiceClientImpl(String host) {
        this(new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy()).build(),
            JacksonAdapter.createDefaultSerializerAdapter(), host);
    }

    /**
     * Initializes an instance of AutoRestLongRunningOperationTestServiceClient client.
     * 
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param host server parameter.
     */
    public AutoRestLongRunningOperationTestServiceClientImpl(HttpPipeline httpPipeline, String host) {
        this(httpPipeline, JacksonAdapter.createDefaultSerializerAdapter(), host);
    }

    /**
     * Initializes an instance of AutoRestLongRunningOperationTestServiceClient client.
     * 
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param serializerAdapter The serializer to serialize an object into a string.
     * @param host server parameter.
     */
    public AutoRestLongRunningOperationTestServiceClientImpl(HttpPipeline httpPipeline,
        SerializerAdapter serializerAdapter, String host) {
        this.httpPipeline = httpPipeline;
        this.serializerAdapter = serializerAdapter;
        this.host = host;
        this.lROs = new LROsImpl(this);
        this.lroRetrys = new LroRetrysImpl(this);
        this.lrosaDs = new LrosaDsImpl(this);
        this.lrosCustomHeaders = new LrosCustomHeadersImpl(this);
    }
}
