// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com._specs_.azure.clientgenerator.core.usage;

import com._specs_.azure.clientgenerator.core.usage.implementation.ModelInOperationsImpl;
import com._specs_.azure.clientgenerator.core.usage.models.InputModel;
import com._specs_.azure.clientgenerator.core.usage.models.OutputModel;
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
 * Initializes a new instance of the synchronous UsageClient type.
 */
@ServiceClient(builder = UsageClientBuilder.class)
public final class UsageClient {
    @Generated
    private final ModelInOperationsImpl serviceClient;

    /**
     * Initializes an instance of UsageClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
    UsageClient(ModelInOperationsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Expected body parameter:
     * ```json
     * {
     * "name": &lt;any string&gt;
     * }
     * ```.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     name: String (Required)
     * }
     * }</pre>
     * 
     * @param body Usage override to roundtrip.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> inputToInputOutputWithResponse(BinaryData body, RequestOptions requestOptions) {
        return this.serviceClient.inputToInputOutputWithResponse(body, requestOptions);
    }

    /**
     * Expected response body:
     * ```json
     * {
     * "name": &lt;any string&gt;
     * }
     * ```.
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     name: String (Required)
     * }
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return usage override to roundtrip along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> outputToInputOutputWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.outputToInputOutputWithResponse(requestOptions);
    }

    /**
     * Expected body parameter:
     * ```json
     * {
     * "name": &lt;any string&gt;
     * }
     * ```.
     * 
     * @param body Usage override to roundtrip.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void inputToInputOutput(InputModel body) {
        // Generated convenience method for inputToInputOutputWithResponse
        RequestOptions requestOptions = new RequestOptions();
        inputToInputOutputWithResponse(BinaryData.fromObject(body), requestOptions).getValue();
    }

    /**
     * Expected response body:
     * ```json
     * {
     * "name": &lt;any string&gt;
     * }
     * ```.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return usage override to roundtrip.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public OutputModel outputToInputOutput() {
        // Generated convenience method for outputToInputOutputWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return outputToInputOutputWithResponse(requestOptions).getValue().toObject(OutputModel.class);
    }
}
