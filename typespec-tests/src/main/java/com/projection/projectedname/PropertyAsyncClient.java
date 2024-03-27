// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.projection.projectedname;

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
import com.projection.projectedname.implementation.PropertiesImpl;
import com.projection.projectedname.models.ClientProjectedNameModel;
import com.projection.projectedname.models.JsonAndClientProjectedNameModel;
import com.projection.projectedname.models.JsonProjectedNameModel;
import com.projection.projectedname.models.LanguageProjectedNameModel;
import reactor.core.publisher.Mono;

/**
 * Initializes a new instance of the asynchronous ProjectedNameClient type.
 */
@ServiceClient(builder = ProjectedNameClientBuilder.class, isAsync = true)
public final class PropertyAsyncClient {
    @Generated
    private final PropertiesImpl serviceClient;

    /**
     * Initializes an instance of PropertyAsyncClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
    PropertyAsyncClient(PropertiesImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * The json operation.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>
     * <code>
     * {
     *     wireName: boolean (Required)
     * }
     * </code>
     * </pre>
     * 
     * @param jsonProjectedNameModel The jsonProjectedNameModel parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> jsonWithResponse(BinaryData jsonProjectedNameModel, RequestOptions requestOptions) {
        return this.serviceClient.jsonWithResponseAsync(jsonProjectedNameModel, requestOptions);
    }

    /**
     * The client operation.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>
     * <code>
     * {
     *     defaultName: boolean (Required)
     * }
     * </code>
     * </pre>
     * 
     * @param clientProjectedNameModel The clientProjectedNameModel parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> clientWithResponse(BinaryData clientProjectedNameModel, RequestOptions requestOptions) {
        return this.serviceClient.clientWithResponseAsync(clientProjectedNameModel, requestOptions);
    }

    /**
     * The language operation.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>
     * <code>
     * {
     *     defaultName: boolean (Required)
     * }
     * </code>
     * </pre>
     * 
     * @param languageProjectedNameModel The languageProjectedNameModel parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> languageWithResponse(BinaryData languageProjectedNameModel,
        RequestOptions requestOptions) {
        return this.serviceClient.languageWithResponseAsync(languageProjectedNameModel, requestOptions);
    }

    /**
     * The jsonAndClient operation.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>
     * <code>
     * {
     *     wireName: boolean (Required)
     * }
     * </code>
     * </pre>
     * 
     * @param jsonAndClientProjectedNameModel The jsonAndClientProjectedNameModel parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> jsonAndClientWithResponse(BinaryData jsonAndClientProjectedNameModel,
        RequestOptions requestOptions) {
        return this.serviceClient.jsonAndClientWithResponseAsync(jsonAndClientProjectedNameModel, requestOptions);
    }

    /**
     * The json operation.
     * 
     * @param jsonProjectedNameModel The jsonProjectedNameModel parameter.
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
    public Mono<Void> json(JsonProjectedNameModel jsonProjectedNameModel) {
        // Generated convenience method for jsonWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return jsonWithResponse(BinaryData.fromObject(jsonProjectedNameModel), requestOptions)
            .flatMap(FluxUtil::toMono);
    }

    /**
     * The client operation.
     * 
     * @param clientProjectedNameModel The clientProjectedNameModel parameter.
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
    public Mono<Void> client(ClientProjectedNameModel clientProjectedNameModel) {
        // Generated convenience method for clientWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return clientWithResponse(BinaryData.fromObject(clientProjectedNameModel), requestOptions)
            .flatMap(FluxUtil::toMono);
    }

    /**
     * The language operation.
     * 
     * @param languageProjectedNameModel The languageProjectedNameModel parameter.
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
    public Mono<Void> language(LanguageProjectedNameModel languageProjectedNameModel) {
        // Generated convenience method for languageWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return languageWithResponse(BinaryData.fromObject(languageProjectedNameModel), requestOptions)
            .flatMap(FluxUtil::toMono);
    }

    /**
     * The jsonAndClient operation.
     * 
     * @param jsonAndClientProjectedNameModel The jsonAndClientProjectedNameModel parameter.
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
    public Mono<Void> jsonAndClient(JsonAndClientProjectedNameModel jsonAndClientProjectedNameModel) {
        // Generated convenience method for jsonAndClientWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return jsonAndClientWithResponse(BinaryData.fromObject(jsonAndClientProjectedNameModel), requestOptions)
            .flatMap(FluxUtil::toMono);
    }
}
