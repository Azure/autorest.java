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
import com.azure.core.util.serializer.TypeReference;
import com.type.scalar.implementation.DecimalVerifiesImpl;
import java.math.BigDecimal;
import java.util.List;
import reactor.core.publisher.Mono;

/**
 * Initializes a new instance of the asynchronous ScalarClient type.
 */
@ServiceClient(builder = ScalarClientBuilder.class, isAsync = true)
public final class DecimalVerifyAsyncClient {
    @Generated
    private final DecimalVerifiesImpl serviceClient;

    /**
     * Initializes an instance of DecimalVerifyAsyncClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
    DecimalVerifyAsyncClient(DecimalVerifiesImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * The prepareVerify operation.
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * [
     *     BigDecimal (Required)
     * ]
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> prepareVerifyWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.prepareVerifyWithResponseAsync(requestOptions);
    }

    /**
     * The verify operation.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
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
    public Mono<Response<Void>> verifyWithResponse(BinaryData body, RequestOptions requestOptions) {
        return this.serviceClient.verifyWithResponseAsync(body, requestOptions);
    }

    /**
     * The prepareVerify operation.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<BigDecimal>> prepareVerify() {
        // Generated convenience method for prepareVerifyWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return prepareVerifyWithResponse(requestOptions).flatMap(FluxUtil::toMono)
            .map(protocolMethodData -> protocolMethodData.toObject(TYPE_REFERENCE_LIST_BIG_DECIMAL));
    }

    /**
     * The verify operation.
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
    public Mono<Void> verify(BigDecimal body) {
        // Generated convenience method for verifyWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return verifyWithResponse(BinaryData.fromObject(body), requestOptions).flatMap(FluxUtil::toMono);
    }

    @Generated
    private static final TypeReference<List<BigDecimal>> TYPE_REFERENCE_LIST_BIG_DECIMAL
        = new TypeReference<List<BigDecimal>>() {
        };
}
