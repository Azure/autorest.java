// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.wiretype.implementation;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
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
 * An instance of this class provides access to all the operations defined in WireTypeOps.
 */
public final class WireTypeOpsImpl {
    /**
     * The proxy service used to perform REST calls.
     */
    private final WireTypeOpsService service;

    /**
     * The service client containing this operation class.
     */
    private final WireTypeClientImpl client;

    /**
     * Initializes an instance of WireTypeOpsImpl.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    WireTypeOpsImpl(WireTypeClientImpl client) {
        this.service
            = RestProxy.create(WireTypeOpsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for WireTypeClientWireTypeOps to be used by the proxy service to perform
     * REST calls.
     */
    @Host("{endpoint}")
    @ServiceInterface(name = "WireTypeClientWireTy")
    public interface WireTypeOpsService {
        @Put("/wireType/superClassMismatch")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<BinaryData>> superClassMismatch(@HostParam("endpoint") String endpoint,
            @HeaderParam("accept") String accept, @BodyParam("application/json") BinaryData subClass,
            RequestOptions requestOptions, Context context);

        @Put("/wireType/superClassMismatch")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<BinaryData> superClassMismatchSync(@HostParam("endpoint") String endpoint,
            @HeaderParam("accept") String accept, @BodyParam("application/json") BinaryData subClass,
            RequestOptions requestOptions, Context context);

        @Put("/wireType/subClassMismatch")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<BinaryData>> subClassMismatch(@HostParam("endpoint") String endpoint,
            @HeaderParam("accept") String accept, @BodyParam("application/json") BinaryData subClassMismatch,
            RequestOptions requestOptions, Context context);

        @Put("/wireType/subClassMismatch")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<BinaryData> subClassMismatchSync(@HostParam("endpoint") String endpoint,
            @HeaderParam("accept") String accept, @BodyParam("application/json") BinaryData subClassMismatch,
            RequestOptions requestOptions, Context context);

        @Put("/wireType/bothClassMismatch")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<BinaryData>> bothClassMismatch(@HostParam("endpoint") String endpoint,
            @HeaderParam("accept") String accept, @BodyParam("application/json") BinaryData subClassBothMismatch,
            RequestOptions requestOptions, Context context);

        @Put("/wireType/bothClassMismatch")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<BinaryData> bothClassMismatchSync(@HostParam("endpoint") String endpoint,
            @HeaderParam("accept") String accept, @BodyParam("application/json") BinaryData subClassBothMismatch,
            RequestOptions requestOptions, Context context);
    }

    /**
     * The superClassMismatch operation.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     dateTimeRfc7231: DateTimeRfc1123 (Required)
     *     dateTime: OffsetDateTime (Required)
     * }
     * }</pre>
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     dateTimeRfc7231: DateTimeRfc1123 (Required)
     *     dateTime: OffsetDateTime (Required)
     * }
     * }</pre>
     * 
     * @param subClass The subClass parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> superClassMismatchWithResponseAsync(BinaryData subClass,
        RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.superClassMismatch(this.client.getEndpoint(), accept, subClass,
            requestOptions, context));
    }

    /**
     * The superClassMismatch operation.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     dateTimeRfc7231: DateTimeRfc1123 (Required)
     *     dateTime: OffsetDateTime (Required)
     * }
     * }</pre>
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     dateTimeRfc7231: DateTimeRfc1123 (Required)
     *     dateTime: OffsetDateTime (Required)
     * }
     * }</pre>
     * 
     * @param subClass The subClass parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> superClassMismatchWithResponse(BinaryData subClass, RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.superClassMismatchSync(this.client.getEndpoint(), accept, subClass, requestOptions,
            Context.NONE);
    }

    /**
     * The subClassMismatch operation.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     dateTime: OffsetDateTime (Required)
     *     dateTimeRfc7231: DateTimeRfc1123 (Required)
     * }
     * }</pre>
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     dateTime: OffsetDateTime (Required)
     *     dateTimeRfc7231: DateTimeRfc1123 (Required)
     * }
     * }</pre>
     * 
     * @param subClassMismatch The subClassMismatch parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> subClassMismatchWithResponseAsync(BinaryData subClassMismatch,
        RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.subClassMismatch(this.client.getEndpoint(), accept,
            subClassMismatch, requestOptions, context));
    }

    /**
     * The subClassMismatch operation.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     dateTime: OffsetDateTime (Required)
     *     dateTimeRfc7231: DateTimeRfc1123 (Required)
     * }
     * }</pre>
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     dateTime: OffsetDateTime (Required)
     *     dateTimeRfc7231: DateTimeRfc1123 (Required)
     * }
     * }</pre>
     * 
     * @param subClassMismatch The subClassMismatch parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> subClassMismatchWithResponse(BinaryData subClassMismatch,
        RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.subClassMismatchSync(this.client.getEndpoint(), accept, subClassMismatch, requestOptions,
            Context.NONE);
    }

    /**
     * The bothClassMismatch operation.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     dateTimeRfc7231: DateTimeRfc1123 (Required)
     *     base64url: Base64Url (Required)
     * }
     * }</pre>
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     dateTimeRfc7231: DateTimeRfc1123 (Required)
     *     base64url: Base64Url (Required)
     * }
     * }</pre>
     * 
     * @param subClassBothMismatch The subClassBothMismatch parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> bothClassMismatchWithResponseAsync(BinaryData subClassBothMismatch,
        RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.bothClassMismatch(this.client.getEndpoint(), accept,
            subClassBothMismatch, requestOptions, context));
    }

    /**
     * The bothClassMismatch operation.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     dateTimeRfc7231: DateTimeRfc1123 (Required)
     *     base64url: Base64Url (Required)
     * }
     * }</pre>
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     dateTimeRfc7231: DateTimeRfc1123 (Required)
     *     base64url: Base64Url (Required)
     * }
     * }</pre>
     * 
     * @param subClassBothMismatch The subClassBothMismatch parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> bothClassMismatchWithResponse(BinaryData subClassBothMismatch,
        RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.bothClassMismatchSync(this.client.getEndpoint(), accept, subClassBothMismatch, requestOptions,
            Context.NONE);
    }
}
