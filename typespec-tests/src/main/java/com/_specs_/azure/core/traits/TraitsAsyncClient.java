// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com._specs_.azure.core.traits;

import com._specs_.azure.core.traits.implementation.TraitsClientImpl;
import com._specs_.azure.core.traits.models.User;
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
import com.azure.core.util.DateTimeRfc1123;
import com.azure.core.util.FluxUtil;
import java.time.OffsetDateTime;
import reactor.core.publisher.Mono;

/** Initializes a new instance of the asynchronous TraitsClient type. */
@ServiceClient(builder = TraitsClientBuilder.class, isAsync = true)
public final class TraitsAsyncClient {
    @Generated private final TraitsClientImpl serviceClient;

    /**
     * Initializes an instance of TraitsAsyncClient class.
     *
     * @param serviceClient the service client implementation.
     */
    @Generated
    TraitsAsyncClient(TraitsClientImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get a resource, sending and receiving headers.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>If-Match</td><td>String</td><td>No</td><td>The request should only proceed if an entity matches this string.</td></tr>
     *     <tr><td>If-None-Match</td><td>String</td><td>No</td><td>The request should only proceed if no entity matches this string.</td></tr>
     *     <tr><td>If-Unmodified-Since</td><td>OffsetDateTime</td><td>No</td><td>The request should only proceed if the entity was not modified after this time.</td></tr>
     *     <tr><td>If-Modified-Since</td><td>OffsetDateTime</td><td>No</td><td>The request should only proceed if the entity was modified after this time.</td></tr>
     * </table>
     *
     * You can add these to a request with {@link RequestOptions#addHeader}
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: int (Required)
     *     name: String (Optional)
     * }
     * }</pre>
     *
     * @param id The user's id.
     * @param foo header in request.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return a resource, sending and receiving headers along with {@link Response} on successful completion of {@link
     *     Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> smokeTestWithResponse(int id, String foo, RequestOptions requestOptions) {
        return this.serviceClient.smokeTestWithResponseAsync(id, foo, requestOptions);
    }

    /**
     * Get a resource, sending and receiving headers.
     *
     * @param id The user's id.
     * @param foo header in request.
     * @param ifMatch The request should only proceed if an entity matches this string.
     * @param ifNoneMatch The request should only proceed if no entity matches this string.
     * @param ifUnmodifiedSince The request should only proceed if the entity was not modified after this time.
     * @param ifModifiedSince The request should only proceed if the entity was modified after this time.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a resource, sending and receiving headers on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<User> smokeTest(
            int id,
            String foo,
            String ifMatch,
            String ifNoneMatch,
            OffsetDateTime ifUnmodifiedSince,
            OffsetDateTime ifModifiedSince) {
        // Generated convenience method for smokeTestWithResponse
        RequestOptions requestOptions = new RequestOptions();
        if (ifMatch != null) {
            requestOptions.setHeader("If-Match", ifMatch);
        }
        if (ifNoneMatch != null) {
            requestOptions.setHeader("If-None-Match", ifNoneMatch);
        }
        if (ifUnmodifiedSince != null) {
            requestOptions.setHeader("If-Unmodified-Since", String.valueOf(new DateTimeRfc1123(ifUnmodifiedSince)));
        }
        if (ifModifiedSince != null) {
            requestOptions.setHeader("If-Modified-Since", String.valueOf(new DateTimeRfc1123(ifModifiedSince)));
        }
        return smokeTestWithResponse(id, foo, requestOptions)
                .flatMap(FluxUtil::toMono)
                .map(protocolMethodData -> protocolMethodData.toObject(User.class));
    }

    /**
     * Get a resource, sending and receiving headers.
     *
     * @param id The user's id.
     * @param foo header in request.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a resource, sending and receiving headers on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<User> smokeTest(int id, String foo) {
        // Generated convenience method for smokeTestWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return smokeTestWithResponse(id, foo, requestOptions)
                .flatMap(FluxUtil::toMono)
                .map(protocolMethodData -> protocolMethodData.toObject(User.class));
    }
}
