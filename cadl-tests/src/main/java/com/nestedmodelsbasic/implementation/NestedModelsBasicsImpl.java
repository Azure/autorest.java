// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.nestedmodelsbasic.implementation;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.Post;
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

/** An instance of this class provides access to all the operations defined in NestedModelsBasics. */
public final class NestedModelsBasicsImpl {
    /** The proxy service used to perform REST calls. */
    private final NestedModelsBasicsService service;

    /** The service client containing this operation class. */
    private final NestedModelsBasicClientImpl client;

    /**
     * Initializes an instance of NestedModelsBasicsImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    NestedModelsBasicsImpl(NestedModelsBasicClientImpl client) {
        this.service =
                RestProxy.create(
                        NestedModelsBasicsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for NestedModelsBasicNestedModelsBasics to be used by the proxy service
     * to perform REST calls.
     */
    @Host("http://localhost:3000")
    @ServiceInterface(name = "NestedModelsBasicNes")
    private interface NestedModelsBasicsService {
        @Post("/nested-models/models")
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
        Mono<Response<Void>> sendNestedModel(
                @HeaderParam("accept") String accept,
                @BodyParam("application/json") BinaryData input,
                RequestOptions requestOptions,
                Context context);

        @Get("/nested-models/models")
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
        Mono<Response<BinaryData>> getNestedModel(
                @HeaderParam("accept") String accept, RequestOptions requestOptions, Context context);

        @Put("/nested-models/models")
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
        Mono<Response<BinaryData>> setNestedModel(
                @HeaderParam("accept") String accept,
                @BodyParam("application/json") BinaryData input,
                RequestOptions requestOptions,
                Context context);
    }

    /**
     * The sendNestedModel operation.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     NestedInputModel (Required): {
     *         requiredString: String (Required)
     *         requiredInt: int (Required)
     *         requiredStringList (Required): [
     *             String (Required)
     *         ]
     *         requiredIntList (Required): [
     *             int (Required)
     *         ]
     *     }
     *     NestedSharedModel (Required): {
     *         requiredString: String (Required)
     *         requiredInt: int (Required)
     *         requiredStringList (Required): [
     *             String (Required)
     *         ]
     *         requiredIntList (Required): [
     *             int (Required)
     *         ]
     *     }
     * }
     * }</pre>
     *
     * @param input Input model with nested model properties.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> sendNestedModelWithResponseAsync(BinaryData input, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.sendNestedModel(accept, input, requestOptions, context));
    }

    /**
     * The sendNestedModel operation.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     NestedInputModel (Required): {
     *         requiredString: String (Required)
     *         requiredInt: int (Required)
     *         requiredStringList (Required): [
     *             String (Required)
     *         ]
     *         requiredIntList (Required): [
     *             int (Required)
     *         ]
     *     }
     *     NestedSharedModel (Required): {
     *         requiredString: String (Required)
     *         requiredInt: int (Required)
     *         requiredStringList (Required): [
     *             String (Required)
     *         ]
     *         requiredIntList (Required): [
     *             int (Required)
     *         ]
     *     }
     * }
     * }</pre>
     *
     * @param input Input model with nested model properties.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> sendNestedModelWithResponse(BinaryData input, RequestOptions requestOptions) {
        return sendNestedModelWithResponseAsync(input, requestOptions).block();
    }

    /**
     * The getNestedModel operation.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     NestedOutputModel (Required): {
     *         requiredString: String (Required)
     *         requiredInt: int (Required)
     *         requiredStringList (Required): [
     *             String (Required)
     *         ]
     *         requiredIntList (Required): [
     *             int (Required)
     *         ]
     *     }
     *     NestedSharedModel (Required): {
     *         requiredString: String (Required)
     *         requiredInt: int (Required)
     *         requiredStringList (Required): [
     *             String (Required)
     *         ]
     *         requiredIntList (Required): [
     *             int (Required)
     *         ]
     *     }
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return output model with nested model properties along with {@link Response} on successful completion of {@link
     *     Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getNestedModelWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getNestedModel(accept, requestOptions, context));
    }

    /**
     * The getNestedModel operation.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     NestedOutputModel (Required): {
     *         requiredString: String (Required)
     *         requiredInt: int (Required)
     *         requiredStringList (Required): [
     *             String (Required)
     *         ]
     *         requiredIntList (Required): [
     *             int (Required)
     *         ]
     *     }
     *     NestedSharedModel (Required): {
     *         requiredString: String (Required)
     *         requiredInt: int (Required)
     *         requiredStringList (Required): [
     *             String (Required)
     *         ]
     *         requiredIntList (Required): [
     *             int (Required)
     *         ]
     *     }
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return output model with nested model properties along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getNestedModelWithResponse(RequestOptions requestOptions) {
        return getNestedModelWithResponseAsync(requestOptions).block();
    }

    /**
     * The setNestedModel operation.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     NestedRoundTripModel (Required): {
     *         requiredString: String (Required)
     *         requiredInt: int (Required)
     *         requiredStringList (Required): [
     *             String (Required)
     *         ]
     *         requiredIntList (Required): [
     *             int (Required)
     *         ]
     *     }
     *     NestedSharedModel (Required): {
     *         requiredString: String (Required)
     *         requiredInt: int (Required)
     *         requiredStringList (Required): [
     *             String (Required)
     *         ]
     *         requiredIntList (Required): [
     *             int (Required)
     *         ]
     *     }
     * }
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     NestedRoundTripModel (Required): {
     *         requiredString: String (Required)
     *         requiredInt: int (Required)
     *         requiredStringList (Required): [
     *             String (Required)
     *         ]
     *         requiredIntList (Required): [
     *             int (Required)
     *         ]
     *     }
     *     NestedSharedModel (Required): {
     *         requiredString: String (Required)
     *         requiredInt: int (Required)
     *         requiredStringList (Required): [
     *             String (Required)
     *         ]
     *         requiredIntList (Required): [
     *             int (Required)
     *         ]
     *     }
     * }
     * }</pre>
     *
     * @param input Round-trip model with nested model properties.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return round-trip model with nested model properties along with {@link Response} on successful completion of
     *     {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> setNestedModelWithResponseAsync(BinaryData input, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.setNestedModel(accept, input, requestOptions, context));
    }

    /**
     * The setNestedModel operation.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     NestedRoundTripModel (Required): {
     *         requiredString: String (Required)
     *         requiredInt: int (Required)
     *         requiredStringList (Required): [
     *             String (Required)
     *         ]
     *         requiredIntList (Required): [
     *             int (Required)
     *         ]
     *     }
     *     NestedSharedModel (Required): {
     *         requiredString: String (Required)
     *         requiredInt: int (Required)
     *         requiredStringList (Required): [
     *             String (Required)
     *         ]
     *         requiredIntList (Required): [
     *             int (Required)
     *         ]
     *     }
     * }
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     NestedRoundTripModel (Required): {
     *         requiredString: String (Required)
     *         requiredInt: int (Required)
     *         requiredStringList (Required): [
     *             String (Required)
     *         ]
     *         requiredIntList (Required): [
     *             int (Required)
     *         ]
     *     }
     *     NestedSharedModel (Required): {
     *         requiredString: String (Required)
     *         requiredInt: int (Required)
     *         requiredStringList (Required): [
     *             String (Required)
     *         ]
     *         requiredIntList (Required): [
     *             int (Required)
     *         ]
     *     }
     * }
     * }</pre>
     *
     * @param input Round-trip model with nested model properties.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return round-trip model with nested model properties along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> setNestedModelWithResponse(BinaryData input, RequestOptions requestOptions) {
        return setNestedModelWithResponseAsync(input, requestOptions).block();
    }
}
