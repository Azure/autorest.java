// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.constantandclientparam.implementation;

import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.Put;
import com.azure.core.annotation.QueryParam;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.exception.ClientAuthenticationException;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.exception.ResourceModifiedException;
import com.azure.core.exception.ResourceNotFoundException;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import com.azure.core.util.serializer.JacksonAdapter;
import com.azure.core.util.serializer.SerializerAdapter;
import reactor.core.publisher.Mono;

/** Initializes a new instance of the ConstantAndClientParameterServiceClient type. */
public final class ConstantAndClientParameterServiceClientImpl {
    /** The proxy service used to perform REST calls. */
    private final ConstantAndClientParameterServiceClientService service;

    /** Query parameter on the client that is required. */
    private final int queryRequiredClientParam;

    /**
     * Gets Query parameter on the client that is required.
     *
     * @return the queryRequiredClientParam value.
     */
    public int getQueryRequiredClientParam() {
        return this.queryRequiredClientParam;
    }

    /** Query parameter on the client that is required and have default value. */
    private final int queryRequiredDefaultValueClientParam;

    /**
     * Gets Query parameter on the client that is required and have default value.
     *
     * @return the queryRequiredDefaultValueClientParam value.
     */
    public int getQueryRequiredDefaultValueClientParam() {
        return this.queryRequiredDefaultValueClientParam;
    }

    /** Query parameter on the client that is not required. */
    private final int queryNonRequiredClientParam;

    /**
     * Gets Query parameter on the client that is not required.
     *
     * @return the queryNonRequiredClientParam value.
     */
    public int getQueryNonRequiredClientParam() {
        return this.queryNonRequiredClientParam;
    }

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

    /**
     * Initializes an instance of ConstantAndClientParameterServiceClient client.
     *
     * @param queryRequiredClientParam Query parameter on the client that is required.
     * @param queryRequiredDefaultValueClientParam Query parameter on the client that is required and have default
     *     value.
     * @param queryNonRequiredClientParam Query parameter on the client that is not required.
     * @param host server parameter.
     */
    public ConstantAndClientParameterServiceClientImpl(
            int queryRequiredClientParam,
            int queryRequiredDefaultValueClientParam,
            int queryNonRequiredClientParam,
            String host) {
        this(
                new HttpPipelineBuilder()
                        .policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy())
                        .build(),
                JacksonAdapter.createDefaultSerializerAdapter(),
                queryRequiredClientParam,
                queryRequiredDefaultValueClientParam,
                queryNonRequiredClientParam,
                host);
    }

    /**
     * Initializes an instance of ConstantAndClientParameterServiceClient client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param queryRequiredClientParam Query parameter on the client that is required.
     * @param queryRequiredDefaultValueClientParam Query parameter on the client that is required and have default
     *     value.
     * @param queryNonRequiredClientParam Query parameter on the client that is not required.
     * @param host server parameter.
     */
    public ConstantAndClientParameterServiceClientImpl(
            HttpPipeline httpPipeline,
            int queryRequiredClientParam,
            int queryRequiredDefaultValueClientParam,
            int queryNonRequiredClientParam,
            String host) {
        this(
                httpPipeline,
                JacksonAdapter.createDefaultSerializerAdapter(),
                queryRequiredClientParam,
                queryRequiredDefaultValueClientParam,
                queryNonRequiredClientParam,
                host);
    }

    /**
     * Initializes an instance of ConstantAndClientParameterServiceClient client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param serializerAdapter The serializer to serialize an object into a string.
     * @param queryRequiredClientParam Query parameter on the client that is required.
     * @param queryRequiredDefaultValueClientParam Query parameter on the client that is required and have default
     *     value.
     * @param queryNonRequiredClientParam Query parameter on the client that is not required.
     * @param host server parameter.
     */
    public ConstantAndClientParameterServiceClientImpl(
            HttpPipeline httpPipeline,
            SerializerAdapter serializerAdapter,
            int queryRequiredClientParam,
            int queryRequiredDefaultValueClientParam,
            int queryNonRequiredClientParam,
            String host) {
        this.httpPipeline = httpPipeline;
        this.serializerAdapter = serializerAdapter;
        this.queryRequiredClientParam = queryRequiredClientParam;
        this.queryRequiredDefaultValueClientParam = queryRequiredDefaultValueClientParam;
        this.queryNonRequiredClientParam = queryNonRequiredClientParam;
        this.host = host;
        this.service =
                RestProxy.create(
                        ConstantAndClientParameterServiceClientService.class,
                        this.httpPipeline,
                        this.getSerializerAdapter());
    }

    /**
     * The interface defining all the services for ConstantAndClientParameterServiceClient to be used by the proxy
     * service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "ConstantAndClientPar")
    private interface ConstantAndClientParameterServiceClientService {
        @Put("/constant/clientparam/path")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(
                value = ClientAuthenticationException.class,
                code = {401})
        @UnexpectedResponseExceptionType(
                value = ResourceNotFoundException.class,
                code = {404})
        @UnexpectedResponseExceptionType(
                value = ResourceModifiedException.class,
                code = {409})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> putClientConstants(
                @HostParam("$host") String host,
                @HeaderParam("header-required-constant") boolean headerRequiredConstant,
                @QueryParam("query-required-constant") int queryRequiredConstant,
                @QueryParam("query-non-required-constant") Integer queryNonRequiredConstant,
                @QueryParam("query-required-client-param") int queryRequiredClientParam,
                @QueryParam("query-required-default-value-client-param") int queryRequiredDefaultValueClientParam,
                @QueryParam("query-non-required-client-param") Integer queryNonRequiredClientParam,
                RequestOptions requestOptions,
                Context context);
    }

    /**
     * Pass constants from the client to this function. Will pass in constant path, query, and header parameters.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putClientConstantsWithResponseAsync(RequestOptions requestOptions) {
        final boolean headerRequiredConstant = true;
        final int queryRequiredConstant = 100;
        final Integer queryNonRequiredConstant = 100;
        return FluxUtil.withContext(
                context ->
                        service.putClientConstants(
                                this.getHost(),
                                headerRequiredConstant,
                                queryRequiredConstant,
                                queryNonRequiredConstant,
                                this.getQueryRequiredClientParam(),
                                this.getQueryRequiredDefaultValueClientParam(),
                                this.getQueryNonRequiredClientParam(),
                                requestOptions,
                                context));
    }

    /**
     * Pass constants from the client to this function. Will pass in constant path, query, and header parameters.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putClientConstantsWithResponse(RequestOptions requestOptions) {
        return putClientConstantsWithResponseAsync(requestOptions).block();
    }
}
