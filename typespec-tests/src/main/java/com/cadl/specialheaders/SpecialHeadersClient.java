// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.specialheaders;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.exception.ClientAuthenticationException;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.exception.ResourceModifiedException;
import com.azure.core.exception.ResourceNotFoundException;
import com.azure.core.http.HttpHeaderName;
import com.azure.core.http.MatchConditions;
import com.azure.core.http.RequestConditions;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import com.azure.core.util.DateTimeRfc1123;
import com.azure.core.util.polling.SyncPoller;
import com.cadl.specialheaders.implementation.SpecialHeadersClientImpl;
import com.cadl.specialheaders.models.Resource;
import java.time.OffsetDateTime;

/** Initializes a new instance of the synchronous SpecialHeadersClient type. */
@ServiceClient(builder = SpecialHeadersClientBuilder.class)
public final class SpecialHeadersClient {
    @Generated private final SpecialHeadersClientImpl serviceClient;

    /**
     * Initializes an instance of SpecialHeadersClient class.
     *
     * @param serviceClient the service client implementation.
     */
    @Generated
    SpecialHeadersClient(SpecialHeadersClientImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Resource read operation template.
     *
     * <p><strong>Response Body Schema</strong>
     *
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
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getWithResponse(String name, RequestOptions requestOptions) {
        return this.serviceClient.getWithResponse(name, requestOptions);
    }

    /**
     * Send a put request with header Repeatability-Request-ID and Repeatability-First-Sent.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>repeatability-request-id</td><td>String</td><td>No</td><td>Repeatability request ID header</td></tr>
     *     <tr><td>repeatability-first-sent</td><td>String</td><td>No</td><td>Repeatability first sent header as HTTP-date</td></tr>
     * </table>
     *
     * You can add these to a request with {@link RequestOptions#addHeader}
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     *     description: String (Optional)
     *     type: String (Required)
     * }
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
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
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> putWithResponse(String name, BinaryData resource, RequestOptions requestOptions) {
        return this.serviceClient.putWithResponse(name, resource, requestOptions);
    }

    /**
     * Send a post request with header Repeatability-Request-ID and Repeatability-First-Sent.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>repeatability-request-id</td><td>String</td><td>No</td><td>Repeatability request ID header</td></tr>
     *     <tr><td>repeatability-first-sent</td><td>String</td><td>No</td><td>Repeatability first sent header as HTTP-date</td></tr>
     * </table>
     *
     * You can add these to a request with {@link RequestOptions#addHeader}
     *
     * <p><strong>Response Body Schema</strong>
     *
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
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> postWithResponse(String name, RequestOptions requestOptions) {
        return this.serviceClient.postWithResponse(name, requestOptions);
    }

    /**
     * Send a LRO request with header Repeatability-Request-ID and Repeatability-First-Sent.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>repeatability-request-id</td><td>String</td><td>No</td><td>Repeatability request ID header</td></tr>
     *     <tr><td>repeatability-first-sent</td><td>String</td><td>No</td><td>Repeatability first sent header as HTTP-date</td></tr>
     * </table>
     *
     * You can add these to a request with {@link RequestOptions#addHeader}
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     *     description: String (Optional)
     *     type: String (Required)
     * }
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
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
     * @return the {@link SyncPoller} for polling of long-running operation.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginCreateLro(
            String name, BinaryData resource, RequestOptions requestOptions) {
        // Convenience API is not generated, as operation 'createLro' is 'application/merge-patch+json'
        return this.serviceClient.beginCreateLro(name, resource, requestOptions);
    }

    /**
     * Create or replace operation template.
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
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     *     description: String (Optional)
     *     type: String (Required)
     * }
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
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
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> putWithRequestHeadersWithResponse(
            String name, BinaryData resource, RequestOptions requestOptions) {
        return this.serviceClient.putWithRequestHeadersWithResponse(name, resource, requestOptions);
    }

    /**
     * Create or replace operation template.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>If-Match</td><td>String</td><td>No</td><td>The request should only proceed if an entity matches this string.</td></tr>
     *     <tr><td>If-None-Match</td><td>String</td><td>No</td><td>The request should only proceed if no entity matches this string.</td></tr>
     * </table>
     *
     * You can add these to a request with {@link RequestOptions#addHeader}
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     *     description: String (Optional)
     *     type: String (Required)
     * }
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
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
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> patchWithMatchHeadersWithResponse(
            String name, BinaryData resource, RequestOptions requestOptions) {
        return this.serviceClient.patchWithMatchHeadersWithResponse(name, resource, requestOptions);
    }

    /**
     * etag headers among other optional query/header/body parameters.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>filter</td><td>String</td><td>No</td><td>A sequence of textual characters.</td></tr>
     * </table>
     *
     * You can add these to a request with {@link RequestOptions#addQueryParam}
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
     *     <tr><td>timestamp</td><td>OffsetDateTime</td><td>No</td><td>An instant in coordinated universal time (UTC)"</td></tr>
     * </table>
     *
     * You can add these to a request with {@link RequestOptions#addHeader}
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     *     description: String (Optional)
     *     type: String (Required)
     * }
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
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
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> putWithOptionalBodyWithResponse(String format, RequestOptions requestOptions) {
        return this.serviceClient.putWithOptionalBodyWithResponse(format, requestOptions);
    }

    /**
     * skip special headers.
     *
     * @param name A sequence of textual characters.
     * @param foo A sequence of textual characters.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> deleteWithSpecialHeadersWithResponse(String name, String foo, RequestOptions requestOptions) {
        return this.serviceClient.deleteWithSpecialHeadersWithResponse(name, foo, requestOptions);
    }

    /**
     * Resource read operation template.
     *
     * @param name A sequence of textual characters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Resource get(String name) {
        // Generated convenience method for getWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return getWithResponse(name, requestOptions).getValue().toObject(Resource.class);
    }

    /**
     * Send a put request with header Repeatability-Request-ID and Repeatability-First-Sent.
     *
     * @param name A sequence of textual characters.
     * @param resource The resource instance.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Resource put(String name, Resource resource) {
        // Generated convenience method for putWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return putWithResponse(name, BinaryData.fromObject(resource), requestOptions)
                .getValue()
                .toObject(Resource.class);
    }

    /**
     * Send a post request with header Repeatability-Request-ID and Repeatability-First-Sent.
     *
     * @param name A sequence of textual characters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Resource post(String name) {
        // Generated convenience method for postWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return postWithResponse(name, requestOptions).getValue().toObject(Resource.class);
    }

    /**
     * Create or replace operation template.
     *
     * @param name A sequence of textual characters.
     * @param resource The resource instance.
     * @param requestConditions Specifies HTTP options for conditional requests based on modification time.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Resource putWithRequestHeaders(String name, Resource resource, RequestConditions requestConditions) {
        // Generated convenience method for putWithRequestHeadersWithResponse
        RequestOptions requestOptions = new RequestOptions();
        String ifMatch = requestConditions == null ? null : requestConditions.getIfMatch();
        String ifNoneMatch = requestConditions == null ? null : requestConditions.getIfNoneMatch();
        OffsetDateTime ifUnmodifiedSince = requestConditions == null ? null : requestConditions.getIfUnmodifiedSince();
        OffsetDateTime ifModifiedSince = requestConditions == null ? null : requestConditions.getIfModifiedSince();
        if (ifMatch != null) {
            requestOptions.setHeader(HttpHeaderName.IF_MATCH, ifMatch);
        }
        if (ifNoneMatch != null) {
            requestOptions.setHeader(HttpHeaderName.IF_NONE_MATCH, ifNoneMatch);
        }
        if (ifUnmodifiedSince != null) {
            requestOptions.setHeader(
                    HttpHeaderName.IF_UNMODIFIED_SINCE, String.valueOf(new DateTimeRfc1123(ifUnmodifiedSince)));
        }
        if (ifModifiedSince != null) {
            requestOptions.setHeader(
                    HttpHeaderName.IF_MODIFIED_SINCE, String.valueOf(new DateTimeRfc1123(ifModifiedSince)));
        }
        return putWithRequestHeadersWithResponse(name, BinaryData.fromObject(resource), requestOptions)
                .getValue()
                .toObject(Resource.class);
    }

    /**
     * Create or replace operation template.
     *
     * @param name A sequence of textual characters.
     * @param resource The resource instance.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Resource putWithRequestHeaders(String name, Resource resource) {
        // Generated convenience method for putWithRequestHeadersWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return putWithRequestHeadersWithResponse(name, BinaryData.fromObject(resource), requestOptions)
                .getValue()
                .toObject(Resource.class);
    }

    /**
     * Create or replace operation template.
     *
     * @param name A sequence of textual characters.
     * @param resource The resource instance.
     * @param matchConditions Specifies HTTP options for conditional requests.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Resource patchWithMatchHeaders(String name, Resource resource, MatchConditions matchConditions) {
        // Generated convenience method for patchWithMatchHeadersWithResponse
        RequestOptions requestOptions = new RequestOptions();
        String ifMatch = matchConditions == null ? null : matchConditions.getIfMatch();
        String ifNoneMatch = matchConditions == null ? null : matchConditions.getIfNoneMatch();
        if (ifMatch != null) {
            requestOptions.setHeader(HttpHeaderName.IF_MATCH, ifMatch);
        }
        if (ifNoneMatch != null) {
            requestOptions.setHeader(HttpHeaderName.IF_NONE_MATCH, ifNoneMatch);
        }
        return patchWithMatchHeadersWithResponse(name, BinaryData.fromObject(resource), requestOptions)
                .getValue()
                .toObject(Resource.class);
    }

    /**
     * Create or replace operation template.
     *
     * @param name A sequence of textual characters.
     * @param resource The resource instance.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Resource patchWithMatchHeaders(String name, Resource resource) {
        // Generated convenience method for patchWithMatchHeadersWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return patchWithMatchHeadersWithResponse(name, BinaryData.fromObject(resource), requestOptions)
                .getValue()
                .toObject(Resource.class);
    }

    /**
     * etag headers among other optional query/header/body parameters.
     *
     * @param format A sequence of textual characters.
     * @param filter A sequence of textual characters.
     * @param timestamp An instant in coordinated universal time (UTC)".
     * @param body The body parameter.
     * @param requestConditions Specifies HTTP options for conditional requests based on modification time.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Resource putWithOptionalBody(
            String format,
            String filter,
            OffsetDateTime timestamp,
            Resource body,
            RequestConditions requestConditions) {
        // Generated convenience method for putWithOptionalBodyWithResponse
        RequestOptions requestOptions = new RequestOptions();
        String ifMatch = requestConditions == null ? null : requestConditions.getIfMatch();
        String ifNoneMatch = requestConditions == null ? null : requestConditions.getIfNoneMatch();
        OffsetDateTime ifUnmodifiedSince = requestConditions == null ? null : requestConditions.getIfUnmodifiedSince();
        OffsetDateTime ifModifiedSince = requestConditions == null ? null : requestConditions.getIfModifiedSince();
        if (filter != null) {
            requestOptions.addQueryParam("filter", filter, false);
        }
        if (timestamp != null) {
            requestOptions.setHeader(HttpHeaderName.fromString("timestamp"), String.valueOf(timestamp.toEpochSecond()));
        }
        if (body != null) {
            requestOptions.setBody(BinaryData.fromObject(body));
        }
        if (ifMatch != null) {
            requestOptions.setHeader(HttpHeaderName.IF_MATCH, ifMatch);
        }
        if (ifNoneMatch != null) {
            requestOptions.setHeader(HttpHeaderName.IF_NONE_MATCH, ifNoneMatch);
        }
        if (ifUnmodifiedSince != null) {
            requestOptions.setHeader(
                    HttpHeaderName.IF_UNMODIFIED_SINCE, String.valueOf(new DateTimeRfc1123(ifUnmodifiedSince)));
        }
        if (ifModifiedSince != null) {
            requestOptions.setHeader(
                    HttpHeaderName.IF_MODIFIED_SINCE, String.valueOf(new DateTimeRfc1123(ifModifiedSince)));
        }
        return putWithOptionalBodyWithResponse(format, requestOptions).getValue().toObject(Resource.class);
    }

    /**
     * etag headers among other optional query/header/body parameters.
     *
     * @param format A sequence of textual characters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Resource putWithOptionalBody(String format) {
        // Generated convenience method for putWithOptionalBodyWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return putWithOptionalBodyWithResponse(format, requestOptions).getValue().toObject(Resource.class);
    }

    /**
     * skip special headers.
     *
     * @param name A sequence of textual characters.
     * @param foo A sequence of textual characters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void deleteWithSpecialHeaders(String name, String foo) {
        // Generated convenience method for deleteWithSpecialHeadersWithResponse
        RequestOptions requestOptions = new RequestOptions();
        deleteWithSpecialHeadersWithResponse(name, foo, requestOptions).getValue();
    }
}
