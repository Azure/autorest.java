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
 * An instance of this class provides access to all the operations defined in PublicOperations.
 */
public final class PublicOperationsImpl {
    /**
     * The proxy service used to perform REST calls.
     */
    private final PublicOperationsService service;

    /**
     * The service client containing this operation class.
     */
    private final AccessClientImpl client;

    /**
     * Initializes an instance of PublicOperationsImpl.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    PublicOperationsImpl(AccessClientImpl client) {
        this.service
            = RestProxy.create(PublicOperationsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for AccessClientPublicOperations to be used by the proxy service to
     * perform REST calls.
     */
    @Host("http://localhost:3000")
    @ServiceInterface(name = "AccessClientPublicOp")
    public interface PublicOperationsService {
        @Get("/azure/client-generator-core/access/publicOperation/noDecoratorInPublic")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<BinaryData>> noDecoratorInPublic(@QueryParam("name") String name,
            @HeaderParam("accept") String accept, RequestOptions requestOptions, Context context);

        @Get("/azure/client-generator-core/access/publicOperation/noDecoratorInPublic")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<BinaryData> noDecoratorInPublicSync(@QueryParam("name") String name,
            @HeaderParam("accept") String accept, RequestOptions requestOptions, Context context);

        @Get("/azure/client-generator-core/access/publicOperation/publicDecoratorInPublic")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<BinaryData>> publicDecoratorInPublic(@QueryParam("name") String name,
            @HeaderParam("accept") String accept, RequestOptions requestOptions, Context context);

        @Get("/azure/client-generator-core/access/publicOperation/publicDecoratorInPublic")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<BinaryData> publicDecoratorInPublicSync(@QueryParam("name") String name,
            @HeaderParam("accept") String accept, RequestOptions requestOptions, Context context);
    }

    /**
     * The noDecoratorInPublic operation.
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     * name: String (Required)
     * }
     * }</pre>
     * 
     * @param name A sequence of textual characters.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return used in a public operation, should be generated and exported along with {@link Response} on successful
     * completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> noDecoratorInPublicWithResponseAsync(String name, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.noDecoratorInPublic(name, accept, requestOptions, context));
    }

    /**
     * The noDecoratorInPublic operation.
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     * name: String (Required)
     * }
     * }</pre>
     * 
     * @param name A sequence of textual characters.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return used in a public operation, should be generated and exported along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> noDecoratorInPublicWithResponse(String name, RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.noDecoratorInPublicSync(name, accept, requestOptions, Context.NONE);
    }

    /**
     * The publicDecoratorInPublic operation.
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     * name: String (Required)
     * }
     * }</pre>
     * 
     * @param name A sequence of textual characters.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return used in a public operation, should be generated and exported along with {@link Response} on successful
     * completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> publicDecoratorInPublicWithResponseAsync(String name,
        RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.publicDecoratorInPublic(name, accept, requestOptions, context));
    }

    /**
     * The publicDecoratorInPublic operation.
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     * name: String (Required)
     * }
     * }</pre>
     * 
     * @param name A sequence of textual characters.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return used in a public operation, should be generated and exported along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> publicDecoratorInPublicWithResponse(String name, RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.publicDecoratorInPublicSync(name, accept, requestOptions, Context.NONE);
    }
}
