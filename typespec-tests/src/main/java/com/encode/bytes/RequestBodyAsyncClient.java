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
import com.azure.core.util.Base64Url;
import com.azure.core.util.BinaryData;
import com.azure.core.util.FluxUtil;
import com.encode.bytes.implementation.RequestBodiesImpl;
import reactor.core.publisher.Mono;

/**
 * Initializes a new instance of the asynchronous BytesClient type.
 */
@ServiceClient(builder = BytesClientBuilder.class, isAsync = true)
public final class RequestBodyAsyncClient {

    @Generated
    private final RequestBodiesImpl serviceClient;

    /**
     * Initializes an instance of RequestBodyAsyncClient class.
     *
     * @param serviceClient the service client implementation.
     */
    @Generated
    RequestBodyAsyncClient(RequestBodiesImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * The defaultMethod operation.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * byte[]
     * }</pre>
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
    public Mono<Response<Void>> defaultMethodWithResponse(BinaryData value, RequestOptions requestOptions) {
        return this.serviceClient.defaultMethodWithResponseAsync(value, requestOptions);
    }

    /**
     * The octetStream operation.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * BinaryData
     * }</pre>
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
    public Mono<Response<Void>> octetStreamWithResponse(BinaryData value, RequestOptions requestOptions) {
        return this.serviceClient.octetStreamWithResponseAsync(value, requestOptions);
    }

    /**
     * The customContentType operation.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * BinaryData
     * }</pre>
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
    public Mono<Response<Void>> customContentTypeWithResponse(BinaryData value, RequestOptions requestOptions) {
        return this.serviceClient.customContentTypeWithResponseAsync(value, requestOptions);
    }

    /**
     * The base64 operation.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * byte[]
     * }</pre>
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
    public Mono<Response<Void>> base64WithResponse(BinaryData value, RequestOptions requestOptions) {
        return this.serviceClient.base64WithResponseAsync(value, requestOptions);
    }

    /**
     * The base64url operation.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * Base64Url
     * }</pre>
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
    public Mono<Response<Void>> base64urlWithResponse(BinaryData value, RequestOptions requestOptions) {
        return this.serviceClient.base64urlWithResponseAsync(value, requestOptions);
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
        return defaultMethodWithResponse(BinaryData.fromObject(value), requestOptions).flatMap(FluxUtil::toMono);
    }

    /**
     * The octetStream operation.
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
    public Mono<Void> octetStream(BinaryData value) {
        // Generated convenience method for octetStreamWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return octetStreamWithResponse(value, requestOptions).flatMap(FluxUtil::toMono);
    }

    /**
     * The customContentType operation.
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
    public Mono<Void> customContentType(BinaryData value) {
        // Generated convenience method for customContentTypeWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return customContentTypeWithResponse(value, requestOptions).flatMap(FluxUtil::toMono);
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
        return base64WithResponse(BinaryData.fromObject(value), requestOptions).flatMap(FluxUtil::toMono);
    }

    /**
     * The base64url operation.
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
    public Mono<Void> base64url(byte[] value) {
        // Generated convenience method for base64urlWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return base64urlWithResponse(BinaryData.fromObject(Base64Url.encode(value)), requestOptions)
            .flatMap(FluxUtil::toMono);
    }
}
