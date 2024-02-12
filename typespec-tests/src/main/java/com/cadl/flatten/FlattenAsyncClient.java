// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.flatten;

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
import com.azure.core.util.BinaryData;
import com.azure.core.util.FluxUtil;
import com.cadl.flatten.implementation.FlattenClientImpl;
import com.cadl.flatten.implementation.models.SendLongRequest;
import com.cadl.flatten.implementation.models.SendProjectedNameRequest;
import com.cadl.flatten.implementation.models.SendRequest;
import com.cadl.flatten.models.SendLongOptions;
import com.cadl.flatten.models.User;
import reactor.core.publisher.Mono;

/**
 * Initializes a new instance of the asynchronous FlattenClient type.
 */
@ServiceClient(builder = FlattenClientBuilder.class, isAsync = true)
public final class FlattenAsyncClient {
    @Generated
    private final FlattenClientImpl serviceClient;

    /**
     * Initializes an instance of FlattenAsyncClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
    FlattenAsyncClient(FlattenClientImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * The send operation.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     user (Optional): {
     *         user: String (Required)
     *     }
     *     input: String (Required)
     * }
     * }</pre>
     * 
     * @param id A sequence of textual characters.
     * @param request The request parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> sendWithResponse(String id, BinaryData request, RequestOptions requestOptions) {
        return this.serviceClient.sendWithResponseAsync(id, request, requestOptions);
    }

    /**
     * The sendProjectedName operation.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     file_id: String (Required)
     * }
     * }</pre>
     * 
     * @param id A sequence of textual characters.
     * @param request The request parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> sendProjectedNameWithResponse(String id, BinaryData request,
        RequestOptions requestOptions) {
        return this.serviceClient.sendProjectedNameWithResponseAsync(id, request, requestOptions);
    }

    /**
     * The sendLong operation.
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
     * </table>
     * You can add these to a request with {@link RequestOptions#addQueryParam}
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     user (Optional): {
     *         user: String (Required)
     *     }
     *     input: String (Required)
     *     dataInt: int (Required)
     *     dataIntOptional: Integer (Optional)
     *     dataLong: Long (Optional)
     *     data_float: Double (Optional)
     * }
     * }</pre>
     * 
     * @param id A sequence of textual characters.
     * @param request The request parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> sendLongWithResponse(String id, BinaryData request, RequestOptions requestOptions) {
        return this.serviceClient.sendLongWithResponseAsync(id, request, requestOptions);
    }

    /**
     * The send operation.
     * 
     * @param id A sequence of textual characters.
     * @param input A sequence of textual characters.
     * @param user The user parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> send(String id, String input, User user) {
        // Generated convenience method for sendWithResponse
        RequestOptions requestOptions = new RequestOptions();
        SendRequest requestObj = new SendRequest(input).setUser(user);
        BinaryData request = BinaryData.fromObject(requestObj);
        return sendWithResponse(id, request, requestOptions).flatMap(FluxUtil::toMono);
    }

    /**
     * The send operation.
     * 
     * @param id A sequence of textual characters.
     * @param input A sequence of textual characters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> send(String id, String input) {
        // Generated convenience method for sendWithResponse
        RequestOptions requestOptions = new RequestOptions();
        SendRequest requestObj = new SendRequest(input);
        BinaryData request = BinaryData.fromObject(requestObj);
        return sendWithResponse(id, request, requestOptions).flatMap(FluxUtil::toMono);
    }

    /**
     * The sendProjectedName operation.
     * 
     * @param id A sequence of textual characters.
     * @param fileIdentifier A sequence of textual characters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> sendProjectedName(String id, String fileIdentifier) {
        // Generated convenience method for sendProjectedNameWithResponse
        RequestOptions requestOptions = new RequestOptions();
        SendProjectedNameRequest requestObj = new SendProjectedNameRequest(fileIdentifier);
        BinaryData request = BinaryData.fromObject(requestObj);
        return sendProjectedNameWithResponse(id, request, requestOptions).flatMap(FluxUtil::toMono);
    }

    /**
     * The sendLong operation.
     * 
     * @param options Options for sendLong API.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> sendLong(SendLongOptions options) {
        // Generated convenience method for sendLongWithResponse
        RequestOptions requestOptions = new RequestOptions();
        String id = options.getId();
        String filter = options.getFilter();
        SendLongRequest requestObj = new SendLongRequest(options.getInput(), options.getDataInt())
            .setUser(options.getUser()).setDataIntOptional(options.getDataIntOptional())
            .setDataLong(options.getDataLong()).setDataFloat(options.getDataFloat());
        BinaryData request = BinaryData.fromObject(requestObj);
        if (filter != null) {
            requestOptions.addQueryParam("filter", filter, false);
        }
        return sendLongWithResponse(id, request, requestOptions).flatMap(FluxUtil::toMono);
    }
}
