// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.builtin;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.exception.ClientAuthenticationException;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.exception.ResourceModifiedException;
import com.azure.core.exception.ResourceNotFoundException;
import com.azure.core.http.HttpHeaderName;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import com.azure.core.util.DateTimeRfc1123;
import com.azure.core.util.FluxUtil;
import com.cadl.builtin.implementation.BuiltinClientImpl;
import com.cadl.builtin.models.Builtin;
import java.time.OffsetDateTime;
import reactor.core.publisher.Mono;

/**
 * Initializes a new instance of the asynchronous BuiltinClient type.
 */
@ServiceClient(builder = BuiltinClientBuilder.class, isAsync = true)
public final class BuiltinAsyncClient {
    @Generated
    private final BuiltinClientImpl serviceClient;

    /**
     * Initializes an instance of BuiltinAsyncClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
    BuiltinAsyncClient(BuiltinClientImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * The read operation.
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
     * <td>filter</td>
     * <td>String</td>
     * <td>No</td>
     * <td>A sequence of textual characters.</td>
     * </tr>
     * <tr>
     * <td>query-opt</td>
     * <td>String</td>
     * <td>No</td>
     * <td>A sequence of textual characters.</td>
     * </tr>
     * <tr>
     * <td>query-opt-encoded</td>
     * <td>String</td>
     * <td>No</td>
     * <td>Represent a URL string as described by https://url.spec.whatwg.org/</td>
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
     * <td>x-ms-date</td>
     * <td>OffsetDateTime</td>
     * <td>No</td>
     * <td>An instant in coordinated universal time (UTC)"</td>
     * </tr>
     * </table>
     * You can add these to a request with {@link RequestOptions#addHeader}
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
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
     * @param queryParam A sequence of textual characters.
     * @param queryParamEncoded Represent a URL string as described by https://url.spec.whatwg.org/.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> readWithResponse(String queryParam, String queryParamEncoded,
        RequestOptions requestOptions) {
        return this.serviceClient.readWithResponseAsync(queryParam, queryParamEncoded, requestOptions);
    }

    /**
     * The write operation.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
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
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> writeWithResponse(BinaryData body, RequestOptions requestOptions) {
        return this.serviceClient.writeWithResponseAsync(body, requestOptions);
    }

    /**
     * The read operation.
     * 
     * @param queryParam A sequence of textual characters.
     * @param queryParamEncoded Represent a URL string as described by https://url.spec.whatwg.org/.
     * @param dateTime An instant in coordinated universal time (UTC)".
     * @param filter A sequence of textual characters.
     * @param queryParamOptional A sequence of textual characters.
     * @param queryParamOptionalEncoded Represent a URL string as described by https://url.spec.whatwg.org/.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Builtin> read(String queryParam, String queryParamEncoded, OffsetDateTime dateTime, String filter,
        String queryParamOptional, String queryParamOptionalEncoded) {
        // Generated convenience method for readWithResponse
        RequestOptions requestOptions = new RequestOptions();
        if (dateTime != null) {
            requestOptions.setHeader(HttpHeaderName.fromString("x-ms-date"),
                String.valueOf(new DateTimeRfc1123(dateTime)));
        }
        if (filter != null) {
            requestOptions.addQueryParam("filter", filter, false);
        }
        if (queryParamOptional != null) {
            requestOptions.addQueryParam("query-opt", queryParamOptional, false);
        }
        if (queryParamOptionalEncoded != null) {
            requestOptions.addQueryParam("query-opt-encoded", queryParamOptionalEncoded, true);
        }
        return readWithResponse(queryParam, queryParamEncoded, requestOptions).flatMap(FluxUtil::toMono)
            .map(protocolMethodData -> protocolMethodData.toObject(Builtin.class));
    }

    /**
     * The read operation.
     * 
     * @param queryParam A sequence of textual characters.
     * @param queryParamEncoded Represent a URL string as described by https://url.spec.whatwg.org/.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Builtin> read(String queryParam, String queryParamEncoded) {
        // Generated convenience method for readWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return readWithResponse(queryParam, queryParamEncoded, requestOptions).flatMap(FluxUtil::toMono)
            .map(protocolMethodData -> protocolMethodData.toObject(Builtin.class));
    }
}
