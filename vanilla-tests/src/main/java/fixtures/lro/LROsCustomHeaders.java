package fixtures.lro;

import com.azure.core.annotation.BodyParam;
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
import fixtures.lro.models.LROsCustomHeadersPost202Retry200Response;
import fixtures.lro.models.LROsCustomHeadersPostAsyncRetrySucceededResponse;
import fixtures.lro.models.LROsCustomHeadersPutAsyncRetrySucceededResponse;
import fixtures.lro.models.Product;
import java.time.Duration;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in LROsCustomHeaders. */
public final class LROsCustomHeaders {
    /** The proxy service used to perform REST calls. */
    private final LROsCustomHeadersService service;

    /** The service client containing this operation class. */
    private final AutoRestLongRunningOperationTestService client;

    /**
     * Initializes an instance of LROsCustomHeaders.
     *
     * @param client the instance of the service client containing this operation class.
     */
    LROsCustomHeaders(AutoRestLongRunningOperationTestService client) {
        this.service =
                RestProxy.create(
                        LROsCustomHeadersService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for AutoRestLongRunningOperationTestServiceLROsCustomHeaders to be used
     * by the proxy service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestLongRunningO")
    private interface LROsCustomHeadersService {
        @Put("/lro/customheader/putasync/retry/succeeded")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LROsCustomHeadersPutAsyncRetrySucceededResponse> putAsyncRetrySucceeded(
                @HostParam("$host") String host,
                @BodyParam("application/json") Product product,
                @HeaderParam("Accept") String accept,
                Context context);

        @Put("/lro/customheader/put/201/creating/succeeded/200")
        @ExpectedResponses({200, 201})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<Response<Product>> put201CreatingSucceeded200(
                @HostParam("$host") String host,
                @BodyParam("application/json") Product product,
                @HeaderParam("Accept") String accept,
                Context context);

        @Post("/lro/customheader/post/202/retry/200")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LROsCustomHeadersPost202Retry200Response> post202Retry200(
                @HostParam("$host") String host,
                @BodyParam("application/json") Product product,
                @HeaderParam("Accept") String accept,
                Context context);

        @Post("/lro/customheader/postasync/retry/succeeded")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LROsCustomHeadersPostAsyncRetrySucceededResponse> postAsyncRetrySucceeded(
                @HostParam("$host") String host,
                @BodyParam("application/json") Product product,
                @HeaderParam("Accept") String accept,
                Context context);
    }

    /**
     * x-ms-client-request-id = 9C4D50EE-2D56-4CD3-8152-34347DC9F2B0 is required message header for all requests. Long
     * running put request, service returns a 200 to the initial request, with an entity that contains
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
    public Mono<LROsCustomHeadersPutAsyncRetrySucceededResponse> putAsyncRetrySucceededWithResponseAsync(
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
                context -> service.putAsyncRetrySucceeded(this.client.getHost(), product, accept, context));
    }

    /**
     * x-ms-client-request-id = 9C4D50EE-2D56-4CD3-8152-34347DC9F2B0 is required message header for all requests. Long
     * running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PollerFlux<Product, Product> beginPutAsyncRetrySucceededAsync(Product product) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.putAsyncRetrySucceededWithResponseAsync(product),
                new ChainedPollingStrategy<>(
                        java.util.Arrays.asList(
                                new OperationResourcePollingStrategy<>(
                                        this.client.getHttpPipeline(), Context.NONE, null, "Azure-AsyncOperation"),
                                new LocationPollingStrategy<>(this.client.getHttpPipeline(), Context.NONE),
                                new StatusCheckPollingStrategy<>())),
                new TypeReference<Product>() {},
                new TypeReference<Product>() {});
    }

    /**
     * x-ms-client-request-id = 9C4D50EE-2D56-4CD3-8152-34347DC9F2B0 is required message header for all requests. Long
     * running put request, service returns a 200 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public SyncPoller<Product, Product> beginPutAsyncRetrySucceeded(Product product) {
        return this.beginPutAsyncRetrySucceededAsync(product).getSyncPoller();
    }

    /**
     * x-ms-client-request-id = 9C4D50EE-2D56-4CD3-8152-34347DC9F2B0 is required message header for all requests. Long
     * running put request, service returns a 201 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Polls return this value until the last poll returns a ‘200’ with
     * ProvisioningState=’Succeeded’.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
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
     * x-ms-client-request-id = 9C4D50EE-2D56-4CD3-8152-34347DC9F2B0 is required message header for all requests. Long
     * running put request, service returns a 201 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Polls return this value until the last poll returns a ‘200’ with
     * ProvisioningState=’Succeeded’.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PollerFlux<Product, Product> beginPut201CreatingSucceeded200Async(Product product) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.put201CreatingSucceeded200WithResponseAsync(product),
                new ChainedPollingStrategy<>(
                        java.util.Arrays.asList(
                                new OperationResourcePollingStrategy<>(
                                        this.client.getHttpPipeline(), Context.NONE, null, "Azure-AsyncOperation"),
                                new LocationPollingStrategy<>(this.client.getHttpPipeline(), Context.NONE),
                                new StatusCheckPollingStrategy<>())),
                new TypeReference<Product>() {},
                new TypeReference<Product>() {});
    }

    /**
     * x-ms-client-request-id = 9C4D50EE-2D56-4CD3-8152-34347DC9F2B0 is required message header for all requests. Long
     * running put request, service returns a 201 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Polls return this value until the last poll returns a ‘200’ with
     * ProvisioningState=’Succeeded’.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public SyncPoller<Product, Product> beginPut201CreatingSucceeded200(Product product) {
        return this.beginPut201CreatingSucceeded200Async(product).getSyncPoller();
    }

    /**
     * x-ms-client-request-id = 9C4D50EE-2D56-4CD3-8152-34347DC9F2B0 is required message header for all requests. Long
     * running post request, service returns a 202 to the initial request, with 'Location' and 'Retry-After' headers,
     * Polls return a 200 with a response body after success.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LROsCustomHeadersPost202Retry200Response> post202Retry200WithResponseAsync(Product product) {
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
     * x-ms-client-request-id = 9C4D50EE-2D56-4CD3-8152-34347DC9F2B0 is required message header for all requests. Long
     * running post request, service returns a 202 to the initial request, with 'Location' and 'Retry-After' headers,
     * Polls return a 200 with a response body after success.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PollerFlux<BinaryData, BinaryData> beginPost202Retry200Async(Product product) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.post202Retry200WithResponseAsync(product),
                new ChainedPollingStrategy<>(
                        java.util.Arrays.asList(
                                new OperationResourcePollingStrategy<>(
                                        this.client.getHttpPipeline(), Context.NONE, null, "Azure-AsyncOperation"),
                                new LocationPollingStrategy<>(this.client.getHttpPipeline(), Context.NONE),
                                new StatusCheckPollingStrategy<>())),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * x-ms-client-request-id = 9C4D50EE-2D56-4CD3-8152-34347DC9F2B0 is required message header for all requests. Long
     * running post request, service returns a 202 to the initial request, with 'Location' and 'Retry-After' headers,
     * Polls return a 200 with a response body after success.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public SyncPoller<BinaryData, BinaryData> beginPost202Retry200(Product product) {
        return this.beginPost202Retry200Async(product).getSyncPoller();
    }

    /**
     * x-ms-client-request-id = 9C4D50EE-2D56-4CD3-8152-34347DC9F2B0 is required message header for all requests. Long
     * running post request, service returns a 202 to the initial request, with an entity that contains
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
    public Mono<LROsCustomHeadersPostAsyncRetrySucceededResponse> postAsyncRetrySucceededWithResponseAsync(
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
                context -> service.postAsyncRetrySucceeded(this.client.getHost(), product, accept, context));
    }

    /**
     * x-ms-client-request-id = 9C4D50EE-2D56-4CD3-8152-34347DC9F2B0 is required message header for all requests. Long
     * running post request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PollerFlux<BinaryData, BinaryData> beginPostAsyncRetrySucceededAsync(Product product) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.postAsyncRetrySucceededWithResponseAsync(product),
                new ChainedPollingStrategy<>(
                        java.util.Arrays.asList(
                                new OperationResourcePollingStrategy<>(
                                        this.client.getHttpPipeline(), Context.NONE, null, "Azure-AsyncOperation"),
                                new LocationPollingStrategy<>(this.client.getHttpPipeline(), Context.NONE),
                                new StatusCheckPollingStrategy<>())),
                new TypeReference<BinaryData>() {},
                new TypeReference<BinaryData>() {});
    }

    /**
     * x-ms-client-request-id = 9C4D50EE-2D56-4CD3-8152-34347DC9F2B0 is required message header for all requests. Long
     * running post request, service returns a 202 to the initial request, with an entity that contains
     * ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation
     * status.
     *
     * @param product Product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public SyncPoller<BinaryData, BinaryData> beginPostAsyncRetrySucceeded(Product product) {
        return this.beginPostAsyncRetrySucceededAsync(product).getSyncPoller();
    }
}
