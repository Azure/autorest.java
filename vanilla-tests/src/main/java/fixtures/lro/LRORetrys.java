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
import fixtures.lro.models.LRORetrysDelete202Retry200Response;
import fixtures.lro.models.LRORetrysDeleteAsyncRelativeRetrySucceededResponse;
import fixtures.lro.models.LRORetrysDeleteProvisioning202Accepted200SucceededResponse;
import fixtures.lro.models.LRORetrysPost202Retry200Response;
import fixtures.lro.models.LRORetrysPostAsyncRelativeRetrySucceededResponse;
import fixtures.lro.models.LRORetrysPutAsyncRelativeRetrySucceededResponse;
import fixtures.lro.models.Product;
import java.time.Duration;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in
 * LRORetrys.
 */
public final class LRORetrys {
    /**
     * The proxy service used to perform REST calls.
     */
    private final LRORetrysService service;

    /**
     * The service client containing this operation class.
     */
    private final AutoRestLongRunningOperationTestService client;

    /**
     * Initializes an instance of LRORetrys.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    LRORetrys(AutoRestLongRunningOperationTestService client) {
        this.service = RestProxy.create(LRORetrysService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for
     * AutoRestLongRunningOperationTestServiceLRORetrys to be used by the proxy
     * service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestLongRunningO")
    private interface LRORetrysService {
        @Put("/lro/retryerror/put/201/creating/succeeded/200")
        @ExpectedResponses({200, 201})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<Response<Product>> put201CreatingSucceeded200(@HostParam("$host") String host, @BodyParam("application/json") Product product, @HeaderParam("Accept") String accept, Context context);

        @Put("/lro/retryerror/putasync/retry/succeeded")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LRORetrysPutAsyncRelativeRetrySucceededResponse> putAsyncRelativeRetrySucceeded(@HostParam("$host") String host, @BodyParam("application/json") Product product, @HeaderParam("Accept") String accept, Context context);

        @Delete("/lro/retryerror/delete/provisioning/202/accepted/200/succeeded")
        @ExpectedResponses({200, 202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LRORetrysDeleteProvisioning202Accepted200SucceededResponse> deleteProvisioning202Accepted200Succeeded(@HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Delete("/lro/retryerror/delete/202/retry/200")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LRORetrysDelete202Retry200Response> delete202Retry200(@HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Delete("/lro/retryerror/deleteasync/retry/succeeded")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LRORetrysDeleteAsyncRelativeRetrySucceededResponse> deleteAsyncRelativeRetrySucceeded(@HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Post("/lro/retryerror/post/202/retry/200")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LRORetrysPost202Retry200Response> post202Retry200(@HostParam("$host") String host, @BodyParam("application/json") Product product, @HeaderParam("Accept") String accept, Context context);

        @Post("/lro/retryerror/postasync/retry/succeeded")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(CloudErrorException.class)
        Mono<LRORetrysPostAsyncRelativeRetrySucceededResponse> postAsyncRelativeRetrySucceeded(@HostParam("$host") String host, @BodyParam("application/json") Product product, @HeaderParam("Accept") String accept, Context context);
    }

    /**
     * Long running put request, service returns a 500, then a 201 to the initial request, with an entity that contains ProvisioningState=’Creating’.  Polls return this value until the last poll returns a ‘200’ with ProvisioningState=’Succeeded’.
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
     * Long running put request, service returns a 500, then a 201 to the initial request, with an entity that contains ProvisioningState=’Creating’.  Polls return this value until the last poll returns a ‘200’ with ProvisioningState=’Succeeded’.
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
     * Long running put request, service returns a 500, then a 201 to the initial request, with an entity that contains ProvisioningState=’Creating’.  Polls return this value until the last poll returns a ‘200’ with ProvisioningState=’Succeeded’.
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
     * Long running put request, service returns a 500, then a 200 to the initial request, with an entity that contains ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation status.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LRORetrysPutAsyncRelativeRetrySucceededResponse> putAsyncRelativeRetrySucceededWithResponseAsync(Product product, Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.putAsyncRelativeRetrySucceeded(this.client.getHost(), product, accept, context));
    }

    /**
     * Long running put request, service returns a 500, then a 200 to the initial request, with an entity that contains ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation status.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PollerFlux<Product, Product> beginPutAsyncRelativeRetrySucceededAsync(Product product, Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.putAsyncRelativeRetrySucceededWithResponseAsync(product, pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Product>() { }, new TypeReference<Product>() { });
    }

    /**
     * Long running put request, service returns a 500, then a 200 to the initial request, with an entity that contains ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation status.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public SyncPoller<Product, Product> beginPutAsyncRelativeRetrySucceeded(Product product, Duration pollInterval) {
        return this.beginPutAsyncRelativeRetrySucceededAsync(product, pollInterval).getSyncPoller();
    }

    /**
     * Long running delete request, service returns a 500, then a  202 to the initial request, with an entity that contains ProvisioningState=’Accepted’.  Polls return this value until the last poll returns a ‘200’ with ProvisioningState=’Succeeded’.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LRORetrysDeleteProvisioning202Accepted200SucceededResponse> deleteProvisioning202Accepted200SucceededWithResponseAsync(Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.deleteProvisioning202Accepted200Succeeded(this.client.getHost(), accept, context));
    }

    /**
     * Long running delete request, service returns a 500, then a  202 to the initial request, with an entity that contains ProvisioningState=’Accepted’.  Polls return this value until the last poll returns a ‘200’ with ProvisioningState=’Succeeded’.
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
     * Long running delete request, service returns a 500, then a  202 to the initial request, with an entity that contains ProvisioningState=’Accepted’.  Polls return this value until the last poll returns a ‘200’ with ProvisioningState=’Succeeded’.
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
     * Long running delete request, service returns a 500, then a 202 to the initial request. Polls return this value until the last poll returns a ‘200’ with ProvisioningState=’Succeeded’.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LRORetrysDelete202Retry200Response> delete202Retry200WithResponseAsync(Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.delete202Retry200(this.client.getHost(), accept, context));
    }

    /**
     * Long running delete request, service returns a 500, then a 202 to the initial request. Polls return this value until the last poll returns a ‘200’ with ProvisioningState=’Succeeded’.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public PollerFlux<Void, Void> beginDelete202Retry200Async(Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.delete202Retry200WithResponseAsync(pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Void>() { }, new TypeReference<Void>() { });
    }

    /**
     * Long running delete request, service returns a 500, then a 202 to the initial request. Polls return this value until the last poll returns a ‘200’ with ProvisioningState=’Succeeded’.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public SyncPoller<Void, Void> beginDelete202Retry200(Duration pollInterval) {
        return this.beginDelete202Retry200Async(pollInterval).getSyncPoller();
    }

    /**
     * Long running delete request, service returns a 500, then a 202 to the initial request. Poll the endpoint indicated in the Azure-AsyncOperation header for operation status.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LRORetrysDeleteAsyncRelativeRetrySucceededResponse> deleteAsyncRelativeRetrySucceededWithResponseAsync(Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.deleteAsyncRelativeRetrySucceeded(this.client.getHost(), accept, context));
    }

    /**
     * Long running delete request, service returns a 500, then a 202 to the initial request. Poll the endpoint indicated in the Azure-AsyncOperation header for operation status.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public PollerFlux<Void, Void> beginDeleteAsyncRelativeRetrySucceededAsync(Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.deleteAsyncRelativeRetrySucceededWithResponseAsync(pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Void>() { }, new TypeReference<Void>() { });
    }

    /**
     * Long running delete request, service returns a 500, then a 202 to the initial request. Poll the endpoint indicated in the Azure-AsyncOperation header for operation status.
     * 
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public SyncPoller<Void, Void> beginDeleteAsyncRelativeRetrySucceeded(Duration pollInterval) {
        return this.beginDeleteAsyncRelativeRetrySucceededAsync(pollInterval).getSyncPoller();
    }

    /**
     * Long running post request, service returns a 500, then a 202 to the initial request, with 'Location' and 'Retry-After' headers, Polls return a 200 with a response body after success.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LRORetrysPost202Retry200Response> post202Retry200WithResponseAsync(Product product, Duration pollInterval) {
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
     * Long running post request, service returns a 500, then a 202 to the initial request, with 'Location' and 'Retry-After' headers, Polls return a 200 with a response body after success.
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
     * Long running post request, service returns a 500, then a 202 to the initial request, with 'Location' and 'Retry-After' headers, Polls return a 200 with a response body after success.
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
     * Long running post request, service returns a 500, then a 202 to the initial request, with an entity that contains ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation status.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LRORetrysPostAsyncRelativeRetrySucceededResponse> postAsyncRelativeRetrySucceededWithResponseAsync(Product product, Duration pollInterval) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (product != null) {
            product.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.postAsyncRelativeRetrySucceeded(this.client.getHost(), product, accept, context));
    }

    /**
     * Long running post request, service returns a 500, then a 202 to the initial request, with an entity that contains ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation status.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public PollerFlux<Void, Void> beginPostAsyncRelativeRetrySucceededAsync(Product product, Duration pollInterval) {
        return PollerFlux.create(pollInterval,
            () -> this.postAsyncRelativeRetrySucceededWithResponseAsync(product, pollInterval),
            ChainedPollingStrategy.createDefault(client.getHttpPipeline(), Context.NONE),
            new TypeReference<Void>() { }, new TypeReference<Void>() { });
    }

    /**
     * Long running post request, service returns a 500, then a 202 to the initial request, with an entity that contains ProvisioningState=’Creating’. Poll the endpoint indicated in the Azure-AsyncOperation header for operation status.
     * 
     * @param product Product to put.
     * @param pollInterval the polling interval.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws CloudErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public SyncPoller<Void, Void> beginPostAsyncRelativeRetrySucceeded(Product product, Duration pollInterval) {
        return this.beginPostAsyncRelativeRetrySucceededAsync(product, pollInterval).getSyncPoller();
    }
}
