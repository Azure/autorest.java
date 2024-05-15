// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.encode.bytes;

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
import com.azure.core.util.Base64Url;
import com.azure.core.util.BinaryData;
import com.encode.bytes.implementation.RequestBodiesImpl;

/**
 * Initializes a new instance of the synchronous BytesClient type.
 */
@ServiceClient(builder = BytesClientBuilder.class)
public final class RequestBodyClient {
    @Generated
    private final RequestBodiesImpl serviceClient;

    /**
     * Initializes an instance of RequestBodyClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
    RequestBodyClient(RequestBodiesImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * The defaultMethod operation.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>{@code
     * byte[]
     * }</pre>
     * 
     * @param value The value parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> defaultMethodWithResponse(BinaryData value, RequestOptions requestOptions) {
        return this.serviceClient.defaultMethodWithResponse(value, requestOptions);
    }

    /**
     * The octetStream operation.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>{@code
     * BinaryData
     * }</pre>
     * 
     * @param value Represent a byte array.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> octetStreamWithResponse(BinaryData value, RequestOptions requestOptions) {
        return this.serviceClient.octetStreamWithResponse(value, requestOptions);
    }

    /**
     * The customContentType operation.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>{@code
     * BinaryData
     * }</pre>
     * 
     * @param value Represent a byte array.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> customContentTypeWithResponse(BinaryData value, RequestOptions requestOptions) {
        return this.serviceClient.customContentTypeWithResponse(value, requestOptions);
    }

    /**
     * The base64 operation.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>{@code
     * byte[]
     * }</pre>
     * 
     * @param value The value parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> base64WithResponse(BinaryData value, RequestOptions requestOptions) {
        return this.serviceClient.base64WithResponse(value, requestOptions);
    }

    /**
     * The base64url operation.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>{@code
     * Base64Url
     * }</pre>
     * 
     * @param value The value parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> base64urlWithResponse(BinaryData value, RequestOptions requestOptions) {
        return this.serviceClient.base64urlWithResponse(value, requestOptions);
    }

    /**
     * The defaultMethod operation.
     * 
     * @param value The value parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void defaultMethod(byte[] value) {
        // Generated convenience method for defaultMethodWithResponse
        RequestOptions requestOptions = new RequestOptions();
        defaultMethodWithResponse(BinaryData.fromObject(value), requestOptions).getValue();
    }

    /**
     * The octetStream operation.
     * 
     * @param value Represent a byte array.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void octetStream(BinaryData value) {
        // Generated convenience method for octetStreamWithResponse
        RequestOptions requestOptions = new RequestOptions();
        octetStreamWithResponse(value, requestOptions).getValue();
    }

    /**
     * The customContentType operation.
     * 
     * @param value Represent a byte array.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void customContentType(BinaryData value) {
        // Generated convenience method for customContentTypeWithResponse
        RequestOptions requestOptions = new RequestOptions();
        customContentTypeWithResponse(value, requestOptions).getValue();
    }

    /**
     * The base64 operation.
     * 
     * @param value The value parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void base64(byte[] value) {
        // Generated convenience method for base64WithResponse
        RequestOptions requestOptions = new RequestOptions();
        base64WithResponse(BinaryData.fromObject(value), requestOptions).getValue();
    }

    /**
     * The base64url operation.
     * 
     * @param value The value parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void base64url(byte[] value) {
        // Generated convenience method for base64urlWithResponse
        RequestOptions requestOptions = new RequestOptions();
        base64urlWithResponse(BinaryData.fromObject(Base64Url.encode(value)), requestOptions).getValue();
    }
}
