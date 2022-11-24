// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.models.visibility.automatic;

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
import com.models.visibility.automatic.models.VisibilityModel;

/** Initializes a new instance of the synchronous AutomaticClient type. */
@ServiceClient(builder = AutomaticClientBuilder.class)
public final class AutomaticClient {
    @Generated private final AutomaticAsyncClient client;

    /**
     * Initializes an instance of AutomaticClient class.
     *
     * @param client the async client.
     */
    @Generated
    AutomaticClient(AutomaticAsyncClient client) {
        this.client = client;
    }

    /**
     * The getModel operation.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     readProp: String (Required)
     *     queryProp: int (Required)
     *     createProp (Required): [
     *         String (Required)
     *     ]
     *     updateProp (Required): [
     *         int (Required)
     *     ]
     *     deleteProp: boolean (Required)
     * }
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     readProp: String (Required)
     *     queryProp: int (Required)
     *     createProp (Required): [
     *         String (Required)
     *     ]
     *     updateProp (Required): [
     *         int (Required)
     *     ]
     *     deleteProp: boolean (Required)
     * }
     * }</pre>
     *
     * @param input Output model with visibility properties.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return output model with visibility properties along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getModelWithResponse(BinaryData input, RequestOptions requestOptions) {
        return this.client.getModelWithResponse(input, requestOptions).block();
    }

    /**
     * The headModel operation.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     readProp: String (Required)
     *     queryProp: int (Required)
     *     createProp (Required): [
     *         String (Required)
     *     ]
     *     updateProp (Required): [
     *         int (Required)
     *     ]
     *     deleteProp: boolean (Required)
     * }
     * }</pre>
     *
     * @param input Output model with visibility properties.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> headModelWithResponse(BinaryData input, RequestOptions requestOptions) {
        return this.client.headModelWithResponse(input, requestOptions).block();
    }

    /**
     * The putModel operation.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     readProp: String (Required)
     *     queryProp: int (Required)
     *     createProp (Required): [
     *         String (Required)
     *     ]
     *     updateProp (Required): [
     *         int (Required)
     *     ]
     *     deleteProp: boolean (Required)
     * }
     * }</pre>
     *
     * @param input Output model with visibility properties.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putModelWithResponse(BinaryData input, RequestOptions requestOptions) {
        return this.client.putModelWithResponse(input, requestOptions).block();
    }

    /**
     * The patchModel operation.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     readProp: String (Required)
     *     queryProp: int (Required)
     *     createProp (Required): [
     *         String (Required)
     *     ]
     *     updateProp (Required): [
     *         int (Required)
     *     ]
     *     deleteProp: boolean (Required)
     * }
     * }</pre>
     *
     * @param input Output model with visibility properties.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> patchModelWithResponse(BinaryData input, RequestOptions requestOptions) {
        return this.client.patchModelWithResponse(input, requestOptions).block();
    }

    /**
     * The postModel operation.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     readProp: String (Required)
     *     queryProp: int (Required)
     *     createProp (Required): [
     *         String (Required)
     *     ]
     *     updateProp (Required): [
     *         int (Required)
     *     ]
     *     deleteProp: boolean (Required)
     * }
     * }</pre>
     *
     * @param input Output model with visibility properties.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> postModelWithResponse(BinaryData input, RequestOptions requestOptions) {
        return this.client.postModelWithResponse(input, requestOptions).block();
    }

    /**
     * The deleteModel operation.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     readProp: String (Required)
     *     queryProp: int (Required)
     *     createProp (Required): [
     *         String (Required)
     *     ]
     *     updateProp (Required): [
     *         int (Required)
     *     ]
     *     deleteProp: boolean (Required)
     * }
     * }</pre>
     *
     * @param input Output model with visibility properties.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> deleteModelWithResponse(BinaryData input, RequestOptions requestOptions) {
        return this.client.deleteModelWithResponse(input, requestOptions).block();
    }

    /**
     * The getModel operation.
     *
     * @param input Output model with visibility properties.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return output model with visibility properties.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public VisibilityModel getModel(VisibilityModel input) {
        // Generated convenience method for getModelWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return getModelWithResponse(BinaryData.fromObject(input), requestOptions)
                .getValue()
                .toObject(VisibilityModel.class);
    }

    /**
     * The headModel operation.
     *
     * @param input Output model with visibility properties.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void headModel(VisibilityModel input) {
        // Generated convenience method for headModelWithResponse
        RequestOptions requestOptions = new RequestOptions();
        headModelWithResponse(BinaryData.fromObject(input), requestOptions).getValue();
    }

    /**
     * The putModel operation.
     *
     * @param input Output model with visibility properties.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putModel(VisibilityModel input) {
        // Generated convenience method for putModelWithResponse
        RequestOptions requestOptions = new RequestOptions();
        putModelWithResponse(BinaryData.fromObject(input), requestOptions).getValue();
    }

    /**
     * The patchModel operation.
     *
     * @param input Output model with visibility properties.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void patchModel(VisibilityModel input) {
        // Generated convenience method for patchModelWithResponse
        RequestOptions requestOptions = new RequestOptions();
        patchModelWithResponse(BinaryData.fromObject(input), requestOptions).getValue();
    }

    /**
     * The postModel operation.
     *
     * @param input Output model with visibility properties.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void postModel(VisibilityModel input) {
        // Generated convenience method for postModelWithResponse
        RequestOptions requestOptions = new RequestOptions();
        postModelWithResponse(BinaryData.fromObject(input), requestOptions).getValue();
    }

    /**
     * The deleteModel operation.
     *
     * @param input Output model with visibility properties.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void deleteModel(VisibilityModel input) {
        // Generated convenience method for deleteModelWithResponse
        RequestOptions requestOptions = new RequestOptions();
        deleteModelWithResponse(BinaryData.fromObject(input), requestOptions).getValue();
    }
}
