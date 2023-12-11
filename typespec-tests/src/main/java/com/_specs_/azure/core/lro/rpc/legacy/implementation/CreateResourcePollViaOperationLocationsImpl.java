// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com._specs_.azure.core.lro.rpc.legacy.implementation;

import com._specs_.azure.core.lro.rpc.legacy.LegacyServiceVersion;
import com._specs_.azure.core.lro.rpc.legacy.models.JobResult;
import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.PathParam;
import com.azure.core.annotation.Post;
import com.azure.core.annotation.QueryParam;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.exception.ClientAuthenticationException;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.exception.ResourceModifiedException;
import com.azure.core.exception.ResourceNotFoundException;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.BinaryData;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import com.azure.core.util.polling.DefaultPollingStrategy;
import com.azure.core.util.polling.PollerFlux;
import com.azure.core.util.polling.PollingStrategyOptions;
import com.azure.core.util.polling.SyncDefaultPollingStrategy;
import com.azure.core.util.polling.SyncPoller;
import com.azure.core.util.serializer.TypeReference;
import java.time.Duration;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in CreateResourcePollViaOperationLocations.
 */
public final class CreateResourcePollViaOperationLocationsImpl {
    /**
     * The proxy service used to perform REST calls.
     */
    private final CreateResourcePollViaOperationLocationsService service;

    /**
     * The service client containing this operation class.
     */
    private final LegacyClientImpl client;

    /**
     * Initializes an instance of CreateResourcePollViaOperationLocationsImpl.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    CreateResourcePollViaOperationLocationsImpl(LegacyClientImpl client) {
        this.service = RestProxy.create(CreateResourcePollViaOperationLocationsService.class, client.getHttpPipeline(),
            client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * Gets Service version.
     * 
     * @return the serviceVersion value.
     */
    public LegacyServiceVersion getServiceVersion() {
        return client.getServiceVersion();
    }

    /**
     * The interface defining all the services for LegacyClientCreateResourcePollViaOperationLocations to be used by
     * the proxy service to perform REST calls.
     */
    @Host("http://localhost:3000")
    @ServiceInterface(name = "LegacyClientCreateRe")
    public interface CreateResourcePollViaOperationLocationsService {
        @Get("/azure/core/lro/rpc/legacy/create-resource-poll-via-operation-location/jobs/{jobId}")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<BinaryData>> getJob(@QueryParam("api-version") String apiVersion,
            @PathParam("jobId") String jobId, @HeaderParam("accept") String accept, RequestOptions requestOptions,
            Context context);

        @Get("/azure/core/lro/rpc/legacy/create-resource-poll-via-operation-location/jobs/{jobId}")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<BinaryData> getJobSync(@QueryParam("api-version") String apiVersion, @PathParam("jobId") String jobId,
            @HeaderParam("accept") String accept, RequestOptions requestOptions, Context context);

        @Post("/azure/core/lro/rpc/legacy/create-resource-poll-via-operation-location/jobs")
        @ExpectedResponses({ 202 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<BinaryData>> createJob(@QueryParam("api-version") String apiVersion,
            @HeaderParam("accept") String accept, @BodyParam("application/json") BinaryData jobData,
            RequestOptions requestOptions, Context context);

        @Post("/azure/core/lro/rpc/legacy/create-resource-poll-via-operation-location/jobs")
        @ExpectedResponses({ 202 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<BinaryData> createJobSync(@QueryParam("api-version") String apiVersion,
            @HeaderParam("accept") String accept, @BodyParam("application/json") BinaryData jobData,
            RequestOptions requestOptions, Context context);
    }

    /**
     * Poll a Job.
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     jobId: String (Required)
     *     comment: String (Required)
     *     status: String(notStarted/running/succeeded/failed/canceled/partiallyCompleted) (Required)
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
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getJobWithResponseAsync(String jobId, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getJob(this.client.getServiceVersion().getVersion(), jobId,
            accept, requestOptions, context));
    }

    /**
     * Poll a Job.
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     jobId: String (Required)
     *     comment: String (Required)
     *     status: String(notStarted/running/succeeded/failed/canceled/partiallyCompleted) (Required)
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
     * @return result of the job along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getJobWithResponse(String jobId, RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.getJobSync(this.client.getServiceVersion().getVersion(), jobId, accept, requestOptions,
            Context.NONE);
    }

    /**
     * Creates a Job.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     comment: String (Required)
     * }
     * }</pre>
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     jobId: String (Required)
     *     comment: String (Required)
     *     status: String(notStarted/running/succeeded/failed/canceled/partiallyCompleted) (Required)
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
     * @param jobData Data of the job.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return result of the job along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<BinaryData>> createJobWithResponseAsync(BinaryData jobData, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.createJob(this.client.getServiceVersion().getVersion(), accept,
            jobData, requestOptions, context));
    }

    /**
     * Creates a Job.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     comment: String (Required)
     * }
     * }</pre>
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     jobId: String (Required)
     *     comment: String (Required)
     *     status: String(notStarted/running/succeeded/failed/canceled/partiallyCompleted) (Required)
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
     * @param jobData Data of the job.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return result of the job along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Response<BinaryData> createJobWithResponse(BinaryData jobData, RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.createJobSync(this.client.getServiceVersion().getVersion(), accept, jobData, requestOptions,
            Context.NONE);
    }

    /**
     * Creates a Job.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     comment: String (Required)
     * }
     * }</pre>
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     jobId: String (Required)
     *     comment: String (Required)
     *     status: String(notStarted/running/succeeded/failed/canceled/partiallyCompleted) (Required)
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
     * @param jobData Data of the job.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link PollerFlux} for polling of result of the job.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginCreateJobAsync(BinaryData jobData, RequestOptions requestOptions) {
        return PollerFlux.create(
            Duration.ofSeconds(1), () -> this.createJobWithResponseAsync(jobData,
                requestOptions),
            new DefaultPollingStrategy<>(new PollingStrategyOptions(this.client.getHttpPipeline())

                .setContext(requestOptions != null && requestOptions.getContext() != null ? requestOptions.getContext()
                    : Context.NONE)
                .setServiceVersion(this.client.getServiceVersion().getVersion())),
            TypeReference.createInstance(BinaryData.class), TypeReference.createInstance(BinaryData.class));
    }

    /**
     * Creates a Job.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     comment: String (Required)
     * }
     * }</pre>
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     jobId: String (Required)
     *     comment: String (Required)
     *     status: String(notStarted/running/succeeded/failed/canceled/partiallyCompleted) (Required)
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
     * @param jobData Data of the job.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link SyncPoller} for polling of result of the job.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginCreateJob(BinaryData jobData, RequestOptions requestOptions) {
        return SyncPoller.createPoller(
            Duration.ofSeconds(1), () -> this.createJobWithResponse(jobData,
                requestOptions),
            new SyncDefaultPollingStrategy<>(new PollingStrategyOptions(this.client.getHttpPipeline())

                .setContext(requestOptions != null && requestOptions.getContext() != null ? requestOptions.getContext()
                    : Context.NONE)
                .setServiceVersion(this.client.getServiceVersion().getVersion())),
            TypeReference.createInstance(BinaryData.class), TypeReference.createInstance(BinaryData.class));
    }

    /**
     * Creates a Job.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     comment: String (Required)
     * }
     * }</pre>
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     jobId: String (Required)
     *     comment: String (Required)
     *     status: String(notStarted/running/succeeded/failed/canceled/partiallyCompleted) (Required)
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
     * @param jobData Data of the job.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link PollerFlux} for polling of result of the job.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<JobResult, JobResult> beginCreateJobWithModelAsync(BinaryData jobData,
        RequestOptions requestOptions) {
        return PollerFlux.create(
            Duration.ofSeconds(1), () -> this.createJobWithResponseAsync(jobData,
                requestOptions),
            new DefaultPollingStrategy<>(new PollingStrategyOptions(this.client.getHttpPipeline())

                .setContext(requestOptions != null && requestOptions.getContext() != null ? requestOptions.getContext()
                    : Context.NONE)
                .setServiceVersion(this.client.getServiceVersion().getVersion())),
            TypeReference.createInstance(JobResult.class), TypeReference.createInstance(JobResult.class));
    }

    /**
     * Creates a Job.
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     comment: String (Required)
     * }
     * }</pre>
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     jobId: String (Required)
     *     comment: String (Required)
     *     status: String(notStarted/running/succeeded/failed/canceled/partiallyCompleted) (Required)
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
     * @param jobData Data of the job.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link SyncPoller} for polling of result of the job.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<JobResult, JobResult> beginCreateJobWithModel(BinaryData jobData, RequestOptions requestOptions) {
        return SyncPoller.createPoller(
            Duration.ofSeconds(1), () -> this.createJobWithResponse(jobData,
                requestOptions),
            new SyncDefaultPollingStrategy<>(new PollingStrategyOptions(this.client.getHttpPipeline())

                .setContext(requestOptions != null && requestOptions.getContext() != null ? requestOptions.getContext()
                    : Context.NONE)
                .setServiceVersion(this.client.getServiceVersion().getVersion())),
            TypeReference.createInstance(JobResult.class), TypeReference.createInstance(JobResult.class));
    }
}
