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
import com.azure.core.util.BinaryData;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import com.azure.core.util.polling.ChainedPollingStrategy;
import com.azure.core.util.polling.LocationPollingStrategy;
import com.azure.core.util.polling.OperationResourcePollingStrategy;
import com.azure.core.util.polling.PollerFlux;
import com.azure.core.util.polling.StatusCheckPollingStrategy;
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

/** An instance of this class provides access to all the operations defined in LrosaDs. */
public final class LrosaDs {
    /** The proxy service used to perform REST calls. */
    private final LrosaDsService service;

    /** The service client containing this operation class. */
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
     * The interface defining all the services for AutoRestLongRunningOperationTestServiceLrosaDs to be used by the
     * proxy service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestLongRunningO")
    private interface LrosaDsService {
        @Put("/lro/nonretryerror/put/400")
        @ExpectedResponses({200, 201})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<Response<Product>> putNonRetry400(
                @HostParam("$host") String host,
                @BodyParam("application/json") Product product,
                @HeaderParam("Accept") String accept,
                Context context);

        @Put("/lro/nonretryerror/put/201/creating/400")
        @ExpectedResponses({200, 201})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<Response<Product>> putNonRetry201Creating400(
                @HostParam("$host") String host,
                @BodyParam("application/json") Product product,
                @HeaderParam("Accept") String accept,
                Context context);

        @Put("/lro/nonretryerror/put/201/creating/400/invalidjson")
        @ExpectedResponses({200, 201})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<Response<Product>> putNonRetry201Creating400InvalidJson(
                @HostParam("$host") String host,
                @BodyParam("application/json") Product product,
                @HeaderParam("Accept") String accept,
                Context context);

        @Put("/lro/nonretryerror/putasync/retry/400")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LrosaDsPutAsyncRelativeRetry400Response> putAsyncRelativeRetry400(
                @HostParam("$host") String host,
                @BodyParam("application/json") Product product,
                @HeaderParam("Accept") String accept,
                Context context);

        @Delete("/lro/nonretryerror/delete/400")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LrosaDsDeleteNonRetry400Response> deleteNonRetry400(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Delete("/lro/nonretryerror/delete/202/retry/400")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LrosaDsDelete202NonRetry400Response> delete202NonRetry400(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Delete("/lro/nonretryerror/deleteasync/retry/400")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LrosaDsDeleteAsyncRelativeRetry400Response> deleteAsyncRelativeRetry400(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Post("/lro/nonretryerror/post/400")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LrosaDsPostNonRetry400Response> postNonRetry400(
                @HostParam("$host") String host,
                @BodyParam("application/json") Product product,
                @HeaderParam("Accept") String accept,
                Context context);

        @Post("/lro/nonretryerror/post/202/retry/400")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LrosaDsPost202NonRetry400Response> post202NonRetry400(
                @HostParam("$host") String host,
                @BodyParam("application/json") Product product,
                @HeaderParam("Accept") String accept,
                Context context);

        @Post("/lro/nonretryerror/postasync/retry/400")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LrosaDsPostAsyncRelativeRetry400Response> postAsyncRelativeRetry400(
                @HostParam("$host") String host,
                @BodyParam("application/json") Product product,
                @HeaderParam("Accept") String accept,
                Context context);

        @Put("/lro/error/put/201/noprovisioningstatepayload")
        @ExpectedResponses({200, 201})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<Response<Product>> putError201NoProvisioningStatePayload(
                @HostParam("$host") String host,
                @BodyParam("application/json") Product product,
                @HeaderParam("Accept") String accept,
                Context context);

        @Put("/lro/error/putasync/retry/nostatus")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LrosaDsPutAsyncRelativeRetryNoStatusResponse> putAsyncRelativeRetryNoStatus(
                @HostParam("$host") String host,
                @BodyParam("application/json") Product product,
                @HeaderParam("Accept") String accept,
                Context context);

        @Put("/lro/error/putasync/retry/nostatuspayload")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LrosaDsPutAsyncRelativeRetryNoStatusPayloadResponse> putAsyncRelativeRetryNoStatusPayload(
                @HostParam("$host") String host,
                @BodyParam("application/json") Product product,
                @HeaderParam("Accept") String accept,
                Context context);

        @Delete("/lro/error/delete/204/nolocation")
        @ExpectedResponses({204})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<Response<Void>> delete204Succeeded(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Delete("/lro/error/deleteasync/retry/nostatus")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LrosaDsDeleteAsyncRelativeRetryNoStatusResponse> deleteAsyncRelativeRetryNoStatus(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Post("/lro/error/post/202/nolocation")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LrosaDsPost202NoLocationResponse> post202NoLocation(
                @HostParam("$host") String host,
                @BodyParam("application/json") Product product,
                @HeaderParam("Accept") String accept,
                Context context);

        @Post("/lro/error/postasync/retry/nopayload")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LrosaDsPostAsyncRelativeRetryNoPayloadResponse> postAsyncRelativeRetryNoPayload(
                @HostParam("$host") String host,
                @BodyParam("application/json") Product product,
                @HeaderParam("Accept") String accept,
                Context context);

        @Put("/lro/error/put/200/invalidjson")
        @ExpectedResponses({200, 204})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<Response<Product>> put200InvalidJson(
                @HostParam("$host") String host,
                @BodyParam("application/json") Product product,
                @HeaderParam("Accept") String accept,
                Context context);

        @Put("/lro/error/putasync/retry/invalidheader")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LrosaDsPutAsyncRelativeRetryInvalidHeaderResponse> putAsyncRelativeRetryInvalidHeader(
                @HostParam("$host") String host,
                @BodyParam("application/json") Product product,
                @HeaderParam("Accept") String accept,
                Context context);

        @Put("/lro/error/putasync/retry/invalidjsonpolling")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LrosaDsPutAsyncRelativeRetryInvalidJsonPollingResponse> putAsyncRelativeRetryInvalidJsonPolling(
                @HostParam("$host") String host,
                @BodyParam("application/json") Product product,
                @HeaderParam("Accept") String accept,
                Context context);

        @Delete("/lro/error/delete/202/retry/invalidheader")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LrosaDsDelete202RetryInvalidHeaderResponse> delete202RetryInvalidHeader(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Delete("/lro/error/deleteasync/retry/invalidheader")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LrosaDsDeleteAsyncRelativeRetryInvalidHeaderResponse> deleteAsyncRelativeRetryInvalidHeader(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Delete("/lro/error/deleteasync/retry/invalidjsonpolling")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LrosaDsDeleteAsyncRelativeRetryInvalidJsonPollingResponse> deleteAsyncRelativeRetryInvalidJsonPolling(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Post("/lro/error/post/202/retry/invalidheader")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LrosaDsPost202RetryInvalidHeaderResponse> post202RetryInvalidHeader(
                @HostParam("$host") String host,
                @BodyParam("application/json") Product product,
                @HeaderParam("Accept") String accept,
                Context context);

        @Post("/lro/error/postasync/retry/invalidheader")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LrosaDsPostAsyncRelativeRetryInvalidHeaderResponse> postAsyncRelativeRetryInvalidHeader(
                @HostParam("$host") String host,
                @BodyParam("application/json") Product product,
                @HeaderParam("Accept") String accept,
                Context context);

        @Post("/lro/error/postasync/retry/invalidjsonpolling")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LrosaDsPostAsyncRelativeRetryInvalidJsonPollingResponse> postAsyncRelativeRetryInvalidJsonPolling(
                @HostParam("$host") String host,
                @BodyParam("application/json") Product product,
                @HeaderParam("Accept") String accept,
                Context context);
    }

    /**
     * Long running put request, service returns a 400 to the initial request.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Product>> putNonRetry400WithResponseAsync(Product product) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
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
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<Product, Product> beginPutNonRetry400Async(Product product) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.putNonRetry400WithResponseAsync(product),
                new ChainedPollingStrategy<>(
                        java.util.Arrays.asList(
                                new OperationResourcePollingStrategy<>(
                                        this.client.getHttpPipeline(), null, "Azure-AsyncOperation"),
                                new LocationPollingStrategy<>(this.client.getHttpPipeline()),
                                new StatusCheckPollingStrategy<>())),
                new TypeReferenceProduct(),
                new TypeReferenceProduct());
    }

    /**
     * Long running put request, service returns a 400 to the initial request.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<Product, Product> beginPutNonRetry400(Product product) {
        return this.beginPutNonRetry400Async(product).getSyncPoller();
    }

    /**
     * Long running put request, service returns a Product with 'ProvisioningState' = 'Creating' and 201 response code.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Product>> putNonRetry201Creating400WithResponseAsync(Product product) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.putNonRetry201Creating400(this.client.getHost(), product, accept, context));
    }

    /**
     * Long running put request, service returns a Product with 'ProvisioningState' = 'Creating' and 201 response code.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<Product, Product> beginPutNonRetry201Creating400Async(Product product) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.putNonRetry201Creating400WithResponseAsync(product),
                new ChainedPollingStrategy<>(
                        java.util.Arrays.asList(
                                new OperationResourcePollingStrategy<>(
                                        this.client.getHttpPipeline(), null, "Azure-AsyncOperation"),
                                new LocationPollingStrategy<>(this.client.getHttpPipeline()),
                                new StatusCheckPollingStrategy<>())),
                new TypeReferenceProduct(),
                new TypeReferenceProduct());
    }

    /**
     * Long running put request, service returns a Product with 'ProvisioningState' = 'Creating' and 201 response code.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<Product, Product> beginPutNonRetry201Creating400(Product product) {
        return this.beginPutNonRetry201Creating400Async(product).getSyncPoller();
    }

    /**
     * Long running put request, service returns a Product with 'ProvisioningState' = 'Creating' and 201 response code.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Product>> putNonRetry201Creating400InvalidJsonWithResponseAsync(Product product) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context ->
                        service.putNonRetry201Creating400InvalidJson(this.client.getHost(), product, accept, context));
    }

    /**
     * Long running put request, service returns a Product with 'ProvisioningState' = 'Creating' and 201 response code.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<Product, Product> beginPutNonRetry201Creating400InvalidJsonAsync(Product product) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.putNonRetry201Creating400InvalidJsonWithResponseAsync(product),
                new ChainedPollingStrategy<>(
                        java.util.Arrays.asList(
                                new OperationResourcePollingStrategy<>(
                                        this.client.getHttpPipeline(), null, "Azure-AsyncOperation"),
                                new LocationPollingStrategy<>(this.client.getHttpPipeline()),
                                new StatusCheckPollingStrategy<>())),
                new TypeReferenceProduct(),
                new TypeReferenceProduct());
    }

    /**
     * Long running put request, service returns a Product with 'ProvisioningState' = 'Creating' and 201 response code.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<Product, Product> beginPutNonRetry201Creating400InvalidJson(Product product) {
        return this.beginPutNonRetry201Creating400InvalidJsonAsync(product).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 200 with ProvisioningState=’Creating’. Poll the endpoint indicated in
     * the Azure-AsyncOperation header for operation status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LrosaDsPutAsyncRelativeRetry400Response> putAsyncRelativeRetry400WithResponseAsync(Product product) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.putAsyncRelativeRetry400(this.client.getHost(), product, accept, context));
    }

    /**
     * Long running put request, service returns a 200 with ProvisioningState=’Creating’. Poll the endpoint indicated in
     * the Azure-AsyncOperation header for operation status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<Product, Product> beginPutAsyncRelativeRetry400Async(Product product) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.putAsyncRelativeRetry400WithResponseAsync(product),
                new ChainedPollingStrategy<>(
                        java.util.Arrays.asList(
                                new OperationResourcePollingStrategy<>(
                                        this.client.getHttpPipeline(), null, "Azure-AsyncOperation"),
                                new LocationPollingStrategy<>(this.client.getHttpPipeline()),
                                new StatusCheckPollingStrategy<>())),
                new TypeReferenceProduct(),
                new TypeReferenceProduct());
    }

    /**
     * Long running put request, service returns a 200 with ProvisioningState=’Creating’. Poll the endpoint indicated in
     * the Azure-AsyncOperation header for operation status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<Product, Product> beginPutAsyncRelativeRetry400(Product product) {
        return this.beginPutAsyncRelativeRetry400Async(product).getSyncPoller();
    }

    /**
     * Long running delete request, service returns a 400 with an error body.
     *
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LrosaDsDeleteNonRetry400Response> deleteNonRetry400WithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.deleteNonRetry400(this.client.getHost(), accept, context));
    }

    /**
     * Long running delete request, service returns a 400 with an error body.
     *
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginDeleteNonRetry400Async() {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.deleteNonRetry400WithResponseAsync(),
                new ChainedPollingStrategy<>(
                        java.util.Arrays.asList(
                                new OperationResourcePollingStrategy<>(
                                        this.client.getHttpPipeline(), null, "Azure-AsyncOperation"),
                                new LocationPollingStrategy<>(this.client.getHttpPipeline()),
                                new StatusCheckPollingStrategy<>())),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running delete request, service returns a 400 with an error body.
     *
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginDeleteNonRetry400() {
        return this.beginDeleteNonRetry400Async().getSyncPoller();
    }

    /**
     * Long running delete request, service returns a 202 with a location header.
     *
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LrosaDsDelete202NonRetry400Response> delete202NonRetry400WithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.delete202NonRetry400(this.client.getHost(), accept, context));
    }

    /**
     * Long running delete request, service returns a 202 with a location header.
     *
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginDelete202NonRetry400Async() {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.delete202NonRetry400WithResponseAsync(),
                new ChainedPollingStrategy<>(
                        java.util.Arrays.asList(
                                new OperationResourcePollingStrategy<>(
                                        this.client.getHttpPipeline(), null, "Azure-AsyncOperation"),
                                new LocationPollingStrategy<>(this.client.getHttpPipeline()),
                                new StatusCheckPollingStrategy<>())),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running delete request, service returns a 202 with a location header.
     *
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginDelete202NonRetry400() {
        return this.beginDelete202NonRetry400Async().getSyncPoller();
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LrosaDsDeleteAsyncRelativeRetry400Response> deleteAsyncRelativeRetry400WithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.deleteAsyncRelativeRetry400(this.client.getHost(), accept, context));
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginDeleteAsyncRelativeRetry400Async() {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.deleteAsyncRelativeRetry400WithResponseAsync(),
                new ChainedPollingStrategy<>(
                        java.util.Arrays.asList(
                                new OperationResourcePollingStrategy<>(
                                        this.client.getHttpPipeline(), null, "Azure-AsyncOperation"),
                                new LocationPollingStrategy<>(this.client.getHttpPipeline()),
                                new StatusCheckPollingStrategy<>())),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginDeleteAsyncRelativeRetry400() {
        return this.beginDeleteAsyncRelativeRetry400Async().getSyncPoller();
    }

    /**
     * Long running post request, service returns a 400 with no error body.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LrosaDsPostNonRetry400Response> postNonRetry400WithResponseAsync(Product product) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.postNonRetry400(this.client.getHost(), product, accept, context));
    }

    /**
     * Long running post request, service returns a 400 with no error body.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginPostNonRetry400Async(Product product) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.postNonRetry400WithResponseAsync(product),
                new ChainedPollingStrategy<>(
                        java.util.Arrays.asList(
                                new OperationResourcePollingStrategy<>(
                                        this.client.getHttpPipeline(), null, "Azure-AsyncOperation"),
                                new LocationPollingStrategy<>(this.client.getHttpPipeline()),
                                new StatusCheckPollingStrategy<>())),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running post request, service returns a 400 with no error body.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginPostNonRetry400(Product product) {
        return this.beginPostNonRetry400Async(product).getSyncPoller();
    }

    /**
     * Long running post request, service returns a 202 with a location header.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LrosaDsPost202NonRetry400Response> post202NonRetry400WithResponseAsync(Product product) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.post202NonRetry400(this.client.getHost(), product, accept, context));
    }

    /**
     * Long running post request, service returns a 202 with a location header.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginPost202NonRetry400Async(Product product) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.post202NonRetry400WithResponseAsync(product),
                new ChainedPollingStrategy<>(
                        java.util.Arrays.asList(
                                new OperationResourcePollingStrategy<>(
                                        this.client.getHttpPipeline(), null, "Azure-AsyncOperation"),
                                new LocationPollingStrategy<>(this.client.getHttpPipeline()),
                                new StatusCheckPollingStrategy<>())),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running post request, service returns a 202 with a location header.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginPost202NonRetry400(Product product) {
        return this.beginPost202NonRetry400Async(product).getSyncPoller();
    }

    /**
     * Long running post request, service returns a 202 to the initial request Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LrosaDsPostAsyncRelativeRetry400Response> postAsyncRelativeRetry400WithResponseAsync(Product product) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.postAsyncRelativeRetry400(this.client.getHost(), product, accept, context));
    }

    /**
     * Long running post request, service returns a 202 to the initial request Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginPostAsyncRelativeRetry400Async(Product product) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.postAsyncRelativeRetry400WithResponseAsync(product),
                new ChainedPollingStrategy<>(
                        java.util.Arrays.asList(
                                new OperationResourcePollingStrategy<>(
                                        this.client.getHttpPipeline(), null, "Azure-AsyncOperation"),
                                new LocationPollingStrategy<>(this.client.getHttpPipeline()),
                                new StatusCheckPollingStrategy<>())),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running post request, service returns a 202 to the initial request Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginPostAsyncRelativeRetry400(Product product) {
        return this.beginPostAsyncRelativeRetry400Async(product).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 201 to the initial request with no payload.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Product>> putError201NoProvisioningStatePayloadWithResponseAsync(Product product) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context ->
                        service.putError201NoProvisioningStatePayload(this.client.getHost(), product, accept, context));
    }

    /**
     * Long running put request, service returns a 201 to the initial request with no payload.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<Product, Product> beginPutError201NoProvisioningStatePayloadAsync(Product product) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.putError201NoProvisioningStatePayloadWithResponseAsync(product),
                new ChainedPollingStrategy<>(
                        java.util.Arrays.asList(
                                new OperationResourcePollingStrategy<>(
                                        this.client.getHttpPipeline(), null, "Azure-AsyncOperation"),
                                new LocationPollingStrategy<>(this.client.getHttpPipeline()),
                                new StatusCheckPollingStrategy<>())),
                new TypeReferenceProduct(),
                new TypeReferenceProduct());
    }

    /**
     * Long running put request, service returns a 201 to the initial request with no payload.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<Product, Product> beginPutError201NoProvisioningStatePayload(Product product) {
        return this.beginPutError201NoProvisioningStatePayloadAsync(product).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LrosaDsPutAsyncRelativeRetryNoStatusResponse> putAsyncRelativeRetryNoStatusWithResponseAsync(
            Product product) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.putAsyncRelativeRetryNoStatus(this.client.getHost(), product, accept, context));
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<Product, Product> beginPutAsyncRelativeRetryNoStatusAsync(Product product) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.putAsyncRelativeRetryNoStatusWithResponseAsync(product),
                new ChainedPollingStrategy<>(
                        java.util.Arrays.asList(
                                new OperationResourcePollingStrategy<>(
                                        this.client.getHttpPipeline(), null, "Azure-AsyncOperation"),
                                new LocationPollingStrategy<>(this.client.getHttpPipeline()),
                                new StatusCheckPollingStrategy<>())),
                new TypeReferenceProduct(),
                new TypeReferenceProduct());
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<Product, Product> beginPutAsyncRelativeRetryNoStatus(Product product) {
        return this.beginPutAsyncRelativeRetryNoStatusAsync(product).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LrosaDsPutAsyncRelativeRetryNoStatusPayloadResponse>
            putAsyncRelativeRetryNoStatusPayloadWithResponseAsync(Product product) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context ->
                        service.putAsyncRelativeRetryNoStatusPayload(this.client.getHost(), product, accept, context));
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<Product, Product> beginPutAsyncRelativeRetryNoStatusPayloadAsync(Product product) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.putAsyncRelativeRetryNoStatusPayloadWithResponseAsync(product),
                new ChainedPollingStrategy<>(
                        java.util.Arrays.asList(
                                new OperationResourcePollingStrategy<>(
                                        this.client.getHttpPipeline(), null, "Azure-AsyncOperation"),
                                new LocationPollingStrategy<>(this.client.getHttpPipeline()),
                                new StatusCheckPollingStrategy<>())),
                new TypeReferenceProduct(),
                new TypeReferenceProduct());
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<Product, Product> beginPutAsyncRelativeRetryNoStatusPayload(Product product) {
        return this.beginPutAsyncRelativeRetryNoStatusPayloadAsync(product).getSyncPoller();
    }

    /**
     * Long running delete request, service returns a 204 to the initial request, indicating success.
     *
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> delete204SucceededWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.delete204Succeeded(this.client.getHost(), accept, context));
    }

    /**
     * Long running delete request, service returns a 204 to the initial request, indicating success.
     *
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginDelete204SucceededAsync() {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.delete204SucceededWithResponseAsync(),
                new ChainedPollingStrategy<>(
                        java.util.Arrays.asList(
                                new OperationResourcePollingStrategy<>(
                                        this.client.getHttpPipeline(), null, "Azure-AsyncOperation"),
                                new LocationPollingStrategy<>(this.client.getHttpPipeline()),
                                new StatusCheckPollingStrategy<>())),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running delete request, service returns a 204 to the initial request, indicating success.
     *
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginDelete204Succeeded() {
        return this.beginDelete204SucceededAsync().getSyncPoller();
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LrosaDsDeleteAsyncRelativeRetryNoStatusResponse> deleteAsyncRelativeRetryNoStatusWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.deleteAsyncRelativeRetryNoStatus(this.client.getHost(), accept, context));
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginDeleteAsyncRelativeRetryNoStatusAsync() {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.deleteAsyncRelativeRetryNoStatusWithResponseAsync(),
                new ChainedPollingStrategy<>(
                        java.util.Arrays.asList(
                                new OperationResourcePollingStrategy<>(
                                        this.client.getHttpPipeline(), null, "Azure-AsyncOperation"),
                                new LocationPollingStrategy<>(this.client.getHttpPipeline()),
                                new StatusCheckPollingStrategy<>())),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginDeleteAsyncRelativeRetryNoStatus() {
        return this.beginDeleteAsyncRelativeRetryNoStatusAsync().getSyncPoller();
    }

    /**
     * Long running post request, service returns a 202 to the initial request, without a location header.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LrosaDsPost202NoLocationResponse> post202NoLocationWithResponseAsync(Product product) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.post202NoLocation(this.client.getHost(), product, accept, context));
    }

    /**
     * Long running post request, service returns a 202 to the initial request, without a location header.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginPost202NoLocationAsync(Product product) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.post202NoLocationWithResponseAsync(product),
                new ChainedPollingStrategy<>(
                        java.util.Arrays.asList(
                                new OperationResourcePollingStrategy<>(
                                        this.client.getHttpPipeline(), null, "Azure-AsyncOperation"),
                                new LocationPollingStrategy<>(this.client.getHttpPipeline()),
                                new StatusCheckPollingStrategy<>())),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running post request, service returns a 202 to the initial request, without a location header.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginPost202NoLocation(Product product) {
        return this.beginPost202NoLocationAsync(product).getSyncPoller();
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LrosaDsPostAsyncRelativeRetryNoPayloadResponse> postAsyncRelativeRetryNoPayloadWithResponseAsync(
            Product product) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.postAsyncRelativeRetryNoPayload(this.client.getHost(), product, accept, context));
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginPostAsyncRelativeRetryNoPayloadAsync(Product product) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.postAsyncRelativeRetryNoPayloadWithResponseAsync(product),
                new ChainedPollingStrategy<>(
                        java.util.Arrays.asList(
                                new OperationResourcePollingStrategy<>(
                                        this.client.getHttpPipeline(), null, "Azure-AsyncOperation"),
                                new LocationPollingStrategy<>(this.client.getHttpPipeline()),
                                new StatusCheckPollingStrategy<>())),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginPostAsyncRelativeRetryNoPayload(Product product) {
        return this.beginPostAsyncRelativeRetryNoPayloadAsync(product).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that is not a valid json.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Product>> put200InvalidJsonWithResponseAsync(Product product) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.put200InvalidJson(this.client.getHost(), product, accept, context));
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that is not a valid json.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<Product, Product> beginPut200InvalidJsonAsync(Product product) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.put200InvalidJsonWithResponseAsync(product),
                new ChainedPollingStrategy<>(
                        java.util.Arrays.asList(
                                new OperationResourcePollingStrategy<>(
                                        this.client.getHttpPipeline(), null, "Azure-AsyncOperation"),
                                new LocationPollingStrategy<>(this.client.getHttpPipeline()),
                                new StatusCheckPollingStrategy<>())),
                new TypeReferenceProduct(),
                new TypeReferenceProduct());
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that is not a valid json.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<Product, Product> beginPut200InvalidJson(Product product) {
        return this.beginPut200InvalidJsonAsync(product).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. The endpoint indicated in the Azure-AsyncOperation header is invalid.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LrosaDsPutAsyncRelativeRetryInvalidHeaderResponse> putAsyncRelativeRetryInvalidHeaderWithResponseAsync(
            Product product) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.putAsyncRelativeRetryInvalidHeader(this.client.getHost(), product, accept, context));
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. The endpoint indicated in the Azure-AsyncOperation header is invalid.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<Product, Product> beginPutAsyncRelativeRetryInvalidHeaderAsync(Product product) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.putAsyncRelativeRetryInvalidHeaderWithResponseAsync(product),
                new ChainedPollingStrategy<>(
                        java.util.Arrays.asList(
                                new OperationResourcePollingStrategy<>(
                                        this.client.getHttpPipeline(), null, "Azure-AsyncOperation"),
                                new LocationPollingStrategy<>(this.client.getHttpPipeline()),
                                new StatusCheckPollingStrategy<>())),
                new TypeReferenceProduct(),
                new TypeReferenceProduct());
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. The endpoint indicated in the Azure-AsyncOperation header is invalid.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<Product, Product> beginPutAsyncRelativeRetryInvalidHeader(Product product) {
        return this.beginPutAsyncRelativeRetryInvalidHeaderAsync(product).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LrosaDsPutAsyncRelativeRetryInvalidJsonPollingResponse>
            putAsyncRelativeRetryInvalidJsonPollingWithResponseAsync(Product product) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context ->
                        service.putAsyncRelativeRetryInvalidJsonPolling(
                                this.client.getHost(), product, accept, context));
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<Product, Product> beginPutAsyncRelativeRetryInvalidJsonPollingAsync(Product product) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.putAsyncRelativeRetryInvalidJsonPollingWithResponseAsync(product),
                new ChainedPollingStrategy<>(
                        java.util.Arrays.asList(
                                new OperationResourcePollingStrategy<>(
                                        this.client.getHttpPipeline(), null, "Azure-AsyncOperation"),
                                new LocationPollingStrategy<>(this.client.getHttpPipeline()),
                                new StatusCheckPollingStrategy<>())),
                new TypeReferenceProduct(),
                new TypeReferenceProduct());
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<Product, Product> beginPutAsyncRelativeRetryInvalidJsonPolling(Product product) {
        return this.beginPutAsyncRelativeRetryInvalidJsonPollingAsync(product).getSyncPoller();
    }

    /**
     * Long running delete request, service returns a 202 to the initial request receing a reponse with an invalid
     * 'Location' and 'Retry-After' headers.
     *
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LrosaDsDelete202RetryInvalidHeaderResponse> delete202RetryInvalidHeaderWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.delete202RetryInvalidHeader(this.client.getHost(), accept, context));
    }

    /**
     * Long running delete request, service returns a 202 to the initial request receing a reponse with an invalid
     * 'Location' and 'Retry-After' headers.
     *
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginDelete202RetryInvalidHeaderAsync() {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.delete202RetryInvalidHeaderWithResponseAsync(),
                new ChainedPollingStrategy<>(
                        java.util.Arrays.asList(
                                new OperationResourcePollingStrategy<>(
                                        this.client.getHttpPipeline(), null, "Azure-AsyncOperation"),
                                new LocationPollingStrategy<>(this.client.getHttpPipeline()),
                                new StatusCheckPollingStrategy<>())),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running delete request, service returns a 202 to the initial request receing a reponse with an invalid
     * 'Location' and 'Retry-After' headers.
     *
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginDelete202RetryInvalidHeader() {
        return this.beginDelete202RetryInvalidHeaderAsync().getSyncPoller();
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. The endpoint indicated in the
     * Azure-AsyncOperation header is invalid.
     *
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LrosaDsDeleteAsyncRelativeRetryInvalidHeaderResponse>
            deleteAsyncRelativeRetryInvalidHeaderWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.deleteAsyncRelativeRetryInvalidHeader(this.client.getHost(), accept, context));
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. The endpoint indicated in the
     * Azure-AsyncOperation header is invalid.
     *
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginDeleteAsyncRelativeRetryInvalidHeaderAsync() {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.deleteAsyncRelativeRetryInvalidHeaderWithResponseAsync(),
                new ChainedPollingStrategy<>(
                        java.util.Arrays.asList(
                                new OperationResourcePollingStrategy<>(
                                        this.client.getHttpPipeline(), null, "Azure-AsyncOperation"),
                                new LocationPollingStrategy<>(this.client.getHttpPipeline()),
                                new StatusCheckPollingStrategy<>())),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. The endpoint indicated in the
     * Azure-AsyncOperation header is invalid.
     *
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginDeleteAsyncRelativeRetryInvalidHeader() {
        return this.beginDeleteAsyncRelativeRetryInvalidHeaderAsync().getSyncPoller();
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LrosaDsDeleteAsyncRelativeRetryInvalidJsonPollingResponse>
            deleteAsyncRelativeRetryInvalidJsonPollingWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.deleteAsyncRelativeRetryInvalidJsonPolling(this.client.getHost(), accept, context));
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginDeleteAsyncRelativeRetryInvalidJsonPollingAsync() {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.deleteAsyncRelativeRetryInvalidJsonPollingWithResponseAsync(),
                new ChainedPollingStrategy<>(
                        java.util.Arrays.asList(
                                new OperationResourcePollingStrategy<>(
                                        this.client.getHttpPipeline(), null, "Azure-AsyncOperation"),
                                new LocationPollingStrategy<>(this.client.getHttpPipeline()),
                                new StatusCheckPollingStrategy<>())),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginDeleteAsyncRelativeRetryInvalidJsonPolling() {
        return this.beginDeleteAsyncRelativeRetryInvalidJsonPollingAsync().getSyncPoller();
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with invalid 'Location' and
     * 'Retry-After' headers.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LrosaDsPost202RetryInvalidHeaderResponse> post202RetryInvalidHeaderWithResponseAsync(Product product) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.post202RetryInvalidHeader(this.client.getHost(), product, accept, context));
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with invalid 'Location' and
     * 'Retry-After' headers.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginPost202RetryInvalidHeaderAsync(Product product) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.post202RetryInvalidHeaderWithResponseAsync(product),
                new ChainedPollingStrategy<>(
                        java.util.Arrays.asList(
                                new OperationResourcePollingStrategy<>(
                                        this.client.getHttpPipeline(), null, "Azure-AsyncOperation"),
                                new LocationPollingStrategy<>(this.client.getHttpPipeline()),
                                new StatusCheckPollingStrategy<>())),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with invalid 'Location' and
     * 'Retry-After' headers.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginPost202RetryInvalidHeader(Product product) {
        return this.beginPost202RetryInvalidHeaderAsync(product).getSyncPoller();
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. The endpoint indicated in the Azure-AsyncOperation header is invalid.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LrosaDsPostAsyncRelativeRetryInvalidHeaderResponse>
            postAsyncRelativeRetryInvalidHeaderWithResponseAsync(Product product) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context ->
                        service.postAsyncRelativeRetryInvalidHeader(this.client.getHost(), product, accept, context));
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. The endpoint indicated in the Azure-AsyncOperation header is invalid.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginPostAsyncRelativeRetryInvalidHeaderAsync(Product product) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.postAsyncRelativeRetryInvalidHeaderWithResponseAsync(product),
                new ChainedPollingStrategy<>(
                        java.util.Arrays.asList(
                                new OperationResourcePollingStrategy<>(
                                        this.client.getHttpPipeline(), null, "Azure-AsyncOperation"),
                                new LocationPollingStrategy<>(this.client.getHttpPipeline()),
                                new StatusCheckPollingStrategy<>())),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. The endpoint indicated in the Azure-AsyncOperation header is invalid.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginPostAsyncRelativeRetryInvalidHeader(Product product) {
        return this.beginPostAsyncRelativeRetryInvalidHeaderAsync(product).getSyncPoller();
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LrosaDsPostAsyncRelativeRetryInvalidJsonPollingResponse>
            postAsyncRelativeRetryInvalidJsonPollingWithResponseAsync(Product product) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context ->
                        service.postAsyncRelativeRetryInvalidJsonPolling(
                                this.client.getHost(), product, accept, context));
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginPostAsyncRelativeRetryInvalidJsonPollingAsync(Product product) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.postAsyncRelativeRetryInvalidJsonPollingWithResponseAsync(product),
                new ChainedPollingStrategy<>(
                        java.util.Arrays.asList(
                                new OperationResourcePollingStrategy<>(
                                        this.client.getHttpPipeline(), null, "Azure-AsyncOperation"),
                                new LocationPollingStrategy<>(this.client.getHttpPipeline()),
                                new StatusCheckPollingStrategy<>())),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginPostAsyncRelativeRetryInvalidJsonPolling(Product product) {
        return this.beginPostAsyncRelativeRetryInvalidJsonPollingAsync(product).getSyncPoller();
    }

    private static final class TypeReferenceProduct extends TypeReference<Product> {
        // empty
    }

    private static final class TypeReferenceBinaryData extends TypeReference<BinaryData> {
        // empty
    }
}
