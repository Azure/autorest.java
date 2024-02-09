// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.
package com.server.versions.notversioned;

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
import com.server.versions.notversioned.implementation.NotVersionedClientImpl;

/**
 * Initializes a new instance of the synchronous NotVersionedClient type.
 */
@ServiceClient(builder = NotVersionedClientBuilder.class)
public final class NotVersionedClient {

    @Generated
    private final NotVersionedClientImpl serviceClient;

    /**
     * Initializes an instance of NotVersionedClient class.
     *
     * @param serviceClient the service client implementation.
     */
    @Generated
    NotVersionedClient(NotVersionedClientImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * The withoutApiVersion operation.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> withoutApiVersionWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.withoutApiVersionWithResponse(requestOptions);
    }

    /**
     * The withQueryApiVersion operation.
     *
     * @param apiVersion A sequence of textual characters.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> withQueryApiVersionWithResponse(String apiVersion, RequestOptions requestOptions) {
        return this.serviceClient.withQueryApiVersionWithResponse(apiVersion, requestOptions);
    }

    /**
     * The withPathApiVersion operation.
     *
     * @param apiVersion A sequence of textual characters.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> withPathApiVersionWithResponse(String apiVersion, RequestOptions requestOptions) {
        return this.serviceClient.withPathApiVersionWithResponse(apiVersion, requestOptions);
    }

    /**
     * The withoutApiVersion operation.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void withoutApiVersion() {
        // Generated convenience method for withoutApiVersionWithResponse
        RequestOptions requestOptions = new RequestOptions();
        withoutApiVersionWithResponse(requestOptions).getValue();
    }

    /**
     * The withQueryApiVersion operation.
     *
     * @param apiVersion A sequence of textual characters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void withQueryApiVersion(String apiVersion) {
        // Generated convenience method for withQueryApiVersionWithResponse
        RequestOptions requestOptions = new RequestOptions();
        withQueryApiVersionWithResponse(apiVersion, requestOptions).getValue();
    }

    /**
     * The withPathApiVersion operation.
     *
     * @param apiVersion A sequence of textual characters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void withPathApiVersion(String apiVersion) {
        // Generated convenience method for withPathApiVersionWithResponse
        RequestOptions requestOptions = new RequestOptions();
        withPathApiVersionWithResponse(apiVersion, requestOptions).getValue();
    }
}
