// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.cadl.optional;

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
import com.cadl.optional.implementation.OptionalOpsImpl;
import reactor.core.publisher.Mono;

/** Initializes a new instance of the asynchronous OptionalClient type. */
@ServiceClient(builder = OptionalClientBuilder.class, isAsync = true)
public final class OptionalAsyncClient {
    @Generated private final OptionalOpsImpl serviceClient;

    /**
     * Initializes an instance of OptionalAsyncClient class.
     *
     * @param serviceClient the service client implementation.
     */
    @Generated
    OptionalAsyncClient(OptionalOpsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * The put operation.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>booleanNullable</td><td>Boolean</td><td>No</td><td>The booleanNullable parameter</td></tr>
     *     <tr><td>string</td><td>String</td><td>No</td><td>The string parameter</td></tr>
     *     <tr><td>stringNullable</td><td>String</td><td>No</td><td>The stringNullable parameter</td></tr>
     * </table>
     *
     * You can add these to a request with {@link RequestOptions#addQueryParam}
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>request-header-optional</td><td>String</td><td>No</td><td>The requestHeaderOptional parameter</td></tr>
     * </table>
     *
     * You can add these to a request with {@link RequestOptions#addHeader}
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     boolean: Boolean (Optional)
     *     booleanNullable: Boolean (Optional)
     *     booleanRequired: boolean (Required)
     *     booleanRequiredNullable: Boolean (Required)
     *     string: String (Optional)
     *     stringNullable: String (Optional)
     *     stringRequired: String (Required)
     *     stringRequiredNullable: String (Required)
     *     bytes: byte[] (Optional)
     *     int: Long (Optional)
     *     long: Long (Optional)
     *     float: Double (Optional)
     *     double: Double (Optional)
     *     duration: Duration (Optional)
     *     dateTime: OffsetDateTime (Optional)
     *     stringList (Optional): [
     *         String (Optional)
     *     ]
     *     bytesDict (Optional): {
     *         String: byte[] (Optional)
     *     }
     * }
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     boolean: Boolean (Optional)
     *     booleanNullable: Boolean (Optional)
     *     booleanRequired: Boolean (Optional)
     *     booleanRequiredNullable: Boolean (Optional)
     *     string: String (Optional)
     *     stringNullable: String (Optional)
     *     stringRequired: String (Optional)
     *     stringRequiredNullable: String (Optional)
     *     bytes: byte[] (Optional)
     *     int: Long (Optional)
     *     long: Long (Optional)
     *     float: Double (Optional)
     *     double: Double (Optional)
     *     duration: Duration (Optional)
     *     dateTime: OffsetDateTime (Optional)
     *     stringList (Optional): [
     *         String (Optional)
     *     ]
     *     bytesDict (Optional): {
     *         String: byte[] (Optional)
     *     }
     * }
     * }</pre>
     *
     * @param requestHeaderRequired The requestHeaderRequired parameter.
     * @param booleanRequired The booleanRequired parameter.
     * @param booleanRequiredNullable The booleanRequiredNullable parameter.
     * @param stringRequired The stringRequired parameter.
     * @param stringRequiredNullable The stringRequiredNullable parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> putWithResponse(
            String requestHeaderRequired,
            boolean booleanRequired,
            Boolean booleanRequiredNullable,
            String stringRequired,
            String stringRequiredNullable,
            RequestOptions requestOptions) {
        return this.serviceClient.putWithResponseAsync(
                requestHeaderRequired,
                booleanRequired,
                booleanRequiredNullable,
                stringRequired,
                stringRequiredNullable,
                requestOptions);
    }
}
