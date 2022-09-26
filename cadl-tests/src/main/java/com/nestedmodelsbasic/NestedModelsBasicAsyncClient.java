// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.nestedmodelsbasic;

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
import com.nestedmodelsbasic.implementation.NestedModelsBasicsImpl;
import com.nestedmodelsbasic.models.InputModel;
import com.nestedmodelsbasic.models.OutputModel;
import com.nestedmodelsbasic.models.RoundTripModel;
import reactor.core.publisher.Mono;

/** Initializes a new instance of the asynchronous NestedModelsBasicClient type. */
@ServiceClient(builder = NestedModelsBasicClientBuilder.class, isAsync = true)
public final class NestedModelsBasicAsyncClient {
    @Generated private final NestedModelsBasicsImpl serviceClient;

    /**
     * Initializes an instance of NestedModelsBasicAsyncClient class.
     *
     * @param serviceClient the service client implementation.
     */
    @Generated
    NestedModelsBasicAsyncClient(NestedModelsBasicsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * The sendNestedModel operation.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     NestedInputModel (Required): {
     *         requiredString: String (Required)
     *         requiredInt: int (Required)
     *         requiredStringList (Required): [
     *             String (Required)
     *         ]
     *         requiredIntList (Required): [
     *             int (Required)
     *         ]
     *     }
     *     NestedSharedModel (Required): {
     *         requiredString: String (Required)
     *         requiredInt: int (Required)
     *         requiredStringList (Required): [
     *             String (Required)
     *         ]
     *         requiredIntList (Required): [
     *             int (Required)
     *         ]
     *     }
     * }
     * }</pre>
     *
     * @param input Input model with nested model properties.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> sendNestedModelWithResponse(BinaryData input, RequestOptions requestOptions) {
        return this.serviceClient.sendNestedModelWithResponseAsync(input, requestOptions);
    }

    /**
     * The getNestedModel operation.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     NestedOutputModel (Required): {
     *         requiredString: String (Required)
     *         requiredInt: int (Required)
     *         requiredStringList (Required): [
     *             String (Required)
     *         ]
     *         requiredIntList (Required): [
     *             int (Required)
     *         ]
     *     }
     *     NestedSharedModel (Required): {
     *         requiredString: String (Required)
     *         requiredInt: int (Required)
     *         requiredStringList (Required): [
     *             String (Required)
     *         ]
     *         requiredIntList (Required): [
     *             int (Required)
     *         ]
     *     }
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return output model with nested model properties along with {@link Response} on successful completion of {@link
     *     Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getNestedModelWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getNestedModelWithResponseAsync(requestOptions);
    }

    /**
     * The setNestedModel operation.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     NestedRoundTripModel (Required): {
     *         requiredString: String (Required)
     *         requiredInt: int (Required)
     *         requiredStringList (Required): [
     *             String (Required)
     *         ]
     *         requiredIntList (Required): [
     *             int (Required)
     *         ]
     *     }
     *     NestedSharedModel (Required): {
     *         requiredString: String (Required)
     *         requiredInt: int (Required)
     *         requiredStringList (Required): [
     *             String (Required)
     *         ]
     *         requiredIntList (Required): [
     *             int (Required)
     *         ]
     *     }
     * }
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     NestedRoundTripModel (Required): {
     *         requiredString: String (Required)
     *         requiredInt: int (Required)
     *         requiredStringList (Required): [
     *             String (Required)
     *         ]
     *         requiredIntList (Required): [
     *             int (Required)
     *         ]
     *     }
     *     NestedSharedModel (Required): {
     *         requiredString: String (Required)
     *         requiredInt: int (Required)
     *         requiredStringList (Required): [
     *             String (Required)
     *         ]
     *         requiredIntList (Required): [
     *             int (Required)
     *         ]
     *     }
     * }
     * }</pre>
     *
     * @param input Round-trip model with nested model properties.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return round-trip model with nested model properties along with {@link Response} on successful completion of
     *     {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> setNestedModelWithResponse(BinaryData input, RequestOptions requestOptions) {
        return this.serviceClient.setNestedModelWithResponseAsync(input, requestOptions);
    }

    /**
     * The sendNestedModel operation.
     *
     * @param input Input model with nested model properties.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> sendNestedModel(InputModel input) {
        // Generated convenience method for sendNestedModelWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return sendNestedModelWithResponse(BinaryData.fromObject(input), requestOptions).then();
    }

    /**
     * The getNestedModel operation.
     *
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return output model with nested model properties on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<OutputModel> getNestedModel() {
        // Generated convenience method for getNestedModelWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return getNestedModelWithResponse(requestOptions)
                .map(Response::getValue)
                .map(protocolMethodData -> protocolMethodData.toObject(OutputModel.class));
    }

    /**
     * The setNestedModel operation.
     *
     * @param input Round-trip model with nested model properties.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return round-trip model with nested model properties on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<RoundTripModel> setNestedModel(RoundTripModel input) {
        // Generated convenience method for setNestedModelWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return setNestedModelWithResponse(BinaryData.fromObject(input), requestOptions)
                .map(Response::getValue)
                .map(protocolMethodData -> protocolMethodData.toObject(RoundTripModel.class));
    }
}
