// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.
package com.parameters.spread;

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
import com.parameters.spread.implementation.AliasImpl;
import com.parameters.spread.implementation.models.SpreadAsRequestBodyRequest;
import com.parameters.spread.implementation.models.SpreadAsRequestParameterRequest;
import com.parameters.spread.implementation.models.SpreadWithMultipleParametersRequest;
import com.parameters.spread.models.SpreadWithMultipleParametersOptions;
import reactor.core.publisher.Mono;

/**
 * Initializes a new instance of the asynchronous SpreadClient type.
 */
@ServiceClient(builder = SpreadClientBuilder.class, isAsync = true)
public final class AliasAsyncClient {

    @Generated
    private final AliasImpl serviceClient;

    /**
     * Initializes an instance of AliasAsyncClient class.
     *
     * @param serviceClient the service client implementation.
     */
    @Generated
    AliasAsyncClient(AliasImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * The spreadAsRequestBody operation.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     name: String (Required)
     * }
     * }</pre>
     *
     * @param request The request parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> spreadAsRequestBodyWithResponse(BinaryData request, RequestOptions requestOptions) {
        return this.serviceClient.spreadAsRequestBodyWithResponseAsync(request, requestOptions);
    }

    /**
     * The spreadAsRequestParameter operation.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     name: String (Required)
     * }
     * }</pre>
     *
     * @param id A sequence of textual characters.
     * @param xMsTestHeader A sequence of textual characters.
     * @param request The request parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> spreadAsRequestParameterWithResponse(String id, String xMsTestHeader,
        BinaryData request, RequestOptions requestOptions) {
        return this.serviceClient.spreadAsRequestParameterWithResponseAsync(id, xMsTestHeader, request, requestOptions);
    }

    /**
     * The spreadWithMultipleParameters operation.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     prop1: String (Required)
     *     prop2: String (Required)
     *     prop3: String (Required)
     *     prop4: String (Required)
     *     prop5: String (Required)
     *     prop6: String (Required)
     * }
     * }</pre>
     *
     * @param id A sequence of textual characters.
     * @param xMsTestHeader A sequence of textual characters.
     * @param request The request parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> spreadWithMultipleParametersWithResponse(String id, String xMsTestHeader,
        BinaryData request, RequestOptions requestOptions) {
        return this.serviceClient.spreadWithMultipleParametersWithResponseAsync(id, xMsTestHeader, request,
            requestOptions);
    }

    /**
     * The spreadAsRequestBody operation.
     *
     * @param name A sequence of textual characters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> spreadAsRequestBody(String name) {
        // Generated convenience method for spreadAsRequestBodyWithResponse
        RequestOptions requestOptions = new RequestOptions();
        SpreadAsRequestBodyRequest requestObj = new SpreadAsRequestBodyRequest(name);
        BinaryData request = BinaryData.fromObject(requestObj);
        return spreadAsRequestBodyWithResponse(request, requestOptions).flatMap(FluxUtil::toMono);
    }

    /**
     * The spreadAsRequestParameter operation.
     *
     * @param id A sequence of textual characters.
     * @param xMsTestHeader A sequence of textual characters.
     * @param name A sequence of textual characters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> spreadAsRequestParameter(String id, String xMsTestHeader, String name) {
        // Generated convenience method for spreadAsRequestParameterWithResponse
        RequestOptions requestOptions = new RequestOptions();
        SpreadAsRequestParameterRequest requestObj = new SpreadAsRequestParameterRequest(name);
        BinaryData request = BinaryData.fromObject(requestObj);
        return spreadAsRequestParameterWithResponse(id, xMsTestHeader, request, requestOptions)
            .flatMap(FluxUtil::toMono);
    }

    /**
     * The spreadWithMultipleParameters operation.
     *
     * @param options Options for spreadWithMultipleParameters API.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> spreadWithMultipleParameters(SpreadWithMultipleParametersOptions options) {
        // Generated convenience method for spreadWithMultipleParametersWithResponse
        RequestOptions requestOptions = new RequestOptions();
        String id = options.getId();
        String xMsTestHeader = options.getXMsTestHeader();
        SpreadWithMultipleParametersRequest requestObj = new SpreadWithMultipleParametersRequest(options.getProp1(),
            options.getProp2(), options.getProp3(), options.getProp4(), options.getProp5(), options.getProp6());
        BinaryData request = BinaryData.fromObject(requestObj);
        return spreadWithMultipleParametersWithResponse(id, xMsTestHeader, request, requestOptions)
            .flatMap(FluxUtil::toMono);
    }
}
