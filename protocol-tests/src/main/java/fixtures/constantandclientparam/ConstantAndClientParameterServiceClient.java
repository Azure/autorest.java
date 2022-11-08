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

/** Initializes a new instance of the synchronous ConstantAndClientParameterServiceClient type. */
@ServiceClient(builder = ConstantAndClientParameterServiceClientBuilder.class)
public final class ConstantAndClientParameterServiceClient {
    @Generated private final ConstantAndClientParameterServiceAsyncClient client;

    /**
     * Initializes an instance of ConstantAndClientParameterServiceClient class.
     *
     * @param client the async client.
     */
    @Generated
    ConstantAndClientParameterServiceClient(ConstantAndClientParameterServiceAsyncClient client) {
        this.client = client;
    }

    /**
     * Pass constants from the client to this function. Will pass in constant path, query, and header parameters.
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
    public Response<Void> putClientConstantsWithResponse(RequestOptions requestOptions) {
        return this.client.putClientConstantsWithResponse(requestOptions).block();
    }

    /**
     * Pass constants from the client to this function. Will pass in constant path, query, and header parameters.
     *
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putClientConstants() {
        // Generated convenience method for putClientConstantsWithResponse
        RequestOptions requestOptions = new RequestOptions();
        putClientConstantsWithResponse(requestOptions).getValue();
    }
}
