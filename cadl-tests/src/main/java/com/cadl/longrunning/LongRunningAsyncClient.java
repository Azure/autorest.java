// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.cadl.longrunning;

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
import com.azure.core.util.polling.PollerFlux;
import com.cadl.longrunning.implementation.LongRunningClientImpl;
import com.cadl.longrunning.models.ExportedResource;
import com.cadl.longrunning.models.OperationStatusResourceResource;
import com.cadl.longrunning.models.Resource;
import reactor.core.publisher.Mono;

/** Initializes a new instance of the asynchronous LongRunningClient type. */
@ServiceClient(builder = LongRunningClientBuilder.class, isAsync = true)
public final class LongRunningAsyncClient {
    @Generated private final LongRunningClientImpl serviceClient;

    /**
     * Initializes an instance of LongRunningAsyncClient class.
     *
     * @param serviceClient the service client implementation.
     */
    @Generated
    LongRunningAsyncClient(LongRunningClientImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * The statusMonitor operation.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: String (Required)
     *     status: String(InProgress/Succeeded/Failed/Canceled) (Required)
     *     error: ResponseError (Optional)
     *     result (Optional): {
     *         id: String (Required)
     *         name: String (Required)
     *         type: String (Required)
     *     }
     * }
     * }</pre>
     *
     * @param name The name parameter.
     * @param operationId The unique ID of the operation.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return provides status details for long running operations along with {@link Response} on successful completion
     *     of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> statusMonitorWithResponse(
            String name, String operationId, RequestOptions requestOptions) {
        return this.serviceClient.statusMonitorWithResponseAsync(name, operationId, requestOptions);
    }

    /**
     * The createOrUpdate operation.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     *     type: String (Required)
     * }
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     *     type: String (Required)
     * }
     * }</pre>
     *
     * @param name The name parameter.
     * @param resource The resource parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link PollerFlux} for polling of long-running operation.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginCreateOrUpdate(
            String name, BinaryData resource, RequestOptions requestOptions) {
        return this.serviceClient.beginCreateOrUpdateAsync(name, resource, requestOptions);
    }

    /**
     * The get operation.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     *     type: String (Required)
     * }
     * }</pre>
     *
     * @param name The name parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getWithResponse(String name, RequestOptions requestOptions) {
        return this.serviceClient.getWithResponseAsync(name, requestOptions);
    }

    /**
     * The delete operation.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: String (Required)
     *     status: String(InProgress/Succeeded/Failed/Canceled) (Required)
     *     error: ResponseError (Optional)
     * }
     * }</pre>
     *
     * @param name The name parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link PollerFlux} for polling of provides status details for long running operations.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, Void> beginDelete(String name, RequestOptions requestOptions) {
        return this.serviceClient.beginDeleteAsync(name, requestOptions);
    }

    /**
     * The export operation.
     *
     * @param name The name parameter.
     * @param projectFileVersion The projectFileVersion parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link PollerFlux} for polling of long-running operation.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, Void> beginExport(
            String name, String projectFileVersion, RequestOptions requestOptions) {
        return this.serviceClient.beginExportAsync(name, projectFileVersion, requestOptions);
    }

    /**
     * The importx operation.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: String (Required)
     *     resourceUri: String (Required)
     * }
     * }</pre>
     *
     * @param name The name parameter.
     * @param exportedResource The exportedResource parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link PollerFlux} for polling of long-running operation.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, Void> beginImportx(
            String name, BinaryData exportedResource, RequestOptions requestOptions) {
        return this.serviceClient.beginImportxAsync(name, exportedResource, requestOptions);
    }

    /**
     * The statusMonitor operation.
     *
     * @param name The name parameter.
     * @param operationId The unique ID of the operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return provides status details for long running operations on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<OperationStatusResourceResource> statusMonitorConvenience(String name, String operationId) {
        // Generated convenience method for statusMonitorWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return statusMonitorWithResponse(name, operationId, requestOptions)
                .map(Response::getValue)
                .map(protocolMethodData -> protocolMethodData.toObject(OperationStatusResourceResource.class));
    }

    /**
     * The createOrUpdate operation.
     *
     * @param name The name parameter.
     * @param resource The resource parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link PollerFlux} for polling of long-running operation.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<OperationStatusResourceResource, Resource> beginCreateOrUpdate(String name, Resource resource) {
        // Generated convenience method for beginCreateOrUpdateWithModel
        RequestOptions requestOptions = new RequestOptions();
        return serviceClient.beginCreateOrUpdateWithModelAsync(name, BinaryData.fromObject(resource), requestOptions);
    }

    /**
     * The get operation.
     *
     * @param name The name parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Resource> get(String name) {
        // Generated convenience method for getWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return getWithResponse(name, requestOptions)
                .map(Response::getValue)
                .map(protocolMethodData -> protocolMethodData.toObject(Resource.class));
    }

    /**
     * The delete operation.
     *
     * @param name The name parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link PollerFlux} for polling of provides status details for long running operations.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<OperationStatusResourceResource, Void> beginDelete(String name) {
        // Generated convenience method for beginDeleteWithModel
        RequestOptions requestOptions = new RequestOptions();
        return serviceClient.beginDeleteWithModelAsync(name, requestOptions);
    }

    /**
     * The export operation.
     *
     * @param name The name parameter.
     * @param projectFileVersion The projectFileVersion parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link PollerFlux} for polling of long-running operation.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<OperationStatusResourceResource, Void> beginExport(String name, String projectFileVersion) {
        // Generated convenience method for beginExportWithModel
        RequestOptions requestOptions = new RequestOptions();
        return serviceClient.beginExportWithModelAsync(name, projectFileVersion, requestOptions);
    }

    /**
     * The importx operation.
     *
     * @param name The name parameter.
     * @param exportedResource The exportedResource parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link PollerFlux} for polling of long-running operation.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<OperationStatusResourceResource, Void> beginImportx(
            String name, ExportedResource exportedResource) {
        // Generated convenience method for beginImportxWithModel
        RequestOptions requestOptions = new RequestOptions();
        return serviceClient.beginImportxWithModelAsync(name, BinaryData.fromObject(exportedResource), requestOptions);
    }
}
