// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.
package com.azure.lro.rpc;

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
import com.azure.lro.rpc.implementation.RpcClientImpl;
import com.azure.lro.rpc.models.JobData;
import com.azure.lro.rpc.models.JobPollResult;
import com.azure.lro.rpc.models.JobResult;
import reactor.core.publisher.Mono;

/** Initializes a new instance of the asynchronous RpcClient type. */
@ServiceClient(builder = RpcClientBuilder.class, isAsync = true)
public final class RpcAsyncClient {

    @Generated private final RpcClientImpl serviceClient;

    /**
     * Initializes an instance of RpcAsyncClient class.
     *
     * @param serviceClient the service client implementation.
     */
    @Generated
    RpcAsyncClient(RpcClientImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Creates a Job.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     comment: String (Required)
     * }
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     jobId: String (Required)
     *     comment: String (Required)
     *     status: String(InProgress/Succeeded/Failed/Canceled) (Required)
     *     errors (Optional): [
     *          (Optional){
     *             error: ResponseError (Required)
     *         }
     *     ]
     *     results (Required): [
     *         String (Required)
     *     ]
     * }
     * }</pre>
     *
     * @param jobData Data of the job.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link PollerFlux} for polling of result of the job.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginCreateJob(BinaryData jobData, RequestOptions requestOptions) {
        return this.serviceClient.beginCreateJobAsync(jobData, requestOptions);
    }

    /**
     * Gets the status of a Job.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     jobId: String (Required)
     *     comment: String (Required)
     *     status: String(InProgress/Succeeded/Failed/Canceled) (Required)
     *     errors (Optional): [
     *          (Optional){
     *             error: ResponseError (Required)
     *         }
     *     ]
     *     results (Required): [
     *         String (Required)
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
     * @return the status of a Job along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getJobWithResponse(String jobId, RequestOptions requestOptions) {
        return this.serviceClient.getJobWithResponseAsync(jobId, requestOptions);
    }

    /**
     * Gets the status of a Job.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     operationId: String (Required)
     *     status: String(InProgress/Succeeded/Failed/Canceled) (Required)
     * }
     * }</pre>
     *
     * @param operationId Operation identifier.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the status of a Job along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getPollWithResponse(String operationId, RequestOptions requestOptions) {
        return this.serviceClient.getPollWithResponseAsync(operationId, requestOptions);
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
     * @return the {@link PollerFlux} for polling of result of the job.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<JobResult, JobResult> beginCreateJob(JobData jobData) {
        // Generated convenience method for beginCreateJobWithModel
        RequestOptions requestOptions = new RequestOptions();
        return serviceClient.beginCreateJobWithModelAsync(BinaryData.fromObject(jobData), requestOptions);
    }

    /**
     * Gets the status of a Job.
     *
     * @param jobId A processing job identifier.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the status of a Job on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<JobResult> getJob(String jobId) {
        // Generated convenience method for getJobWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return getJobWithResponse(jobId, requestOptions)
                .flatMap(FluxUtil::toMono)
                .map(protocolMethodData -> protocolMethodData.toObject(JobResult.class));
    }

    /**
     * Gets the status of a Job.
     *
     * @param operationId Operation identifier.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the status of a Job on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<JobPollResult> getPoll(String operationId) {
        // Generated convenience method for getPollWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return getPollWithResponse(operationId, requestOptions)
                .flatMap(FluxUtil::toMono)
                .map(protocolMethodData -> protocolMethodData.toObject(JobPollResult.class));
    }

    /**
     * Creates a Job.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     comment: String (Required)
     * }
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     jobId: String (Required)
     *     comment: String (Required)
     *     status: String(InProgress/Succeeded/Failed/Canceled) (Required)
     *     errors (Optional): [
     *          (Optional){
     *             error: ResponseError (Required)
     *         }
     *     ]
     *     results (Required): [
     *         String (Required)
     *     ]
     * }
     * }</pre>
     *
     * @param jobData Data of the job.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link PollerFlux} for polling of result of the job.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginCreateJobFinalOnLocation(
            BinaryData jobData, RequestOptions requestOptions) {
        return this.serviceClient.beginCreateJobFinalOnLocationAsync(jobData, requestOptions);
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
     * @return the {@link PollerFlux} for polling of result of the job.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<JobPollResult, JobResult> beginCreateJobFinalOnLocation(JobData jobData) {
        // Generated convenience method for beginCreateJobFinalOnLocationWithModel
        RequestOptions requestOptions = new RequestOptions();
        return serviceClient.beginCreateJobFinalOnLocationWithModelAsync(
                BinaryData.fromObject(jobData), requestOptions);
    }
}
