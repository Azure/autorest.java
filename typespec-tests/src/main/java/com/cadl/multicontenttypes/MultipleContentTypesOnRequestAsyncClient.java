// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

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
import com.cadl.multicontenttypes.implementation.MultipleContentTypesOnRequestsImpl;
import com.cadl.multicontenttypes.models.Resource;
import reactor.core.publisher.Mono;

/**
 * Initializes a new instance of the asynchronous MultiContentTypesClient type.
 */
@ServiceClient(builder = MultiContentTypesClientBuilder.class, isAsync = true)
public final class MultipleContentTypesOnRequestAsyncClient {
    @Generated
    private final MultipleContentTypesOnRequestsImpl serviceClient;

    /**
     * Initializes an instance of MultipleContentTypesOnRequestAsyncClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
    MultipleContentTypesOnRequestAsyncClient(MultipleContentTypesOnRequestsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * one data type maps to multiple content types.
     * <p><strong>Request Body Schema</strong></p>
     * <pre>{@code
     * BinaryData
     * }</pre>
     * 
     * @param contentType The contentType parameter. Allowed values: "application/octet-stream", "image/jpeg",
     * "image/png".
     * @param data Represent a byte array.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> uploadBytesWithSingleBodyTypeForMultiContentTypesWithResponse(String contentType,
        BinaryData data, RequestOptions requestOptions) {
        // Convenience API is not generated, as operation 'uploadBytesWithSingleBodyTypeForMultiContentTypes' is multiple content-type
        return this.serviceClient.uploadBytesWithSingleBodyTypeForMultiContentTypesWithResponseAsync(contentType, data,
            requestOptions);
    }

    /**
     * multiple data types map to multiple content types using shared route.
     * <p><strong>Request Body Schema</strong></p>
     * <pre>{@code
     * BinaryData
     * }</pre>
     * 
     * @param contentType The contentType parameter. Allowed values: "application/octet-stream", "image/jpeg",
     * "image/png".
     * @param data Represent a byte array.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> uploadBytesWithMultiBodyTypesForMultiContentTypesWithResponse(String contentType,
        BinaryData data, RequestOptions requestOptions) {
        // Convenience API is not generated, as operation 'uploadBytesWithMultiBodyTypesForMultiContentTypes' is multiple content-type
        return this.serviceClient.uploadBytesWithMultiBodyTypesForMultiContentTypesWithResponseAsync(contentType, data,
            requestOptions);
    }

    /**
     * multiple data types map to multiple content types using shared route.
     * <p><strong>Request Body Schema</strong></p>
     * <pre>{@code
     * {
     * id: String (Required)
     * name: String (Required)
     * }
     * }</pre>
     * 
     * @param data The data parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> uploadJsonWithMultiBodyTypesForMultiContentTypesWithResponse(BinaryData data,
        RequestOptions requestOptions) {
        return this.serviceClient.uploadJsonWithMultiBodyTypesForMultiContentTypesWithResponseAsync(data,
            requestOptions);
    }

    /**
     * multiple data types map to multiple content types using shared route.
     * <p><strong>Request Body Schema</strong></p>
     * <pre>{@code
     * BinaryData
     * }</pre>
     * 
     * @param contentType The contentType parameter. Allowed values: "application/json", "application/octet-stream",
     * "image/jpeg", "image/png".
     * @param data The data parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> uploadJsonOrBytesWithMultiBodyTypesForMultiContentTypesWithResponse(String contentType,
        BinaryData data, RequestOptions requestOptions) {
        // Convenience API is not generated, as operation 'uploadJsonOrBytesWithMultiBodyTypesForMultiContentTypes' is multiple content-type
        return this.serviceClient.uploadJsonOrBytesWithMultiBodyTypesForMultiContentTypesWithResponseAsync(contentType,
            data, requestOptions);
    }

    /**
     * multiple data types map to multiple content types using shared route.
     * 
     * @param data The data parameter.
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
    public Mono<Void> uploadJsonWithMultiBodyTypesForMultiContentTypes(Resource data) {
        // Generated convenience method for uploadJsonWithMultiBodyTypesForMultiContentTypesWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return uploadJsonWithMultiBodyTypesForMultiContentTypesWithResponse(BinaryData.fromObject(data), requestOptions)
            .flatMap(FluxUtil::toMono);
    }
}
