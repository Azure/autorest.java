// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.cadl.response;

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
import com.cadl.response.implementation.ResponseOpsImpl;
import reactor.core.publisher.Mono;

/** Initializes a new instance of the asynchronous ResponseClient type. */
@ServiceClient(builder = ResponseClientBuilder.class, isAsync = true)
public final class ResponseAsyncClient {
    @Generated private final ResponseOpsImpl serviceClient;

    /**
     * Initializes an instance of ResponseAsyncClient class.
     *
     * @param serviceClient the service client implementation.
     */
    @Generated
    ResponseAsyncClient(ResponseOpsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * The getBinary operation.
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
    public Mono<Response<BinaryData>> getBinaryWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getBinaryWithResponseAsync(requestOptions);
    }

    /*
     * Generated convenience method for getBinaryWithResponse
     */
    /**
     * The getBinary operation.
     *
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    public Mono<BinaryData> getBinary() {
        RequestOptions requestOptions = new RequestOptions();
        return getBinaryWithResponse(requestOptions).map(Response::getValue);
    }
}
