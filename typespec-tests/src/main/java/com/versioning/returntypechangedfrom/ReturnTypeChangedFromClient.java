// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.versioning.returntypechangedfrom;

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
import com.versioning.returntypechangedfrom.implementation.ReturnTypeChangedFromClientImpl;

/**
 * Initializes a new instance of the synchronous ReturnTypeChangedFromClient type.
 */
@ServiceClient(builder = ReturnTypeChangedFromClientBuilder.class)
public final class ReturnTypeChangedFromClient {
    @Generated
    private final ReturnTypeChangedFromClientImpl serviceClient;

    /**
     * Initializes an instance of ReturnTypeChangedFromClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
    ReturnTypeChangedFromClient(ReturnTypeChangedFromClientImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * The test operation.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>{@code
     * String
     * }</pre>
     * 
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>{@code
     * String
     * }</pre>
     * 
     * @param body The body parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return a sequence of textual characters along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> testWithResponse(BinaryData body, RequestOptions requestOptions) {
        return this.serviceClient.testWithResponse(body, requestOptions);
    }

    /**
     * The test operation.
     * 
     * @param body The body parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a sequence of textual characters.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public String test(String body) {
        // Generated convenience method for testWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return testWithResponse(BinaryData.fromObject(body), requestOptions).getValue().toObject(String.class);
    }
}
