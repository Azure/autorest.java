// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.cadl.flatten;

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
import com.cadl.flatten.models.User;
import java.util.HashMap;
import java.util.Map;

/** Initializes a new instance of the synchronous FlattenClient type. */
@ServiceClient(builder = FlattenClientBuilder.class)
public final class FlattenClient {
    @Generated private final FlattenAsyncClient client;

    /**
     * Initializes an instance of FlattenClient class.
     *
     * @param client the async client.
     */
    @Generated
    FlattenClient(FlattenAsyncClient client) {
        this.client = client;
    }

    /**
     * The send operation.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     user (Optional): {
     *         user: String (Required)
     *     }
     *     input: String (Required)
     * }
     * }</pre>
     *
     * @param id The id parameter.
     * @param request The request parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> sendWithResponse(String id, BinaryData request, RequestOptions requestOptions) {
        return this.client.sendWithResponse(id, request, requestOptions).block();
    }

    /**
     * The send operation.
     *
     * @param id The id parameter.
     * @param input The input parameter.
     * @param user The user parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void send(String id, String input, User user) {
        // Generated convenience method for sendWithResponse
        RequestOptions requestOptions = new RequestOptions();
        Map<String, Object> requestObj = new HashMap<>();
        requestObj.put("user", user);
        requestObj.put("input", input);
        BinaryData request = BinaryData.fromObject(requestObj);
        sendWithResponse(id, request, requestOptions).getValue();
    }

    /**
     * The send operation.
     *
     * @param id The id parameter.
     * @param input The input parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void send(String id, String input) {
        // Generated convenience method for sendWithResponse
        RequestOptions requestOptions = new RequestOptions();
        Map<String, Object> requestObj = new HashMap<>();
        requestObj.put("input", input);
        BinaryData request = BinaryData.fromObject(requestObj);
        sendWithResponse(id, request, requestOptions).getValue();
    }
}
