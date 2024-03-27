// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.visibility;

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
import com.cadl.visibility.implementation.VisibilityClientImpl;
import com.cadl.visibility.models.Dog;
import com.cadl.visibility.models.ReadDog;
import com.cadl.visibility.models.RoundTripModel;
import com.cadl.visibility.models.WriteDog;

/**
 * Initializes a new instance of the synchronous VisibilityClient type.
 */
@ServiceClient(builder = VisibilityClientBuilder.class)
public final class VisibilityClient {
    @Generated
    private final VisibilityClientImpl serviceClient;

    /**
     * Initializes an instance of VisibilityClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
    VisibilityClient(VisibilityClientImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * The get operation.
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>
     * <code>
     * {
     *     id: int (Required)
     *     secretName: String (Required)
     *     name: String (Required)
     * }
     * </code>
     * </pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getWithResponse(requestOptions);
    }

    /**
     * The create operation.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>
     * <code>
     * {
     *     name: String (Required)
     * }
     * </code>
     * </pre>
     * 
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>
     * <code>
     * {
     *     id: int (Required)
     *     secretName: String (Required)
     *     name: String (Required)
     * }
     * </code>
     * </pre>
     * 
     * @param dog The dog parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> createWithResponse(BinaryData dog, RequestOptions requestOptions) {
        return this.serviceClient.createWithResponse(dog, requestOptions);
    }

    /**
     * The query operation.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>
     * <code>
     * {
     *     id: int (Required)
     *     name: String (Required)
     * }
     * </code>
     * </pre>
     * 
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>
     * <code>
     * {
     *     id: int (Required)
     *     secretName: String (Required)
     *     name: String (Required)
     * }
     * </code>
     * </pre>
     * 
     * @param dog The dog parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> queryWithResponse(BinaryData dog, RequestOptions requestOptions) {
        return this.serviceClient.queryWithResponse(dog, requestOptions);
    }

    /**
     * The roundtrip operation.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>
     * <code>
     * {
     *     name: String (Required)
     *     secretName: String (Required)
     * }
     * </code>
     * </pre>
     * 
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>
     * <code>
     * {
     *     name: String (Required)
     *     secretName: String (Required)
     * }
     * </code>
     * </pre>
     * 
     * @param body The body parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> roundtripWithResponse(BinaryData body, RequestOptions requestOptions) {
        return this.serviceClient.roundtripWithResponse(body, requestOptions);
    }

    /**
     * The get operation.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Dog get() {
        // Generated convenience method for getWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return getWithResponse(requestOptions).getValue().toObject(Dog.class);
    }

    /**
     * The create operation.
     * 
     * @param dog The dog parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Dog create(WriteDog dog) {
        // Generated convenience method for createWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return createWithResponse(BinaryData.fromObject(dog), requestOptions).getValue().toObject(Dog.class);
    }

    /**
     * The query operation.
     * 
     * @param dog The dog parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Dog query(ReadDog dog) {
        // Generated convenience method for queryWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return queryWithResponse(BinaryData.fromObject(dog), requestOptions).getValue().toObject(Dog.class);
    }

    /**
     * The roundtrip operation.
     * 
     * @param body The body parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public RoundTripModel roundtrip(RoundTripModel body) {
        // Generated convenience method for roundtripWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return roundtripWithResponse(BinaryData.fromObject(body), requestOptions).getValue()
            .toObject(RoundTripModel.class);
    }
}
