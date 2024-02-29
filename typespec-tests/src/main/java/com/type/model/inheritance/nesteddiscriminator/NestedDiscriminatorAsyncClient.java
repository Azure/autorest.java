// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.model.inheritance.nesteddiscriminator;

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
import com.type.model.inheritance.nesteddiscriminator.implementation.NestedDiscriminatorClientImpl;
import com.type.model.inheritance.nesteddiscriminator.models.Fish;
import reactor.core.publisher.Mono;

/**
 * Initializes a new instance of the asynchronous NestedDiscriminatorClient type.
 */
@ServiceClient(builder = NestedDiscriminatorClientBuilder.class, isAsync = true)
public final class NestedDiscriminatorAsyncClient {
    @Generated
    private final NestedDiscriminatorClientImpl serviceClient;

    /**
     * Initializes an instance of NestedDiscriminatorAsyncClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
    NestedDiscriminatorAsyncClient(NestedDiscriminatorClientImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * The getModel operation.
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     *     kind: String (Optional)
     *     age: int (Required)
     * }
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return this is base model for polymorphic multiple levels inheritance with a discriminator along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getModelWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getModelWithResponseAsync(requestOptions);
    }

    /**
     * The putModel operation.
     * <p><strong>Request Body Schema</strong></p>
     * <pre>{@code
     * {
     *     kind: String (Optional)
     *     age: int (Required)
     * }
     * }</pre>
     * 
     * @param input This is base model for polymorphic multiple levels inheritance with a discriminator.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putModelWithResponse(BinaryData input, RequestOptions requestOptions) {
        return this.serviceClient.putModelWithResponseAsync(input, requestOptions);
    }

    /**
     * The getRecursiveModel operation.
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     *     kind: String (Optional)
     *     age: int (Required)
     * }
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return this is base model for polymorphic multiple levels inheritance with a discriminator along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getRecursiveModelWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getRecursiveModelWithResponseAsync(requestOptions);
    }

    /**
     * The putRecursiveModel operation.
     * <p><strong>Request Body Schema</strong></p>
     * <pre>{@code
     * {
     *     kind: String (Optional)
     *     age: int (Required)
     * }
     * }</pre>
     * 
     * @param input This is base model for polymorphic multiple levels inheritance with a discriminator.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putRecursiveModelWithResponse(BinaryData input, RequestOptions requestOptions) {
        return this.serviceClient.putRecursiveModelWithResponseAsync(input, requestOptions);
    }

    /**
     * The getMissingDiscriminator operation.
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     *     kind: String (Optional)
     *     age: int (Required)
     * }
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return this is base model for polymorphic multiple levels inheritance with a discriminator along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getMissingDiscriminatorWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getMissingDiscriminatorWithResponseAsync(requestOptions);
    }

    /**
     * The getWrongDiscriminator operation.
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     *     kind: String (Optional)
     *     age: int (Required)
     * }
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return this is base model for polymorphic multiple levels inheritance with a discriminator along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getWrongDiscriminatorWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getWrongDiscriminatorWithResponseAsync(requestOptions);
    }

    /**
     * The getModel operation.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return this is base model for polymorphic multiple levels inheritance with a discriminator on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Fish> getModel() {
        // Generated convenience method for getModelWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return getModelWithResponse(requestOptions).flatMap(FluxUtil::toMono)
            .map(protocolMethodData -> protocolMethodData.toObject(Fish.class));
    }

    /**
     * The putModel operation.
     * 
     * @param input This is base model for polymorphic multiple levels inheritance with a discriminator.
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
    public Mono<Void> putModel(Fish input) {
        // Generated convenience method for putModelWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return putModelWithResponse(BinaryData.fromObject(input), requestOptions).flatMap(FluxUtil::toMono);
    }

    /**
     * The getRecursiveModel operation.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return this is base model for polymorphic multiple levels inheritance with a discriminator on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Fish> getRecursiveModel() {
        // Generated convenience method for getRecursiveModelWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return getRecursiveModelWithResponse(requestOptions).flatMap(FluxUtil::toMono)
            .map(protocolMethodData -> protocolMethodData.toObject(Fish.class));
    }

    /**
     * The putRecursiveModel operation.
     * 
     * @param input This is base model for polymorphic multiple levels inheritance with a discriminator.
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
    public Mono<Void> putRecursiveModel(Fish input) {
        // Generated convenience method for putRecursiveModelWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return putRecursiveModelWithResponse(BinaryData.fromObject(input), requestOptions).flatMap(FluxUtil::toMono);
    }

    /**
     * The getMissingDiscriminator operation.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return this is base model for polymorphic multiple levels inheritance with a discriminator on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Fish> getMissingDiscriminator() {
        // Generated convenience method for getMissingDiscriminatorWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return getMissingDiscriminatorWithResponse(requestOptions).flatMap(FluxUtil::toMono)
            .map(protocolMethodData -> protocolMethodData.toObject(Fish.class));
    }

    /**
     * The getWrongDiscriminator operation.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return this is base model for polymorphic multiple levels inheritance with a discriminator on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Fish> getWrongDiscriminator() {
        // Generated convenience method for getWrongDiscriminatorWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return getWrongDiscriminatorWithResponse(requestOptions).flatMap(FluxUtil::toMono)
            .map(protocolMethodData -> protocolMethodData.toObject(Fish.class));
    }
}
