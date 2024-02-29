// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.parameters.spread;

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
import com.parameters.spread.implementation.ModelsImpl;
import com.parameters.spread.models.BodyParameter;
import reactor.core.publisher.Mono;

/**
 * Initializes a new instance of the asynchronous SpreadClient type.
 */
@ServiceClient(builder = SpreadClientBuilder.class, isAsync = true)
public final class ModelAsyncClient {
    @Generated
    private final ModelsImpl serviceClient;

    /**
     * Initializes an instance of ModelAsyncClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
    ModelAsyncClient(ModelsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * The spreadAsRequestBody operation.
     * <p><strong>Request Body Schema</strong></p>
     * <pre>{@code
     * {
     *     name: String (Required)
     * }
     * }</pre>
     * 
     * @param bodyParameter This is a simple model.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> spreadAsRequestBodyWithResponse(BinaryData bodyParameter,
        RequestOptions requestOptions) {
        return this.serviceClient.spreadAsRequestBodyWithResponseAsync(bodyParameter, requestOptions);
    }

    /**
     * The spreadAsRequestBody operation.
     * 
     * @param bodyParameter This is a simple model.
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
    public Mono<Void> spreadAsRequestBody(BodyParameter bodyParameter) {
        // Generated convenience method for spreadAsRequestBodyWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return spreadAsRequestBodyWithResponse(BinaryData.fromObject(bodyParameter), requestOptions)
            .flatMap(FluxUtil::toMono);
    }
}
