// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.cadl.multicontenttypes;

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
import com.azure.core.util.BinaryData;
import com.azure.core.util.FluxUtil;
import com.cadl.multicontenttypes.implementation.MultiContentTypesClientImpl;
import com.cadl.multicontenttypes.models.ContentType;
import reactor.core.publisher.Mono;

/** Initializes a new instance of the asynchronous MultiContentTypesClient type. */
@ServiceClient(builder = MultiContentTypesClientBuilder.class, isAsync = true)
public final class MultiContentTypesAsyncClient {
    @Generated private final MultiContentTypesClientImpl serviceClient;

    /**
     * Initializes an instance of MultiContentTypesAsyncClient class.
     *
     * @param serviceClient the service client implementation.
     */
    @Generated
    MultiContentTypesAsyncClient(MultiContentTypesClientImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * response is binary.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * BinaryData
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> downloadImageWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.downloadImageWithResponseAsync(requestOptions);
    }

    /**
     * request is binary.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * BinaryData
     * }</pre>
     *
     * @param data data.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> uploadImageWithResponse(BinaryData data, RequestOptions requestOptions) {
        return this.serviceClient.uploadImageWithResponseAsync(data, requestOptions);
    }

    /**
     * one data type maps to multiple content types.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * BinaryData
     * }</pre>
     *
     * @param contentType The contentType parameter. Allowed values: "application/octet-stream", "image/jpeg",
     *     "image/png".
     * @param data data.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> uploadBytesWithResponse(
            String contentType, BinaryData data, RequestOptions requestOptions) {
        return this.serviceClient.uploadBytesWithResponseAsync(contentType, data, requestOptions);
    }

    /**
     * response is binary.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> downloadImage() {
        // Generated convenience method for downloadImageWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return downloadImageWithResponse(requestOptions).flatMap(FluxUtil::toMono);
    }

    /**
     * request is binary.
     *
     * @param data data.
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
    public Mono<Void> uploadImage(BinaryData data) {
        // Generated convenience method for uploadImageWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return uploadImageWithResponse(data, requestOptions).flatMap(FluxUtil::toMono);
    }

    /**
     * one data type maps to multiple content types.
     *
     * @param contentType The contentType parameter.
     * @param data data.
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
    public Mono<Void> uploadBytes(ContentType contentType, BinaryData data) {
        // Generated convenience method for uploadBytesWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return uploadBytesWithResponse(contentType.toString(), data, requestOptions).flatMap(FluxUtil::toMono);
    }
}
