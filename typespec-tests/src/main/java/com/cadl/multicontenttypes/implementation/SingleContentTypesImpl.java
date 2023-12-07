// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.multicontenttypes.implementation;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
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
 * An instance of this class provides access to all the operations defined in SingleContentTypes.
 */
public final class SingleContentTypesImpl {
    /**
     * The proxy service used to perform REST calls.
     */
    private final SingleContentTypesService service;

    /**
     * The service client containing this operation class.
     */
    private final MultiContentTypesClientImpl client;

    /**
     * Initializes an instance of SingleContentTypesImpl.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    SingleContentTypesImpl(MultiContentTypesClientImpl client) {
        this.service = RestProxy.create(SingleContentTypesService.class, client.getHttpPipeline(),
            client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for MultiContentTypesClientSingleContentTypes to be used by the proxy
     * service to perform REST calls.
     */
    @Host("{endpoint}")
    @ServiceInterface(name = "MultiContentTypesCli")
    public interface SingleContentTypesService {
        @Get("/single/request/download/image")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<BinaryData>> downloadImageForSingleContentType(@HostParam("endpoint") String endpoint,
            @HeaderParam("accept") String accept, RequestOptions requestOptions, Context context);

        @Get("/single/request/download/image")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<BinaryData> downloadImageForSingleContentTypeSync(@HostParam("endpoint") String endpoint,
            @HeaderParam("accept") String accept, RequestOptions requestOptions, Context context);

        @Post("/single/request/upload/image")
        @ExpectedResponses({ 204 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> uploadImageForSingleContentType(@HostParam("endpoint") String endpoint,
            @HeaderParam("content-type") String contentType, @HeaderParam("accept") String accept,
            @BodyParam("image/png") BinaryData data, RequestOptions requestOptions, Context context);

        @Post("/single/request/upload/image")
        @ExpectedResponses({ 204 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<Void> uploadImageForSingleContentTypeSync(@HostParam("endpoint") String endpoint,
            @HeaderParam("content-type") String contentType, @HeaderParam("accept") String accept,
            @BodyParam("image/png") BinaryData data, RequestOptions requestOptions, Context context);
    }

    /**
     * response is binary.
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * BinaryData
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>>
        downloadImageForSingleContentTypeWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json, image/png";
        return FluxUtil.withContext(context -> service.downloadImageForSingleContentType(this.client.getEndpoint(),
            accept, requestOptions, context));
    }

    /**
     * response is binary.
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * BinaryData
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> downloadImageForSingleContentTypeWithResponse(RequestOptions requestOptions) {
        final String accept = "application/json, image/png";
        return service.downloadImageForSingleContentTypeSync(this.client.getEndpoint(), accept, requestOptions,
            Context.NONE);
    }

    /**
     * request is binary.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * BinaryData
     * }</pre>
     * 
     * @param data Represent a byte array.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> uploadImageForSingleContentTypeWithResponseAsync(BinaryData data,
        RequestOptions requestOptions) {
        final String contentType = "image/png";
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.uploadImageForSingleContentType(this.client.getEndpoint(),
            contentType, accept, data, requestOptions, context));
    }

    /**
     * request is binary.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * BinaryData
     * }</pre>
     * 
     * @param data Represent a byte array.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> uploadImageForSingleContentTypeWithResponse(BinaryData data, RequestOptions requestOptions) {
        final String contentType = "image/png";
        final String accept = "application/json";
        return service.uploadImageForSingleContentTypeSync(this.client.getEndpoint(), contentType, accept, data,
            requestOptions, Context.NONE);
    }
}
