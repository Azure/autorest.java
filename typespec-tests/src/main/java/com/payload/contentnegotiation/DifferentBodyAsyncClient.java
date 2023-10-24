// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.payload.contentnegotiation;

import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
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
import com.azure.core.http.rest.PagedResponse;
import com.azure.core.http.rest.PagedResponseBase;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.http.rest.SimpleResponse;
import com.azure.core.util.Base64Url;
import com.azure.core.util.BinaryData;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import com.azure.core.util.UrlBuilder;
import com.azure.core.util.serializer.CollectionFormat;
import com.azure.core.util.serializer.JacksonAdapter;
import com.azure.core.util.serializer.TypeReference;
import com.payload.contentnegotiation.implementation.DifferentBodiesImpl;
import com.payload.contentnegotiation.models.PngImageAsJson;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Initializes a new instance of the asynchronous ContentNegotiationClient type.
 */
@ServiceClient(builder = ContentNegotiationClientBuilder.class, isAsync = true)
public final class DifferentBodyAsyncClient {
    @Generated
    private final DifferentBodiesImpl serviceClient;

    /**
     * Initializes an instance of DifferentBodyAsyncClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
     DifferentBodyAsyncClient(DifferentBodiesImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * The getAvatarAsPng operation.
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * BinaryData
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getAvatarAsPngWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getAvatarAsPngWithResponseAsync(requestOptions);
    }

    /**
     * The getAvatarAsJson operation.
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     *     content: byte[] (Required)
     * }
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getAvatarAsJsonWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getAvatarAsJsonWithResponseAsync(requestOptions);
    }

    /**
     * The getAvatarAsPng operation.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> getAvatarAsPng() {
        // Generated convenience method for getAvatarAsPngWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return getAvatarAsPngWithResponse(requestOptions).flatMap(FluxUtil::toMono);
    }

    /**
     * The getAvatarAsJson operation.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PngImageAsJson> getAvatarAsJson() {
        // Generated convenience method for getAvatarAsJsonWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return getAvatarAsJsonWithResponse(requestOptions).flatMap(FluxUtil::toMono).map(protocolMethodData -> protocolMethodData.toObject(PngImageAsJson.class));
    }
}
