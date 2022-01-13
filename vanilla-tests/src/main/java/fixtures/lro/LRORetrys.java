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
import fixtures.lro.models.LRORetrysDelete202Retry200Response;
import fixtures.lro.models.LRORetrysDeleteAsyncRelativeRetrySucceededResponse;
import fixtures.lro.models.LRORetrysDeleteProvisioning202Accepted200SucceededResponse;
import fixtures.lro.models.LRORetrysPost202Retry200Response;
import fixtures.lro.models.LRORetrysPostAsyncRelativeRetrySucceededResponse;
import fixtures.lro.models.LRORetrysPutAsyncRelativeRetrySucceededResponse;
import fixtures.lro.models.Product;
import java.time.Duration;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in LRORetrys. */
public final class LRORetrys {
    /** The proxy service used to perform REST calls. */
    private final LRORetrysService service;

    /** The service client containing this operation class. */
    private final AutoRestLongRunningOperationTestService client;

    /**
     * Initializes an instance of LRORetrys.
     *
     * @param client the instance of the service client containing this operation class.
     */
    LRORetrys(AutoRestLongRunningOperationTestService client) {
        this.service =
                RestProxy.create(LRORetrysService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for AutoRestLongRunningOperationTestServiceLRORetrys to be used by the
     * proxy service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestLongRunningO")
    private interface LRORetrysService {
        @Put("/lro/retryerror/put/201/creating/succeeded/200")
        @ExpectedResponses({200, 201})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<Response<Product>> put201CreatingSucceeded200(
                @HostParam("$host") String host,
                @BodyParam("application/json") Product product,
                @HeaderParam("Accept") String accept,
                Context context);

        @Put("/lro/retryerror/putasync/retry/succeeded")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LRORetrysPutAsyncRelativeRetrySucceededResponse> putAsyncRelativeRetrySucceeded(
                @HostParam("$host") String host,
                @BodyParam("application/json") Product product,
                @HeaderParam("Accept") String accept,
                Context context);

        @Delete("/lro/retryerror/delete/provisioning/202/accepted/200/succeeded")
        @ExpectedResponses({200, 202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LRORetrysDeleteProvisioning202Accepted200SucceededResponse> deleteProvisioning202Accepted200Succeeded(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Delete("/lro/retryerror/delete/202/retry/200")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LRORetrysDelete202Retry200Response> delete202Retry200(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Delete("/lro/retryerror/deleteasync/retry/succeeded")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LRORetrysDeleteAsyncRelativeRetrySucceededResponse> deleteAsyncRelativeRetrySucceeded(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Post("/lro/retryerror/post/202/retry/200")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LRORetrysPost202Retry200Response> post202Retry200(
                @HostParam("$host") String host,
                @BodyParam("application/json") Product product,
                @HeaderParam("Accept") String accept,
                Context context);

        @Post("/lro/retryerror/postasync/retry/succeeded")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LRORetrysPostAsyncRelativeRetrySucceededResponse> postAsyncRelativeRetrySucceeded(
                @HostParam("$host") String host,
                @BodyParam("application/json") Product product,
                @HeaderParam("Accept") String accept,
                Context context);
    }

    /**
     * Long running put request, service returns a 500, then a 201 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Polls return this value until the last poll returns a ‘200’ with
     * ProvisioningState=’Succeeded’.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Product>> put201CreatingSucceeded200WithResponseAsync(Product product) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.put201CreatingSucceeded200(this.client.getHost(), product, accept, context));
    }

    /**
     * Long running put request, service returns a 500, then a 201 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Polls return this value until the last poll returns a ‘200’ with
     * ProvisioningState=’Succeeded’.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<Product, Product> beginPut201CreatingSucceeded200Async(Product product) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.put201CreatingSucceeded200WithResponseAsync(product),
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
     * Long running put request, service returns a 500, then a 201 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Polls return this value until the last poll returns a ‘200’ with
     * ProvisioningState=’Succeeded’.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<Product, Product> beginPut201CreatingSucceeded200(Product product) {
        return this.beginPut201CreatingSucceeded200Async(product).getSyncPoller();
    }

    /**
     * Long running put request, service returns a 500, then a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LRORetrysPutAsyncRelativeRetrySucceededResponse> putAsyncRelativeRetrySucceededWithResponseAsync(
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
                context -> service.putAsyncRelativeRetrySucceeded(this.client.getHost(), product, accept, context));
    }

    /**
     * Long running put request, service returns a 500, then a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<Product, Product> beginPutAsyncRelativeRetrySucceededAsync(Product product) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.putAsyncRelativeRetrySucceededWithResponseAsync(product),
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
     * Long running put request, service returns a 500, then a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<Product, Product> beginPutAsyncRelativeRetrySucceeded(Product product) {
        return this.beginPutAsyncRelativeRetrySucceededAsync(product).getSyncPoller();
    }

    /**
     * Long running delete request, service returns a 500, then a 202 to the initial request, with an entity that
     * contains ProvisioningState=’Accepted’. Polls return this value until the last poll returns a ‘200’ with
     * ProvisioningState=’Succeeded’.
     *
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LRORetrysDeleteProvisioning202Accepted200SucceededResponse>
            deleteProvisioning202Accepted200SucceededWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.deleteProvisioning202Accepted200Succeeded(this.client.getHost(), accept, context));
    }

    /**
     * Long running delete request, service returns a 500, then a 202 to the initial request, with an entity that
     * contains ProvisioningState=’Accepted’. Polls return this value until the last poll returns a ‘200’ with
     * ProvisioningState=’Succeeded’.
     *
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<Product, Product> beginDeleteProvisioning202Accepted200SucceededAsync() {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.deleteProvisioning202Accepted200SucceededWithResponseAsync(),
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
     * Long running delete request, service returns a 500, then a 202 to the initial request, with an entity that
     * contains ProvisioningState=’Accepted’. Polls return this value until the last poll returns a ‘200’ with
     * ProvisioningState=’Succeeded’.
     *
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<Product, Product> beginDeleteProvisioning202Accepted200Succeeded() {
        return this.beginDeleteProvisioning202Accepted200SucceededAsync().getSyncPoller();
    }

    /**
     * Long running delete request, service returns a 500, then a 202 to the initial request. Polls return this value
     * until the last poll returns a ‘200’ with ProvisioningState=’Succeeded’.
     *
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LRORetrysDelete202Retry200Response> delete202Retry200WithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.delete202Retry200(this.client.getHost(), accept, context));
    }

    /**
     * Long running delete request, service returns a 500, then a 202 to the initial request. Polls return this value
     * until the last poll returns a ‘200’ with ProvisioningState=’Succeeded’.
     *
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginDelete202Retry200Async() {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.delete202Retry200WithResponseAsync(),
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
     * Long running delete request, service returns a 500, then a 202 to the initial request. Polls return this value
     * until the last poll returns a ‘200’ with ProvisioningState=’Succeeded’.
     *
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginDelete202Retry200() {
        return this.beginDelete202Retry200Async().getSyncPoller();
    }

    /**
     * Long running delete request, service returns a 500, then a 202 to the initial request. Poll the endpoint
     * indicated in the Azure-AsyncOperation header for operation status.
     *
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LRORetrysDeleteAsyncRelativeRetrySucceededResponse>
            deleteAsyncRelativeRetrySucceededWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.deleteAsyncRelativeRetrySucceeded(this.client.getHost(), accept, context));
    }

    /**
     * Long running delete request, service returns a 500, then a 202 to the initial request. Poll the endpoint
     * indicated in the Azure-AsyncOperation header for operation status.
     *
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginDeleteAsyncRelativeRetrySucceededAsync() {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.deleteAsyncRelativeRetrySucceededWithResponseAsync(),
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
     * Long running delete request, service returns a 500, then a 202 to the initial request. Poll the endpoint
     * indicated in the Azure-AsyncOperation header for operation status.
     *
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginDeleteAsyncRelativeRetrySucceeded() {
        return this.beginDeleteAsyncRelativeRetrySucceededAsync().getSyncPoller();
    }

    /**
     * Long running post request, service returns a 500, then a 202 to the initial request, with 'Location' and
     * 'Retry-After' headers, Polls return a 200 with a response body after success.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LRORetrysPost202Retry200Response> post202Retry200WithResponseAsync(Product product) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.post202Retry200(this.client.getHost(), product, accept, context));
    }

    /**
     * Long running post request, service returns a 500, then a 202 to the initial request, with 'Location' and
     * 'Retry-After' headers, Polls return a 200 with a response body after success.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginPost202Retry200Async(Product product) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.post202Retry200WithResponseAsync(product),
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
     * Long running post request, service returns a 500, then a 202 to the initial request, with 'Location' and
     * 'Retry-After' headers, Polls return a 200 with a response body after success.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginPost202Retry200(Product product) {
        return this.beginPost202Retry200Async(product).getSyncPoller();
    }

    /**
     * Long running post request, service returns a 500, then a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LRORetrysPostAsyncRelativeRetrySucceededResponse> postAsyncRelativeRetrySucceededWithResponseAsync(
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
                context -> service.postAsyncRelativeRetrySucceeded(this.client.getHost(), product, accept, context));
    }

    /**
     * Long running post request, service returns a 500, then a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginPostAsyncRelativeRetrySucceededAsync(Product product) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.postAsyncRelativeRetrySucceededWithResponseAsync(product),
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
     * Long running post request, service returns a 500, then a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginPostAsyncRelativeRetrySucceeded(Product product) {
        return this.beginPostAsyncRelativeRetrySucceededAsync(product).getSyncPoller();
    }

    private static final class TypeReferenceProduct extends TypeReference<Product> {
        // empty
    }

    private static final class TypeReferenceBinaryData extends TypeReference<BinaryData> {
        // empty
    }
}
