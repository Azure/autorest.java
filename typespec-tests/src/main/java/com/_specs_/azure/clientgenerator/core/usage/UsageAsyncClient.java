// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com._specs_.azure.clientgenerator.core.usage;

import com._specs_.azure.clientgenerator.core.usage.implementation.ModelInOperationsImpl;
import com._specs_.azure.clientgenerator.core.usage.models.InputModel;
import com._specs_.azure.clientgenerator.core.usage.models.OutputModel;
import com._specs_.azure.clientgenerator.core.usage.models.RoundTripModel;
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
import reactor.core.publisher.Mono;

/**
 * Initializes a new instance of the asynchronous UsageClient type.
 */
@ServiceClient(builder = UsageClientBuilder.class, isAsync = true)
public final class UsageAsyncClient {
    @Generated
    private final ModelInOperationsImpl serviceClient;

    /**
     * Initializes an instance of UsageAsyncClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
    UsageAsyncClient(ModelInOperationsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Expected body parameter:
     * ```json
     * {
     * "name": &lt;any string&gt;
     * }
     * ```.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     name: String (Required)
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
    public Mono<Response<Void>> inputToInputOutputWithResponse(BinaryData body, RequestOptions requestOptions) {
        return this.serviceClient.inputToInputOutputWithResponseAsync(body, requestOptions);
    }

    /**
     * Expected response body:
     * ```json
     * {
     * "name": &lt;any string&gt;
     * }
     * ```.
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     name: String (Required)
     * }
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return usage override to roundtrip along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> outputToInputOutputWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.outputToInputOutputWithResponseAsync(requestOptions);
    }

    /**
     * "ResultModel" should be usage=output, as it is read-only and does not exist in request body.
     * 
     * Expected body parameter:
     * ```json
     * {
     * }
     * ```
     * 
     * Expected response body:
     * ```json
     * {
     * "name": &lt;any string&gt;
     * }
     * ```.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     result (Required): {
     *         name: String (Required)
     *     }
     * }
     * }</pre>
     * 
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     result (Required): {
     *         name: String (Required)
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
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> modelInReadOnlyPropertyWithResponse(BinaryData body,
        RequestOptions requestOptions) {
        return this.serviceClient.modelInReadOnlyPropertyWithResponseAsync(body, requestOptions);
    }

    /**
     * Expected body parameter:
     * ```json
     * {
     * "name": &lt;any string&gt;
     * }
     * ```.
     * 
     * @param body The body parameter.
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
    public Mono<Void> inputToInputOutput(InputModel body) {
        // Generated convenience method for inputToInputOutputWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return inputToInputOutputWithResponse(BinaryData.fromObject(body), requestOptions).flatMap(FluxUtil::toMono);
    }

    /**
     * Expected response body:
     * ```json
     * {
     * "name": &lt;any string&gt;
     * }
     * ```.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return usage override to roundtrip on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<OutputModel> outputToInputOutput() {
        // Generated convenience method for outputToInputOutputWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return outputToInputOutputWithResponse(requestOptions).flatMap(FluxUtil::toMono)
            .map(protocolMethodData -> protocolMethodData.toObject(OutputModel.class));
    }

    /**
     * "ResultModel" should be usage=output, as it is read-only and does not exist in request body.
     * 
     * Expected body parameter:
     * ```json
     * {
     * }
     * ```
     * 
     * Expected response body:
     * ```json
     * {
     * "name": &lt;any string&gt;
     * }
     * ```.
     * 
     * @param body The body parameter.
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
    public Mono<RoundTripModel> modelInReadOnlyProperty(RoundTripModel body) {
        // Generated convenience method for modelInReadOnlyPropertyWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return modelInReadOnlyPropertyWithResponse(BinaryData.fromObject(body), requestOptions)
            .flatMap(FluxUtil::toMono)
            .map(protocolMethodData -> protocolMethodData.toObject(RoundTripModel.class));
    }
}
