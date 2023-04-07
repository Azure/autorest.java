// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.cadl.literalservice;

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
import com.cadl.literalservice.implementation.LiteralServiceClientImpl;
import com.cadl.literalservice.models.Model;
import com.cadl.literalservice.models.OptionalLiteralParam;

/** Initializes a new instance of the synchronous LiteralServiceClient type. */
@ServiceClient(builder = LiteralServiceClientBuilder.class)
public final class LiteralServiceClient {
    @Generated private final LiteralServiceClientImpl serviceClient;

    /**
     * Initializes an instance of LiteralServiceClient class.
     *
     * @param serviceClient the service client implementation.
     */
    @Generated
    LiteralServiceClient(LiteralServiceClientImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * The put operation.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>optionalLiteralParam</td><td>String</td><td>No</td><td>The optionalLiteralParam parameter. Allowed values: "optionalLiteralParam".</td></tr>
     * </table>
     *
     * You can add these to a request with {@link RequestOptions#addQueryParam}
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     literal: String (Required)
     *     optionalLiteral: String(optionalLiteral) (Optional)
     * }
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     literal: String (Required)
     *     optionalLiteral: String(optionalLiteral) (Optional)
     * }
     * }</pre>
     *
     * @param model The model parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> putWithResponse(BinaryData model, RequestOptions requestOptions) {
        return this.serviceClient.putWithResponse(model, requestOptions);
    }

    /**
     * The put operation.
     *
     * @param model The model parameter.
     * @param optionalLiteralParam The optionalLiteralParam parameter.
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
    public Model put(Model model, OptionalLiteralParam optionalLiteralParam) {
        // Generated convenience method for putWithResponse
        RequestOptions requestOptions = new RequestOptions();
        if (optionalLiteralParam != null) {
            requestOptions.addQueryParam("optionalLiteralParam", optionalLiteralParam.toString(), false);
        }
        return putWithResponse(BinaryData.fromObject(model), requestOptions).getValue().toObject(Model.class);
    }

    /**
     * The put operation.
     *
     * @param model The model parameter.
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
    public Model put(Model model) {
        // Generated convenience method for putWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return putWithResponse(BinaryData.fromObject(model), requestOptions).getValue().toObject(Model.class);
    }
}
