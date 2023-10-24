// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.encode.duration;

import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.QueryParam;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
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
import com.azure.core.http.rest.SimpleResponse;
import com.azure.core.util.Base64Url;
import com.azure.core.util.BinaryData;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import com.azure.core.util.UrlBuilder;
import com.azure.core.util.logging.ClientLogger;
import com.azure.core.util.serializer.CollectionFormat;
import com.azure.core.util.serializer.JacksonAdapter;
import com.azure.core.util.serializer.TypeReference;
import com.encode.duration.implementation.QueriesImpl;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import reactor.core.publisher.Mono;

/**
 * Initializes a new instance of the synchronous DurationClient type.
 */
@ServiceClient(builder = DurationClientBuilder.class)
public final class QueryClient {
    @Generated
    private final QueriesImpl serviceClient;

    /**
     * Initializes an instance of QueryClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
     QueryClient(QueriesImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * The defaultMethod operation.
     * 
     * @param input A duration/time period. e.g 5s, 10h.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> defaultMethodWithResponse(Duration input, RequestOptions requestOptions) {
        return this.serviceClient.defaultMethodWithResponse(input, requestOptions);
    }

    /**
     * The iso8601 operation.
     * 
     * @param input A duration/time period. e.g 5s, 10h.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> iso8601WithResponse(Duration input, RequestOptions requestOptions) {
        return this.serviceClient.iso8601WithResponse(input, requestOptions);
    }

    /**
     * The int32Seconds operation.
     * 
     * @param input A duration/time period. e.g 5s, 10h.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> int32SecondsWithResponse(Duration input, RequestOptions requestOptions) {
        return this.serviceClient.int32SecondsWithResponse(input, requestOptions);
    }

    /**
     * The floatSeconds operation.
     * 
     * @param input A duration/time period. e.g 5s, 10h.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> floatSecondsWithResponse(Duration input, RequestOptions requestOptions) {
        return this.serviceClient.floatSecondsWithResponse(input, requestOptions);
    }

    /**
     * The int32SecondsArray operation.
     * 
     * @param input Array of InputModel.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> int32SecondsArrayWithResponse(List<Duration> input, RequestOptions requestOptions) {
        return this.serviceClient.int32SecondsArrayWithResponse(input, requestOptions);
    }

    /**
     * The defaultMethod operation.
     * 
     * @param input A duration/time period. e.g 5s, 10h.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void defaultMethod(Duration input) {
        // Generated convenience method for defaultMethodWithResponse
        RequestOptions requestOptions = new RequestOptions();
        defaultMethodWithResponse(input, requestOptions).getValue();
    }

    /**
     * The iso8601 operation.
     * 
     * @param input A duration/time period. e.g 5s, 10h.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void iso8601(Duration input) {
        // Generated convenience method for iso8601WithResponse
        RequestOptions requestOptions = new RequestOptions();
        iso8601WithResponse(input, requestOptions).getValue();
    }

    /**
     * The int32Seconds operation.
     * 
     * @param input A duration/time period. e.g 5s, 10h.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void int32Seconds(Duration input) {
        // Generated convenience method for int32SecondsWithResponse
        RequestOptions requestOptions = new RequestOptions();
        int32SecondsWithResponse(input, requestOptions).getValue();
    }

    /**
     * The floatSeconds operation.
     * 
     * @param input A duration/time period. e.g 5s, 10h.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void floatSeconds(Duration input) {
        // Generated convenience method for floatSecondsWithResponse
        RequestOptions requestOptions = new RequestOptions();
        floatSecondsWithResponse(input, requestOptions).getValue();
    }

    /**
     * The int32SecondsArray operation.
     * 
     * @param input Array of InputModel.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void int32SecondsArray(List<Duration> input) {
        // Generated convenience method for int32SecondsArrayWithResponse
        RequestOptions requestOptions = new RequestOptions();
        int32SecondsArrayWithResponse(input, requestOptions).getValue();
    }
}
