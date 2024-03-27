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
import fixtures.bodycomplex.implementation.BasicsImpl;
import reactor.core.publisher.Mono;

/**
 * Initializes a new instance of the asynchronous AutoRestComplexTestServiceClient type.
 */
@ServiceClient(builder = BasicClientBuilder.class, isAsync = true)
public final class BasicAsyncClient {
    @Generated
    private final BasicsImpl serviceClient;

    /**
     * Initializes an instance of BasicAsyncClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
    BasicAsyncClient(BasicsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get complex type {id: 2, name: 'abc', color: 'YELLOW'}.
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>
     * <code>
     * {
     *     id: Integer (Optional)
     *     name: String (Optional)
     *     color: String(cyan/Magenta/YELLOW/blacK) (Optional)
     * }
     * </code>
     * </pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return complex type {id: 2, name: 'abc', color: 'YELLOW'} along with {@link Response} on successful completion
     * of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getValidWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getValidWithResponseAsync(requestOptions);
    }

    /**
     * Please put {id: 2, name: 'abc', color: 'Magenta'}.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>
     * <code>
     * {
     *     id: Integer (Optional)
     *     name: String (Optional)
     *     color: String(cyan/Magenta/YELLOW/blacK) (Optional)
     * }
     * </code>
     * </pre>
     * 
     * @param complexBody Please put {id: 2, name: 'abc', color: 'Magenta'}.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putValidWithResponse(BinaryData complexBody, RequestOptions requestOptions) {
        return this.serviceClient.putValidWithResponseAsync(complexBody, requestOptions);
    }

    /**
     * Get a basic complex type that is invalid for the local strong type.
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>
     * <code>
     * {
     *     id: Integer (Optional)
     *     name: String (Optional)
     *     color: String(cyan/Magenta/YELLOW/blacK) (Optional)
     * }
     * </code>
     * </pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return a basic complex type that is invalid for the local strong type along with {@link Response} on successful
     * completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getInvalidWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getInvalidWithResponseAsync(requestOptions);
    }

    /**
     * Get a basic complex type that is empty.
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>
     * <code>
     * {
     *     id: Integer (Optional)
     *     name: String (Optional)
     *     color: String(cyan/Magenta/YELLOW/blacK) (Optional)
     * }
     * </code>
     * </pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return a basic complex type that is empty along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getEmptyWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getEmptyWithResponseAsync(requestOptions);
    }

    /**
     * Get a basic complex type whose properties are null.
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>
     * <code>
     * {
     *     id: Integer (Optional)
     *     name: String (Optional)
     *     color: String(cyan/Magenta/YELLOW/blacK) (Optional)
     * }
     * </code>
     * </pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return a basic complex type whose properties are null along with {@link Response} on successful completion of
     * {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getNullWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getNullWithResponseAsync(requestOptions);
    }

    /**
     * Get a basic complex type while the server doesn't provide a response payload.
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>
     * <code>
     * {
     *     id: Integer (Optional)
     *     name: String (Optional)
     *     color: String(cyan/Magenta/YELLOW/blacK) (Optional)
     * }
     * </code>
     * </pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return a basic complex type while the server doesn't provide a response payload along with {@link Response} on
     * successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getNotProvidedWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getNotProvidedWithResponseAsync(requestOptions);
    }
}
