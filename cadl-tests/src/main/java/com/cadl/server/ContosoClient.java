// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.cadl.server;

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

/** Initializes a new instance of the synchronous ContosoClient type. */
@ServiceClient(builder = ContosoClientBuilder.class)
public final class ContosoClient {
    @Generated private final ContosoAsyncClient client;

    /**
     * Initializes an instance of ContosoClient class.
     *
     * @param client the async client.
     */
    @Generated
    ContosoClient(ContosoAsyncClient client) {
        this.client = client;
    }

    /**
     * The get operation.
     *
     * @param code The code parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getWithResponse(int code, RequestOptions requestOptions) {
        return this.client.getWithResponse(code, requestOptions).block();
    }

    /**
     * The get operation.
     *
     * @param code The code parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void get(int code) {
        // Generated convenience method for getWithResponse
        RequestOptions requestOptions = new RequestOptions();
        getWithResponse(code, requestOptions).getValue();
    }
}
