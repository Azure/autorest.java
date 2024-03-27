// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.property.optional;

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
import com.type.property.optional.implementation.RequiredAndOptionalsImpl;
import com.type.property.optional.models.RequiredAndOptionalProperty;

/**
 * Initializes a new instance of the synchronous OptionalClient type.
 */
@ServiceClient(builder = OptionalClientBuilder.class)
public final class RequiredAndOptionalClient {
    @Generated
    private final RequiredAndOptionalsImpl serviceClient;

    /**
     * Initializes an instance of RequiredAndOptionalClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
    RequiredAndOptionalClient(RequiredAndOptionalsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get models that will return all properties in the model.
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>
     * <code>
     * {
     *     optionalProperty: String (Optional)
     *     requiredProperty: int (Required)
     * }
     * </code>
     * </pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return models that will return all properties in the model along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getAllWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getAllWithResponse(requestOptions);
    }

    /**
     * Get models that will return only the required properties.
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>
     * <code>
     * {
     *     optionalProperty: String (Optional)
     *     requiredProperty: int (Required)
     * }
     * </code>
     * </pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return models that will return only the required properties along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getRequiredOnlyWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getRequiredOnlyWithResponse(requestOptions);
    }

    /**
     * Put a body with all properties present.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>
     * <code>
     * {
     *     optionalProperty: String (Optional)
     *     requiredProperty: int (Required)
     * }
     * </code>
     * </pre>
     * 
     * @param body Model with required and optional properties.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putAllWithResponse(BinaryData body, RequestOptions requestOptions) {
        return this.serviceClient.putAllWithResponse(body, requestOptions);
    }

    /**
     * Put a body with only required properties.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>
     * <code>
     * {
     *     optionalProperty: String (Optional)
     *     requiredProperty: int (Required)
     * }
     * </code>
     * </pre>
     * 
     * @param body Model with required and optional properties.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putRequiredOnlyWithResponse(BinaryData body, RequestOptions requestOptions) {
        return this.serviceClient.putRequiredOnlyWithResponse(body, requestOptions);
    }

    /**
     * Get models that will return all properties in the model.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return models that will return all properties in the model.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public RequiredAndOptionalProperty getAll() {
        // Generated convenience method for getAllWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return getAllWithResponse(requestOptions).getValue().toObject(RequiredAndOptionalProperty.class);
    }

    /**
     * Get models that will return only the required properties.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return models that will return only the required properties.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public RequiredAndOptionalProperty getRequiredOnly() {
        // Generated convenience method for getRequiredOnlyWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return getRequiredOnlyWithResponse(requestOptions).getValue().toObject(RequiredAndOptionalProperty.class);
    }

    /**
     * Put a body with all properties present.
     * 
     * @param body Model with required and optional properties.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putAll(RequiredAndOptionalProperty body) {
        // Generated convenience method for putAllWithResponse
        RequestOptions requestOptions = new RequestOptions();
        putAllWithResponse(BinaryData.fromObject(body), requestOptions).getValue();
    }

    /**
     * Put a body with only required properties.
     * 
     * @param body Model with required and optional properties.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putRequiredOnly(RequiredAndOptionalProperty body) {
        // Generated convenience method for putRequiredOnlyWithResponse
        RequestOptions requestOptions = new RequestOptions();
        putRequiredOnlyWithResponse(BinaryData.fromObject(body), requestOptions).getValue();
    }
}
