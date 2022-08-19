// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.cadl.testserver.enumpropertiesbasic;

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
import com.cadl.testserver.enumpropertiesbasic.implementation.EnumPropertiesBasicsImpl;
import com.cadl.testserver.enumpropertiesbasic.models.InputModel;
import com.cadl.testserver.enumpropertiesbasic.models.OutputModel;
import com.cadl.testserver.enumpropertiesbasic.models.RoundTripModel;
import reactor.core.publisher.Mono;

/** Initializes a new instance of the asynchronous EnumPropertiesBasicClient type. */
@ServiceClient(builder = EnumPropertiesBasicClientBuilder.class, isAsync = true)
public final class EnumPropertiesBasicAsyncClient {
    @Generated private final EnumPropertiesBasicsImpl serviceClient;

    /**
     * Initializes an instance of EnumPropertiesBasicAsyncClient class.
     *
     * @param serviceClient the service client implementation.
     */
    @Generated
    EnumPropertiesBasicAsyncClient(EnumPropertiesBasicsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * The sendEnumPropertyModel operation.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     Day: String(Monday/Tuesday/Wednesday/Thursday/Friday/Saturday/Sunday) (Required)
     *     Language: String(English/Spanish/Mandarin/Undocumented) (Required)
     * }
     * }</pre>
     *
     * @param input Input model with enum properties.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> sendEnumPropertyModelWithResponse(BinaryData input, RequestOptions requestOptions) {
        return this.serviceClient.sendEnumPropertyModelWithResponseAsync(input, requestOptions);
    }

    /**
     * The getEnumPropertModel operation.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     Day: String(Monday/Tuesday/Wednesday/Thursday/Friday/Saturday/Sunday) (Required)
     *     Language: String(English/Spanish/Mandarin/Undocumented) (Required)
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return output model with enum properties along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getEnumPropertModelWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getEnumPropertModelWithResponseAsync(requestOptions);
    }

    /**
     * The setEnumPropertModel operation.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     Day: String(Monday/Tuesday/Wednesday/Thursday/Friday/Saturday/Sunday) (Required)
     *     Language: String(English/Spanish/Mandarin/Undocumented) (Required)
     * }
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     Day: String(Monday/Tuesday/Wednesday/Thursday/Friday/Saturday/Sunday) (Required)
     *     Language: String(English/Spanish/Mandarin/Undocumented) (Required)
     * }
     * }</pre>
     *
     * @param input Round-trip model with enum properties.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return round-trip model with enum properties along with {@link Response} on successful completion of {@link
     *     Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> setEnumPropertModelWithResponse(BinaryData input, RequestOptions requestOptions) {
        return this.serviceClient.setEnumPropertModelWithResponseAsync(input, requestOptions);
    }

    /*
     * Generated convenience method for sendEnumPropertyModelWithResponse
     */
    /**
     * The sendEnumPropertyModel operation.
     *
     * @param input Input model with enum properties.
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
    public Mono<Void> sendEnumPropertyModel(InputModel input) {
        RequestOptions requestOptions = new RequestOptions();
        return sendEnumPropertyModelWithResponse(BinaryData.fromObject(input), requestOptions).map(Response::getValue);
    }

    /*
     * Generated convenience method for getEnumPropertModelWithResponse
     */
    /**
     * The getEnumPropertModel operation.
     *
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return output model with enum properties on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<OutputModel> getEnumPropertModel() {
        RequestOptions requestOptions = new RequestOptions();
        return getEnumPropertModelWithResponse(requestOptions)
                .map(Response::getValue)
                .map(protocolMethodData -> protocolMethodData.toObject(OutputModel.class));
    }

    /*
     * Generated convenience method for setEnumPropertModelWithResponse
     */
    /**
     * The setEnumPropertModel operation.
     *
     * @param input Round-trip model with enum properties.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return round-trip model with enum properties on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<RoundTripModel> setEnumPropertModel(RoundTripModel input) {
        RequestOptions requestOptions = new RequestOptions();
        return setEnumPropertModelWithResponse(BinaryData.fromObject(input), requestOptions)
                .map(Response::getValue)
                .map(protocolMethodData -> protocolMethodData.toObject(RoundTripModel.class));
    }
}
