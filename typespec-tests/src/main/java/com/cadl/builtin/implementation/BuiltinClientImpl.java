// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.cadl.builtin.implementation;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.Post;
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

/** Initializes a new instance of the BuiltinClient type. */
public final class BuiltinClientImpl {
    /** The proxy service used to perform REST calls. */
    private final BuiltinClientService service;

    /** Server parameter. */
    private final String endpoint;

    /**
     * Gets Server parameter.
     *
     * @return the endpoint value.
     */
    public String getEndpoint() {
        return this.endpoint;
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
     * Initializes an instance of BuiltinClient client.
     *
     * @param endpoint Server parameter.
     */
    public BuiltinClientImpl(String endpoint) {
        this(
                new HttpPipelineBuilder()
                        .policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy())
                        .build(),
                JacksonAdapter.createDefaultSerializerAdapter(),
                endpoint);
    }

    /**
     * Initializes an instance of BuiltinClient client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param endpoint Server parameter.
     */
    public BuiltinClientImpl(HttpPipeline httpPipeline, String endpoint) {
        this(httpPipeline, JacksonAdapter.createDefaultSerializerAdapter(), endpoint);
    }

    /**
     * Initializes an instance of BuiltinClient client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param serializerAdapter The serializer to serialize an object into a string.
     * @param endpoint Server parameter.
     */
    public BuiltinClientImpl(HttpPipeline httpPipeline, SerializerAdapter serializerAdapter, String endpoint) {
        this.httpPipeline = httpPipeline;
        this.serializerAdapter = serializerAdapter;
        this.endpoint = endpoint;
        this.service = RestProxy.create(BuiltinClientService.class, this.httpPipeline, this.getSerializerAdapter());
    }

    /**
     * The interface defining all the services for BuiltinClient to be used by the proxy service to perform REST calls.
     */
    @Host("{endpoint}")
    @ServiceInterface(name = "BuiltinClient")
    public interface BuiltinClientService {
        @Get("/builtin")
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
        Mono<Response<BinaryData>> read(
                @HostParam("endpoint") String endpoint,
                @QueryParam("query") String queryParam,
                @QueryParam(value = "query-encoded", encoded = true) String queryParamEncoded,
                @HeaderParam("accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/builtin")
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
        Response<BinaryData> readSync(
                @HostParam("endpoint") String endpoint,
                @QueryParam("query") String queryParam,
                @QueryParam(value = "query-encoded", encoded = true) String queryParamEncoded,
                @HeaderParam("accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/builtin")
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
        Mono<Response<Void>> write(
                @HostParam("endpoint") String endpoint,
                @HeaderParam("accept") String accept,
                @BodyParam("application/json") BinaryData body,
                RequestOptions requestOptions,
                Context context);

        @Post("/builtin")
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
        Response<Void> writeSync(
                @HostParam("endpoint") String endpoint,
                @HeaderParam("accept") String accept,
                @BodyParam("application/json") BinaryData body,
                RequestOptions requestOptions,
                Context context);
    }

    /**
     * The read operation.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>filter</td><td>String</td><td>No</td><td>The filter parameter</td></tr>
     *     <tr><td>query-opt</td><td>String</td><td>No</td><td>The queryParamOptional parameter</td></tr>
     *     <tr><td>query-opt-encoded</td><td>String</td><td>No</td><td>The queryParamOptionalEncoded parameter</td></tr>
     * </table>
     *
     * You can add these to a request with {@link RequestOptions#addQueryParam}
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>x-ms-date</td><td>OffsetDateTime</td><td>No</td><td>The dateTime parameter</td></tr>
     * </table>
     *
     * You can add these to a request with {@link RequestOptions#addHeader}
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     formatString (Required): {
     *         base64Encoded: Base64Url (Required)
     *         binary: byte[] (Required)
     *         dateTime: OffsetDateTime (Required)
     *         dateTimeRfc1123: DateTimeRfc1123 (Required)
     *         password: String (Required)
     *         uri: String (Required)
     *         extensibleEnum: String(Value1/Value2) (Required)
     *         extensibleEnumScalar: String(Value1/Value2) (Required)
     *     }
     *     boolean: boolean (Required)
     *     string: String (Required)
     *     bytes: byte[] (Required)
     *     int: int (Required)
     *     safeint: long (Required)
     *     long: long (Required)
     *     float: double (Required)
     *     double: double (Required)
     *     duration: Duration (Required)
     *     date: LocalDate (Required)
     *     dateTime: OffsetDateTime (Required)
     *     stringList (Required): [
     *         String (Required)
     *     ]
     *     bytesDict (Required): {
     *         String: byte[] (Required)
     *     }
     *     url: String (Required)
     *     nullableFloatDict (Required): {
     *         String: double (Required)
     *     }
     *     encoded (Required): {
     *         timeInSeconds: Long (Optional)
     *         timeInSecondsFraction: Double (Optional)
     *         dateTime: OffsetDateTime (Optional)
     *         dateTimeRfc7231: DateTimeRfc1123 (Optional)
     *         unixTimestamp: Long (Optional)
     *         base64: byte[] (Optional)
     *         base64url: Base64Url (Optional)
     *     }
     * }
     * }</pre>
     *
     * @param queryParam The queryParam parameter.
     * @param queryParamEncoded The queryParamEncoded parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> readWithResponseAsync(
            String queryParam, String queryParamEncoded, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context ->
                        service.read(
                                this.getEndpoint(), queryParam, queryParamEncoded, accept, requestOptions, context));
    }

    /**
     * The read operation.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>filter</td><td>String</td><td>No</td><td>The filter parameter</td></tr>
     *     <tr><td>query-opt</td><td>String</td><td>No</td><td>The queryParamOptional parameter</td></tr>
     *     <tr><td>query-opt-encoded</td><td>String</td><td>No</td><td>The queryParamOptionalEncoded parameter</td></tr>
     * </table>
     *
     * You can add these to a request with {@link RequestOptions#addQueryParam}
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>x-ms-date</td><td>OffsetDateTime</td><td>No</td><td>The dateTime parameter</td></tr>
     * </table>
     *
     * You can add these to a request with {@link RequestOptions#addHeader}
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     formatString (Required): {
     *         base64Encoded: Base64Url (Required)
     *         binary: byte[] (Required)
     *         dateTime: OffsetDateTime (Required)
     *         dateTimeRfc1123: DateTimeRfc1123 (Required)
     *         password: String (Required)
     *         uri: String (Required)
     *         extensibleEnum: String(Value1/Value2) (Required)
     *         extensibleEnumScalar: String(Value1/Value2) (Required)
     *     }
     *     boolean: boolean (Required)
     *     string: String (Required)
     *     bytes: byte[] (Required)
     *     int: int (Required)
     *     safeint: long (Required)
     *     long: long (Required)
     *     float: double (Required)
     *     double: double (Required)
     *     duration: Duration (Required)
     *     date: LocalDate (Required)
     *     dateTime: OffsetDateTime (Required)
     *     stringList (Required): [
     *         String (Required)
     *     ]
     *     bytesDict (Required): {
     *         String: byte[] (Required)
     *     }
     *     url: String (Required)
     *     nullableFloatDict (Required): {
     *         String: double (Required)
     *     }
     *     encoded (Required): {
     *         timeInSeconds: Long (Optional)
     *         timeInSecondsFraction: Double (Optional)
     *         dateTime: OffsetDateTime (Optional)
     *         dateTimeRfc7231: DateTimeRfc1123 (Optional)
     *         unixTimestamp: Long (Optional)
     *         base64: byte[] (Optional)
     *         base64url: Base64Url (Optional)
     *     }
     * }
     * }</pre>
     *
     * @param queryParam The queryParam parameter.
     * @param queryParamEncoded The queryParamEncoded parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> readWithResponse(
            String queryParam, String queryParamEncoded, RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.readSync(
                this.getEndpoint(), queryParam, queryParamEncoded, accept, requestOptions, Context.NONE);
    }

    /**
     * The write operation.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     formatString (Required): {
     *         base64Encoded: Base64Url (Required)
     *         binary: byte[] (Required)
     *         dateTime: OffsetDateTime (Required)
     *         dateTimeRfc1123: DateTimeRfc1123 (Required)
     *         password: String (Required)
     *         uri: String (Required)
     *         extensibleEnum: String(Value1/Value2) (Required)
     *         extensibleEnumScalar: String(Value1/Value2) (Required)
     *     }
     *     boolean: boolean (Required)
     *     string: String (Required)
     *     bytes: byte[] (Required)
     *     int: int (Required)
     *     safeint: long (Required)
     *     long: long (Required)
     *     float: double (Required)
     *     double: double (Required)
     *     duration: Duration (Required)
     *     date: LocalDate (Required)
     *     dateTime: OffsetDateTime (Required)
     *     stringList (Required): [
     *         String (Required)
     *     ]
     *     bytesDict (Required): {
     *         String: byte[] (Required)
     *     }
     *     url: String (Required)
     *     nullableFloatDict (Required): {
     *         String: double (Required)
     *     }
     *     encoded (Required): {
     *         timeInSeconds: Long (Optional)
     *         timeInSecondsFraction: Double (Optional)
     *         dateTime: OffsetDateTime (Optional)
     *         dateTimeRfc7231: DateTimeRfc1123 (Optional)
     *         unixTimestamp: Long (Optional)
     *         base64: byte[] (Optional)
     *         base64url: Base64Url (Optional)
     *     }
     * }
     * }</pre>
     *
     * @param body The body parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> writeWithResponseAsync(BinaryData body, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.write(this.getEndpoint(), accept, body, requestOptions, context));
    }

    /**
     * The write operation.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     formatString (Required): {
     *         base64Encoded: Base64Url (Required)
     *         binary: byte[] (Required)
     *         dateTime: OffsetDateTime (Required)
     *         dateTimeRfc1123: DateTimeRfc1123 (Required)
     *         password: String (Required)
     *         uri: String (Required)
     *         extensibleEnum: String(Value1/Value2) (Required)
     *         extensibleEnumScalar: String(Value1/Value2) (Required)
     *     }
     *     boolean: boolean (Required)
     *     string: String (Required)
     *     bytes: byte[] (Required)
     *     int: int (Required)
     *     safeint: long (Required)
     *     long: long (Required)
     *     float: double (Required)
     *     double: double (Required)
     *     duration: Duration (Required)
     *     date: LocalDate (Required)
     *     dateTime: OffsetDateTime (Required)
     *     stringList (Required): [
     *         String (Required)
     *     ]
     *     bytesDict (Required): {
     *         String: byte[] (Required)
     *     }
     *     url: String (Required)
     *     nullableFloatDict (Required): {
     *         String: double (Required)
     *     }
     *     encoded (Required): {
     *         timeInSeconds: Long (Optional)
     *         timeInSecondsFraction: Double (Optional)
     *         dateTime: OffsetDateTime (Optional)
     *         dateTimeRfc7231: DateTimeRfc1123 (Optional)
     *         unixTimestamp: Long (Optional)
     *         base64: byte[] (Optional)
     *         base64url: Base64Url (Optional)
     *     }
     * }
     * }</pre>
     *
     * @param body The body parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> writeWithResponse(BinaryData body, RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.writeSync(this.getEndpoint(), accept, body, requestOptions, Context.NONE);
    }
}
