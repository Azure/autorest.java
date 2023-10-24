// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.resiliency.servicedriven.v1;

import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.Head;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
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
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
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
import com.azure.core.util.serializer.SerializerAdapter;
import com.azure.core.util.serializer.TypeReference;
import com.resiliency.servicedriven.v1.implementation.ResiliencyServiceDrivenClientImpl;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import reactor.core.publisher.Mono;

/**
 * Initializes a new instance of the synchronous ResiliencyServiceDrivenClient type.
 */
@ServiceClient(builder = ResiliencyServiceDrivenClientBuilder.class)
public final class ResiliencyServiceDrivenClient {
    @Generated
    private final ResiliencyServiceDrivenClientImpl serviceClient;

    /**
     * Initializes an instance of ResiliencyServiceDrivenClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
     ResiliencyServiceDrivenClient(ResiliencyServiceDrivenClientImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Test that currently accepts no parameters, will be updated in next spec to accept a new optional parameter as well.
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> fromNoneWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.fromNoneWithResponse(requestOptions);
    }

    /**
     * Test that currently accepts one required parameter, will be updated in next spec to accept a new optional parameter as well.
     * 
     * @param parameter I am a required parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> fromOneRequiredWithResponse(String parameter, RequestOptions requestOptions) {
        return this.serviceClient.fromOneRequiredWithResponse(parameter, requestOptions);
    }

    /**
     * Test that currently accepts one optional parameter, will be updated in next spec to accept a new optional parameter as well.
     * <p><strong>Query Parameters</strong></p>
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>parameter</td><td>String</td><td>No</td><td>I am an optional parameter</td></tr>
     * </table>
     * You can add these to a request with {@link RequestOptions#addQueryParam}
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> fromOneOptionalWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.fromOneOptionalWithResponse(requestOptions);
    }

    /**
     * Test that currently accepts no parameters, will be updated in next spec to accept a new optional parameter as well.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void fromNone() {
        // Generated convenience method for fromNoneWithResponse
        RequestOptions requestOptions = new RequestOptions();
        fromNoneWithResponse(requestOptions).getValue();
    }

    /**
     * Test that currently accepts one required parameter, will be updated in next spec to accept a new optional parameter as well.
     * 
     * @param parameter I am a required parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void fromOneRequired(String parameter) {
        // Generated convenience method for fromOneRequiredWithResponse
        RequestOptions requestOptions = new RequestOptions();
        fromOneRequiredWithResponse(parameter, requestOptions).getValue();
    }

    /**
     * Test that currently accepts one optional parameter, will be updated in next spec to accept a new optional parameter as well.
     * 
     * @param parameter I am an optional parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void fromOneOptional(String parameter) {
        // Generated convenience method for fromOneOptionalWithResponse
        RequestOptions requestOptions = new RequestOptions();
        if (parameter != null) {
            requestOptions.addQueryParam("parameter", parameter, false);
        }
        fromOneOptionalWithResponse(requestOptions).getValue();
    }

    /**
     * Test that currently accepts one optional parameter, will be updated in next spec to accept a new optional parameter as well.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void fromOneOptional() {
        // Generated convenience method for fromOneOptionalWithResponse
        RequestOptions requestOptions = new RequestOptions();
        fromOneOptionalWithResponse(requestOptions).getValue();
    }
}
