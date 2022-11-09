// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.models.property.optional.implementation;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.Put;
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

/** An instance of this class provides access to all the operations defined in RequiredAndOptionals. */
public final class RequiredAndOptionalsImpl {
    /** The proxy service used to perform REST calls. */
    private final RequiredAndOptionalsService service;

    /** The service client containing this operation class. */
    private final OptionalClientImpl client;

    /**
     * Initializes an instance of RequiredAndOptionalsImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    RequiredAndOptionalsImpl(OptionalClientImpl client) {
        this.service =
                RestProxy.create(
                        RequiredAndOptionalsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for OptionalClientRequiredAndOptionals to be used by the proxy service to
     * perform REST calls.
     */
    @Host("http://localhost:3000")
    @ServiceInterface(name = "OptionalClientRequir")
    public interface RequiredAndOptionalsService {
        @Get("/models/properties/optional/requiredAndOptional/all")
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
        Mono<Response<BinaryData>> getAll(
                @HeaderParam("accept") String accept, RequestOptions requestOptions, Context context);

        @Get("/models/properties/optional/requiredAndOptional/requiredOnly")
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
        Mono<Response<BinaryData>> getRequiredOnly(
                @HeaderParam("accept") String accept, RequestOptions requestOptions, Context context);

        @Put("/models/properties/optional/requiredAndOptional/all")
        @ExpectedResponses({204})
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
        Mono<Response<Void>> putAll(
                @HeaderParam("accept") String accept,
                @BodyParam("application/json") BinaryData body,
                RequestOptions requestOptions,
                Context context);

        @Put("/models/properties/optional/requiredAndOptional/requiredOnly")
        @ExpectedResponses({204})
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
        Mono<Response<Void>> putRequiredOnly(
                @HeaderParam("accept") String accept,
                @BodyParam("application/json") BinaryData body,
                RequestOptions requestOptions,
                Context context);
    }

    /**
     * Get models that will return all properties in the model.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     optionalProperty: String (Optional)
     *     requiredProperty: int (Required)
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return models that will return all properties in the model along with {@link Response} on successful completion
     *     of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getAllWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getAll(accept, requestOptions, context));
    }

    /**
     * Get models that will return all properties in the model.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     optionalProperty: String (Optional)
     *     requiredProperty: int (Required)
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return models that will return all properties in the model along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getAllWithResponse(RequestOptions requestOptions) {
        return getAllWithResponseAsync(requestOptions).block();
    }

    /**
     * Get models that will return only the required properties.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     optionalProperty: String (Optional)
     *     requiredProperty: int (Required)
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return models that will return only the required properties along with {@link Response} on successful completion
     *     of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getRequiredOnlyWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getRequiredOnly(accept, requestOptions, context));
    }

    /**
     * Get models that will return only the required properties.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     optionalProperty: String (Optional)
     *     requiredProperty: int (Required)
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return models that will return only the required properties along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getRequiredOnlyWithResponse(RequestOptions requestOptions) {
        return getRequiredOnlyWithResponseAsync(requestOptions).block();
    }

    /**
     * Put a body with all properties present.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     optionalProperty: String (Optional)
     *     requiredProperty: int (Required)
     * }
     * }</pre>
     *
     * @param body Model with required and optional properties.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putAllWithResponseAsync(BinaryData body, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.putAll(accept, body, requestOptions, context));
    }

    /**
     * Put a body with all properties present.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     optionalProperty: String (Optional)
     *     requiredProperty: int (Required)
     * }
     * }</pre>
     *
     * @param body Model with required and optional properties.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putAllWithResponse(BinaryData body, RequestOptions requestOptions) {
        return putAllWithResponseAsync(body, requestOptions).block();
    }

    /**
     * Put a body with only required properties.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     optionalProperty: String (Optional)
     *     requiredProperty: int (Required)
     * }
     * }</pre>
     *
     * @param body Model with required and optional properties.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putRequiredOnlyWithResponseAsync(BinaryData body, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.putRequiredOnly(accept, body, requestOptions, context));
    }

    /**
     * Put a body with only required properties.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     optionalProperty: String (Optional)
     *     requiredProperty: int (Required)
     * }
     * }</pre>
     *
     * @param body Model with required and optional properties.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putRequiredOnlyWithResponse(BinaryData body, RequestOptions requestOptions) {
        return putRequiredOnlyWithResponseAsync(body, requestOptions).block();
    }
}
