// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.encode.bytes;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.exception.ClientAuthenticationException;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.exception.ResourceModifiedException;
import com.azure.core.exception.ResourceNotFoundException;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.FluxUtil;
import com.encode.bytes.implementation.QueriesImpl;
import java.util.List;
import reactor.core.publisher.Mono;

/**
 * Initializes a new instance of the asynchronous BytesClient type.
 */
@ServiceClient(builder = BytesClientBuilder.class, isAsync = true)
public final class QueryAsyncClient {
    @Generated
    private final QueriesImpl serviceClient;

    /**
     * Initializes an instance of QueryAsyncClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
    QueryAsyncClient(QueriesImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * The defaultMethod operation.
     * 
     * @param value Represent a byte array.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> defaultMethodWithResponse(byte[] value, RequestOptions requestOptions) {
        return this.serviceClient.defaultMethodWithResponseAsync(value, requestOptions);
    }

    /**
     * The base64 operation.
     * 
     * @param value Represent a byte array.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> base64WithResponse(byte[] value, RequestOptions requestOptions) {
        return this.serviceClient.base64WithResponseAsync(value, requestOptions);
    }

    /**
     * The base64Url operation.
     * 
     * @param value Represent a byte array.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> base64UrlWithResponse(byte[] value, RequestOptions requestOptions) {
        return this.serviceClient.base64UrlWithResponseAsync(value, requestOptions);
    }

    /**
     * The base64UrlArray operation.
     * 
     * @param value Array of Value.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> base64UrlArrayWithResponse(List<byte[]> value, RequestOptions requestOptions) {
        return this.serviceClient.base64UrlArrayWithResponseAsync(value, requestOptions);
    }

    /**
     * The defaultMethod operation.
     * 
     * @param value Represent a byte array.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> defaultMethod(byte[] value) {
        // Generated convenience method for defaultMethodWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return defaultMethodWithResponse(value, requestOptions).flatMap(FluxUtil::toMono);
    }

    /**
     * The base64 operation.
     * 
     * @param value Represent a byte array.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> base64(byte[] value) {
        // Generated convenience method for base64WithResponse
        RequestOptions requestOptions = new RequestOptions();
        return base64WithResponse(value, requestOptions).flatMap(FluxUtil::toMono);
    }

    /**
     * The base64Url operation.
     * 
     * @param value Represent a byte array.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> base64Url(byte[] value) {
        // Generated convenience method for base64UrlWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return base64UrlWithResponse(value, requestOptions).flatMap(FluxUtil::toMono);
    }

    /**
     * The base64UrlArray operation.
     * 
     * @param value Array of Value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> base64UrlArray(List<byte[]> value) {
        // Generated convenience method for base64UrlArrayWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return base64UrlArrayWithResponse(value, requestOptions).flatMap(FluxUtil::toMono);
    }
}
