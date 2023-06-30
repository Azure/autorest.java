// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.resiliency.servicedriven.implementation;

import com.azure.core.annotation.Delete;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.Head;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.QueryParam;
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
import com.resiliency.servicedriven.ServiceDrivenServiceVersion;
import reactor.core.publisher.Mono;

/** Initializes a new instance of the ResiliencyServiceDrivenClient type. */
public final class ResiliencyServiceDrivenClientImpl {
    /** The proxy service used to perform REST calls. */
    private final ResiliencyServiceDrivenClientService service;

    /**
     * Pass in either 'v1' or 'v2'. This represents a version of the service deployment in history. 'v1' is for the
     * deployment when the service had only one api version. 'v2' is for the deployment when the service had
     * api-versions 'v1' and 'v2'.
     */
    private final String serviceDeploymentVersion;

    /**
     * Gets Pass in either 'v1' or 'v2'. This represents a version of the service deployment in history. 'v1' is for the
     * deployment when the service had only one api version. 'v2' is for the deployment when the service had
     * api-versions 'v1' and 'v2'.
     *
     * @return the serviceDeploymentVersion value.
     */
    public String getServiceDeploymentVersion() {
        return this.serviceDeploymentVersion;
    }

    /** Service version. */
    private final ServiceDrivenServiceVersion serviceVersion;

    /**
     * Gets Service version.
     *
     * @return the serviceVersion value.
     */
    public ServiceDrivenServiceVersion getServiceVersion() {
        return this.serviceVersion;
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
     * Initializes an instance of ResiliencyServiceDrivenClient client.
     *
     * @param serviceDeploymentVersion Pass in either 'v1' or 'v2'. This represents a version of the service deployment
     *     in history. 'v1' is for the deployment when the service had only one api version. 'v2' is for the deployment
     *     when the service had api-versions 'v1' and 'v2'.
     * @param serviceVersion Service version.
     */
    public ResiliencyServiceDrivenClientImpl(
            String serviceDeploymentVersion, ServiceDrivenServiceVersion serviceVersion) {
        this(
                new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy()).build(),
                JacksonAdapter.createDefaultSerializerAdapter(),
                serviceDeploymentVersion,
                serviceVersion);
    }

    /**
     * Initializes an instance of ResiliencyServiceDrivenClient client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param serviceDeploymentVersion Pass in either 'v1' or 'v2'. This represents a version of the service deployment
     *     in history. 'v1' is for the deployment when the service had only one api version. 'v2' is for the deployment
     *     when the service had api-versions 'v1' and 'v2'.
     * @param serviceVersion Service version.
     */
    public ResiliencyServiceDrivenClientImpl(
            HttpPipeline httpPipeline, String serviceDeploymentVersion, ServiceDrivenServiceVersion serviceVersion) {
        this(httpPipeline, JacksonAdapter.createDefaultSerializerAdapter(), serviceDeploymentVersion, serviceVersion);
    }

    /**
     * Initializes an instance of ResiliencyServiceDrivenClient client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param serializerAdapter The serializer to serialize an object into a string.
     * @param serviceDeploymentVersion Pass in either 'v1' or 'v2'. This represents a version of the service deployment
     *     in history. 'v1' is for the deployment when the service had only one api version. 'v2' is for the deployment
     *     when the service had api-versions 'v1' and 'v2'.
     * @param serviceVersion Service version.
     */
    public ResiliencyServiceDrivenClientImpl(
            HttpPipeline httpPipeline,
            SerializerAdapter serializerAdapter,
            String serviceDeploymentVersion,
            ServiceDrivenServiceVersion serviceVersion) {
        this.httpPipeline = httpPipeline;
        this.serializerAdapter = serializerAdapter;
        this.serviceDeploymentVersion = serviceDeploymentVersion;
        this.serviceVersion = serviceVersion;
        this.service =
                RestProxy.create(
                        ResiliencyServiceDrivenClientService.class, this.httpPipeline, this.getSerializerAdapter());
    }

    /**
     * The interface defining all the services for ResiliencyServiceDrivenClient to be used by the proxy service to
     * perform REST calls.
     */
    @Host(
            "http://localhost:3000/resiliency/service-driven/client:v2/service:{serviceDeploymentVersion}/api-version:{apiVersion}")
    @ServiceInterface(name = "ResiliencyServiceDri")
    public interface ResiliencyServiceDrivenClientService {
        @Delete("/add-operation")
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
        Mono<Response<Void>> addOperation(
                @HostParam("serviceDeploymentVersion") String serviceDeploymentVersion,
                @HostParam("apiVersion") String apiVersion,
                @HeaderParam("accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Delete("/add-operation")
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
        Response<Void> addOperationSync(
                @HostParam("serviceDeploymentVersion") String serviceDeploymentVersion,
                @HostParam("apiVersion") String apiVersion,
                @HeaderParam("accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Head("/add-optional-param/from-none")
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
        Mono<Response<Void>> fromNone(
                @HostParam("serviceDeploymentVersion") String serviceDeploymentVersion,
                @HostParam("apiVersion") String apiVersion,
                @HeaderParam("accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Head("/add-optional-param/from-none")
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
        Response<Void> fromNoneSync(
                @HostParam("serviceDeploymentVersion") String serviceDeploymentVersion,
                @HostParam("apiVersion") String apiVersion,
                @HeaderParam("accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/add-optional-param/from-one-required")
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
        Mono<Response<Void>> fromOneRequired(
                @HostParam("serviceDeploymentVersion") String serviceDeploymentVersion,
                @HostParam("apiVersion") String apiVersion,
                @QueryParam("parameter") String parameter,
                @HeaderParam("accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/add-optional-param/from-one-required")
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
        Response<Void> fromOneRequiredSync(
                @HostParam("serviceDeploymentVersion") String serviceDeploymentVersion,
                @HostParam("apiVersion") String apiVersion,
                @QueryParam("parameter") String parameter,
                @HeaderParam("accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/add-optional-param/from-one-optional")
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
        Mono<Response<Void>> fromOneOptional(
                @HostParam("serviceDeploymentVersion") String serviceDeploymentVersion,
                @HostParam("apiVersion") String apiVersion,
                @HeaderParam("accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/add-optional-param/from-one-optional")
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
        Response<Void> fromOneOptionalSync(
                @HostParam("serviceDeploymentVersion") String serviceDeploymentVersion,
                @HostParam("apiVersion") String apiVersion,
                @HeaderParam("accept") String accept,
                RequestOptions requestOptions,
                Context context);
    }

    /**
     * Added operation.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> addOperationWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context ->
                        service.addOperation(
                                this.getServiceDeploymentVersion(),
                                this.getServiceVersion().getVersion(),
                                accept,
                                requestOptions,
                                context));
    }

    /**
     * Added operation.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> addOperationWithResponse(RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.addOperationSync(
                this.getServiceDeploymentVersion(),
                this.getServiceVersion().getVersion(),
                accept,
                requestOptions,
                Context.NONE);
    }

    /**
     * Test that grew up from accepting no parameters to an optional input parameter.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>new-parameter</td><td>String</td><td>No</td><td>I'm a new input optional parameter</td></tr>
     * </table>
     *
     * You can add these to a request with {@link RequestOptions#addQueryParam}
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> fromNoneWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context ->
                        service.fromNone(
                                this.getServiceDeploymentVersion(),
                                this.getServiceVersion().getVersion(),
                                accept,
                                requestOptions,
                                context));
    }

    /**
     * Test that grew up from accepting no parameters to an optional input parameter.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>new-parameter</td><td>String</td><td>No</td><td>I'm a new input optional parameter</td></tr>
     * </table>
     *
     * You can add these to a request with {@link RequestOptions#addQueryParam}
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> fromNoneWithResponse(RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.fromNoneSync(
                this.getServiceDeploymentVersion(),
                this.getServiceVersion().getVersion(),
                accept,
                requestOptions,
                Context.NONE);
    }

    /**
     * Operation that grew up from accepting one required parameter to accepting a required parameter and an optional
     * parameter.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>new-parameter</td><td>String</td><td>No</td><td>I'm a new input optional parameter</td></tr>
     * </table>
     *
     * You can add these to a request with {@link RequestOptions#addQueryParam}
     *
     * @param parameter I am a required parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> fromOneRequiredWithResponseAsync(String parameter, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context ->
                        service.fromOneRequired(
                                this.getServiceDeploymentVersion(),
                                this.getServiceVersion().getVersion(),
                                parameter,
                                accept,
                                requestOptions,
                                context));
    }

    /**
     * Operation that grew up from accepting one required parameter to accepting a required parameter and an optional
     * parameter.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>new-parameter</td><td>String</td><td>No</td><td>I'm a new input optional parameter</td></tr>
     * </table>
     *
     * You can add these to a request with {@link RequestOptions#addQueryParam}
     *
     * @param parameter I am a required parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> fromOneRequiredWithResponse(String parameter, RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.fromOneRequiredSync(
                this.getServiceDeploymentVersion(),
                this.getServiceVersion().getVersion(),
                parameter,
                accept,
                requestOptions,
                Context.NONE);
    }

    /**
     * Tests that we can grow up an operation from accepting one optional parameter to accepting two optional
     * parameters.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>parameter</td><td>String</td><td>No</td><td>I am an optional parameter</td></tr>
     *     <tr><td>new-parameter</td><td>String</td><td>No</td><td>I'm a new input optional parameter</td></tr>
     * </table>
     *
     * You can add these to a request with {@link RequestOptions#addQueryParam}
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> fromOneOptionalWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context ->
                        service.fromOneOptional(
                                this.getServiceDeploymentVersion(),
                                this.getServiceVersion().getVersion(),
                                accept,
                                requestOptions,
                                context));
    }

    /**
     * Tests that we can grow up an operation from accepting one optional parameter to accepting two optional
     * parameters.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>parameter</td><td>String</td><td>No</td><td>I am an optional parameter</td></tr>
     *     <tr><td>new-parameter</td><td>String</td><td>No</td><td>I'm a new input optional parameter</td></tr>
     * </table>
     *
     * You can add these to a request with {@link RequestOptions#addQueryParam}
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> fromOneOptionalWithResponse(RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.fromOneOptionalSync(
                this.getServiceDeploymentVersion(),
                this.getServiceVersion().getVersion(),
                accept,
                requestOptions,
                Context.NONE);
    }
}
