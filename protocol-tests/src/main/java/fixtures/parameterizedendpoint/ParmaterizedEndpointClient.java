// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.parameterizedendpoint;

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

/** Initializes a new instance of the synchronous ParmaterizedEndpointClient type. */
@ServiceClient(builder = ParmaterizedEndpointClientBuilder.class)
public final class ParmaterizedEndpointClient {
    @Generated private final ParmaterizedEndpointAsyncClient client;

    /**
     * Initializes an instance of ParmaterizedEndpointClient class.
     *
     * @param client the async client.
     */
    @Generated
    ParmaterizedEndpointClient(ParmaterizedEndpointAsyncClient client) {
        this.client = client;
    }

    /**
     * Basic get to make sure base url formatting of 'endpoint' works.
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
    public Response<Void> getWithResponse(RequestOptions requestOptions) {
        return this.client.getWithResponse(requestOptions).block();
    }
}
