package fixtures.lro.implementation;

import com.azure.core.annotation.Delete;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.Post;
import com.azure.core.annotation.Put;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.BinaryData;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import com.azure.core.util.polling.DefaultPollingStrategy;
import com.azure.core.util.polling.PollerFlux;
import com.azure.core.util.polling.SyncPoller;
import com.azure.core.util.serializer.TypeReference;
import java.time.Duration;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in LrosaDs. */
public final class LrosaDsImpl {
    /** The proxy service used to perform REST calls. */
    private final LrosaDsService service;

    /** The service client containing this operation class. */
    private final AutoRestLongRunningOperationTestServiceImpl client;

    /**
     * Initializes an instance of LrosaDsImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    LrosaDsImpl(AutoRestLongRunningOperationTestServiceImpl client) {
        this.service = RestProxy.create(LrosaDsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for AutoRestLongRunningOperationTestServiceLrosaDs to be used by the
     * proxy service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestLongRunningO")
    private interface LrosaDsService {
        @Put("/lro/nonretryerror/put/400")
        Mono<Response<BinaryData>> putNonRetry400(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Put("/lro/nonretryerror/put/201/creating/400")
        Mono<Response<BinaryData>> putNonRetry201Creating400(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Put("/lro/nonretryerror/put/201/creating/400/invalidjson")
        Mono<Response<BinaryData>> putNonRetry201Creating400InvalidJson(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Put("/lro/nonretryerror/putasync/retry/400")
        Mono<Response<BinaryData>> putAsyncRelativeRetry400(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Delete("/lro/nonretryerror/delete/400")
        Mono<Response<Void>> deleteNonRetry400(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Delete("/lro/nonretryerror/delete/202/retry/400")
        Mono<Response<Void>> delete202NonRetry400(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Delete("/lro/nonretryerror/deleteasync/retry/400")
        Mono<Response<Void>> deleteAsyncRelativeRetry400(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Post("/lro/nonretryerror/post/400")
        Mono<Response<Void>> postNonRetry400(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Post("/lro/nonretryerror/post/202/retry/400")
        Mono<Response<Void>> post202NonRetry400(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Post("/lro/nonretryerror/postasync/retry/400")
        Mono<Response<Void>> postAsyncRelativeRetry400(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Put("/lro/error/put/201/noprovisioningstatepayload")
        Mono<Response<BinaryData>> putError201NoProvisioningStatePayload(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Put("/lro/error/putasync/retry/nostatus")
        Mono<Response<BinaryData>> putAsyncRelativeRetryNoStatus(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Put("/lro/error/putasync/retry/nostatuspayload")
        Mono<Response<BinaryData>> putAsyncRelativeRetryNoStatusPayload(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Delete("/lro/error/delete/204/nolocation")
        Mono<Response<Void>> delete204Succeeded(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Delete("/lro/error/deleteasync/retry/nostatus")
        Mono<Response<Void>> deleteAsyncRelativeRetryNoStatus(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Post("/lro/error/post/202/nolocation")
        Mono<Response<Void>> post202NoLocation(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Post("/lro/error/postasync/retry/nopayload")
        Mono<Response<Void>> postAsyncRelativeRetryNoPayload(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Put("/lro/error/put/200/invalidjson")
        Mono<Response<BinaryData>> put200InvalidJson(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Put("/lro/error/putasync/retry/invalidheader")
        Mono<Response<BinaryData>> putAsyncRelativeRetryInvalidHeader(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Put("/lro/error/putasync/retry/invalidjsonpolling")
        Mono<Response<BinaryData>> putAsyncRelativeRetryInvalidJsonPolling(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Delete("/lro/error/delete/202/retry/invalidheader")
        Mono<Response<Void>> delete202RetryInvalidHeader(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Delete("/lro/error/deleteasync/retry/invalidheader")
        Mono<Response<Void>> deleteAsyncRelativeRetryInvalidHeader(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Delete("/lro/error/deleteasync/retry/invalidjsonpolling")
        Mono<Response<Void>> deleteAsyncRelativeRetryInvalidJsonPolling(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Post("/lro/error/post/202/retry/invalidheader")
        Mono<Response<Void>> post202RetryInvalidHeader(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Post("/lro/error/postasync/retry/invalidheader")
        Mono<Response<Void>> postAsyncRelativeRetryInvalidHeader(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Post("/lro/error/postasync/retry/invalidjsonpolling")
        Mono<Response<Void>> postAsyncRelativeRetryInvalidJsonPolling(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);
    }

    /**
     * Long running put request, service returns a 400 to the initial request.
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> putNonRetry400WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.putNonRetry400(this.client.getHost(), requestOptions, context));
    }

    /**
     * Long running put request, service returns a 400 to the initial request.
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> putNonRetry400WithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.putNonRetry400(this.client.getHost(), requestOptions, context);
    }

    /**
     * Long running put request, service returns a 400 to the initial request.
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginPutNonRetry400Async(RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.putNonRetry400WithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running put request, service returns a 400 to the initial request.
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginPutNonRetry400Async(RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.putNonRetry400WithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running put request, service returns a 400 to the initial request.
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginPutNonRetry400(RequestOptions requestOptions, Context context) {
        return this.beginPutNonRetry400Async(requestOptions, context).getSyncPoller();
    }

    /**
     * Long running put request, service returns a Product with 'ProvisioningState' = 'Creating' and 201 response code.
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> putNonRetry201Creating400WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.putNonRetry201Creating400(this.client.getHost(), requestOptions, context));
    }

    /**
     * Long running put request, service returns a Product with 'ProvisioningState' = 'Creating' and 201 response code.
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> putNonRetry201Creating400WithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.putNonRetry201Creating400(this.client.getHost(), requestOptions, context);
    }

    /**
     * Long running put request, service returns a Product with 'ProvisioningState' = 'Creating' and 201 response code.
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginPutNonRetry201Creating400Async(RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.putNonRetry201Creating400WithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running put request, service returns a Product with 'ProvisioningState' = 'Creating' and 201 response code.
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginPutNonRetry201Creating400Async(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.putNonRetry201Creating400WithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running put request, service returns a Product with 'ProvisioningState' = 'Creating' and 201 response code.
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginPutNonRetry201Creating400(
            RequestOptions requestOptions, Context context) {
        return this.beginPutNonRetry201Creating400Async(requestOptions, context).getSyncPoller();
    }

    /**
     * Long running put request, service returns a Product with 'ProvisioningState' = 'Creating' and 201 response code.
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> putNonRetry201Creating400InvalidJsonWithResponseAsync(
            RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context ->
                        service.putNonRetry201Creating400InvalidJson(this.client.getHost(), requestOptions, context));
    }

    /**
     * Long running put request, service returns a Product with 'ProvisioningState' = 'Creating' and 201 response code.
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> putNonRetry201Creating400InvalidJsonWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.putNonRetry201Creating400InvalidJson(this.client.getHost(), requestOptions, context);
    }

    /**
     * Long running put request, service returns a Product with 'ProvisioningState' = 'Creating' and 201 response code.
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginPutNonRetry201Creating400InvalidJsonAsync(
            RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.putNonRetry201Creating400InvalidJsonWithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running put request, service returns a Product with 'ProvisioningState' = 'Creating' and 201 response code.
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginPutNonRetry201Creating400InvalidJsonAsync(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.putNonRetry201Creating400InvalidJsonWithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running put request, service returns a Product with 'ProvisioningState' = 'Creating' and 201 response code.
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginPutNonRetry201Creating400InvalidJson(
            RequestOptions requestOptions, Context context) {
        return this.beginPutNonRetry201Creating400InvalidJsonAsync(requestOptions, context).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 200 with ProvisioningState=’Creating’. Poll the endpoint indicated in
     * the Azure-AsyncOperation header for operation status.
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> putAsyncRelativeRetry400WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.putAsyncRelativeRetry400(this.client.getHost(), requestOptions, context));
    }

    /**
     * Long running put request, service returns a 200 with ProvisioningState=’Creating’. Poll the endpoint indicated in
     * the Azure-AsyncOperation header for operation status.
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> putAsyncRelativeRetry400WithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.putAsyncRelativeRetry400(this.client.getHost(), requestOptions, context);
    }

    /**
     * Long running put request, service returns a 200 with ProvisioningState=’Creating’. Poll the endpoint indicated in
     * the Azure-AsyncOperation header for operation status.
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginPutAsyncRelativeRetry400Async(RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.putAsyncRelativeRetry400WithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running put request, service returns a 200 with ProvisioningState=’Creating’. Poll the endpoint indicated in
     * the Azure-AsyncOperation header for operation status.
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginPutAsyncRelativeRetry400Async(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.putAsyncRelativeRetry400WithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running put request, service returns a 200 with ProvisioningState=’Creating’. Poll the endpoint indicated in
     * the Azure-AsyncOperation header for operation status.
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginPutAsyncRelativeRetry400(
            RequestOptions requestOptions, Context context) {
        return this.beginPutAsyncRelativeRetry400Async(requestOptions, context).getSyncPoller();
    }

    /**
     * Long running delete request, service returns a 400 with an error body.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> deleteNonRetry400WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.deleteNonRetry400(this.client.getHost(), requestOptions, context));
    }

    /**
     * Long running delete request, service returns a 400 with an error body.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> deleteNonRetry400WithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.deleteNonRetry400(this.client.getHost(), requestOptions, context);
    }

    /**
     * Long running delete request, service returns a 400 with an error body.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginDeleteNonRetry400Async(RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.deleteNonRetry400WithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running delete request, service returns a 400 with an error body.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginDeleteNonRetry400Async(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.deleteNonRetry400WithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running delete request, service returns a 400 with an error body.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginDeleteNonRetry400(RequestOptions requestOptions, Context context) {
        return this.beginDeleteNonRetry400Async(requestOptions, context).getSyncPoller();
    }

    /**
     * Long running delete request, service returns a 202 with a location header.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> delete202NonRetry400WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.delete202NonRetry400(this.client.getHost(), requestOptions, context));
    }

    /**
     * Long running delete request, service returns a 202 with a location header.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> delete202NonRetry400WithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.delete202NonRetry400(this.client.getHost(), requestOptions, context);
    }

    /**
     * Long running delete request, service returns a 202 with a location header.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginDelete202NonRetry400Async(RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.delete202NonRetry400WithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running delete request, service returns a 202 with a location header.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginDelete202NonRetry400Async(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.delete202NonRetry400WithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running delete request, service returns a 202 with a location header.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginDelete202NonRetry400(
            RequestOptions requestOptions, Context context) {
        return this.beginDelete202NonRetry400Async(requestOptions, context).getSyncPoller();
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> deleteAsyncRelativeRetry400WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.deleteAsyncRelativeRetry400(this.client.getHost(), requestOptions, context));
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> deleteAsyncRelativeRetry400WithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.deleteAsyncRelativeRetry400(this.client.getHost(), requestOptions, context);
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginDeleteAsyncRelativeRetry400Async(RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.deleteAsyncRelativeRetry400WithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginDeleteAsyncRelativeRetry400Async(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.deleteAsyncRelativeRetry400WithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginDeleteAsyncRelativeRetry400(
            RequestOptions requestOptions, Context context) {
        return this.beginDeleteAsyncRelativeRetry400Async(requestOptions, context).getSyncPoller();
    }

    /**
     * Long running post request, service returns a 400 with no error body.
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> postNonRetry400WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.postNonRetry400(this.client.getHost(), requestOptions, context));
    }

    /**
     * Long running post request, service returns a 400 with no error body.
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> postNonRetry400WithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.postNonRetry400(this.client.getHost(), requestOptions, context);
    }

    /**
     * Long running post request, service returns a 400 with no error body.
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginPostNonRetry400Async(RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.postNonRetry400WithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running post request, service returns a 400 with no error body.
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginPostNonRetry400Async(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.postNonRetry400WithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running post request, service returns a 400 with no error body.
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginPostNonRetry400(RequestOptions requestOptions, Context context) {
        return this.beginPostNonRetry400Async(requestOptions, context).getSyncPoller();
    }

    /**
     * Long running post request, service returns a 202 with a location header.
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post202NonRetry400WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.post202NonRetry400(this.client.getHost(), requestOptions, context));
    }

    /**
     * Long running post request, service returns a 202 with a location header.
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post202NonRetry400WithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.post202NonRetry400(this.client.getHost(), requestOptions, context);
    }

    /**
     * Long running post request, service returns a 202 with a location header.
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginPost202NonRetry400Async(RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.post202NonRetry400WithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running post request, service returns a 202 with a location header.
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginPost202NonRetry400Async(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.post202NonRetry400WithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running post request, service returns a 202 with a location header.
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginPost202NonRetry400(RequestOptions requestOptions, Context context) {
        return this.beginPost202NonRetry400Async(requestOptions, context).getSyncPoller();
    }

    /**
     * Long running post request, service returns a 202 to the initial request Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> postAsyncRelativeRetry400WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.postAsyncRelativeRetry400(this.client.getHost(), requestOptions, context));
    }

    /**
     * Long running post request, service returns a 202 to the initial request Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> postAsyncRelativeRetry400WithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.postAsyncRelativeRetry400(this.client.getHost(), requestOptions, context);
    }

    /**
     * Long running post request, service returns a 202 to the initial request Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginPostAsyncRelativeRetry400Async(RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.postAsyncRelativeRetry400WithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running post request, service returns a 202 to the initial request Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginPostAsyncRelativeRetry400Async(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.postAsyncRelativeRetry400WithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running post request, service returns a 202 to the initial request Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginPostAsyncRelativeRetry400(
            RequestOptions requestOptions, Context context) {
        return this.beginPostAsyncRelativeRetry400Async(requestOptions, context).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 201 to the initial request with no payload.
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> putError201NoProvisioningStatePayloadWithResponseAsync(
            RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context ->
                        service.putError201NoProvisioningStatePayload(this.client.getHost(), requestOptions, context));
    }

    /**
     * Long running put request, service returns a 201 to the initial request with no payload.
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> putError201NoProvisioningStatePayloadWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.putError201NoProvisioningStatePayload(this.client.getHost(), requestOptions, context);
    }

    /**
     * Long running put request, service returns a 201 to the initial request with no payload.
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginPutError201NoProvisioningStatePayloadAsync(
            RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.putError201NoProvisioningStatePayloadWithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running put request, service returns a 201 to the initial request with no payload.
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginPutError201NoProvisioningStatePayloadAsync(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.putError201NoProvisioningStatePayloadWithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running put request, service returns a 201 to the initial request with no payload.
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginPutError201NoProvisioningStatePayload(
            RequestOptions requestOptions, Context context) {
        return this.beginPutError201NoProvisioningStatePayloadAsync(requestOptions, context).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> putAsyncRelativeRetryNoStatusWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.putAsyncRelativeRetryNoStatus(this.client.getHost(), requestOptions, context));
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> putAsyncRelativeRetryNoStatusWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.putAsyncRelativeRetryNoStatus(this.client.getHost(), requestOptions, context);
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginPutAsyncRelativeRetryNoStatusAsync(RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.putAsyncRelativeRetryNoStatusWithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginPutAsyncRelativeRetryNoStatusAsync(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.putAsyncRelativeRetryNoStatusWithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginPutAsyncRelativeRetryNoStatus(
            RequestOptions requestOptions, Context context) {
        return this.beginPutAsyncRelativeRetryNoStatusAsync(requestOptions, context).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> putAsyncRelativeRetryNoStatusPayloadWithResponseAsync(
            RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context ->
                        service.putAsyncRelativeRetryNoStatusPayload(this.client.getHost(), requestOptions, context));
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> putAsyncRelativeRetryNoStatusPayloadWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.putAsyncRelativeRetryNoStatusPayload(this.client.getHost(), requestOptions, context);
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginPutAsyncRelativeRetryNoStatusPayloadAsync(
            RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.putAsyncRelativeRetryNoStatusPayloadWithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginPutAsyncRelativeRetryNoStatusPayloadAsync(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.putAsyncRelativeRetryNoStatusPayloadWithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginPutAsyncRelativeRetryNoStatusPayload(
            RequestOptions requestOptions, Context context) {
        return this.beginPutAsyncRelativeRetryNoStatusPayloadAsync(requestOptions, context).getSyncPoller();
    }

    /**
     * Long running delete request, service returns a 204 to the initial request, indicating success.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> delete204SucceededWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.delete204Succeeded(this.client.getHost(), requestOptions, context));
    }

    /**
     * Long running delete request, service returns a 204 to the initial request, indicating success.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> delete204SucceededWithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.delete204Succeeded(this.client.getHost(), requestOptions, context);
    }

    /**
     * Long running delete request, service returns a 204 to the initial request, indicating success.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginDelete204SucceededAsync(RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.delete204SucceededWithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running delete request, service returns a 204 to the initial request, indicating success.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginDelete204SucceededAsync(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.delete204SucceededWithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running delete request, service returns a 204 to the initial request, indicating success.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginDelete204Succeeded(RequestOptions requestOptions, Context context) {
        return this.beginDelete204SucceededAsync(requestOptions, context).getSyncPoller();
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> deleteAsyncRelativeRetryNoStatusWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.deleteAsyncRelativeRetryNoStatus(this.client.getHost(), requestOptions, context));
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> deleteAsyncRelativeRetryNoStatusWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.deleteAsyncRelativeRetryNoStatus(this.client.getHost(), requestOptions, context);
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginDeleteAsyncRelativeRetryNoStatusAsync(
            RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.deleteAsyncRelativeRetryNoStatusWithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginDeleteAsyncRelativeRetryNoStatusAsync(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.deleteAsyncRelativeRetryNoStatusWithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginDeleteAsyncRelativeRetryNoStatus(
            RequestOptions requestOptions, Context context) {
        return this.beginDeleteAsyncRelativeRetryNoStatusAsync(requestOptions, context).getSyncPoller();
    }

    /**
     * Long running post request, service returns a 202 to the initial request, without a location header.
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post202NoLocationWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.post202NoLocation(this.client.getHost(), requestOptions, context));
    }

    /**
     * Long running post request, service returns a 202 to the initial request, without a location header.
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post202NoLocationWithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.post202NoLocation(this.client.getHost(), requestOptions, context);
    }

    /**
     * Long running post request, service returns a 202 to the initial request, without a location header.
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginPost202NoLocationAsync(RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.post202NoLocationWithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running post request, service returns a 202 to the initial request, without a location header.
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginPost202NoLocationAsync(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.post202NoLocationWithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running post request, service returns a 202 to the initial request, without a location header.
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginPost202NoLocation(RequestOptions requestOptions, Context context) {
        return this.beginPost202NoLocationAsync(requestOptions, context).getSyncPoller();
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> postAsyncRelativeRetryNoPayloadWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.postAsyncRelativeRetryNoPayload(this.client.getHost(), requestOptions, context));
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> postAsyncRelativeRetryNoPayloadWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.postAsyncRelativeRetryNoPayload(this.client.getHost(), requestOptions, context);
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginPostAsyncRelativeRetryNoPayloadAsync(RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.postAsyncRelativeRetryNoPayloadWithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginPostAsyncRelativeRetryNoPayloadAsync(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.postAsyncRelativeRetryNoPayloadWithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginPostAsyncRelativeRetryNoPayload(
            RequestOptions requestOptions, Context context) {
        return this.beginPostAsyncRelativeRetryNoPayloadAsync(requestOptions, context).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that is not a valid json.
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> put200InvalidJsonWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.put200InvalidJson(this.client.getHost(), requestOptions, context));
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that is not a valid json.
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> put200InvalidJsonWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.put200InvalidJson(this.client.getHost(), requestOptions, context);
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that is not a valid json.
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginPut200InvalidJsonAsync(RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.put200InvalidJsonWithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that is not a valid json.
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginPut200InvalidJsonAsync(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.put200InvalidJsonWithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that is not a valid json.
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginPut200InvalidJson(RequestOptions requestOptions, Context context) {
        return this.beginPut200InvalidJsonAsync(requestOptions, context).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. The endpoint indicated in the Azure-AsyncOperation header is invalid.
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> putAsyncRelativeRetryInvalidHeaderWithResponseAsync(
            RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.putAsyncRelativeRetryInvalidHeader(this.client.getHost(), requestOptions, context));
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. The endpoint indicated in the Azure-AsyncOperation header is invalid.
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> putAsyncRelativeRetryInvalidHeaderWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.putAsyncRelativeRetryInvalidHeader(this.client.getHost(), requestOptions, context);
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. The endpoint indicated in the Azure-AsyncOperation header is invalid.
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginPutAsyncRelativeRetryInvalidHeaderAsync(
            RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.putAsyncRelativeRetryInvalidHeaderWithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. The endpoint indicated in the Azure-AsyncOperation header is invalid.
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginPutAsyncRelativeRetryInvalidHeaderAsync(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.putAsyncRelativeRetryInvalidHeaderWithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. The endpoint indicated in the Azure-AsyncOperation header is invalid.
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginPutAsyncRelativeRetryInvalidHeader(
            RequestOptions requestOptions, Context context) {
        return this.beginPutAsyncRelativeRetryInvalidHeaderAsync(requestOptions, context).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> putAsyncRelativeRetryInvalidJsonPollingWithResponseAsync(
            RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context ->
                        service.putAsyncRelativeRetryInvalidJsonPolling(
                                this.client.getHost(), requestOptions, context));
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> putAsyncRelativeRetryInvalidJsonPollingWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.putAsyncRelativeRetryInvalidJsonPolling(this.client.getHost(), requestOptions, context);
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginPutAsyncRelativeRetryInvalidJsonPollingAsync(
            RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.putAsyncRelativeRetryInvalidJsonPollingWithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginPutAsyncRelativeRetryInvalidJsonPollingAsync(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.putAsyncRelativeRetryInvalidJsonPollingWithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginPutAsyncRelativeRetryInvalidJsonPolling(
            RequestOptions requestOptions, Context context) {
        return this.beginPutAsyncRelativeRetryInvalidJsonPollingAsync(requestOptions, context).getSyncPoller();
    }

    /**
     * Long running delete request, service returns a 202 to the initial request receing a reponse with an invalid
     * 'Location' and 'Retry-After' headers.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> delete202RetryInvalidHeaderWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.delete202RetryInvalidHeader(this.client.getHost(), requestOptions, context));
    }

    /**
     * Long running delete request, service returns a 202 to the initial request receing a reponse with an invalid
     * 'Location' and 'Retry-After' headers.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> delete202RetryInvalidHeaderWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.delete202RetryInvalidHeader(this.client.getHost(), requestOptions, context);
    }

    /**
     * Long running delete request, service returns a 202 to the initial request receing a reponse with an invalid
     * 'Location' and 'Retry-After' headers.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginDelete202RetryInvalidHeaderAsync(RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.delete202RetryInvalidHeaderWithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running delete request, service returns a 202 to the initial request receing a reponse with an invalid
     * 'Location' and 'Retry-After' headers.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginDelete202RetryInvalidHeaderAsync(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.delete202RetryInvalidHeaderWithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running delete request, service returns a 202 to the initial request receing a reponse with an invalid
     * 'Location' and 'Retry-After' headers.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginDelete202RetryInvalidHeader(
            RequestOptions requestOptions, Context context) {
        return this.beginDelete202RetryInvalidHeaderAsync(requestOptions, context).getSyncPoller();
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. The endpoint indicated in the
     * Azure-AsyncOperation header is invalid.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> deleteAsyncRelativeRetryInvalidHeaderWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context ->
                        service.deleteAsyncRelativeRetryInvalidHeader(this.client.getHost(), requestOptions, context));
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. The endpoint indicated in the
     * Azure-AsyncOperation header is invalid.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> deleteAsyncRelativeRetryInvalidHeaderWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.deleteAsyncRelativeRetryInvalidHeader(this.client.getHost(), requestOptions, context);
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. The endpoint indicated in the
     * Azure-AsyncOperation header is invalid.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginDeleteAsyncRelativeRetryInvalidHeaderAsync(
            RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.deleteAsyncRelativeRetryInvalidHeaderWithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. The endpoint indicated in the
     * Azure-AsyncOperation header is invalid.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginDeleteAsyncRelativeRetryInvalidHeaderAsync(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.deleteAsyncRelativeRetryInvalidHeaderWithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. The endpoint indicated in the
     * Azure-AsyncOperation header is invalid.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginDeleteAsyncRelativeRetryInvalidHeader(
            RequestOptions requestOptions, Context context) {
        return this.beginDeleteAsyncRelativeRetryInvalidHeaderAsync(requestOptions, context).getSyncPoller();
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> deleteAsyncRelativeRetryInvalidJsonPollingWithResponseAsync(
            RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context ->
                        service.deleteAsyncRelativeRetryInvalidJsonPolling(
                                this.client.getHost(), requestOptions, context));
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> deleteAsyncRelativeRetryInvalidJsonPollingWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.deleteAsyncRelativeRetryInvalidJsonPolling(this.client.getHost(), requestOptions, context);
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginDeleteAsyncRelativeRetryInvalidJsonPollingAsync(
            RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.deleteAsyncRelativeRetryInvalidJsonPollingWithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginDeleteAsyncRelativeRetryInvalidJsonPollingAsync(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.deleteAsyncRelativeRetryInvalidJsonPollingWithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginDeleteAsyncRelativeRetryInvalidJsonPolling(
            RequestOptions requestOptions, Context context) {
        return this.beginDeleteAsyncRelativeRetryInvalidJsonPollingAsync(requestOptions, context).getSyncPoller();
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with invalid 'Location' and
     * 'Retry-After' headers.
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post202RetryInvalidHeaderWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.post202RetryInvalidHeader(this.client.getHost(), requestOptions, context));
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with invalid 'Location' and
     * 'Retry-After' headers.
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post202RetryInvalidHeaderWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.post202RetryInvalidHeader(this.client.getHost(), requestOptions, context);
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with invalid 'Location' and
     * 'Retry-After' headers.
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginPost202RetryInvalidHeaderAsync(RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.post202RetryInvalidHeaderWithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with invalid 'Location' and
     * 'Retry-After' headers.
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginPost202RetryInvalidHeaderAsync(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.post202RetryInvalidHeaderWithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with invalid 'Location' and
     * 'Retry-After' headers.
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginPost202RetryInvalidHeader(
            RequestOptions requestOptions, Context context) {
        return this.beginPost202RetryInvalidHeaderAsync(requestOptions, context).getSyncPoller();
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. The endpoint indicated in the Azure-AsyncOperation header is invalid.
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> postAsyncRelativeRetryInvalidHeaderWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.postAsyncRelativeRetryInvalidHeader(this.client.getHost(), requestOptions, context));
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. The endpoint indicated in the Azure-AsyncOperation header is invalid.
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> postAsyncRelativeRetryInvalidHeaderWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.postAsyncRelativeRetryInvalidHeader(this.client.getHost(), requestOptions, context);
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. The endpoint indicated in the Azure-AsyncOperation header is invalid.
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginPostAsyncRelativeRetryInvalidHeaderAsync(
            RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.postAsyncRelativeRetryInvalidHeaderWithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. The endpoint indicated in the Azure-AsyncOperation header is invalid.
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginPostAsyncRelativeRetryInvalidHeaderAsync(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.postAsyncRelativeRetryInvalidHeaderWithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. The endpoint indicated in the Azure-AsyncOperation header is invalid.
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginPostAsyncRelativeRetryInvalidHeader(
            RequestOptions requestOptions, Context context) {
        return this.beginPostAsyncRelativeRetryInvalidHeaderAsync(requestOptions, context).getSyncPoller();
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> postAsyncRelativeRetryInvalidJsonPollingWithResponseAsync(
            RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context ->
                        service.postAsyncRelativeRetryInvalidJsonPolling(
                                this.client.getHost(), requestOptions, context));
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> postAsyncRelativeRetryInvalidJsonPollingWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.postAsyncRelativeRetryInvalidJsonPolling(this.client.getHost(), requestOptions, context);
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginPostAsyncRelativeRetryInvalidJsonPollingAsync(
            RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.postAsyncRelativeRetryInvalidJsonPollingWithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginPostAsyncRelativeRetryInvalidJsonPollingAsync(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.postAsyncRelativeRetryInvalidJsonPollingWithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginPostAsyncRelativeRetryInvalidJsonPolling(
            RequestOptions requestOptions, Context context) {
        return this.beginPostAsyncRelativeRetryInvalidJsonPollingAsync(requestOptions, context).getSyncPoller();
    }

    private static final class TypeReferenceBinaryData extends TypeReference<BinaryData> {}
}
