// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.requiredoptionalbody;

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
import fixtures.requiredoptionalbody.implementation.RequiredOptionalBodyClientImpl;
import reactor.core.publisher.Mono;

/**
 * Initializes a new instance of the asynchronous RequiredOptionalBodyClient type.
 */
@ServiceClient(builder = RequiredOptionalBodyClientBuilder.class, isAsync = true)
public final class RequiredOptionalBodyAsyncClient {
    @Generated
    private final RequiredOptionalBodyClientImpl serviceClient;

    /**
     * Initializes an instance of RequiredOptionalBodyAsyncClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
    RequiredOptionalBodyAsyncClient(RequiredOptionalBodyClientImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Creates or updates a deployment.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>
     * <code>
     * {
     *     deploymentId: String (Required)
     *     startDateTime: OffsetDateTime (Required)
     *     groupId: String (Required)
     *     deviceClassSubgroups (Optional): [
     *         String (Optional)
     *     ]
     *     isCanceled: Boolean (Optional)
     *     isRetried: Boolean (Optional)
     *     OperationFilterStatus: String(Running/NotStarted) (Required)
     *     tags (Optional): {
     *         String: String (Required)
     *     }
     * }
     * </code>
     * </pre>
     * 
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>
     * <code>
     * {
     *     deploymentId: String (Required)
     *     startDateTime: OffsetDateTime (Required)
     *     groupId: String (Required)
     *     deviceClassSubgroups (Optional): [
     *         String (Optional)
     *     ]
     *     isCanceled: Boolean (Optional)
     *     isRetried: Boolean (Optional)
     *     OperationFilterStatus: String(Running/NotStarted) (Required)
     *     tags (Optional): {
     *         String: String (Required)
     *     }
     * }
     * </code>
     * </pre>
     * 
     * @param deployment The deployment properties.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return deployment metadata along with {@link Response} on successful completion of {@link Mono}.
     * @see <a href=https://docs.microsoft.com/azure/developer/java/sdk/overview>Rest API Documentation</a>
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> createOrUpdateDeploymentWithResponse(BinaryData deployment,
        RequestOptions requestOptions) {
        return this.serviceClient.createOrUpdateDeploymentWithResponseAsync(deployment, requestOptions);
    }

    /**
     * optional object.
     * <p><strong>Header Parameters</strong></p>
     * <table border="1">
     * <caption>Header Parameters</caption>
     * <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     * <tr><td>Content-Type</td><td>String</td><td>No</td><td>The content type. Allowed values:
     * "application/json".</td></tr>
     * </table>
     * You can add these to a request with {@link RequestOptions#addHeader}
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>
     * <code>
     * {
     *     deploymentId: String (Required)
     *     startDateTime: OffsetDateTime (Required)
     *     groupId: String (Required)
     *     deviceClassSubgroups (Optional): [
     *         String (Optional)
     *     ]
     *     isCanceled: Boolean (Optional)
     *     isRetried: Boolean (Optional)
     *     OperationFilterStatus: String(Running/NotStarted) (Required)
     *     tags (Optional): {
     *         String: String (Required)
     *     }
     * }
     * </code>
     * </pre>
     * 
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>
     * <code>
     * {
     *     deploymentId: String (Required)
     *     startDateTime: OffsetDateTime (Required)
     *     groupId: String (Required)
     *     deviceClassSubgroups (Optional): [
     *         String (Optional)
     *     ]
     *     isCanceled: Boolean (Optional)
     *     isRetried: Boolean (Optional)
     *     OperationFilterStatus: String(Running/NotStarted) (Required)
     *     tags (Optional): {
     *         String: String (Required)
     *     }
     * }
     * </code>
     * </pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return deployment metadata along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> optionalObjectWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.optionalObjectWithResponseAsync(requestOptions);
    }
}
