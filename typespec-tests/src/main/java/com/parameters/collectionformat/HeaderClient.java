// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.parameters.collectionformat;

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

import com.parameters.collectionformat.implementation.HeadersImpl;

import java.util.List;

/**
 * Initializes a new instance of the synchronous CollectionFormatClient type.
 */
@ServiceClient(builder = CollectionFormatClientBuilder.class)
public final class HeaderClient {
    @Generated
    private final HeadersImpl serviceClient;

    /**
     * Initializes an instance of HeaderClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
    HeaderClient(HeadersImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * The csv operation.
     * 
     * @param colors Possible values for colors are [blue,red,green].
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> csvWithResponse(List<String> colors, RequestOptions requestOptions) {
        return this.serviceClient.csvWithResponse(colors, requestOptions);
    }

    /**
     * The csv operation.
     * 
     * @param colors Possible values for colors are [blue,red,green].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void csv(List<String> colors) {
        // Generated convenience method for csvWithResponse
        RequestOptions requestOptions = new RequestOptions();
        csvWithResponse(colors, requestOptions).getValue();
    }
}
