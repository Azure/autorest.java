// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package tsptest.multipleapiversion;

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
import tsptest.multipleapiversion.implementation.NoApiVersionClientImpl;

/**
 * Initializes a new instance of the synchronous NoApiVersionClient type.
 */
@ServiceClient(builder = NoApiVersionClientBuilder.class)
public final class NoApiVersionClient {
    @Generated
    private final NoApiVersionClientImpl serviceClient;

    /**
     * Initializes an instance of NoApiVersionClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
    NoApiVersionClient(NoApiVersionClientImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * The action operation.
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
    public Response<Void> actionWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.actionWithResponse(requestOptions);
    }

    /**
     * The action operation.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void action() {
        // Generated convenience method for actionWithResponse
        RequestOptions requestOptions = new RequestOptions();
        actionWithResponse(requestOptions).getValue();
    }
}
