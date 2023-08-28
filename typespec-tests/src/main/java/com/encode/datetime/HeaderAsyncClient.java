// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.encode.datetime;

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
import com.encode.datetime.implementation.HeadersImpl;
import java.time.OffsetDateTime;
import java.util.List;
import reactor.core.publisher.Mono;

/** Initializes a new instance of the asynchronous DatetimeClient type. */
@ServiceClient(builder = DatetimeClientBuilder.class, isAsync = true)
public final class HeaderAsyncClient {
    @Generated private final HeadersImpl serviceClient;

    /**
     * Initializes an instance of HeaderAsyncClient class.
     *
     * @param serviceClient the service client implementation.
     */
    @Generated
    HeaderAsyncClient(HeadersImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * The defaultMethod operation.
     *
     * @param value An instant in coordinated universal time (UTC)".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> defaultMethodWithResponse(OffsetDateTime value, RequestOptions requestOptions) {
        return this.serviceClient.defaultMethodWithResponseAsync(value, requestOptions);
    }

    /**
     * The rfc3339 operation.
     *
     * @param value An instant in coordinated universal time (UTC)".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> rfc3339WithResponse(OffsetDateTime value, RequestOptions requestOptions) {
        return this.serviceClient.rfc3339WithResponseAsync(value, requestOptions);
    }

    /**
     * The rfc7231 operation.
     *
     * @param value An instant in coordinated universal time (UTC)".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> rfc7231WithResponse(OffsetDateTime value, RequestOptions requestOptions) {
        return this.serviceClient.rfc7231WithResponseAsync(value, requestOptions);
    }

    /**
     * The unixTimestamp operation.
     *
     * @param value An instant in coordinated universal time (UTC)".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> unixTimestampWithResponse(OffsetDateTime value, RequestOptions requestOptions) {
        return this.serviceClient.unixTimestampWithResponseAsync(value, requestOptions);
    }

    /**
     * The unixTimestampArray operation.
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
    public Mono<Response<Void>> unixTimestampArrayWithResponse(
            List<OffsetDateTime> value, RequestOptions requestOptions) {
        return this.serviceClient.unixTimestampArrayWithResponseAsync(value, requestOptions);
    }

    /**
     * The defaultMethod operation.
     *
     * @param value An instant in coordinated universal time (UTC)".
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
    public Mono<Void> defaultMethod(OffsetDateTime value) {
        // Generated convenience method for defaultMethodWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return defaultMethodWithResponse(value, requestOptions).flatMap(FluxUtil::toMono);
    }

    /**
     * The rfc3339 operation.
     *
     * @param value An instant in coordinated universal time (UTC)".
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
    public Mono<Void> rfc3339(OffsetDateTime value) {
        // Generated convenience method for rfc3339WithResponse
        RequestOptions requestOptions = new RequestOptions();
        return rfc3339WithResponse(value, requestOptions).flatMap(FluxUtil::toMono);
    }

    /**
     * The rfc7231 operation.
     *
     * @param value An instant in coordinated universal time (UTC)".
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
    public Mono<Void> rfc7231(OffsetDateTime value) {
        // Generated convenience method for rfc7231WithResponse
        RequestOptions requestOptions = new RequestOptions();
        return rfc7231WithResponse(value, requestOptions).flatMap(FluxUtil::toMono);
    }

    /**
     * The unixTimestamp operation.
     *
     * @param value An instant in coordinated universal time (UTC)".
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
    public Mono<Void> unixTimestamp(OffsetDateTime value) {
        // Generated convenience method for unixTimestampWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return unixTimestampWithResponse(value, requestOptions).flatMap(FluxUtil::toMono);
    }

    /**
     * The unixTimestampArray operation.
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
    public Mono<Void> unixTimestampArray(List<OffsetDateTime> value) {
        // Generated convenience method for unixTimestampArrayWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return unixTimestampArrayWithResponse(value, requestOptions).flatMap(FluxUtil::toMono);
    }
}
