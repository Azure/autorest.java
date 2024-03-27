// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.
package com.cadl.partialupdate;

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
import com.cadl.partialupdate.implementation.PartialUpdateClientImpl;
import com.cadl.partialupdate.models.PartialUpdateModel;
import reactor.core.publisher.Mono;

/**
 * Initializes a new instance of the asynchronous PartialUpdateClient type.
 */
@ServiceClient(builder = PartialUpdateClientBuilder.class, isAsync = true)
public final class PartialUpdateAsyncClient {

    @Generated
    private final PartialUpdateClientImpl serviceClient;

    /**
     * Initializes an instance of PartialUpdateAsyncClient class.
     *
     * @param serviceClient the service client implementation.
     */
    @Generated
    PartialUpdateAsyncClient(PartialUpdateClientImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * The read operation.
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     * boolean: boolean (Required)
     * string: String (Required)
     * bytes: byte[] (Required)
     * aggregate: String (Optional)
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> readWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.readWithResponseAsync(requestOptions);
    }

    /**
     * The read operation.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PartialUpdateModel> read() {
        // Generated convenience method for readWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return readWithResponse(requestOptions).flatMap(FluxUtil::toMono)
            .map(protocolMethodData -> protocolMethodData.toObject(PartialUpdateModel.class));
    }
}
