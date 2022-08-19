// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.cadl.testserver.optionalproperties;

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
import com.cadl.testserver.optionalproperties.implementation.OptionalPropertiesImpl;
import com.cadl.testserver.optionalproperties.models.InputModel;
import com.cadl.testserver.optionalproperties.models.OutputModel;
import com.cadl.testserver.optionalproperties.models.RoundTripModel;
import reactor.core.publisher.Mono;

/** Initializes a new instance of the asynchronous OptionalPropertiesClient type. */
@ServiceClient(builder = OptionalPropertiesClientBuilder.class, isAsync = true)
public final class OptionalPropertiesAsyncClient {
    @Generated private final OptionalPropertiesImpl serviceClient;

    /**
     * Initializes an instance of OptionalPropertiesAsyncClient class.
     *
     * @param serviceClient the service client implementation.
     */
    @Generated
    OptionalPropertiesAsyncClient(OptionalPropertiesImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * The sendOptionalPropertyModel operation.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     optionalString: String (Optional)
     *     optionalInt: Long (Optional)
     *     optionalStringList (Optional): [
     *         String (Optional)
     *     ]
     *     optionalIntList (Optional): [
     *         long (Optional)
     *     ]
     * }
     * }</pre>
     *
     * @param input Input model with optional properties.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> sendOptionalPropertyModelWithResponse(BinaryData input, RequestOptions requestOptions) {
        return this.serviceClient.sendOptionalPropertyModelWithResponseAsync(input, requestOptions);
    }

    /**
     * The getOptionalPropertyModel operation.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     optionalString: String (Optional)
     *     optionalInt: Long (Optional)
     *     optionalStringList (Optional): [
     *         String (Optional)
     *     ]
     *     optionalIntList (Optional): [
     *         long (Optional)
     *     ]
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return output model with optional properties along with {@link Response} on successful completion of {@link
     *     Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getOptionalPropertyModelWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getOptionalPropertyModelWithResponseAsync(requestOptions);
    }

    /**
     * The setOptionalPropertyModel operation.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     optionalString: String (Optional)
     *     optionalInt: Long (Optional)
     *     optionalStringList (Optional): [
     *         String (Optional)
     *     ]
     *     optionalIntList (Optional): [
     *         long (Optional)
     *     ]
     * }
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     optionalString: String (Optional)
     *     optionalInt: Long (Optional)
     *     optionalStringList (Optional): [
     *         String (Optional)
     *     ]
     *     optionalIntList (Optional): [
     *         long (Optional)
     *     ]
     * }
     * }</pre>
     *
     * @param input Round-trip model with optional properties.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return round-trip model with optional properties along with {@link Response} on successful completion of {@link
     *     Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> setOptionalPropertyModelWithResponse(
            BinaryData input, RequestOptions requestOptions) {
        return this.serviceClient.setOptionalPropertyModelWithResponseAsync(input, requestOptions);
    }

    /*
     * Generated convenience method for sendOptionalPropertyModelWithResponse
     */
    /**
     * The sendOptionalPropertyModel operation.
     *
     * @param input Input model with optional properties.
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
    public Mono<Void> sendOptionalPropertyModel(InputModel input) {
        RequestOptions requestOptions = new RequestOptions();
        return sendOptionalPropertyModelWithResponse(BinaryData.fromObject(input), requestOptions)
                .map(Response::getValue);
    }

    /*
     * Generated convenience method for getOptionalPropertyModelWithResponse
     */
    /**
     * The getOptionalPropertyModel operation.
     *
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return output model with optional properties on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<OutputModel> getOptionalPropertyModel() {
        RequestOptions requestOptions = new RequestOptions();
        return getOptionalPropertyModelWithResponse(requestOptions)
                .map(Response::getValue)
                .map(protocolMethodData -> protocolMethodData.toObject(OutputModel.class));
    }

    /*
     * Generated convenience method for setOptionalPropertyModelWithResponse
     */
    /**
     * The setOptionalPropertyModel operation.
     *
     * @param input Round-trip model with optional properties.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return round-trip model with optional properties on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<RoundTripModel> setOptionalPropertyModel(RoundTripModel input) {
        RequestOptions requestOptions = new RequestOptions();
        return setOptionalPropertyModelWithResponse(BinaryData.fromObject(input), requestOptions)
                .map(Response::getValue)
                .map(protocolMethodData -> protocolMethodData.toObject(RoundTripModel.class));
    }
}
