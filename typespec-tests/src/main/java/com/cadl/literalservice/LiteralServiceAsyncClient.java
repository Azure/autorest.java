// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

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
import com.azure.core.util.FluxUtil;
import com.cadl.literalservice.implementation.LiteralOpsImpl;
import com.cadl.literalservice.models.Model;
import com.cadl.literalservice.models.OptionalLiteralParam;
import reactor.core.publisher.Mono;

/**
 * Initializes a new instance of the asynchronous LiteralServiceClient type.
 */
@ServiceClient(builder = LiteralServiceClientBuilder.class, isAsync = true)
public final class LiteralServiceAsyncClient {
    @Generated
    private final LiteralOpsImpl serviceClient;

    /**
     * Initializes an instance of LiteralServiceAsyncClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
    LiteralServiceAsyncClient(LiteralOpsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * The put operation.
     * <p><strong>Query Parameters</strong></p>
     * <table border="1">
     * <caption>Query Parameters</caption>
     * <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     * <tr><td>optionalLiteralParam</td><td>String</td><td>No</td><td>The optionalLiteralParam parameter. Allowed
     * values: "optionalLiteralParam".</td></tr>
     * </table>
     * You can add these to a request with {@link RequestOptions#addQueryParam}
     * <p><strong>Request Body Schema</strong></p>
     * <pre>{@code
     * {
     *     literal: String (Required)
     *     optionalLiteral: String(optionalLiteral) (Optional)
     * }
     * }</pre>
     * <p><strong>Response Body Schema</strong></p>
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
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> putWithResponse(BinaryData model, RequestOptions requestOptions) {
        return this.serviceClient.putWithResponseAsync(model, requestOptions);
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
     * @return the response body on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Model> put(Model model, OptionalLiteralParam optionalLiteralParam) {
        // Generated convenience method for putWithResponse
        RequestOptions requestOptions = new RequestOptions();
        if (optionalLiteralParam != null) {
            requestOptions.addQueryParam("optionalLiteralParam", optionalLiteralParam.toString(), false);
        }
        return putWithResponse(BinaryData.fromObject(model), requestOptions).flatMap(FluxUtil::toMono)
            .map(protocolMethodData -> protocolMethodData.toObject(Model.class));
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
     * @return the response body on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Model> put(Model model) {
        // Generated convenience method for putWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return putWithResponse(BinaryData.fromObject(model), requestOptions).flatMap(FluxUtil::toMono)
            .map(protocolMethodData -> protocolMethodData.toObject(Model.class));
    }
}
