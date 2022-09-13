// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.authentication.oauth2;

import com.authentication.oauth2.implementation.OAuth2sImpl;
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
import reactor.core.publisher.Mono;

/** Initializes a new instance of the asynchronous AuthenticationOAuth2Client type. */
@ServiceClient(builder = AuthenticationOAuth2ClientBuilder.class, isAsync = true)
public final class AuthenticationOAuth2AsyncClient {
    @Generated private final OAuth2sImpl serviceClient;

    /**
     * Initializes an instance of AuthenticationOAuth2AsyncClient class.
     *
     * @param serviceClient the service client implementation.
     */
    @Generated
    AuthenticationOAuth2AsyncClient(OAuth2sImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Check whether client is authenticated.
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
    public Mono<Response<Void>> validWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.validWithResponseAsync(requestOptions);
    }

    /**
     * Check whether client is authenticated. Will return an invalid bearer error.
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
    public Mono<Response<Void>> invalidWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.invalidWithResponseAsync(requestOptions);
    }

    /**
     * Check whether client is authenticated.
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
    public Mono<Void> valid() {
        // Generated convenience method for validWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return validWithResponse(requestOptions).then();
    }

    /**
     * Check whether client is authenticated. Will return an invalid bearer error.
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
    public Mono<Void> invalid() {
        // Generated convenience method for invalidWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return invalidWithResponse(requestOptions).then();
    }
}
