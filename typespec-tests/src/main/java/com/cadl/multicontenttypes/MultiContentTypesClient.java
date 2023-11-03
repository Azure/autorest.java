// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.multicontenttypes;

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
import com.cadl.multicontenttypes.implementation.MultiContentTypesClientImpl;
import com.cadl.multicontenttypes.models.Resource;

/**
 * Initializes a new instance of the synchronous MultiContentTypesClient type.
 */
@ServiceClient(builder = MultiContentTypesClientBuilder.class)
public final class MultiContentTypesClient {
    @Generated
    private final MultiContentTypesClientImpl serviceClient;

    /**
     * Initializes an instance of MultiContentTypesClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
    MultiContentTypesClient(MultiContentTypesClientImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * multiple data types map to multiple content types.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * DataModelBase
     * }</pre>
     * 
     * @param contentType The contentType parameter. Allowed values: "text/plain", "application/json",
     * "application/octet-stream", "image/jpeg", "image/png".
     * @param data The data parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> uploadWithOverloadWithResponse(String contentType, BinaryData data,
        RequestOptions requestOptions) {
        // Convenience API is not generated, as operation 'uploadWithOverload' is multiple content-type
        return this.serviceClient.uploadWithOverloadWithResponse(contentType, data, requestOptions);
    }

    /**
     * response is binary.
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * BinaryData
     * }</pre>
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
    public Response<BinaryData> downloadImageForSingleContentTypeWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.downloadImageForSingleContentTypeWithResponse(requestOptions);
    }

    /**
     * request is binary.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * BinaryData
     * }</pre>
     * 
     * @param data Represent a byte array.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> uploadImageForSingleContentTypeWithResponse(BinaryData data, RequestOptions requestOptions) {
        return this.serviceClient.uploadImageForSingleContentTypeWithResponse(data, requestOptions);
    }

    /**
     * one data type maps to multiple content types.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * byte[]
     * }</pre>
     * 
     * @param contentType The contentType parameter. Allowed values: "application/octet-stream", "image/jpeg",
     * "image/png".
     * @param data Represent a byte array.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> uploadBytesWithSingleBodyTypeForMultiContentTypesWithResponse(String contentType,
        BinaryData data, RequestOptions requestOptions) {
        // Convenience API is not generated, as operation 'uploadBytesWithSingleBodyTypeForMultiContentTypes' is
        // multiple content-type
        return this.serviceClient.uploadBytesWithSingleBodyTypeForMultiContentTypesWithResponse(contentType, data,
            requestOptions);
    }

    /**
     * multiple data types map to multiple content types using shared route.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * byte[]
     * }</pre>
     * 
     * @param contentType The contentType parameter. Allowed values: "application/octet-stream", "image/jpeg",
     * "image/png".
     * @param data Represent a byte array.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> uploadBytesWithMultiBodyTypesForMultiContentTypesWithResponse(String contentType,
        BinaryData data, RequestOptions requestOptions) {
        // Convenience API is not generated, as operation 'uploadBytesWithMultiBodyTypesForMultiContentTypes' is
        // multiple content-type
        return this.serviceClient.uploadBytesWithMultiBodyTypesForMultiContentTypesWithResponse(contentType, data,
            requestOptions);
    }

    /**
     * multiple data types map to multiple content types using shared route.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     * }
     * }</pre>
     * 
     * @param data The data parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> uploadJsonWithMultiBodyTypesForMultiContentTypesWithResponse(BinaryData data,
        RequestOptions requestOptions) {
        return this.serviceClient.uploadJsonWithMultiBodyTypesForMultiContentTypesWithResponse(data, requestOptions);
    }

    /**
     * multiple data types map to multiple content types using shared route.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * DataModelBase1
     * }</pre>
     * 
     * @param contentType The contentType parameter. Allowed values: "application/json", "application/octet-stream",
     * "image/jpeg", "image/png".
     * @param data The data parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> uploadJsonOrBytesWithMultiBodyTypesForMultiContentTypesWithResponse(String contentType,
        BinaryData data, RequestOptions requestOptions) {
        // Convenience API is not generated, as operation 'uploadJsonOrBytesWithMultiBodyTypesForMultiContentTypes' is
        // multiple content-type
        return this.serviceClient.uploadJsonOrBytesWithMultiBodyTypesForMultiContentTypesWithResponse(contentType, data,
            requestOptions);
    }

    /**
     * response is binary.
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
    public BinaryData downloadImageForSingleContentType() {
        // Generated convenience method for downloadImageForSingleContentTypeWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return downloadImageForSingleContentTypeWithResponse(requestOptions).getValue();
    }

    /**
     * request is binary.
     * 
     * @param data Represent a byte array.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void uploadImageForSingleContentType(BinaryData data) {
        // Generated convenience method for uploadImageForSingleContentTypeWithResponse
        RequestOptions requestOptions = new RequestOptions();
        uploadImageForSingleContentTypeWithResponse(data, requestOptions).getValue();
    }

    /**
     * multiple data types map to multiple content types using shared route.
     * 
     * @param data The data parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void uploadJsonWithMultiBodyTypesForMultiContentTypes(Resource data) {
        // Generated convenience method for uploadJsonWithMultiBodyTypesForMultiContentTypesWithResponse
        RequestOptions requestOptions = new RequestOptions();
        uploadJsonWithMultiBodyTypesForMultiContentTypesWithResponse(BinaryData.fromObject(data), requestOptions)
            .getValue();
    }
}
