// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.hello;

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
import com.hello.implementation.HelloClientImpl;
import reactor.core.publisher.Mono;

/** Initializes a new instance of the asynchronous HelloClient type. */
@ServiceClient(builder = HelloClientBuilder.class, isAsync = true)
public final class HelloAsyncClient {
    @Generated private final HelloClientImpl serviceClient;

    /**
     * Initializes an instance of HelloAsyncClient class.
     *
     * @param serviceClient the service client implementation.
     */
    @Generated
    HelloAsyncClient(HelloClientImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * The world operation.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * String
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
    public Mono<Response<BinaryData>> worldWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.worldWithResponseAsync(requestOptions);
    }

    /**
     * The world operation.
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
    public Mono<String> world() {
        // Generated convenience method for worldWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return worldWithResponse(requestOptions)
                .flatMap(FluxUtil::toMono)
                .map(protocolMethodData -> protocolMethodData.toObject(String.class));
    }
}
