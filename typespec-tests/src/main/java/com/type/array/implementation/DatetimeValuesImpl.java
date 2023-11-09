// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.array.implementation;

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

/**
 * An instance of this class provides access to all the operations defined in DatetimeValues.
 */
public final class DatetimeValuesImpl {
    /**
     * The proxy service used to perform REST calls.
     */
    private final DatetimeValuesService service;

    /**
     * The service client containing this operation class.
     */
    private final ArrayClientImpl client;

    /**
     * Initializes an instance of DatetimeValuesImpl.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    DatetimeValuesImpl(ArrayClientImpl client) {
        this.service
            = RestProxy.create(DatetimeValuesService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for ArrayClientDatetimeValues to be used by the proxy service to perform
     * REST calls.
     */
    @Host("http://localhost:3000")
    @ServiceInterface(name = "ArrayClientDatetimeV")
    public interface DatetimeValuesService {
        @Get("/type/array/datetime")
        @ExpectedResponses({
            200
        })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = {
            401
        })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = {
            404
        })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = {
            409
        })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<BinaryData>> get(@HeaderParam("accept") String accept, RequestOptions requestOptions,
            Context context);

        @Get("/type/array/datetime")
        @ExpectedResponses({
            200
        })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = {
            401
        })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = {
            404
        })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = {
            409
        })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<BinaryData> getSync(@HeaderParam("accept") String accept, RequestOptions requestOptions,
            Context context);

        @Put("/type/array/datetime")
        @ExpectedResponses({
            204
        })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = {
            401
        })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = {
            404
        })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = {
            409
        })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> put(@HeaderParam("accept") String accept, @BodyParam("application/json") BinaryData body,
            RequestOptions requestOptions, Context context);

        @Put("/type/array/datetime")
        @ExpectedResponses({
            204
        })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = {
            401
        })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = {
            404
        })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = {
            409
        })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<Void> putSync(@HeaderParam("accept") String accept, @BodyParam("application/json") BinaryData body,
            RequestOptions requestOptions, Context context);
    }

    /**
     * The get operation.
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * [
     *     OffsetDateTime (Required)
     * ]
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return array of GetResponse along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.get(accept, requestOptions, context));
    }

    /**
     * The get operation.
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * [
     *     OffsetDateTime (Required)
     * ]
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return array of GetResponse along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getWithResponse(RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.getSync(accept, requestOptions, Context.NONE);
    }

    /**
     * The put operation.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * [
     *     OffsetDateTime (Required)
     * ]
     * }</pre>
     * 
     * @param body Array of GetResponse.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putWithResponseAsync(BinaryData body, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.put(accept, body, requestOptions, context));
    }

    /**
     * The put operation.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * [
     *     OffsetDateTime (Required)
     * ]
     * }</pre>
     * 
     * @param body Array of GetResponse.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putWithResponse(BinaryData body, RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.putSync(accept, body, requestOptions, Context.NONE);
    }
}
