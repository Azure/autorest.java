// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.
package com.client.structure.service;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.FluxUtil;
import com.client.structure.service.implementation.Group1sImpl;
import reactor.core.publisher.Mono;

/**
 * Initializes a new instance of the asynchronous TwoOperationGroupClient type.
 */
@ServiceClient(builder = TwoOperationGroupClientBuilder.class, isAsync = true)
public final class Group1AsyncClient {

    @Generated
    private final Group1sImpl serviceClient;

    /**
     * Initializes an instance of Group1AsyncClient class.
     *
     * @param serviceClient the service client implementation.
     */
    @Generated
    Group1AsyncClient(Group1sImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * The one operation.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> oneWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.oneWithResponseAsync(requestOptions);
    }

    /**
     * The three operation.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> threeWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.threeWithResponseAsync(requestOptions);
    }

    /**
     * The four operation.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> fourWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.fourWithResponseAsync(requestOptions);
    }

    /**
     * The one operation.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> one() {
        // Generated convenience method for oneWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return oneWithResponse(requestOptions).flatMap(FluxUtil::toMono);
    }

    /**
     * The three operation.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> three() {
        // Generated convenience method for threeWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return threeWithResponse(requestOptions).flatMap(FluxUtil::toMono);
    }

    /**
     * The four operation.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> four() {
        // Generated convenience method for fourWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return fourWithResponse(requestOptions).flatMap(FluxUtil::toMono);
    }
}
