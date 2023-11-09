// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.server.path.multiple.implementation;

import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.PathParam;
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
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import com.azure.core.util.serializer.JacksonAdapter;
import com.azure.core.util.serializer.SerializerAdapter;
import com.server.path.multiple.MultipleServiceVersion;
import reactor.core.publisher.Mono;

/**
 * Initializes a new instance of the MultipleClient type.
 */
public final class MultipleClientImpl {
    /**
     * The proxy service used to perform REST calls.
     */
    private final MultipleClientService service;

    /**
     * Pass in http://localhost:3000 for endpoint.
     */
    private final String endpoint;

    /**
     * Gets Pass in http://localhost:3000 for endpoint.
     * 
     * @return the endpoint value.
     */
    public String getEndpoint() {
        return this.endpoint;
    }

    /**
     * Service version.
     */
    private final MultipleServiceVersion serviceVersion;

    /**
     * Gets Service version.
     * 
     * @return the serviceVersion value.
     */
    public MultipleServiceVersion getServiceVersion() {
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
     * Initializes an instance of MultipleClient client.
     * 
     * @param endpoint Pass in http://localhost:3000 for endpoint.
     * @param serviceVersion Service version.
     */
    public MultipleClientImpl(String endpoint, MultipleServiceVersion serviceVersion) {
        this(new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy()).build(),
            JacksonAdapter.createDefaultSerializerAdapter(), endpoint, serviceVersion);
    }

    /**
     * Initializes an instance of MultipleClient client.
     * 
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param endpoint Pass in http://localhost:3000 for endpoint.
     * @param serviceVersion Service version.
     */
    public MultipleClientImpl(HttpPipeline httpPipeline, String endpoint, MultipleServiceVersion serviceVersion) {
        this(httpPipeline, JacksonAdapter.createDefaultSerializerAdapter(), endpoint, serviceVersion);
    }

    /**
     * Initializes an instance of MultipleClient client.
     * 
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param serializerAdapter The serializer to serialize an object into a string.
     * @param endpoint Pass in http://localhost:3000 for endpoint.
     * @param serviceVersion Service version.
     */
    public MultipleClientImpl(HttpPipeline httpPipeline, SerializerAdapter serializerAdapter, String endpoint,
        MultipleServiceVersion serviceVersion) {
        this.httpPipeline = httpPipeline;
        this.serializerAdapter = serializerAdapter;
        this.endpoint = endpoint;
        this.serviceVersion = serviceVersion;
        this.service = RestProxy.create(MultipleClientService.class, this.httpPipeline, this.getSerializerAdapter());
    }

    /**
     * The interface defining all the services for MultipleClient to be used by the proxy service to perform REST
     * calls.
     */
    @Host("{endpoint}/server/path/multiple/{apiVersion}")
    @ServiceInterface(name = "MultipleClient")
    public interface MultipleClientService {
        @Get("/")
        @ExpectedResponses({
            204
        })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = {
            401
        })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = {
            404
        })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = {
            409
        })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> noOperationParams(@HostParam("endpoint") String endpoint,
            @HostParam("apiVersion") String apiVersion, @HeaderParam("accept") String accept,
            RequestOptions requestOptions, Context context);

        @Get("/")
        @ExpectedResponses({
            204
        })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = {
            401
        })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = {
            404
        })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = {
            409
        })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<Void> noOperationParamsSync(@HostParam("endpoint") String endpoint,
            @HostParam("apiVersion") String apiVersion, @HeaderParam("accept") String accept,
            RequestOptions requestOptions, Context context);

        @Get("/{keyword}")
        @ExpectedResponses({
            204
        })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = {
            401
        })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = {
            404
        })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = {
            409
        })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> withOperationPathParam(@HostParam("endpoint") String endpoint,
            @HostParam("apiVersion") String apiVersion, @PathParam("keyword") String keyword,
            @HeaderParam("accept") String accept, RequestOptions requestOptions, Context context);

        @Get("/{keyword}")
        @ExpectedResponses({
            204
        })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = {
            401
        })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = {
            404
        })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = {
            409
        })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<Void> withOperationPathParamSync(@HostParam("endpoint") String endpoint,
            @HostParam("apiVersion") String apiVersion, @PathParam("keyword") String keyword,
            @HeaderParam("accept") String accept, RequestOptions requestOptions, Context context);
    }

    /**
     * The noOperationParams operation.
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> noOperationParamsWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.noOperationParams(this.getEndpoint(),
            this.getServiceVersion().getVersion(), accept, requestOptions, context));
    }

    /**
     * The noOperationParams operation.
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> noOperationParamsWithResponse(RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.noOperationParamsSync(this.getEndpoint(), this.getServiceVersion().getVersion(), accept,
            requestOptions, Context.NONE);
    }

    /**
     * The withOperationPathParam operation.
     * 
     * @param keyword A sequence of textual characters.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> withOperationPathParamWithResponseAsync(String keyword, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.withOperationPathParam(this.getEndpoint(),
            this.getServiceVersion().getVersion(), keyword, accept, requestOptions, context));
    }

    /**
     * The withOperationPathParam operation.
     * 
     * @param keyword A sequence of textual characters.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> withOperationPathParamWithResponse(String keyword, RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.withOperationPathParamSync(this.getEndpoint(), this.getServiceVersion().getVersion(), keyword,
            accept, requestOptions, Context.NONE);
    }
}
