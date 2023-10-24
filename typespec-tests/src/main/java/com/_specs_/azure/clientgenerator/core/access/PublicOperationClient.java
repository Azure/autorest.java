// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com._specs_.azure.clientgenerator.core.access;

import com._specs_.azure.clientgenerator.core.access.implementation.PublicOperationsImpl;
import com._specs_.azure.clientgenerator.core.access.models.NoDecoratorModelInPublic;
import com._specs_.azure.clientgenerator.core.access.models.PublicDecoratorModelInPublic;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import reactor.core.publisher.Mono;

/**
 * Initializes a new instance of the synchronous AccessClient type.
 */
@ServiceClient(builder = AccessClientBuilder.class)
public final class PublicOperationClient {
    @Generated
    private final PublicOperationsImpl serviceClient;

    /**
     * Initializes an instance of PublicOperationClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
     PublicOperationClient(PublicOperationsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * The noDecoratorInPublic operation.
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     *     name: String (Required)
     * }
     * }</pre>
     * 
     * @param name A sequence of textual characters.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return used in a public operation, should be generated and exported along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> noDecoratorInPublicWithResponse(String name, RequestOptions requestOptions) {
        return this.serviceClient.noDecoratorInPublicWithResponse(name, requestOptions);
    }

    /**
     * The publicDecoratorInPublic operation.
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     *     name: String (Required)
     * }
     * }</pre>
     * 
     * @param name A sequence of textual characters.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return used in a public operation, should be generated and exported along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> publicDecoratorInPublicWithResponse(String name, RequestOptions requestOptions) {
        return this.serviceClient.publicDecoratorInPublicWithResponse(name, requestOptions);
    }

    /**
     * The noDecoratorInPublic operation.
     * 
     * @param name A sequence of textual characters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return used in a public operation, should be generated and exported.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public NoDecoratorModelInPublic noDecoratorInPublic(String name) {
        // Generated convenience method for noDecoratorInPublicWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return noDecoratorInPublicWithResponse(name, requestOptions).getValue().toObject(NoDecoratorModelInPublic.class);
    }

    /**
     * The publicDecoratorInPublic operation.
     * 
     * @param name A sequence of textual characters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return used in a public operation, should be generated and exported.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public PublicDecoratorModelInPublic publicDecoratorInPublic(String name) {
        // Generated convenience method for publicDecoratorInPublicWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return publicDecoratorInPublicWithResponse(name, requestOptions).getValue().toObject(PublicDecoratorModelInPublic.class);
    }
}
