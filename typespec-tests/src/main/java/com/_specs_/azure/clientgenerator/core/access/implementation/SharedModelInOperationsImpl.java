// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com._specs_.azure.clientgenerator.core.access.implementation;

import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
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
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in SharedModelInOperations.
 */
public final class SharedModelInOperationsImpl {
    /**
     * The proxy service used to perform REST calls.
     */
    private final SharedModelInOperationsService service;

    /**
     * The service client containing this operation class.
     */
    private final AccessClientImpl client;

    /**
     * Initializes an instance of SharedModelInOperationsImpl.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    SharedModelInOperationsImpl(AccessClientImpl client) {
        this.service = RestProxy.create(SharedModelInOperationsService.class, client.getHttpPipeline(),
            client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for AccessClientSharedModelInOperations to be used by the proxy service
     * to perform REST calls.
     */
    @Host("http://localhost:3000")
    @ServiceInterface(name = "AccessClientSharedMo")
    public interface SharedModelInOperationsService {
        @Get("/azure/client-generator-core/access/sharedModelInOperation/public")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<BinaryData>> publicMethod(@QueryParam("name") String name, @HeaderParam("accept") String accept,
            RequestOptions requestOptions, Context context);

        @Get("/azure/client-generator-core/access/sharedModelInOperation/public")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<BinaryData> publicMethodSync(@QueryParam("name") String name, @HeaderParam("accept") String accept,
            RequestOptions requestOptions, Context context);

        @Get("/azure/client-generator-core/access/sharedModelInOperation/internal")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<BinaryData>> internal(@QueryParam("name") String name, @HeaderParam("accept") String accept,
            RequestOptions requestOptions, Context context);

        @Get("/azure/client-generator-core/access/sharedModelInOperation/internal")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<BinaryData> internalSync(@QueryParam("name") String name, @HeaderParam("accept") String accept,
            RequestOptions requestOptions, Context context);
    }

    /**
     * The publicMethod operation.
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     name: String (Required)
     * }
     * }</pre>
     * 
     * @param name A sequence of textual characters.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return used by both public and internal operation along with {@link Response} on successful completion of
     * {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> publicMethodWithResponseAsync(String name, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.publicMethod(name, accept, requestOptions, context));
    }

    /**
     * The publicMethod operation.
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     name: String (Required)
     * }
     * }</pre>
     * 
     * @param name A sequence of textual characters.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return used by both public and internal operation along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> publicMethodWithResponse(String name, RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.publicMethodSync(name, accept, requestOptions, Context.NONE);
    }

    /**
     * The internal operation.
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     name: String (Required)
     * }
     * }</pre>
     * 
     * @param name A sequence of textual characters.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return used by both public and internal operation along with {@link Response} on successful completion of
     * {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> internalWithResponseAsync(String name, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.internal(name, accept, requestOptions, context));
    }

    /**
     * The internal operation.
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     name: String (Required)
     * }
     * }</pre>
     * 
     * @param name A sequence of textual characters.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return used by both public and internal operation along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> internalWithResponse(String name, RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.internalSync(name, accept, requestOptions, Context.NONE);
    }
}
