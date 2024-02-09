// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.specialheaders.implementation;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.Patch;
import com.azure.core.annotation.PathParam;
import com.azure.core.annotation.Put;
import com.azure.core.annotation.QueryParam;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.exception.ClientAuthenticationException;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.exception.ResourceModifiedException;
import com.azure.core.exception.ResourceNotFoundException;

import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;

import com.azure.core.util.BinaryData;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;

import com.cadl.specialheaders.SpecialHeadersServiceVersion;

import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in EtagHeaders.
 */
public final class EtagHeadersImpl {
    /**
     * The proxy service used to perform REST calls.
     */
    private final EtagHeadersService service;

    /**
     * The service client containing this operation class.
     */
    private final SpecialHeadersClientImpl client;

    /**
     * Initializes an instance of EtagHeadersImpl.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    EtagHeadersImpl(SpecialHeadersClientImpl client) {
        this.service
            = RestProxy.create(EtagHeadersService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * Gets Service version.
     * 
     * @return the serviceVersion value.
     */
    public SpecialHeadersServiceVersion getServiceVersion() {
        return client.getServiceVersion();
    }

    /**
     * The interface defining all the services for SpecialHeadersClientEtagHeaders to be used by the proxy service to
     * perform REST calls.
     */
    @Host("{endpoint}")
    @ServiceInterface(name = "SpecialHeadersClient")
    public interface EtagHeadersService {
        @Put("/etag-headers/resources/{name}")
        @ExpectedResponses({ 200, 201 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<BinaryData>> putWithRequestHeaders(@HostParam("endpoint") String endpoint,
            @QueryParam("api-version") String apiVersion, @PathParam("name") String name,
            @HeaderParam("accept") String accept, @BodyParam("application/json") BinaryData resource,
            RequestOptions requestOptions, Context context);

        @Put("/etag-headers/resources/{name}")
        @ExpectedResponses({ 200, 201 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<BinaryData> putWithRequestHeadersSync(@HostParam("endpoint") String endpoint,
            @QueryParam("api-version") String apiVersion, @PathParam("name") String name,
            @HeaderParam("accept") String accept, @BodyParam("application/json") BinaryData resource,
            RequestOptions requestOptions, Context context);

        @Patch("/etag-headers/resources/{name}")
        @ExpectedResponses({ 200, 201 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<BinaryData>> patchWithMatchHeaders(@HostParam("endpoint") String endpoint,
            @QueryParam("api-version") String apiVersion, @PathParam("name") String name,
            @HeaderParam("accept") String accept, @BodyParam("application/json") BinaryData resource,
            RequestOptions requestOptions, Context context);

        @Patch("/etag-headers/resources/{name}")
        @ExpectedResponses({ 200, 201 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<BinaryData> patchWithMatchHeadersSync(@HostParam("endpoint") String endpoint,
            @QueryParam("api-version") String apiVersion, @PathParam("name") String name,
            @HeaderParam("accept") String accept, @BodyParam("application/json") BinaryData resource,
            RequestOptions requestOptions, Context context);
    }

    /**
     * Create or replace operation template.
     * <p>
     * <strong>Header Parameters</strong>
     * </p>
     * <table border="1">
     * <caption>Header Parameters</caption>
     * <tr>
     * <th>Name</th>
     * <th>Type</th>
     * <th>Required</th>
     * <th>Description</th>
     * </tr>
     * <tr>
     * <td>If-Match</td>
     * <td>String</td>
     * <td>No</td>
     * <td>The request should only proceed if an entity matches this string.</td>
     * </tr>
     * <tr>
     * <td>If-None-Match</td>
     * <td>String</td>
     * <td>No</td>
     * <td>The request should only proceed if no entity matches this string.</td>
     * </tr>
     * <tr>
     * <td>If-Unmodified-Since</td>
     * <td>OffsetDateTime</td>
     * <td>No</td>
     * <td>The request should only proceed if the entity was not modified after this time.</td>
     * </tr>
     * <tr>
     * <td>If-Modified-Since</td>
     * <td>OffsetDateTime</td>
     * <td>No</td>
     * <td>The request should only proceed if the entity was modified after this time.</td>
     * </tr>
     * </table>
     * You can add these to a request with {@link RequestOptions#addHeader}
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     *     description: String (Optional)
     *     type: String (Required)
     * }
     * }</pre>
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     *     description: String (Optional)
     *     type: String (Required)
     * }
     * }</pre>
     * 
     * @param name A sequence of textual characters.
     * @param resource The resource instance.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> putWithRequestHeadersWithResponseAsync(String name, BinaryData resource,
        RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.putWithRequestHeaders(this.client.getEndpoint(),
            this.client.getServiceVersion().getVersion(), name, accept, resource, requestOptions, context));
    }

    /**
     * Create or replace operation template.
     * <p>
     * <strong>Header Parameters</strong>
     * </p>
     * <table border="1">
     * <caption>Header Parameters</caption>
     * <tr>
     * <th>Name</th>
     * <th>Type</th>
     * <th>Required</th>
     * <th>Description</th>
     * </tr>
     * <tr>
     * <td>If-Match</td>
     * <td>String</td>
     * <td>No</td>
     * <td>The request should only proceed if an entity matches this string.</td>
     * </tr>
     * <tr>
     * <td>If-None-Match</td>
     * <td>String</td>
     * <td>No</td>
     * <td>The request should only proceed if no entity matches this string.</td>
     * </tr>
     * <tr>
     * <td>If-Unmodified-Since</td>
     * <td>OffsetDateTime</td>
     * <td>No</td>
     * <td>The request should only proceed if the entity was not modified after this time.</td>
     * </tr>
     * <tr>
     * <td>If-Modified-Since</td>
     * <td>OffsetDateTime</td>
     * <td>No</td>
     * <td>The request should only proceed if the entity was modified after this time.</td>
     * </tr>
     * </table>
     * You can add these to a request with {@link RequestOptions#addHeader}
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     *     description: String (Optional)
     *     type: String (Required)
     * }
     * }</pre>
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     *     description: String (Optional)
     *     type: String (Required)
     * }
     * }</pre>
     * 
     * @param name A sequence of textual characters.
     * @param resource The resource instance.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> putWithRequestHeadersWithResponse(String name, BinaryData resource,
        RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.putWithRequestHeadersSync(this.client.getEndpoint(),
            this.client.getServiceVersion().getVersion(), name, accept, resource, requestOptions, Context.NONE);
    }

    /**
     * Create or replace operation template.
     * <p>
     * <strong>Header Parameters</strong>
     * </p>
     * <table border="1">
     * <caption>Header Parameters</caption>
     * <tr>
     * <th>Name</th>
     * <th>Type</th>
     * <th>Required</th>
     * <th>Description</th>
     * </tr>
     * <tr>
     * <td>If-Match</td>
     * <td>String</td>
     * <td>No</td>
     * <td>The request should only proceed if an entity matches this string.</td>
     * </tr>
     * <tr>
     * <td>If-None-Match</td>
     * <td>String</td>
     * <td>No</td>
     * <td>The request should only proceed if no entity matches this string.</td>
     * </tr>
     * </table>
     * You can add these to a request with {@link RequestOptions#addHeader}
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     *     description: String (Optional)
     *     type: String (Required)
     * }
     * }</pre>
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     *     description: String (Optional)
     *     type: String (Required)
     * }
     * }</pre>
     * 
     * @param name A sequence of textual characters.
     * @param resource The resource instance.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> patchWithMatchHeadersWithResponseAsync(String name, BinaryData resource,
        RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.patchWithMatchHeaders(this.client.getEndpoint(),
            this.client.getServiceVersion().getVersion(), name, accept, resource, requestOptions, context));
    }

    /**
     * Create or replace operation template.
     * <p>
     * <strong>Header Parameters</strong>
     * </p>
     * <table border="1">
     * <caption>Header Parameters</caption>
     * <tr>
     * <th>Name</th>
     * <th>Type</th>
     * <th>Required</th>
     * <th>Description</th>
     * </tr>
     * <tr>
     * <td>If-Match</td>
     * <td>String</td>
     * <td>No</td>
     * <td>The request should only proceed if an entity matches this string.</td>
     * </tr>
     * <tr>
     * <td>If-None-Match</td>
     * <td>String</td>
     * <td>No</td>
     * <td>The request should only proceed if no entity matches this string.</td>
     * </tr>
     * </table>
     * You can add these to a request with {@link RequestOptions#addHeader}
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     *     description: String (Optional)
     *     type: String (Required)
     * }
     * }</pre>
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     *     description: String (Optional)
     *     type: String (Required)
     * }
     * }</pre>
     * 
     * @param name A sequence of textual characters.
     * @param resource The resource instance.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> patchWithMatchHeadersWithResponse(String name, BinaryData resource,
        RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.patchWithMatchHeadersSync(this.client.getEndpoint(),
            this.client.getServiceVersion().getVersion(), name, accept, resource, requestOptions, Context.NONE);
    }
}
