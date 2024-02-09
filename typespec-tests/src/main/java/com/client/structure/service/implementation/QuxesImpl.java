// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.client.structure.service.implementation;

import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.Post;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.exception.ClientAuthenticationException;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.exception.ResourceModifiedException;
import com.azure.core.exception.ResourceNotFoundException;

import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;

import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;

import com.client.structure.service.ServiceServiceVersion;

import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in Quxes.
 */
public final class QuxesImpl {
    /**
     * The proxy service used to perform REST calls.
     */
    private final QuxesService service;

    /**
     * The service client containing this operation class.
     */
    private final ServiceClientClientImpl client;

    /**
     * Initializes an instance of QuxesImpl.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    QuxesImpl(ServiceClientClientImpl client) {
        this.service = RestProxy.create(QuxesService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * Gets Service version.
     * 
     * @return the serviceVersion value.
     */
    public ServiceServiceVersion getServiceVersion() {
        return client.getServiceVersion();
    }

    /**
     * The interface defining all the services for ServiceClientClientQuxes to be used by the proxy service to perform
     * REST calls.
     */
    @Host("{endpoint}/client/structure/{client}")
    @ServiceInterface(name = "ServiceClientClientQ")
    public interface QuxesService {
        @Post("/eight")
        @ExpectedResponses({ 204 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> eight(@HostParam("endpoint") String endpoint, @HostParam("client") String client,
            @HeaderParam("accept") String accept, RequestOptions requestOptions, Context context);

        @Post("/eight")
        @ExpectedResponses({ 204 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<Void> eightSync(@HostParam("endpoint") String endpoint, @HostParam("client") String client,
            @HeaderParam("accept") String accept, RequestOptions requestOptions, Context context);
    }

    /**
     * The eight operation.
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> eightWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.eight(this.client.getEndpoint(), this.client.getClient(), accept,
            requestOptions, context));
    }

    /**
     * The eight operation.
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> eightWithResponse(RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.eightSync(this.client.getEndpoint(), this.client.getClient(), accept, requestOptions,
            Context.NONE);
    }
}
