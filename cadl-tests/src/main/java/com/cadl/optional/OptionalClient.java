// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.cadl.optional;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.exception.ClientAuthenticationException;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.exception.ResourceModifiedException;
import com.azure.core.exception.ResourceNotFoundException;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.ResponseBase;
import com.azure.core.http.rest.SimpleResponse;
import com.azure.core.util.BinaryData;
import com.azure.core.util.Context;
import com.cadl.optional.models.AllPropertiesOptional;
import com.cadl.optional.models.Optional;
import com.cadl.optional.models.OptionalOpsPutHeaders;

/** Initializes a new instance of the synchronous OptionalClient type. */
@ServiceClient(builder = OptionalClientBuilder.class)
public final class OptionalClient {
    @Generated private final OptionalAsyncClient client;

    /**
     * Initializes an instance of OptionalClient class.
     *
     * @param client the async client.
     */
    @Generated
    OptionalClient(OptionalAsyncClient client) {
        this.client = client;
    }

    /**
     * The put operation.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>booleanNullable</td><td>Boolean</td><td>No</td><td>The booleanNullable parameter</td></tr>
     *     <tr><td>string</td><td>String</td><td>No</td><td>The string parameter</td></tr>
     *     <tr><td>stringNullable</td><td>String</td><td>No</td><td>The stringNullable parameter</td></tr>
     * </table>
     *
     * You can add these to a request with {@link RequestOptions#addQueryParam}
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>request-header-optional</td><td>String</td><td>No</td><td>The requestHeaderOptional parameter</td></tr>
     * </table>
     *
     * You can add these to a request with {@link RequestOptions#addHeader}
     *
     * <p><strong>Request Body Schema</strong>
     *
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
     *     int: Long (Optional)
     *     long: Long (Optional)
     *     float: Double (Optional)
     *     double: Double (Optional)
     *     duration: Duration (Optional)
     *     dateTime: OffsetDateTime (Optional)
     *     stringList (Optional): [
     *         String (Optional)
     *     ]
     *     bytesDict (Optional): {
     *         String: byte[] (Optional)
     *     }
     * }
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
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
     *     int: Long (Optional)
     *     long: Long (Optional)
     *     float: Double (Optional)
     *     double: Double (Optional)
     *     duration: Duration (Optional)
     *     dateTime: OffsetDateTime (Optional)
     *     stringList (Optional): [
     *         String (Optional)
     *     ]
     *     bytesDict (Optional): {
     *         String: byte[] (Optional)
     *     }
     * }
     * }</pre>
     *
     * @param requestHeaderRequired The requestHeaderRequired parameter.
     * @param booleanRequired The booleanRequired parameter.
     * @param booleanRequiredNullable The booleanRequiredNullable parameter.
     * @param stringRequired The stringRequired parameter.
     * @param stringRequiredNullable The stringRequiredNullable parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> putWithResponse(
            String requestHeaderRequired,
            boolean booleanRequired,
            Boolean booleanRequiredNullable,
            String stringRequired,
            String stringRequiredNullable,
            RequestOptions requestOptions) {
        return this.client
                .putWithResponse(
                        requestHeaderRequired,
                        booleanRequired,
                        booleanRequiredNullable,
                        stringRequired,
                        stringRequiredNullable,
                        requestOptions)
                .block();
    }

    /*
     * Generated convenience method for putWithResponse
     */
    /**
     * The put operation.
     *
     * @param requestHeaderRequired The requestHeaderRequired parameter.
     * @param booleanRequired The booleanRequired parameter.
     * @param booleanRequiredNullable The booleanRequiredNullable parameter.
     * @param stringRequired The stringRequired parameter.
     * @param stringRequiredNullable The stringRequiredNullable parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public AllPropertiesOptional put(
            String requestHeaderRequired,
            boolean booleanRequired,
            Boolean booleanRequiredNullable,
            String stringRequired,
            String stringRequiredNullable) {
        RequestOptions requestOptions = new RequestOptions();
        return putWithResponse(
                        requestHeaderRequired,
                        booleanRequired,
                        booleanRequiredNullable,
                        stringRequired,
                        stringRequiredNullable,
                        requestOptions)
                .getValue()
                .toObject(AllPropertiesOptional.class);
    }

    /*
     * Generated convenience method for putWithResponse
     */
    /**
     * The put operation.
     *
     * @param requestHeaderRequired The requestHeaderRequired parameter.
     * @param booleanRequired The booleanRequired parameter.
     * @param booleanRequiredNullable The booleanRequiredNullable parameter.
     * @param stringRequired The stringRequired parameter.
     * @param stringRequiredNullable The stringRequiredNullable parameter.
     * @param requestHeaderOptional The requestHeaderOptional parameter.
     * @param booleanNullable The booleanNullable parameter.
     * @param string The string parameter.
     * @param stringNullable The stringNullable parameter.
     * @param optional The optional parameter.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link ResponseBase}.
     */
    public ResponseBase<OptionalOpsPutHeaders, AllPropertiesOptional> putWithResponse(
            String requestHeaderRequired,
            boolean booleanRequired,
            Boolean booleanRequiredNullable,
            String stringRequired,
            String stringRequiredNullable,
            String requestHeaderOptional,
            Boolean booleanNullable,
            String string,
            String stringNullable,
            Optional optional,
            Context context) {
        RequestOptions requestOptions = new RequestOptions();
        if (requestHeaderOptional != null) {
            requestOptions.setHeader("request-header-optional", requestHeaderOptional);
        }
        if (booleanNullable != null) {
            requestOptions.addQueryParam("booleanNullable", String.valueOf(booleanNullable));
        }
        if (string != null) {
            requestOptions.addQueryParam("string", string);
        }
        if (stringNullable != null) {
            requestOptions.addQueryParam("stringNullable", stringNullable);
        }
        if (optional != null) {
            requestOptions.setBody(BinaryData.fromObject(optional));
        }
        requestOptions.setContext(context);
        Response<BinaryData> protocolMethodResponse =
                putWithResponse(
                        requestHeaderRequired,
                        booleanRequired,
                        booleanRequiredNullable,
                        stringRequired,
                        stringRequiredNullable,
                        requestOptions);
        return new SimpleResponse<>(protocolMethodResponse, protocolMethodResponse.getValue());
    }
}
