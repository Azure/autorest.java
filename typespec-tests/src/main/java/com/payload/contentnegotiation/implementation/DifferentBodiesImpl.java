// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.payload.contentnegotiation.implementation;

import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
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
import com.azure.core.http.rest.SimpleResponse;
import com.azure.core.util.BinaryData;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import com.azure.core.util.UrlBuilder;
import com.azure.core.util.logging.ClientLogger;
import java.util.Objects;
import java.util.stream.Collectors;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in DifferentBodies.
 */
public final class DifferentBodiesImpl {
    /**
     * The proxy service used to perform REST calls.
     */
    private final DifferentBodiesService service;

    /**
     * The service client containing this operation class.
     */
    private final ContentNegotiationClientImpl client;

    /**
     * Initializes an instance of DifferentBodiesImpl.
     * 
     * @param client the instance of the service client containing this operation class.
     */
     DifferentBodiesImpl(ContentNegotiationClientImpl client) {
        this.service = RestProxy.create(DifferentBodiesService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for ContentNegotiationClientDifferentBodies to be used by the proxy
     * service to perform REST calls.
     */
    @Host("http://localhost:3000")
    @ServiceInterface(name = "ContentNegotiationCl")
    public interface DifferentBodiesService {
        @Get("/content-negotiation/different-body")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = {401})
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = {404})
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = {409})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<BinaryData>> getAvatarAsPng(@HeaderParam("accept") String accept, RequestOptions requestOptions, Context context);

        @Get("/content-negotiation/different-body")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = {401})
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = {404})
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = {409})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<BinaryData> getAvatarAsPngSync(@HeaderParam("accept") String accept, RequestOptions requestOptions, Context context);

        @Get("/content-negotiation/different-body")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = {401})
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = {404})
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = {409})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<BinaryData>> getAvatarAsJson(@HeaderParam("accept") String accept, RequestOptions requestOptions, Context context);

        @Get("/content-negotiation/different-body")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = {401})
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = {404})
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = {409})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<BinaryData> getAvatarAsJsonSync(@HeaderParam("accept") String accept, RequestOptions requestOptions, Context context);
    }

    /**
     * The getAvatarAsPng operation.
     * <p><strong>Response Body Schema</strong></p>
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
    public Mono<Response<BinaryData>> getAvatarAsPngWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "image/png";
        return FluxUtil.withContext(context -> service.getAvatarAsPng(accept, requestOptions, context));
    }

    /**
     * The getAvatarAsPng operation.
     * <p><strong>Response Body Schema</strong></p>
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
    public Response<BinaryData> getAvatarAsPngWithResponse(RequestOptions requestOptions) {
        final String accept = "image/png";
        return service.getAvatarAsPngSync(accept, requestOptions, Context.NONE);
    }

    /**
     * The getAvatarAsJson operation.
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     *     content: byte[] (Required)
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
    public Mono<Response<BinaryData>> getAvatarAsJsonWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getAvatarAsJson(accept, requestOptions, context));
    }

    /**
     * The getAvatarAsJson operation.
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     *     content: byte[] (Required)
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
    public Response<BinaryData> getAvatarAsJsonWithResponse(RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.getAvatarAsJsonSync(accept, requestOptions, Context.NONE);
    }
}
