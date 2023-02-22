// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.enums.fixed;

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
import com.enums.fixed.models.DaysOfWeekEnum;

/** Initializes a new instance of the synchronous FixedClient type. */
@ServiceClient(builder = FixedClientBuilder.class)
public final class FixedClient {
    @Generated private final FixedAsyncClient client;

    /**
     * Initializes an instance of FixedClient class.
     *
     * @param client the async client.
     */
    @Generated
    FixedClient(FixedAsyncClient client) {
        this.client = client;
    }

    /**
     * getKnownValue.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * String(Monday/Tuesday/Wednesday/Thursday/Friday/Saturday/Sunday)
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<String> getKnownValueWithResponse(RequestOptions requestOptions) {
        return this.client.getKnownValueWithResponse(requestOptions).block();
    }

    /**
     * putKnownValue.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * String(Monday/Tuesday/Wednesday/Thursday/Friday/Saturday/Sunday)
     * }</pre>
     *
     * @param body _.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putKnownValueWithResponse(BinaryData body, RequestOptions requestOptions) {
        return this.client.putKnownValueWithResponse(body, requestOptions).block();
    }

    /**
     * putUnknownValue.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * String(Monday/Tuesday/Wednesday/Thursday/Friday/Saturday/Sunday)
     * }</pre>
     *
     * @param body _.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putUnknownValueWithResponse(BinaryData body, RequestOptions requestOptions) {
        return this.client.putUnknownValueWithResponse(body, requestOptions).block();
    }

    /**
     * getKnownValue.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public DaysOfWeekEnum getKnownValue() {
        // Generated convenience method for getKnownValueWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return DaysOfWeekEnum.fromString(getKnownValueWithResponse(requestOptions).getValue());
    }

    /**
     * putKnownValue.
     *
     * @param body _.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putKnownValue(DaysOfWeekEnum body) {
        // Generated convenience method for putKnownValueWithResponse
        RequestOptions requestOptions = new RequestOptions();
        putKnownValueWithResponse(BinaryData.fromObject(body), requestOptions).getValue();
    }

    /**
     * putUnknownValue.
     *
     * @param body _.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putUnknownValue(DaysOfWeekEnum body) {
        // Generated convenience method for putUnknownValueWithResponse
        RequestOptions requestOptions = new RequestOptions();
        putUnknownValueWithResponse(BinaryData.fromObject(body), requestOptions).getValue();
    }
}
