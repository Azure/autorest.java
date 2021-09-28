package fixtures.lro.implementation;

import com.azure.core.annotation.Delete;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.Patch;
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

/** An instance of this class provides access to all the operations defined in LROs. */
public final class LROsImpl {
    /** The proxy service used to perform REST calls. */
    private final LROsService service;

    /** The service client containing this operation class. */
    private final AutoRestLongRunningOperationTestServiceImpl client;

    /**
     * Initializes an instance of LROsImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    LROsImpl(AutoRestLongRunningOperationTestServiceImpl client) {
        this.service = RestProxy.create(LROsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for AutoRestLongRunningOperationTestServiceLROs to be used by the proxy
     * service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestLongRunningO")
    private interface LROsService {
        @Put("/lro/put/200/succeeded")
        Mono<Response<BinaryData>> put200Succeeded(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Patch("/lro/patch/200/succeeded/ignoreheaders")
        Mono<Response<BinaryData>> patch200SucceededIgnoreHeaders(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Put("/lro/put/201/succeeded")
        Mono<Response<BinaryData>> put201Succeeded(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Post("/lro/list")
        Mono<Response<BinaryData>> post202List(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Put("/lro/put/200/succeeded/nostate")
        Mono<Response<BinaryData>> put200SucceededNoState(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Put("/lro/put/202/retry/200")
        Mono<Response<BinaryData>> put202Retry200(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Put("/lro/put/201/creating/succeeded/200")
        Mono<Response<BinaryData>> put201CreatingSucceeded200(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Put("/lro/put/200/updating/succeeded/200")
        Mono<Response<BinaryData>> put200UpdatingSucceeded204(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Put("/lro/put/201/created/failed/200")
        Mono<Response<BinaryData>> put201CreatingFailed200(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Put("/lro/put/200/accepted/canceled/200")
        Mono<Response<BinaryData>> put200Acceptedcanceled200(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Put("/lro/put/noheader/202/200")
        Mono<Response<BinaryData>> putNoHeaderInRetry(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Put("/lro/putasync/retry/succeeded")
        Mono<Response<BinaryData>> putAsyncRetrySucceeded(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Put("/lro/putasync/noretry/succeeded")
        Mono<Response<BinaryData>> putAsyncNoRetrySucceeded(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Put("/lro/putasync/retry/failed")
        Mono<Response<BinaryData>> putAsyncRetryFailed(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Put("/lro/putasync/noretry/canceled")
        Mono<Response<BinaryData>> putAsyncNoRetrycanceled(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Put("/lro/putasync/noheader/201/200")
        Mono<Response<BinaryData>> putAsyncNoHeaderInRetry(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Put("/lro/putnonresource/202/200")
        Mono<Response<BinaryData>> putNonResource(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Put("/lro/putnonresourceasync/202/200")
        Mono<Response<BinaryData>> putAsyncNonResource(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Put("/lro/putsubresource/202/200")
        Mono<Response<BinaryData>> putSubResource(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Put("/lro/putsubresourceasync/202/200")
        Mono<Response<BinaryData>> putAsyncSubResource(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Delete("/lro/delete/provisioning/202/accepted/200/succeeded")
        Mono<Response<BinaryData>> deleteProvisioning202Accepted200Succeeded(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Delete("/lro/delete/provisioning/202/deleting/200/failed")
        Mono<Response<BinaryData>> deleteProvisioning202DeletingFailed200(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Delete("/lro/delete/provisioning/202/deleting/200/canceled")
        Mono<Response<BinaryData>> deleteProvisioning202Deletingcanceled200(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Delete("/lro/delete/204/succeeded")
        Mono<Response<Void>> delete204Succeeded(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Delete("/lro/delete/202/retry/200")
        Mono<Response<BinaryData>> delete202Retry200(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Delete("/lro/delete/202/noretry/204")
        Mono<Response<BinaryData>> delete202NoRetry204(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Delete("/lro/delete/noheader")
        Mono<Response<Void>> deleteNoHeaderInRetry(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Delete("/lro/deleteasync/noheader/202/204")
        Mono<Response<Void>> deleteAsyncNoHeaderInRetry(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Delete("/lro/deleteasync/retry/succeeded")
        Mono<Response<Void>> deleteAsyncRetrySucceeded(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Delete("/lro/deleteasync/noretry/succeeded")
        Mono<Response<Void>> deleteAsyncNoRetrySucceeded(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Delete("/lro/deleteasync/retry/failed")
        Mono<Response<Void>> deleteAsyncRetryFailed(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Delete("/lro/deleteasync/retry/canceled")
        Mono<Response<Void>> deleteAsyncRetrycanceled(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Post("/lro/post/payload/200")
        Mono<Response<BinaryData>> post200WithPayload(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Post("/lro/post/202/retry/200")
        Mono<Response<Void>> post202Retry200(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Post("/lro/post/202/noretry/204")
        Mono<Response<BinaryData>> post202NoRetry204(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Post("/lro/LROPostDoubleHeadersFinalLocationGet")
        Mono<Response<BinaryData>> postDoubleHeadersFinalLocationGet(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Post("/lro/LROPostDoubleHeadersFinalAzureHeaderGet")
        Mono<Response<BinaryData>> postDoubleHeadersFinalAzureHeaderGet(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Post("/lro/LROPostDoubleHeadersFinalAzureHeaderGetDefault")
        Mono<Response<BinaryData>> postDoubleHeadersFinalAzureHeaderGetDefault(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Post("/lro/postasync/retry/succeeded")
        Mono<Response<BinaryData>> postAsyncRetrySucceeded(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Post("/lro/postasync/noretry/succeeded")
        Mono<Response<BinaryData>> postAsyncNoRetrySucceeded(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Post("/lro/postasync/retry/failed")
        Mono<Response<Void>> postAsyncRetryFailed(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Post("/lro/postasync/retry/canceled")
        Mono<Response<Void>> postAsyncRetrycanceled(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> put200SucceededWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.put200Succeeded(this.client.getHost(), requestOptions, context));
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> put200SucceededWithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.put200Succeeded(this.client.getHost(), requestOptions, context);
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    public PollerFlux<BinaryData, BinaryData> beginPut200SucceededAsync(RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.put200SucceededWithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    public PollerFlux<BinaryData, BinaryData> beginPut200SucceededAsync(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.put200SucceededWithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    public SyncPoller<BinaryData, BinaryData> beginPut200Succeeded(RequestOptions requestOptions, Context context) {
        return this.beginPut200SucceededAsync(requestOptions, context).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 200 to the initial request with location header. We should not have
     * any subsequent calls after receiving this first response.
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
    public Mono<Response<BinaryData>> patch200SucceededIgnoreHeadersWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.patch200SucceededIgnoreHeaders(this.client.getHost(), requestOptions, context));
    }

    /**
     * Long running put request, service returns a 200 to the initial request with location header. We should not have
     * any subsequent calls after receiving this first response.
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
    public Mono<Response<BinaryData>> patch200SucceededIgnoreHeadersWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.patch200SucceededIgnoreHeaders(this.client.getHost(), requestOptions, context);
    }

    /**
     * Long running put request, service returns a 200 to the initial request with location header. We should not have
     * any subsequent calls after receiving this first response.
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
    public PollerFlux<BinaryData, BinaryData> beginPatch200SucceededIgnoreHeadersAsync(RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.patch200SucceededIgnoreHeadersWithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running put request, service returns a 200 to the initial request with location header. We should not have
     * any subsequent calls after receiving this first response.
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
    public PollerFlux<BinaryData, BinaryData> beginPatch200SucceededIgnoreHeadersAsync(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.patch200SucceededIgnoreHeadersWithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running put request, service returns a 200 to the initial request with location header. We should not have
     * any subsequent calls after receiving this first response.
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
    public SyncPoller<BinaryData, BinaryData> beginPatch200SucceededIgnoreHeaders(
            RequestOptions requestOptions, Context context) {
        return this.beginPatch200SucceededIgnoreHeadersAsync(requestOptions, context).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 201 to the initial request, with an entity that contains
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> put201SucceededWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.put201Succeeded(this.client.getHost(), requestOptions, context));
    }

    /**
     * Long running put request, service returns a 201 to the initial request, with an entity that contains
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> put201SucceededWithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.put201Succeeded(this.client.getHost(), requestOptions, context);
    }

    /**
     * Long running put request, service returns a 201 to the initial request, with an entity that contains
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    public PollerFlux<BinaryData, BinaryData> beginPut201SucceededAsync(RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.put201SucceededWithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running put request, service returns a 201 to the initial request, with an entity that contains
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    public PollerFlux<BinaryData, BinaryData> beginPut201SucceededAsync(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.put201SucceededWithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running put request, service returns a 201 to the initial request, with an entity that contains
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    public SyncPoller<BinaryData, BinaryData> beginPut201Succeeded(RequestOptions requestOptions, Context context) {
        return this.beginPut201SucceededAsync(requestOptions, context).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 202 with empty body to first request, returns a 200 with body [{
     * 'id': '100', 'name': 'foo' }].
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * [
     *     {
     *         id: String
     *         type: String
     *         tags: {
     *             String: String
     *         }
     *         location: String
     *         name: String
     *         properties: {
     *             provisioningState: String
     *             provisioningStateValues: String(Succeeded/Failed/canceled/Accepted/Creating/Created/Updating/Updated/Deleting/Deleted/OK)
     *         }
     *     }
     * ]
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return array of Product.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> post202ListWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.post202List(this.client.getHost(), requestOptions, context));
    }

    /**
     * Long running put request, service returns a 202 with empty body to first request, returns a 200 with body [{
     * 'id': '100', 'name': 'foo' }].
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * [
     *     {
     *         id: String
     *         type: String
     *         tags: {
     *             String: String
     *         }
     *         location: String
     *         name: String
     *         properties: {
     *             provisioningState: String
     *             provisioningStateValues: String(Succeeded/Failed/canceled/Accepted/Creating/Created/Updating/Updated/Deleting/Deleted/OK)
     *         }
     *     }
     * ]
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return array of Product.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> post202ListWithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.post202List(this.client.getHost(), requestOptions, context);
    }

    /**
     * Long running put request, service returns a 202 with empty body to first request, returns a 200 with body [{
     * 'id': '100', 'name': 'foo' }].
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * [
     *     {
     *         id: String
     *         type: String
     *         tags: {
     *             String: String
     *         }
     *         location: String
     *         name: String
     *         properties: {
     *             provisioningState: String
     *             provisioningStateValues: String(Succeeded/Failed/canceled/Accepted/Creating/Created/Updating/Updated/Deleting/Deleted/OK)
     *         }
     *     }
     * ]
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return array of Product.
     */
    public PollerFlux<BinaryData, BinaryData> beginPost202ListAsync(RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.post202ListWithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running put request, service returns a 202 with empty body to first request, returns a 200 with body [{
     * 'id': '100', 'name': 'foo' }].
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * [
     *     {
     *         id: String
     *         type: String
     *         tags: {
     *             String: String
     *         }
     *         location: String
     *         name: String
     *         properties: {
     *             provisioningState: String
     *             provisioningStateValues: String(Succeeded/Failed/canceled/Accepted/Creating/Created/Updating/Updated/Deleting/Deleted/OK)
     *         }
     *     }
     * ]
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return array of Product.
     */
    public PollerFlux<BinaryData, BinaryData> beginPost202ListAsync(RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.post202ListWithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running put request, service returns a 202 with empty body to first request, returns a 200 with body [{
     * 'id': '100', 'name': 'foo' }].
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * [
     *     {
     *         id: String
     *         type: String
     *         tags: {
     *             String: String
     *         }
     *         location: String
     *         name: String
     *         properties: {
     *             provisioningState: String
     *             provisioningStateValues: String(Succeeded/Failed/canceled/Accepted/Creating/Created/Updating/Updated/Deleting/Deleted/OK)
     *         }
     *     }
     * ]
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return array of Product.
     */
    public SyncPoller<BinaryData, BinaryData> beginPost202List(RequestOptions requestOptions, Context context) {
        return this.beginPost202ListAsync(requestOptions, context).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that does not contain
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> put200SucceededNoStateWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.put200SucceededNoState(this.client.getHost(), requestOptions, context));
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that does not contain
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> put200SucceededNoStateWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.put200SucceededNoState(this.client.getHost(), requestOptions, context);
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that does not contain
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    public PollerFlux<BinaryData, BinaryData> beginPut200SucceededNoStateAsync(RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.put200SucceededNoStateWithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that does not contain
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    public PollerFlux<BinaryData, BinaryData> beginPut200SucceededNoStateAsync(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.put200SucceededNoStateWithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that does not contain
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    public SyncPoller<BinaryData, BinaryData> beginPut200SucceededNoState(
            RequestOptions requestOptions, Context context) {
        return this.beginPut200SucceededNoStateAsync(requestOptions, context).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 202 to the initial request, with a location header that points to a
     * polling URL that returns a 200 and an entity that doesn't contains ProvisioningState.
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
    public Mono<Response<BinaryData>> put202Retry200WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.put202Retry200(this.client.getHost(), requestOptions, context));
    }

    /**
     * Long running put request, service returns a 202 to the initial request, with a location header that points to a
     * polling URL that returns a 200 and an entity that doesn't contains ProvisioningState.
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
    public Mono<Response<BinaryData>> put202Retry200WithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.put202Retry200(this.client.getHost(), requestOptions, context);
    }

    /**
     * Long running put request, service returns a 202 to the initial request, with a location header that points to a
     * polling URL that returns a 200 and an entity that doesn't contains ProvisioningState.
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
    public PollerFlux<BinaryData, BinaryData> beginPut202Retry200Async(RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.put202Retry200WithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running put request, service returns a 202 to the initial request, with a location header that points to a
     * polling URL that returns a 200 and an entity that doesn't contains ProvisioningState.
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
    public PollerFlux<BinaryData, BinaryData> beginPut202Retry200Async(RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.put202Retry200WithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running put request, service returns a 202 to the initial request, with a location header that points to a
     * polling URL that returns a 200 and an entity that doesn't contains ProvisioningState.
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
    public SyncPoller<BinaryData, BinaryData> beginPut202Retry200(RequestOptions requestOptions, Context context) {
        return this.beginPut202Retry200Async(requestOptions, context).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 201 to the initial request, with an entity that contains
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> put201CreatingSucceeded200WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.put201CreatingSucceeded200(this.client.getHost(), requestOptions, context));
    }

    /**
     * Long running put request, service returns a 201 to the initial request, with an entity that contains
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> put201CreatingSucceeded200WithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.put201CreatingSucceeded200(this.client.getHost(), requestOptions, context);
    }

    /**
     * Long running put request, service returns a 201 to the initial request, with an entity that contains
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    public PollerFlux<BinaryData, BinaryData> beginPut201CreatingSucceeded200Async(RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.put201CreatingSucceeded200WithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running put request, service returns a 201 to the initial request, with an entity that contains
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    public PollerFlux<BinaryData, BinaryData> beginPut201CreatingSucceeded200Async(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.put201CreatingSucceeded200WithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running put request, service returns a 201 to the initial request, with an entity that contains
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    public SyncPoller<BinaryData, BinaryData> beginPut201CreatingSucceeded200(
            RequestOptions requestOptions, Context context) {
        return this.beginPut201CreatingSucceeded200Async(requestOptions, context).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 201 to the initial request, with an entity that contains
     * ProvisioningState=’Updating’. Polls return this value until the last poll returns a ‘200’ with
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> put200UpdatingSucceeded204WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.put200UpdatingSucceeded204(this.client.getHost(), requestOptions, context));
    }

    /**
     * Long running put request, service returns a 201 to the initial request, with an entity that contains
     * ProvisioningState=’Updating’. Polls return this value until the last poll returns a ‘200’ with
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> put200UpdatingSucceeded204WithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.put200UpdatingSucceeded204(this.client.getHost(), requestOptions, context);
    }

    /**
     * Long running put request, service returns a 201 to the initial request, with an entity that contains
     * ProvisioningState=’Updating’. Polls return this value until the last poll returns a ‘200’ with
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    public PollerFlux<BinaryData, BinaryData> beginPut200UpdatingSucceeded204Async(RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.put200UpdatingSucceeded204WithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running put request, service returns a 201 to the initial request, with an entity that contains
     * ProvisioningState=’Updating’. Polls return this value until the last poll returns a ‘200’ with
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    public PollerFlux<BinaryData, BinaryData> beginPut200UpdatingSucceeded204Async(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.put200UpdatingSucceeded204WithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running put request, service returns a 201 to the initial request, with an entity that contains
     * ProvisioningState=’Updating’. Polls return this value until the last poll returns a ‘200’ with
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
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    public SyncPoller<BinaryData, BinaryData> beginPut200UpdatingSucceeded204(
            RequestOptions requestOptions, Context context) {
        return this.beginPut200UpdatingSucceeded204Async(requestOptions, context).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 201 to the initial request, with an entity that contains
     * ProvisioningState=’Created’. Polls return this value until the last poll returns a ‘200’ with
     * ProvisioningState=’Failed’.
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
    public Mono<Response<BinaryData>> put201CreatingFailed200WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.put201CreatingFailed200(this.client.getHost(), requestOptions, context));
    }

    /**
     * Long running put request, service returns a 201 to the initial request, with an entity that contains
     * ProvisioningState=’Created’. Polls return this value until the last poll returns a ‘200’ with
     * ProvisioningState=’Failed’.
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
    public Mono<Response<BinaryData>> put201CreatingFailed200WithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.put201CreatingFailed200(this.client.getHost(), requestOptions, context);
    }

    /**
     * Long running put request, service returns a 201 to the initial request, with an entity that contains
     * ProvisioningState=’Created’. Polls return this value until the last poll returns a ‘200’ with
     * ProvisioningState=’Failed’.
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
    public PollerFlux<BinaryData, BinaryData> beginPut201CreatingFailed200Async(RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.put201CreatingFailed200WithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running put request, service returns a 201 to the initial request, with an entity that contains
     * ProvisioningState=’Created’. Polls return this value until the last poll returns a ‘200’ with
     * ProvisioningState=’Failed’.
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
    public PollerFlux<BinaryData, BinaryData> beginPut201CreatingFailed200Async(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.put201CreatingFailed200WithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running put request, service returns a 201 to the initial request, with an entity that contains
     * ProvisioningState=’Created’. Polls return this value until the last poll returns a ‘200’ with
     * ProvisioningState=’Failed’.
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
    public SyncPoller<BinaryData, BinaryData> beginPut201CreatingFailed200(
            RequestOptions requestOptions, Context context) {
        return this.beginPut201CreatingFailed200Async(requestOptions, context).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 201 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Polls return this value until the last poll returns a ‘200’ with
     * ProvisioningState=’Canceled’.
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
    public Mono<Response<BinaryData>> put200Acceptedcanceled200WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.put200Acceptedcanceled200(this.client.getHost(), requestOptions, context));
    }

    /**
     * Long running put request, service returns a 201 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Polls return this value until the last poll returns a ‘200’ with
     * ProvisioningState=’Canceled’.
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
    public Mono<Response<BinaryData>> put200Acceptedcanceled200WithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.put200Acceptedcanceled200(this.client.getHost(), requestOptions, context);
    }

    /**
     * Long running put request, service returns a 201 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Polls return this value until the last poll returns a ‘200’ with
     * ProvisioningState=’Canceled’.
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
    public PollerFlux<BinaryData, BinaryData> beginPut200Acceptedcanceled200Async(RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.put200Acceptedcanceled200WithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running put request, service returns a 201 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Polls return this value until the last poll returns a ‘200’ with
     * ProvisioningState=’Canceled’.
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
    public PollerFlux<BinaryData, BinaryData> beginPut200Acceptedcanceled200Async(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.put200Acceptedcanceled200WithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running put request, service returns a 201 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Polls return this value until the last poll returns a ‘200’ with
     * ProvisioningState=’Canceled’.
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
    public SyncPoller<BinaryData, BinaryData> beginPut200Acceptedcanceled200(
            RequestOptions requestOptions, Context context) {
        return this.beginPut200Acceptedcanceled200Async(requestOptions, context).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 202 to the initial request with location header. Subsequent calls to
     * operation status do not contain location header.
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
    public Mono<Response<BinaryData>> putNoHeaderInRetryWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.putNoHeaderInRetry(this.client.getHost(), requestOptions, context));
    }

    /**
     * Long running put request, service returns a 202 to the initial request with location header. Subsequent calls to
     * operation status do not contain location header.
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
    public Mono<Response<BinaryData>> putNoHeaderInRetryWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.putNoHeaderInRetry(this.client.getHost(), requestOptions, context);
    }

    /**
     * Long running put request, service returns a 202 to the initial request with location header. Subsequent calls to
     * operation status do not contain location header.
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
    public PollerFlux<BinaryData, BinaryData> beginPutNoHeaderInRetryAsync(RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.putNoHeaderInRetryWithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running put request, service returns a 202 to the initial request with location header. Subsequent calls to
     * operation status do not contain location header.
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
    public PollerFlux<BinaryData, BinaryData> beginPutNoHeaderInRetryAsync(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.putNoHeaderInRetryWithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running put request, service returns a 202 to the initial request with location header. Subsequent calls to
     * operation status do not contain location header.
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
    public SyncPoller<BinaryData, BinaryData> beginPutNoHeaderInRetry(RequestOptions requestOptions, Context context) {
        return this.beginPutNoHeaderInRetryAsync(requestOptions, context).getSyncPoller();
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
    public Mono<Response<BinaryData>> putAsyncRetrySucceededWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.putAsyncRetrySucceeded(this.client.getHost(), requestOptions, context));
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
    public Mono<Response<BinaryData>> putAsyncRetrySucceededWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.putAsyncRetrySucceeded(this.client.getHost(), requestOptions, context);
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
    public PollerFlux<BinaryData, BinaryData> beginPutAsyncRetrySucceededAsync(RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.putAsyncRetrySucceededWithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
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
    public PollerFlux<BinaryData, BinaryData> beginPutAsyncRetrySucceededAsync(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.putAsyncRetrySucceededWithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
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
    public SyncPoller<BinaryData, BinaryData> beginPutAsyncRetrySucceeded(
            RequestOptions requestOptions, Context context) {
        return this.beginPutAsyncRetrySucceededAsync(requestOptions, context).getSyncPoller();
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
    public Mono<Response<BinaryData>> putAsyncNoRetrySucceededWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.putAsyncNoRetrySucceeded(this.client.getHost(), requestOptions, context));
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
    public Mono<Response<BinaryData>> putAsyncNoRetrySucceededWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.putAsyncNoRetrySucceeded(this.client.getHost(), requestOptions, context);
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
    public PollerFlux<BinaryData, BinaryData> beginPutAsyncNoRetrySucceededAsync(RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.putAsyncNoRetrySucceededWithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
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
    public PollerFlux<BinaryData, BinaryData> beginPutAsyncNoRetrySucceededAsync(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.putAsyncNoRetrySucceededWithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
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
    public SyncPoller<BinaryData, BinaryData> beginPutAsyncNoRetrySucceeded(
            RequestOptions requestOptions, Context context) {
        return this.beginPutAsyncNoRetrySucceededAsync(requestOptions, context).getSyncPoller();
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
    public Mono<Response<BinaryData>> putAsyncRetryFailedWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.putAsyncRetryFailed(this.client.getHost(), requestOptions, context));
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
    public Mono<Response<BinaryData>> putAsyncRetryFailedWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.putAsyncRetryFailed(this.client.getHost(), requestOptions, context);
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
    public PollerFlux<BinaryData, BinaryData> beginPutAsyncRetryFailedAsync(RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.putAsyncRetryFailedWithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
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
    public PollerFlux<BinaryData, BinaryData> beginPutAsyncRetryFailedAsync(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.putAsyncRetryFailedWithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
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
    public SyncPoller<BinaryData, BinaryData> beginPutAsyncRetryFailed(RequestOptions requestOptions, Context context) {
        return this.beginPutAsyncRetryFailedAsync(requestOptions, context).getSyncPoller();
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
    public Mono<Response<BinaryData>> putAsyncNoRetrycanceledWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.putAsyncNoRetrycanceled(this.client.getHost(), requestOptions, context));
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
    public Mono<Response<BinaryData>> putAsyncNoRetrycanceledWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.putAsyncNoRetrycanceled(this.client.getHost(), requestOptions, context);
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
    public PollerFlux<BinaryData, BinaryData> beginPutAsyncNoRetrycanceledAsync(RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.putAsyncNoRetrycanceledWithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
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
    public PollerFlux<BinaryData, BinaryData> beginPutAsyncNoRetrycanceledAsync(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.putAsyncNoRetrycanceledWithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
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
    public SyncPoller<BinaryData, BinaryData> beginPutAsyncNoRetrycanceled(
            RequestOptions requestOptions, Context context) {
        return this.beginPutAsyncNoRetrycanceledAsync(requestOptions, context).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 202 to the initial request with Azure-AsyncOperation header.
     * Subsequent calls to operation status do not contain Azure-AsyncOperation header.
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
    public Mono<Response<BinaryData>> putAsyncNoHeaderInRetryWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.putAsyncNoHeaderInRetry(this.client.getHost(), requestOptions, context));
    }

    /**
     * Long running put request, service returns a 202 to the initial request with Azure-AsyncOperation header.
     * Subsequent calls to operation status do not contain Azure-AsyncOperation header.
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
    public Mono<Response<BinaryData>> putAsyncNoHeaderInRetryWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.putAsyncNoHeaderInRetry(this.client.getHost(), requestOptions, context);
    }

    /**
     * Long running put request, service returns a 202 to the initial request with Azure-AsyncOperation header.
     * Subsequent calls to operation status do not contain Azure-AsyncOperation header.
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
    public PollerFlux<BinaryData, BinaryData> beginPutAsyncNoHeaderInRetryAsync(RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.putAsyncNoHeaderInRetryWithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running put request, service returns a 202 to the initial request with Azure-AsyncOperation header.
     * Subsequent calls to operation status do not contain Azure-AsyncOperation header.
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
    public PollerFlux<BinaryData, BinaryData> beginPutAsyncNoHeaderInRetryAsync(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.putAsyncNoHeaderInRetryWithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running put request, service returns a 202 to the initial request with Azure-AsyncOperation header.
     * Subsequent calls to operation status do not contain Azure-AsyncOperation header.
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
    public SyncPoller<BinaryData, BinaryData> beginPutAsyncNoHeaderInRetry(
            RequestOptions requestOptions, Context context) {
        return this.beginPutAsyncNoHeaderInRetryAsync(requestOptions, context).getSyncPoller();
    }

    /**
     * Long running put request with non resource.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     name: String
     *     id: String
     * }
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     name: String
     *     id: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> putNonResourceWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.putNonResource(this.client.getHost(), requestOptions, context));
    }

    /**
     * Long running put request with non resource.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     name: String
     *     id: String
     * }
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     name: String
     *     id: String
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
    public Mono<Response<BinaryData>> putNonResourceWithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.putNonResource(this.client.getHost(), requestOptions, context);
    }

    /**
     * Long running put request with non resource.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     name: String
     *     id: String
     * }
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     name: String
     *     id: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    public PollerFlux<BinaryData, BinaryData> beginPutNonResourceAsync(RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.putNonResourceWithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running put request with non resource.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     name: String
     *     id: String
     * }
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     name: String
     *     id: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    public PollerFlux<BinaryData, BinaryData> beginPutNonResourceAsync(RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.putNonResourceWithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running put request with non resource.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     name: String
     *     id: String
     * }
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     name: String
     *     id: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    public SyncPoller<BinaryData, BinaryData> beginPutNonResource(RequestOptions requestOptions, Context context) {
        return this.beginPutNonResourceAsync(requestOptions, context).getSyncPoller();
    }

    /**
     * Long running put request with non resource.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     name: String
     *     id: String
     * }
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     name: String
     *     id: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> putAsyncNonResourceWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.putAsyncNonResource(this.client.getHost(), requestOptions, context));
    }

    /**
     * Long running put request with non resource.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     name: String
     *     id: String
     * }
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     name: String
     *     id: String
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
    public Mono<Response<BinaryData>> putAsyncNonResourceWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.putAsyncNonResource(this.client.getHost(), requestOptions, context);
    }

    /**
     * Long running put request with non resource.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     name: String
     *     id: String
     * }
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     name: String
     *     id: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    public PollerFlux<BinaryData, BinaryData> beginPutAsyncNonResourceAsync(RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.putAsyncNonResourceWithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running put request with non resource.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     name: String
     *     id: String
     * }
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     name: String
     *     id: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    public PollerFlux<BinaryData, BinaryData> beginPutAsyncNonResourceAsync(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.putAsyncNonResourceWithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running put request with non resource.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     name: String
     *     id: String
     * }
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     name: String
     *     id: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    public SyncPoller<BinaryData, BinaryData> beginPutAsyncNonResource(RequestOptions requestOptions, Context context) {
        return this.beginPutAsyncNonResourceAsync(requestOptions, context).getSyncPoller();
    }

    /**
     * Long running put request with sub resource.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: String
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
    public Mono<Response<BinaryData>> putSubResourceWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.putSubResource(this.client.getHost(), requestOptions, context));
    }

    /**
     * Long running put request with sub resource.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: String
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
    public Mono<Response<BinaryData>> putSubResourceWithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.putSubResource(this.client.getHost(), requestOptions, context);
    }

    /**
     * Long running put request with sub resource.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: String
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
    public PollerFlux<BinaryData, BinaryData> beginPutSubResourceAsync(RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.putSubResourceWithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running put request with sub resource.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: String
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
    public PollerFlux<BinaryData, BinaryData> beginPutSubResourceAsync(RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.putSubResourceWithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running put request with sub resource.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: String
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
    public SyncPoller<BinaryData, BinaryData> beginPutSubResource(RequestOptions requestOptions, Context context) {
        return this.beginPutSubResourceAsync(requestOptions, context).getSyncPoller();
    }

    /**
     * Long running put request with sub resource.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: String
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
    public Mono<Response<BinaryData>> putAsyncSubResourceWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.putAsyncSubResource(this.client.getHost(), requestOptions, context));
    }

    /**
     * Long running put request with sub resource.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: String
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
    public Mono<Response<BinaryData>> putAsyncSubResourceWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.putAsyncSubResource(this.client.getHost(), requestOptions, context);
    }

    /**
     * Long running put request with sub resource.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: String
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
    public PollerFlux<BinaryData, BinaryData> beginPutAsyncSubResourceAsync(RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.putAsyncSubResourceWithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running put request with sub resource.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: String
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
    public PollerFlux<BinaryData, BinaryData> beginPutAsyncSubResourceAsync(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.putAsyncSubResourceWithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running put request with sub resource.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: String
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
    public SyncPoller<BinaryData, BinaryData> beginPutAsyncSubResource(RequestOptions requestOptions, Context context) {
        return this.beginPutAsyncSubResourceAsync(requestOptions, context).getSyncPoller();
    }

    /**
     * Long running delete request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Accepted’. Polls return this value until the last poll returns a ‘200’ with
     * ProvisioningState=’Succeeded’.
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
    public Mono<Response<BinaryData>> deleteProvisioning202Accepted200SucceededWithResponseAsync(
            RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context ->
                        service.deleteProvisioning202Accepted200Succeeded(
                                this.client.getHost(), requestOptions, context));
    }

    /**
     * Long running delete request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Accepted’. Polls return this value until the last poll returns a ‘200’ with
     * ProvisioningState=’Succeeded’.
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
    public Mono<Response<BinaryData>> deleteProvisioning202Accepted200SucceededWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.deleteProvisioning202Accepted200Succeeded(this.client.getHost(), requestOptions, context);
    }

    /**
     * Long running delete request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Accepted’. Polls return this value until the last poll returns a ‘200’ with
     * ProvisioningState=’Succeeded’.
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
    public PollerFlux<BinaryData, BinaryData> beginDeleteProvisioning202Accepted200SucceededAsync(
            RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.deleteProvisioning202Accepted200SucceededWithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running delete request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Accepted’. Polls return this value until the last poll returns a ‘200’ with
     * ProvisioningState=’Succeeded’.
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
    public PollerFlux<BinaryData, BinaryData> beginDeleteProvisioning202Accepted200SucceededAsync(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.deleteProvisioning202Accepted200SucceededWithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running delete request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Accepted’. Polls return this value until the last poll returns a ‘200’ with
     * ProvisioningState=’Succeeded’.
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
    public SyncPoller<BinaryData, BinaryData> beginDeleteProvisioning202Accepted200Succeeded(
            RequestOptions requestOptions, Context context) {
        return this.beginDeleteProvisioning202Accepted200SucceededAsync(requestOptions, context).getSyncPoller();
    }

    /**
     * Long running delete request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Polls return this value until the last poll returns a ‘200’ with
     * ProvisioningState=’Failed’.
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
    public Mono<Response<BinaryData>> deleteProvisioning202DeletingFailed200WithResponseAsync(
            RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context ->
                        service.deleteProvisioning202DeletingFailed200(this.client.getHost(), requestOptions, context));
    }

    /**
     * Long running delete request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Polls return this value until the last poll returns a ‘200’ with
     * ProvisioningState=’Failed’.
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
    public Mono<Response<BinaryData>> deleteProvisioning202DeletingFailed200WithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.deleteProvisioning202DeletingFailed200(this.client.getHost(), requestOptions, context);
    }

    /**
     * Long running delete request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Polls return this value until the last poll returns a ‘200’ with
     * ProvisioningState=’Failed’.
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
    public PollerFlux<BinaryData, BinaryData> beginDeleteProvisioning202DeletingFailed200Async(
            RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.deleteProvisioning202DeletingFailed200WithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running delete request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Polls return this value until the last poll returns a ‘200’ with
     * ProvisioningState=’Failed’.
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
    public PollerFlux<BinaryData, BinaryData> beginDeleteProvisioning202DeletingFailed200Async(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.deleteProvisioning202DeletingFailed200WithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running delete request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Polls return this value until the last poll returns a ‘200’ with
     * ProvisioningState=’Failed’.
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
    public SyncPoller<BinaryData, BinaryData> beginDeleteProvisioning202DeletingFailed200(
            RequestOptions requestOptions, Context context) {
        return this.beginDeleteProvisioning202DeletingFailed200Async(requestOptions, context).getSyncPoller();
    }

    /**
     * Long running delete request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Polls return this value until the last poll returns a ‘200’ with
     * ProvisioningState=’Canceled’.
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
    public Mono<Response<BinaryData>> deleteProvisioning202Deletingcanceled200WithResponseAsync(
            RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context ->
                        service.deleteProvisioning202Deletingcanceled200(
                                this.client.getHost(), requestOptions, context));
    }

    /**
     * Long running delete request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Polls return this value until the last poll returns a ‘200’ with
     * ProvisioningState=’Canceled’.
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
    public Mono<Response<BinaryData>> deleteProvisioning202Deletingcanceled200WithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.deleteProvisioning202Deletingcanceled200(this.client.getHost(), requestOptions, context);
    }

    /**
     * Long running delete request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Polls return this value until the last poll returns a ‘200’ with
     * ProvisioningState=’Canceled’.
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
    public PollerFlux<BinaryData, BinaryData> beginDeleteProvisioning202Deletingcanceled200Async(
            RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.deleteProvisioning202Deletingcanceled200WithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running delete request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Polls return this value until the last poll returns a ‘200’ with
     * ProvisioningState=’Canceled’.
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
    public PollerFlux<BinaryData, BinaryData> beginDeleteProvisioning202Deletingcanceled200Async(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.deleteProvisioning202Deletingcanceled200WithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running delete request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Polls return this value until the last poll returns a ‘200’ with
     * ProvisioningState=’Canceled’.
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
    public SyncPoller<BinaryData, BinaryData> beginDeleteProvisioning202Deletingcanceled200(
            RequestOptions requestOptions, Context context) {
        return this.beginDeleteProvisioning202Deletingcanceled200Async(requestOptions, context).getSyncPoller();
    }

    /**
     * Long running delete succeeds and returns right away.
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
     * Long running delete succeeds and returns right away.
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
     * Long running delete succeeds and returns right away.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    public PollerFlux<BinaryData, BinaryData> beginDelete204SucceededAsync(RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.delete204SucceededWithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running delete succeeds and returns right away.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    public PollerFlux<BinaryData, BinaryData> beginDelete204SucceededAsync(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.delete204SucceededWithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running delete succeeds and returns right away.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    public SyncPoller<BinaryData, BinaryData> beginDelete204Succeeded(RequestOptions requestOptions, Context context) {
        return this.beginDelete204SucceededAsync(requestOptions, context).getSyncPoller();
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Polls return this value until the last
     * poll returns a ‘200’ with ProvisioningState=’Succeeded’.
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
    public Mono<Response<BinaryData>> delete202Retry200WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.delete202Retry200(this.client.getHost(), requestOptions, context));
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Polls return this value until the last
     * poll returns a ‘200’ with ProvisioningState=’Succeeded’.
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
    public Mono<Response<BinaryData>> delete202Retry200WithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.delete202Retry200(this.client.getHost(), requestOptions, context);
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Polls return this value until the last
     * poll returns a ‘200’ with ProvisioningState=’Succeeded’.
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
    public PollerFlux<BinaryData, BinaryData> beginDelete202Retry200Async(RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.delete202Retry200WithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Polls return this value until the last
     * poll returns a ‘200’ with ProvisioningState=’Succeeded’.
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
    public PollerFlux<BinaryData, BinaryData> beginDelete202Retry200Async(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.delete202Retry200WithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Polls return this value until the last
     * poll returns a ‘200’ with ProvisioningState=’Succeeded’.
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
    public SyncPoller<BinaryData, BinaryData> beginDelete202Retry200(RequestOptions requestOptions, Context context) {
        return this.beginDelete202Retry200Async(requestOptions, context).getSyncPoller();
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Polls return this value until the last
     * poll returns a ‘200’ with ProvisioningState=’Succeeded’.
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
    public Mono<Response<BinaryData>> delete202NoRetry204WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.delete202NoRetry204(this.client.getHost(), requestOptions, context));
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Polls return this value until the last
     * poll returns a ‘200’ with ProvisioningState=’Succeeded’.
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
    public Mono<Response<BinaryData>> delete202NoRetry204WithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.delete202NoRetry204(this.client.getHost(), requestOptions, context);
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Polls return this value until the last
     * poll returns a ‘200’ with ProvisioningState=’Succeeded’.
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
    public PollerFlux<BinaryData, BinaryData> beginDelete202NoRetry204Async(RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.delete202NoRetry204WithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Polls return this value until the last
     * poll returns a ‘200’ with ProvisioningState=’Succeeded’.
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
    public PollerFlux<BinaryData, BinaryData> beginDelete202NoRetry204Async(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.delete202NoRetry204WithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Polls return this value until the last
     * poll returns a ‘200’ with ProvisioningState=’Succeeded’.
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
    public SyncPoller<BinaryData, BinaryData> beginDelete202NoRetry204(RequestOptions requestOptions, Context context) {
        return this.beginDelete202NoRetry204Async(requestOptions, context).getSyncPoller();
    }

    /**
     * Long running delete request, service returns a location header in the initial request. Subsequent calls to
     * operation status do not contain location header.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> deleteNoHeaderInRetryWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.deleteNoHeaderInRetry(this.client.getHost(), requestOptions, context));
    }

    /**
     * Long running delete request, service returns a location header in the initial request. Subsequent calls to
     * operation status do not contain location header.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> deleteNoHeaderInRetryWithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.deleteNoHeaderInRetry(this.client.getHost(), requestOptions, context);
    }

    /**
     * Long running delete request, service returns a location header in the initial request. Subsequent calls to
     * operation status do not contain location header.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    public PollerFlux<BinaryData, BinaryData> beginDeleteNoHeaderInRetryAsync(RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.deleteNoHeaderInRetryWithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running delete request, service returns a location header in the initial request. Subsequent calls to
     * operation status do not contain location header.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    public PollerFlux<BinaryData, BinaryData> beginDeleteNoHeaderInRetryAsync(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.deleteNoHeaderInRetryWithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running delete request, service returns a location header in the initial request. Subsequent calls to
     * operation status do not contain location header.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    public SyncPoller<BinaryData, BinaryData> beginDeleteNoHeaderInRetry(
            RequestOptions requestOptions, Context context) {
        return this.beginDeleteNoHeaderInRetryAsync(requestOptions, context).getSyncPoller();
    }

    /**
     * Long running delete request, service returns an Azure-AsyncOperation header in the initial request. Subsequent
     * calls to operation status do not contain Azure-AsyncOperation header.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> deleteAsyncNoHeaderInRetryWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.deleteAsyncNoHeaderInRetry(this.client.getHost(), requestOptions, context));
    }

    /**
     * Long running delete request, service returns an Azure-AsyncOperation header in the initial request. Subsequent
     * calls to operation status do not contain Azure-AsyncOperation header.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> deleteAsyncNoHeaderInRetryWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.deleteAsyncNoHeaderInRetry(this.client.getHost(), requestOptions, context);
    }

    /**
     * Long running delete request, service returns an Azure-AsyncOperation header in the initial request. Subsequent
     * calls to operation status do not contain Azure-AsyncOperation header.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    public PollerFlux<BinaryData, BinaryData> beginDeleteAsyncNoHeaderInRetryAsync(RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.deleteAsyncNoHeaderInRetryWithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running delete request, service returns an Azure-AsyncOperation header in the initial request. Subsequent
     * calls to operation status do not contain Azure-AsyncOperation header.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    public PollerFlux<BinaryData, BinaryData> beginDeleteAsyncNoHeaderInRetryAsync(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.deleteAsyncNoHeaderInRetryWithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running delete request, service returns an Azure-AsyncOperation header in the initial request. Subsequent
     * calls to operation status do not contain Azure-AsyncOperation header.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    public SyncPoller<BinaryData, BinaryData> beginDeleteAsyncNoHeaderInRetry(
            RequestOptions requestOptions, Context context) {
        return this.beginDeleteAsyncNoHeaderInRetryAsync(requestOptions, context).getSyncPoller();
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
    public Mono<Response<Void>> deleteAsyncRetrySucceededWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.deleteAsyncRetrySucceeded(this.client.getHost(), requestOptions, context));
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
    public Mono<Response<Void>> deleteAsyncRetrySucceededWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.deleteAsyncRetrySucceeded(this.client.getHost(), requestOptions, context);
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
    public PollerFlux<BinaryData, BinaryData> beginDeleteAsyncRetrySucceededAsync(RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.deleteAsyncRetrySucceededWithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
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
    public PollerFlux<BinaryData, BinaryData> beginDeleteAsyncRetrySucceededAsync(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.deleteAsyncRetrySucceededWithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
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
    public SyncPoller<BinaryData, BinaryData> beginDeleteAsyncRetrySucceeded(
            RequestOptions requestOptions, Context context) {
        return this.beginDeleteAsyncRetrySucceededAsync(requestOptions, context).getSyncPoller();
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
    public Mono<Response<Void>> deleteAsyncNoRetrySucceededWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.deleteAsyncNoRetrySucceeded(this.client.getHost(), requestOptions, context));
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
    public Mono<Response<Void>> deleteAsyncNoRetrySucceededWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.deleteAsyncNoRetrySucceeded(this.client.getHost(), requestOptions, context);
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
    public PollerFlux<BinaryData, BinaryData> beginDeleteAsyncNoRetrySucceededAsync(RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.deleteAsyncNoRetrySucceededWithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
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
    public PollerFlux<BinaryData, BinaryData> beginDeleteAsyncNoRetrySucceededAsync(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.deleteAsyncNoRetrySucceededWithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
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
    public SyncPoller<BinaryData, BinaryData> beginDeleteAsyncNoRetrySucceeded(
            RequestOptions requestOptions, Context context) {
        return this.beginDeleteAsyncNoRetrySucceededAsync(requestOptions, context).getSyncPoller();
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
    public Mono<Response<Void>> deleteAsyncRetryFailedWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.deleteAsyncRetryFailed(this.client.getHost(), requestOptions, context));
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
    public Mono<Response<Void>> deleteAsyncRetryFailedWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.deleteAsyncRetryFailed(this.client.getHost(), requestOptions, context);
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
    public PollerFlux<BinaryData, BinaryData> beginDeleteAsyncRetryFailedAsync(RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.deleteAsyncRetryFailedWithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
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
    public PollerFlux<BinaryData, BinaryData> beginDeleteAsyncRetryFailedAsync(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.deleteAsyncRetryFailedWithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
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
    public SyncPoller<BinaryData, BinaryData> beginDeleteAsyncRetryFailed(
            RequestOptions requestOptions, Context context) {
        return this.beginDeleteAsyncRetryFailedAsync(requestOptions, context).getSyncPoller();
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
    public Mono<Response<Void>> deleteAsyncRetrycanceledWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.deleteAsyncRetrycanceled(this.client.getHost(), requestOptions, context));
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
    public Mono<Response<Void>> deleteAsyncRetrycanceledWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.deleteAsyncRetrycanceled(this.client.getHost(), requestOptions, context);
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
    public PollerFlux<BinaryData, BinaryData> beginDeleteAsyncRetrycanceledAsync(RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.deleteAsyncRetrycanceledWithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
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
    public PollerFlux<BinaryData, BinaryData> beginDeleteAsyncRetrycanceledAsync(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.deleteAsyncRetrycanceledWithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
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
    public SyncPoller<BinaryData, BinaryData> beginDeleteAsyncRetrycanceled(
            RequestOptions requestOptions, Context context) {
        return this.beginDeleteAsyncRetrycanceledAsync(requestOptions, context).getSyncPoller();
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with 'Location' header. Poll returns a
     * 200 with a response body after success.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     name: String
     *     id: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> post200WithPayloadWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.post200WithPayload(this.client.getHost(), requestOptions, context));
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with 'Location' header. Poll returns a
     * 200 with a response body after success.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     name: String
     *     id: String
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
    public Mono<Response<BinaryData>> post200WithPayloadWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.post200WithPayload(this.client.getHost(), requestOptions, context);
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with 'Location' header. Poll returns a
     * 200 with a response body after success.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     name: String
     *     id: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    public PollerFlux<BinaryData, BinaryData> beginPost200WithPayloadAsync(RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.post200WithPayloadWithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with 'Location' header. Poll returns a
     * 200 with a response body after success.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     name: String
     *     id: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    public PollerFlux<BinaryData, BinaryData> beginPost200WithPayloadAsync(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.post200WithPayloadWithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with 'Location' header. Poll returns a
     * 200 with a response body after success.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     name: String
     *     id: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    public SyncPoller<BinaryData, BinaryData> beginPost200WithPayload(RequestOptions requestOptions, Context context) {
        return this.beginPost200WithPayloadAsync(requestOptions, context).getSyncPoller();
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with 'Location' and 'Retry-After'
     * headers, Polls return a 200 with a response body after success.
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
    public Mono<Response<Void>> post202Retry200WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.post202Retry200(this.client.getHost(), requestOptions, context));
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with 'Location' and 'Retry-After'
     * headers, Polls return a 200 with a response body after success.
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
    public Mono<Response<Void>> post202Retry200WithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.post202Retry200(this.client.getHost(), requestOptions, context);
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with 'Location' and 'Retry-After'
     * headers, Polls return a 200 with a response body after success.
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
    public PollerFlux<BinaryData, BinaryData> beginPost202Retry200Async(RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.post202Retry200WithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with 'Location' and 'Retry-After'
     * headers, Polls return a 200 with a response body after success.
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
    public PollerFlux<BinaryData, BinaryData> beginPost202Retry200Async(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.post202Retry200WithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with 'Location' and 'Retry-After'
     * headers, Polls return a 200 with a response body after success.
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
    public SyncPoller<BinaryData, BinaryData> beginPost202Retry200(RequestOptions requestOptions, Context context) {
        return this.beginPost202Retry200Async(requestOptions, context).getSyncPoller();
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with 'Location' header, 204 with
     * noresponse body after success.
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
    public Mono<Response<BinaryData>> post202NoRetry204WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.post202NoRetry204(this.client.getHost(), requestOptions, context));
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with 'Location' header, 204 with
     * noresponse body after success.
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
    public Mono<Response<BinaryData>> post202NoRetry204WithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.post202NoRetry204(this.client.getHost(), requestOptions, context);
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with 'Location' header, 204 with
     * noresponse body after success.
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
    public PollerFlux<BinaryData, BinaryData> beginPost202NoRetry204Async(RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.post202NoRetry204WithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with 'Location' header, 204 with
     * noresponse body after success.
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
    public PollerFlux<BinaryData, BinaryData> beginPost202NoRetry204Async(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.post202NoRetry204WithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with 'Location' header, 204 with
     * noresponse body after success.
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
    public SyncPoller<BinaryData, BinaryData> beginPost202NoRetry204(RequestOptions requestOptions, Context context) {
        return this.beginPost202NoRetry204Async(requestOptions, context).getSyncPoller();
    }

    /**
     * Long running post request, service returns a 202 to the initial request with both Location and Azure-Async
     * header. Poll Azure-Async and it's success. Should poll Location to get the final object.
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
    public Mono<Response<BinaryData>> postDoubleHeadersFinalLocationGetWithResponseAsync(
            RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.postDoubleHeadersFinalLocationGet(this.client.getHost(), requestOptions, context));
    }

    /**
     * Long running post request, service returns a 202 to the initial request with both Location and Azure-Async
     * header. Poll Azure-Async and it's success. Should poll Location to get the final object.
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
    public Mono<Response<BinaryData>> postDoubleHeadersFinalLocationGetWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.postDoubleHeadersFinalLocationGet(this.client.getHost(), requestOptions, context);
    }

    /**
     * Long running post request, service returns a 202 to the initial request with both Location and Azure-Async
     * header. Poll Azure-Async and it's success. Should poll Location to get the final object.
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
    public PollerFlux<BinaryData, BinaryData> beginPostDoubleHeadersFinalLocationGetAsync(
            RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.postDoubleHeadersFinalLocationGetWithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running post request, service returns a 202 to the initial request with both Location and Azure-Async
     * header. Poll Azure-Async and it's success. Should poll Location to get the final object.
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
    public PollerFlux<BinaryData, BinaryData> beginPostDoubleHeadersFinalLocationGetAsync(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.postDoubleHeadersFinalLocationGetWithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running post request, service returns a 202 to the initial request with both Location and Azure-Async
     * header. Poll Azure-Async and it's success. Should poll Location to get the final object.
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
    public SyncPoller<BinaryData, BinaryData> beginPostDoubleHeadersFinalLocationGet(
            RequestOptions requestOptions, Context context) {
        return this.beginPostDoubleHeadersFinalLocationGetAsync(requestOptions, context).getSyncPoller();
    }

    /**
     * Long running post request, service returns a 202 to the initial request with both Location and Azure-Async
     * header. Poll Azure-Async and it's success. Should NOT poll Location to get the final object.
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
    public Mono<Response<BinaryData>> postDoubleHeadersFinalAzureHeaderGetWithResponseAsync(
            RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context ->
                        service.postDoubleHeadersFinalAzureHeaderGet(this.client.getHost(), requestOptions, context));
    }

    /**
     * Long running post request, service returns a 202 to the initial request with both Location and Azure-Async
     * header. Poll Azure-Async and it's success. Should NOT poll Location to get the final object.
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
    public Mono<Response<BinaryData>> postDoubleHeadersFinalAzureHeaderGetWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.postDoubleHeadersFinalAzureHeaderGet(this.client.getHost(), requestOptions, context);
    }

    /**
     * Long running post request, service returns a 202 to the initial request with both Location and Azure-Async
     * header. Poll Azure-Async and it's success. Should NOT poll Location to get the final object.
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
    public PollerFlux<BinaryData, BinaryData> beginPostDoubleHeadersFinalAzureHeaderGetAsync(
            RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.postDoubleHeadersFinalAzureHeaderGetWithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running post request, service returns a 202 to the initial request with both Location and Azure-Async
     * header. Poll Azure-Async and it's success. Should NOT poll Location to get the final object.
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
    public PollerFlux<BinaryData, BinaryData> beginPostDoubleHeadersFinalAzureHeaderGetAsync(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.postDoubleHeadersFinalAzureHeaderGetWithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running post request, service returns a 202 to the initial request with both Location and Azure-Async
     * header. Poll Azure-Async and it's success. Should NOT poll Location to get the final object.
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
    public SyncPoller<BinaryData, BinaryData> beginPostDoubleHeadersFinalAzureHeaderGet(
            RequestOptions requestOptions, Context context) {
        return this.beginPostDoubleHeadersFinalAzureHeaderGetAsync(requestOptions, context).getSyncPoller();
    }

    /**
     * Long running post request, service returns a 202 to the initial request with both Location and Azure-Async
     * header. Poll Azure-Async and it's success. Should NOT poll Location to get the final object if you support
     * initial Autorest behavior.
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
    public Mono<Response<BinaryData>> postDoubleHeadersFinalAzureHeaderGetDefaultWithResponseAsync(
            RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context ->
                        service.postDoubleHeadersFinalAzureHeaderGetDefault(
                                this.client.getHost(), requestOptions, context));
    }

    /**
     * Long running post request, service returns a 202 to the initial request with both Location and Azure-Async
     * header. Poll Azure-Async and it's success. Should NOT poll Location to get the final object if you support
     * initial Autorest behavior.
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
    public Mono<Response<BinaryData>> postDoubleHeadersFinalAzureHeaderGetDefaultWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.postDoubleHeadersFinalAzureHeaderGetDefault(this.client.getHost(), requestOptions, context);
    }

    /**
     * Long running post request, service returns a 202 to the initial request with both Location and Azure-Async
     * header. Poll Azure-Async and it's success. Should NOT poll Location to get the final object if you support
     * initial Autorest behavior.
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
    public PollerFlux<BinaryData, BinaryData> beginPostDoubleHeadersFinalAzureHeaderGetDefaultAsync(
            RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.postDoubleHeadersFinalAzureHeaderGetDefaultWithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running post request, service returns a 202 to the initial request with both Location and Azure-Async
     * header. Poll Azure-Async and it's success. Should NOT poll Location to get the final object if you support
     * initial Autorest behavior.
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
    public PollerFlux<BinaryData, BinaryData> beginPostDoubleHeadersFinalAzureHeaderGetDefaultAsync(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.postDoubleHeadersFinalAzureHeaderGetDefaultWithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * Long running post request, service returns a 202 to the initial request with both Location and Azure-Async
     * header. Poll Azure-Async and it's success. Should NOT poll Location to get the final object if you support
     * initial Autorest behavior.
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
    public SyncPoller<BinaryData, BinaryData> beginPostDoubleHeadersFinalAzureHeaderGetDefault(
            RequestOptions requestOptions, Context context) {
        return this.beginPostDoubleHeadersFinalAzureHeaderGetDefaultAsync(requestOptions, context).getSyncPoller();
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
    public Mono<Response<BinaryData>> postAsyncRetrySucceededWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.postAsyncRetrySucceeded(this.client.getHost(), requestOptions, context));
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
    public Mono<Response<BinaryData>> postAsyncRetrySucceededWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.postAsyncRetrySucceeded(this.client.getHost(), requestOptions, context);
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
    public PollerFlux<BinaryData, BinaryData> beginPostAsyncRetrySucceededAsync(RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.postAsyncRetrySucceededWithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
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
    public PollerFlux<BinaryData, BinaryData> beginPostAsyncRetrySucceededAsync(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.postAsyncRetrySucceededWithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
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
    public SyncPoller<BinaryData, BinaryData> beginPostAsyncRetrySucceeded(
            RequestOptions requestOptions, Context context) {
        return this.beginPostAsyncRetrySucceededAsync(requestOptions, context).getSyncPoller();
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
    public Mono<Response<BinaryData>> postAsyncNoRetrySucceededWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.postAsyncNoRetrySucceeded(this.client.getHost(), requestOptions, context));
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
    public Mono<Response<BinaryData>> postAsyncNoRetrySucceededWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.postAsyncNoRetrySucceeded(this.client.getHost(), requestOptions, context);
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
    public PollerFlux<BinaryData, BinaryData> beginPostAsyncNoRetrySucceededAsync(RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.postAsyncNoRetrySucceededWithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
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
    public PollerFlux<BinaryData, BinaryData> beginPostAsyncNoRetrySucceededAsync(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.postAsyncNoRetrySucceededWithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
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
    public SyncPoller<BinaryData, BinaryData> beginPostAsyncNoRetrySucceeded(
            RequestOptions requestOptions, Context context) {
        return this.beginPostAsyncNoRetrySucceededAsync(requestOptions, context).getSyncPoller();
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
    public Mono<Response<Void>> postAsyncRetryFailedWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.postAsyncRetryFailed(this.client.getHost(), requestOptions, context));
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
    public Mono<Response<Void>> postAsyncRetryFailedWithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.postAsyncRetryFailed(this.client.getHost(), requestOptions, context);
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
    public PollerFlux<BinaryData, BinaryData> beginPostAsyncRetryFailedAsync(RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.postAsyncRetryFailedWithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
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
    public PollerFlux<BinaryData, BinaryData> beginPostAsyncRetryFailedAsync(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.postAsyncRetryFailedWithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
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
    public SyncPoller<BinaryData, BinaryData> beginPostAsyncRetryFailed(
            RequestOptions requestOptions, Context context) {
        return this.beginPostAsyncRetryFailedAsync(requestOptions, context).getSyncPoller();
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
    public Mono<Response<Void>> postAsyncRetrycanceledWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.postAsyncRetrycanceled(this.client.getHost(), requestOptions, context));
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
    public Mono<Response<Void>> postAsyncRetrycanceledWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.postAsyncRetrycanceled(this.client.getHost(), requestOptions, context);
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
    public PollerFlux<BinaryData, BinaryData> beginPostAsyncRetrycanceledAsync(RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.postAsyncRetrycanceledWithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
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
    public PollerFlux<BinaryData, BinaryData> beginPostAsyncRetrycanceledAsync(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.postAsyncRetrycanceledWithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
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
    public SyncPoller<BinaryData, BinaryData> beginPostAsyncRetrycanceled(
            RequestOptions requestOptions, Context context) {
        return this.beginPostAsyncRetrycanceledAsync(requestOptions, context).getSyncPoller();
    }
}
