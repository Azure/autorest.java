// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.multiinterfaceclient;

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
import com.multiinterfaceclient.models.Cat;
import com.multiinterfaceclient.models.Dog;

/** Initializes a new instance of the synchronous MultiInterfaceClient type. */
@ServiceClient(builder = MultiInterfaceClientBuilder.class)
public final class MultiInterfaceClient {
    @Generated private final MultiInterfaceAsyncClient client;

    /**
     * Initializes an instance of MultiInterfaceClient class.
     *
     * @param client the async client.
     */
    @Generated
    MultiInterfaceClient(MultiInterfaceAsyncClient client) {
        this.client = client;
    }

    /**
     * The getDogs operation.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     name: String (Required)
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return simple model along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getDogsWithResponse(RequestOptions requestOptions) {
        return this.client.getDogsWithResponse(requestOptions).block();
    }

    /**
     * The setDogs operation.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     name: String (Required)
     * }
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     name: String (Required)
     * }
     * }</pre>
     *
     * @param input Simple model.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return simple model along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> setDogsWithResponse(BinaryData input, RequestOptions requestOptions) {
        return this.client.setDogsWithResponse(input, requestOptions).block();
    }

    /**
     * The getCats operation.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     name: String (Required)
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return simple model along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getCatsWithResponse(RequestOptions requestOptions) {
        return this.client.getCatsWithResponse(requestOptions).block();
    }

    /**
     * The setCats operation.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     name: String (Required)
     * }
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     name: String (Required)
     * }
     * }</pre>
     *
     * @param input Simple model.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return simple model along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> setCatsWithResponse(BinaryData input, RequestOptions requestOptions) {
        return this.client.setCatsWithResponse(input, requestOptions).block();
    }

    /**
     * The getDogs operation.
     *
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return simple model.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Dog getDogs() {
        // Generated convenience method for getDogsWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return getDogsWithResponse(requestOptions).getValue().toObject(Dog.class);
    }

    /**
     * The setDogs operation.
     *
     * @param input Simple model.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return simple model.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Dog setDogs(Dog input) {
        // Generated convenience method for setDogsWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return setDogsWithResponse(BinaryData.fromObject(input), requestOptions).getValue().toObject(Dog.class);
    }

    /**
     * The getCats operation.
     *
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return simple model.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Cat getCats() {
        // Generated convenience method for getCatsWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return getCatsWithResponse(requestOptions).getValue().toObject(Cat.class);
    }

    /**
     * The setCats operation.
     *
     * @param input Simple model.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return simple model.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Cat setCats(Cat input) {
        // Generated convenience method for setCatsWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return setCatsWithResponse(BinaryData.fromObject(input), requestOptions).getValue().toObject(Cat.class);
    }
}
