// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.
package com.cadl.internal.implementation;

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
 * An instance of this class provides access to all the operations defined in InternalOps.
 */
public final class InternalOpsImpl {

    /**
     * The proxy service used to perform REST calls.
     */
    private final InternalOpsService service;

    /**
     * The service client containing this operation class.
     */
    private final InternalClientImpl client;

    /**
     * Initializes an instance of InternalOpsImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    InternalOpsImpl(InternalClientImpl client) {
        this.service
            = RestProxy.create(InternalOpsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for InternalClientInternalOps to be used by the proxy service to perform
     * REST calls.
     */
    @Host("{endpoint}")
    @ServiceInterface(name = "InternalClientIntern")
    public interface InternalOpsService {

        @Post("/internal")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<BinaryData>> postInternal(@HostParam("endpoint") String endpoint,
            @HeaderParam("accept") String accept, @BodyParam("application/json") BinaryData apiRequest,
            RequestOptions requestOptions, Context context);

        @Post("/internal")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<BinaryData> postInternalSync(@HostParam("endpoint") String endpoint,
            @HeaderParam("accept") String accept, @BodyParam("application/json") BinaryData apiRequest,
            RequestOptions requestOptions, Context context);

        @Get("/internal")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<BinaryData>> getInternal(@HostParam("endpoint") String endpoint,
            @HeaderParam("accept") String accept, RequestOptions requestOptions, Context context);

        @Get("/internal")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<BinaryData> getInternalSync(@HostParam("endpoint") String endpoint,
            @HeaderParam("accept") String accept, RequestOptions requestOptions, Context context);
    }

    /**
     * The postInternal operation.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     property (Required): {
     *         name: String (Required)
     *     }
     * }
     * }</pre>
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     property (Required): {
     *         name: String (Required)
     *     }
     * }
     * }</pre>
     *
     * @param apiRequest The apiRequest parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> postInternalWithResponseAsync(BinaryData apiRequest,
        RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
            context -> service.postInternal(this.client.getEndpoint(), accept, apiRequest, requestOptions, context));
    }

    /**
     * The postInternal operation.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     property (Required): {
     *         name: String (Required)
     *     }
     * }
     * }</pre>
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     property (Required): {
     *         name: String (Required)
     *     }
     * }
     * }</pre>
     *
     * @param apiRequest The apiRequest parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> postInternalWithResponse(BinaryData apiRequest, RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.postInternalSync(this.client.getEndpoint(), accept, apiRequest, requestOptions, Context.NONE);
    }

    /**
     * The getInternal operation.
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     name: String (Required)
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
    public Mono<Response<BinaryData>> getInternalWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil
            .withContext(context -> service.getInternal(this.client.getEndpoint(), accept, requestOptions, context));
    }

    /**
     * The getInternal operation.
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     name: String (Required)
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
    public Response<BinaryData> getInternalWithResponse(RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.getInternalSync(this.client.getEndpoint(), accept, requestOptions, Context.NONE);
    }
}
