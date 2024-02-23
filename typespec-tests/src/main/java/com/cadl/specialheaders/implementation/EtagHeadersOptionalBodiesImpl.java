// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.specialheaders.implementation;

import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
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
import com.azure.core.http.HttpHeaderName;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.BinaryData;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import com.cadl.specialheaders.SpecialHeadersServiceVersion;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in EtagHeadersOptionalBodies.
 */
public final class EtagHeadersOptionalBodiesImpl {
    /**
     * The proxy service used to perform REST calls.
     */
    private final EtagHeadersOptionalBodiesService service;

    /**
     * The service client containing this operation class.
     */
    private final SpecialHeadersClientImpl client;

    /**
     * Initializes an instance of EtagHeadersOptionalBodiesImpl.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    EtagHeadersOptionalBodiesImpl(SpecialHeadersClientImpl client) {
        this.service = RestProxy.create(EtagHeadersOptionalBodiesService.class, client.getHttpPipeline(),
            client.getSerializerAdapter());
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
     * The interface defining all the services for SpecialHeadersClientEtagHeadersOptionalBodies to be used by the proxy
     * service to perform REST calls.
     */
    @Host("{endpoint}")
    @ServiceInterface(name = "SpecialHeadersClient")
    public interface EtagHeadersOptionalBodiesService {
        @Put("/etag-headers-optional-body")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<BinaryData>> putWithOptionalBody(@HostParam("endpoint") String endpoint,
            @QueryParam("format") String format, @HeaderParam("accept") String accept, RequestOptions requestOptions,
            Context context);

        @Put("/etag-headers-optional-body")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<BinaryData> putWithOptionalBodySync(@HostParam("endpoint") String endpoint,
            @QueryParam("format") String format, @HeaderParam("accept") String accept, RequestOptions requestOptions,
            Context context);
    }

    /**
     * etag headers among other optional query/header/body parameters.
     * <p>
     * <strong>Query Parameters</strong>
     * </p>
     * <table border="1">
     * <caption>Query Parameters</caption>
     * <tr>
     * <th>Name</th>
     * <th>Type</th>
     * <th>Required</th>
     * <th>Description</th>
     * </tr>
     * <tr>
     * <td>filter</td>
     * <td>String</td>
     * <td>No</td>
     * <td>A sequence of textual characters.</td>
     * </tr>
     * </table>
     * You can add these to a request with {@link RequestOptions#addQueryParam}
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
     * <tr>
     * <td>timestamp</td>
     * <td>OffsetDateTime</td>
     * <td>No</td>
     * <td>An instant in coordinated universal time (UTC)"</td>
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
     * @param format A sequence of textual characters.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> putWithOptionalBodyWithResponseAsync(String format,
        RequestOptions requestOptions) {
        final String accept = "application/json";
        RequestOptions requestOptionsLocal = requestOptions == null ? new RequestOptions() : requestOptions;
        requestOptionsLocal.addRequestCallback(requestLocal -> {
            if (requestLocal.getBody() != null && requestLocal.getHeaders().get(HttpHeaderName.CONTENT_TYPE) == null) {
                requestLocal.getHeaders().set(HttpHeaderName.CONTENT_TYPE, "application/json");
            }
        });
        return FluxUtil.withContext(context -> service.putWithOptionalBody(this.client.getEndpoint(), format, accept,
            requestOptionsLocal, context));
    }

    /**
     * etag headers among other optional query/header/body parameters.
     * <p>
     * <strong>Query Parameters</strong>
     * </p>
     * <table border="1">
     * <caption>Query Parameters</caption>
     * <tr>
     * <th>Name</th>
     * <th>Type</th>
     * <th>Required</th>
     * <th>Description</th>
     * </tr>
     * <tr>
     * <td>filter</td>
     * <td>String</td>
     * <td>No</td>
     * <td>A sequence of textual characters.</td>
     * </tr>
     * </table>
     * You can add these to a request with {@link RequestOptions#addQueryParam}
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
     * <tr>
     * <td>timestamp</td>
     * <td>OffsetDateTime</td>
     * <td>No</td>
     * <td>An instant in coordinated universal time (UTC)"</td>
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
     * @param format A sequence of textual characters.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> putWithOptionalBodyWithResponse(String format, RequestOptions requestOptions) {
        final String accept = "application/json";
        RequestOptions requestOptionsLocal = requestOptions == null ? new RequestOptions() : requestOptions;
        requestOptionsLocal.addRequestCallback(requestLocal -> {
            if (requestLocal.getBody() != null && requestLocal.getHeaders().get(HttpHeaderName.CONTENT_TYPE) == null) {
                requestLocal.getHeaders().set(HttpHeaderName.CONTENT_TYPE, "application/json");
            }
        });
        return service.putWithOptionalBodySync(this.client.getEndpoint(), format, accept, requestOptionsLocal,
            Context.NONE);
    }
}
