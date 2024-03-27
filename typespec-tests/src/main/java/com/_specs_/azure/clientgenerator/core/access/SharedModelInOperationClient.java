// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com._specs_.azure.clientgenerator.core.access;

import com._specs_.azure.clientgenerator.core.access.implementation.SharedModelInOperationsImpl;
import com._specs_.azure.clientgenerator.core.access.models.SharedModel;
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

/**
 * Initializes a new instance of the synchronous AccessClient type.
 */
@ServiceClient(builder = AccessClientBuilder.class)
public final class SharedModelInOperationClient {
    @Generated
    private final SharedModelInOperationsImpl serviceClient;

    /**
     * Initializes an instance of SharedModelInOperationClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
    SharedModelInOperationClient(SharedModelInOperationsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * The publicMethod operation.
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     * name: String (Required)
     * }
     * }</pre>
     * 
     * @param name A sequence of textual characters.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return used by both public and internal operation along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> publicMethodWithResponse(String name, RequestOptions requestOptions) {
        return this.serviceClient.publicMethodWithResponse(name, requestOptions);
    }

    /**
     * The internal operation.
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     * name: String (Required)
     * }
     * }</pre>
     * 
     * @param name A sequence of textual characters.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return used by both public and internal operation along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    Response<BinaryData> internalWithResponse(String name, RequestOptions requestOptions) {
        return this.serviceClient.internalWithResponse(name, requestOptions);
    }

    /**
     * The publicMethod operation.
     * 
     * @param name A sequence of textual characters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return used by both public and internal operation.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SharedModel publicMethod(String name) {
        // Generated convenience method for publicMethodWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return publicMethodWithResponse(name, requestOptions).getValue().toObject(SharedModel.class);
    }

    /**
     * The internal operation.
     * 
     * @param name A sequence of textual characters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return used by both public and internal operation.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    SharedModel internal(String name) {
        // Generated convenience method for internalWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return internalWithResponse(name, requestOptions).getValue().toObject(SharedModel.class);
    }
}
