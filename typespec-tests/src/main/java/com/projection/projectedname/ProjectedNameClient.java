// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.
package com.projection.projectedname;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.projection.projectedname.implementation.ProjectedNameClientImpl;

/**
 * Initializes a new instance of the synchronous ProjectedNameClient type.
 */
@ServiceClient(builder = ProjectedNameClientBuilder.class)
public final class ProjectedNameClient {

    @Generated
    private final ProjectedNameClientImpl serviceClient;

    /**
     * Initializes an instance of ProjectedNameClient class.
     *
     * @param serviceClient the service client implementation.
     */
    @Generated
    ProjectedNameClient(ProjectedNameClientImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * The clientName operation.
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
    public Response<Void> clientNameWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.clientNameWithResponse(requestOptions);
    }

    /**
     * The parameter operation.
     *
     * @param clientName A sequence of textual characters.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> parameterWithResponse(String clientName, RequestOptions requestOptions) {
        return this.serviceClient.parameterWithResponse(clientName, requestOptions);
    }

    /**
     * The clientName operation.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void clientName() {
        // Generated convenience method for clientNameWithResponse
        RequestOptions requestOptions = new RequestOptions();
        clientNameWithResponse(requestOptions).getValue();
    }

    /**
     * The parameter operation.
     *
     * @param clientName A sequence of textual characters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void parameter(String clientName) {
        // Generated convenience method for parameterWithResponse
        RequestOptions requestOptions = new RequestOptions();
        parameterWithResponse(clientName, requestOptions).getValue();
    }
}
