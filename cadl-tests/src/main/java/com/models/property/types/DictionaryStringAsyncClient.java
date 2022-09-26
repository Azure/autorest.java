// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.models.property.types;

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
import com.models.property.types.implementation.DictionaryStringsImpl;
import com.models.property.types.models.DictionaryStringProperty;
import reactor.core.publisher.Mono;

/** Initializes a new instance of the asynchronous ModelsPropertyTypesClient type. */
@ServiceClient(builder = DictionaryStringClientBuilder.class, isAsync = true)
public final class DictionaryStringAsyncClient {
    @Generated private final DictionaryStringsImpl serviceClient;

    /**
     * Initializes an instance of DictionaryStringAsyncClient class.
     *
     * @param serviceClient the service client implementation.
     */
    @Generated
    DictionaryStringAsyncClient(DictionaryStringsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * The get operation.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     property (Required): {
     *         String: String (Required)
     *     }
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return model with dictionary string properties along with {@link Response} on successful completion of {@link
     *     Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getWithResponseAsync(requestOptions);
    }

    /**
     * The put operation.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     property (Required): {
     *         String: String (Required)
     *     }
     * }
     * }</pre>
     *
     * @param body Model with dictionary string properties.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putWithResponse(BinaryData body, RequestOptions requestOptions) {
        return this.serviceClient.putWithResponseAsync(body, requestOptions);
    }

    /**
     * The get operation.
     *
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return model with dictionary string properties on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DictionaryStringProperty> get() {
        // Generated convenience method for getWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return getWithResponse(requestOptions)
                .map(Response::getValue)
                .map(protocolMethodData -> protocolMethodData.toObject(DictionaryStringProperty.class));
    }

    /**
     * The put operation.
     *
     * @param body Model with dictionary string properties.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> put(DictionaryStringProperty body) {
        // Generated convenience method for putWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return putWithResponse(BinaryData.fromObject(body), requestOptions).then();
    }
}
