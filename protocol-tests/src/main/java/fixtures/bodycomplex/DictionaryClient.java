// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex;

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

import fixtures.bodycomplex.implementation.DictionariesImpl;

/**
 * Initializes a new instance of the synchronous AutoRestComplexTestServiceClient type.
 */
@ServiceClient(builder = DictionaryClientBuilder.class)
public final class DictionaryClient {
    @Generated
    private final DictionariesImpl serviceClient;

    /**
     * Initializes an instance of DictionaryClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
    DictionaryClient(DictionariesImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get complex types with dictionary property.
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     defaultProgram (Optional): {
     *         String: String (Required)
     *     }
     * }
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return complex types with dictionary property along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getValidWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getValidWithResponse(requestOptions);
    }

    /**
     * Put complex types with dictionary property.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     defaultProgram (Optional): {
     *         String: String (Required)
     *     }
     * }
     * }</pre>
     * 
     * @param complexBody Please put a dictionary with 5 key-value pairs: "txt":"notepad", "bmp":"mspaint",
     * "xls":"excel", "exe":"", "":null.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putValidWithResponse(BinaryData complexBody, RequestOptions requestOptions) {
        return this.serviceClient.putValidWithResponse(complexBody, requestOptions);
    }

    /**
     * Get complex types with dictionary property which is empty.
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     defaultProgram (Optional): {
     *         String: String (Required)
     *     }
     * }
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return complex types with dictionary property which is empty along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getEmptyWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getEmptyWithResponse(requestOptions);
    }

    /**
     * Put complex types with dictionary property which is empty.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     defaultProgram (Optional): {
     *         String: String (Required)
     *     }
     * }
     * }</pre>
     * 
     * @param complexBody Please put an empty dictionary.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putEmptyWithResponse(BinaryData complexBody, RequestOptions requestOptions) {
        return this.serviceClient.putEmptyWithResponse(complexBody, requestOptions);
    }

    /**
     * Get complex types with dictionary property which is null.
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     defaultProgram (Optional): {
     *         String: String (Required)
     *     }
     * }
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return complex types with dictionary property which is null along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getNullWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getNullWithResponse(requestOptions);
    }

    /**
     * Get complex types with dictionary property while server doesn't provide a response payload.
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     defaultProgram (Optional): {
     *         String: String (Required)
     *     }
     * }
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return complex types with dictionary property while server doesn't provide a response payload along with
     * {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getNotProvidedWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getNotProvidedWithResponse(requestOptions);
    }
}
