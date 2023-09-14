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

/** An instance of this class provides access to all the operations defined in RelativeModelInOperations. */
public final class RelativeModelInOperationsImpl {
    /** The proxy service used to perform REST calls. */
    private final RelativeModelInOperationsService service;

    /** The service client containing this operation class. */
    private final AccessClientImpl client;

    /**
     * Initializes an instance of RelativeModelInOperationsImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    RelativeModelInOperationsImpl(AccessClientImpl client) {
        this.service =
                RestProxy.create(
                        RelativeModelInOperationsService.class,
                        client.getHttpPipeline(),
                        client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for AccessClientRelativeModelInOperations to be used by the proxy service
     * to perform REST calls.
     */
    @Host("http://localhost:3000")
    @ServiceInterface(name = "AccessClientRelative")
    public interface RelativeModelInOperationsService {
        @Get("/azure/client-generator-core/access/relativeModelInOperation/operation")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(
                value = ClientAuthenticationException.class,
                code = {401})
        @UnexpectedResponseExceptionType(
                value = ResourceNotFoundException.class,
                code = {404})
        @UnexpectedResponseExceptionType(
                value = ResourceModifiedException.class,
                code = {409})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<BinaryData>> operation(
                @QueryParam("name") String name,
                @HeaderParam("accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/azure/client-generator-core/access/relativeModelInOperation/operation")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(
                value = ClientAuthenticationException.class,
                code = {401})
        @UnexpectedResponseExceptionType(
                value = ResourceNotFoundException.class,
                code = {404})
        @UnexpectedResponseExceptionType(
                value = ResourceModifiedException.class,
                code = {409})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<BinaryData> operationSync(
                @QueryParam("name") String name,
                @HeaderParam("accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/azure/client-generator-core/access/relativeModelInOperation/discriminator")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(
                value = ClientAuthenticationException.class,
                code = {401})
        @UnexpectedResponseExceptionType(
                value = ResourceNotFoundException.class,
                code = {404})
        @UnexpectedResponseExceptionType(
                value = ResourceModifiedException.class,
                code = {409})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<BinaryData>> discriminator(
                @QueryParam("kind") String kind,
                @HeaderParam("accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/azure/client-generator-core/access/relativeModelInOperation/discriminator")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(
                value = ClientAuthenticationException.class,
                code = {401})
        @UnexpectedResponseExceptionType(
                value = ResourceNotFoundException.class,
                code = {404})
        @UnexpectedResponseExceptionType(
                value = ResourceModifiedException.class,
                code = {409})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<BinaryData> discriminatorSync(
                @QueryParam("kind") String kind,
                @HeaderParam("accept") String accept,
                RequestOptions requestOptions,
                Context context);
    }

    /**
     * Expected query parameter: name=&lt;any string&gt; Expected response body: ```json { "name": &lt;any string&gt;,
     * "inner": { "name": &lt;any string&gt; } } ```.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     name: String (Required)
     *     inner (Required): {
     *         name: String (Required)
     *     }
     * }
     * }</pre>
     *
     * @param name A sequence of textual characters.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return used in internal operations, should be generated but not exported along with {@link Response} on
     *     successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> operationWithResponseAsync(String name, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.operation(name, accept, requestOptions, context));
    }

    /**
     * Expected query parameter: name=&lt;any string&gt; Expected response body: ```json { "name": &lt;any string&gt;,
     * "inner": { "name": &lt;any string&gt; } } ```.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     name: String (Required)
     *     inner (Required): {
     *         name: String (Required)
     *     }
     * }
     * }</pre>
     *
     * @param name A sequence of textual characters.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return used in internal operations, should be generated but not exported along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> operationWithResponse(String name, RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.operationSync(name, accept, requestOptions, Context.NONE);
    }

    /**
     * Expected query parameter: kind=&lt;any string&gt; Expected response body: ```json { "name": &lt;any string&gt;,
     * "kind": "real" } ```.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     name: String (Required)
     * }
     * }</pre>
     *
     * @param kind A sequence of textual characters.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return used in internal operations, should be generated but not exported along with {@link Response} on
     *     successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> discriminatorWithResponseAsync(String kind, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.discriminator(kind, accept, requestOptions, context));
    }

    /**
     * Expected query parameter: kind=&lt;any string&gt; Expected response body: ```json { "name": &lt;any string&gt;,
     * "kind": "real" } ```.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     name: String (Required)
     * }
     * }</pre>
     *
     * @param kind A sequence of textual characters.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return used in internal operations, should be generated but not exported along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> discriminatorWithResponse(String kind, RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.discriminatorSync(kind, accept, requestOptions, Context.NONE);
    }
}
