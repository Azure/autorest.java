package fixtures.lro;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.Delete;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.Patch;
import com.azure.core.annotation.Post;
import com.azure.core.annotation.Put;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import com.azure.core.util.polling.ChainedPollingStrategy;
import com.azure.core.util.polling.PollerFlux;
import com.azure.core.util.polling.SyncPoller;
import com.azure.core.util.serializer.TypeReference;
import fixtures.lro.models.CloudErrorException;
import fixtures.lro.models.LROsDelete202NoRetry204Response;
import fixtures.lro.models.LROsDelete202Retry200Response;
import fixtures.lro.models.LROsDeleteAsyncNoHeaderInRetryResponse;
import fixtures.lro.models.LROsDeleteAsyncNoRetrySucceededResponse;
import fixtures.lro.models.LROsDeleteAsyncRetrycanceledResponse;
import fixtures.lro.models.LROsDeleteAsyncRetryFailedResponse;
import fixtures.lro.models.LROsDeleteAsyncRetrySucceededResponse;
import fixtures.lro.models.LROsDeleteNoHeaderInRetryResponse;
import fixtures.lro.models.LROsDeleteProvisioning202Accepted200SucceededResponse;
import fixtures.lro.models.LROsDeleteProvisioning202Deletingcanceled200Response;
import fixtures.lro.models.LROsDeleteProvisioning202DeletingFailed200Response;
import fixtures.lro.models.LROsPatch200SucceededIgnoreHeadersResponse;
import fixtures.lro.models.LROsPost202ListResponse;
import fixtures.lro.models.LROsPost202NoRetry204Response;
import fixtures.lro.models.LROsPost202Retry200Response;
import fixtures.lro.models.LROsPostAsyncNoRetrySucceededResponse;
import fixtures.lro.models.LROsPostAsyncRetrycanceledResponse;
import fixtures.lro.models.LROsPostAsyncRetryFailedResponse;
import fixtures.lro.models.LROsPostAsyncRetrySucceededResponse;
import fixtures.lro.models.LROsPutAsyncNoHeaderInRetryResponse;
import fixtures.lro.models.LROsPutAsyncNoRetrycanceledResponse;
import fixtures.lro.models.LROsPutAsyncNoRetrySucceededResponse;
import fixtures.lro.models.LROsPutAsyncRetryFailedResponse;
import fixtures.lro.models.LROsPutAsyncRetrySucceededResponse;
import fixtures.lro.models.LROsPutNoHeaderInRetryResponse;
import fixtures.lro.models.Product;
import fixtures.lro.models.Sku;
import fixtures.lro.models.SubProduct;
import java.time.Duration;
import java.util.List;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in
 * LROs.
 */
public final class LROs {
    /**
     * The proxy service used to perform REST calls.
     */
    private final LROsService service;

    /**
     * The service client containing this operation class.
     */
    private final AutoRestLongRunningOperationTestService client;

    /**
     * Initializes an instance of LROs.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    LROs(AutoRestLongRunningOperationTestService client) {
        this.service = RestProxy.create(LROsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for
     * AutoRestLongRunningOperationTestServiceLROs to be used by the proxy
     * service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestLongRunningO")
    private interface LROsService {
        @Put("/lro/put/200/succeeded")
        @ExpectedResponses({200, 204})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<Response<Product>> put200Succeeded(@HostParam("$host") String host, @BodyParam("application/json") Product product, @HeaderParam("Accept") String accept, Context context);

        @Patch("/lro/patch/200/succeeded/ignoreheaders")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LROsPatch200SucceededIgnoreHeadersResponse> patch200SucceededIgnoreHeaders(@HostParam("$host") String host, @BodyParam("application/json") Product product, @HeaderParam("Accept") String accept, Context context);

        @Put("/lro/put/201/succeeded")
        @ExpectedResponses({201})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<Response<Product>> put201Succeeded(@HostParam("$host") String host, @BodyParam("application/json") Product product, @HeaderParam("Accept") String accept, Context context);

        @Post("/lro/list")
        @ExpectedResponses({200, 202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LROsPost202ListResponse> post202List(@HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/lro/put/200/succeeded/nostate")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<Response<Product>> put200SucceededNoState(@HostParam("$host") String host, @BodyParam("application/json") Product product, @HeaderParam("Accept") String accept, Context context);

        @Put("/lro/put/202/retry/200")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<Response<Product>> put202Retry200(@HostParam("$host") String host, @BodyParam("application/json") Product product, @HeaderParam("Accept") String accept, Context context);

        @Put("/lro/put/201/creating/succeeded/200")
        @ExpectedResponses({200, 201})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<Response<Product>> put201CreatingSucceeded200(@HostParam("$host") String host, @BodyParam("application/json") Product product, @HeaderParam("Accept") String accept, Context context);

        @Put("/lro/put/200/updating/succeeded/200")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<Response<Product>> put200UpdatingSucceeded204(@HostParam("$host") String host, @BodyParam("application/json") Product product, @HeaderParam("Accept") String accept, Context context);

        @Put("/lro/put/201/created/failed/200")
        @ExpectedResponses({200, 201})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<Response<Product>> put201CreatingFailed200(@HostParam("$host") String host, @BodyParam("application/json") Product product, @HeaderParam("Accept") String accept, Context context);

        @Put("/lro/put/200/accepted/canceled/200")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<Response<Product>> put200Acceptedcanceled200(@HostParam("$host") String host, @BodyParam("application/json") Product product, @HeaderParam("Accept") String accept, Context context);

        @Put("/lro/put/noheader/202/200")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LROsPutNoHeaderInRetryResponse> putNoHeaderInRetry(@HostParam("$host") String host, @BodyParam("application/json") Product product, @HeaderParam("Accept") String accept, Context context);

        @Put("/lro/putasync/retry/succeeded")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LROsPutAsyncRetrySucceededResponse> putAsyncRetrySucceeded(@HostParam("$host") String host, @BodyParam("application/json") Product product, @HeaderParam("Accept") String accept, Context context);

        @Put("/lro/putasync/noretry/succeeded")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LROsPutAsyncNoRetrySucceededResponse> putAsyncNoRetrySucceeded(@HostParam("$host") String host, @BodyParam("application/json") Product product, @HeaderParam("Accept") String accept, Context context);

        @Put("/lro/putasync/retry/failed")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LROsPutAsyncRetryFailedResponse> putAsyncRetryFailed(@HostParam("$host") String host, @BodyParam("application/json") Product product, @HeaderParam("Accept") String accept, Context context);

        @Put("/lro/putasync/noretry/canceled")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LROsPutAsyncNoRetrycanceledResponse> putAsyncNoRetrycanceled(@HostParam("$host") String host, @BodyParam("application/json") Product product, @HeaderParam("Accept") String accept, Context context);

        @Put("/lro/putasync/noheader/201/200")
        @ExpectedResponses({201})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LROsPutAsyncNoHeaderInRetryResponse> putAsyncNoHeaderInRetry(@HostParam("$host") String host, @BodyParam("application/json") Product product, @HeaderParam("Accept") String accept, Context context);

        @Put("/lro/putnonresource/202/200")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<Response<Sku>> putNonResource(@HostParam("$host") String host, @BodyParam("application/json") Sku sku, @HeaderParam("Accept") String accept, Context context);

        @Put("/lro/putnonresourceasync/202/200")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<Response<Sku>> putAsyncNonResource(@HostParam("$host") String host, @BodyParam("application/json") Sku sku, @HeaderParam("Accept") String accept, Context context);

        @Put("/lro/putsubresource/202/200")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<Response<SubProduct>> putSubResource(@HostParam("$host") String host, @BodyParam("application/json") SubProduct product, @HeaderParam("Accept") String accept, Context context);

        @Put("/lro/putsubresourceasync/202/200")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<Response<SubProduct>> putAsyncSubResource(@HostParam("$host") String host, @BodyParam("application/json") SubProduct product, @HeaderParam("Accept") String accept, Context context);

        @Delete("/lro/delete/provisioning/202/accepted/200/succeeded")
        @ExpectedResponses({200, 202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LROsDeleteProvisioning202Accepted200SucceededResponse> deleteProvisioning202Accepted200Succeeded(@HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Delete("/lro/delete/provisioning/202/deleting/200/failed")
        @ExpectedResponses({200, 202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LROsDeleteProvisioning202DeletingFailed200Response> deleteProvisioning202DeletingFailed200(@HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Delete("/lro/delete/provisioning/202/deleting/200/canceled")
        @ExpectedResponses({200, 202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LROsDeleteProvisioning202Deletingcanceled200Response> deleteProvisioning202Deletingcanceled200(@HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Delete("/lro/delete/204/succeeded")
        @ExpectedResponses({204})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<Response<Void>> delete204Succeeded(@HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Delete("/lro/delete/202/retry/200")
        @ExpectedResponses({200, 202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LROsDelete202Retry200Response> delete202Retry200(@HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Delete("/lro/delete/202/noretry/204")
        @ExpectedResponses({200, 202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LROsDelete202NoRetry204Response> delete202NoRetry204(@HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Delete("/lro/delete/noheader")
        @ExpectedResponses({202, 204})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LROsDeleteNoHeaderInRetryResponse> deleteNoHeaderInRetry(@HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Delete("/lro/deleteasync/noheader/202/204")
        @ExpectedResponses({202, 204})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LROsDeleteAsyncNoHeaderInRetryResponse> deleteAsyncNoHeaderInRetry(@HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Delete("/lro/deleteasync/retry/succeeded")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LROsDeleteAsyncRetrySucceededResponse> deleteAsyncRetrySucceeded(@HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Delete("/lro/deleteasync/noretry/succeeded")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LROsDeleteAsyncNoRetrySucceededResponse> deleteAsyncNoRetrySucceeded(@HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Delete("/lro/deleteasync/retry/failed")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LROsDeleteAsyncRetryFailedResponse> deleteAsyncRetryFailed(@HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Delete("/lro/deleteasync/retry/canceled")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LROsDeleteAsyncRetrycanceledResponse> deleteAsyncRetrycanceled(@HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Post("/lro/post/payload/200")
        @ExpectedResponses({200, 202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<Response<Sku>> post200WithPayload(@HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Post("/lro/post/202/retry/200")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LROsPost202Retry200Response> post202Retry200(@HostParam("$host") String host, @BodyParam("application/json") Product product, @HeaderParam("Accept") String accept, Context context);

        @Post("/lro/post/202/noretry/204")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LROsPost202NoRetry204Response> post202NoRetry204(@HostParam("$host") String host, @BodyParam("application/json") Product product, @HeaderParam("Accept") String accept, Context context);

        @Post("/lro/LROPostDoubleHeadersFinalLocationGet")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<Response<Product>> postDoubleHeadersFinalLocationGet(@HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Post("/lro/LROPostDoubleHeadersFinalAzureHeaderGet")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<Response<Product>> postDoubleHeadersFinalAzureHeaderGet(@HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Post("/lro/LROPostDoubleHeadersFinalAzureHeaderGetDefault")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<Response<Product>> postDoubleHeadersFinalAzureHeaderGetDefault(@HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Post("/lro/postasync/retry/succeeded")
        @ExpectedResponses({200, 202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LROsPostAsyncRetrySucceededResponse> postAsyncRetrySucceeded(@HostParam("$host") String host, @BodyParam("application/json") Product product, @HeaderParam("Accept") String accept, Context context);

        @Post("/lro/postasync/noretry/succeeded")
        @ExpectedResponses({200, 202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LROsPostAsyncNoRetrySucceededResponse> postAsyncNoRetrySucceeded(@HostParam("$host") String host, @BodyParam("application/json") Product product, @HeaderParam("Accept") String accept, Context context);

        @Post("/lro/postasync/retry/failed")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LROsPostAsyncRetryFailedResponse> postAsyncRetryFailed(@HostParam("$host") String host, @BodyParam("application/json") Product product, @HeaderParam("Accept") String accept, Context context);

        @Post("/lro/postasync/retry/canceled")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LROsPostAsyncRetrycanceledResponse> postAsyncRetrycanceled(@HostParam("$host") String host, @BodyParam("application/json") Product product, @HeaderParam("Accept") String accept, Context context);
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains ProvisioningState=’Succeeded’.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Product>> put200SucceededWithResponseAsync(Product product, Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.put200Succeeded(this.client.getHost(), product, accept, context));
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains ProvisioningState=’Succeeded’.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PollerFlux<Product, Product> beginPut200SucceededAsync(Product product, Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.put200SucceededWithResponseAsync(product, pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Product>() { }, new TypeReference<Product>() { });
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains ProvisioningState=’Succeeded’.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public SyncPoller<Product, Product> beginPut200Succeeded(Product product, Duration pollInterval) {
        return this.beginPut200SucceededAsync(product, pollInterval).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 200 to the initial request with location header. We should not have any subsequent calls after receiving this first response.
     * 
     * @param product Product to patch.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LROsPatch200SucceededIgnoreHeadersResponse> patch200SucceededIgnoreHeadersWithResponseAsync(Product product, Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.patch200SucceededIgnoreHeaders(this.client.getHost(), product, accept, context));
    }

    /**
     * Long running put request, service returns a 200 to the initial request with location header. We should not have any subsequent calls after receiving this first response.
     * 
     * @param product Product to patch.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PollerFlux<Product, Product> beginPatch200SucceededIgnoreHeadersAsync(Product product, Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.patch200SucceededIgnoreHeadersWithResponseAsync(product, pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Product>() { }, new TypeReference<Product>() { });
    }

    /**
     * Long running put request, service returns a 200 to the initial request with location header. We should not have any subsequent calls after receiving this first response.
     * 
     * @param product Product to patch.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public SyncPoller<Product, Product> beginPatch200SucceededIgnoreHeaders(Product product, Duration pollInterval) {
        return this.beginPatch200SucceededIgnoreHeadersAsync(product, pollInterval).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 201 to the initial request, with an entity that contains ProvisioningState=’Succeeded’.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Product>> put201SucceededWithResponseAsync(Product product, Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.put201Succeeded(this.client.getHost(), product, accept, context));
    }

    /**
     * Long running put request, service returns a 201 to the initial request, with an entity that contains ProvisioningState=’Succeeded’.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PollerFlux<Product, Product> beginPut201SucceededAsync(Product product, Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.put201SucceededWithResponseAsync(product, pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Product>() { }, new TypeReference<Product>() { });
    }

    /**
     * Long running put request, service returns a 201 to the initial request, with an entity that contains ProvisioningState=’Succeeded’.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public SyncPoller<Product, Product> beginPut201Succeeded(Product product, Duration pollInterval) {
        return this.beginPut201SucceededAsync(product, pollInterval).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 202 with empty body to first request, returns a 200 with body [{ 'id': '100', 'name': 'foo' }].
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return array of Product.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LROsPost202ListResponse> post202ListWithResponseAsync(Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.post202List(this.client.getHost(), accept, context));
    }

    /**
     * Long running put request, service returns a 202 with empty body to first request, returns a 200 with body [{ 'id': '100', 'name': 'foo' }].
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return array of Product.
     */
    public PollerFlux<List<Product>, List<Product>> beginPost202ListAsync(Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.post202ListWithResponseAsync(pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<List<Product>>() { }, new TypeReference<List<Product>>() { });
    }

    /**
     * Long running put request, service returns a 202 with empty body to first request, returns a 200 with body [{ 'id': '100', 'name': 'foo' }].
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return array of Product.
     */
    public SyncPoller<List<Product>, List<Product>> beginPost202List(Duration pollInterval) {
        return this.beginPost202ListAsync(pollInterval).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that does not contain ProvisioningState=’Succeeded’.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Product>> put200SucceededNoStateWithResponseAsync(Product product, Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.put200SucceededNoState(this.client.getHost(), product, accept, context));
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that does not contain ProvisioningState=’Succeeded’.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PollerFlux<Product, Product> beginPut200SucceededNoStateAsync(Product product, Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.put200SucceededNoStateWithResponseAsync(product, pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Product>() { }, new TypeReference<Product>() { });
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that does not contain ProvisioningState=’Succeeded’.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public SyncPoller<Product, Product> beginPut200SucceededNoState(Product product, Duration pollInterval) {
        return this.beginPut200SucceededNoStateAsync(product, pollInterval).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 202 to the initial request, with a location header that points to a polling URL that returns a 200 and an entity that doesn't contains ProvisioningState.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Product>> put202Retry200WithResponseAsync(Product product, Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.put202Retry200(this.client.getHost(), product, accept, context));
    }

    /**
     * Long running put request, service returns a 202 to the initial request, with a location header that points to a polling URL that returns a 200 and an entity that doesn't contains ProvisioningState.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PollerFlux<Product, Product> beginPut202Retry200Async(Product product, Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.put202Retry200WithResponseAsync(product, pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Product>() { }, new TypeReference<Product>() { });
    }

    /**
     * Long running put request, service returns a 202 to the initial request, with a location header that points to a polling URL that returns a 200 and an entity that doesn't contains ProvisioningState.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public SyncPoller<Product, Product> beginPut202Retry200(Product product, Duration pollInterval) {
        return this.beginPut202Retry200Async(product, pollInterval).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 201 to the initial request, with an entity that contains ProvisioningState=’Creating’.  Polls return this value until the last poll returns a ‘200’ with ProvisioningState=’Succeeded’.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Product>> put201CreatingSucceeded200WithResponseAsync(Product product, Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.put201CreatingSucceeded200(this.client.getHost(), product, accept, context));
    }

    /**
     * Long running put request, service returns a 201 to the initial request, with an entity that contains ProvisioningState=’Creating’.  Polls return this value until the last poll returns a ‘200’ with ProvisioningState=’Succeeded’.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PollerFlux<Product, Product> beginPut201CreatingSucceeded200Async(Product product, Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.put201CreatingSucceeded200WithResponseAsync(product, pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Product>() { }, new TypeReference<Product>() { });
    }

    /**
     * Long running put request, service returns a 201 to the initial request, with an entity that contains ProvisioningState=’Creating’.  Polls return this value until the last poll returns a ‘200’ with ProvisioningState=’Succeeded’.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public SyncPoller<Product, Product> beginPut201CreatingSucceeded200(Product product, Duration pollInterval) {
        return this.beginPut201CreatingSucceeded200Async(product, pollInterval).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 201 to the initial request, with an entity that contains ProvisioningState=’Updating’.  Polls return this value until the last poll returns a ‘200’ with ProvisioningState=’Succeeded’.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Product>> put200UpdatingSucceeded204WithResponseAsync(Product product, Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.put200UpdatingSucceeded204(this.client.getHost(), product, accept, context));
    }

    /**
     * Long running put request, service returns a 201 to the initial request, with an entity that contains ProvisioningState=’Updating’.  Polls return this value until the last poll returns a ‘200’ with ProvisioningState=’Succeeded’.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PollerFlux<Product, Product> beginPut200UpdatingSucceeded204Async(Product product, Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.put200UpdatingSucceeded204WithResponseAsync(product, pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Product>() { }, new TypeReference<Product>() { });
    }

    /**
     * Long running put request, service returns a 201 to the initial request, with an entity that contains ProvisioningState=’Updating’.  Polls return this value until the last poll returns a ‘200’ with ProvisioningState=’Succeeded’.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public SyncPoller<Product, Product> beginPut200UpdatingSucceeded204(Product product, Duration pollInterval) {
        return this.beginPut200UpdatingSucceeded204Async(product, pollInterval).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 201 to the initial request, with an entity that contains ProvisioningState=’Created’.  Polls return this value until the last poll returns a ‘200’ with ProvisioningState=’Failed’.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Product>> put201CreatingFailed200WithResponseAsync(Product product, Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.put201CreatingFailed200(this.client.getHost(), product, accept, context));
    }

    /**
     * Long running put request, service returns a 201 to the initial request, with an entity that contains ProvisioningState=’Created’.  Polls return this value until the last poll returns a ‘200’ with ProvisioningState=’Failed’.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PollerFlux<Product, Product> beginPut201CreatingFailed200Async(Product product, Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.put201CreatingFailed200WithResponseAsync(product, pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Product>() { }, new TypeReference<Product>() { });
    }

    /**
     * Long running put request, service returns a 201 to the initial request, with an entity that contains ProvisioningState=’Created’.  Polls return this value until the last poll returns a ‘200’ with ProvisioningState=’Failed’.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public SyncPoller<Product, Product> beginPut201CreatingFailed200(Product product, Duration pollInterval) {
        return this.beginPut201CreatingFailed200Async(product, pollInterval).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 201 to the initial request, with an entity that contains ProvisioningState=’Creating’.  Polls return this value until the last poll returns a ‘200’ with ProvisioningState=’Canceled’.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Product>> put200Acceptedcanceled200WithResponseAsync(Product product, Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.put200Acceptedcanceled200(this.client.getHost(), product, accept, context));
    }

    /**
     * Long running put request, service returns a 201 to the initial request, with an entity that contains ProvisioningState=’Creating’.  Polls return this value until the last poll returns a ‘200’ with ProvisioningState=’Canceled’.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PollerFlux<Product, Product> beginPut200Acceptedcanceled200Async(Product product, Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.put200Acceptedcanceled200WithResponseAsync(product, pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Product>() { }, new TypeReference<Product>() { });
    }

    /**
     * Long running put request, service returns a 201 to the initial request, with an entity that contains ProvisioningState=’Creating’.  Polls return this value until the last poll returns a ‘200’ with ProvisioningState=’Canceled’.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public SyncPoller<Product, Product> beginPut200Acceptedcanceled200(Product product, Duration pollInterval) {
        return this.beginPut200Acceptedcanceled200Async(product, pollInterval).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 202 to the initial request with location header. Subsequent calls to operation status do not contain location header.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LROsPutNoHeaderInRetryResponse> putNoHeaderInRetryWithResponseAsync(Product product, Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.putNoHeaderInRetry(this.client.getHost(), product, accept, context));
    }

    /**
     * Long running put request, service returns a 202 to the initial request with location header. Subsequent calls to operation status do not contain location header.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PollerFlux<Product, Product> beginPutNoHeaderInRetryAsync(Product product, Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.putNoHeaderInRetryWithResponseAsync(product, pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Product>() { }, new TypeReference<Product>() { });
    }

    /**
     * Long running put request, service returns a 202 to the initial request with location header. Subsequent calls to operation status do not contain location header.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public SyncPoller<Product, Product> beginPutNoHeaderInRetry(Product product, Duration pollInterval) {
        return this.beginPutNoHeaderInRetryAsync(product, pollInterval).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation status.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LROsPutAsyncRetrySucceededResponse> putAsyncRetrySucceededWithResponseAsync(Product product, Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.putAsyncRetrySucceeded(this.client.getHost(), product, accept, context));
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation status.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PollerFlux<Product, Product> beginPutAsyncRetrySucceededAsync(Product product, Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.putAsyncRetrySucceededWithResponseAsync(product, pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Product>() { }, new TypeReference<Product>() { });
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation status.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public SyncPoller<Product, Product> beginPutAsyncRetrySucceeded(Product product, Duration pollInterval) {
        return this.beginPutAsyncRetrySucceededAsync(product, pollInterval).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation status.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LROsPutAsyncNoRetrySucceededResponse> putAsyncNoRetrySucceededWithResponseAsync(Product product, Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.putAsyncNoRetrySucceeded(this.client.getHost(), product, accept, context));
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation status.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PollerFlux<Product, Product> beginPutAsyncNoRetrySucceededAsync(Product product, Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.putAsyncNoRetrySucceededWithResponseAsync(product, pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Product>() { }, new TypeReference<Product>() { });
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation status.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public SyncPoller<Product, Product> beginPutAsyncNoRetrySucceeded(Product product, Duration pollInterval) {
        return this.beginPutAsyncNoRetrySucceededAsync(product, pollInterval).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation status.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LROsPutAsyncRetryFailedResponse> putAsyncRetryFailedWithResponseAsync(Product product, Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.putAsyncRetryFailed(this.client.getHost(), product, accept, context));
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation status.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PollerFlux<Product, Product> beginPutAsyncRetryFailedAsync(Product product, Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.putAsyncRetryFailedWithResponseAsync(product, pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Product>() { }, new TypeReference<Product>() { });
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation status.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public SyncPoller<Product, Product> beginPutAsyncRetryFailed(Product product, Duration pollInterval) {
        return this.beginPutAsyncRetryFailedAsync(product, pollInterval).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation status.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LROsPutAsyncNoRetrycanceledResponse> putAsyncNoRetrycanceledWithResponseAsync(Product product, Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.putAsyncNoRetrycanceled(this.client.getHost(), product, accept, context));
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation status.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PollerFlux<Product, Product> beginPutAsyncNoRetrycanceledAsync(Product product, Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.putAsyncNoRetrycanceledWithResponseAsync(product, pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Product>() { }, new TypeReference<Product>() { });
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation status.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public SyncPoller<Product, Product> beginPutAsyncNoRetrycanceled(Product product, Duration pollInterval) {
        return this.beginPutAsyncNoRetrycanceledAsync(product, pollInterval).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 202 to the initial request with Azure-AsyncOperation header. Subsequent calls to operation status do not contain Azure-AsyncOperation header.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LROsPutAsyncNoHeaderInRetryResponse> putAsyncNoHeaderInRetryWithResponseAsync(Product product, Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.putAsyncNoHeaderInRetry(this.client.getHost(), product, accept, context));
    }

    /**
     * Long running put request, service returns a 202 to the initial request with Azure-AsyncOperation header. Subsequent calls to operation status do not contain Azure-AsyncOperation header.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PollerFlux<Product, Product> beginPutAsyncNoHeaderInRetryAsync(Product product, Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.putAsyncNoHeaderInRetryWithResponseAsync(product, pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Product>() { }, new TypeReference<Product>() { });
    }

    /**
     * Long running put request, service returns a 202 to the initial request with Azure-AsyncOperation header. Subsequent calls to operation status do not contain Azure-AsyncOperation header.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public SyncPoller<Product, Product> beginPutAsyncNoHeaderInRetry(Product product, Duration pollInterval) {
        return this.beginPutAsyncNoHeaderInRetryAsync(product, pollInterval).getSyncPoller();
    }

    /**
     * Long running put request with non resource.
     * 
     * @param sku sku to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Sku>> putNonResourceWithResponseAsync(Sku sku, Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (sku != null) {
            sku.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.putNonResource(this.client.getHost(), sku, accept, context));
    }

    /**
     * Long running put request with non resource.
     * 
     * @param sku sku to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PollerFlux<Sku, Sku> beginPutNonResourceAsync(Sku sku, Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.putNonResourceWithResponseAsync(sku, pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Sku>() { }, new TypeReference<Sku>() { });
    }

    /**
     * Long running put request with non resource.
     * 
     * @param sku sku to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public SyncPoller<Sku, Sku> beginPutNonResource(Sku sku, Duration pollInterval) {
        return this.beginPutNonResourceAsync(sku, pollInterval).getSyncPoller();
    }

    /**
     * Long running put request with non resource.
     * 
     * @param sku Sku to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Sku>> putAsyncNonResourceWithResponseAsync(Sku sku, Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (sku != null) {
            sku.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.putAsyncNonResource(this.client.getHost(), sku, accept, context));
    }

    /**
     * Long running put request with non resource.
     * 
     * @param sku Sku to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PollerFlux<Sku, Sku> beginPutAsyncNonResourceAsync(Sku sku, Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.putAsyncNonResourceWithResponseAsync(sku, pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Sku>() { }, new TypeReference<Sku>() { });
    }

    /**
     * Long running put request with non resource.
     * 
     * @param sku Sku to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public SyncPoller<Sku, Sku> beginPutAsyncNonResource(Sku sku, Duration pollInterval) {
        return this.beginPutAsyncNonResourceAsync(sku, pollInterval).getSyncPoller();
    }

    /**
     * Long running put request with sub resource.
     * 
     * @param product Sub Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<SubProduct>> putSubResourceWithResponseAsync(SubProduct product, Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.putSubResource(this.client.getHost(), product, accept, context));
    }

    /**
     * Long running put request with sub resource.
     * 
     * @param product Sub Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PollerFlux<SubProduct, SubProduct> beginPutSubResourceAsync(SubProduct product, Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.putSubResourceWithResponseAsync(product, pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<SubProduct>() { }, new TypeReference<SubProduct>() { });
    }

    /**
     * Long running put request with sub resource.
     * 
     * @param product Sub Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public SyncPoller<SubProduct, SubProduct> beginPutSubResource(SubProduct product, Duration pollInterval) {
        return this.beginPutSubResourceAsync(product, pollInterval).getSyncPoller();
    }

    /**
     * Long running put request with sub resource.
     * 
     * @param product Sub Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<SubProduct>> putAsyncSubResourceWithResponseAsync(SubProduct product, Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.putAsyncSubResource(this.client.getHost(), product, accept, context));
    }

    /**
     * Long running put request with sub resource.
     * 
     * @param product Sub Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PollerFlux<SubProduct, SubProduct> beginPutAsyncSubResourceAsync(SubProduct product, Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.putAsyncSubResourceWithResponseAsync(product, pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<SubProduct>() { }, new TypeReference<SubProduct>() { });
    }

    /**
     * Long running put request with sub resource.
     * 
     * @param product Sub Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public SyncPoller<SubProduct, SubProduct> beginPutAsyncSubResource(SubProduct product, Duration pollInterval) {
        return this.beginPutAsyncSubResourceAsync(product, pollInterval).getSyncPoller();
    }

    /**
     * Long running delete request, service returns a 202 to the initial request, with an entity that contains ProvisioningState=’Accepted’.  Polls return this value until the last poll returns a ‘200’ with ProvisioningState=’Succeeded’.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LROsDeleteProvisioning202Accepted200SucceededResponse> deleteProvisioning202Accepted200SucceededWithResponseAsync(Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.deleteProvisioning202Accepted200Succeeded(this.client.getHost(), accept, context));
    }

    /**
     * Long running delete request, service returns a 202 to the initial request, with an entity that contains ProvisioningState=’Accepted’.  Polls return this value until the last poll returns a ‘200’ with ProvisioningState=’Succeeded’.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PollerFlux<Product, Product> beginDeleteProvisioning202Accepted200SucceededAsync(Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.deleteProvisioning202Accepted200SucceededWithResponseAsync(pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Product>() { }, new TypeReference<Product>() { });
    }

    /**
     * Long running delete request, service returns a 202 to the initial request, with an entity that contains ProvisioningState=’Accepted’.  Polls return this value until the last poll returns a ‘200’ with ProvisioningState=’Succeeded’.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public SyncPoller<Product, Product> beginDeleteProvisioning202Accepted200Succeeded(Duration pollInterval) {
        return this.beginDeleteProvisioning202Accepted200SucceededAsync(pollInterval).getSyncPoller();
    }

    /**
     * Long running delete request, service returns a 202 to the initial request, with an entity that contains ProvisioningState=’Creating’.  Polls return this value until the last poll returns a ‘200’ with ProvisioningState=’Failed’.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LROsDeleteProvisioning202DeletingFailed200Response> deleteProvisioning202DeletingFailed200WithResponseAsync(Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.deleteProvisioning202DeletingFailed200(this.client.getHost(), accept, context));
    }

    /**
     * Long running delete request, service returns a 202 to the initial request, with an entity that contains ProvisioningState=’Creating’.  Polls return this value until the last poll returns a ‘200’ with ProvisioningState=’Failed’.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PollerFlux<Product, Product> beginDeleteProvisioning202DeletingFailed200Async(Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.deleteProvisioning202DeletingFailed200WithResponseAsync(pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Product>() { }, new TypeReference<Product>() { });
    }

    /**
     * Long running delete request, service returns a 202 to the initial request, with an entity that contains ProvisioningState=’Creating’.  Polls return this value until the last poll returns a ‘200’ with ProvisioningState=’Failed’.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public SyncPoller<Product, Product> beginDeleteProvisioning202DeletingFailed200(Duration pollInterval) {
        return this.beginDeleteProvisioning202DeletingFailed200Async(pollInterval).getSyncPoller();
    }

    /**
     * Long running delete request, service returns a 202 to the initial request, with an entity that contains ProvisioningState=’Creating’.  Polls return this value until the last poll returns a ‘200’ with ProvisioningState=’Canceled’.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LROsDeleteProvisioning202Deletingcanceled200Response> deleteProvisioning202Deletingcanceled200WithResponseAsync(Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.deleteProvisioning202Deletingcanceled200(this.client.getHost(), accept, context));
    }

    /**
     * Long running delete request, service returns a 202 to the initial request, with an entity that contains ProvisioningState=’Creating’.  Polls return this value until the last poll returns a ‘200’ with ProvisioningState=’Canceled’.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PollerFlux<Product, Product> beginDeleteProvisioning202Deletingcanceled200Async(Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.deleteProvisioning202Deletingcanceled200WithResponseAsync(pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Product>() { }, new TypeReference<Product>() { });
    }

    /**
     * Long running delete request, service returns a 202 to the initial request, with an entity that contains ProvisioningState=’Creating’.  Polls return this value until the last poll returns a ‘200’ with ProvisioningState=’Canceled’.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public SyncPoller<Product, Product> beginDeleteProvisioning202Deletingcanceled200(Duration pollInterval) {
        return this.beginDeleteProvisioning202Deletingcanceled200Async(pollInterval).getSyncPoller();
    }

    /**
     * Long running delete succeeds and returns right away.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> delete204SucceededWithResponseAsync(Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.delete204Succeeded(this.client.getHost(), accept, context));
    }

    /**
     * Long running delete succeeds and returns right away.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public PollerFlux<Void, Void> beginDelete204SucceededAsync(Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.delete204SucceededWithResponseAsync(pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Void>() { }, new TypeReference<Void>() { });
    }

    /**
     * Long running delete succeeds and returns right away.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public SyncPoller<Void, Void> beginDelete204Succeeded(Duration pollInterval) {
        return this.beginDelete204SucceededAsync(pollInterval).getSyncPoller();
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Polls return this value until the last poll returns a ‘200’ with ProvisioningState=’Succeeded’.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LROsDelete202Retry200Response> delete202Retry200WithResponseAsync(Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.delete202Retry200(this.client.getHost(), accept, context));
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Polls return this value until the last poll returns a ‘200’ with ProvisioningState=’Succeeded’.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PollerFlux<Product, Product> beginDelete202Retry200Async(Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.delete202Retry200WithResponseAsync(pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Product>() { }, new TypeReference<Product>() { });
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Polls return this value until the last poll returns a ‘200’ with ProvisioningState=’Succeeded’.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public SyncPoller<Product, Product> beginDelete202Retry200(Duration pollInterval) {
        return this.beginDelete202Retry200Async(pollInterval).getSyncPoller();
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Polls return this value until the last poll returns a ‘200’ with ProvisioningState=’Succeeded’.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LROsDelete202NoRetry204Response> delete202NoRetry204WithResponseAsync(Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.delete202NoRetry204(this.client.getHost(), accept, context));
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Polls return this value until the last poll returns a ‘200’ with ProvisioningState=’Succeeded’.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PollerFlux<Product, Product> beginDelete202NoRetry204Async(Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.delete202NoRetry204WithResponseAsync(pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Product>() { }, new TypeReference<Product>() { });
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Polls return this value until the last poll returns a ‘200’ with ProvisioningState=’Succeeded’.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public SyncPoller<Product, Product> beginDelete202NoRetry204(Duration pollInterval) {
        return this.beginDelete202NoRetry204Async(pollInterval).getSyncPoller();
    }

    /**
     * Long running delete request, service returns a location header in the initial request. Subsequent calls to operation status do not contain location header.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LROsDeleteNoHeaderInRetryResponse> deleteNoHeaderInRetryWithResponseAsync(Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.deleteNoHeaderInRetry(this.client.getHost(), accept, context));
    }

    /**
     * Long running delete request, service returns a location header in the initial request. Subsequent calls to operation status do not contain location header.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public PollerFlux<Void, Void> beginDeleteNoHeaderInRetryAsync(Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.deleteNoHeaderInRetryWithResponseAsync(pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Void>() { }, new TypeReference<Void>() { });
    }

    /**
     * Long running delete request, service returns a location header in the initial request. Subsequent calls to operation status do not contain location header.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public SyncPoller<Void, Void> beginDeleteNoHeaderInRetry(Duration pollInterval) {
        return this.beginDeleteNoHeaderInRetryAsync(pollInterval).getSyncPoller();
    }

    /**
     * Long running delete request, service returns an Azure-AsyncOperation header in the initial request. Subsequent calls to operation status do not contain Azure-AsyncOperation header.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LROsDeleteAsyncNoHeaderInRetryResponse> deleteAsyncNoHeaderInRetryWithResponseAsync(Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.deleteAsyncNoHeaderInRetry(this.client.getHost(), accept, context));
    }

    /**
     * Long running delete request, service returns an Azure-AsyncOperation header in the initial request. Subsequent calls to operation status do not contain Azure-AsyncOperation header.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public PollerFlux<Void, Void> beginDeleteAsyncNoHeaderInRetryAsync(Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.deleteAsyncNoHeaderInRetryWithResponseAsync(pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Void>() { }, new TypeReference<Void>() { });
    }

    /**
     * Long running delete request, service returns an Azure-AsyncOperation header in the initial request. Subsequent calls to operation status do not contain Azure-AsyncOperation header.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public SyncPoller<Void, Void> beginDeleteAsyncNoHeaderInRetry(Duration pollInterval) {
        return this.beginDeleteAsyncNoHeaderInRetryAsync(pollInterval).getSyncPoller();
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the Azure-AsyncOperation header for operation status.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LROsDeleteAsyncRetrySucceededResponse> deleteAsyncRetrySucceededWithResponseAsync(Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.deleteAsyncRetrySucceeded(this.client.getHost(), accept, context));
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the Azure-AsyncOperation header for operation status.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public PollerFlux<Void, Void> beginDeleteAsyncRetrySucceededAsync(Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.deleteAsyncRetrySucceededWithResponseAsync(pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Void>() { }, new TypeReference<Void>() { });
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the Azure-AsyncOperation header for operation status.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public SyncPoller<Void, Void> beginDeleteAsyncRetrySucceeded(Duration pollInterval) {
        return this.beginDeleteAsyncRetrySucceededAsync(pollInterval).getSyncPoller();
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the Azure-AsyncOperation header for operation status.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LROsDeleteAsyncNoRetrySucceededResponse> deleteAsyncNoRetrySucceededWithResponseAsync(Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.deleteAsyncNoRetrySucceeded(this.client.getHost(), accept, context));
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the Azure-AsyncOperation header for operation status.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public PollerFlux<Void, Void> beginDeleteAsyncNoRetrySucceededAsync(Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.deleteAsyncNoRetrySucceededWithResponseAsync(pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Void>() { }, new TypeReference<Void>() { });
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the Azure-AsyncOperation header for operation status.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public SyncPoller<Void, Void> beginDeleteAsyncNoRetrySucceeded(Duration pollInterval) {
        return this.beginDeleteAsyncNoRetrySucceededAsync(pollInterval).getSyncPoller();
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the Azure-AsyncOperation header for operation status.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LROsDeleteAsyncRetryFailedResponse> deleteAsyncRetryFailedWithResponseAsync(Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.deleteAsyncRetryFailed(this.client.getHost(), accept, context));
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the Azure-AsyncOperation header for operation status.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public PollerFlux<Void, Void> beginDeleteAsyncRetryFailedAsync(Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.deleteAsyncRetryFailedWithResponseAsync(pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Void>() { }, new TypeReference<Void>() { });
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the Azure-AsyncOperation header for operation status.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public SyncPoller<Void, Void> beginDeleteAsyncRetryFailed(Duration pollInterval) {
        return this.beginDeleteAsyncRetryFailedAsync(pollInterval).getSyncPoller();
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the Azure-AsyncOperation header for operation status.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LROsDeleteAsyncRetrycanceledResponse> deleteAsyncRetrycanceledWithResponseAsync(Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.deleteAsyncRetrycanceled(this.client.getHost(), accept, context));
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the Azure-AsyncOperation header for operation status.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public PollerFlux<Void, Void> beginDeleteAsyncRetrycanceledAsync(Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.deleteAsyncRetrycanceledWithResponseAsync(pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Void>() { }, new TypeReference<Void>() { });
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the Azure-AsyncOperation header for operation status.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public SyncPoller<Void, Void> beginDeleteAsyncRetrycanceled(Duration pollInterval) {
        return this.beginDeleteAsyncRetrycanceledAsync(pollInterval).getSyncPoller();
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with 'Location' header. Poll returns a 200 with a response body after success.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Sku>> post200WithPayloadWithResponseAsync(Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.post200WithPayload(this.client.getHost(), accept, context));
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with 'Location' header. Poll returns a 200 with a response body after success.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PollerFlux<Sku, Sku> beginPost200WithPayloadAsync(Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.post200WithPayloadWithResponseAsync(pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Sku>() { }, new TypeReference<Sku>() { });
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with 'Location' header. Poll returns a 200 with a response body after success.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public SyncPoller<Sku, Sku> beginPost200WithPayload(Duration pollInterval) {
        return this.beginPost200WithPayloadAsync(pollInterval).getSyncPoller();
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with 'Location' and 'Retry-After' headers, Polls return a 200 with a response body after success.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LROsPost202Retry200Response> post202Retry200WithResponseAsync(Product product, Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.post202Retry200(this.client.getHost(), product, accept, context));
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with 'Location' and 'Retry-After' headers, Polls return a 200 with a response body after success.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public PollerFlux<Void, Void> beginPost202Retry200Async(Product product, Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.post202Retry200WithResponseAsync(product, pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Void>() { }, new TypeReference<Void>() { });
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with 'Location' and 'Retry-After' headers, Polls return a 200 with a response body after success.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public SyncPoller<Void, Void> beginPost202Retry200(Product product, Duration pollInterval) {
        return this.beginPost202Retry200Async(product, pollInterval).getSyncPoller();
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with 'Location' header, 204 with noresponse body after success.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LROsPost202NoRetry204Response> post202NoRetry204WithResponseAsync(Product product, Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.post202NoRetry204(this.client.getHost(), product, accept, context));
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with 'Location' header, 204 with noresponse body after success.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PollerFlux<Product, Product> beginPost202NoRetry204Async(Product product, Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.post202NoRetry204WithResponseAsync(product, pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Product>() { }, new TypeReference<Product>() { });
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with 'Location' header, 204 with noresponse body after success.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public SyncPoller<Product, Product> beginPost202NoRetry204(Product product, Duration pollInterval) {
        return this.beginPost202NoRetry204Async(product, pollInterval).getSyncPoller();
    }

    /**
     * Long running post request, service returns a 202 to the initial request with both Location and Azure-Async header. Poll Azure-Async and it's success. Should poll Location to get the final object.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Product>> postDoubleHeadersFinalLocationGetWithResponseAsync(Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.postDoubleHeadersFinalLocationGet(this.client.getHost(), accept, context));
    }

    /**
     * Long running post request, service returns a 202 to the initial request with both Location and Azure-Async header. Poll Azure-Async and it's success. Should poll Location to get the final object.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PollerFlux<Product, Product> beginPostDoubleHeadersFinalLocationGetAsync(Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.postDoubleHeadersFinalLocationGetWithResponseAsync(pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Product>() { }, new TypeReference<Product>() { });
    }

    /**
     * Long running post request, service returns a 202 to the initial request with both Location and Azure-Async header. Poll Azure-Async and it's success. Should poll Location to get the final object.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public SyncPoller<Product, Product> beginPostDoubleHeadersFinalLocationGet(Duration pollInterval) {
        return this.beginPostDoubleHeadersFinalLocationGetAsync(pollInterval).getSyncPoller();
    }

    /**
     * Long running post request, service returns a 202 to the initial request with both Location and Azure-Async header. Poll Azure-Async and it's success. Should NOT poll Location to get the final object.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Product>> postDoubleHeadersFinalAzureHeaderGetWithResponseAsync(Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.postDoubleHeadersFinalAzureHeaderGet(this.client.getHost(), accept, context));
    }

    /**
     * Long running post request, service returns a 202 to the initial request with both Location and Azure-Async header. Poll Azure-Async and it's success. Should NOT poll Location to get the final object.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PollerFlux<Product, Product> beginPostDoubleHeadersFinalAzureHeaderGetAsync(Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.postDoubleHeadersFinalAzureHeaderGetWithResponseAsync(pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Product>() { }, new TypeReference<Product>() { });
    }

    /**
     * Long running post request, service returns a 202 to the initial request with both Location and Azure-Async header. Poll Azure-Async and it's success. Should NOT poll Location to get the final object.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public SyncPoller<Product, Product> beginPostDoubleHeadersFinalAzureHeaderGet(Duration pollInterval) {
        return this.beginPostDoubleHeadersFinalAzureHeaderGetAsync(pollInterval).getSyncPoller();
    }

    /**
     * Long running post request, service returns a 202 to the initial request with both Location and Azure-Async header. Poll Azure-Async and it's success. Should NOT poll Location to get the final object if you support initial Autorest behavior.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Product>> postDoubleHeadersFinalAzureHeaderGetDefaultWithResponseAsync(Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.postDoubleHeadersFinalAzureHeaderGetDefault(this.client.getHost(), accept, context));
    }

    /**
     * Long running post request, service returns a 202 to the initial request with both Location and Azure-Async header. Poll Azure-Async and it's success. Should NOT poll Location to get the final object if you support initial Autorest behavior.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PollerFlux<Product, Product> beginPostDoubleHeadersFinalAzureHeaderGetDefaultAsync(Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.postDoubleHeadersFinalAzureHeaderGetDefaultWithResponseAsync(pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Product>() { }, new TypeReference<Product>() { });
    }

    /**
     * Long running post request, service returns a 202 to the initial request with both Location and Azure-Async header. Poll Azure-Async and it's success. Should NOT poll Location to get the final object if you support initial Autorest behavior.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public SyncPoller<Product, Product> beginPostDoubleHeadersFinalAzureHeaderGetDefault(Duration pollInterval) {
        return this.beginPostDoubleHeadersFinalAzureHeaderGetDefaultAsync(pollInterval).getSyncPoller();
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation status.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LROsPostAsyncRetrySucceededResponse> postAsyncRetrySucceededWithResponseAsync(Product product, Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.postAsyncRetrySucceeded(this.client.getHost(), product, accept, context));
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation status.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PollerFlux<Product, Product> beginPostAsyncRetrySucceededAsync(Product product, Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.postAsyncRetrySucceededWithResponseAsync(product, pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Product>() { }, new TypeReference<Product>() { });
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation status.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public SyncPoller<Product, Product> beginPostAsyncRetrySucceeded(Product product, Duration pollInterval) {
        return this.beginPostAsyncRetrySucceededAsync(product, pollInterval).getSyncPoller();
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation status.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LROsPostAsyncNoRetrySucceededResponse> postAsyncNoRetrySucceededWithResponseAsync(Product product, Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.postAsyncNoRetrySucceeded(this.client.getHost(), product, accept, context));
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation status.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PollerFlux<Product, Product> beginPostAsyncNoRetrySucceededAsync(Product product, Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.postAsyncNoRetrySucceededWithResponseAsync(product, pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Product>() { }, new TypeReference<Product>() { });
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation status.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public SyncPoller<Product, Product> beginPostAsyncNoRetrySucceeded(Product product, Duration pollInterval) {
        return this.beginPostAsyncNoRetrySucceededAsync(product, pollInterval).getSyncPoller();
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation status.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LROsPostAsyncRetryFailedResponse> postAsyncRetryFailedWithResponseAsync(Product product, Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.postAsyncRetryFailed(this.client.getHost(), product, accept, context));
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation status.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public PollerFlux<Void, Void> beginPostAsyncRetryFailedAsync(Product product, Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.postAsyncRetryFailedWithResponseAsync(product, pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Void>() { }, new TypeReference<Void>() { });
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation status.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public SyncPoller<Void, Void> beginPostAsyncRetryFailed(Product product, Duration pollInterval) {
        return this.beginPostAsyncRetryFailedAsync(product, pollInterval).getSyncPoller();
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation status.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LROsPostAsyncRetrycanceledResponse> postAsyncRetrycanceledWithResponseAsync(Product product, Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.postAsyncRetrycanceled(this.client.getHost(), product, accept, context));
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation status.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public PollerFlux<Void, Void> beginPostAsyncRetrycanceledAsync(Product product, Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.postAsyncRetrycanceledWithResponseAsync(product, pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Void>() { }, new TypeReference<Void>() { });
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation status.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public SyncPoller<Void, Void> beginPostAsyncRetrycanceled(Product product, Duration pollInterval) {
        return this.beginPostAsyncRetrycanceledAsync(product, pollInterval).getSyncPoller();
    }
}
