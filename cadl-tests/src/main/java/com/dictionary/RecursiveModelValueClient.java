// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.dictionary;

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
import com.dictionary.models.InnerModel;
import java.util.Map;

/** Initializes a new instance of the synchronous DictionaryClient type. */
@ServiceClient(builder = RecursiveModelValueClientBuilder.class)
public final class RecursiveModelValueClient {
    @Generated private final RecursiveModelValueAsyncClient client;

    /**
     * Initializes an instance of RecursiveModelValueClient class.
     *
     * @param client the async client.
     */
    @Generated
    RecursiveModelValueClient(RecursiveModelValueAsyncClient client) {
        this.client = client;
    }

    /**
     * The get operation.
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
    public Response<Void> getWithResponse(RequestOptions requestOptions) {
        return this.client.getWithResponse(requestOptions).block();
    }

    /**
     * The put operation.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     String (Required): {
     *         property: String (Required)
     *         children (Optional): {
     *             String (Optional): (recursive schema, see String above)
     *         }
     *     }
     * }
     * }</pre>
     *
     * @param body The body parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putWithResponse(BinaryData body, RequestOptions requestOptions) {
        return this.client.putWithResponse(body, requestOptions).block();
    }

    /**
     * The get operation.
     *
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void get() {
        // Generated convenience method for getWithResponse
        RequestOptions requestOptions = new RequestOptions();
        getWithResponse(requestOptions).getValue();
    }

    /**
     * The put operation.
     *
     * @param body The body parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void put(Map<String, InnerModel> body) {
        // Generated convenience method for putWithResponse
        RequestOptions requestOptions = new RequestOptions();
        putWithResponse(BinaryData.fromObject(body), requestOptions).getValue();
    }
}
