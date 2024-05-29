// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.union.implementation;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
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
 * An instance of this class provides access to all the operations defined in StringsOnlies.
 */
public final class StringsOnliesImpl {
    /**
     * The proxy service used to perform REST calls.
     */
    private final StringsOnliesService service;

    /**
     * The service client containing this operation class.
     */
    private final UnionClientImpl client;

    /**
     * Initializes an instance of StringsOnliesImpl.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    StringsOnliesImpl(UnionClientImpl client) {
        this.service
            = RestProxy.create(StringsOnliesService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for UnionClientStringsOnlies to be used by the proxy service to perform
     * REST calls.
     */
    @Host("http://localhost:3000")
    @ServiceInterface(name = "UnionClientStringsOn")
    public interface StringsOnliesService {
        @Get("/type/union/strings-only")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<BinaryData>> get(@HeaderParam("accept") String accept, RequestOptions requestOptions,
            Context context);

        @Get("/type/union/strings-only")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<BinaryData> getSync(@HeaderParam("accept") String accept, RequestOptions requestOptions,
            Context context);

        @Post("/type/union/strings-only")
        @ExpectedResponses({ 204 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> send(@HeaderParam("accept") String accept,
            @BodyParam("application/json") BinaryData sendRequest9, RequestOptions requestOptions, Context context);

        @Post("/type/union/strings-only")
        @ExpectedResponses({ 204 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<Void> sendSync(@HeaderParam("accept") String accept,
            @BodyParam("application/json") BinaryData sendRequest9, RequestOptions requestOptions, Context context);
    }

    /**
     * The get operation.
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     prop: String(a/b/c) (Required)
     * }
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
    public Mono<Response<BinaryData>> getWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.get(accept, requestOptions, context));
    }

    /**
     * The get operation.
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     prop: String(a/b/c) (Required)
     * }
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
    public Response<BinaryData> getWithResponse(RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.getSync(accept, requestOptions, Context.NONE);
    }

    /**
     * The send operation.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     prop: String(a/b/c) (Required)
     * }
     * }</pre>
     * 
     * @param sendRequest9 The sendRequest9 parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> sendWithResponseAsync(BinaryData sendRequest9, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.send(accept, sendRequest9, requestOptions, context));
    }

    /**
     * The send operation.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     prop: String(a/b/c) (Required)
     * }
     * }</pre>
     * 
     * @param sendRequest9 The sendRequest9 parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> sendWithResponse(BinaryData sendRequest9, RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.sendSync(accept, sendRequest9, requestOptions, Context.NONE);
    }
}
