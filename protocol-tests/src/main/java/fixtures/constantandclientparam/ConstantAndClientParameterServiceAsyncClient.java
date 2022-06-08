// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.constantandclientparam;

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
import fixtures.constantandclientparam.implementation.ConstantAndClientParameterServiceClientImpl;
import reactor.core.publisher.Mono;

/** Initializes a new instance of the asynchronous ConstantAndClientParameterServiceClient type. */
@ServiceClient(builder = ConstantAndClientParameterServiceClientBuilder.class, isAsync = true)
public final class ConstantAndClientParameterServiceAsyncClient {
    @Generated private final ConstantAndClientParameterServiceClientImpl serviceClient;

    /**
     * Initializes an instance of ConstantAndClientParameterServiceAsyncClient class.
     *
     * @param serviceClient the service client implementation.
     */
    @Generated
    ConstantAndClientParameterServiceAsyncClient(ConstantAndClientParameterServiceClientImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Pass constants from the client to this function. Will pass in constant path, query, and header parameters.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>query-non-required-client-param</td><td>Integer</td><td>No</td><td>Query parameter on the client that is not required</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putClientConstantsWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.putClientConstantsWithResponseAsync(requestOptions);
    }
}
