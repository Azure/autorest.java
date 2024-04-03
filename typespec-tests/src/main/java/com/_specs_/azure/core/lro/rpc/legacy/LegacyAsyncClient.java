// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com._specs_.azure.core.lro.rpc.legacy;

import com._specs_.azure.core.lro.rpc.legacy.implementation.CreateResourcePollViaOperationLocationsImpl;
import com._specs_.azure.core.lro.rpc.legacy.models.JobData;
import com._specs_.azure.core.lro.rpc.legacy.models.JobResult;
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
import com.azure.core.util.FluxUtil;
import com.azure.core.util.polling.PollerFlux;
import reactor.core.publisher.Mono;

/**
 * Initializes a new instance of the asynchronous LegacyClient type.
 */
@ServiceClient(builder = LegacyClientBuilder.class, isAsync = true)
public final class LegacyAsyncClient {
    @Generated
    private final CreateResourcePollViaOperationLocationsImpl serviceClient;

    /**
     * Initializes an instance of LegacyAsyncClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
    LegacyAsyncClient(CreateResourcePollViaOperationLocationsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Poll a Job.
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     *     jobId: String (Required)
     *     comment: String (Required)
     *     status: String(notStarted/running/Succeeded/Failed/canceled/partiallyCompleted) (Required)
     *     errors (Optional): [
     *          (Optional){
     *             error (Required): {
     *                 code: String (Required)
     *                 message: String (Required)
     *                 target: String (Optional)
     *                 details (Optional): [
     *                     (recursive schema, see above)
     *                 ]
     *                 innererror (Optional): {
     *                     code: String (Optional)
     *                     innererror (Optional): (recursive schema, see innererror above)
     *                 }
     *             }
     *         }
     *     ]
     *     results (Optional): [
     *         String (Optional)
     *     ]
     * }
     * }</pre>
     * 
     * @param jobId A processing job identifier.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return result of the job along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getJobWithResponse(String jobId, RequestOptions requestOptions) {
        return this.serviceClient.getJobWithResponseAsync(jobId, requestOptions);
    }

    /**
     * Creates a Job.
     * <p><strong>Request Body Schema</strong></p>
     * <pre>{@code
     * {
     *     comment: String (Required)
     * }
     * }</pre>
     * 
     * @param jobData Data of the job.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link PollerFlux} for polling of long-running operation.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginCreateJob(BinaryData jobData, RequestOptions requestOptions) {
        return this.serviceClient.beginCreateJobAsync(jobData, requestOptions);
    }

    /**
     * Poll a Job.
     * 
     * @param jobId A processing job identifier.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return result of the job on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<JobResult> getJob(String jobId) {
        // Generated convenience method for getJobWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return getJobWithResponse(jobId, requestOptions).flatMap(FluxUtil::toMono)
            .map(protocolMethodData -> protocolMethodData.toObject(JobResult.class));
    }

    /**
     * Creates a Job.
     * 
     * @param jobData Data of the job.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link PollerFlux} for polling of long-running operation.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<JobResult, Void> beginCreateJob(JobData jobData) {
        // Generated convenience method for beginCreateJobWithModel
        RequestOptions requestOptions = new RequestOptions();
        return serviceClient.beginCreateJobWithModelAsync(BinaryData.fromObject(jobData), requestOptions);
    }
}
