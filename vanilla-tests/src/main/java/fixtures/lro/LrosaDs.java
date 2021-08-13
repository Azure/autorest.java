package fixtures.lro;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.Delete;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
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
import fixtures.lro.models.LrosaDsDelete202NonRetry400Response;
import fixtures.lro.models.LrosaDsDelete202RetryInvalidHeaderResponse;
import fixtures.lro.models.LrosaDsDeleteAsyncRelativeRetry400Response;
import fixtures.lro.models.LrosaDsDeleteAsyncRelativeRetryInvalidHeaderResponse;
import fixtures.lro.models.LrosaDsDeleteAsyncRelativeRetryInvalidJsonPollingResponse;
import fixtures.lro.models.LrosaDsDeleteAsyncRelativeRetryNoStatusResponse;
import fixtures.lro.models.LrosaDsDeleteNonRetry400Response;
import fixtures.lro.models.LrosaDsPost202NoLocationResponse;
import fixtures.lro.models.LrosaDsPost202NonRetry400Response;
import fixtures.lro.models.LrosaDsPost202RetryInvalidHeaderResponse;
import fixtures.lro.models.LrosaDsPostAsyncRelativeRetry400Response;
import fixtures.lro.models.LrosaDsPostAsyncRelativeRetryInvalidHeaderResponse;
import fixtures.lro.models.LrosaDsPostAsyncRelativeRetryInvalidJsonPollingResponse;
import fixtures.lro.models.LrosaDsPostAsyncRelativeRetryNoPayloadResponse;
import fixtures.lro.models.LrosaDsPostNonRetry400Response;
import fixtures.lro.models.LrosaDsPutAsyncRelativeRetry400Response;
import fixtures.lro.models.LrosaDsPutAsyncRelativeRetryInvalidHeaderResponse;
import fixtures.lro.models.LrosaDsPutAsyncRelativeRetryInvalidJsonPollingResponse;
import fixtures.lro.models.LrosaDsPutAsyncRelativeRetryNoStatusPayloadResponse;
import fixtures.lro.models.LrosaDsPutAsyncRelativeRetryNoStatusResponse;
import fixtures.lro.models.Product;
import java.time.Duration;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in
 * LrosaDs.
 */
public final class LrosaDs {
    /**
     * The proxy service used to perform REST calls.
     */
    private final LrosaDsService service;

    /**
     * The service client containing this operation class.
     */
    private final AutoRestLongRunningOperationTestService client;

    /**
     * Initializes an instance of LrosaDs.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    LrosaDs(AutoRestLongRunningOperationTestService client) {
        this.service = RestProxy.create(LrosaDsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for
     * AutoRestLongRunningOperationTestServiceLrosaDs to be used by the proxy
     * service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestLongRunningO")
    private interface LrosaDsService {
        @Put("/lro/nonretryerror/put/400")
        @ExpectedResponses({200, 201})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<Response<Product>> putNonRetry400(@HostParam("$host") String host, @BodyParam("application/json") Product product, @HeaderParam("Accept") String accept, Context context);

        @Put("/lro/nonretryerror/put/201/creating/400")
        @ExpectedResponses({200, 201})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<Response<Product>> putNonRetry201Creating400(@HostParam("$host") String host, @BodyParam("application/json") Product product, @HeaderParam("Accept") String accept, Context context);

        @Put("/lro/nonretryerror/put/201/creating/400/invalidjson")
        @ExpectedResponses({200, 201})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<Response<Product>> putNonRetry201Creating400InvalidJson(@HostParam("$host") String host, @BodyParam("application/json") Product product, @HeaderParam("Accept") String accept, Context context);

        @Put("/lro/nonretryerror/putasync/retry/400")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LrosaDsPutAsyncRelativeRetry400Response> putAsyncRelativeRetry400(@HostParam("$host") String host, @BodyParam("application/json") Product product, @HeaderParam("Accept") String accept, Context context);

        @Delete("/lro/nonretryerror/delete/400")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LrosaDsDeleteNonRetry400Response> deleteNonRetry400(@HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Delete("/lro/nonretryerror/delete/202/retry/400")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LrosaDsDelete202NonRetry400Response> delete202NonRetry400(@HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Delete("/lro/nonretryerror/deleteasync/retry/400")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LrosaDsDeleteAsyncRelativeRetry400Response> deleteAsyncRelativeRetry400(@HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Post("/lro/nonretryerror/post/400")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LrosaDsPostNonRetry400Response> postNonRetry400(@HostParam("$host") String host, @BodyParam("application/json") Product product, @HeaderParam("Accept") String accept, Context context);

        @Post("/lro/nonretryerror/post/202/retry/400")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LrosaDsPost202NonRetry400Response> post202NonRetry400(@HostParam("$host") String host, @BodyParam("application/json") Product product, @HeaderParam("Accept") String accept, Context context);

        @Post("/lro/nonretryerror/postasync/retry/400")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LrosaDsPostAsyncRelativeRetry400Response> postAsyncRelativeRetry400(@HostParam("$host") String host, @BodyParam("application/json") Product product, @HeaderParam("Accept") String accept, Context context);

        @Put("/lro/error/put/201/noprovisioningstatepayload")
        @ExpectedResponses({200, 201})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<Response<Product>> putError201NoProvisioningStatePayload(@HostParam("$host") String host, @BodyParam("application/json") Product product, @HeaderParam("Accept") String accept, Context context);

        @Put("/lro/error/putasync/retry/nostatus")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LrosaDsPutAsyncRelativeRetryNoStatusResponse> putAsyncRelativeRetryNoStatus(@HostParam("$host") String host, @BodyParam("application/json") Product product, @HeaderParam("Accept") String accept, Context context);

        @Put("/lro/error/putasync/retry/nostatuspayload")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LrosaDsPutAsyncRelativeRetryNoStatusPayloadResponse> putAsyncRelativeRetryNoStatusPayload(@HostParam("$host") String host, @BodyParam("application/json") Product product, @HeaderParam("Accept") String accept, Context context);

        @Delete("/lro/error/delete/204/nolocation")
        @ExpectedResponses({204})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<Response<Void>> delete204Succeeded(@HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Delete("/lro/error/deleteasync/retry/nostatus")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LrosaDsDeleteAsyncRelativeRetryNoStatusResponse> deleteAsyncRelativeRetryNoStatus(@HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Post("/lro/error/post/202/nolocation")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LrosaDsPost202NoLocationResponse> post202NoLocation(@HostParam("$host") String host, @BodyParam("application/json") Product product, @HeaderParam("Accept") String accept, Context context);

        @Post("/lro/error/postasync/retry/nopayload")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LrosaDsPostAsyncRelativeRetryNoPayloadResponse> postAsyncRelativeRetryNoPayload(@HostParam("$host") String host, @BodyParam("application/json") Product product, @HeaderParam("Accept") String accept, Context context);

        @Put("/lro/error/put/200/invalidjson")
        @ExpectedResponses({200, 204})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<Response<Product>> put200InvalidJson(@HostParam("$host") String host, @BodyParam("application/json") Product product, @HeaderParam("Accept") String accept, Context context);

        @Put("/lro/error/putasync/retry/invalidheader")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LrosaDsPutAsyncRelativeRetryInvalidHeaderResponse> putAsyncRelativeRetryInvalidHeader(@HostParam("$host") String host, @BodyParam("application/json") Product product, @HeaderParam("Accept") String accept, Context context);

        @Put("/lro/error/putasync/retry/invalidjsonpolling")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LrosaDsPutAsyncRelativeRetryInvalidJsonPollingResponse> putAsyncRelativeRetryInvalidJsonPolling(@HostParam("$host") String host, @BodyParam("application/json") Product product, @HeaderParam("Accept") String accept, Context context);

        @Delete("/lro/error/delete/202/retry/invalidheader")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LrosaDsDelete202RetryInvalidHeaderResponse> delete202RetryInvalidHeader(@HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Delete("/lro/error/deleteasync/retry/invalidheader")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LrosaDsDeleteAsyncRelativeRetryInvalidHeaderResponse> deleteAsyncRelativeRetryInvalidHeader(@HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Delete("/lro/error/deleteasync/retry/invalidjsonpolling")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LrosaDsDeleteAsyncRelativeRetryInvalidJsonPollingResponse> deleteAsyncRelativeRetryInvalidJsonPolling(@HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Post("/lro/error/post/202/retry/invalidheader")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LrosaDsPost202RetryInvalidHeaderResponse> post202RetryInvalidHeader(@HostParam("$host") String host, @BodyParam("application/json") Product product, @HeaderParam("Accept") String accept, Context context);

        @Post("/lro/error/postasync/retry/invalidheader")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LrosaDsPostAsyncRelativeRetryInvalidHeaderResponse> postAsyncRelativeRetryInvalidHeader(@HostParam("$host") String host, @BodyParam("application/json") Product product, @HeaderParam("Accept") String accept, Context context);

        @Post("/lro/error/postasync/retry/invalidjsonpolling")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LrosaDsPostAsyncRelativeRetryInvalidJsonPollingResponse> postAsyncRelativeRetryInvalidJsonPolling(@HostParam("$host") String host, @BodyParam("application/json") Product product, @HeaderParam("Accept") String accept, Context context);
    }

    /**
     * Long running put request, service returns a 400 to the initial request.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Product>> putNonRetry400WithResponseAsync(Product product, Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.putNonRetry400(this.client.getHost(), product, accept, context));
    }

    /**
     * Long running put request, service returns a 400 to the initial request.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PollerFlux<Product, Product> beginPutNonRetry400Async(Product product, Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.putNonRetry400WithResponseAsync(product, pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Product>() { }, new TypeReference<Product>() { });
    }

    /**
     * Long running put request, service returns a 400 to the initial request.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public SyncPoller<Product, Product> beginPutNonRetry400(Product product, Duration pollInterval) {
        return this.beginPutNonRetry400Async(product, pollInterval).getSyncPoller();
    }

    /**
     * Long running put request, service returns a Product with 'ProvisioningState' = 'Creating' and 201 response code.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Product>> putNonRetry201Creating400WithResponseAsync(Product product, Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.putNonRetry201Creating400(this.client.getHost(), product, accept, context));
    }

    /**
     * Long running put request, service returns a Product with 'ProvisioningState' = 'Creating' and 201 response code.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PollerFlux<Product, Product> beginPutNonRetry201Creating400Async(Product product, Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.putNonRetry201Creating400WithResponseAsync(product, pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Product>() { }, new TypeReference<Product>() { });
    }

    /**
     * Long running put request, service returns a Product with 'ProvisioningState' = 'Creating' and 201 response code.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public SyncPoller<Product, Product> beginPutNonRetry201Creating400(Product product, Duration pollInterval) {
        return this.beginPutNonRetry201Creating400Async(product, pollInterval).getSyncPoller();
    }

    /**
     * Long running put request, service returns a Product with 'ProvisioningState' = 'Creating' and 201 response code.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Product>> putNonRetry201Creating400InvalidJsonWithResponseAsync(Product product, Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.putNonRetry201Creating400InvalidJson(this.client.getHost(), product, accept, context));
    }

    /**
     * Long running put request, service returns a Product with 'ProvisioningState' = 'Creating' and 201 response code.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PollerFlux<Product, Product> beginPutNonRetry201Creating400InvalidJsonAsync(Product product, Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.putNonRetry201Creating400InvalidJsonWithResponseAsync(product, pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Product>() { }, new TypeReference<Product>() { });
    }

    /**
     * Long running put request, service returns a Product with 'ProvisioningState' = 'Creating' and 201 response code.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public SyncPoller<Product, Product> beginPutNonRetry201Creating400InvalidJson(Product product, Duration pollInterval) {
        return this.beginPutNonRetry201Creating400InvalidJsonAsync(product, pollInterval).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 200 with ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation status.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LrosaDsPutAsyncRelativeRetry400Response> putAsyncRelativeRetry400WithResponseAsync(Product product, Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.putAsyncRelativeRetry400(this.client.getHost(), product, accept, context));
    }

    /**
     * Long running put request, service returns a 200 with ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation status.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PollerFlux<Product, Product> beginPutAsyncRelativeRetry400Async(Product product, Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.putAsyncRelativeRetry400WithResponseAsync(product, pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Product>() { }, new TypeReference<Product>() { });
    }

    /**
     * Long running put request, service returns a 200 with ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation status.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public SyncPoller<Product, Product> beginPutAsyncRelativeRetry400(Product product, Duration pollInterval) {
        return this.beginPutAsyncRelativeRetry400Async(product, pollInterval).getSyncPoller();
    }

    /**
     * Long running delete request, service returns a 400 with an error body.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LrosaDsDeleteNonRetry400Response> deleteNonRetry400WithResponseAsync(Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.deleteNonRetry400(this.client.getHost(), accept, context));
    }

    /**
     * Long running delete request, service returns a 400 with an error body.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public PollerFlux<Void, Void> beginDeleteNonRetry400Async(Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.deleteNonRetry400WithResponseAsync(pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Void>() { }, new TypeReference<Void>() { });
    }

    /**
     * Long running delete request, service returns a 400 with an error body.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public SyncPoller<Void, Void> beginDeleteNonRetry400(Duration pollInterval) {
        return this.beginDeleteNonRetry400Async(pollInterval).getSyncPoller();
    }

    /**
     * Long running delete request, service returns a 202 with a location header.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LrosaDsDelete202NonRetry400Response> delete202NonRetry400WithResponseAsync(Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.delete202NonRetry400(this.client.getHost(), accept, context));
    }

    /**
     * Long running delete request, service returns a 202 with a location header.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public PollerFlux<Void, Void> beginDelete202NonRetry400Async(Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.delete202NonRetry400WithResponseAsync(pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Void>() { }, new TypeReference<Void>() { });
    }

    /**
     * Long running delete request, service returns a 202 with a location header.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public SyncPoller<Void, Void> beginDelete202NonRetry400(Duration pollInterval) {
        return this.beginDelete202NonRetry400Async(pollInterval).getSyncPoller();
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
    public Mono<LrosaDsDeleteAsyncRelativeRetry400Response> deleteAsyncRelativeRetry400WithResponseAsync(Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.deleteAsyncRelativeRetry400(this.client.getHost(), accept, context));
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
    public PollerFlux<Void, Void> beginDeleteAsyncRelativeRetry400Async(Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.deleteAsyncRelativeRetry400WithResponseAsync(pollInterval),
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
    public SyncPoller<Void, Void> beginDeleteAsyncRelativeRetry400(Duration pollInterval) {
        return this.beginDeleteAsyncRelativeRetry400Async(pollInterval).getSyncPoller();
    }

    /**
     * Long running post request, service returns a 400 with no error body.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LrosaDsPostNonRetry400Response> postNonRetry400WithResponseAsync(Product product, Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.postNonRetry400(this.client.getHost(), product, accept, context));
    }

    /**
     * Long running post request, service returns a 400 with no error body.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public PollerFlux<Void, Void> beginPostNonRetry400Async(Product product, Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.postNonRetry400WithResponseAsync(product, pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Void>() { }, new TypeReference<Void>() { });
    }

    /**
     * Long running post request, service returns a 400 with no error body.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public SyncPoller<Void, Void> beginPostNonRetry400(Product product, Duration pollInterval) {
        return this.beginPostNonRetry400Async(product, pollInterval).getSyncPoller();
    }

    /**
     * Long running post request, service returns a 202 with a location header.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LrosaDsPost202NonRetry400Response> post202NonRetry400WithResponseAsync(Product product, Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.post202NonRetry400(this.client.getHost(), product, accept, context));
    }

    /**
     * Long running post request, service returns a 202 with a location header.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public PollerFlux<Void, Void> beginPost202NonRetry400Async(Product product, Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.post202NonRetry400WithResponseAsync(product, pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Void>() { }, new TypeReference<Void>() { });
    }

    /**
     * Long running post request, service returns a 202 with a location header.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public SyncPoller<Void, Void> beginPost202NonRetry400(Product product, Duration pollInterval) {
        return this.beginPost202NonRetry400Async(product, pollInterval).getSyncPoller();
    }

    /**
     * Long running post request, service returns a 202 to the initial request Poll the endpoint indicated in the Azure-AsyncOperation header for operation status.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LrosaDsPostAsyncRelativeRetry400Response> postAsyncRelativeRetry400WithResponseAsync(Product product, Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.postAsyncRelativeRetry400(this.client.getHost(), product, accept, context));
    }

    /**
     * Long running post request, service returns a 202 to the initial request Poll the endpoint indicated in the Azure-AsyncOperation header for operation status.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public PollerFlux<Void, Void> beginPostAsyncRelativeRetry400Async(Product product, Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.postAsyncRelativeRetry400WithResponseAsync(product, pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Void>() { }, new TypeReference<Void>() { });
    }

    /**
     * Long running post request, service returns a 202 to the initial request Poll the endpoint indicated in the Azure-AsyncOperation header for operation status.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public SyncPoller<Void, Void> beginPostAsyncRelativeRetry400(Product product, Duration pollInterval) {
        return this.beginPostAsyncRelativeRetry400Async(product, pollInterval).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 201 to the initial request with no payload.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Product>> putError201NoProvisioningStatePayloadWithResponseAsync(Product product, Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.putError201NoProvisioningStatePayload(this.client.getHost(), product, accept, context));
    }

    /**
     * Long running put request, service returns a 201 to the initial request with no payload.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PollerFlux<Product, Product> beginPutError201NoProvisioningStatePayloadAsync(Product product, Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.putError201NoProvisioningStatePayloadWithResponseAsync(product, pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Product>() { }, new TypeReference<Product>() { });
    }

    /**
     * Long running put request, service returns a 201 to the initial request with no payload.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public SyncPoller<Product, Product> beginPutError201NoProvisioningStatePayload(Product product, Duration pollInterval) {
        return this.beginPutError201NoProvisioningStatePayloadAsync(product, pollInterval).getSyncPoller();
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
    public Mono<LrosaDsPutAsyncRelativeRetryNoStatusResponse> putAsyncRelativeRetryNoStatusWithResponseAsync(Product product, Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.putAsyncRelativeRetryNoStatus(this.client.getHost(), product, accept, context));
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
    public PollerFlux<Product, Product> beginPutAsyncRelativeRetryNoStatusAsync(Product product, Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.putAsyncRelativeRetryNoStatusWithResponseAsync(product, pollInterval),
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
    public SyncPoller<Product, Product> beginPutAsyncRelativeRetryNoStatus(Product product, Duration pollInterval) {
        return this.beginPutAsyncRelativeRetryNoStatusAsync(product, pollInterval).getSyncPoller();
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
    public Mono<LrosaDsPutAsyncRelativeRetryNoStatusPayloadResponse> putAsyncRelativeRetryNoStatusPayloadWithResponseAsync(Product product, Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.putAsyncRelativeRetryNoStatusPayload(this.client.getHost(), product, accept, context));
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
    public PollerFlux<Product, Product> beginPutAsyncRelativeRetryNoStatusPayloadAsync(Product product, Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.putAsyncRelativeRetryNoStatusPayloadWithResponseAsync(product, pollInterval),
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
    public SyncPoller<Product, Product> beginPutAsyncRelativeRetryNoStatusPayload(Product product, Duration pollInterval) {
        return this.beginPutAsyncRelativeRetryNoStatusPayloadAsync(product, pollInterval).getSyncPoller();
    }

    /**
     * Long running delete request, service returns a 204 to the initial request, indicating success.
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
     * Long running delete request, service returns a 204 to the initial request, indicating success.
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
     * Long running delete request, service returns a 204 to the initial request, indicating success.
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
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the Azure-AsyncOperation header for operation status.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LrosaDsDeleteAsyncRelativeRetryNoStatusResponse> deleteAsyncRelativeRetryNoStatusWithResponseAsync(Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.deleteAsyncRelativeRetryNoStatus(this.client.getHost(), accept, context));
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
    public PollerFlux<Void, Void> beginDeleteAsyncRelativeRetryNoStatusAsync(Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.deleteAsyncRelativeRetryNoStatusWithResponseAsync(pollInterval),
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
    public SyncPoller<Void, Void> beginDeleteAsyncRelativeRetryNoStatus(Duration pollInterval) {
        return this.beginDeleteAsyncRelativeRetryNoStatusAsync(pollInterval).getSyncPoller();
    }

    /**
     * Long running post request, service returns a 202 to the initial request, without a location header.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LrosaDsPost202NoLocationResponse> post202NoLocationWithResponseAsync(Product product, Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.post202NoLocation(this.client.getHost(), product, accept, context));
    }

    /**
     * Long running post request, service returns a 202 to the initial request, without a location header.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public PollerFlux<Void, Void> beginPost202NoLocationAsync(Product product, Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.post202NoLocationWithResponseAsync(product, pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Void>() { }, new TypeReference<Void>() { });
    }

    /**
     * Long running post request, service returns a 202 to the initial request, without a location header.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public SyncPoller<Void, Void> beginPost202NoLocation(Product product, Duration pollInterval) {
        return this.beginPost202NoLocationAsync(product, pollInterval).getSyncPoller();
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
    public Mono<LrosaDsPostAsyncRelativeRetryNoPayloadResponse> postAsyncRelativeRetryNoPayloadWithResponseAsync(Product product, Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.postAsyncRelativeRetryNoPayload(this.client.getHost(), product, accept, context));
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
    public PollerFlux<Void, Void> beginPostAsyncRelativeRetryNoPayloadAsync(Product product, Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.postAsyncRelativeRetryNoPayloadWithResponseAsync(product, pollInterval),
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
    public SyncPoller<Void, Void> beginPostAsyncRelativeRetryNoPayload(Product product, Duration pollInterval) {
        return this.beginPostAsyncRelativeRetryNoPayloadAsync(product, pollInterval).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that is not a valid json.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Product>> put200InvalidJsonWithResponseAsync(Product product, Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.put200InvalidJson(this.client.getHost(), product, accept, context));
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that is not a valid json.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PollerFlux<Product, Product> beginPut200InvalidJsonAsync(Product product, Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.put200InvalidJsonWithResponseAsync(product, pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Product>() { }, new TypeReference<Product>() { });
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that is not a valid json.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public SyncPoller<Product, Product> beginPut200InvalidJson(Product product, Duration pollInterval) {
        return this.beginPut200InvalidJsonAsync(product, pollInterval).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains ProvisioningState=’Creating’. The endpoint indicated in the Azure-AsyncOperation header is invalid.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LrosaDsPutAsyncRelativeRetryInvalidHeaderResponse> putAsyncRelativeRetryInvalidHeaderWithResponseAsync(Product product, Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.putAsyncRelativeRetryInvalidHeader(this.client.getHost(), product, accept, context));
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains ProvisioningState=’Creating’. The endpoint indicated in the Azure-AsyncOperation header is invalid.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PollerFlux<Product, Product> beginPutAsyncRelativeRetryInvalidHeaderAsync(Product product, Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.putAsyncRelativeRetryInvalidHeaderWithResponseAsync(product, pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Product>() { }, new TypeReference<Product>() { });
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains ProvisioningState=’Creating’. The endpoint indicated in the Azure-AsyncOperation header is invalid.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public SyncPoller<Product, Product> beginPutAsyncRelativeRetryInvalidHeader(Product product, Duration pollInterval) {
        return this.beginPutAsyncRelativeRetryInvalidHeaderAsync(product, pollInterval).getSyncPoller();
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
    public Mono<LrosaDsPutAsyncRelativeRetryInvalidJsonPollingResponse> putAsyncRelativeRetryInvalidJsonPollingWithResponseAsync(Product product, Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.putAsyncRelativeRetryInvalidJsonPolling(this.client.getHost(), product, accept, context));
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
    public PollerFlux<Product, Product> beginPutAsyncRelativeRetryInvalidJsonPollingAsync(Product product, Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.putAsyncRelativeRetryInvalidJsonPollingWithResponseAsync(product, pollInterval),
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
    public SyncPoller<Product, Product> beginPutAsyncRelativeRetryInvalidJsonPolling(Product product, Duration pollInterval) {
        return this.beginPutAsyncRelativeRetryInvalidJsonPollingAsync(product, pollInterval).getSyncPoller();
    }

    /**
     * Long running delete request, service returns a 202 to the initial request receing a reponse with an invalid 'Location' and 'Retry-After' headers.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LrosaDsDelete202RetryInvalidHeaderResponse> delete202RetryInvalidHeaderWithResponseAsync(Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.delete202RetryInvalidHeader(this.client.getHost(), accept, context));
    }

    /**
     * Long running delete request, service returns a 202 to the initial request receing a reponse with an invalid 'Location' and 'Retry-After' headers.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public PollerFlux<Void, Void> beginDelete202RetryInvalidHeaderAsync(Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.delete202RetryInvalidHeaderWithResponseAsync(pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Void>() { }, new TypeReference<Void>() { });
    }

    /**
     * Long running delete request, service returns a 202 to the initial request receing a reponse with an invalid 'Location' and 'Retry-After' headers.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public SyncPoller<Void, Void> beginDelete202RetryInvalidHeader(Duration pollInterval) {
        return this.beginDelete202RetryInvalidHeaderAsync(pollInterval).getSyncPoller();
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. The endpoint indicated in the Azure-AsyncOperation header is invalid.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LrosaDsDeleteAsyncRelativeRetryInvalidHeaderResponse> deleteAsyncRelativeRetryInvalidHeaderWithResponseAsync(Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.deleteAsyncRelativeRetryInvalidHeader(this.client.getHost(), accept, context));
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. The endpoint indicated in the Azure-AsyncOperation header is invalid.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public PollerFlux<Void, Void> beginDeleteAsyncRelativeRetryInvalidHeaderAsync(Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.deleteAsyncRelativeRetryInvalidHeaderWithResponseAsync(pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Void>() { }, new TypeReference<Void>() { });
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. The endpoint indicated in the Azure-AsyncOperation header is invalid.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public SyncPoller<Void, Void> beginDeleteAsyncRelativeRetryInvalidHeader(Duration pollInterval) {
        return this.beginDeleteAsyncRelativeRetryInvalidHeaderAsync(pollInterval).getSyncPoller();
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
    public Mono<LrosaDsDeleteAsyncRelativeRetryInvalidJsonPollingResponse> deleteAsyncRelativeRetryInvalidJsonPollingWithResponseAsync(Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.deleteAsyncRelativeRetryInvalidJsonPolling(this.client.getHost(), accept, context));
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
    public PollerFlux<Void, Void> beginDeleteAsyncRelativeRetryInvalidJsonPollingAsync(Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.deleteAsyncRelativeRetryInvalidJsonPollingWithResponseAsync(pollInterval),
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
    public SyncPoller<Void, Void> beginDeleteAsyncRelativeRetryInvalidJsonPolling(Duration pollInterval) {
        return this.beginDeleteAsyncRelativeRetryInvalidJsonPollingAsync(pollInterval).getSyncPoller();
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with invalid 'Location' and 'Retry-After' headers.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LrosaDsPost202RetryInvalidHeaderResponse> post202RetryInvalidHeaderWithResponseAsync(Product product, Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.post202RetryInvalidHeader(this.client.getHost(), product, accept, context));
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with invalid 'Location' and 'Retry-After' headers.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public PollerFlux<Void, Void> beginPost202RetryInvalidHeaderAsync(Product product, Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.post202RetryInvalidHeaderWithResponseAsync(product, pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Void>() { }, new TypeReference<Void>() { });
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with invalid 'Location' and 'Retry-After' headers.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public SyncPoller<Void, Void> beginPost202RetryInvalidHeader(Product product, Duration pollInterval) {
        return this.beginPost202RetryInvalidHeaderAsync(product, pollInterval).getSyncPoller();
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains ProvisioningState=’Creating’. The endpoint indicated in the Azure-AsyncOperation header is invalid.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LrosaDsPostAsyncRelativeRetryInvalidHeaderResponse> postAsyncRelativeRetryInvalidHeaderWithResponseAsync(Product product, Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.postAsyncRelativeRetryInvalidHeader(this.client.getHost(), product, accept, context));
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains ProvisioningState=’Creating’. The endpoint indicated in the Azure-AsyncOperation header is invalid.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public PollerFlux<Void, Void> beginPostAsyncRelativeRetryInvalidHeaderAsync(Product product, Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.postAsyncRelativeRetryInvalidHeaderWithResponseAsync(product, pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Void>() { }, new TypeReference<Void>() { });
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains ProvisioningState=’Creating’. The endpoint indicated in the Azure-AsyncOperation header is invalid.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public SyncPoller<Void, Void> beginPostAsyncRelativeRetryInvalidHeader(Product product, Duration pollInterval) {
        return this.beginPostAsyncRelativeRetryInvalidHeaderAsync(product, pollInterval).getSyncPoller();
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
    public Mono<LrosaDsPostAsyncRelativeRetryInvalidJsonPollingResponse> postAsyncRelativeRetryInvalidJsonPollingWithResponseAsync(Product product, Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.postAsyncRelativeRetryInvalidJsonPolling(this.client.getHost(), product, accept, context));
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
    public PollerFlux<Void, Void> beginPostAsyncRelativeRetryInvalidJsonPollingAsync(Product product, Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.postAsyncRelativeRetryInvalidJsonPollingWithResponseAsync(product, pollInterval),
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
    public SyncPoller<Void, Void> beginPostAsyncRelativeRetryInvalidJsonPolling(Product product, Duration pollInterval) {
        return this.beginPostAsyncRelativeRetryInvalidJsonPollingAsync(product, pollInterval).getSyncPoller();
    }
}
