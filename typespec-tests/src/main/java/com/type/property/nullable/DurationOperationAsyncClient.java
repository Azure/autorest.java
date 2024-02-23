// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.property.nullable;

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
import com.type.property.nullable.implementation.DurationOperationsImpl;
import com.type.property.nullable.implementation.JsonMergePatchHelper;
import com.type.property.nullable.models.DurationProperty;
import reactor.core.publisher.Mono;

/**
 * Initializes a new instance of the asynchronous NullableClient type.
 */
@ServiceClient(builder = NullableClientBuilder.class, isAsync = true)
public final class DurationOperationAsyncClient {
    @Generated
    private final DurationOperationsImpl serviceClient;

    /**
     * Initializes an instance of DurationOperationAsyncClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
    DurationOperationAsyncClient(DurationOperationsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get models that will return all properties in the model.
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     requiredProperty: String (Optional, Required on create)
     *     nullableProperty: Duration (Optional, Required on create)
     * }
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return models that will return all properties in the model along with {@link Response} on successful completion
     * of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getNonNullWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getNonNullWithResponseAsync(requestOptions);
    }

    /**
     * Get models that will return the default object.
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     requiredProperty: String (Optional, Required on create)
     *     nullableProperty: Duration (Optional, Required on create)
     * }
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return models that will return the default object along with {@link Response} on successful completion of
     * {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getNullWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getNullWithResponseAsync(requestOptions);
    }

    /**
     * Put a body with all properties present.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     requiredProperty: String (Optional, Required on create)
     *     nullableProperty: Duration (Optional, Required on create)
     * }
     * }</pre>
     * 
     * @param body Model with a duration property.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> patchNonNullWithResponse(BinaryData body, RequestOptions requestOptions) {
        return this.serviceClient.patchNonNullWithResponseAsync(body, requestOptions);
    }

    /**
     * Put a body with default properties.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     requiredProperty: String (Optional, Required on create)
     *     nullableProperty: Duration (Optional, Required on create)
     * }
     * }</pre>
     * 
     * @param body Model with a duration property.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> patchNullWithResponse(BinaryData body, RequestOptions requestOptions) {
        return this.serviceClient.patchNullWithResponseAsync(body, requestOptions);
    }

    /**
     * Get models that will return all properties in the model.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return models that will return all properties in the model on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DurationProperty> getNonNull() {
        // Generated convenience method for getNonNullWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return getNonNullWithResponse(requestOptions).flatMap(FluxUtil::toMono)
            .map(protocolMethodData -> protocolMethodData.toObject(DurationProperty.class));
    }

    /**
     * Get models that will return the default object.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return models that will return the default object on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DurationProperty> getNull() {
        // Generated convenience method for getNullWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return getNullWithResponse(requestOptions).flatMap(FluxUtil::toMono)
            .map(protocolMethodData -> protocolMethodData.toObject(DurationProperty.class));
    }

    /**
     * Put a body with all properties present.
     * 
     * @param body Model with a duration property.
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
    public Mono<Void> patchNonNull(DurationProperty body) {
        // Generated convenience method for patchNonNullWithResponse
        RequestOptions requestOptions = new RequestOptions();
        JsonMergePatchHelper.getDurationPropertyAccessor().prepareModelForJsonMergePatch(body, true);
        BinaryData bodyInBinaryData = BinaryData.fromBytes(BinaryData.fromObject(body).toBytes());
        JsonMergePatchHelper.getDurationPropertyAccessor().prepareModelForJsonMergePatch(body, false);
        return patchNonNullWithResponse(bodyInBinaryData, requestOptions).flatMap(FluxUtil::toMono);
    }

    /**
     * Put a body with default properties.
     * 
     * @param body Model with a duration property.
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
    public Mono<Void> patchNull(DurationProperty body) {
        // Generated convenience method for patchNullWithResponse
        RequestOptions requestOptions = new RequestOptions();
        JsonMergePatchHelper.getDurationPropertyAccessor().prepareModelForJsonMergePatch(body, true);
        BinaryData bodyInBinaryData = BinaryData.fromBytes(BinaryData.fromObject(body).toBytes());
        JsonMergePatchHelper.getDurationPropertyAccessor().prepareModelForJsonMergePatch(body, false);
        return patchNullWithResponse(bodyInBinaryData, requestOptions).flatMap(FluxUtil::toMono);
    }
}
