// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

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
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.BinaryData;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in BuiltinOps.
 */
public final class BuiltinOpsImpl {
    /**
     * The proxy service used to perform REST calls.
     */
    private final BuiltinOpsService service;

    /**
     * The service client containing this operation class.
     */
    private final BuiltinClientImpl client;

    /**
     * Initializes an instance of BuiltinOpsImpl.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    BuiltinOpsImpl(BuiltinClientImpl client) {
        this.service
            = RestProxy.create(BuiltinOpsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for BuiltinClientBuiltinOps to be used by the proxy service to perform
     * REST calls.
     */
    @Host("{endpoint}")
    @ServiceInterface(name = "BuiltinClientBuiltin")
    public interface BuiltinOpsService {
        @Get("/builtin")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<BinaryData>> read(@HostParam("endpoint") String endpoint, @QueryParam("query") String queryParam,
            @QueryParam(value = "query-encoded", encoded = true) String queryParamEncoded,
            @HeaderParam("accept") String accept, RequestOptions requestOptions, Context context);

        @Get("/builtin")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<BinaryData> readSync(@HostParam("endpoint") String endpoint, @QueryParam("query") String queryParam,
            @QueryParam(value = "query-encoded", encoded = true) String queryParamEncoded,
            @HeaderParam("accept") String accept, RequestOptions requestOptions, Context context);

        @Post("/builtin")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> write(@HostParam("endpoint") String endpoint, @HeaderParam("accept") String accept,
            @BodyParam("application/json") BinaryData body, RequestOptions requestOptions, Context context);

        @Post("/builtin")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<Void> writeSync(@HostParam("endpoint") String endpoint, @HeaderParam("accept") String accept,
            @BodyParam("application/json") BinaryData body, RequestOptions requestOptions, Context context);
    }

    /**
     * The read operation.
     * <p><strong>Query Parameters</strong></p>
     * <table border="1">
     * <caption>Query Parameters</caption>
     * <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     * <tr><td>filter</td><td>String</td><td>No</td><td>The filter parameter</td></tr>
     * <tr><td>query-opt</td><td>String</td><td>No</td><td>The queryParamOptional parameter</td></tr>
     * <tr><td>query-opt-encoded</td><td>String</td><td>No</td><td>The queryParamOptionalEncoded parameter</td></tr>
     * </table>
     * You can add these to a request with {@link RequestOptions#addQueryParam}
     * <p><strong>Header Parameters</strong></p>
     * <table border="1">
     * <caption>Header Parameters</caption>
     * <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     * <tr><td>x-ms-date</td><td>OffsetDateTime</td><td>No</td><td>The dateTime parameter</td></tr>
     * </table>
     * You can add these to a request with {@link RequestOptions#addHeader}
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     boolean: boolean (Required)
     *     string: String (Required)
     *     bytes: byte[] (Required)
     *     int: int (Required)
     *     safeint: long (Required)
     *     decimal: BigDecimal (Required)
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
     *         String: Double (Optional)
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
    public Mono<Response<BinaryData>> readWithResponseAsync(String queryParam, String queryParamEncoded,
        RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.read(this.client.getEndpoint(), queryParam, queryParamEncoded,
            accept, requestOptions, context));
    }

    /**
     * The read operation.
     * <p><strong>Query Parameters</strong></p>
     * <table border="1">
     * <caption>Query Parameters</caption>
     * <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     * <tr><td>filter</td><td>String</td><td>No</td><td>The filter parameter</td></tr>
     * <tr><td>query-opt</td><td>String</td><td>No</td><td>The queryParamOptional parameter</td></tr>
     * <tr><td>query-opt-encoded</td><td>String</td><td>No</td><td>The queryParamOptionalEncoded parameter</td></tr>
     * </table>
     * You can add these to a request with {@link RequestOptions#addQueryParam}
     * <p><strong>Header Parameters</strong></p>
     * <table border="1">
     * <caption>Header Parameters</caption>
     * <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     * <tr><td>x-ms-date</td><td>OffsetDateTime</td><td>No</td><td>The dateTime parameter</td></tr>
     * </table>
     * You can add these to a request with {@link RequestOptions#addHeader}
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     boolean: boolean (Required)
     *     string: String (Required)
     *     bytes: byte[] (Required)
     *     int: int (Required)
     *     safeint: long (Required)
     *     decimal: BigDecimal (Required)
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
     *         String: Double (Optional)
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
    public Response<BinaryData> readWithResponse(String queryParam, String queryParamEncoded,
        RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.readSync(this.client.getEndpoint(), queryParam, queryParamEncoded, accept, requestOptions,
            Context.NONE);
    }

    /**
     * The write operation.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     boolean: boolean (Required)
     *     string: String (Required)
     *     bytes: byte[] (Required)
     *     int: int (Required)
     *     safeint: long (Required)
     *     decimal: BigDecimal (Required)
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
     *         String: Double (Optional)
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
        return FluxUtil
            .withContext(context -> service.write(this.client.getEndpoint(), accept, body, requestOptions, context));
    }

    /**
     * The write operation.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     boolean: boolean (Required)
     *     string: String (Required)
     *     bytes: byte[] (Required)
     *     int: int (Required)
     *     safeint: long (Required)
     *     decimal: BigDecimal (Required)
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
     *         String: Double (Optional)
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
        return service.writeSync(this.client.getEndpoint(), accept, body, requestOptions, Context.NONE);
    }
}
