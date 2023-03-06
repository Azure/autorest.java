// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.collectionformat;

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
import com.collectionformat.implementation.CollectionFormatClientImpl;
import com.collectionformat.models.MessageResponse;
import java.util.List;
import reactor.core.publisher.Mono;

/** Initializes a new instance of the asynchronous CollectionFormatClient type. */
@ServiceClient(builder = CollectionFormatClientBuilder.class, isAsync = true)
public final class CollectionFormatAsyncClient {
    @Generated private final CollectionFormatClientImpl serviceClient;

    /**
     * Initializes an instance of CollectionFormatAsyncClient class.
     *
     * @param serviceClient the service client implementation.
     */
    @Generated
    CollectionFormatAsyncClient(CollectionFormatClientImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * The testMulti operation.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     message: String (Required)
     * }
     * }</pre>
     *
     * @param colors Possible values for colors are [blue,red,green].
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> testMultiWithResponse(List<String> colors, RequestOptions requestOptions) {
        return this.serviceClient.testMultiWithResponseAsync(colors, requestOptions);
    }

    /**
     * The testCsv operation.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     message: String (Required)
     * }
     * }</pre>
     *
     * @param colors Possible values for colors are [blue,red,green].
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> testCsvWithResponse(List<String> colors, RequestOptions requestOptions) {
        return this.serviceClient.testCsvWithResponseAsync(colors, requestOptions);
    }

    /**
     * The testMulti operation.
     *
     * @param colors Possible values for colors are [blue,red,green].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<MessageResponse> testMulti(List<String> colors) {
        // Generated convenience method for testMultiWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return testMultiWithResponse(colors, requestOptions)
                .flatMap(FluxUtil::toMono)
                .map(protocolMethodData -> protocolMethodData.toObject(MessageResponse.class));
    }

    /**
     * The testCsv operation.
     *
     * @param colors Possible values for colors are [blue,red,green].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<MessageResponse> testCsv(List<String> colors) {
        // Generated convenience method for testCsvWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return testCsvWithResponse(colors, requestOptions)
                .flatMap(FluxUtil::toMono)
                .map(protocolMethodData -> protocolMethodData.toObject(MessageResponse.class));
    }
}
