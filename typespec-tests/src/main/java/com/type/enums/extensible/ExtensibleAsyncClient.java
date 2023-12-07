// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.enums.extensible;

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
import com.type.enums.extensible.implementation.StringOperationsImpl;
import com.type.enums.extensible.models.DaysOfWeekExtensibleEnum;
import reactor.core.publisher.Mono;

/**
 * Initializes a new instance of the asynchronous ExtensibleClient type.
 */
@ServiceClient(builder = ExtensibleClientBuilder.class, isAsync = true)
public final class ExtensibleAsyncClient {
    @Generated
    private final StringOperationsImpl serviceClient;

    /**
     * Initializes an instance of ExtensibleAsyncClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
    ExtensibleAsyncClient(StringOperationsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * The getKnownValue operation.
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * String(Monday/Tuesday/Wednesday/Thursday/Friday/Saturday/Sunday)
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return days of the week along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getKnownValueWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getKnownValueWithResponseAsync(requestOptions);
    }

    /**
     * The getUnknownValue operation.
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * String(Monday/Tuesday/Wednesday/Thursday/Friday/Saturday/Sunday)
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return days of the week along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getUnknownValueWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getUnknownValueWithResponseAsync(requestOptions);
    }

    /**
     * The putKnownValue operation.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * String(Monday/Tuesday/Wednesday/Thursday/Friday/Saturday/Sunday)
     * }</pre>
     * 
     * @param body Days of the week.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putKnownValueWithResponse(BinaryData body, RequestOptions requestOptions) {
        return this.serviceClient.putKnownValueWithResponseAsync(body, requestOptions);
    }

    /**
     * The putUnknownValue operation.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * String(Monday/Tuesday/Wednesday/Thursday/Friday/Saturday/Sunday)
     * }</pre>
     * 
     * @param body Days of the week.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putUnknownValueWithResponse(BinaryData body, RequestOptions requestOptions) {
        return this.serviceClient.putUnknownValueWithResponseAsync(body, requestOptions);
    }

    /**
     * The getKnownValue operation.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return days of the week on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DaysOfWeekExtensibleEnum> getKnownValue() {
        // Generated convenience method for getKnownValueWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return getKnownValueWithResponse(requestOptions).flatMap(FluxUtil::toMono)
            .map(protocolMethodData -> DaysOfWeekExtensibleEnum.fromString(protocolMethodData.toObject(String.class)));
    }

    /**
     * The getUnknownValue operation.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return days of the week on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DaysOfWeekExtensibleEnum> getUnknownValue() {
        // Generated convenience method for getUnknownValueWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return getUnknownValueWithResponse(requestOptions).flatMap(FluxUtil::toMono)
            .map(protocolMethodData -> DaysOfWeekExtensibleEnum.fromString(protocolMethodData.toObject(String.class)));
    }

    /**
     * The putKnownValue operation.
     * 
     * @param body Days of the week.
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
    public Mono<Void> putKnownValue(DaysOfWeekExtensibleEnum body) {
        // Generated convenience method for putKnownValueWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return putKnownValueWithResponse(BinaryData.fromObject(body == null ? null : body.toString()), requestOptions)
            .flatMap(FluxUtil::toMono);
    }

    /**
     * The putUnknownValue operation.
     * 
     * @param body Days of the week.
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
    public Mono<Void> putUnknownValue(DaysOfWeekExtensibleEnum body) {
        // Generated convenience method for putUnknownValueWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return putUnknownValueWithResponse(BinaryData.fromObject(body == null ? null : body.toString()), requestOptions)
            .flatMap(FluxUtil::toMono);
    }
}
