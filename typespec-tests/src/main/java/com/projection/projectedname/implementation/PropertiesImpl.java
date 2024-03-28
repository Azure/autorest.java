// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.projection.projectedname.implementation;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
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
 * An instance of this class provides access to all the operations defined in Properties.
 */
public final class PropertiesImpl {
    /**
     * The proxy service used to perform REST calls.
     */
    private final PropertiesService service;

    /**
     * The service client containing this operation class.
     */
    private final ProjectedNameClientImpl client;

    /**
     * Initializes an instance of PropertiesImpl.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    PropertiesImpl(ProjectedNameClientImpl client) {
        this.service
            = RestProxy.create(PropertiesService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for ProjectedNameClientProperties to be used by the proxy service to
     * perform REST calls.
     */
    @Host("http://localhost:3000")
    @ServiceInterface(name = "ProjectedNameClientP")
    public interface PropertiesService {
        @Post("/projection/projected-name/property/json")
        @ExpectedResponses({ 204 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> json(@HeaderParam("accept") String accept,
            @BodyParam("application/json") BinaryData jsonProjectedNameModel, RequestOptions requestOptions,
            Context context);

        @Post("/projection/projected-name/property/json")
        @ExpectedResponses({ 204 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<Void> jsonSync(@HeaderParam("accept") String accept,
            @BodyParam("application/json") BinaryData jsonProjectedNameModel, RequestOptions requestOptions,
            Context context);

        @Post("/projection/projected-name/property/client")
        @ExpectedResponses({ 204 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> client(@HeaderParam("accept") String accept,
            @BodyParam("application/json") BinaryData clientProjectedNameModel, RequestOptions requestOptions,
            Context context);

        @Post("/projection/projected-name/property/client")
        @ExpectedResponses({ 204 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<Void> clientSync(@HeaderParam("accept") String accept,
            @BodyParam("application/json") BinaryData clientProjectedNameModel, RequestOptions requestOptions,
            Context context);

        @Post("/projection/projected-name/property/language")
        @ExpectedResponses({ 204 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> language(@HeaderParam("accept") String accept,
            @BodyParam("application/json") BinaryData languageProjectedNameModel, RequestOptions requestOptions,
            Context context);

        @Post("/projection/projected-name/property/language")
        @ExpectedResponses({ 204 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<Void> languageSync(@HeaderParam("accept") String accept,
            @BodyParam("application/json") BinaryData languageProjectedNameModel, RequestOptions requestOptions,
            Context context);

        @Post("/projection/projected-name/property/json-and-client")
        @ExpectedResponses({ 204 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> jsonAndClient(@HeaderParam("accept") String accept,
            @BodyParam("application/json") BinaryData jsonAndClientProjectedNameModel, RequestOptions requestOptions,
            Context context);

        @Post("/projection/projected-name/property/json-and-client")
        @ExpectedResponses({ 204 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<Void> jsonAndClientSync(@HeaderParam("accept") String accept,
            @BodyParam("application/json") BinaryData jsonAndClientProjectedNameModel, RequestOptions requestOptions,
            Context context);
    }

    /**
     * The json operation.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     wireName: boolean (Required)
     * }
     * }</pre>
     * 
     * @param jsonProjectedNameModel The jsonProjectedNameModel parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> jsonWithResponseAsync(BinaryData jsonProjectedNameModel,
        RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.json(accept, jsonProjectedNameModel, requestOptions, context));
    }

    /**
     * The json operation.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     wireName: boolean (Required)
     * }
     * }</pre>
     * 
     * @param jsonProjectedNameModel The jsonProjectedNameModel parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> jsonWithResponse(BinaryData jsonProjectedNameModel, RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.jsonSync(accept, jsonProjectedNameModel, requestOptions, Context.NONE);
    }

    /**
     * The client operation.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     defaultName: boolean (Required)
     * }
     * }</pre>
     * 
     * @param clientProjectedNameModel The clientProjectedNameModel parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> clientWithResponseAsync(BinaryData clientProjectedNameModel,
        RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil
            .withContext(context -> service.client(accept, clientProjectedNameModel, requestOptions, context));
    }

    /**
     * The client operation.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     defaultName: boolean (Required)
     * }
     * }</pre>
     * 
     * @param clientProjectedNameModel The clientProjectedNameModel parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> clientWithResponse(BinaryData clientProjectedNameModel, RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.clientSync(accept, clientProjectedNameModel, requestOptions, Context.NONE);
    }

    /**
     * The language operation.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     defaultName: boolean (Required)
     * }
     * }</pre>
     * 
     * @param languageProjectedNameModel The languageProjectedNameModel parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> languageWithResponseAsync(BinaryData languageProjectedNameModel,
        RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil
            .withContext(context -> service.language(accept, languageProjectedNameModel, requestOptions, context));
    }

    /**
     * The language operation.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     defaultName: boolean (Required)
     * }
     * }</pre>
     * 
     * @param languageProjectedNameModel The languageProjectedNameModel parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> languageWithResponse(BinaryData languageProjectedNameModel, RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.languageSync(accept, languageProjectedNameModel, requestOptions, Context.NONE);
    }

    /**
     * The jsonAndClient operation.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     wireName: boolean (Required)
     * }
     * }</pre>
     * 
     * @param jsonAndClientProjectedNameModel The jsonAndClientProjectedNameModel parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> jsonAndClientWithResponseAsync(BinaryData jsonAndClientProjectedNameModel,
        RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
            context -> service.jsonAndClient(accept, jsonAndClientProjectedNameModel, requestOptions, context));
    }

    /**
     * The jsonAndClient operation.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     wireName: boolean (Required)
     * }
     * }</pre>
     * 
     * @param jsonAndClientProjectedNameModel The jsonAndClientProjectedNameModel parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> jsonAndClientWithResponse(BinaryData jsonAndClientProjectedNameModel,
        RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.jsonAndClientSync(accept, jsonAndClientProjectedNameModel, requestOptions, Context.NONE);
    }
}
