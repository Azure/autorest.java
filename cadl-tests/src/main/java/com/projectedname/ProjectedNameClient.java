// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.projectedname;

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
import com.projectedname.models.Project;

/** Initializes a new instance of the synchronous ProjectedNameClient type. */
@ServiceClient(builder = ProjectedNameClientBuilder.class)
public final class ProjectedNameClient {
    @Generated private final ProjectedNameAsyncClient client;

    /**
     * Initializes an instance of ProjectedNameClient class.
     *
     * @param client the async client.
     */
    @Generated
    ProjectedNameClient(ProjectedNameAsyncClient client) {
        this.client = client;
    }

    /**
     * The jsonProjection operation.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     codegen: String (Optional)
     *     builtfrom: String (Optional)
     *     wasMadeFor: String (Optional)
     * }
     * }</pre>
     *
     * @param project The project parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> jsonProjectionWithResponse(BinaryData project, RequestOptions requestOptions) {
        return this.client.jsonProjectionWithResponse(project, requestOptions).block();
    }

    /**
     * The clientProjection operation.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     codegen: String (Optional)
     *     builtfrom: String (Optional)
     *     wasMadeFor: String (Optional)
     * }
     * }</pre>
     *
     * @param project The project parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> clientProjectionWithResponse(BinaryData project, RequestOptions requestOptions) {
        return this.client.clientProjectionWithResponse(project, requestOptions).block();
    }

    /**
     * The languageProjection operation.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     codegen: String (Optional)
     *     builtfrom: String (Optional)
     *     wasMadeFor: String (Optional)
     * }
     * }</pre>
     *
     * @param project The project parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> languageProjectionWithResponse(BinaryData project, RequestOptions requestOptions) {
        return this.client.languageProjectionWithResponse(project, requestOptions).block();
    }

    /**
     * The jsonProjection operation.
     *
     * @param project The project parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void jsonProjection(Project project) {
        // Generated convenience method for jsonProjectionWithResponse
        RequestOptions requestOptions = new RequestOptions();
        jsonProjectionWithResponse(BinaryData.fromObject(project), requestOptions).getValue();
    }

    /**
     * The clientProjection operation.
     *
     * @param project The project parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void clientProjection(Project project) {
        // Generated convenience method for clientProjectionWithResponse
        RequestOptions requestOptions = new RequestOptions();
        clientProjectionWithResponse(BinaryData.fromObject(project), requestOptions).getValue();
    }

    /**
     * The languageProjection operation.
     *
     * @param project The project parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void languageProjection(Project project) {
        // Generated convenience method for languageProjectionWithResponse
        RequestOptions requestOptions = new RequestOptions();
        languageProjectionWithResponse(BinaryData.fromObject(project), requestOptions).getValue();
    }
}
