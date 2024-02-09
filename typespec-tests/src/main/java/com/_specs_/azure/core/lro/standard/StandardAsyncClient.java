// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.
package com._specs_.azure.core.lro.standard;

import com._specs_.azure.core.lro.standard.implementation.StandardClientImpl;
import com._specs_.azure.core.lro.standard.models.ExportedUser;
import com._specs_.azure.core.lro.standard.models.User;
import com.azure.core.annotation.Generated;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.exception.ClientAuthenticationException;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.exception.ResourceModifiedException;
import com.azure.core.exception.ResourceNotFoundException;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.util.BinaryData;
import com.azure.core.util.polling.PollerFlux;
import com.azure.core.util.polling.PollOperationDetails;

/**
 * Initializes a new instance of the asynchronous StandardClient type.
 */
@ServiceClient(builder = StandardClientBuilder.class, isAsync = true)
public final class StandardAsyncClient {

    @Generated
    private final StandardClientImpl serviceClient;

    /**
     * Initializes an instance of StandardAsyncClient class.
     *
     * @param serviceClient the service client implementation.
     */
    @Generated
    StandardAsyncClient(StandardClientImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Adds a user or replaces a user's fields.
     *
     * Creates or replaces a User.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     name: String (Required)
     *     role: String (Required)
     * }
     * }</pre>
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     name: String (Required)
     *     role: String (Required)
     * }
     * }</pre>
     *
     * @param name The name of user.
     * @param resource The resource instance.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link PollerFlux} for polling of details about a user.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginCreateOrReplace(String name, BinaryData resource,
        RequestOptions requestOptions) {
        return this.serviceClient.beginCreateOrReplaceAsync(name, resource, requestOptions);
    }

    /**
     * Deletes a user.
     *
     * Deletes a User.
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     id: String (Required)
     *     status: String(NotStarted/Running/Succeeded/Failed/Canceled) (Required)
     *     error (Optional): {
     *         code: String (Required)
     *         message: String (Required)
     *         target: String (Optional)
     *         details (Optional): [
     *             (recursive schema, see above)
     *         ]
     *     }
     * }
     * }</pre>
     *
     * @param name The name of user.
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
     * Exports a user.
     *
     * Exports a User.
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     id: String (Required)
     *     status: String (Required)
     *     error (Optional): {
     *         code: String (Required)
     *         message: String (Required)
     *         target: String (Optional)
     *         details (Optional): [
     *             (recursive schema, see above)
     *         ]
     *     }
     * }
     * }</pre>
     *
     * @param name The name of user.
     * @param format The format of the data.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link PollerFlux} for polling of status details for long running operations.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginExport(String name, String format, RequestOptions requestOptions) {
        return this.serviceClient.beginExportAsync(name, format, requestOptions);
    }

    /**
     * Adds a user or replaces a user's fields.
     *
     * Creates or replaces a User.
     *
     * @param name The name of user.
     * @param resource The resource instance.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link PollerFlux} for polling of details about a user.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<PollOperationDetails, User> beginCreateOrReplace(String name, User resource) {
        // Generated convenience method for beginCreateOrReplaceWithModel
        RequestOptions requestOptions = new RequestOptions();
        return serviceClient.beginCreateOrReplaceWithModelAsync(name, BinaryData.fromObject(resource), requestOptions);
    }

    /**
     * Deletes a user.
     *
     * Deletes a User.
     *
     * @param name The name of user.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link PollerFlux} for polling of provides status details for long running operations.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<PollOperationDetails, Void> beginDelete(String name) {
        // Generated convenience method for beginDeleteWithModel
        RequestOptions requestOptions = new RequestOptions();
        return serviceClient.beginDeleteWithModelAsync(name, requestOptions);
    }

    /**
     * Exports a user.
     *
     * Exports a User.
     *
     * @param name The name of user.
     * @param format The format of the data.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link PollerFlux} for polling of status details for long running operations.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<PollOperationDetails, ExportedUser> beginExport(String name, String format) {
        // Generated convenience method for beginExportWithModel
        RequestOptions requestOptions = new RequestOptions();
        return serviceClient.beginExportWithModelAsync(name, format, requestOptions);
    }
}
