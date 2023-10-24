// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.visibility.implementation;

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
 * An instance of this class provides access to all the operations defined in VisibilityWrites.
 */
public final class VisibilityWritesImpl {
    /**
     * The proxy service used to perform REST calls.
     */
    private final VisibilityWritesService service;

    /**
     * The service client containing this operation class.
     */
    private final VisibilityClientImpl client;

    /**
     * Initializes an instance of VisibilityWritesImpl.
     * 
     * @param client the instance of the service client containing this operation class.
     */
     VisibilityWritesImpl(VisibilityClientImpl client) {
        this.service = RestProxy.create(VisibilityWritesService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for VisibilityClientVisibilityWrites to be used by the proxy service to
     * perform REST calls.
     */
    @Host("{endpoint}")
    @ServiceInterface(name = "VisibilityClientVisi")
    public interface VisibilityWritesService {
        @Put("/write")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = {401})
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = {404})
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = {409})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<BinaryData>> create(@HostParam("endpoint") String endpoint, @HeaderParam("accept") String accept, @BodyParam("application/json") BinaryData dog, RequestOptions requestOptions, Context context);

        @Put("/write")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = {401})
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = {404})
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = {409})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<BinaryData> createSync(@HostParam("endpoint") String endpoint, @HeaderParam("accept") String accept, @BodyParam("application/json") BinaryData dog, RequestOptions requestOptions, Context context);
    }

    /**
     * The create operation.
     * <p><strong>Request Body Schema</strong></p>
     * <pre>{@code
     * {
     *     name: String (Required)
     * }
     * }</pre>
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     *     id: int (Required)
     *     secretName: String (Required)
     *     name: String (Required)
     * }
     * }</pre>
     * 
     * @param dog The dog parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> createWithResponseAsync(BinaryData dog, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.create(this.client.getEndpoint(), accept, dog, requestOptions, context));
    }

    /**
     * The create operation.
     * <p><strong>Request Body Schema</strong></p>
     * <pre>{@code
     * {
     *     name: String (Required)
     * }
     * }</pre>
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     *     id: int (Required)
     *     secretName: String (Required)
     *     name: String (Required)
     * }
     * }</pre>
     * 
     * @param dog The dog parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> createWithResponse(BinaryData dog, RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.createSync(this.client.getEndpoint(), accept, dog, requestOptions, Context.NONE);
    }
}
