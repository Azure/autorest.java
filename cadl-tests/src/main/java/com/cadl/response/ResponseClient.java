// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.cadl.response;

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
import com.azure.core.http.rest.ResponseBase;
import com.azure.core.http.rest.SimpleResponse;
import com.azure.core.util.BinaryData;
import com.azure.core.util.Context;
import com.cadl.response.models.Resource;
import com.cadl.response.models.ResponseOpsCreateWithHeadersHeaders;
import com.cadl.response.models.ResponseOpsDeleteWithHeadersHeaders;

/** Initializes a new instance of the synchronous ResponseClient type. */
@ServiceClient(builder = ResponseClientBuilder.class)
public final class ResponseClient {
    @Generated private final ResponseAsyncClient client;

    /**
     * Initializes an instance of ResponseClient class.
     *
     * @param client the async client.
     */
    @Generated
    ResponseClient(ResponseAsyncClient client) {
        this.client = client;
    }

    /**
     * The getBinary operation.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * BinaryData
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
    public Response<BinaryData> getBinaryWithResponse(RequestOptions requestOptions) {
        return this.client.getBinaryWithResponse(requestOptions).block();
    }

    /**
     * The createWithHeaders operation.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     *     type: String (Required)
     * }
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
    public Response<BinaryData> createWithHeadersWithResponse(RequestOptions requestOptions) {
        return this.client.createWithHeadersWithResponse(requestOptions).block();
    }

    /**
     * The deleteWithHeaders operation.
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
    public Response<Void> deleteWithHeadersWithResponse(RequestOptions requestOptions) {
        return this.client.deleteWithHeadersWithResponse(requestOptions).block();
    }

    /**
     * Creates or replaces a Resource.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     *     type: String (Required)
     * }
     * }</pre>
     *
     * @param name The name parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> createWithResponse(String name, RequestOptions requestOptions) {
        return this.client.createWithResponse(name, requestOptions).block();
    }

    /**
     * The getBinary operation.
     *
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData getBinary() {
        // Generated convenience method for getBinaryWithResponse

        RequestOptions requestOptions = new RequestOptions();
        return getBinaryWithResponse(requestOptions).getValue();
    }

    /**
     * The getBinary operation.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getBinaryWithResponse(Context context) {
        // Generated convenience method for getBinaryWithResponse

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.setContext(context);
        return getBinaryWithResponse(requestOptions);
    }

    /**
     * The createWithHeaders operation.
     *
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Resource createWithHeaders() {
        // Generated convenience method for createWithHeadersWithResponse

        RequestOptions requestOptions = new RequestOptions();
        return createWithHeadersWithResponse(requestOptions).getValue().toObject(Resource.class);
    }

    /**
     * The createWithHeaders operation.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link ResponseBase}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ResponseBase<ResponseOpsCreateWithHeadersHeaders, Resource> createWithHeadersWithResponse(Context context) {
        // Generated convenience method for createWithHeadersWithResponse

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.setContext(context);
        Response<BinaryData> protocolMethodResponse = createWithHeadersWithResponse(requestOptions);
        return new ResponseBase<>(
                protocolMethodResponse.getRequest(),
                protocolMethodResponse.getStatusCode(),
                protocolMethodResponse.getHeaders(),
                protocolMethodResponse.getValue().toObject(Resource.class),
                new ResponseOpsCreateWithHeadersHeaders(protocolMethodResponse.getHeaders()));
    }

    /**
     * The deleteWithHeaders operation.
     *
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void deleteWithHeaders() {
        // Generated convenience method for deleteWithHeadersWithResponse

        RequestOptions requestOptions = new RequestOptions();
        deleteWithHeadersWithResponse(requestOptions).getValue();
    }

    /**
     * The deleteWithHeaders operation.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link ResponseBase}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ResponseBase<ResponseOpsDeleteWithHeadersHeaders, Void> deleteWithHeadersWithResponse(Context context) {
        // Generated convenience method for deleteWithHeadersWithResponse

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.setContext(context);
        Response<Void> protocolMethodResponse = deleteWithHeadersWithResponse(requestOptions);
        return new ResponseBase<>(
                protocolMethodResponse.getRequest(),
                protocolMethodResponse.getStatusCode(),
                protocolMethodResponse.getHeaders(),
                null,
                new ResponseOpsDeleteWithHeadersHeaders(protocolMethodResponse.getHeaders()));
    }

    /**
     * Creates or replaces a Resource.
     *
     * @param name The name parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Resource create(String name) {
        // Generated convenience method for createWithResponse

        RequestOptions requestOptions = new RequestOptions();
        return createWithResponse(name, requestOptions).getValue().toObject(Resource.class);
    }

    /**
     * Creates or replaces a Resource.
     *
     * @param name The name parameter.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Resource> createWithResponse(String name, Context context) {
        // Generated convenience method for createWithResponse

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.setContext(context);
        Response<BinaryData> protocolMethodResponse = createWithResponse(name, requestOptions);
        return new SimpleResponse<>(protocolMethodResponse, protocolMethodResponse.getValue().toObject(Resource.class));
    }
}
