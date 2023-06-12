// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.parameters.bodyoptionality;

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
import com.parameters.bodyoptionality.implementation.OptionalExplicitsImpl;
import com.parameters.bodyoptionality.models.BodyModel;

/** Initializes a new instance of the synchronous BodyOptionalityClient type. */
@ServiceClient(builder = BodyOptionalityClientBuilder.class)
public final class OptionalExplicitClient {
    @Generated private final OptionalExplicitsImpl serviceClient;

    /**
     * Initializes an instance of OptionalExplicitClient class.
     *
     * @param serviceClient the service client implementation.
     */
    @Generated
    OptionalExplicitClient(OptionalExplicitsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * The set operation.
     *
     * <p><strong>Request Body Schema</strong>
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
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> setWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.setWithResponse(requestOptions);
    }

    /**
     * The omit operation.
     *
     * <p><strong>Request Body Schema</strong>
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
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> omitWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.omitWithResponse(requestOptions);
    }

    /**
     * The set operation.
     *
     * @param body The body parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void set(BodyModel body) {
        // Generated convenience method for setWithResponse
        RequestOptions requestOptions = new RequestOptions();
        if (body != null) {
            requestOptions.setBody(BinaryData.fromObject(body));
        }
        setWithResponse(requestOptions).getValue();
    }

    /**
     * The set operation.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void set() {
        // Generated convenience method for setWithResponse
        RequestOptions requestOptions = new RequestOptions();
        setWithResponse(requestOptions).getValue();
    }

    /**
     * The omit operation.
     *
     * @param body The body parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void omit(BodyModel body) {
        // Generated convenience method for omitWithResponse
        RequestOptions requestOptions = new RequestOptions();
        if (body != null) {
            requestOptions.setBody(BinaryData.fromObject(body));
        }
        omitWithResponse(requestOptions).getValue();
    }

    /**
     * The omit operation.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void omit() {
        // Generated convenience method for omitWithResponse
        RequestOptions requestOptions = new RequestOptions();
        omitWithResponse(requestOptions).getValue();
    }
}
