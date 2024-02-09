// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.
package com._specs_.azure.clientgenerator.core.access;

import com._specs_.azure.clientgenerator.core.access.implementation.RelativeModelInOperationsImpl;
import com._specs_.azure.clientgenerator.core.access.implementation.models.AbstractModel;
import com._specs_.azure.clientgenerator.core.access.implementation.models.OuterModel;
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
 * Initializes a new instance of the asynchronous AccessClient type.
 */
@ServiceClient(builder = AccessClientBuilder.class, isAsync = true)
public final class RelativeModelInOperationAsyncClient {

    @Generated
    private final RelativeModelInOperationsImpl serviceClient;

    /**
     * Initializes an instance of RelativeModelInOperationAsyncClient class.
     *
     * @param serviceClient the service client implementation.
     */
    @Generated
    RelativeModelInOperationAsyncClient(RelativeModelInOperationsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Expected query parameter: name=&lt;any string&gt;
     * Expected response body:
     * ```json
     * {
     * "name": &lt;any string&gt;,
     * "inner":
     * {
     * "name": &lt;any string&gt;
     * }
     * }
     * ```.
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     name: String (Required)
     *     inner (Required): {
     *         name: String (Required)
     *     }
     * }
     * }</pre>
     *
     * @param name A sequence of textual characters.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return used in internal operations, should be generated but not exported along with {@link Response} on
     * successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    Mono<Response<BinaryData>> operationWithResponse(String name, RequestOptions requestOptions) {
        return this.serviceClient.operationWithResponseAsync(name, requestOptions);
    }

    /**
     * Expected query parameter: kind=&lt;any string&gt;
     * Expected response body:
     * ```json
     * {
     * "name": &lt;any string&gt;,
     * "kind": "real"
     * }
     * ```.
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     kind: String (Optional)
     *     name: String (Required)
     * }
     * }</pre>
     *
     * @param kind A sequence of textual characters.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return used in internal operations, should be generated but not exported along with {@link Response} on
     * successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    Mono<Response<BinaryData>> discriminatorWithResponse(String kind, RequestOptions requestOptions) {
        return this.serviceClient.discriminatorWithResponseAsync(kind, requestOptions);
    }

    /**
     * Expected query parameter: name=&lt;any string&gt;
     * Expected response body:
     * ```json
     * {
     * "name": &lt;any string&gt;,
     * "inner":
     * {
     * "name": &lt;any string&gt;
     * }
     * }
     * ```.
     *
     * @param name A sequence of textual characters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return used in internal operations, should be generated but not exported on successful completion of
     * {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    Mono<OuterModel> operation(String name) {
        // Generated convenience method for operationWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return operationWithResponse(name, requestOptions).flatMap(FluxUtil::toMono)
            .map(protocolMethodData -> protocolMethodData.toObject(OuterModel.class));
    }

    /**
     * Expected query parameter: kind=&lt;any string&gt;
     * Expected response body:
     * ```json
     * {
     * "name": &lt;any string&gt;,
     * "kind": "real"
     * }
     * ```.
     *
     * @param kind A sequence of textual characters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return used in internal operations, should be generated but not exported on successful completion of
     * {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    Mono<AbstractModel> discriminator(String kind) {
        // Generated convenience method for discriminatorWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return discriminatorWithResponse(kind, requestOptions).flatMap(FluxUtil::toMono)
            .map(protocolMethodData -> protocolMethodData.toObject(AbstractModel.class));
    }
}
