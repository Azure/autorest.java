// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.specialheaders.repeatability.implementation;

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
import com.azure.core.http.HttpHeaderName;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.http.rest.SimpleResponse;
import com.azure.core.util.Context;
import com.azure.core.util.DateTimeRfc1123;
import com.azure.core.util.FluxUtil;
import com.azure.core.util.UrlBuilder;
import com.azure.core.util.logging.ClientLogger;
import com.azure.core.util.serializer.JacksonAdapter;
import com.azure.core.util.serializer.SerializerAdapter;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import reactor.core.publisher.Mono;

/**
 * Initializes a new instance of the RepeatabilityClient type.
 */
public final class RepeatabilityClientImpl {
    /**
     * The proxy service used to perform REST calls.
     */
    private final RepeatabilityClientService service;

    /**
     * The HTTP pipeline to send requests through.
     */
    private final HttpPipeline httpPipeline;

    /**
     * Gets The HTTP pipeline to send requests through.
     * 
     * @return the httpPipeline value.
     */
    public HttpPipeline getHttpPipeline() {
        return this.httpPipeline;
    }

    /**
     * The serializer to serialize an object into a string.
     */
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
     * Initializes an instance of RepeatabilityClient client.
     */
    public RepeatabilityClientImpl() {
        this(new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy()).build(), JacksonAdapter.createDefaultSerializerAdapter());
    }

    /**
     * Initializes an instance of RepeatabilityClient client.
     * 
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    public RepeatabilityClientImpl(HttpPipeline httpPipeline) {
        this(httpPipeline, JacksonAdapter.createDefaultSerializerAdapter());
    }

    /**
     * Initializes an instance of RepeatabilityClient client.
     * 
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param serializerAdapter The serializer to serialize an object into a string.
     */
    public RepeatabilityClientImpl(HttpPipeline httpPipeline, SerializerAdapter serializerAdapter) {
        this.httpPipeline = httpPipeline;
        this.serializerAdapter = serializerAdapter;
        this.service = RestProxy.create(RepeatabilityClientService.class, this.httpPipeline, this.getSerializerAdapter());
    }

    /**
     * The interface defining all the services for RepeatabilityClient to be used by the proxy service to perform REST
     * calls.
     */
    @Host("http://localhost:3000")
    @ServiceInterface(name = "RepeatabilityClient")
    public interface RepeatabilityClientService {
        @Post("/special-headers/repeatability/immediateSuccess")
        @ExpectedResponses({204})
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = {401})
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = {404})
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = {409})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> immediateSuccess(@HeaderParam("accept") String accept, RequestOptions requestOptions, Context context);

        @Post("/special-headers/repeatability/immediateSuccess")
        @ExpectedResponses({204})
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = {401})
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = {404})
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = {409})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<Void> immediateSuccessSync(@HeaderParam("accept") String accept, RequestOptions requestOptions, Context context);
    }

    /**
     * Check we recognize Repeatability-Request-ID and Repeatability-First-Sent.
     * <p><strong>Header Parameters</strong></p>
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>repeatability-request-id</td><td>String</td><td>No</td><td>Repeatability request ID header</td></tr>
     *     <tr><td>repeatability-first-sent</td><td>String</td><td>No</td><td>Repeatability first sent header as HTTP-date</td></tr>
     * </table>
     * You can add these to a request with {@link RequestOptions#addHeader}
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> immediateSuccessWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        RequestOptions requestOptionsLocal = requestOptions == null ? new RequestOptions() : requestOptions;
        String repeatabilityRequestId = UUID.randomUUID().toString();
        String repeatabilityFirstSent = DateTimeRfc1123.toRfc1123String(OffsetDateTime.now());
        requestOptionsLocal.addRequestCallback(requestLocal -> {
            if (requestLocal.getHeaders().get(HttpHeaderName.fromString("repeatability-request-id")) == null) {
                requestLocal.getHeaders().set(HttpHeaderName.fromString("repeatability-request-id"), repeatabilityRequestId);
            }
        });
        requestOptionsLocal.addRequestCallback(requestLocal -> {
            if (requestLocal.getHeaders().get(HttpHeaderName.fromString("repeatability-first-sent")) == null) {
                requestLocal.getHeaders().set(HttpHeaderName.fromString("repeatability-first-sent"), repeatabilityFirstSent);
            }
        });
        return FluxUtil.withContext(context -> service.immediateSuccess(accept, requestOptionsLocal, context));
    }

    /**
     * Check we recognize Repeatability-Request-ID and Repeatability-First-Sent.
     * <p><strong>Header Parameters</strong></p>
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>repeatability-request-id</td><td>String</td><td>No</td><td>Repeatability request ID header</td></tr>
     *     <tr><td>repeatability-first-sent</td><td>String</td><td>No</td><td>Repeatability first sent header as HTTP-date</td></tr>
     * </table>
     * You can add these to a request with {@link RequestOptions#addHeader}
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> immediateSuccessWithResponse(RequestOptions requestOptions) {
        final String accept = "application/json";
        RequestOptions requestOptionsLocal = requestOptions == null ? new RequestOptions() : requestOptions;
        String repeatabilityRequestId = UUID.randomUUID().toString();
        String repeatabilityFirstSent = DateTimeRfc1123.toRfc1123String(OffsetDateTime.now());
        requestOptionsLocal.addRequestCallback(requestLocal -> {
            if (requestLocal.getHeaders().get(HttpHeaderName.fromString("repeatability-request-id")) == null) {
                requestLocal.getHeaders().set(HttpHeaderName.fromString("repeatability-request-id"), repeatabilityRequestId);
            }
        });
        requestOptionsLocal.addRequestCallback(requestLocal -> {
            if (requestLocal.getHeaders().get(HttpHeaderName.fromString("repeatability-first-sent")) == null) {
                requestLocal.getHeaders().set(HttpHeaderName.fromString("repeatability-first-sent"), repeatabilityFirstSent);
            }
        });
        return service.immediateSuccessSync(accept, requestOptionsLocal, Context.NONE);
    }
}
