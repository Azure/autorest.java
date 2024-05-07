// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.versioning.renamedfrom;

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
import com.versioning.renamedfrom.implementation.RenamedFromClientImpl;
import com.versioning.renamedfrom.models.NewModel;

/**
 * Initializes a new instance of the synchronous RenamedFromClient type.
 */
@ServiceClient(builder = RenamedFromClientBuilder.class)
public final class RenamedFromClient {
    @Generated
    private final RenamedFromClientImpl serviceClient;

    /**
     * Initializes an instance of RenamedFromClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
    RenamedFromClient(RenamedFromClientImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * The newOp operation.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     newProp: String (Required)
     *     enumProp: String(newEnumMember) (Required)
     *     unionProp: BinaryData (Required)
     * }
     * }</pre>
     * 
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     newProp: String (Required)
     *     enumProp: String(newEnumMember) (Required)
     *     unionProp: BinaryData (Required)
     * }
     * }</pre>
     * 
     * @param newQuery A sequence of textual characters.
     * @param body The body parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> newOpWithResponse(String newQuery, BinaryData body, RequestOptions requestOptions) {
        return this.serviceClient.newOpWithResponse(newQuery, body, requestOptions);
    }

    /**
     * The newOp operation.
     * 
     * @param newQuery A sequence of textual characters.
     * @param body The body parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public NewModel newOp(String newQuery, NewModel body) {
        // Generated convenience method for newOpWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return newOpWithResponse(newQuery, BinaryData.fromObject(body), requestOptions).getValue()
            .toObject(NewModel.class);
    }
}
