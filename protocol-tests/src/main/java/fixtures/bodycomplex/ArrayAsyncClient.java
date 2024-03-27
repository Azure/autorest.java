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
import fixtures.bodycomplex.implementation.ArraysImpl;
import reactor.core.publisher.Mono;

/**
 * Initializes a new instance of the asynchronous AutoRestComplexTestServiceClient type.
 */
@ServiceClient(builder = ArrayClientBuilder.class, isAsync = true)
public final class ArrayAsyncClient {
    @Generated
    private final ArraysImpl serviceClient;

    /**
     * Initializes an instance of ArrayAsyncClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
    ArrayAsyncClient(ArraysImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get complex types with array property.
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>
     * <code>
     * {
     *     array (Optional): [
     *         String (Optional)
     *     ]
     * }
     * </code>
     * </pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return complex types with array property along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getValidWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getValidWithResponseAsync(requestOptions);
    }

    /**
     * Put complex types with array property.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>
     * <code>
     * {
     *     array (Optional): [
     *         String (Optional)
     *     ]
     * }
     * </code>
     * </pre>
     * 
     * @param complexBody Please put an array with 4 items: "1, 2, 3, 4", "", null, "&amp;S#$(*Y", "The quick brown fox
     * jumps over the lazy dog".
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
     * Get complex types with array property which is empty.
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>
     * <code>
     * {
     *     array (Optional): [
     *         String (Optional)
     *     ]
     * }
     * </code>
     * </pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return complex types with array property which is empty along with {@link Response} on successful completion of
     * {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getEmptyWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getEmptyWithResponseAsync(requestOptions);
    }

    /**
     * Put complex types with array property which is empty.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>
     * <code>
     * {
     *     array (Optional): [
     *         String (Optional)
     *     ]
     * }
     * </code>
     * </pre>
     * 
     * @param complexBody Please put an empty array.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putEmptyWithResponse(BinaryData complexBody, RequestOptions requestOptions) {
        return this.serviceClient.putEmptyWithResponseAsync(complexBody, requestOptions);
    }

    /**
     * Get complex types with array property while server doesn't provide a response payload.
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>
     * <code>
     * {
     *     array (Optional): [
     *         String (Optional)
     *     ]
     * }
     * </code>
     * </pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return complex types with array property while server doesn't provide a response payload along with
     * {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getNotProvidedWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getNotProvidedWithResponseAsync(requestOptions);
    }
}
