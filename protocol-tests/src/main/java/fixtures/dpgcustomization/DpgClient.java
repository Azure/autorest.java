// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.dpgcustomization;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.exception.ClientAuthenticationException;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.exception.ResourceModifiedException;
import com.azure.core.exception.ResourceNotFoundException;
import com.azure.core.http.rest.PagedIterable;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import com.azure.core.util.polling.SyncPoller;
import fixtures.dpgcustomization.implementation.DpgClientImpl;

/**
 * Initializes a new instance of the synchronous DpgClient type.
 */
@ServiceClient(builder = DpgClientBuilder.class)
public final class DpgClient {
    @Generated
    private final DpgClientImpl serviceClient;

    /**
     * Initializes an instance of DpgClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
    DpgClient(DpgClientImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get models that you will either return to end users as a raw body, or with a model added during grow up.
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     received: String(raw/model) (Required)
     * }
     * }</pre>
     * 
     * @param mode The mode with which you'll be handling your returned body. 'raw' for just dealing with the raw body,
     * and 'model' if you are going to convert the raw body to a customized body before returning to users.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return models that you will either return to end users as a raw body, or with a model added during grow up along
     * with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getModelWithResponse(String mode, RequestOptions requestOptions) {
        return this.serviceClient.getModelWithResponse(mode, requestOptions);
    }

    /**
     * Post either raw response as a model and pass in 'raw' for mode, or grow up your operation to take a model
     * instead, and put in 'model' as mode.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     hello: String (Required)
     * }
     * }</pre>
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     received: String(raw/model) (Required)
     * }
     * }</pre>
     * 
     * @param mode The mode with which you'll be handling your returned body. 'raw' for just dealing with the raw body,
     * and 'model' if you are going to convert the raw body to a customized body before returning to users.
     * @param input Please put {'hello': 'world!'}.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> postModelWithResponse(String mode, BinaryData input, RequestOptions requestOptions) {
        return this.serviceClient.postModelWithResponse(mode, input, requestOptions);
    }

    /**
     * Get pages that you will either return to users in pages of raw bodies, or pages of models following growup.
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     received: String(raw/model) (Required)
     * }
     * }</pre>
     * 
     * @param mode The mode with which you'll be handling your returned body. 'raw' for just dealing with the raw body,
     * and 'model' if you are going to convert the raw body to a customized body before returning to users.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return pages that you will either return to users in pages of raw bodies, or pages of models following growup as
     * paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<BinaryData> getPages(String mode, RequestOptions requestOptions) {
        return this.serviceClient.getPages(mode, requestOptions);
    }

    /**
     * Long running put request that will either return to end users a final payload of a raw body, or a final payload
     * of a model after the SDK has grown up.
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     received: String(raw/model) (Required)
     *     provisioningState: String (Required)
     * }
     * }</pre>
     * 
     * @param mode The mode with which you'll be handling your returned body. 'raw' for just dealing with the raw body,
     * and 'model' if you are going to convert the raw body to a customized body before returning to users.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link SyncPoller} for polling of long-running operation.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginLro(String mode, RequestOptions requestOptions) {
        return this.serviceClient.beginLro(mode, requestOptions);
    }
}
