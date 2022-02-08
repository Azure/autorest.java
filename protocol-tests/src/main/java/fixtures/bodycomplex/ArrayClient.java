// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import fixtures.bodycomplex.implementation.ArraysImpl;

/** Initializes a new instance of the synchronous AutoRestComplexTestServiceClient type. */
@ServiceClient(builder = ArrayClientBuilder.class)
public final class ArrayClient {
    @Generated private final ArraysImpl serviceClient;

    /**
     * Initializes an instance of Arrays client.
     *
     * @param serviceClient the service client implementation.
     */
    @Generated
    ArrayClient(ArraysImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get complex types with array property.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     array: [
     *         String
     *     ]
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return complex types with array property along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getValidWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getValidWithResponse(requestOptions);
    }

    /**
     * Put complex types with array property.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     array: [
     *         String
     *     ]
     * }
     * }</pre>
     *
     * @param complexBody Please put an array with 4 items: "1, 2, 3, 4", "", null, "&amp;amp;S#$(*Y", "The quick brown
     *     fox jumps over the lazy dog".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putValidWithResponse(BinaryData complexBody, RequestOptions requestOptions) {
        return this.serviceClient.putValidWithResponse(complexBody, requestOptions);
    }

    /**
     * Get complex types with array property which is empty.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     array: [
     *         String
     *     ]
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return complex types with array property which is empty along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getEmptyWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getEmptyWithResponse(requestOptions);
    }

    /**
     * Put complex types with array property which is empty.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     array: [
     *         String
     *     ]
     * }
     * }</pre>
     *
     * @param complexBody Please put an empty array.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putEmptyWithResponse(BinaryData complexBody, RequestOptions requestOptions) {
        return this.serviceClient.putEmptyWithResponse(complexBody, requestOptions);
    }

    /**
     * Get complex types with array property while server doesn't provide a response payload.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     array: [
     *         String
     *     ]
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return complex types with array property while server doesn't provide a response payload along with {@link
     *     Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getNotProvidedWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getNotProvidedWithResponse(requestOptions);
    }
}
