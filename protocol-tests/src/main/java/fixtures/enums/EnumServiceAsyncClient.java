// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.enums;

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
import fixtures.enums.implementation.EnumServiceClientImpl;
import reactor.core.publisher.Mono;

/** Initializes a new instance of the asynchronous EnumServiceClient type. */
@ServiceClient(builder = EnumServiceClientBuilder.class, isAsync = true)
public final class EnumServiceAsyncClient {
    @Generated private final EnumServiceClientImpl serviceClient;

    /**
     * Initializes an instance of EnumServiceAsyncClient class.
     *
     * @param serviceClient the service client implementation.
     */
    @Generated
    EnumServiceAsyncClient(EnumServiceClientImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * query enums operation.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>query-non-required-enum</td><td>String</td><td>No</td><td>non required enum with three values. Allowed values: "test1", "test2", "test@&lt;/spec.,i`~!&amp;*-al@char/&gt;".</td></tr>
     * </table>
     *
     * You can add these to a request with {@link RequestOptions#addQueryParam}
     *
     * @param queryIntegerEnum integer enum with three values. Allowed values: 100, 200, 300.
     * @param queryBooleanEnum boolean enum with two values. Allowed values: true, false.
     * @param queryRequiredEnum required enum with three values. Allowed values: "test1", "test2",
     *     "test@&lt;/spec.,i`~!&amp;*-al@char/&gt;".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putQueryEnumsWithResponse(
            String queryIntegerEnum, String queryBooleanEnum, String queryRequiredEnum, RequestOptions requestOptions) {
        return this.serviceClient.putQueryEnumsWithResponseAsync(
                queryIntegerEnum, queryBooleanEnum, queryRequiredEnum, requestOptions);
    }

    /**
     * enums header operation.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>header-non-required-string-enum</td><td>String</td><td>No</td><td>non required string enum with three values. Allowed values: "test1", "test2", "test@&lt;/spec.,i`~!&amp;*-al@char/&gt;".</td></tr>
     * </table>
     *
     * You can add these to a request with {@link RequestOptions#addHeader}
     *
     * @param headerRequiredStringEnum required string enum with three values. Allowed values: "test1", "test2",
     *     "test@&lt;/spec.,i`~!&amp;*-al@char/&gt;".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putHeaderEnumsWithResponse(
            String headerRequiredStringEnum, RequestOptions requestOptions) {
        return this.serviceClient.putHeaderEnumsWithResponseAsync(headerRequiredStringEnum, requestOptions);
    }

    /**
     * enums required body operation.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * String(test1/test2/test@</spec.,i`~!&*-al@char/>)
     * }</pre>
     *
     * @param bodyRequiredEnum body required enum.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putRequiredBodyEnumsWithResponse(
            BinaryData bodyRequiredEnum, RequestOptions requestOptions) {
        return this.serviceClient.putRequiredBodyEnumsWithResponseAsync(bodyRequiredEnum, requestOptions);
    }

    /**
     * enums non required body operation.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * String(test1/test2)
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putNonRequiredBodyEnumsWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.putNonRequiredBodyEnumsWithResponseAsync(requestOptions);
    }
}
