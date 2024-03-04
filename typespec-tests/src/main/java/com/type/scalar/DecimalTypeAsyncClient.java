// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.scalar;

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
import com.type.scalar.implementation.DecimalTypesImpl;
import java.math.BigDecimal;
import reactor.core.publisher.Mono;

/**
 * Initializes a new instance of the asynchronous ScalarClient type.
 */
@ServiceClient(builder = ScalarClientBuilder.class, isAsync = true)
public final class DecimalTypeAsyncClient {
    @Generated
    private final DecimalTypesImpl serviceClient;

    /**
     * Initializes an instance of DecimalTypeAsyncClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
    DecimalTypeAsyncClient(DecimalTypesImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * The responseBody operation.
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * BigDecimal
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return a decimal number with any length and precision along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> responseBodyWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.responseBodyWithResponseAsync(requestOptions);
    }

    /**
     * The requestBody operation.
     * <p><strong>Request Body Schema</strong></p>
     * <pre>{@code
     * BigDecimal
     * }</pre>
     * 
     * @param body A decimal number with any length and precision. This represent any `decimal` value possible.
     * It is commonly represented as `BigDecimal` in some languages.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> requestBodyWithResponse(BinaryData body, RequestOptions requestOptions) {
        return this.serviceClient.requestBodyWithResponseAsync(body, requestOptions);
    }

    /**
     * The requestParameter operation.
     * 
     * @param value A decimal number with any length and precision. This represent any `decimal` value possible.
     * It is commonly represented as `BigDecimal` in some languages.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> requestParameterWithResponse(BigDecimal value, RequestOptions requestOptions) {
        return this.serviceClient.requestParameterWithResponseAsync(value, requestOptions);
    }

    /**
     * The responseBody operation.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a decimal number with any length and precision on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BigDecimal> responseBody() {
        // Generated convenience method for responseBodyWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return responseBodyWithResponse(requestOptions).flatMap(FluxUtil::toMono)
            .map(protocolMethodData -> protocolMethodData.toObject(BigDecimal.class));
    }

    /**
     * The requestBody operation.
     * 
     * @param body A decimal number with any length and precision. This represent any `decimal` value possible.
     * It is commonly represented as `BigDecimal` in some languages.
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
    public Mono<Void> requestBody(BigDecimal body) {
        // Generated convenience method for requestBodyWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return requestBodyWithResponse(BinaryData.fromObject(body), requestOptions).flatMap(FluxUtil::toMono);
    }

    /**
     * The requestParameter operation.
     * 
     * @param value A decimal number with any length and precision. This represent any `decimal` value possible.
     * It is commonly represented as `BigDecimal` in some languages.
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
    public Mono<Void> requestParameter(BigDecimal value) {
        // Generated convenience method for requestParameterWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return requestParameterWithResponse(value, requestOptions).flatMap(FluxUtil::toMono);
    }
}
