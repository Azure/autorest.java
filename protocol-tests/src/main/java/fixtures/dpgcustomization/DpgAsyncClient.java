// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.dpgcustomization;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.rest.PagedFlux;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import com.azure.core.util.polling.PollerFlux;
import fixtures.dpgcustomization.implementation.DpgClientImpl;
import reactor.core.publisher.Mono;

/** Initializes a new instance of the asynchronous DpgClient type. */
@ServiceClient(builder = DpgClientBuilder.class, isAsync = true)
public final class DpgAsyncClient {
    @Generated private final DpgClientImpl serviceClient;

    /**
     * Initializes an instance of DpgClient client.
     *
     * @param serviceClient the service client implementation.
     */
    @Generated
    DpgAsyncClient(DpgClientImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get models that you will either return to end users as a raw body, or with a model added during grow up.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     received: String(raw/model)
     * }
     * }</pre>
     *
     * @param mode The mode with which you'll be handling your returned body. 'raw' for just dealing with the raw body,
     *     and 'model' if you are going to convert the raw body to a customized body before returning to users.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return models that you will either return to end users as a raw body, or with a model added during grow up along
     *     with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getModelWithResponse(String mode, RequestOptions requestOptions) {
        return this.serviceClient.getModelWithResponseAsync(mode, requestOptions);
    }

    /**
     * Post either raw response as a model and pass in 'raw' for mode, or grow up your operation to take a model
     * instead, and put in 'model' as mode.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     hello: String
     * }
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     received: String(raw/model)
     * }
     * }</pre>
     *
     * @param mode The mode with which you'll be handling your returned body. 'raw' for just dealing with the raw body,
     *     and 'model' if you are going to convert the raw body to a customized body before returning to users.
     * @param input Please put {'hello': 'world!'}.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> postModelWithResponse(
            String mode, BinaryData input, RequestOptions requestOptions) {
        return this.serviceClient.postModelWithResponseAsync(mode, input, requestOptions);
    }

    /**
     * Get pages that you will either return to users in pages of raw bodies, or pages of models following growup.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             received: String(raw/model)
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param mode The mode with which you'll be handling your returned body. 'raw' for just dealing with the raw body,
     *     and 'model' if you are going to convert the raw body to a customized body before returning to users.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return pages that you will either return to users in pages of raw bodies, or pages of models following growup as
     *     paginated response with {@link PagedFlux}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> getPages(String mode, RequestOptions requestOptions) {
        return this.serviceClient.getPagesAsync(mode, requestOptions);
    }

    /**
     * Long running put request that will either return to end users a final payload of a raw body, or a final payload
     * of a model after the SDK has grown up.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     received: String(raw/model)
     *     provisioningState: String
     * }
     * }</pre>
     *
     * @param mode The mode with which you'll be handling your returned body. 'raw' for just dealing with the raw body,
     *     and 'model' if you are going to convert the raw body to a customized body before returning to users.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> lroWithResponse(String mode, RequestOptions requestOptions) {
        return this.serviceClient.lroWithResponseAsync(mode, requestOptions);
    }

    /**
     * Long running put request that will either return to end users a final payload of a raw body, or a final payload
     * of a model after the SDK has grown up.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     received: String(raw/model)
     *     provisioningState: String
     * }
     * }</pre>
     *
     * @param mode The mode with which you'll be handling your returned body. 'raw' for just dealing with the raw body,
     *     and 'model' if you are going to convert the raw body to a customized body before returning to users.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return the {@link PollerFlux} for polling of long-running operation.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginLro(String mode, RequestOptions requestOptions) {
        return this.serviceClient.beginLroAsync(mode, requestOptions);
    }

    /**
     * Sends the {@code httpRequest}.
     *
     * @param httpRequest The HTTP request to send.
     * @return the response body on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> sendRequest(HttpRequest httpRequest) {
        return this.serviceClient.sendRequestAsync(httpRequest);
    }
}
