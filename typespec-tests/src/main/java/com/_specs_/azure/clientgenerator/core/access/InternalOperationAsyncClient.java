// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com._specs_.azure.clientgenerator.core.access;

import com._specs_.azure.clientgenerator.core.access.implementation.InternalOperationsImpl;
import com._specs_.azure.clientgenerator.core.access.implementation.models.InternalDecoratorModelInInternal;
import com._specs_.azure.clientgenerator.core.access.implementation.models.NoDecoratorModelInInternal;
import com._specs_.azure.clientgenerator.core.access.models.PublicDecoratorModelInInternal;
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
import reactor.core.publisher.Mono;

/**
 * Initializes a new instance of the asynchronous AccessClient type.
 */
@ServiceClient(builder = AccessClientBuilder.class, isAsync = true)
public final class InternalOperationAsyncClient {
    @Generated
    private final InternalOperationsImpl serviceClient;

    /**
     * Initializes an instance of InternalOperationAsyncClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
    InternalOperationAsyncClient(InternalOperationsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * The noDecoratorInInternal operation.
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     name: String (Required)
     * }
     * }</pre>
     * 
     * @param name A sequence of textual characters.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return used in an internal operation, should be generated but not exported along with {@link Response} on
     * successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    Mono<Response<BinaryData>> noDecoratorInInternalWithResponse(String name, RequestOptions requestOptions) {
        return this.serviceClient.noDecoratorInInternalWithResponseAsync(name, requestOptions);
    }

    /**
     * The internalDecoratorInInternal operation.
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     name: String (Required)
     * }
     * }</pre>
     * 
     * @param name A sequence of textual characters.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return used in an internal operation, should be generated but not exported along with {@link Response} on
     * successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    Mono<Response<BinaryData>> internalDecoratorInInternalWithResponse(String name, RequestOptions requestOptions) {
        return this.serviceClient.internalDecoratorInInternalWithResponseAsync(name, requestOptions);
    }

    /**
     * The publicDecoratorInInternal operation.
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     name: String (Required)
     * }
     * }</pre>
     * 
     * @param name A sequence of textual characters.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return used in an internal operation but with public decorator, should be generated and exported along with
     * {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    Mono<Response<BinaryData>> publicDecoratorInInternalWithResponse(String name, RequestOptions requestOptions) {
        return this.serviceClient.publicDecoratorInInternalWithResponseAsync(name, requestOptions);
    }

    /**
     * The noDecoratorInInternal operation.
     * 
     * @param name A sequence of textual characters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return used in an internal operation, should be generated but not exported on successful completion of
     * {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    Mono<NoDecoratorModelInInternal> noDecoratorInInternal(String name) {
        // Generated convenience method for noDecoratorInInternalWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return noDecoratorInInternalWithResponse(name, requestOptions).flatMap(FluxUtil::toMono)
            .map(protocolMethodData -> protocolMethodData.toObject(NoDecoratorModelInInternal.class));
    }

    /**
     * The internalDecoratorInInternal operation.
     * 
     * @param name A sequence of textual characters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return used in an internal operation, should be generated but not exported on successful completion of
     * {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    Mono<InternalDecoratorModelInInternal> internalDecoratorInInternal(String name) {
        // Generated convenience method for internalDecoratorInInternalWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return internalDecoratorInInternalWithResponse(name, requestOptions).flatMap(FluxUtil::toMono)
            .map(protocolMethodData -> protocolMethodData.toObject(InternalDecoratorModelInInternal.class));
    }

    /**
     * The publicDecoratorInInternal operation.
     * 
     * @param name A sequence of textual characters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return used in an internal operation but with public decorator, should be generated and exported on successful
     * completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    Mono<PublicDecoratorModelInInternal> publicDecoratorInInternal(String name) {
        // Generated convenience method for publicDecoratorInInternalWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return publicDecoratorInInternalWithResponse(name, requestOptions).flatMap(FluxUtil::toMono)
            .map(protocolMethodData -> protocolMethodData.toObject(PublicDecoratorModelInInternal.class));
    }
}
