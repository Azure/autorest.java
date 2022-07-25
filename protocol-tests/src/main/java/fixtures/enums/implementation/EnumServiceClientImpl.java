// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.enums.implementation;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.Put;
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
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.BinaryData;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import com.azure.core.util.serializer.JacksonAdapter;
import com.azure.core.util.serializer.SerializerAdapter;
import reactor.core.publisher.Mono;

/** Initializes a new instance of the EnumServiceClient type. */
public final class EnumServiceClientImpl {
    /** The proxy service used to perform REST calls. */
    private final EnumServiceClientService service;

    /** server parameter. */
    private final String host;

    /**
     * Gets server parameter.
     *
     * @return the host value.
     */
    public String getHost() {
        return this.host;
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
     * Initializes an instance of EnumServiceClient client.
     *
     * @param host server parameter.
     */
    public EnumServiceClientImpl(String host) {
        this(
                new HttpPipelineBuilder()
                        .policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy())
                        .build(),
                JacksonAdapter.createDefaultSerializerAdapter(),
                host);
    }

    /**
     * Initializes an instance of EnumServiceClient client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param host server parameter.
     */
    public EnumServiceClientImpl(HttpPipeline httpPipeline, String host) {
        this(httpPipeline, JacksonAdapter.createDefaultSerializerAdapter(), host);
    }

    /**
     * Initializes an instance of EnumServiceClient client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param serializerAdapter The serializer to serialize an object into a string.
     * @param host server parameter.
     */
    public EnumServiceClientImpl(HttpPipeline httpPipeline, SerializerAdapter serializerAdapter, String host) {
        this.httpPipeline = httpPipeline;
        this.serializerAdapter = serializerAdapter;
        this.host = host;
        this.service = RestProxy.create(EnumServiceClientService.class, this.httpPipeline, this.getSerializerAdapter());
    }

    /**
     * The interface defining all the services for EnumServiceClient to be used by the proxy service to perform REST
     * calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "EnumServiceClient")
    private interface EnumServiceClientService {
        @Put("/enums/queryparam")
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
        Mono<Response<Void>> putQueryEnums(
                @HostParam("$host") String host,
                @QueryParam("query-integer-enum") String queryIntegerEnum,
                @QueryParam("query-boolean-enum") String queryBooleanEnum,
                @QueryParam("query-required-enum") String queryRequiredEnum,
                RequestOptions requestOptions,
                Context context);

        @Put("/enums/headerparam")
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
        Mono<Response<Void>> putHeaderEnums(
                @HostParam("$host") String host,
                @HeaderParam("header-required-string-enum") String headerRequiredStringEnum,
                RequestOptions requestOptions,
                Context context);

        @Put("/enums/required/body")
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
        Mono<Response<Void>> putRequiredBodyEnums(
                @HostParam("$host") String host,
                @BodyParam("application/json") BinaryData bodyRequiredEnum,
                RequestOptions requestOptions,
                Context context);

        @Put("/enums/nonrequired/body")
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
        Mono<Response<Void>> putNonRequiredBodyEnums(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);
    }

    /**
     * query enums operation.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>query-non-required-enum</td><td>String</td><td>No</td><td>non required enum with three values. Allowed values: "test1", "test2", "test@&lt;/spec.,i`~!&amp;*-al@char/&gt;".</td></tr>
     * </table>
     *
     * You can add these to a request with {@link RequestOptions#addQueryParam}
     *
     * @param queryIntegerEnum integer enum with three values. Allowed values: 100, 200, 300.
     * @param queryBooleanEnum boolean enum with two values. Allowed values: true, false.
     * @param queryRequiredEnum required enum with three values. Allowed values: "test1", "test2",
     *     "test@&lt;/spec.,i`~!&amp;*-al@char/&gt;".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putQueryEnumsWithResponseAsync(
            String queryIntegerEnum, String queryBooleanEnum, String queryRequiredEnum, RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context ->
                        service.putQueryEnums(
                                this.getHost(),
                                queryIntegerEnum,
                                queryBooleanEnum,
                                queryRequiredEnum,
                                requestOptions,
                                context));
    }

    /**
     * query enums operation.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>query-non-required-enum</td><td>String</td><td>No</td><td>non required enum with three values. Allowed values: "test1", "test2", "test@&lt;/spec.,i`~!&amp;*-al@char/&gt;".</td></tr>
     * </table>
     *
     * You can add these to a request with {@link RequestOptions#addQueryParam}
     *
     * @param queryIntegerEnum integer enum with three values. Allowed values: 100, 200, 300.
     * @param queryBooleanEnum boolean enum with two values. Allowed values: true, false.
     * @param queryRequiredEnum required enum with three values. Allowed values: "test1", "test2",
     *     "test@&lt;/spec.,i`~!&amp;*-al@char/&gt;".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putQueryEnumsWithResponseAsync(
            String queryIntegerEnum,
            String queryBooleanEnum,
            String queryRequiredEnum,
            RequestOptions requestOptions,
            Context context) {
        return service.putQueryEnums(
                this.getHost(), queryIntegerEnum, queryBooleanEnum, queryRequiredEnum, requestOptions, context);
    }

    /**
     * query enums operation.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>query-non-required-enum</td><td>String</td><td>No</td><td>non required enum with three values. Allowed values: "test1", "test2", "test@&lt;/spec.,i`~!&amp;*-al@char/&gt;".</td></tr>
     * </table>
     *
     * You can add these to a request with {@link RequestOptions#addQueryParam}
     *
     * @param queryIntegerEnum integer enum with three values. Allowed values: 100, 200, 300.
     * @param queryBooleanEnum boolean enum with two values. Allowed values: true, false.
     * @param queryRequiredEnum required enum with three values. Allowed values: "test1", "test2",
     *     "test@&lt;/spec.,i`~!&amp;*-al@char/&gt;".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putQueryEnumsWithResponse(
            String queryIntegerEnum, String queryBooleanEnum, String queryRequiredEnum, RequestOptions requestOptions) {
        return putQueryEnumsWithResponseAsync(queryIntegerEnum, queryBooleanEnum, queryRequiredEnum, requestOptions)
                .block();
    }

    /**
     * enums header operation.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>header-non-required-string-enum</td><td>String</td><td>No</td><td>non required string enum with three values. Allowed values: "test1", "test2", "test@&lt;/spec.,i`~!&amp;*-al@char/&gt;".</td></tr>
     * </table>
     *
     * You can add these to a request with {@link RequestOptions#addHeader}
     *
     * @param headerRequiredStringEnum required string enum with three values. Allowed values: "test1", "test2",
     *     "test@&lt;/spec.,i`~!&amp;*-al@char/&gt;".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putHeaderEnumsWithResponseAsync(
            String headerRequiredStringEnum, RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.putHeaderEnums(this.getHost(), headerRequiredStringEnum, requestOptions, context));
    }

    /**
     * enums header operation.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>header-non-required-string-enum</td><td>String</td><td>No</td><td>non required string enum with three values. Allowed values: "test1", "test2", "test@&lt;/spec.,i`~!&amp;*-al@char/&gt;".</td></tr>
     * </table>
     *
     * You can add these to a request with {@link RequestOptions#addHeader}
     *
     * @param headerRequiredStringEnum required string enum with three values. Allowed values: "test1", "test2",
     *     "test@&lt;/spec.,i`~!&amp;*-al@char/&gt;".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putHeaderEnumsWithResponseAsync(
            String headerRequiredStringEnum, RequestOptions requestOptions, Context context) {
        return service.putHeaderEnums(this.getHost(), headerRequiredStringEnum, requestOptions, context);
    }

    /**
     * enums header operation.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>header-non-required-string-enum</td><td>String</td><td>No</td><td>non required string enum with three values. Allowed values: "test1", "test2", "test@&lt;/spec.,i`~!&amp;*-al@char/&gt;".</td></tr>
     * </table>
     *
     * You can add these to a request with {@link RequestOptions#addHeader}
     *
     * @param headerRequiredStringEnum required string enum with three values. Allowed values: "test1", "test2",
     *     "test@&lt;/spec.,i`~!&amp;*-al@char/&gt;".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putHeaderEnumsWithResponse(String headerRequiredStringEnum, RequestOptions requestOptions) {
        return putHeaderEnumsWithResponseAsync(headerRequiredStringEnum, requestOptions).block();
    }

    /**
     * enums required body operation.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * String(test1/test2/test@</spec.,i`~!&*-al@char/>)
     * }</pre>
     *
     * @param bodyRequiredEnum body required enum.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putRequiredBodyEnumsWithResponseAsync(
            BinaryData bodyRequiredEnum, RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.putRequiredBodyEnums(this.getHost(), bodyRequiredEnum, requestOptions, context));
    }

    /**
     * enums required body operation.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * String(test1/test2/test@</spec.,i`~!&*-al@char/>)
     * }</pre>
     *
     * @param bodyRequiredEnum body required enum.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putRequiredBodyEnumsWithResponseAsync(
            BinaryData bodyRequiredEnum, RequestOptions requestOptions, Context context) {
        return service.putRequiredBodyEnums(this.getHost(), bodyRequiredEnum, requestOptions, context);
    }

    /**
     * enums required body operation.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * String(test1/test2/test@</spec.,i`~!&*-al@char/>)
     * }</pre>
     *
     * @param bodyRequiredEnum body required enum.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putRequiredBodyEnumsWithResponse(BinaryData bodyRequiredEnum, RequestOptions requestOptions) {
        return putRequiredBodyEnumsWithResponseAsync(bodyRequiredEnum, requestOptions).block();
    }

    /**
     * enums non required body operation.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>Content-Type</td><td>String</td><td>No</td><td>The content type. Allowed values: "application/json".</td></tr>
     * </table>
     *
     * You can add these to a request with {@link RequestOptions#addHeader}
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * String(test1/test2)
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putNonRequiredBodyEnumsWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.putNonRequiredBodyEnums(this.getHost(), requestOptions, context));
    }

    /**
     * enums non required body operation.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>Content-Type</td><td>String</td><td>No</td><td>The content type. Allowed values: "application/json".</td></tr>
     * </table>
     *
     * You can add these to a request with {@link RequestOptions#addHeader}
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * String(test1/test2)
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putNonRequiredBodyEnumsWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.putNonRequiredBodyEnums(this.getHost(), requestOptions, context);
    }

    /**
     * enums non required body operation.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>Content-Type</td><td>String</td><td>No</td><td>The content type. Allowed values: "application/json".</td></tr>
     * </table>
     *
     * You can add these to a request with {@link RequestOptions#addHeader}
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * String(test1/test2)
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putNonRequiredBodyEnumsWithResponse(RequestOptions requestOptions) {
        return putNonRequiredBodyEnumsWithResponseAsync(requestOptions).block();
    }
}
