// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.client.structure.service.implementation;

import com.azure.core.annotation.ExpectedResponses;
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
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import com.azure.core.util.serializer.JacksonAdapter;
import com.azure.core.util.serializer.SerializerAdapter;
import reactor.core.publisher.Mono;

/** Initializes a new instance of the ServiceClientClient type. */
public final class ServiceClientClientImpl {
    /** The proxy service used to perform REST calls. */
    private final ServiceClientClientService service;

    /** Need to be set as 'http://localhost:3000' in client. */
    private final String endpoint;

    /**
     * Gets Need to be set as 'http://localhost:3000' in client.
     *
     * @return the endpoint value.
     */
    public String getEndpoint() {
        return this.endpoint;
    }

    /** Need to be set as 'default', 'multi-client', 'renamed-operation', 'two-operation-group' in client. */
    private final String client;

    /**
     * Gets Need to be set as 'default', 'multi-client', 'renamed-operation', 'two-operation-group' in client.
     *
     * @return the client value.
     */
    public String getClient() {
        return this.client;
    }

    /** The HTTP pipeline to send requests through. */
    private final HttpPipeline httpPipeline;

    /**
     * Gets The HTTP pipeline to send requests through.
     *
     * @return the httpPipeline value.
     */
    public HttpPipeline getHttpPipeline() {
        return this.httpPipeline;
    }

    /** The serializer to serialize an object into a string. */
    private final SerializerAdapter serializerAdapter;

    /**
     * Gets The serializer to serialize an object into a string.
     *
     * @return the serializerAdapter value.
     */
    public SerializerAdapter getSerializerAdapter() {
        return this.serializerAdapter;
    }

    /**
     * Initializes an instance of ServiceClientClient client.
     *
     * @param endpoint Need to be set as 'http://localhost:3000' in client.
     * @param client Need to be set as 'default', 'multi-client', 'renamed-operation', 'two-operation-group' in client.
     */
    public ServiceClientClientImpl(String endpoint, String client) {
        this(
                new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy()).build(),
                JacksonAdapter.createDefaultSerializerAdapter(),
                endpoint,
                client);
    }

    /**
     * Initializes an instance of ServiceClientClient client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param endpoint Need to be set as 'http://localhost:3000' in client.
     * @param client Need to be set as 'default', 'multi-client', 'renamed-operation', 'two-operation-group' in client.
     */
    public ServiceClientClientImpl(HttpPipeline httpPipeline, String endpoint, String client) {
        this(httpPipeline, JacksonAdapter.createDefaultSerializerAdapter(), endpoint, client);
    }

    /**
     * Initializes an instance of ServiceClientClient client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param serializerAdapter The serializer to serialize an object into a string.
     * @param endpoint Need to be set as 'http://localhost:3000' in client.
     * @param client Need to be set as 'default', 'multi-client', 'renamed-operation', 'two-operation-group' in client.
     */
    public ServiceClientClientImpl(
            HttpPipeline httpPipeline, SerializerAdapter serializerAdapter, String endpoint, String client) {
        this.httpPipeline = httpPipeline;
        this.serializerAdapter = serializerAdapter;
        this.endpoint = endpoint;
        this.client = client;
        this.service =
                RestProxy.create(ServiceClientClientService.class, this.httpPipeline, this.getSerializerAdapter());
    }

    /**
     * The interface defining all the services for ServiceClientClient to be used by the proxy service to perform REST
     * calls.
     */
    @Host("{endpoint}/client/structure/{client}")
    @ServiceInterface(name = "ServiceClientClient")
    public interface ServiceClientClientService {
        @Post("/one")
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
        Mono<Response<Void>> one(
                @HostParam("endpoint") String endpoint,
                @HostParam("client") String client,
                @HeaderParam("accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/one")
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
        Response<Void> oneSync(
                @HostParam("endpoint") String endpoint,
                @HostParam("client") String client,
                @HeaderParam("accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/two")
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
        Mono<Response<Void>> two(
                @HostParam("endpoint") String endpoint,
                @HostParam("client") String client,
                @HeaderParam("accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/two")
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
        Response<Void> twoSync(
                @HostParam("endpoint") String endpoint,
                @HostParam("client") String client,
                @HeaderParam("accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/three")
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
        Mono<Response<Void>> three(
                @HostParam("endpoint") String endpoint,
                @HostParam("client") String client,
                @HeaderParam("accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/three")
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
        Response<Void> threeSync(
                @HostParam("endpoint") String endpoint,
                @HostParam("client") String client,
                @HeaderParam("accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/four")
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
        Mono<Response<Void>> four(
                @HostParam("endpoint") String endpoint,
                @HostParam("client") String client,
                @HeaderParam("accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/four")
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
        Response<Void> fourSync(
                @HostParam("endpoint") String endpoint,
                @HostParam("client") String client,
                @HeaderParam("accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/five")
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
        Mono<Response<Void>> five(
                @HostParam("endpoint") String endpoint,
                @HostParam("client") String client,
                @HeaderParam("accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/five")
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
        Response<Void> fiveSync(
                @HostParam("endpoint") String endpoint,
                @HostParam("client") String client,
                @HeaderParam("accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/six")
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
        Mono<Response<Void>> six(
                @HostParam("endpoint") String endpoint,
                @HostParam("client") String client,
                @HeaderParam("accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/six")
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
        Response<Void> sixSync(
                @HostParam("endpoint") String endpoint,
                @HostParam("client") String client,
                @HeaderParam("accept") String accept,
                RequestOptions requestOptions,
                Context context);
    }

    /**
     * The one operation.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> oneWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.one(this.getEndpoint(), this.getClient(), accept, requestOptions, context));
    }

    /**
     * The one operation.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> oneWithResponse(RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.oneSync(this.getEndpoint(), this.getClient(), accept, requestOptions, Context.NONE);
    }

    /**
     * The two operation.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> twoWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.two(this.getEndpoint(), this.getClient(), accept, requestOptions, context));
    }

    /**
     * The two operation.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> twoWithResponse(RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.twoSync(this.getEndpoint(), this.getClient(), accept, requestOptions, Context.NONE);
    }

    /**
     * The three operation.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> threeWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.three(this.getEndpoint(), this.getClient(), accept, requestOptions, context));
    }

    /**
     * The three operation.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> threeWithResponse(RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.threeSync(this.getEndpoint(), this.getClient(), accept, requestOptions, Context.NONE);
    }

    /**
     * The four operation.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> fourWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.four(this.getEndpoint(), this.getClient(), accept, requestOptions, context));
    }

    /**
     * The four operation.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> fourWithResponse(RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.fourSync(this.getEndpoint(), this.getClient(), accept, requestOptions, Context.NONE);
    }

    /**
     * The five operation.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> fiveWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.five(this.getEndpoint(), this.getClient(), accept, requestOptions, context));
    }

    /**
     * The five operation.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> fiveWithResponse(RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.fiveSync(this.getEndpoint(), this.getClient(), accept, requestOptions, Context.NONE);
    }

    /**
     * The six operation.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> sixWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.six(this.getEndpoint(), this.getClient(), accept, requestOptions, context));
    }

    /**
     * The six operation.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> sixWithResponse(RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.sixSync(this.getEndpoint(), this.getClient(), accept, requestOptions, Context.NONE);
    }
}
