// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com._specs_.azure.core.lro.rpc;

import com._specs_.azure.core.lro.rpc.implementation.RpcClientImpl;
import com._specs_.azure.core.lro.rpc.implementation.models.LongRunningRpcRequest;
import com._specs_.azure.core.lro.rpc.models.GenerationResult;
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
import com.azure.core.util.polling.PollOperationDetails;
import com.azure.core.util.polling.SyncPoller;

/**
 * Initializes a new instance of the synchronous RpcClient type.
 */
@ServiceClient(builder = RpcClientBuilder.class)
public final class RpcClient {
    @Generated
    private final RpcClientImpl serviceClient;

    /**
     * Initializes an instance of RpcClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
    RpcClient(RpcClientImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Generate data.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     prompt: String (Required)
     * }
     * }</pre>
     * 
     * <p><strong>Response Body Schema</strong></p>
     * 
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
     *     result (Optional): {
     *         data: String (Required)
     *     }
     * }
     * }</pre>
     * 
     * @param generationOptions The generationOptions parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link SyncPoller} for polling of provides status details for long running operations.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginLongRunningRpc(BinaryData generationOptions,
        RequestOptions requestOptions) {
        return this.serviceClient.beginLongRunningRpc(generationOptions, requestOptions);
    }

    /**
     * Generate data.
     * 
     * @param prompt Prompt.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link SyncPoller} for polling of provides status details for long running operations.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<PollOperationDetails, GenerationResult> beginLongRunningRpc(String prompt) {
        // Generated convenience method for beginLongRunningRpcWithModel
        RequestOptions requestOptions = new RequestOptions();
        LongRunningRpcRequest generationOptionsObj = new LongRunningRpcRequest(prompt);
        BinaryData generationOptions = BinaryData.fromObject(generationOptionsObj);
        return serviceClient.beginLongRunningRpcWithModel(generationOptions, requestOptions);
    }
}
