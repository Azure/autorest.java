// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.parameterizedendpoint;

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
import fixtures.parameterizedendpoint.implementation.ParmaterizedEndpointClientImpl;
import reactor.core.publisher.Mono;

/** Initializes a new instance of the asynchronous ParmaterizedEndpointClient type. */
@ServiceClient(builder = ParmaterizedEndpointClientBuilder.class, isAsync = true)
public final class ParmaterizedEndpointAsyncClient {
    @Generated private final ParmaterizedEndpointClientImpl serviceClient;

    /**
     * Initializes an instance of ParmaterizedEndpointAsyncClient class.
     *
     * @param serviceClient the service client implementation.
     */
    @Generated
    ParmaterizedEndpointAsyncClient(ParmaterizedEndpointClientImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Basic get to make sure base url formatting of 'endpoint' works.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getWithResponseAsync(requestOptions);
    }

    /**
     * Basic get to make sure base url formatting of 'endpoint' works.
     *
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get() {
        // Generated convenience method for getWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return getWithResponse(requestOptions).then();
    }
}
