// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.lro;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import com.azure.core.util.polling.SyncPoller;
import fixtures.lro.implementation.LrosCustomHeadersImpl;
import reactor.core.publisher.Mono;

/** Initializes a new instance of the synchronous AutoRestLongRunningOperationTestServiceClient type. */
@ServiceClient(builder = AutoRestLongRunningOperationTestServiceClientBuilder.class)
public final class LrosCustomHeaderClient {
    @Generated private final LrosCustomHeadersImpl serviceClient;

    /**
     * Initializes an instance of LrosCustomHeaders client.
     *
     * @param serviceClient the service client implementation.
     */
    @Generated
    LrosCustomHeaderClient(LrosCustomHeadersImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * x-ms-client-request-id = 9C4D50EE-2D56-4CD3-8152-34347DC9F2B0 is required message header for all requests. Long
     * running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: String
     *     type: String
     *     tags: {
     *         String: String
     *     }
     *     location: String
     *     name: String
     *     properties: {
     *         provisioningState: String
     *         provisioningStateValues: String(Succeeded/Failed/canceled/Accepted/Creating/Created/Updating/Updated/Deleting/Deleted/OK)
     *     }
     * }
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: String
     *     type: String
     *     tags: {
     *         String: String
     *     }
     *     location: String
     *     name: String
     *     properties: {
     *         provisioningState: String
     *         provisioningStateValues: String(Succeeded/Failed/canceled/Accepted/Creating/Created/Updating/Updated/Deleting/Deleted/OK)
     *     }
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginPutAsyncRetrySucceeded(RequestOptions requestOptions) {
        return this.serviceClient.beginPutAsyncRetrySucceeded(requestOptions);
    }

    /**
     * x-ms-client-request-id = 9C4D50EE-2D56-4CD3-8152-34347DC9F2B0 is required message header for all requests. Long
     * running put request, service returns a 201 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Polls return this value until the last poll returns a ‘200’ with
     * ProvisioningState=’Succeeded’.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: String
     *     type: String
     *     tags: {
     *         String: String
     *     }
     *     location: String
     *     name: String
     *     properties: {
     *         provisioningState: String
     *         provisioningStateValues: String(Succeeded/Failed/canceled/Accepted/Creating/Created/Updating/Updated/Deleting/Deleted/OK)
     *     }
     * }
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: String
     *     type: String
     *     tags: {
     *         String: String
     *     }
     *     location: String
     *     name: String
     *     properties: {
     *         provisioningState: String
     *         provisioningStateValues: String(Succeeded/Failed/canceled/Accepted/Creating/Created/Updating/Updated/Deleting/Deleted/OK)
     *     }
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginPut201CreatingSucceeded200(RequestOptions requestOptions) {
        return this.serviceClient.beginPut201CreatingSucceeded200(requestOptions);
    }

    /**
     * x-ms-client-request-id = 9C4D50EE-2D56-4CD3-8152-34347DC9F2B0 is required message header for all requests. Long
     * running post request, service returns a 202 to the initial request, with 'Location' and 'Retry-After' headers,
     * Polls return a 200 with a response body after success.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: String
     *     type: String
     *     tags: {
     *         String: String
     *     }
     *     location: String
     *     name: String
     *     properties: {
     *         provisioningState: String
     *         provisioningStateValues: String(Succeeded/Failed/canceled/Accepted/Creating/Created/Updating/Updated/Deleting/Deleted/OK)
     *     }
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginPost202Retry200(RequestOptions requestOptions) {
        return this.serviceClient.beginPost202Retry200(requestOptions);
    }

    /**
     * x-ms-client-request-id = 9C4D50EE-2D56-4CD3-8152-34347DC9F2B0 is required message header for all requests. Long
     * running post request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: String
     *     type: String
     *     tags: {
     *         String: String
     *     }
     *     location: String
     *     name: String
     *     properties: {
     *         provisioningState: String
     *         provisioningStateValues: String(Succeeded/Failed/canceled/Accepted/Creating/Created/Updating/Updated/Deleting/Deleted/OK)
     *     }
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginPostAsyncRetrySucceeded(RequestOptions requestOptions) {
        return this.serviceClient.beginPostAsyncRetrySucceeded(requestOptions);
    }
}
