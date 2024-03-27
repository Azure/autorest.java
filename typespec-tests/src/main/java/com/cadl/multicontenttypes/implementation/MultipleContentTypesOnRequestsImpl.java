// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.multicontenttypes.implementation;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.Post;
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
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in MultipleContentTypesOnRequests.
 */
public final class MultipleContentTypesOnRequestsImpl {
    /**
     * The proxy service used to perform REST calls.
     */
    private final MultipleContentTypesOnRequestsService service;

    /**
     * The service client containing this operation class.
     */
    private final MultiContentTypesClientImpl client;

    /**
     * Initializes an instance of MultipleContentTypesOnRequestsImpl.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    MultipleContentTypesOnRequestsImpl(MultiContentTypesClientImpl client) {
        this.service = RestProxy.create(MultipleContentTypesOnRequestsService.class, client.getHttpPipeline(),
            client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for MultiContentTypesClientMultipleContentTypesOnRequests to be used by
     * the proxy service to perform REST calls.
     */
    @Host("{endpoint}")
    @ServiceInterface(name = "MultiContentTypesCli")
    public interface MultipleContentTypesOnRequestsService {
        @Post("/multiple/sharedroute/request/upload/single-body-type")
        @ExpectedResponses({ 204 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> uploadBytesWithSingleBodyTypeForMultiContentTypes(@HostParam("endpoint") String endpoint,
            @HeaderParam("content-type") String contentType, @HeaderParam("accept") String accept,
            @BodyParam("application/json") BinaryData data, RequestOptions requestOptions, Context context);

        @Post("/multiple/sharedroute/request/upload/single-body-type")
        @ExpectedResponses({ 204 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<Void> uploadBytesWithSingleBodyTypeForMultiContentTypesSync(@HostParam("endpoint") String endpoint,
            @HeaderParam("content-type") String contentType, @HeaderParam("accept") String accept,
            @BodyParam("application/json") BinaryData data, RequestOptions requestOptions, Context context);

        @Post("/multiple/sharedroute/request/upload/multi-body-types")
        @ExpectedResponses({ 204 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> uploadBytesWithMultiBodyTypesForMultiContentTypes(@HostParam("endpoint") String endpoint,
            @HeaderParam("content-type") String contentType, @HeaderParam("accept") String accept,
            @BodyParam("application/json") BinaryData data, RequestOptions requestOptions, Context context);

        @Post("/multiple/sharedroute/request/upload/multi-body-types")
        @ExpectedResponses({ 204 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<Void> uploadBytesWithMultiBodyTypesForMultiContentTypesSync(@HostParam("endpoint") String endpoint,
            @HeaderParam("content-type") String contentType, @HeaderParam("accept") String accept,
            @BodyParam("application/json") BinaryData data, RequestOptions requestOptions, Context context);

        @Post("/multiple/sharedroute/request/upload/multi-body-types")
        @ExpectedResponses({ 204 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> uploadJsonWithMultiBodyTypesForMultiContentTypes(@HostParam("endpoint") String endpoint,
            @HeaderParam("content-type") String contentType, @HeaderParam("accept") String accept,
            @BodyParam("application/json") BinaryData data, RequestOptions requestOptions, Context context);

        @Post("/multiple/sharedroute/request/upload/multi-body-types")
        @ExpectedResponses({ 204 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<Void> uploadJsonWithMultiBodyTypesForMultiContentTypesSync(@HostParam("endpoint") String endpoint,
            @HeaderParam("content-type") String contentType, @HeaderParam("accept") String accept,
            @BodyParam("application/json") BinaryData data, RequestOptions requestOptions, Context context);

        @Post("/multiple/sharedroute/request/upload/multi-body-types")
        @ExpectedResponses({ 204 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> uploadJsonOrBytesWithMultiBodyTypesForMultiContentTypes(
            @HostParam("endpoint") String endpoint, @HeaderParam("content-type") String contentType,
            @HeaderParam("accept") String accept, @BodyParam("application/json") BinaryData data,
            RequestOptions requestOptions, Context context);

        @Post("/multiple/sharedroute/request/upload/multi-body-types")
        @ExpectedResponses({ 204 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<Void> uploadJsonOrBytesWithMultiBodyTypesForMultiContentTypesSync(
            @HostParam("endpoint") String endpoint, @HeaderParam("content-type") String contentType,
            @HeaderParam("accept") String accept, @BodyParam("application/json") BinaryData data,
            RequestOptions requestOptions, Context context);
    }

    /**
     * one data type maps to multiple content types.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>{@code
     * BinaryData
     * }</pre>
     * 
     * @param contentType The contentType parameter. Allowed values: "application/octet-stream", "image/jpeg",
     * "image/png".
     * @param data Represent a byte array.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> uploadBytesWithSingleBodyTypeForMultiContentTypesWithResponseAsync(String contentType,
        BinaryData data, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil
            .withContext(context -> service.uploadBytesWithSingleBodyTypeForMultiContentTypes(this.client.getEndpoint(),
                contentType, accept, data, requestOptions, context));
    }

    /**
     * one data type maps to multiple content types.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>{@code
     * BinaryData
     * }</pre>
     * 
     * @param contentType The contentType parameter. Allowed values: "application/octet-stream", "image/jpeg",
     * "image/png".
     * @param data Represent a byte array.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> uploadBytesWithSingleBodyTypeForMultiContentTypesWithResponse(String contentType,
        BinaryData data, RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.uploadBytesWithSingleBodyTypeForMultiContentTypesSync(this.client.getEndpoint(), contentType,
            accept, data, requestOptions, Context.NONE);
    }

    /**
     * multiple data types map to multiple content types using shared route.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>{@code
     * BinaryData
     * }</pre>
     * 
     * @param contentType The contentType parameter. Allowed values: "application/octet-stream", "image/jpeg",
     * "image/png".
     * @param data Represent a byte array.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> uploadBytesWithMultiBodyTypesForMultiContentTypesWithResponseAsync(String contentType,
        BinaryData data, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil
            .withContext(context -> service.uploadBytesWithMultiBodyTypesForMultiContentTypes(this.client.getEndpoint(),
                contentType, accept, data, requestOptions, context));
    }

    /**
     * multiple data types map to multiple content types using shared route.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>{@code
     * BinaryData
     * }</pre>
     * 
     * @param contentType The contentType parameter. Allowed values: "application/octet-stream", "image/jpeg",
     * "image/png".
     * @param data Represent a byte array.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> uploadBytesWithMultiBodyTypesForMultiContentTypesWithResponse(String contentType,
        BinaryData data, RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.uploadBytesWithMultiBodyTypesForMultiContentTypesSync(this.client.getEndpoint(), contentType,
            accept, data, requestOptions, Context.NONE);
    }

    /**
     * multiple data types map to multiple content types using shared route.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     * }
     * }</pre>
     * 
     * @param data The data parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> uploadJsonWithMultiBodyTypesForMultiContentTypesWithResponseAsync(BinaryData data,
        RequestOptions requestOptions) {
        final String contentType = "application/json";
        final String accept = "application/json";
        return FluxUtil
            .withContext(context -> service.uploadJsonWithMultiBodyTypesForMultiContentTypes(this.client.getEndpoint(),
                contentType, accept, data, requestOptions, context));
    }

    /**
     * multiple data types map to multiple content types using shared route.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     * }
     * }</pre>
     * 
     * @param data The data parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> uploadJsonWithMultiBodyTypesForMultiContentTypesWithResponse(BinaryData data,
        RequestOptions requestOptions) {
        final String contentType = "application/json";
        final String accept = "application/json";
        return service.uploadJsonWithMultiBodyTypesForMultiContentTypesSync(this.client.getEndpoint(), contentType,
            accept, data, requestOptions, Context.NONE);
    }

    /**
     * multiple data types map to multiple content types using shared route.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>{@code
     * BinaryData
     * }</pre>
     * 
     * @param contentType The contentType parameter. Allowed values: "application/json", "application/octet-stream",
     * "image/jpeg", "image/png".
     * @param data The data parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> uploadJsonOrBytesWithMultiBodyTypesForMultiContentTypesWithResponseAsync(
        String contentType, BinaryData data, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.uploadJsonOrBytesWithMultiBodyTypesForMultiContentTypes(
            this.client.getEndpoint(), contentType, accept, data, requestOptions, context));
    }

    /**
     * multiple data types map to multiple content types using shared route.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>{@code
     * BinaryData
     * }</pre>
     * 
     * @param contentType The contentType parameter. Allowed values: "application/json", "application/octet-stream",
     * "image/jpeg", "image/png".
     * @param data The data parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> uploadJsonOrBytesWithMultiBodyTypesForMultiContentTypesWithResponse(String contentType,
        BinaryData data, RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.uploadJsonOrBytesWithMultiBodyTypesForMultiContentTypesSync(this.client.getEndpoint(),
            contentType, accept, data, requestOptions, Context.NONE);
    }
}
