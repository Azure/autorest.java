// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.versioning;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.exception.ClientAuthenticationException;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.exception.ResourceModifiedException;
import com.azure.core.exception.ResourceNotFoundException;
import com.azure.core.http.rest.PagedIterable;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.util.BinaryData;
import com.azure.core.util.logging.ClientLogger;
import com.azure.core.util.polling.PollOperationDetails;
import com.azure.core.util.polling.SyncPoller;
import com.cadl.versioning.implementation.VersioningOpsImpl;
import com.cadl.versioning.models.ExportedResource;
import com.cadl.versioning.models.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * Initializes a new instance of the synchronous VersioningClient type.
 */
@ServiceClient(builder = VersioningClientBuilder.class)
public final class VersioningClient {
    @Generated
    private final VersioningOpsImpl serviceClient;

    /**
     * Initializes an instance of VersioningClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
    VersioningClient(VersioningOpsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Long-running resource action operation template.
     * <p><strong>Query Parameters</strong></p>
     * <table border="1">
     * <caption>Query Parameters</caption>
     * <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     * <tr><td>projectFileVersion</td><td>String</td><td>No</td><td>A sequence of textual characters.</td></tr>
     * <tr><td>projectedFileFormat</td><td>String</td><td>No</td><td>A sequence of textual characters.</td></tr>
     * </table>
     * You can add these to a request with {@link RequestOptions#addQueryParam}
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     * id: String (Required)
     * status: String (Required)
     * error (Optional): {
     * code: String (Required)
     * message: String (Required)
     * target: String (Optional)
     * details (Optional): [
     * (recursive schema, see above)
     * ]
     * }
     * }
     * }</pre>
     * 
     * @param name A sequence of textual characters.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link SyncPoller} for polling of status details for long running operations.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginExport(String name, RequestOptions requestOptions) {
        return this.serviceClient.beginExport(name, requestOptions);
    }

    /**
     * Resource list operation template.
     * <p><strong>Query Parameters</strong></p>
     * <table border="1">
     * <caption>Query Parameters</caption>
     * <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     * <tr><td>select</td><td>List&lt;String&gt;</td><td>No</td><td>Select the specified fields to be included in the
     * response. Call {@link RequestOptions#addQueryParam} to add string to array.</td></tr>
     * <tr><td>filter</td><td>String</td><td>No</td><td>Filter the result list using the given expression.</td></tr>
     * </table>
     * You can add these to a request with {@link RequestOptions#addQueryParam}
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     * id: String (Required)
     * name: String (Required)
     * type: String (Required)
     * }
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return paged collection of Resource items as paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<BinaryData> list(RequestOptions requestOptions) {
        return this.serviceClient.list(requestOptions);
    }

    /**
     * Long-running resource action operation template.
     * 
     * @param name A sequence of textual characters.
     * @param projectFileVersion A sequence of textual characters.
     * @param projectedFileFormat A sequence of textual characters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link SyncPoller} for polling of status details for long running operations.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<PollOperationDetails, ExportedResource> beginExport(String name, String projectFileVersion,
        String projectedFileFormat) {
        // Generated convenience method for beginExportWithModel
        RequestOptions requestOptions = new RequestOptions();
        if (!Arrays.asList("2022-12-01-preview").contains(serviceClient.getServiceVersion().getVersion())) {
            throw LOGGER.logExceptionAsError(new IllegalArgumentException(
                "Parameter projectedFileFormat is only available in api-version 2022-12-01-preview."));
        }
        if (projectFileVersion != null) {
            requestOptions.addQueryParam("projectFileVersion", projectFileVersion, false);
        }
        if (projectedFileFormat != null) {
            requestOptions.addQueryParam("projectedFileFormat", projectedFileFormat, false);
        }
        return serviceClient.beginExportWithModel(name, requestOptions);
    }

    /**
     * Long-running resource action operation template.
     * 
     * @param name A sequence of textual characters.
     * @param projectFileVersion A sequence of textual characters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link SyncPoller} for polling of status details for long running operations.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<PollOperationDetails, ExportedResource> beginExport(String name, String projectFileVersion) {
        // Generated convenience method for beginExportWithModel
        RequestOptions requestOptions = new RequestOptions();
        if (projectFileVersion != null) {
            requestOptions.addQueryParam("projectFileVersion", projectFileVersion, false);
        }
        return serviceClient.beginExportWithModel(name, requestOptions);
    }

    /**
     * Long-running resource action operation template.
     * 
     * @param name A sequence of textual characters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link SyncPoller} for polling of status details for long running operations.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<PollOperationDetails, ExportedResource> beginExport(String name) {
        // Generated convenience method for beginExportWithModel
        RequestOptions requestOptions = new RequestOptions();
        return serviceClient.beginExportWithModel(name, requestOptions);
    }

    /**
     * Resource list operation template.
     * 
     * @param select Select the specified fields to be included in the response.
     * @param filter Filter the result list using the given expression.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return paged collection of Resource items as paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Resource> list(List<String> select, String filter) {
        // Generated convenience method for list
        RequestOptions requestOptions = new RequestOptions();
        if (!Arrays.asList("2022-12-01-preview").contains(serviceClient.getServiceVersion().getVersion())) {
            throw LOGGER.logExceptionAsError(
                new IllegalArgumentException("Parameter filter is only available in api-version 2022-12-01-preview."));
        }
        if (select != null) {
            for (String paramItemValue : select) {
                if (paramItemValue != null) {
                    requestOptions.addQueryParam("select", paramItemValue, false);
                }
            }
        }
        if (filter != null) {
            requestOptions.addQueryParam("filter", filter, false);
        }
        return serviceClient.list(requestOptions).mapPage(bodyItemValue -> bodyItemValue.toObject(Resource.class));
    }

    /**
     * Resource list operation template.
     * 
     * @param select Select the specified fields to be included in the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return paged collection of Resource items as paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Resource> list(List<String> select) {
        // Generated convenience method for list
        RequestOptions requestOptions = new RequestOptions();
        if (select != null) {
            for (String paramItemValue : select) {
                if (paramItemValue != null) {
                    requestOptions.addQueryParam("select", paramItemValue, false);
                }
            }
        }
        return serviceClient.list(requestOptions).mapPage(bodyItemValue -> bodyItemValue.toObject(Resource.class));
    }

    /**
     * Resource list operation template.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return paged collection of Resource items as paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Resource> list() {
        // Generated convenience method for list
        RequestOptions requestOptions = new RequestOptions();
        return serviceClient.list(requestOptions).mapPage(bodyItemValue -> bodyItemValue.toObject(Resource.class));
    }

    private static final ClientLogger LOGGER = new ClientLogger(VersioningClient.class);
}
