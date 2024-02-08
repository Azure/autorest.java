// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.
package fixtures.llcresi.implementation;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.util.serializer.JacksonAdapter;
import com.azure.core.util.serializer.SerializerAdapter;
import fixtures.llcresi.DpgServiceVersion;

/**
 * Initializes a new instance of the DpgClient type.
 */
public final class DpgClientImpl {

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
     * Service version.
     */
    private final DpgServiceVersion serviceVersion;

    /**
     * Gets Service version.
     *
     * @return the serviceVersion value.
     */
    public DpgServiceVersion getServiceVersion() {
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
     * The ParamsImpl object to access its operations.
     */
    private final ParamsImpl params;

    /**
     * Gets the ParamsImpl object to access its operations.
     *
     * @return the ParamsImpl object.
     */
    public ParamsImpl getParams() {
        return this.params;
    }

    /**
     * Initializes an instance of DpgClient client.
     *
     * @param host server parameter.
     * @param serviceVersion Service version.
     */
    public DpgClientImpl(String host, DpgServiceVersion serviceVersion) {
        this(new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy()).build(),
            JacksonAdapter.createDefaultSerializerAdapter(), host, serviceVersion);
    }

    /**
     * Initializes an instance of DpgClient client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param host server parameter.
     * @param serviceVersion Service version.
     */
    public DpgClientImpl(HttpPipeline httpPipeline, String host, DpgServiceVersion serviceVersion) {
        this(httpPipeline, JacksonAdapter.createDefaultSerializerAdapter(), host, serviceVersion);
    }

    /**
     * Initializes an instance of DpgClient client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param serializerAdapter The serializer to serialize an object into a string.
     * @param host server parameter.
     * @param serviceVersion Service version.
     */
    public DpgClientImpl(HttpPipeline httpPipeline, SerializerAdapter serializerAdapter, String host,
        DpgServiceVersion serviceVersion) {
        this.httpPipeline = httpPipeline;
        this.serializerAdapter = serializerAdapter;
        this.host = host;
        this.serviceVersion = serviceVersion;
        this.params = new ParamsImpl(this);
    }
}
