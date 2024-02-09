// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.nonstringenum;

import com.azure.core.annotation.Generated;

import com.azure.core.annotation.ReturnType;

import com.azure.core.annotation.ServiceMethod;

import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.rest.Response;

import com.azure.core.util.Context;

import fixtures.nonstringenum.implementation.FloatOperationsImpl;
import fixtures.nonstringenum.models.FloatEnum;

/**
 * Initializes a new instance of the synchronous NonStringEnumsClient type.
 */
public final class FloatOperationClient {
    @Generated
    private final FloatOperationsImpl serviceClient;

    /**
     * Initializes an instance of FloatOperationClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
    FloatOperationClient(FloatOperationsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Put a float enum.
     * 
     * @param input Input float enum.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<String> putWithResponse(FloatEnum input, Context context) {
        return this.serviceClient.putWithResponse(input, context);
    }

    /**
     * Put a float enum.
     * 
     * @param input Input float enum.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public String put(FloatEnum input) {
        return this.serviceClient.put(input);
    }

    /**
     * Put a float enum.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public String put() {
        return this.serviceClient.put();
    }

    /**
     * Get a float enum.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a float enum along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<FloatEnum> getWithResponse(Context context) {
        return this.serviceClient.getWithResponse(context);
    }

    /**
     * Get a float enum.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a float enum.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public FloatEnum get() {
        return this.serviceClient.get();
    }
}
