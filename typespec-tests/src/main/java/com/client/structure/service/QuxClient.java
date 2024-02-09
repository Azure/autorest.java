// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.client.structure.service;

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

import com.client.structure.service.implementation.QuxesImpl;

/**
 * Initializes a new instance of the synchronous ServiceClientClient type.
 */
@ServiceClient(builder = ServiceClientClientBuilder.class)
public final class QuxClient {
    @Generated
    private final QuxesImpl serviceClient;

    /**
     * Initializes an instance of QuxClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
    QuxClient(QuxesImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * The eight operation.
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
    public Response<Void> eightWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.eightWithResponse(requestOptions);
    }

    /**
     * The eight operation.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void eight() {
        // Generated convenience method for eightWithResponse
        RequestOptions requestOptions = new RequestOptions();
        eightWithResponse(requestOptions).getValue();
    }
}
