// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.client.naming;

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
import com.client.naming.implementation.NamingClientImpl;
import com.client.naming.models.ClientNameAndJsonEncodedNameModel;
import com.client.naming.models.ClientNameModel;
import com.client.naming.models.LanguageClientNameModel;
import reactor.core.publisher.Mono;

/**
 * Initializes a new instance of the asynchronous NamingClient type.
 */
@ServiceClient(builder = NamingClientBuilder.class, isAsync = true)
public final class NamingAsyncClient {
    @Generated
    private final NamingClientImpl serviceClient;

    /**
     * Initializes an instance of NamingAsyncClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
    NamingAsyncClient(NamingClientImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * The clientName operation.
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> clientNameWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.clientNameWithResponseAsync(requestOptions);
    }

    /**
     * The parameter operation.
     * 
     * @param clientName A sequence of textual characters.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> parameterWithResponse(String clientName, RequestOptions requestOptions) {
        return this.serviceClient.parameterWithResponseAsync(clientName, requestOptions);
    }

    /**
     * The client operation.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     defaultName: boolean (Required)
     * }
     * }</pre>
     * 
     * @param clientNameModel The clientNameModel parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> clientWithResponse(BinaryData clientNameModel, RequestOptions requestOptions) {
        return this.serviceClient.clientWithResponseAsync(clientNameModel, requestOptions);
    }

    /**
     * The language operation.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     defaultName: boolean (Required)
     * }
     * }</pre>
     * 
     * @param languageClientNameModel The languageClientNameModel parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> languageWithResponse(BinaryData languageClientNameModel,
        RequestOptions requestOptions) {
        return this.serviceClient.languageWithResponseAsync(languageClientNameModel, requestOptions);
    }

    /**
     * The compatibleWithEncodedName operation.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     wireName: boolean (Required)
     * }
     * }</pre>
     * 
     * @param clientNameAndJsonEncodedNameModel The clientNameAndJsonEncodedNameModel parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> compatibleWithEncodedNameWithResponse(BinaryData clientNameAndJsonEncodedNameModel,
        RequestOptions requestOptions) {
        return this.serviceClient.compatibleWithEncodedNameWithResponseAsync(clientNameAndJsonEncodedNameModel,
            requestOptions);
    }

    /**
     * The request operation.
     * 
     * @param clientName A sequence of textual characters.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> requestWithResponse(String clientName, RequestOptions requestOptions) {
        return this.serviceClient.requestWithResponseAsync(clientName, requestOptions);
    }

    /**
     * The response operation.
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.responseWithResponseAsync(requestOptions);
    }

    /**
     * The clientName operation.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> clientName() {
        // Generated convenience method for clientNameWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return clientNameWithResponse(requestOptions).flatMap(FluxUtil::toMono);
    }

    /**
     * The parameter operation.
     * 
     * @param clientName A sequence of textual characters.
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
    public Mono<Void> parameter(String clientName) {
        // Generated convenience method for parameterWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return parameterWithResponse(clientName, requestOptions).flatMap(FluxUtil::toMono);
    }

    /**
     * The client operation.
     * 
     * @param clientNameModel The clientNameModel parameter.
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
    public Mono<Void> client(ClientNameModel clientNameModel) {
        // Generated convenience method for clientWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return clientWithResponse(BinaryData.fromObject(clientNameModel), requestOptions).flatMap(FluxUtil::toMono);
    }

    /**
     * The language operation.
     * 
     * @param languageClientNameModel The languageClientNameModel parameter.
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
    public Mono<Void> language(LanguageClientNameModel languageClientNameModel) {
        // Generated convenience method for languageWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return languageWithResponse(BinaryData.fromObject(languageClientNameModel), requestOptions)
            .flatMap(FluxUtil::toMono);
    }

    /**
     * The compatibleWithEncodedName operation.
     * 
     * @param clientNameAndJsonEncodedNameModel The clientNameAndJsonEncodedNameModel parameter.
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
    public Mono<Void> compatibleWithEncodedName(ClientNameAndJsonEncodedNameModel clientNameAndJsonEncodedNameModel) {
        // Generated convenience method for compatibleWithEncodedNameWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return compatibleWithEncodedNameWithResponse(BinaryData.fromObject(clientNameAndJsonEncodedNameModel),
            requestOptions).flatMap(FluxUtil::toMono);
    }

    /**
     * The request operation.
     * 
     * @param clientName A sequence of textual characters.
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
    public Mono<Void> request(String clientName) {
        // Generated convenience method for requestWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return requestWithResponse(clientName, requestOptions).flatMap(FluxUtil::toMono);
    }

    /**
     * The response operation.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> response() {
        // Generated convenience method for responseWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return responseWithResponse(requestOptions).flatMap(FluxUtil::toMono);
    }
}
