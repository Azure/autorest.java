// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.
package com.cadl.multicontenttypes;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import com.cadl.multicontenttypes.implementation.MultiContentTypesClientImpl;
import reactor.core.publisher.Mono;

/**
 * Initializes a new instance of the asynchronous MultiContentTypesClient type.
 */
@ServiceClient(builder = MultiContentTypesClientBuilder.class, isAsync = true)
public final class MultiContentTypesAsyncClient {

    @Generated
    private final MultiContentTypesClientImpl serviceClient;

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
     * multiple data types map to multiple content types.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * BinaryData
     * }</pre>
     *
     * @param contentType The contentType parameter. Allowed values: "text/plain", "application/json",
     * "application/octet-stream", "image/jpeg", "image/png".
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
    public Mono<Response<Void>> uploadWithOverloadWithResponse(String contentType, BinaryData data,
        RequestOptions requestOptions) {
        // Convenience API is not generated, as operation 'uploadWithOverload' is multiple content-type
        return this.serviceClient.uploadWithOverloadWithResponseAsync(contentType, data, requestOptions);
    }
}
