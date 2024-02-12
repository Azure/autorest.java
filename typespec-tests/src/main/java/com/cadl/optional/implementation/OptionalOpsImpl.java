// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.optional.implementation;

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
import com.azure.core.http.HttpHeaderName;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.BinaryData;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in OptionalOps.
 */
public final class OptionalOpsImpl {
    /**
     * The proxy service used to perform REST calls.
     */
    private final OptionalOpsService service;

    /**
     * The service client containing this operation class.
     */
    private final OptionalClientImpl client;

    /**
     * Initializes an instance of OptionalOpsImpl.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    OptionalOpsImpl(OptionalClientImpl client) {
        this.service
            = RestProxy.create(OptionalOpsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for OptionalClientOptionalOps to be used by the proxy service to perform
     * REST calls.
     */
    @Host("{endpoint}")
    @ServiceInterface(name = "OptionalClientOption")
    public interface OptionalOpsService {
        @Put("/optional/put")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<BinaryData>> put(@HostParam("endpoint") String endpoint,
            @HeaderParam("request-header-required") String requestHeaderRequired,
            @QueryParam("booleanRequired") boolean booleanRequired,
            @QueryParam("booleanRequiredNullable") Boolean booleanRequiredNullable,
            @QueryParam("stringRequired") String stringRequired,
            @QueryParam("stringRequiredNullable") String stringRequiredNullable, @HeaderParam("accept") String accept,
            RequestOptions requestOptions, Context context);

        @Put("/optional/put")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<BinaryData> putSync(@HostParam("endpoint") String endpoint,
            @HeaderParam("request-header-required") String requestHeaderRequired,
            @QueryParam("booleanRequired") boolean booleanRequired,
            @QueryParam("booleanRequiredNullable") Boolean booleanRequiredNullable,
            @QueryParam("stringRequired") String stringRequired,
            @QueryParam("stringRequiredNullable") String stringRequiredNullable, @HeaderParam("accept") String accept,
            RequestOptions requestOptions, Context context);
    }

    /**
     * The put operation.
     * <p>
     * <strong>Query Parameters</strong>
     * </p>
     * <table border="1">
     * <caption>Query Parameters</caption>
     * <tr>
     * <th>Name</th>
     * <th>Type</th>
     * <th>Required</th>
     * <th>Description</th>
     * </tr>
     * <tr>
     * <td>booleanNullable</td>
     * <td>Boolean</td>
     * <td>No</td>
     * <td>Boolean with `true` and `false` values.</td>
     * </tr>
     * <tr>
     * <td>string</td>
     * <td>String</td>
     * <td>No</td>
     * <td>A sequence of textual characters.</td>
     * </tr>
     * <tr>
     * <td>stringNullable</td>
     * <td>String</td>
     * <td>No</td>
     * <td>A sequence of textual characters.</td>
     * </tr>
     * </table>
     * You can add these to a request with {@link RequestOptions#addQueryParam}
     * <p>
     * <strong>Header Parameters</strong>
     * </p>
     * <table border="1">
     * <caption>Header Parameters</caption>
     * <tr>
     * <th>Name</th>
     * <th>Type</th>
     * <th>Required</th>
     * <th>Description</th>
     * </tr>
     * <tr>
     * <td>request-header-optional</td>
     * <td>String</td>
     * <td>No</td>
     * <td>A sequence of textual characters.</td>
     * </tr>
     * </table>
     * You can add these to a request with {@link RequestOptions#addHeader}
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     boolean: Boolean (Optional)
     *     booleanNullable: Boolean (Optional)
     *     booleanRequired: boolean (Required)
     *     booleanRequiredNullable: Boolean (Required)
     *     string: String (Optional)
     *     stringNullable: String (Optional)
     *     stringRequired: String (Required)
     *     stringRequiredNullable: String (Required)
     *     bytes: byte[] (Optional)
     *     int: Integer (Optional)
     *     long: Long (Optional)
     *     float: Double (Optional)
     *     double: Double (Optional)
     *     duration: Duration (Optional)
     *     dateTime: OffsetDateTime (Optional)
     *     epochDateTimeRequiredNullable: OffsetDateTime (Required)
     *     epochDateTimeNullable: Long (Optional)
     *     stringList (Optional): [
     *         String (Optional)
     *     ]
     *     bytesDict (Optional): {
     *         String: byte[] (Required)
     *     }
     * }
     * }</pre>
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     boolean: Boolean (Optional)
     *     booleanNullable: Boolean (Optional)
     *     booleanRequired: Boolean (Optional)
     *     booleanRequiredNullable: Boolean (Optional)
     *     string: String (Optional)
     *     stringNullable: String (Optional)
     *     stringRequired: String (Optional)
     *     stringRequiredNullable: String (Optional)
     *     bytes: byte[] (Optional)
     *     int: Integer (Optional)
     *     long: Long (Optional)
     *     float: Double (Optional)
     *     double: Double (Optional)
     *     duration: Duration (Optional)
     *     dateTime: OffsetDateTime (Optional)
     *     epochDateTimeRequiredNullable: OffsetDateTime (Optional)
     *     epochDateTimeNullable: Long (Optional)
     *     stringList (Optional): [
     *         String (Optional)
     *     ]
     *     bytesDict (Optional): {
     *         String: byte[] (Required)
     *     }
     *     immutable (Optional): {
     *         stringReadWriteRequired: String (Required)
     *         stringReadOnlyOptional: String (Optional)
     *     }
     * }
     * }</pre>
     * 
     * @param requestHeaderRequired A sequence of textual characters.
     * @param booleanRequired Boolean with `true` and `false` values.
     * @param booleanRequiredNullable Boolean with `true` and `false` values.
     * @param stringRequired A sequence of textual characters.
     * @param stringRequiredNullable A sequence of textual characters.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> putWithResponseAsync(String requestHeaderRequired, boolean booleanRequired,
        Boolean booleanRequiredNullable, String stringRequired, String stringRequiredNullable,
        RequestOptions requestOptions) {
        final String accept = "application/json";
        RequestOptions requestOptionsLocal = requestOptions == null ? new RequestOptions() : requestOptions;
        requestOptionsLocal.addRequestCallback(requestLocal -> {
            if (requestLocal.getBody() != null && requestLocal.getHeaders().get(HttpHeaderName.CONTENT_TYPE) == null) {
                requestLocal.getHeaders().set(HttpHeaderName.CONTENT_TYPE, "application/json");
            }
        });
        return FluxUtil
            .withContext(context -> service.put(this.client.getEndpoint(), requestHeaderRequired, booleanRequired,
                booleanRequiredNullable, stringRequired, stringRequiredNullable, accept, requestOptionsLocal, context));
    }

    /**
     * The put operation.
     * <p>
     * <strong>Query Parameters</strong>
     * </p>
     * <table border="1">
     * <caption>Query Parameters</caption>
     * <tr>
     * <th>Name</th>
     * <th>Type</th>
     * <th>Required</th>
     * <th>Description</th>
     * </tr>
     * <tr>
     * <td>booleanNullable</td>
     * <td>Boolean</td>
     * <td>No</td>
     * <td>Boolean with `true` and `false` values.</td>
     * </tr>
     * <tr>
     * <td>string</td>
     * <td>String</td>
     * <td>No</td>
     * <td>A sequence of textual characters.</td>
     * </tr>
     * <tr>
     * <td>stringNullable</td>
     * <td>String</td>
     * <td>No</td>
     * <td>A sequence of textual characters.</td>
     * </tr>
     * </table>
     * You can add these to a request with {@link RequestOptions#addQueryParam}
     * <p>
     * <strong>Header Parameters</strong>
     * </p>
     * <table border="1">
     * <caption>Header Parameters</caption>
     * <tr>
     * <th>Name</th>
     * <th>Type</th>
     * <th>Required</th>
     * <th>Description</th>
     * </tr>
     * <tr>
     * <td>request-header-optional</td>
     * <td>String</td>
     * <td>No</td>
     * <td>A sequence of textual characters.</td>
     * </tr>
     * </table>
     * You can add these to a request with {@link RequestOptions#addHeader}
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     boolean: Boolean (Optional)
     *     booleanNullable: Boolean (Optional)
     *     booleanRequired: boolean (Required)
     *     booleanRequiredNullable: Boolean (Required)
     *     string: String (Optional)
     *     stringNullable: String (Optional)
     *     stringRequired: String (Required)
     *     stringRequiredNullable: String (Required)
     *     bytes: byte[] (Optional)
     *     int: Integer (Optional)
     *     long: Long (Optional)
     *     float: Double (Optional)
     *     double: Double (Optional)
     *     duration: Duration (Optional)
     *     dateTime: OffsetDateTime (Optional)
     *     epochDateTimeRequiredNullable: OffsetDateTime (Required)
     *     epochDateTimeNullable: Long (Optional)
     *     stringList (Optional): [
     *         String (Optional)
     *     ]
     *     bytesDict (Optional): {
     *         String: byte[] (Required)
     *     }
     * }
     * }</pre>
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     boolean: Boolean (Optional)
     *     booleanNullable: Boolean (Optional)
     *     booleanRequired: Boolean (Optional)
     *     booleanRequiredNullable: Boolean (Optional)
     *     string: String (Optional)
     *     stringNullable: String (Optional)
     *     stringRequired: String (Optional)
     *     stringRequiredNullable: String (Optional)
     *     bytes: byte[] (Optional)
     *     int: Integer (Optional)
     *     long: Long (Optional)
     *     float: Double (Optional)
     *     double: Double (Optional)
     *     duration: Duration (Optional)
     *     dateTime: OffsetDateTime (Optional)
     *     epochDateTimeRequiredNullable: OffsetDateTime (Optional)
     *     epochDateTimeNullable: Long (Optional)
     *     stringList (Optional): [
     *         String (Optional)
     *     ]
     *     bytesDict (Optional): {
     *         String: byte[] (Required)
     *     }
     *     immutable (Optional): {
     *         stringReadWriteRequired: String (Required)
     *         stringReadOnlyOptional: String (Optional)
     *     }
     * }
     * }</pre>
     * 
     * @param requestHeaderRequired A sequence of textual characters.
     * @param booleanRequired Boolean with `true` and `false` values.
     * @param booleanRequiredNullable Boolean with `true` and `false` values.
     * @param stringRequired A sequence of textual characters.
     * @param stringRequiredNullable A sequence of textual characters.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> putWithResponse(String requestHeaderRequired, boolean booleanRequired,
        Boolean booleanRequiredNullable, String stringRequired, String stringRequiredNullable,
        RequestOptions requestOptions) {
        final String accept = "application/json";
        RequestOptions requestOptionsLocal = requestOptions == null ? new RequestOptions() : requestOptions;
        requestOptionsLocal.addRequestCallback(requestLocal -> {
            if (requestLocal.getBody() != null && requestLocal.getHeaders().get(HttpHeaderName.CONTENT_TYPE) == null) {
                requestLocal.getHeaders().set(HttpHeaderName.CONTENT_TYPE, "application/json");
            }
        });
        return service.putSync(this.client.getEndpoint(), requestHeaderRequired, booleanRequired,
            booleanRequiredNullable, stringRequired, stringRequiredNullable, accept, requestOptionsLocal, Context.NONE);
    }
}
