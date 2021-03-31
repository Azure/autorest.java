package fixtures.lro.implementation;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.Delete;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Headers;
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
import com.azure.core.management.exception.ManagementException;
import com.azure.core.management.polling.PollResult;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import com.azure.core.util.logging.ClientLogger;
import com.azure.core.util.polling.PollerFlux;
import com.azure.core.util.polling.SyncPoller;
import fixtures.lro.fluent.LrosaDsClient;
import fixtures.lro.fluent.models.ProductInner;
import java.nio.ByteBuffer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in LrosaDsClient. */
public final class LrosaDsClientImpl implements LrosaDsClient {
    private final ClientLogger logger = new ClientLogger(LrosaDsClientImpl.class);

    /** The proxy service used to perform REST calls. */
    private final LrosaDsService service;

    /** The service client containing this operation class. */
    private final AutoRestLongRunningOperationTestServiceImpl client;

    /**
     * Initializes an instance of LrosaDsClientImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    LrosaDsClientImpl(AutoRestLongRunningOperationTestServiceImpl client) {
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
        @Headers({"Content-Type: application/json"})
        @Put("/lro/nonretryerror/put/400")
        @ExpectedResponses({200, 201})
        @UnexpectedResponseExceptionType(ManagementException.class)
        Mono<Response<Flux<ByteBuffer>>> putNonRetry400(
            @HostParam("$host") String endpoint,
            @BodyParam("application/json") ProductInner product,
            @HeaderParam("Accept") String accept,
            Context context);

        @Headers({"Content-Type: application/json"})
        @Put("/lro/nonretryerror/put/201/creating/400")
        @ExpectedResponses({200, 201})
        @UnexpectedResponseExceptionType(ManagementException.class)
        Mono<Response<Flux<ByteBuffer>>> putNonRetry201Creating400(
            @HostParam("$host") String endpoint,
            @BodyParam("application/json") ProductInner product,
            @HeaderParam("Accept") String accept,
            Context context);

        @Headers({"Content-Type: application/json"})
        @Put("/lro/nonretryerror/put/201/creating/400/invalidjson")
        @ExpectedResponses({200, 201})
        @UnexpectedResponseExceptionType(ManagementException.class)
        Mono<Response<Flux<ByteBuffer>>> putNonRetry201Creating400InvalidJson(
            @HostParam("$host") String endpoint,
            @BodyParam("application/json") ProductInner product,
            @HeaderParam("Accept") String accept,
            Context context);

        @Headers({"Content-Type: application/json"})
        @Put("/lro/nonretryerror/putasync/retry/400")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ManagementException.class)
        Mono<Response<Flux<ByteBuffer>>> putAsyncRelativeRetry400(
            @HostParam("$host") String endpoint,
            @BodyParam("application/json") ProductInner product,
            @HeaderParam("Accept") String accept,
            Context context);

        @Headers({"Content-Type: application/json"})
        @Delete("/lro/nonretryerror/delete/400")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(ManagementException.class)
        Mono<Response<Flux<ByteBuffer>>> deleteNonRetry400(
            @HostParam("$host") String endpoint, @HeaderParam("Accept") String accept, Context context);

        @Headers({"Content-Type: application/json"})
        @Delete("/lro/nonretryerror/delete/202/retry/400")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(ManagementException.class)
        Mono<Response<Flux<ByteBuffer>>> delete202NonRetry400(
            @HostParam("$host") String endpoint, @HeaderParam("Accept") String accept, Context context);

        @Headers({"Content-Type: application/json"})
        @Delete("/lro/nonretryerror/deleteasync/retry/400")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(ManagementException.class)
        Mono<Response<Flux<ByteBuffer>>> deleteAsyncRelativeRetry400(
            @HostParam("$host") String endpoint, @HeaderParam("Accept") String accept, Context context);

        @Headers({"Content-Type: application/json"})
        @Post("/lro/nonretryerror/post/400")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(ManagementException.class)
        Mono<Response<Flux<ByteBuffer>>> postNonRetry400(
            @HostParam("$host") String endpoint,
            @BodyParam("application/json") ProductInner product,
            @HeaderParam("Accept") String accept,
            Context context);

        @Headers({"Content-Type: application/json"})
        @Post("/lro/nonretryerror/post/202/retry/400")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(ManagementException.class)
        Mono<Response<Flux<ByteBuffer>>> post202NonRetry400(
            @HostParam("$host") String endpoint,
            @BodyParam("application/json") ProductInner product,
            @HeaderParam("Accept") String accept,
            Context context);

        @Headers({"Content-Type: application/json"})
        @Post("/lro/nonretryerror/postasync/retry/400")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(ManagementException.class)
        Mono<Response<Flux<ByteBuffer>>> postAsyncRelativeRetry400(
            @HostParam("$host") String endpoint,
            @BodyParam("application/json") ProductInner product,
            @HeaderParam("Accept") String accept,
            Context context);

        @Headers({"Content-Type: application/json"})
        @Put("/lro/error/put/201/noprovisioningstatepayload")
        @ExpectedResponses({200, 201})
        @UnexpectedResponseExceptionType(ManagementException.class)
        Mono<Response<Flux<ByteBuffer>>> putError201NoProvisioningStatePayload(
            @HostParam("$host") String endpoint,
            @BodyParam("application/json") ProductInner product,
            @HeaderParam("Accept") String accept,
            Context context);

        @Headers({"Content-Type: application/json"})
        @Put("/lro/error/putasync/retry/nostatus")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ManagementException.class)
        Mono<Response<Flux<ByteBuffer>>> putAsyncRelativeRetryNoStatus(
            @HostParam("$host") String endpoint,
            @BodyParam("application/json") ProductInner product,
            @HeaderParam("Accept") String accept,
            Context context);

        @Headers({"Content-Type: application/json"})
        @Put("/lro/error/putasync/retry/nostatuspayload")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ManagementException.class)
        Mono<Response<Flux<ByteBuffer>>> putAsyncRelativeRetryNoStatusPayload(
            @HostParam("$host") String endpoint,
            @BodyParam("application/json") ProductInner product,
            @HeaderParam("Accept") String accept,
            Context context);

        @Headers({"Content-Type: application/json"})
        @Delete("/lro/error/delete/204/nolocation")
        @ExpectedResponses({204})
        @UnexpectedResponseExceptionType(ManagementException.class)
        Mono<Response<Flux<ByteBuffer>>> delete204Succeeded(
            @HostParam("$host") String endpoint, @HeaderParam("Accept") String accept, Context context);

        @Headers({"Content-Type: application/json"})
        @Delete("/lro/error/deleteasync/retry/nostatus")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(ManagementException.class)
        Mono<Response<Flux<ByteBuffer>>> deleteAsyncRelativeRetryNoStatus(
            @HostParam("$host") String endpoint, @HeaderParam("Accept") String accept, Context context);

        @Headers({"Content-Type: application/json"})
        @Post("/lro/error/post/202/nolocation")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(ManagementException.class)
        Mono<Response<Flux<ByteBuffer>>> post202NoLocation(
            @HostParam("$host") String endpoint,
            @BodyParam("application/json") ProductInner product,
            @HeaderParam("Accept") String accept,
            Context context);

        @Headers({"Content-Type: application/json"})
        @Post("/lro/error/postasync/retry/nopayload")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(ManagementException.class)
        Mono<Response<Flux<ByteBuffer>>> postAsyncRelativeRetryNoPayload(
            @HostParam("$host") String endpoint,
            @BodyParam("application/json") ProductInner product,
            @HeaderParam("Accept") String accept,
            Context context);

        @Headers({"Content-Type: application/json"})
        @Put("/lro/error/put/200/invalidjson")
        @ExpectedResponses({200, 204})
        @UnexpectedResponseExceptionType(ManagementException.class)
        Mono<Response<Flux<ByteBuffer>>> put200InvalidJson(
            @HostParam("$host") String endpoint,
            @BodyParam("application/json") ProductInner product,
            @HeaderParam("Accept") String accept,
            Context context);

        @Headers({"Content-Type: application/json"})
        @Put("/lro/error/putasync/retry/invalidheader")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ManagementException.class)
        Mono<Response<Flux<ByteBuffer>>> putAsyncRelativeRetryInvalidHeader(
            @HostParam("$host") String endpoint,
            @BodyParam("application/json") ProductInner product,
            @HeaderParam("Accept") String accept,
            Context context);

        @Headers({"Content-Type: application/json"})
        @Put("/lro/error/putasync/retry/invalidjsonpolling")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ManagementException.class)
        Mono<Response<Flux<ByteBuffer>>> putAsyncRelativeRetryInvalidJsonPolling(
            @HostParam("$host") String endpoint,
            @BodyParam("application/json") ProductInner product,
            @HeaderParam("Accept") String accept,
            Context context);

        @Headers({"Content-Type: application/json"})
        @Delete("/lro/error/delete/202/retry/invalidheader")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(ManagementException.class)
        Mono<Response<Flux<ByteBuffer>>> delete202RetryInvalidHeader(
            @HostParam("$host") String endpoint, @HeaderParam("Accept") String accept, Context context);

        @Headers({"Content-Type: application/json"})
        @Delete("/lro/error/deleteasync/retry/invalidheader")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(ManagementException.class)
        Mono<Response<Flux<ByteBuffer>>> deleteAsyncRelativeRetryInvalidHeader(
            @HostParam("$host") String endpoint, @HeaderParam("Accept") String accept, Context context);

        @Headers({"Content-Type: application/json"})
        @Delete("/lro/error/deleteasync/retry/invalidjsonpolling")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(ManagementException.class)
        Mono<Response<Flux<ByteBuffer>>> deleteAsyncRelativeRetryInvalidJsonPolling(
            @HostParam("$host") String endpoint, @HeaderParam("Accept") String accept, Context context);

        @Headers({"Content-Type: application/json"})
        @Post("/lro/error/post/202/retry/invalidheader")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(ManagementException.class)
        Mono<Response<Flux<ByteBuffer>>> post202RetryInvalidHeader(
            @HostParam("$host") String endpoint,
            @BodyParam("application/json") ProductInner product,
            @HeaderParam("Accept") String accept,
            Context context);

        @Headers({"Content-Type: application/json"})
        @Post("/lro/error/postasync/retry/invalidheader")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(ManagementException.class)
        Mono<Response<Flux<ByteBuffer>>> postAsyncRelativeRetryInvalidHeader(
            @HostParam("$host") String endpoint,
            @BodyParam("application/json") ProductInner product,
            @HeaderParam("Accept") String accept,
            Context context);

        @Headers({"Content-Type: application/json"})
        @Post("/lro/error/postasync/retry/invalidjsonpolling")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(ManagementException.class)
        Mono<Response<Flux<ByteBuffer>>> postAsyncRelativeRetryInvalidJsonPolling(
            @HostParam("$host") String endpoint,
            @BodyParam("application/json") ProductInner product,
            @HeaderParam("Accept") String accept,
            Context context);
    }

    /**
     * Long running put request, service returns a 400 to the initial request.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Flux<ByteBuffer>>> putNonRetry400WithResponseAsync(ProductInner product) {
        if (this.client.getEndpoint() == null) {
            return Mono
                .error(
                    new IllegalArgumentException(
                        "Parameter this.client.getEndpoint() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil
            .withContext(context -> service.putNonRetry400(this.client.getEndpoint(), product, accept, context))
            .contextWrite(context -> context.putAll(FluxUtil.toReactorContext(this.client.getContext()).readOnly()));
    }

    /**
     * Long running put request, service returns a 400 to the initial request.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Flux<ByteBuffer>>> putNonRetry400WithResponseAsync(ProductInner product, Context context) {
        if (this.client.getEndpoint() == null) {
            return Mono
                .error(
                    new IllegalArgumentException(
                        "Parameter this.client.getEndpoint() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        context = this.client.mergeContext(context);
        return service.putNonRetry400(this.client.getEndpoint(), product, accept, context);
    }

    /**
     * Long running put request, service returns a 400 to the initial request.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PollerFlux<PollResult<ProductInner>, ProductInner> beginPutNonRetry400Async(ProductInner product) {
        Mono<Response<Flux<ByteBuffer>>> mono = putNonRetry400WithResponseAsync(product);
        return this
            .client
            .<ProductInner, ProductInner>getLroResult(
                mono, this.client.getHttpPipeline(), ProductInner.class, ProductInner.class, Context.NONE);
    }

    /**
     * Long running put request, service returns a 400 to the initial request.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PollerFlux<PollResult<ProductInner>, ProductInner> beginPutNonRetry400Async(
        ProductInner product, Context context) {
        context = this.client.mergeContext(context);
        Mono<Response<Flux<ByteBuffer>>> mono = putNonRetry400WithResponseAsync(product, context);
        return this
            .client
            .<ProductInner, ProductInner>getLroResult(
                mono, this.client.getHttpPipeline(), ProductInner.class, ProductInner.class, context);
    }

    /**
     * Long running put request, service returns a 400 to the initial request.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SyncPoller<PollResult<ProductInner>, ProductInner> beginPutNonRetry400(ProductInner product) {
        return beginPutNonRetry400Async(product).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 400 to the initial request.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SyncPoller<PollResult<ProductInner>, ProductInner> beginPutNonRetry400(
        ProductInner product, Context context) {
        return beginPutNonRetry400Async(product, context).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 400 to the initial request.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<ProductInner> putNonRetry400Async(ProductInner product) {
        return beginPutNonRetry400Async(product).last().flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running put request, service returns a 400 to the initial request.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<ProductInner> putNonRetry400Async() {
        final ProductInner product = null;
        return beginPutNonRetry400Async(product).last().flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running put request, service returns a 400 to the initial request.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<ProductInner> putNonRetry400Async(ProductInner product, Context context) {
        return beginPutNonRetry400Async(product, context).last().flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running put request, service returns a 400 to the initial request.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProductInner putNonRetry400(ProductInner product) {
        return putNonRetry400Async(product).block();
    }

    /**
     * Long running put request, service returns a 400 to the initial request.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProductInner putNonRetry400() {
        final ProductInner product = null;
        return putNonRetry400Async(product).block();
    }

    /**
     * Long running put request, service returns a 400 to the initial request.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProductInner putNonRetry400(ProductInner product, Context context) {
        return putNonRetry400Async(product, context).block();
    }

    /**
     * Long running put request, service returns a Product with 'ProvisioningState' = 'Creating' and 201 response code.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Flux<ByteBuffer>>> putNonRetry201Creating400WithResponseAsync(ProductInner product) {
        if (this.client.getEndpoint() == null) {
            return Mono
                .error(
                    new IllegalArgumentException(
                        "Parameter this.client.getEndpoint() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil
            .withContext(
                context -> service.putNonRetry201Creating400(this.client.getEndpoint(), product, accept, context))
            .contextWrite(context -> context.putAll(FluxUtil.toReactorContext(this.client.getContext()).readOnly()));
    }

    /**
     * Long running put request, service returns a Product with 'ProvisioningState' = 'Creating' and 201 response code.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Flux<ByteBuffer>>> putNonRetry201Creating400WithResponseAsync(
        ProductInner product, Context context) {
        if (this.client.getEndpoint() == null) {
            return Mono
                .error(
                    new IllegalArgumentException(
                        "Parameter this.client.getEndpoint() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        context = this.client.mergeContext(context);
        return service.putNonRetry201Creating400(this.client.getEndpoint(), product, accept, context);
    }

    /**
     * Long running put request, service returns a Product with 'ProvisioningState' = 'Creating' and 201 response code.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PollerFlux<PollResult<ProductInner>, ProductInner> beginPutNonRetry201Creating400Async(
        ProductInner product) {
        Mono<Response<Flux<ByteBuffer>>> mono = putNonRetry201Creating400WithResponseAsync(product);
        return this
            .client
            .<ProductInner, ProductInner>getLroResult(
                mono, this.client.getHttpPipeline(), ProductInner.class, ProductInner.class, Context.NONE);
    }

    /**
     * Long running put request, service returns a Product with 'ProvisioningState' = 'Creating' and 201 response code.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PollerFlux<PollResult<ProductInner>, ProductInner> beginPutNonRetry201Creating400Async(
        ProductInner product, Context context) {
        context = this.client.mergeContext(context);
        Mono<Response<Flux<ByteBuffer>>> mono = putNonRetry201Creating400WithResponseAsync(product, context);
        return this
            .client
            .<ProductInner, ProductInner>getLroResult(
                mono, this.client.getHttpPipeline(), ProductInner.class, ProductInner.class, context);
    }

    /**
     * Long running put request, service returns a Product with 'ProvisioningState' = 'Creating' and 201 response code.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SyncPoller<PollResult<ProductInner>, ProductInner> beginPutNonRetry201Creating400(ProductInner product) {
        return beginPutNonRetry201Creating400Async(product).getSyncPoller();
    }

    /**
     * Long running put request, service returns a Product with 'ProvisioningState' = 'Creating' and 201 response code.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SyncPoller<PollResult<ProductInner>, ProductInner> beginPutNonRetry201Creating400(
        ProductInner product, Context context) {
        return beginPutNonRetry201Creating400Async(product, context).getSyncPoller();
    }

    /**
     * Long running put request, service returns a Product with 'ProvisioningState' = 'Creating' and 201 response code.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<ProductInner> putNonRetry201Creating400Async(ProductInner product) {
        return beginPutNonRetry201Creating400Async(product).last().flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running put request, service returns a Product with 'ProvisioningState' = 'Creating' and 201 response code.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<ProductInner> putNonRetry201Creating400Async() {
        final ProductInner product = null;
        return beginPutNonRetry201Creating400Async(product).last().flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running put request, service returns a Product with 'ProvisioningState' = 'Creating' and 201 response code.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<ProductInner> putNonRetry201Creating400Async(ProductInner product, Context context) {
        return beginPutNonRetry201Creating400Async(product, context)
            .last()
            .flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running put request, service returns a Product with 'ProvisioningState' = 'Creating' and 201 response code.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProductInner putNonRetry201Creating400(ProductInner product) {
        return putNonRetry201Creating400Async(product).block();
    }

    /**
     * Long running put request, service returns a Product with 'ProvisioningState' = 'Creating' and 201 response code.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProductInner putNonRetry201Creating400() {
        final ProductInner product = null;
        return putNonRetry201Creating400Async(product).block();
    }

    /**
     * Long running put request, service returns a Product with 'ProvisioningState' = 'Creating' and 201 response code.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProductInner putNonRetry201Creating400(ProductInner product, Context context) {
        return putNonRetry201Creating400Async(product, context).block();
    }

    /**
     * Long running put request, service returns a Product with 'ProvisioningState' = 'Creating' and 201 response code.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Flux<ByteBuffer>>> putNonRetry201Creating400InvalidJsonWithResponseAsync(
        ProductInner product) {
        if (this.client.getEndpoint() == null) {
            return Mono
                .error(
                    new IllegalArgumentException(
                        "Parameter this.client.getEndpoint() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil
            .withContext(
                context ->
                    service.putNonRetry201Creating400InvalidJson(this.client.getEndpoint(), product, accept, context))
            .contextWrite(context -> context.putAll(FluxUtil.toReactorContext(this.client.getContext()).readOnly()));
    }

    /**
     * Long running put request, service returns a Product with 'ProvisioningState' = 'Creating' and 201 response code.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Flux<ByteBuffer>>> putNonRetry201Creating400InvalidJsonWithResponseAsync(
        ProductInner product, Context context) {
        if (this.client.getEndpoint() == null) {
            return Mono
                .error(
                    new IllegalArgumentException(
                        "Parameter this.client.getEndpoint() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        context = this.client.mergeContext(context);
        return service.putNonRetry201Creating400InvalidJson(this.client.getEndpoint(), product, accept, context);
    }

    /**
     * Long running put request, service returns a Product with 'ProvisioningState' = 'Creating' and 201 response code.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PollerFlux<PollResult<ProductInner>, ProductInner> beginPutNonRetry201Creating400InvalidJsonAsync(
        ProductInner product) {
        Mono<Response<Flux<ByteBuffer>>> mono = putNonRetry201Creating400InvalidJsonWithResponseAsync(product);
        return this
            .client
            .<ProductInner, ProductInner>getLroResult(
                mono, this.client.getHttpPipeline(), ProductInner.class, ProductInner.class, Context.NONE);
    }

    /**
     * Long running put request, service returns a Product with 'ProvisioningState' = 'Creating' and 201 response code.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PollerFlux<PollResult<ProductInner>, ProductInner> beginPutNonRetry201Creating400InvalidJsonAsync(
        ProductInner product, Context context) {
        context = this.client.mergeContext(context);
        Mono<Response<Flux<ByteBuffer>>> mono = putNonRetry201Creating400InvalidJsonWithResponseAsync(product, context);
        return this
            .client
            .<ProductInner, ProductInner>getLroResult(
                mono, this.client.getHttpPipeline(), ProductInner.class, ProductInner.class, context);
    }

    /**
     * Long running put request, service returns a Product with 'ProvisioningState' = 'Creating' and 201 response code.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SyncPoller<PollResult<ProductInner>, ProductInner> beginPutNonRetry201Creating400InvalidJson(
        ProductInner product) {
        return beginPutNonRetry201Creating400InvalidJsonAsync(product).getSyncPoller();
    }

    /**
     * Long running put request, service returns a Product with 'ProvisioningState' = 'Creating' and 201 response code.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SyncPoller<PollResult<ProductInner>, ProductInner> beginPutNonRetry201Creating400InvalidJson(
        ProductInner product, Context context) {
        return beginPutNonRetry201Creating400InvalidJsonAsync(product, context).getSyncPoller();
    }

    /**
     * Long running put request, service returns a Product with 'ProvisioningState' = 'Creating' and 201 response code.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<ProductInner> putNonRetry201Creating400InvalidJsonAsync(ProductInner product) {
        return beginPutNonRetry201Creating400InvalidJsonAsync(product)
            .last()
            .flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running put request, service returns a Product with 'ProvisioningState' = 'Creating' and 201 response code.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<ProductInner> putNonRetry201Creating400InvalidJsonAsync() {
        final ProductInner product = null;
        return beginPutNonRetry201Creating400InvalidJsonAsync(product)
            .last()
            .flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running put request, service returns a Product with 'ProvisioningState' = 'Creating' and 201 response code.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<ProductInner> putNonRetry201Creating400InvalidJsonAsync(ProductInner product, Context context) {
        return beginPutNonRetry201Creating400InvalidJsonAsync(product, context)
            .last()
            .flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running put request, service returns a Product with 'ProvisioningState' = 'Creating' and 201 response code.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProductInner putNonRetry201Creating400InvalidJson(ProductInner product) {
        return putNonRetry201Creating400InvalidJsonAsync(product).block();
    }

    /**
     * Long running put request, service returns a Product with 'ProvisioningState' = 'Creating' and 201 response code.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProductInner putNonRetry201Creating400InvalidJson() {
        final ProductInner product = null;
        return putNonRetry201Creating400InvalidJsonAsync(product).block();
    }

    /**
     * Long running put request, service returns a Product with 'ProvisioningState' = 'Creating' and 201 response code.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProductInner putNonRetry201Creating400InvalidJson(ProductInner product, Context context) {
        return putNonRetry201Creating400InvalidJsonAsync(product, context).block();
    }

    /**
     * Long running put request, service returns a 200 with ProvisioningState=’Creating’. Poll the endpoint indicated in
     * the Azure-AsyncOperation header for operation status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Flux<ByteBuffer>>> putAsyncRelativeRetry400WithResponseAsync(ProductInner product) {
        if (this.client.getEndpoint() == null) {
            return Mono
                .error(
                    new IllegalArgumentException(
                        "Parameter this.client.getEndpoint() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil
            .withContext(
                context -> service.putAsyncRelativeRetry400(this.client.getEndpoint(), product, accept, context))
            .contextWrite(context -> context.putAll(FluxUtil.toReactorContext(this.client.getContext()).readOnly()));
    }

    /**
     * Long running put request, service returns a 200 with ProvisioningState=’Creating’. Poll the endpoint indicated in
     * the Azure-AsyncOperation header for operation status.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Flux<ByteBuffer>>> putAsyncRelativeRetry400WithResponseAsync(
        ProductInner product, Context context) {
        if (this.client.getEndpoint() == null) {
            return Mono
                .error(
                    new IllegalArgumentException(
                        "Parameter this.client.getEndpoint() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        context = this.client.mergeContext(context);
        return service.putAsyncRelativeRetry400(this.client.getEndpoint(), product, accept, context);
    }

    /**
     * Long running put request, service returns a 200 with ProvisioningState=’Creating’. Poll the endpoint indicated in
     * the Azure-AsyncOperation header for operation status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PollerFlux<PollResult<ProductInner>, ProductInner> beginPutAsyncRelativeRetry400Async(
        ProductInner product) {
        Mono<Response<Flux<ByteBuffer>>> mono = putAsyncRelativeRetry400WithResponseAsync(product);
        return this
            .client
            .<ProductInner, ProductInner>getLroResult(
                mono, this.client.getHttpPipeline(), ProductInner.class, ProductInner.class, Context.NONE);
    }

    /**
     * Long running put request, service returns a 200 with ProvisioningState=’Creating’. Poll the endpoint indicated in
     * the Azure-AsyncOperation header for operation status.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PollerFlux<PollResult<ProductInner>, ProductInner> beginPutAsyncRelativeRetry400Async(
        ProductInner product, Context context) {
        context = this.client.mergeContext(context);
        Mono<Response<Flux<ByteBuffer>>> mono = putAsyncRelativeRetry400WithResponseAsync(product, context);
        return this
            .client
            .<ProductInner, ProductInner>getLroResult(
                mono, this.client.getHttpPipeline(), ProductInner.class, ProductInner.class, context);
    }

    /**
     * Long running put request, service returns a 200 with ProvisioningState=’Creating’. Poll the endpoint indicated in
     * the Azure-AsyncOperation header for operation status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SyncPoller<PollResult<ProductInner>, ProductInner> beginPutAsyncRelativeRetry400(ProductInner product) {
        return beginPutAsyncRelativeRetry400Async(product).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 200 with ProvisioningState=’Creating’. Poll the endpoint indicated in
     * the Azure-AsyncOperation header for operation status.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SyncPoller<PollResult<ProductInner>, ProductInner> beginPutAsyncRelativeRetry400(
        ProductInner product, Context context) {
        return beginPutAsyncRelativeRetry400Async(product, context).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 200 with ProvisioningState=’Creating’. Poll the endpoint indicated in
     * the Azure-AsyncOperation header for operation status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<ProductInner> putAsyncRelativeRetry400Async(ProductInner product) {
        return beginPutAsyncRelativeRetry400Async(product).last().flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running put request, service returns a 200 with ProvisioningState=’Creating’. Poll the endpoint indicated in
     * the Azure-AsyncOperation header for operation status.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<ProductInner> putAsyncRelativeRetry400Async() {
        final ProductInner product = null;
        return beginPutAsyncRelativeRetry400Async(product).last().flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running put request, service returns a 200 with ProvisioningState=’Creating’. Poll the endpoint indicated in
     * the Azure-AsyncOperation header for operation status.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<ProductInner> putAsyncRelativeRetry400Async(ProductInner product, Context context) {
        return beginPutAsyncRelativeRetry400Async(product, context)
            .last()
            .flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running put request, service returns a 200 with ProvisioningState=’Creating’. Poll the endpoint indicated in
     * the Azure-AsyncOperation header for operation status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProductInner putAsyncRelativeRetry400(ProductInner product) {
        return putAsyncRelativeRetry400Async(product).block();
    }

    /**
     * Long running put request, service returns a 200 with ProvisioningState=’Creating’. Poll the endpoint indicated in
     * the Azure-AsyncOperation header for operation status.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProductInner putAsyncRelativeRetry400() {
        final ProductInner product = null;
        return putAsyncRelativeRetry400Async(product).block();
    }

    /**
     * Long running put request, service returns a 200 with ProvisioningState=’Creating’. Poll the endpoint indicated in
     * the Azure-AsyncOperation header for operation status.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProductInner putAsyncRelativeRetry400(ProductInner product, Context context) {
        return putAsyncRelativeRetry400Async(product, context).block();
    }

    /**
     * Long running delete request, service returns a 400 with an error body.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Flux<ByteBuffer>>> deleteNonRetry400WithResponseAsync() {
        if (this.client.getEndpoint() == null) {
            return Mono
                .error(
                    new IllegalArgumentException(
                        "Parameter this.client.getEndpoint() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil
            .withContext(context -> service.deleteNonRetry400(this.client.getEndpoint(), accept, context))
            .contextWrite(context -> context.putAll(FluxUtil.toReactorContext(this.client.getContext()).readOnly()));
    }

    /**
     * Long running delete request, service returns a 400 with an error body.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Flux<ByteBuffer>>> deleteNonRetry400WithResponseAsync(Context context) {
        if (this.client.getEndpoint() == null) {
            return Mono
                .error(
                    new IllegalArgumentException(
                        "Parameter this.client.getEndpoint() is required and cannot be null."));
        }
        final String accept = "application/json";
        context = this.client.mergeContext(context);
        return service.deleteNonRetry400(this.client.getEndpoint(), accept, context);
    }

    /**
     * Long running delete request, service returns a 400 with an error body.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PollerFlux<PollResult<Void>, Void> beginDeleteNonRetry400Async() {
        Mono<Response<Flux<ByteBuffer>>> mono = deleteNonRetry400WithResponseAsync();
        return this
            .client
            .<Void, Void>getLroResult(mono, this.client.getHttpPipeline(), Void.class, Void.class, Context.NONE);
    }

    /**
     * Long running delete request, service returns a 400 with an error body.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PollerFlux<PollResult<Void>, Void> beginDeleteNonRetry400Async(Context context) {
        context = this.client.mergeContext(context);
        Mono<Response<Flux<ByteBuffer>>> mono = deleteNonRetry400WithResponseAsync(context);
        return this
            .client
            .<Void, Void>getLroResult(mono, this.client.getHttpPipeline(), Void.class, Void.class, context);
    }

    /**
     * Long running delete request, service returns a 400 with an error body.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SyncPoller<PollResult<Void>, Void> beginDeleteNonRetry400() {
        return beginDeleteNonRetry400Async().getSyncPoller();
    }

    /**
     * Long running delete request, service returns a 400 with an error body.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SyncPoller<PollResult<Void>, Void> beginDeleteNonRetry400(Context context) {
        return beginDeleteNonRetry400Async(context).getSyncPoller();
    }

    /**
     * Long running delete request, service returns a 400 with an error body.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Void> deleteNonRetry400Async() {
        return beginDeleteNonRetry400Async().last().flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running delete request, service returns a 400 with an error body.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Void> deleteNonRetry400Async(Context context) {
        return beginDeleteNonRetry400Async(context).last().flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running delete request, service returns a 400 with an error body.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void deleteNonRetry400() {
        deleteNonRetry400Async().block();
    }

    /**
     * Long running delete request, service returns a 400 with an error body.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void deleteNonRetry400(Context context) {
        deleteNonRetry400Async(context).block();
    }

    /**
     * Long running delete request, service returns a 202 with a location header.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Flux<ByteBuffer>>> delete202NonRetry400WithResponseAsync() {
        if (this.client.getEndpoint() == null) {
            return Mono
                .error(
                    new IllegalArgumentException(
                        "Parameter this.client.getEndpoint() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil
            .withContext(context -> service.delete202NonRetry400(this.client.getEndpoint(), accept, context))
            .contextWrite(context -> context.putAll(FluxUtil.toReactorContext(this.client.getContext()).readOnly()));
    }

    /**
     * Long running delete request, service returns a 202 with a location header.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Flux<ByteBuffer>>> delete202NonRetry400WithResponseAsync(Context context) {
        if (this.client.getEndpoint() == null) {
            return Mono
                .error(
                    new IllegalArgumentException(
                        "Parameter this.client.getEndpoint() is required and cannot be null."));
        }
        final String accept = "application/json";
        context = this.client.mergeContext(context);
        return service.delete202NonRetry400(this.client.getEndpoint(), accept, context);
    }

    /**
     * Long running delete request, service returns a 202 with a location header.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PollerFlux<PollResult<Void>, Void> beginDelete202NonRetry400Async() {
        Mono<Response<Flux<ByteBuffer>>> mono = delete202NonRetry400WithResponseAsync();
        return this
            .client
            .<Void, Void>getLroResult(mono, this.client.getHttpPipeline(), Void.class, Void.class, Context.NONE);
    }

    /**
     * Long running delete request, service returns a 202 with a location header.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PollerFlux<PollResult<Void>, Void> beginDelete202NonRetry400Async(Context context) {
        context = this.client.mergeContext(context);
        Mono<Response<Flux<ByteBuffer>>> mono = delete202NonRetry400WithResponseAsync(context);
        return this
            .client
            .<Void, Void>getLroResult(mono, this.client.getHttpPipeline(), Void.class, Void.class, context);
    }

    /**
     * Long running delete request, service returns a 202 with a location header.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SyncPoller<PollResult<Void>, Void> beginDelete202NonRetry400() {
        return beginDelete202NonRetry400Async().getSyncPoller();
    }

    /**
     * Long running delete request, service returns a 202 with a location header.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SyncPoller<PollResult<Void>, Void> beginDelete202NonRetry400(Context context) {
        return beginDelete202NonRetry400Async(context).getSyncPoller();
    }

    /**
     * Long running delete request, service returns a 202 with a location header.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Void> delete202NonRetry400Async() {
        return beginDelete202NonRetry400Async().last().flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running delete request, service returns a 202 with a location header.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Void> delete202NonRetry400Async(Context context) {
        return beginDelete202NonRetry400Async(context).last().flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running delete request, service returns a 202 with a location header.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void delete202NonRetry400() {
        delete202NonRetry400Async().block();
    }

    /**
     * Long running delete request, service returns a 202 with a location header.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void delete202NonRetry400(Context context) {
        delete202NonRetry400Async(context).block();
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Flux<ByteBuffer>>> deleteAsyncRelativeRetry400WithResponseAsync() {
        if (this.client.getEndpoint() == null) {
            return Mono
                .error(
                    new IllegalArgumentException(
                        "Parameter this.client.getEndpoint() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil
            .withContext(context -> service.deleteAsyncRelativeRetry400(this.client.getEndpoint(), accept, context))
            .contextWrite(context -> context.putAll(FluxUtil.toReactorContext(this.client.getContext()).readOnly()));
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Flux<ByteBuffer>>> deleteAsyncRelativeRetry400WithResponseAsync(Context context) {
        if (this.client.getEndpoint() == null) {
            return Mono
                .error(
                    new IllegalArgumentException(
                        "Parameter this.client.getEndpoint() is required and cannot be null."));
        }
        final String accept = "application/json";
        context = this.client.mergeContext(context);
        return service.deleteAsyncRelativeRetry400(this.client.getEndpoint(), accept, context);
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PollerFlux<PollResult<Void>, Void> beginDeleteAsyncRelativeRetry400Async() {
        Mono<Response<Flux<ByteBuffer>>> mono = deleteAsyncRelativeRetry400WithResponseAsync();
        return this
            .client
            .<Void, Void>getLroResult(mono, this.client.getHttpPipeline(), Void.class, Void.class, Context.NONE);
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PollerFlux<PollResult<Void>, Void> beginDeleteAsyncRelativeRetry400Async(Context context) {
        context = this.client.mergeContext(context);
        Mono<Response<Flux<ByteBuffer>>> mono = deleteAsyncRelativeRetry400WithResponseAsync(context);
        return this
            .client
            .<Void, Void>getLroResult(mono, this.client.getHttpPipeline(), Void.class, Void.class, context);
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SyncPoller<PollResult<Void>, Void> beginDeleteAsyncRelativeRetry400() {
        return beginDeleteAsyncRelativeRetry400Async().getSyncPoller();
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SyncPoller<PollResult<Void>, Void> beginDeleteAsyncRelativeRetry400(Context context) {
        return beginDeleteAsyncRelativeRetry400Async(context).getSyncPoller();
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Void> deleteAsyncRelativeRetry400Async() {
        return beginDeleteAsyncRelativeRetry400Async().last().flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Void> deleteAsyncRelativeRetry400Async(Context context) {
        return beginDeleteAsyncRelativeRetry400Async(context).last().flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void deleteAsyncRelativeRetry400() {
        deleteAsyncRelativeRetry400Async().block();
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void deleteAsyncRelativeRetry400(Context context) {
        deleteAsyncRelativeRetry400Async(context).block();
    }

    /**
     * Long running post request, service returns a 400 with no error body.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Flux<ByteBuffer>>> postNonRetry400WithResponseAsync(ProductInner product) {
        if (this.client.getEndpoint() == null) {
            return Mono
                .error(
                    new IllegalArgumentException(
                        "Parameter this.client.getEndpoint() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil
            .withContext(context -> service.postNonRetry400(this.client.getEndpoint(), product, accept, context))
            .contextWrite(context -> context.putAll(FluxUtil.toReactorContext(this.client.getContext()).readOnly()));
    }

    /**
     * Long running post request, service returns a 400 with no error body.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Flux<ByteBuffer>>> postNonRetry400WithResponseAsync(ProductInner product, Context context) {
        if (this.client.getEndpoint() == null) {
            return Mono
                .error(
                    new IllegalArgumentException(
                        "Parameter this.client.getEndpoint() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        context = this.client.mergeContext(context);
        return service.postNonRetry400(this.client.getEndpoint(), product, accept, context);
    }

    /**
     * Long running post request, service returns a 400 with no error body.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PollerFlux<PollResult<Void>, Void> beginPostNonRetry400Async(ProductInner product) {
        Mono<Response<Flux<ByteBuffer>>> mono = postNonRetry400WithResponseAsync(product);
        return this
            .client
            .<Void, Void>getLroResult(mono, this.client.getHttpPipeline(), Void.class, Void.class, Context.NONE);
    }

    /**
     * Long running post request, service returns a 400 with no error body.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PollerFlux<PollResult<Void>, Void> beginPostNonRetry400Async(ProductInner product, Context context) {
        context = this.client.mergeContext(context);
        Mono<Response<Flux<ByteBuffer>>> mono = postNonRetry400WithResponseAsync(product, context);
        return this
            .client
            .<Void, Void>getLroResult(mono, this.client.getHttpPipeline(), Void.class, Void.class, context);
    }

    /**
     * Long running post request, service returns a 400 with no error body.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SyncPoller<PollResult<Void>, Void> beginPostNonRetry400(ProductInner product) {
        return beginPostNonRetry400Async(product).getSyncPoller();
    }

    /**
     * Long running post request, service returns a 400 with no error body.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SyncPoller<PollResult<Void>, Void> beginPostNonRetry400(ProductInner product, Context context) {
        return beginPostNonRetry400Async(product, context).getSyncPoller();
    }

    /**
     * Long running post request, service returns a 400 with no error body.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Void> postNonRetry400Async(ProductInner product) {
        return beginPostNonRetry400Async(product).last().flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running post request, service returns a 400 with no error body.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Void> postNonRetry400Async() {
        final ProductInner product = null;
        return beginPostNonRetry400Async(product).last().flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running post request, service returns a 400 with no error body.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Void> postNonRetry400Async(ProductInner product, Context context) {
        return beginPostNonRetry400Async(product, context).last().flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running post request, service returns a 400 with no error body.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void postNonRetry400(ProductInner product) {
        postNonRetry400Async(product).block();
    }

    /**
     * Long running post request, service returns a 400 with no error body.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void postNonRetry400() {
        final ProductInner product = null;
        postNonRetry400Async(product).block();
    }

    /**
     * Long running post request, service returns a 400 with no error body.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void postNonRetry400(ProductInner product, Context context) {
        postNonRetry400Async(product, context).block();
    }

    /**
     * Long running post request, service returns a 202 with a location header.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Flux<ByteBuffer>>> post202NonRetry400WithResponseAsync(ProductInner product) {
        if (this.client.getEndpoint() == null) {
            return Mono
                .error(
                    new IllegalArgumentException(
                        "Parameter this.client.getEndpoint() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil
            .withContext(context -> service.post202NonRetry400(this.client.getEndpoint(), product, accept, context))
            .contextWrite(context -> context.putAll(FluxUtil.toReactorContext(this.client.getContext()).readOnly()));
    }

    /**
     * Long running post request, service returns a 202 with a location header.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Flux<ByteBuffer>>> post202NonRetry400WithResponseAsync(
        ProductInner product, Context context) {
        if (this.client.getEndpoint() == null) {
            return Mono
                .error(
                    new IllegalArgumentException(
                        "Parameter this.client.getEndpoint() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        context = this.client.mergeContext(context);
        return service.post202NonRetry400(this.client.getEndpoint(), product, accept, context);
    }

    /**
     * Long running post request, service returns a 202 with a location header.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PollerFlux<PollResult<Void>, Void> beginPost202NonRetry400Async(ProductInner product) {
        Mono<Response<Flux<ByteBuffer>>> mono = post202NonRetry400WithResponseAsync(product);
        return this
            .client
            .<Void, Void>getLroResult(mono, this.client.getHttpPipeline(), Void.class, Void.class, Context.NONE);
    }

    /**
     * Long running post request, service returns a 202 with a location header.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PollerFlux<PollResult<Void>, Void> beginPost202NonRetry400Async(ProductInner product, Context context) {
        context = this.client.mergeContext(context);
        Mono<Response<Flux<ByteBuffer>>> mono = post202NonRetry400WithResponseAsync(product, context);
        return this
            .client
            .<Void, Void>getLroResult(mono, this.client.getHttpPipeline(), Void.class, Void.class, context);
    }

    /**
     * Long running post request, service returns a 202 with a location header.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SyncPoller<PollResult<Void>, Void> beginPost202NonRetry400(ProductInner product) {
        return beginPost202NonRetry400Async(product).getSyncPoller();
    }

    /**
     * Long running post request, service returns a 202 with a location header.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SyncPoller<PollResult<Void>, Void> beginPost202NonRetry400(ProductInner product, Context context) {
        return beginPost202NonRetry400Async(product, context).getSyncPoller();
    }

    /**
     * Long running post request, service returns a 202 with a location header.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Void> post202NonRetry400Async(ProductInner product) {
        return beginPost202NonRetry400Async(product).last().flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running post request, service returns a 202 with a location header.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Void> post202NonRetry400Async() {
        final ProductInner product = null;
        return beginPost202NonRetry400Async(product).last().flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running post request, service returns a 202 with a location header.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Void> post202NonRetry400Async(ProductInner product, Context context) {
        return beginPost202NonRetry400Async(product, context).last().flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running post request, service returns a 202 with a location header.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void post202NonRetry400(ProductInner product) {
        post202NonRetry400Async(product).block();
    }

    /**
     * Long running post request, service returns a 202 with a location header.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void post202NonRetry400() {
        final ProductInner product = null;
        post202NonRetry400Async(product).block();
    }

    /**
     * Long running post request, service returns a 202 with a location header.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void post202NonRetry400(ProductInner product, Context context) {
        post202NonRetry400Async(product, context).block();
    }

    /**
     * Long running post request, service returns a 202 to the initial request Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Flux<ByteBuffer>>> postAsyncRelativeRetry400WithResponseAsync(ProductInner product) {
        if (this.client.getEndpoint() == null) {
            return Mono
                .error(
                    new IllegalArgumentException(
                        "Parameter this.client.getEndpoint() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil
            .withContext(
                context -> service.postAsyncRelativeRetry400(this.client.getEndpoint(), product, accept, context))
            .contextWrite(context -> context.putAll(FluxUtil.toReactorContext(this.client.getContext()).readOnly()));
    }

    /**
     * Long running post request, service returns a 202 to the initial request Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Flux<ByteBuffer>>> postAsyncRelativeRetry400WithResponseAsync(
        ProductInner product, Context context) {
        if (this.client.getEndpoint() == null) {
            return Mono
                .error(
                    new IllegalArgumentException(
                        "Parameter this.client.getEndpoint() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        context = this.client.mergeContext(context);
        return service.postAsyncRelativeRetry400(this.client.getEndpoint(), product, accept, context);
    }

    /**
     * Long running post request, service returns a 202 to the initial request Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PollerFlux<PollResult<Void>, Void> beginPostAsyncRelativeRetry400Async(ProductInner product) {
        Mono<Response<Flux<ByteBuffer>>> mono = postAsyncRelativeRetry400WithResponseAsync(product);
        return this
            .client
            .<Void, Void>getLroResult(mono, this.client.getHttpPipeline(), Void.class, Void.class, Context.NONE);
    }

    /**
     * Long running post request, service returns a 202 to the initial request Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PollerFlux<PollResult<Void>, Void> beginPostAsyncRelativeRetry400Async(
        ProductInner product, Context context) {
        context = this.client.mergeContext(context);
        Mono<Response<Flux<ByteBuffer>>> mono = postAsyncRelativeRetry400WithResponseAsync(product, context);
        return this
            .client
            .<Void, Void>getLroResult(mono, this.client.getHttpPipeline(), Void.class, Void.class, context);
    }

    /**
     * Long running post request, service returns a 202 to the initial request Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SyncPoller<PollResult<Void>, Void> beginPostAsyncRelativeRetry400(ProductInner product) {
        return beginPostAsyncRelativeRetry400Async(product).getSyncPoller();
    }

    /**
     * Long running post request, service returns a 202 to the initial request Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SyncPoller<PollResult<Void>, Void> beginPostAsyncRelativeRetry400(ProductInner product, Context context) {
        return beginPostAsyncRelativeRetry400Async(product, context).getSyncPoller();
    }

    /**
     * Long running post request, service returns a 202 to the initial request Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Void> postAsyncRelativeRetry400Async(ProductInner product) {
        return beginPostAsyncRelativeRetry400Async(product).last().flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running post request, service returns a 202 to the initial request Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Void> postAsyncRelativeRetry400Async() {
        final ProductInner product = null;
        return beginPostAsyncRelativeRetry400Async(product).last().flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running post request, service returns a 202 to the initial request Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Void> postAsyncRelativeRetry400Async(ProductInner product, Context context) {
        return beginPostAsyncRelativeRetry400Async(product, context)
            .last()
            .flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running post request, service returns a 202 to the initial request Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void postAsyncRelativeRetry400(ProductInner product) {
        postAsyncRelativeRetry400Async(product).block();
    }

    /**
     * Long running post request, service returns a 202 to the initial request Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void postAsyncRelativeRetry400() {
        final ProductInner product = null;
        postAsyncRelativeRetry400Async(product).block();
    }

    /**
     * Long running post request, service returns a 202 to the initial request Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void postAsyncRelativeRetry400(ProductInner product, Context context) {
        postAsyncRelativeRetry400Async(product, context).block();
    }

    /**
     * Long running put request, service returns a 201 to the initial request with no payload.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Flux<ByteBuffer>>> putError201NoProvisioningStatePayloadWithResponseAsync(
        ProductInner product) {
        if (this.client.getEndpoint() == null) {
            return Mono
                .error(
                    new IllegalArgumentException(
                        "Parameter this.client.getEndpoint() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil
            .withContext(
                context ->
                    service.putError201NoProvisioningStatePayload(this.client.getEndpoint(), product, accept, context))
            .contextWrite(context -> context.putAll(FluxUtil.toReactorContext(this.client.getContext()).readOnly()));
    }

    /**
     * Long running put request, service returns a 201 to the initial request with no payload.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Flux<ByteBuffer>>> putError201NoProvisioningStatePayloadWithResponseAsync(
        ProductInner product, Context context) {
        if (this.client.getEndpoint() == null) {
            return Mono
                .error(
                    new IllegalArgumentException(
                        "Parameter this.client.getEndpoint() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        context = this.client.mergeContext(context);
        return service.putError201NoProvisioningStatePayload(this.client.getEndpoint(), product, accept, context);
    }

    /**
     * Long running put request, service returns a 201 to the initial request with no payload.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PollerFlux<PollResult<ProductInner>, ProductInner> beginPutError201NoProvisioningStatePayloadAsync(
        ProductInner product) {
        Mono<Response<Flux<ByteBuffer>>> mono = putError201NoProvisioningStatePayloadWithResponseAsync(product);
        return this
            .client
            .<ProductInner, ProductInner>getLroResult(
                mono, this.client.getHttpPipeline(), ProductInner.class, ProductInner.class, Context.NONE);
    }

    /**
     * Long running put request, service returns a 201 to the initial request with no payload.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PollerFlux<PollResult<ProductInner>, ProductInner> beginPutError201NoProvisioningStatePayloadAsync(
        ProductInner product, Context context) {
        context = this.client.mergeContext(context);
        Mono<Response<Flux<ByteBuffer>>> mono =
            putError201NoProvisioningStatePayloadWithResponseAsync(product, context);
        return this
            .client
            .<ProductInner, ProductInner>getLroResult(
                mono, this.client.getHttpPipeline(), ProductInner.class, ProductInner.class, context);
    }

    /**
     * Long running put request, service returns a 201 to the initial request with no payload.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SyncPoller<PollResult<ProductInner>, ProductInner> beginPutError201NoProvisioningStatePayload(
        ProductInner product) {
        return beginPutError201NoProvisioningStatePayloadAsync(product).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 201 to the initial request with no payload.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SyncPoller<PollResult<ProductInner>, ProductInner> beginPutError201NoProvisioningStatePayload(
        ProductInner product, Context context) {
        return beginPutError201NoProvisioningStatePayloadAsync(product, context).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 201 to the initial request with no payload.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<ProductInner> putError201NoProvisioningStatePayloadAsync(ProductInner product) {
        return beginPutError201NoProvisioningStatePayloadAsync(product)
            .last()
            .flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running put request, service returns a 201 to the initial request with no payload.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<ProductInner> putError201NoProvisioningStatePayloadAsync() {
        final ProductInner product = null;
        return beginPutError201NoProvisioningStatePayloadAsync(product)
            .last()
            .flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running put request, service returns a 201 to the initial request with no payload.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<ProductInner> putError201NoProvisioningStatePayloadAsync(ProductInner product, Context context) {
        return beginPutError201NoProvisioningStatePayloadAsync(product, context)
            .last()
            .flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running put request, service returns a 201 to the initial request with no payload.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProductInner putError201NoProvisioningStatePayload(ProductInner product) {
        return putError201NoProvisioningStatePayloadAsync(product).block();
    }

    /**
     * Long running put request, service returns a 201 to the initial request with no payload.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProductInner putError201NoProvisioningStatePayload() {
        final ProductInner product = null;
        return putError201NoProvisioningStatePayloadAsync(product).block();
    }

    /**
     * Long running put request, service returns a 201 to the initial request with no payload.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProductInner putError201NoProvisioningStatePayload(ProductInner product, Context context) {
        return putError201NoProvisioningStatePayloadAsync(product, context).block();
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Flux<ByteBuffer>>> putAsyncRelativeRetryNoStatusWithResponseAsync(ProductInner product) {
        if (this.client.getEndpoint() == null) {
            return Mono
                .error(
                    new IllegalArgumentException(
                        "Parameter this.client.getEndpoint() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil
            .withContext(
                context -> service.putAsyncRelativeRetryNoStatus(this.client.getEndpoint(), product, accept, context))
            .contextWrite(context -> context.putAll(FluxUtil.toReactorContext(this.client.getContext()).readOnly()));
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Flux<ByteBuffer>>> putAsyncRelativeRetryNoStatusWithResponseAsync(
        ProductInner product, Context context) {
        if (this.client.getEndpoint() == null) {
            return Mono
                .error(
                    new IllegalArgumentException(
                        "Parameter this.client.getEndpoint() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        context = this.client.mergeContext(context);
        return service.putAsyncRelativeRetryNoStatus(this.client.getEndpoint(), product, accept, context);
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PollerFlux<PollResult<ProductInner>, ProductInner> beginPutAsyncRelativeRetryNoStatusAsync(
        ProductInner product) {
        Mono<Response<Flux<ByteBuffer>>> mono = putAsyncRelativeRetryNoStatusWithResponseAsync(product);
        return this
            .client
            .<ProductInner, ProductInner>getLroResult(
                mono, this.client.getHttpPipeline(), ProductInner.class, ProductInner.class, Context.NONE);
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PollerFlux<PollResult<ProductInner>, ProductInner> beginPutAsyncRelativeRetryNoStatusAsync(
        ProductInner product, Context context) {
        context = this.client.mergeContext(context);
        Mono<Response<Flux<ByteBuffer>>> mono = putAsyncRelativeRetryNoStatusWithResponseAsync(product, context);
        return this
            .client
            .<ProductInner, ProductInner>getLroResult(
                mono, this.client.getHttpPipeline(), ProductInner.class, ProductInner.class, context);
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SyncPoller<PollResult<ProductInner>, ProductInner> beginPutAsyncRelativeRetryNoStatus(ProductInner product) {
        return beginPutAsyncRelativeRetryNoStatusAsync(product).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SyncPoller<PollResult<ProductInner>, ProductInner> beginPutAsyncRelativeRetryNoStatus(
        ProductInner product, Context context) {
        return beginPutAsyncRelativeRetryNoStatusAsync(product, context).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<ProductInner> putAsyncRelativeRetryNoStatusAsync(ProductInner product) {
        return beginPutAsyncRelativeRetryNoStatusAsync(product).last().flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<ProductInner> putAsyncRelativeRetryNoStatusAsync() {
        final ProductInner product = null;
        return beginPutAsyncRelativeRetryNoStatusAsync(product).last().flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<ProductInner> putAsyncRelativeRetryNoStatusAsync(ProductInner product, Context context) {
        return beginPutAsyncRelativeRetryNoStatusAsync(product, context)
            .last()
            .flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProductInner putAsyncRelativeRetryNoStatus(ProductInner product) {
        return putAsyncRelativeRetryNoStatusAsync(product).block();
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProductInner putAsyncRelativeRetryNoStatus() {
        final ProductInner product = null;
        return putAsyncRelativeRetryNoStatusAsync(product).block();
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProductInner putAsyncRelativeRetryNoStatus(ProductInner product, Context context) {
        return putAsyncRelativeRetryNoStatusAsync(product, context).block();
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Flux<ByteBuffer>>> putAsyncRelativeRetryNoStatusPayloadWithResponseAsync(
        ProductInner product) {
        if (this.client.getEndpoint() == null) {
            return Mono
                .error(
                    new IllegalArgumentException(
                        "Parameter this.client.getEndpoint() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil
            .withContext(
                context ->
                    service.putAsyncRelativeRetryNoStatusPayload(this.client.getEndpoint(), product, accept, context))
            .contextWrite(context -> context.putAll(FluxUtil.toReactorContext(this.client.getContext()).readOnly()));
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Flux<ByteBuffer>>> putAsyncRelativeRetryNoStatusPayloadWithResponseAsync(
        ProductInner product, Context context) {
        if (this.client.getEndpoint() == null) {
            return Mono
                .error(
                    new IllegalArgumentException(
                        "Parameter this.client.getEndpoint() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        context = this.client.mergeContext(context);
        return service.putAsyncRelativeRetryNoStatusPayload(this.client.getEndpoint(), product, accept, context);
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PollerFlux<PollResult<ProductInner>, ProductInner> beginPutAsyncRelativeRetryNoStatusPayloadAsync(
        ProductInner product) {
        Mono<Response<Flux<ByteBuffer>>> mono = putAsyncRelativeRetryNoStatusPayloadWithResponseAsync(product);
        return this
            .client
            .<ProductInner, ProductInner>getLroResult(
                mono, this.client.getHttpPipeline(), ProductInner.class, ProductInner.class, Context.NONE);
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PollerFlux<PollResult<ProductInner>, ProductInner> beginPutAsyncRelativeRetryNoStatusPayloadAsync(
        ProductInner product, Context context) {
        context = this.client.mergeContext(context);
        Mono<Response<Flux<ByteBuffer>>> mono = putAsyncRelativeRetryNoStatusPayloadWithResponseAsync(product, context);
        return this
            .client
            .<ProductInner, ProductInner>getLroResult(
                mono, this.client.getHttpPipeline(), ProductInner.class, ProductInner.class, context);
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SyncPoller<PollResult<ProductInner>, ProductInner> beginPutAsyncRelativeRetryNoStatusPayload(
        ProductInner product) {
        return beginPutAsyncRelativeRetryNoStatusPayloadAsync(product).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SyncPoller<PollResult<ProductInner>, ProductInner> beginPutAsyncRelativeRetryNoStatusPayload(
        ProductInner product, Context context) {
        return beginPutAsyncRelativeRetryNoStatusPayloadAsync(product, context).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<ProductInner> putAsyncRelativeRetryNoStatusPayloadAsync(ProductInner product) {
        return beginPutAsyncRelativeRetryNoStatusPayloadAsync(product)
            .last()
            .flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<ProductInner> putAsyncRelativeRetryNoStatusPayloadAsync() {
        final ProductInner product = null;
        return beginPutAsyncRelativeRetryNoStatusPayloadAsync(product)
            .last()
            .flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<ProductInner> putAsyncRelativeRetryNoStatusPayloadAsync(ProductInner product, Context context) {
        return beginPutAsyncRelativeRetryNoStatusPayloadAsync(product, context)
            .last()
            .flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProductInner putAsyncRelativeRetryNoStatusPayload(ProductInner product) {
        return putAsyncRelativeRetryNoStatusPayloadAsync(product).block();
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProductInner putAsyncRelativeRetryNoStatusPayload() {
        final ProductInner product = null;
        return putAsyncRelativeRetryNoStatusPayloadAsync(product).block();
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProductInner putAsyncRelativeRetryNoStatusPayload(ProductInner product, Context context) {
        return putAsyncRelativeRetryNoStatusPayloadAsync(product, context).block();
    }

    /**
     * Long running delete request, service returns a 204 to the initial request, indicating success.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Flux<ByteBuffer>>> delete204SucceededWithResponseAsync() {
        if (this.client.getEndpoint() == null) {
            return Mono
                .error(
                    new IllegalArgumentException(
                        "Parameter this.client.getEndpoint() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil
            .withContext(context -> service.delete204Succeeded(this.client.getEndpoint(), accept, context))
            .contextWrite(context -> context.putAll(FluxUtil.toReactorContext(this.client.getContext()).readOnly()));
    }

    /**
     * Long running delete request, service returns a 204 to the initial request, indicating success.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Flux<ByteBuffer>>> delete204SucceededWithResponseAsync(Context context) {
        if (this.client.getEndpoint() == null) {
            return Mono
                .error(
                    new IllegalArgumentException(
                        "Parameter this.client.getEndpoint() is required and cannot be null."));
        }
        final String accept = "application/json";
        context = this.client.mergeContext(context);
        return service.delete204Succeeded(this.client.getEndpoint(), accept, context);
    }

    /**
     * Long running delete request, service returns a 204 to the initial request, indicating success.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PollerFlux<PollResult<Void>, Void> beginDelete204SucceededAsync() {
        Mono<Response<Flux<ByteBuffer>>> mono = delete204SucceededWithResponseAsync();
        return this
            .client
            .<Void, Void>getLroResult(mono, this.client.getHttpPipeline(), Void.class, Void.class, Context.NONE);
    }

    /**
     * Long running delete request, service returns a 204 to the initial request, indicating success.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PollerFlux<PollResult<Void>, Void> beginDelete204SucceededAsync(Context context) {
        context = this.client.mergeContext(context);
        Mono<Response<Flux<ByteBuffer>>> mono = delete204SucceededWithResponseAsync(context);
        return this
            .client
            .<Void, Void>getLroResult(mono, this.client.getHttpPipeline(), Void.class, Void.class, context);
    }

    /**
     * Long running delete request, service returns a 204 to the initial request, indicating success.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SyncPoller<PollResult<Void>, Void> beginDelete204Succeeded() {
        return beginDelete204SucceededAsync().getSyncPoller();
    }

    /**
     * Long running delete request, service returns a 204 to the initial request, indicating success.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SyncPoller<PollResult<Void>, Void> beginDelete204Succeeded(Context context) {
        return beginDelete204SucceededAsync(context).getSyncPoller();
    }

    /**
     * Long running delete request, service returns a 204 to the initial request, indicating success.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Void> delete204SucceededAsync() {
        return beginDelete204SucceededAsync().last().flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running delete request, service returns a 204 to the initial request, indicating success.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Void> delete204SucceededAsync(Context context) {
        return beginDelete204SucceededAsync(context).last().flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running delete request, service returns a 204 to the initial request, indicating success.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void delete204Succeeded() {
        delete204SucceededAsync().block();
    }

    /**
     * Long running delete request, service returns a 204 to the initial request, indicating success.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void delete204Succeeded(Context context) {
        delete204SucceededAsync(context).block();
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Flux<ByteBuffer>>> deleteAsyncRelativeRetryNoStatusWithResponseAsync() {
        if (this.client.getEndpoint() == null) {
            return Mono
                .error(
                    new IllegalArgumentException(
                        "Parameter this.client.getEndpoint() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil
            .withContext(
                context -> service.deleteAsyncRelativeRetryNoStatus(this.client.getEndpoint(), accept, context))
            .contextWrite(context -> context.putAll(FluxUtil.toReactorContext(this.client.getContext()).readOnly()));
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Flux<ByteBuffer>>> deleteAsyncRelativeRetryNoStatusWithResponseAsync(Context context) {
        if (this.client.getEndpoint() == null) {
            return Mono
                .error(
                    new IllegalArgumentException(
                        "Parameter this.client.getEndpoint() is required and cannot be null."));
        }
        final String accept = "application/json";
        context = this.client.mergeContext(context);
        return service.deleteAsyncRelativeRetryNoStatus(this.client.getEndpoint(), accept, context);
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PollerFlux<PollResult<Void>, Void> beginDeleteAsyncRelativeRetryNoStatusAsync() {
        Mono<Response<Flux<ByteBuffer>>> mono = deleteAsyncRelativeRetryNoStatusWithResponseAsync();
        return this
            .client
            .<Void, Void>getLroResult(mono, this.client.getHttpPipeline(), Void.class, Void.class, Context.NONE);
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PollerFlux<PollResult<Void>, Void> beginDeleteAsyncRelativeRetryNoStatusAsync(Context context) {
        context = this.client.mergeContext(context);
        Mono<Response<Flux<ByteBuffer>>> mono = deleteAsyncRelativeRetryNoStatusWithResponseAsync(context);
        return this
            .client
            .<Void, Void>getLroResult(mono, this.client.getHttpPipeline(), Void.class, Void.class, context);
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SyncPoller<PollResult<Void>, Void> beginDeleteAsyncRelativeRetryNoStatus() {
        return beginDeleteAsyncRelativeRetryNoStatusAsync().getSyncPoller();
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SyncPoller<PollResult<Void>, Void> beginDeleteAsyncRelativeRetryNoStatus(Context context) {
        return beginDeleteAsyncRelativeRetryNoStatusAsync(context).getSyncPoller();
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Void> deleteAsyncRelativeRetryNoStatusAsync() {
        return beginDeleteAsyncRelativeRetryNoStatusAsync().last().flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Void> deleteAsyncRelativeRetryNoStatusAsync(Context context) {
        return beginDeleteAsyncRelativeRetryNoStatusAsync(context)
            .last()
            .flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void deleteAsyncRelativeRetryNoStatus() {
        deleteAsyncRelativeRetryNoStatusAsync().block();
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void deleteAsyncRelativeRetryNoStatus(Context context) {
        deleteAsyncRelativeRetryNoStatusAsync(context).block();
    }

    /**
     * Long running post request, service returns a 202 to the initial request, without a location header.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Flux<ByteBuffer>>> post202NoLocationWithResponseAsync(ProductInner product) {
        if (this.client.getEndpoint() == null) {
            return Mono
                .error(
                    new IllegalArgumentException(
                        "Parameter this.client.getEndpoint() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil
            .withContext(context -> service.post202NoLocation(this.client.getEndpoint(), product, accept, context))
            .contextWrite(context -> context.putAll(FluxUtil.toReactorContext(this.client.getContext()).readOnly()));
    }

    /**
     * Long running post request, service returns a 202 to the initial request, without a location header.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Flux<ByteBuffer>>> post202NoLocationWithResponseAsync(ProductInner product, Context context) {
        if (this.client.getEndpoint() == null) {
            return Mono
                .error(
                    new IllegalArgumentException(
                        "Parameter this.client.getEndpoint() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        context = this.client.mergeContext(context);
        return service.post202NoLocation(this.client.getEndpoint(), product, accept, context);
    }

    /**
     * Long running post request, service returns a 202 to the initial request, without a location header.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PollerFlux<PollResult<Void>, Void> beginPost202NoLocationAsync(ProductInner product) {
        Mono<Response<Flux<ByteBuffer>>> mono = post202NoLocationWithResponseAsync(product);
        return this
            .client
            .<Void, Void>getLroResult(mono, this.client.getHttpPipeline(), Void.class, Void.class, Context.NONE);
    }

    /**
     * Long running post request, service returns a 202 to the initial request, without a location header.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PollerFlux<PollResult<Void>, Void> beginPost202NoLocationAsync(ProductInner product, Context context) {
        context = this.client.mergeContext(context);
        Mono<Response<Flux<ByteBuffer>>> mono = post202NoLocationWithResponseAsync(product, context);
        return this
            .client
            .<Void, Void>getLroResult(mono, this.client.getHttpPipeline(), Void.class, Void.class, context);
    }

    /**
     * Long running post request, service returns a 202 to the initial request, without a location header.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SyncPoller<PollResult<Void>, Void> beginPost202NoLocation(ProductInner product) {
        return beginPost202NoLocationAsync(product).getSyncPoller();
    }

    /**
     * Long running post request, service returns a 202 to the initial request, without a location header.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SyncPoller<PollResult<Void>, Void> beginPost202NoLocation(ProductInner product, Context context) {
        return beginPost202NoLocationAsync(product, context).getSyncPoller();
    }

    /**
     * Long running post request, service returns a 202 to the initial request, without a location header.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Void> post202NoLocationAsync(ProductInner product) {
        return beginPost202NoLocationAsync(product).last().flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running post request, service returns a 202 to the initial request, without a location header.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Void> post202NoLocationAsync() {
        final ProductInner product = null;
        return beginPost202NoLocationAsync(product).last().flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running post request, service returns a 202 to the initial request, without a location header.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Void> post202NoLocationAsync(ProductInner product, Context context) {
        return beginPost202NoLocationAsync(product, context).last().flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running post request, service returns a 202 to the initial request, without a location header.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void post202NoLocation(ProductInner product) {
        post202NoLocationAsync(product).block();
    }

    /**
     * Long running post request, service returns a 202 to the initial request, without a location header.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void post202NoLocation() {
        final ProductInner product = null;
        post202NoLocationAsync(product).block();
    }

    /**
     * Long running post request, service returns a 202 to the initial request, without a location header.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void post202NoLocation(ProductInner product, Context context) {
        post202NoLocationAsync(product, context).block();
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Flux<ByteBuffer>>> postAsyncRelativeRetryNoPayloadWithResponseAsync(ProductInner product) {
        if (this.client.getEndpoint() == null) {
            return Mono
                .error(
                    new IllegalArgumentException(
                        "Parameter this.client.getEndpoint() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil
            .withContext(
                context -> service.postAsyncRelativeRetryNoPayload(this.client.getEndpoint(), product, accept, context))
            .contextWrite(context -> context.putAll(FluxUtil.toReactorContext(this.client.getContext()).readOnly()));
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Flux<ByteBuffer>>> postAsyncRelativeRetryNoPayloadWithResponseAsync(
        ProductInner product, Context context) {
        if (this.client.getEndpoint() == null) {
            return Mono
                .error(
                    new IllegalArgumentException(
                        "Parameter this.client.getEndpoint() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        context = this.client.mergeContext(context);
        return service.postAsyncRelativeRetryNoPayload(this.client.getEndpoint(), product, accept, context);
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PollerFlux<PollResult<Void>, Void> beginPostAsyncRelativeRetryNoPayloadAsync(ProductInner product) {
        Mono<Response<Flux<ByteBuffer>>> mono = postAsyncRelativeRetryNoPayloadWithResponseAsync(product);
        return this
            .client
            .<Void, Void>getLroResult(mono, this.client.getHttpPipeline(), Void.class, Void.class, Context.NONE);
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PollerFlux<PollResult<Void>, Void> beginPostAsyncRelativeRetryNoPayloadAsync(
        ProductInner product, Context context) {
        context = this.client.mergeContext(context);
        Mono<Response<Flux<ByteBuffer>>> mono = postAsyncRelativeRetryNoPayloadWithResponseAsync(product, context);
        return this
            .client
            .<Void, Void>getLroResult(mono, this.client.getHttpPipeline(), Void.class, Void.class, context);
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SyncPoller<PollResult<Void>, Void> beginPostAsyncRelativeRetryNoPayload(ProductInner product) {
        return beginPostAsyncRelativeRetryNoPayloadAsync(product).getSyncPoller();
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SyncPoller<PollResult<Void>, Void> beginPostAsyncRelativeRetryNoPayload(
        ProductInner product, Context context) {
        return beginPostAsyncRelativeRetryNoPayloadAsync(product, context).getSyncPoller();
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Void> postAsyncRelativeRetryNoPayloadAsync(ProductInner product) {
        return beginPostAsyncRelativeRetryNoPayloadAsync(product).last().flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Void> postAsyncRelativeRetryNoPayloadAsync() {
        final ProductInner product = null;
        return beginPostAsyncRelativeRetryNoPayloadAsync(product).last().flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Void> postAsyncRelativeRetryNoPayloadAsync(ProductInner product, Context context) {
        return beginPostAsyncRelativeRetryNoPayloadAsync(product, context)
            .last()
            .flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void postAsyncRelativeRetryNoPayload(ProductInner product) {
        postAsyncRelativeRetryNoPayloadAsync(product).block();
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void postAsyncRelativeRetryNoPayload() {
        final ProductInner product = null;
        postAsyncRelativeRetryNoPayloadAsync(product).block();
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void postAsyncRelativeRetryNoPayload(ProductInner product, Context context) {
        postAsyncRelativeRetryNoPayloadAsync(product, context).block();
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that is not a valid json.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Flux<ByteBuffer>>> put200InvalidJsonWithResponseAsync(ProductInner product) {
        if (this.client.getEndpoint() == null) {
            return Mono
                .error(
                    new IllegalArgumentException(
                        "Parameter this.client.getEndpoint() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil
            .withContext(context -> service.put200InvalidJson(this.client.getEndpoint(), product, accept, context))
            .contextWrite(context -> context.putAll(FluxUtil.toReactorContext(this.client.getContext()).readOnly()));
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that is not a valid json.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Flux<ByteBuffer>>> put200InvalidJsonWithResponseAsync(ProductInner product, Context context) {
        if (this.client.getEndpoint() == null) {
            return Mono
                .error(
                    new IllegalArgumentException(
                        "Parameter this.client.getEndpoint() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        context = this.client.mergeContext(context);
        return service.put200InvalidJson(this.client.getEndpoint(), product, accept, context);
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that is not a valid json.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PollerFlux<PollResult<ProductInner>, ProductInner> beginPut200InvalidJsonAsync(ProductInner product) {
        Mono<Response<Flux<ByteBuffer>>> mono = put200InvalidJsonWithResponseAsync(product);
        return this
            .client
            .<ProductInner, ProductInner>getLroResult(
                mono, this.client.getHttpPipeline(), ProductInner.class, ProductInner.class, Context.NONE);
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that is not a valid json.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PollerFlux<PollResult<ProductInner>, ProductInner> beginPut200InvalidJsonAsync(
        ProductInner product, Context context) {
        context = this.client.mergeContext(context);
        Mono<Response<Flux<ByteBuffer>>> mono = put200InvalidJsonWithResponseAsync(product, context);
        return this
            .client
            .<ProductInner, ProductInner>getLroResult(
                mono, this.client.getHttpPipeline(), ProductInner.class, ProductInner.class, context);
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that is not a valid json.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SyncPoller<PollResult<ProductInner>, ProductInner> beginPut200InvalidJson(ProductInner product) {
        return beginPut200InvalidJsonAsync(product).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that is not a valid json.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SyncPoller<PollResult<ProductInner>, ProductInner> beginPut200InvalidJson(
        ProductInner product, Context context) {
        return beginPut200InvalidJsonAsync(product, context).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that is not a valid json.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<ProductInner> put200InvalidJsonAsync(ProductInner product) {
        return beginPut200InvalidJsonAsync(product).last().flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that is not a valid json.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<ProductInner> put200InvalidJsonAsync() {
        final ProductInner product = null;
        return beginPut200InvalidJsonAsync(product).last().flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that is not a valid json.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<ProductInner> put200InvalidJsonAsync(ProductInner product, Context context) {
        return beginPut200InvalidJsonAsync(product, context).last().flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that is not a valid json.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProductInner put200InvalidJson(ProductInner product) {
        return put200InvalidJsonAsync(product).block();
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that is not a valid json.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProductInner put200InvalidJson() {
        final ProductInner product = null;
        return put200InvalidJsonAsync(product).block();
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that is not a valid json.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProductInner put200InvalidJson(ProductInner product, Context context) {
        return put200InvalidJsonAsync(product, context).block();
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. The endpoint indicated in the Azure-AsyncOperation header is invalid.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Flux<ByteBuffer>>> putAsyncRelativeRetryInvalidHeaderWithResponseAsync(ProductInner product) {
        if (this.client.getEndpoint() == null) {
            return Mono
                .error(
                    new IllegalArgumentException(
                        "Parameter this.client.getEndpoint() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil
            .withContext(
                context ->
                    service.putAsyncRelativeRetryInvalidHeader(this.client.getEndpoint(), product, accept, context))
            .contextWrite(context -> context.putAll(FluxUtil.toReactorContext(this.client.getContext()).readOnly()));
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. The endpoint indicated in the Azure-AsyncOperation header is invalid.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Flux<ByteBuffer>>> putAsyncRelativeRetryInvalidHeaderWithResponseAsync(
        ProductInner product, Context context) {
        if (this.client.getEndpoint() == null) {
            return Mono
                .error(
                    new IllegalArgumentException(
                        "Parameter this.client.getEndpoint() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        context = this.client.mergeContext(context);
        return service.putAsyncRelativeRetryInvalidHeader(this.client.getEndpoint(), product, accept, context);
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. The endpoint indicated in the Azure-AsyncOperation header is invalid.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PollerFlux<PollResult<ProductInner>, ProductInner> beginPutAsyncRelativeRetryInvalidHeaderAsync(
        ProductInner product) {
        Mono<Response<Flux<ByteBuffer>>> mono = putAsyncRelativeRetryInvalidHeaderWithResponseAsync(product);
        return this
            .client
            .<ProductInner, ProductInner>getLroResult(
                mono, this.client.getHttpPipeline(), ProductInner.class, ProductInner.class, Context.NONE);
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. The endpoint indicated in the Azure-AsyncOperation header is invalid.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PollerFlux<PollResult<ProductInner>, ProductInner> beginPutAsyncRelativeRetryInvalidHeaderAsync(
        ProductInner product, Context context) {
        context = this.client.mergeContext(context);
        Mono<Response<Flux<ByteBuffer>>> mono = putAsyncRelativeRetryInvalidHeaderWithResponseAsync(product, context);
        return this
            .client
            .<ProductInner, ProductInner>getLroResult(
                mono, this.client.getHttpPipeline(), ProductInner.class, ProductInner.class, context);
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. The endpoint indicated in the Azure-AsyncOperation header is invalid.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SyncPoller<PollResult<ProductInner>, ProductInner> beginPutAsyncRelativeRetryInvalidHeader(
        ProductInner product) {
        return beginPutAsyncRelativeRetryInvalidHeaderAsync(product).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. The endpoint indicated in the Azure-AsyncOperation header is invalid.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SyncPoller<PollResult<ProductInner>, ProductInner> beginPutAsyncRelativeRetryInvalidHeader(
        ProductInner product, Context context) {
        return beginPutAsyncRelativeRetryInvalidHeaderAsync(product, context).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. The endpoint indicated in the Azure-AsyncOperation header is invalid.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<ProductInner> putAsyncRelativeRetryInvalidHeaderAsync(ProductInner product) {
        return beginPutAsyncRelativeRetryInvalidHeaderAsync(product)
            .last()
            .flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. The endpoint indicated in the Azure-AsyncOperation header is invalid.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<ProductInner> putAsyncRelativeRetryInvalidHeaderAsync() {
        final ProductInner product = null;
        return beginPutAsyncRelativeRetryInvalidHeaderAsync(product)
            .last()
            .flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. The endpoint indicated in the Azure-AsyncOperation header is invalid.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<ProductInner> putAsyncRelativeRetryInvalidHeaderAsync(ProductInner product, Context context) {
        return beginPutAsyncRelativeRetryInvalidHeaderAsync(product, context)
            .last()
            .flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. The endpoint indicated in the Azure-AsyncOperation header is invalid.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProductInner putAsyncRelativeRetryInvalidHeader(ProductInner product) {
        return putAsyncRelativeRetryInvalidHeaderAsync(product).block();
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. The endpoint indicated in the Azure-AsyncOperation header is invalid.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProductInner putAsyncRelativeRetryInvalidHeader() {
        final ProductInner product = null;
        return putAsyncRelativeRetryInvalidHeaderAsync(product).block();
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. The endpoint indicated in the Azure-AsyncOperation header is invalid.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProductInner putAsyncRelativeRetryInvalidHeader(ProductInner product, Context context) {
        return putAsyncRelativeRetryInvalidHeaderAsync(product, context).block();
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Flux<ByteBuffer>>> putAsyncRelativeRetryInvalidJsonPollingWithResponseAsync(
        ProductInner product) {
        if (this.client.getEndpoint() == null) {
            return Mono
                .error(
                    new IllegalArgumentException(
                        "Parameter this.client.getEndpoint() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil
            .withContext(
                context ->
                    service
                        .putAsyncRelativeRetryInvalidJsonPolling(this.client.getEndpoint(), product, accept, context))
            .contextWrite(context -> context.putAll(FluxUtil.toReactorContext(this.client.getContext()).readOnly()));
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Flux<ByteBuffer>>> putAsyncRelativeRetryInvalidJsonPollingWithResponseAsync(
        ProductInner product, Context context) {
        if (this.client.getEndpoint() == null) {
            return Mono
                .error(
                    new IllegalArgumentException(
                        "Parameter this.client.getEndpoint() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        context = this.client.mergeContext(context);
        return service.putAsyncRelativeRetryInvalidJsonPolling(this.client.getEndpoint(), product, accept, context);
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PollerFlux<PollResult<ProductInner>, ProductInner> beginPutAsyncRelativeRetryInvalidJsonPollingAsync(
        ProductInner product) {
        Mono<Response<Flux<ByteBuffer>>> mono = putAsyncRelativeRetryInvalidJsonPollingWithResponseAsync(product);
        return this
            .client
            .<ProductInner, ProductInner>getLroResult(
                mono, this.client.getHttpPipeline(), ProductInner.class, ProductInner.class, Context.NONE);
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PollerFlux<PollResult<ProductInner>, ProductInner> beginPutAsyncRelativeRetryInvalidJsonPollingAsync(
        ProductInner product, Context context) {
        context = this.client.mergeContext(context);
        Mono<Response<Flux<ByteBuffer>>> mono =
            putAsyncRelativeRetryInvalidJsonPollingWithResponseAsync(product, context);
        return this
            .client
            .<ProductInner, ProductInner>getLroResult(
                mono, this.client.getHttpPipeline(), ProductInner.class, ProductInner.class, context);
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SyncPoller<PollResult<ProductInner>, ProductInner> beginPutAsyncRelativeRetryInvalidJsonPolling(
        ProductInner product) {
        return beginPutAsyncRelativeRetryInvalidJsonPollingAsync(product).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SyncPoller<PollResult<ProductInner>, ProductInner> beginPutAsyncRelativeRetryInvalidJsonPolling(
        ProductInner product, Context context) {
        return beginPutAsyncRelativeRetryInvalidJsonPollingAsync(product, context).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<ProductInner> putAsyncRelativeRetryInvalidJsonPollingAsync(ProductInner product) {
        return beginPutAsyncRelativeRetryInvalidJsonPollingAsync(product)
            .last()
            .flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<ProductInner> putAsyncRelativeRetryInvalidJsonPollingAsync() {
        final ProductInner product = null;
        return beginPutAsyncRelativeRetryInvalidJsonPollingAsync(product)
            .last()
            .flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<ProductInner> putAsyncRelativeRetryInvalidJsonPollingAsync(ProductInner product, Context context) {
        return beginPutAsyncRelativeRetryInvalidJsonPollingAsync(product, context)
            .last()
            .flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProductInner putAsyncRelativeRetryInvalidJsonPolling(ProductInner product) {
        return putAsyncRelativeRetryInvalidJsonPollingAsync(product).block();
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProductInner putAsyncRelativeRetryInvalidJsonPolling() {
        final ProductInner product = null;
        return putAsyncRelativeRetryInvalidJsonPollingAsync(product).block();
    }

    /**
     * Long running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProductInner putAsyncRelativeRetryInvalidJsonPolling(ProductInner product, Context context) {
        return putAsyncRelativeRetryInvalidJsonPollingAsync(product, context).block();
    }

    /**
     * Long running delete request, service returns a 202 to the initial request receing a reponse with an invalid
     * 'Location' and 'Retry-After' headers.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Flux<ByteBuffer>>> delete202RetryInvalidHeaderWithResponseAsync() {
        if (this.client.getEndpoint() == null) {
            return Mono
                .error(
                    new IllegalArgumentException(
                        "Parameter this.client.getEndpoint() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil
            .withContext(context -> service.delete202RetryInvalidHeader(this.client.getEndpoint(), accept, context))
            .contextWrite(context -> context.putAll(FluxUtil.toReactorContext(this.client.getContext()).readOnly()));
    }

    /**
     * Long running delete request, service returns a 202 to the initial request receing a reponse with an invalid
     * 'Location' and 'Retry-After' headers.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Flux<ByteBuffer>>> delete202RetryInvalidHeaderWithResponseAsync(Context context) {
        if (this.client.getEndpoint() == null) {
            return Mono
                .error(
                    new IllegalArgumentException(
                        "Parameter this.client.getEndpoint() is required and cannot be null."));
        }
        final String accept = "application/json";
        context = this.client.mergeContext(context);
        return service.delete202RetryInvalidHeader(this.client.getEndpoint(), accept, context);
    }

    /**
     * Long running delete request, service returns a 202 to the initial request receing a reponse with an invalid
     * 'Location' and 'Retry-After' headers.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PollerFlux<PollResult<Void>, Void> beginDelete202RetryInvalidHeaderAsync() {
        Mono<Response<Flux<ByteBuffer>>> mono = delete202RetryInvalidHeaderWithResponseAsync();
        return this
            .client
            .<Void, Void>getLroResult(mono, this.client.getHttpPipeline(), Void.class, Void.class, Context.NONE);
    }

    /**
     * Long running delete request, service returns a 202 to the initial request receing a reponse with an invalid
     * 'Location' and 'Retry-After' headers.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PollerFlux<PollResult<Void>, Void> beginDelete202RetryInvalidHeaderAsync(Context context) {
        context = this.client.mergeContext(context);
        Mono<Response<Flux<ByteBuffer>>> mono = delete202RetryInvalidHeaderWithResponseAsync(context);
        return this
            .client
            .<Void, Void>getLroResult(mono, this.client.getHttpPipeline(), Void.class, Void.class, context);
    }

    /**
     * Long running delete request, service returns a 202 to the initial request receing a reponse with an invalid
     * 'Location' and 'Retry-After' headers.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SyncPoller<PollResult<Void>, Void> beginDelete202RetryInvalidHeader() {
        return beginDelete202RetryInvalidHeaderAsync().getSyncPoller();
    }

    /**
     * Long running delete request, service returns a 202 to the initial request receing a reponse with an invalid
     * 'Location' and 'Retry-After' headers.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SyncPoller<PollResult<Void>, Void> beginDelete202RetryInvalidHeader(Context context) {
        return beginDelete202RetryInvalidHeaderAsync(context).getSyncPoller();
    }

    /**
     * Long running delete request, service returns a 202 to the initial request receing a reponse with an invalid
     * 'Location' and 'Retry-After' headers.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Void> delete202RetryInvalidHeaderAsync() {
        return beginDelete202RetryInvalidHeaderAsync().last().flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running delete request, service returns a 202 to the initial request receing a reponse with an invalid
     * 'Location' and 'Retry-After' headers.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Void> delete202RetryInvalidHeaderAsync(Context context) {
        return beginDelete202RetryInvalidHeaderAsync(context).last().flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running delete request, service returns a 202 to the initial request receing a reponse with an invalid
     * 'Location' and 'Retry-After' headers.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void delete202RetryInvalidHeader() {
        delete202RetryInvalidHeaderAsync().block();
    }

    /**
     * Long running delete request, service returns a 202 to the initial request receing a reponse with an invalid
     * 'Location' and 'Retry-After' headers.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void delete202RetryInvalidHeader(Context context) {
        delete202RetryInvalidHeaderAsync(context).block();
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. The endpoint indicated in the
     * Azure-AsyncOperation header is invalid.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Flux<ByteBuffer>>> deleteAsyncRelativeRetryInvalidHeaderWithResponseAsync() {
        if (this.client.getEndpoint() == null) {
            return Mono
                .error(
                    new IllegalArgumentException(
                        "Parameter this.client.getEndpoint() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil
            .withContext(
                context -> service.deleteAsyncRelativeRetryInvalidHeader(this.client.getEndpoint(), accept, context))
            .contextWrite(context -> context.putAll(FluxUtil.toReactorContext(this.client.getContext()).readOnly()));
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. The endpoint indicated in the
     * Azure-AsyncOperation header is invalid.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Flux<ByteBuffer>>> deleteAsyncRelativeRetryInvalidHeaderWithResponseAsync(Context context) {
        if (this.client.getEndpoint() == null) {
            return Mono
                .error(
                    new IllegalArgumentException(
                        "Parameter this.client.getEndpoint() is required and cannot be null."));
        }
        final String accept = "application/json";
        context = this.client.mergeContext(context);
        return service.deleteAsyncRelativeRetryInvalidHeader(this.client.getEndpoint(), accept, context);
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. The endpoint indicated in the
     * Azure-AsyncOperation header is invalid.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PollerFlux<PollResult<Void>, Void> beginDeleteAsyncRelativeRetryInvalidHeaderAsync() {
        Mono<Response<Flux<ByteBuffer>>> mono = deleteAsyncRelativeRetryInvalidHeaderWithResponseAsync();
        return this
            .client
            .<Void, Void>getLroResult(mono, this.client.getHttpPipeline(), Void.class, Void.class, Context.NONE);
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. The endpoint indicated in the
     * Azure-AsyncOperation header is invalid.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PollerFlux<PollResult<Void>, Void> beginDeleteAsyncRelativeRetryInvalidHeaderAsync(Context context) {
        context = this.client.mergeContext(context);
        Mono<Response<Flux<ByteBuffer>>> mono = deleteAsyncRelativeRetryInvalidHeaderWithResponseAsync(context);
        return this
            .client
            .<Void, Void>getLroResult(mono, this.client.getHttpPipeline(), Void.class, Void.class, context);
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. The endpoint indicated in the
     * Azure-AsyncOperation header is invalid.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SyncPoller<PollResult<Void>, Void> beginDeleteAsyncRelativeRetryInvalidHeader() {
        return beginDeleteAsyncRelativeRetryInvalidHeaderAsync().getSyncPoller();
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. The endpoint indicated in the
     * Azure-AsyncOperation header is invalid.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SyncPoller<PollResult<Void>, Void> beginDeleteAsyncRelativeRetryInvalidHeader(Context context) {
        return beginDeleteAsyncRelativeRetryInvalidHeaderAsync(context).getSyncPoller();
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. The endpoint indicated in the
     * Azure-AsyncOperation header is invalid.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Void> deleteAsyncRelativeRetryInvalidHeaderAsync() {
        return beginDeleteAsyncRelativeRetryInvalidHeaderAsync().last().flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. The endpoint indicated in the
     * Azure-AsyncOperation header is invalid.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Void> deleteAsyncRelativeRetryInvalidHeaderAsync(Context context) {
        return beginDeleteAsyncRelativeRetryInvalidHeaderAsync(context)
            .last()
            .flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. The endpoint indicated in the
     * Azure-AsyncOperation header is invalid.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void deleteAsyncRelativeRetryInvalidHeader() {
        deleteAsyncRelativeRetryInvalidHeaderAsync().block();
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. The endpoint indicated in the
     * Azure-AsyncOperation header is invalid.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void deleteAsyncRelativeRetryInvalidHeader(Context context) {
        deleteAsyncRelativeRetryInvalidHeaderAsync(context).block();
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Flux<ByteBuffer>>> deleteAsyncRelativeRetryInvalidJsonPollingWithResponseAsync() {
        if (this.client.getEndpoint() == null) {
            return Mono
                .error(
                    new IllegalArgumentException(
                        "Parameter this.client.getEndpoint() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil
            .withContext(
                context ->
                    service.deleteAsyncRelativeRetryInvalidJsonPolling(this.client.getEndpoint(), accept, context))
            .contextWrite(context -> context.putAll(FluxUtil.toReactorContext(this.client.getContext()).readOnly()));
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Flux<ByteBuffer>>> deleteAsyncRelativeRetryInvalidJsonPollingWithResponseAsync(
        Context context) {
        if (this.client.getEndpoint() == null) {
            return Mono
                .error(
                    new IllegalArgumentException(
                        "Parameter this.client.getEndpoint() is required and cannot be null."));
        }
        final String accept = "application/json";
        context = this.client.mergeContext(context);
        return service.deleteAsyncRelativeRetryInvalidJsonPolling(this.client.getEndpoint(), accept, context);
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PollerFlux<PollResult<Void>, Void> beginDeleteAsyncRelativeRetryInvalidJsonPollingAsync() {
        Mono<Response<Flux<ByteBuffer>>> mono = deleteAsyncRelativeRetryInvalidJsonPollingWithResponseAsync();
        return this
            .client
            .<Void, Void>getLroResult(mono, this.client.getHttpPipeline(), Void.class, Void.class, Context.NONE);
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PollerFlux<PollResult<Void>, Void> beginDeleteAsyncRelativeRetryInvalidJsonPollingAsync(Context context) {
        context = this.client.mergeContext(context);
        Mono<Response<Flux<ByteBuffer>>> mono = deleteAsyncRelativeRetryInvalidJsonPollingWithResponseAsync(context);
        return this
            .client
            .<Void, Void>getLroResult(mono, this.client.getHttpPipeline(), Void.class, Void.class, context);
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SyncPoller<PollResult<Void>, Void> beginDeleteAsyncRelativeRetryInvalidJsonPolling() {
        return beginDeleteAsyncRelativeRetryInvalidJsonPollingAsync().getSyncPoller();
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SyncPoller<PollResult<Void>, Void> beginDeleteAsyncRelativeRetryInvalidJsonPolling(Context context) {
        return beginDeleteAsyncRelativeRetryInvalidJsonPollingAsync(context).getSyncPoller();
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Void> deleteAsyncRelativeRetryInvalidJsonPollingAsync() {
        return beginDeleteAsyncRelativeRetryInvalidJsonPollingAsync()
            .last()
            .flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Void> deleteAsyncRelativeRetryInvalidJsonPollingAsync(Context context) {
        return beginDeleteAsyncRelativeRetryInvalidJsonPollingAsync(context)
            .last()
            .flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void deleteAsyncRelativeRetryInvalidJsonPolling() {
        deleteAsyncRelativeRetryInvalidJsonPollingAsync().block();
    }

    /**
     * Long running delete request, service returns a 202 to the initial request. Poll the endpoint indicated in the
     * Azure-AsyncOperation header for operation status.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void deleteAsyncRelativeRetryInvalidJsonPolling(Context context) {
        deleteAsyncRelativeRetryInvalidJsonPollingAsync(context).block();
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with invalid 'Location' and
     * 'Retry-After' headers.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Flux<ByteBuffer>>> post202RetryInvalidHeaderWithResponseAsync(ProductInner product) {
        if (this.client.getEndpoint() == null) {
            return Mono
                .error(
                    new IllegalArgumentException(
                        "Parameter this.client.getEndpoint() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil
            .withContext(
                context -> service.post202RetryInvalidHeader(this.client.getEndpoint(), product, accept, context))
            .contextWrite(context -> context.putAll(FluxUtil.toReactorContext(this.client.getContext()).readOnly()));
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with invalid 'Location' and
     * 'Retry-After' headers.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Flux<ByteBuffer>>> post202RetryInvalidHeaderWithResponseAsync(
        ProductInner product, Context context) {
        if (this.client.getEndpoint() == null) {
            return Mono
                .error(
                    new IllegalArgumentException(
                        "Parameter this.client.getEndpoint() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        context = this.client.mergeContext(context);
        return service.post202RetryInvalidHeader(this.client.getEndpoint(), product, accept, context);
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with invalid 'Location' and
     * 'Retry-After' headers.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PollerFlux<PollResult<Void>, Void> beginPost202RetryInvalidHeaderAsync(ProductInner product) {
        Mono<Response<Flux<ByteBuffer>>> mono = post202RetryInvalidHeaderWithResponseAsync(product);
        return this
            .client
            .<Void, Void>getLroResult(mono, this.client.getHttpPipeline(), Void.class, Void.class, Context.NONE);
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with invalid 'Location' and
     * 'Retry-After' headers.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PollerFlux<PollResult<Void>, Void> beginPost202RetryInvalidHeaderAsync(
        ProductInner product, Context context) {
        context = this.client.mergeContext(context);
        Mono<Response<Flux<ByteBuffer>>> mono = post202RetryInvalidHeaderWithResponseAsync(product, context);
        return this
            .client
            .<Void, Void>getLroResult(mono, this.client.getHttpPipeline(), Void.class, Void.class, context);
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with invalid 'Location' and
     * 'Retry-After' headers.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SyncPoller<PollResult<Void>, Void> beginPost202RetryInvalidHeader(ProductInner product) {
        return beginPost202RetryInvalidHeaderAsync(product).getSyncPoller();
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with invalid 'Location' and
     * 'Retry-After' headers.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SyncPoller<PollResult<Void>, Void> beginPost202RetryInvalidHeader(ProductInner product, Context context) {
        return beginPost202RetryInvalidHeaderAsync(product, context).getSyncPoller();
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with invalid 'Location' and
     * 'Retry-After' headers.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Void> post202RetryInvalidHeaderAsync(ProductInner product) {
        return beginPost202RetryInvalidHeaderAsync(product).last().flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with invalid 'Location' and
     * 'Retry-After' headers.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Void> post202RetryInvalidHeaderAsync() {
        final ProductInner product = null;
        return beginPost202RetryInvalidHeaderAsync(product).last().flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with invalid 'Location' and
     * 'Retry-After' headers.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Void> post202RetryInvalidHeaderAsync(ProductInner product, Context context) {
        return beginPost202RetryInvalidHeaderAsync(product, context)
            .last()
            .flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with invalid 'Location' and
     * 'Retry-After' headers.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void post202RetryInvalidHeader(ProductInner product) {
        post202RetryInvalidHeaderAsync(product).block();
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with invalid 'Location' and
     * 'Retry-After' headers.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void post202RetryInvalidHeader() {
        final ProductInner product = null;
        post202RetryInvalidHeaderAsync(product).block();
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with invalid 'Location' and
     * 'Retry-After' headers.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void post202RetryInvalidHeader(ProductInner product, Context context) {
        post202RetryInvalidHeaderAsync(product, context).block();
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. The endpoint indicated in the Azure-AsyncOperation header is invalid.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Flux<ByteBuffer>>> postAsyncRelativeRetryInvalidHeaderWithResponseAsync(
        ProductInner product) {
        if (this.client.getEndpoint() == null) {
            return Mono
                .error(
                    new IllegalArgumentException(
                        "Parameter this.client.getEndpoint() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil
            .withContext(
                context ->
                    service.postAsyncRelativeRetryInvalidHeader(this.client.getEndpoint(), product, accept, context))
            .contextWrite(context -> context.putAll(FluxUtil.toReactorContext(this.client.getContext()).readOnly()));
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. The endpoint indicated in the Azure-AsyncOperation header is invalid.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Flux<ByteBuffer>>> postAsyncRelativeRetryInvalidHeaderWithResponseAsync(
        ProductInner product, Context context) {
        if (this.client.getEndpoint() == null) {
            return Mono
                .error(
                    new IllegalArgumentException(
                        "Parameter this.client.getEndpoint() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        context = this.client.mergeContext(context);
        return service.postAsyncRelativeRetryInvalidHeader(this.client.getEndpoint(), product, accept, context);
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. The endpoint indicated in the Azure-AsyncOperation header is invalid.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PollerFlux<PollResult<Void>, Void> beginPostAsyncRelativeRetryInvalidHeaderAsync(ProductInner product) {
        Mono<Response<Flux<ByteBuffer>>> mono = postAsyncRelativeRetryInvalidHeaderWithResponseAsync(product);
        return this
            .client
            .<Void, Void>getLroResult(mono, this.client.getHttpPipeline(), Void.class, Void.class, Context.NONE);
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. The endpoint indicated in the Azure-AsyncOperation header is invalid.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PollerFlux<PollResult<Void>, Void> beginPostAsyncRelativeRetryInvalidHeaderAsync(
        ProductInner product, Context context) {
        context = this.client.mergeContext(context);
        Mono<Response<Flux<ByteBuffer>>> mono = postAsyncRelativeRetryInvalidHeaderWithResponseAsync(product, context);
        return this
            .client
            .<Void, Void>getLroResult(mono, this.client.getHttpPipeline(), Void.class, Void.class, context);
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. The endpoint indicated in the Azure-AsyncOperation header is invalid.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SyncPoller<PollResult<Void>, Void> beginPostAsyncRelativeRetryInvalidHeader(ProductInner product) {
        return beginPostAsyncRelativeRetryInvalidHeaderAsync(product).getSyncPoller();
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. The endpoint indicated in the Azure-AsyncOperation header is invalid.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SyncPoller<PollResult<Void>, Void> beginPostAsyncRelativeRetryInvalidHeader(
        ProductInner product, Context context) {
        return beginPostAsyncRelativeRetryInvalidHeaderAsync(product, context).getSyncPoller();
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. The endpoint indicated in the Azure-AsyncOperation header is invalid.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Void> postAsyncRelativeRetryInvalidHeaderAsync(ProductInner product) {
        return beginPostAsyncRelativeRetryInvalidHeaderAsync(product)
            .last()
            .flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. The endpoint indicated in the Azure-AsyncOperation header is invalid.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Void> postAsyncRelativeRetryInvalidHeaderAsync() {
        final ProductInner product = null;
        return beginPostAsyncRelativeRetryInvalidHeaderAsync(product)
            .last()
            .flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. The endpoint indicated in the Azure-AsyncOperation header is invalid.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Void> postAsyncRelativeRetryInvalidHeaderAsync(ProductInner product, Context context) {
        return beginPostAsyncRelativeRetryInvalidHeaderAsync(product, context)
            .last()
            .flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. The endpoint indicated in the Azure-AsyncOperation header is invalid.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void postAsyncRelativeRetryInvalidHeader(ProductInner product) {
        postAsyncRelativeRetryInvalidHeaderAsync(product).block();
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. The endpoint indicated in the Azure-AsyncOperation header is invalid.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void postAsyncRelativeRetryInvalidHeader() {
        final ProductInner product = null;
        postAsyncRelativeRetryInvalidHeaderAsync(product).block();
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. The endpoint indicated in the Azure-AsyncOperation header is invalid.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void postAsyncRelativeRetryInvalidHeader(ProductInner product, Context context) {
        postAsyncRelativeRetryInvalidHeaderAsync(product, context).block();
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Flux<ByteBuffer>>> postAsyncRelativeRetryInvalidJsonPollingWithResponseAsync(
        ProductInner product) {
        if (this.client.getEndpoint() == null) {
            return Mono
                .error(
                    new IllegalArgumentException(
                        "Parameter this.client.getEndpoint() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil
            .withContext(
                context ->
                    service
                        .postAsyncRelativeRetryInvalidJsonPolling(this.client.getEndpoint(), product, accept, context))
            .contextWrite(context -> context.putAll(FluxUtil.toReactorContext(this.client.getContext()).readOnly()));
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Flux<ByteBuffer>>> postAsyncRelativeRetryInvalidJsonPollingWithResponseAsync(
        ProductInner product, Context context) {
        if (this.client.getEndpoint() == null) {
            return Mono
                .error(
                    new IllegalArgumentException(
                        "Parameter this.client.getEndpoint() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        context = this.client.mergeContext(context);
        return service.postAsyncRelativeRetryInvalidJsonPolling(this.client.getEndpoint(), product, accept, context);
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PollerFlux<PollResult<Void>, Void> beginPostAsyncRelativeRetryInvalidJsonPollingAsync(
        ProductInner product) {
        Mono<Response<Flux<ByteBuffer>>> mono = postAsyncRelativeRetryInvalidJsonPollingWithResponseAsync(product);
        return this
            .client
            .<Void, Void>getLroResult(mono, this.client.getHttpPipeline(), Void.class, Void.class, Context.NONE);
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PollerFlux<PollResult<Void>, Void> beginPostAsyncRelativeRetryInvalidJsonPollingAsync(
        ProductInner product, Context context) {
        context = this.client.mergeContext(context);
        Mono<Response<Flux<ByteBuffer>>> mono =
            postAsyncRelativeRetryInvalidJsonPollingWithResponseAsync(product, context);
        return this
            .client
            .<Void, Void>getLroResult(mono, this.client.getHttpPipeline(), Void.class, Void.class, context);
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SyncPoller<PollResult<Void>, Void> beginPostAsyncRelativeRetryInvalidJsonPolling(ProductInner product) {
        return beginPostAsyncRelativeRetryInvalidJsonPollingAsync(product).getSyncPoller();
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SyncPoller<PollResult<Void>, Void> beginPostAsyncRelativeRetryInvalidJsonPolling(
        ProductInner product, Context context) {
        return beginPostAsyncRelativeRetryInvalidJsonPollingAsync(product, context).getSyncPoller();
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Void> postAsyncRelativeRetryInvalidJsonPollingAsync(ProductInner product) {
        return beginPostAsyncRelativeRetryInvalidJsonPollingAsync(product)
            .last()
            .flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Void> postAsyncRelativeRetryInvalidJsonPollingAsync() {
        final ProductInner product = null;
        return beginPostAsyncRelativeRetryInvalidJsonPollingAsync(product)
            .last()
            .flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Void> postAsyncRelativeRetryInvalidJsonPollingAsync(ProductInner product, Context context) {
        return beginPostAsyncRelativeRetryInvalidJsonPollingAsync(product, context)
            .last()
            .flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void postAsyncRelativeRetryInvalidJsonPolling(ProductInner product) {
        postAsyncRelativeRetryInvalidJsonPollingAsync(product).block();
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void postAsyncRelativeRetryInvalidJsonPolling() {
        final ProductInner product = null;
        postAsyncRelativeRetryInvalidJsonPollingAsync(product).block();
    }

    /**
     * Long running post request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void postAsyncRelativeRetryInvalidJsonPolling(ProductInner product, Context context) {
        postAsyncRelativeRetryInvalidJsonPollingAsync(product, context).block();
    }
}
