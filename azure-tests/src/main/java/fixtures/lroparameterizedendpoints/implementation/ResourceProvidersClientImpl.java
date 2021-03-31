package fixtures.lroparameterizedendpoints.implementation;

import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Headers;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.Post;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.management.polling.PollResult;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import com.azure.core.util.logging.ClientLogger;
import com.azure.core.util.polling.PollerFlux;
import com.azure.core.util.polling.SyncPoller;
import fixtures.lroparameterizedendpoints.fluent.ResourceProvidersClient;
import fixtures.lroparameterizedendpoints.models.ErrorException;
import java.nio.ByteBuffer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in ResourceProvidersClient. */
public final class ResourceProvidersClientImpl implements ResourceProvidersClient {
    private final ClientLogger logger = new ClientLogger(ResourceProvidersClientImpl.class);

    /** The proxy service used to perform REST calls. */
    private final ResourceProvidersService service;

    /** The service client containing this operation class. */
    private final LroWithParamaterizedEndpointsImpl client;

    /**
     * Initializes an instance of ResourceProvidersClientImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    ResourceProvidersClientImpl(LroWithParamaterizedEndpointsImpl client) {
        this.service =
            RestProxy.create(ResourceProvidersService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for LroWithParamaterizedEndpointsResourceProviders to be used by the
     * proxy service to perform REST calls.
     */
    @Host("http://{accountName}{host}")
    @ServiceInterface(name = "LroWithParamaterized")
    private interface ResourceProvidersService {
        @Headers({"Content-Type: application/json"})
        @Post("/lroParameterizedEndpoints")
        @ExpectedResponses({200, 202})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Flux<ByteBuffer>>> pollWithParameterizedEndpoints(
            @HostParam("accountName") String accountName,
            @HostParam("host") String host,
            @HeaderParam("Accept") String accept,
            Context context);
    }

    /**
     * Poll with method and client level parameters in endpoint.
     *
     * @param accountName Account Name. Pass in 'local' to pass test.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Flux<ByteBuffer>>> pollWithParameterizedEndpointsWithResponseAsync(String accountName) {
        if (accountName == null) {
            return Mono.error(new IllegalArgumentException("Parameter accountName is required and cannot be null."));
        }
        if (this.client.getHost() == null) {
            return Mono
                .error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil
            .withContext(
                context -> service.pollWithParameterizedEndpoints(accountName, this.client.getHost(), accept, context))
            .contextWrite(context -> context.putAll(FluxUtil.toReactorContext(this.client.getContext()).readOnly()));
    }

    /**
     * Poll with method and client level parameters in endpoint.
     *
     * @param accountName Account Name. Pass in 'local' to pass test.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Flux<ByteBuffer>>> pollWithParameterizedEndpointsWithResponseAsync(
        String accountName, Context context) {
        if (accountName == null) {
            return Mono.error(new IllegalArgumentException("Parameter accountName is required and cannot be null."));
        }
        if (this.client.getHost() == null) {
            return Mono
                .error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        context = this.client.mergeContext(context);
        return service.pollWithParameterizedEndpoints(accountName, this.client.getHost(), accept, context);
    }

    /**
     * Poll with method and client level parameters in endpoint.
     *
     * @param accountName Account Name. Pass in 'local' to pass test.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PollerFlux<PollResult<String>, String> beginPollWithParameterizedEndpointsAsync(String accountName) {
        Mono<Response<Flux<ByteBuffer>>> mono = pollWithParameterizedEndpointsWithResponseAsync(accountName);
        return this
            .client
            .<String, String>getLroResult(
                mono, this.client.getHttpPipeline(), String.class, String.class, Context.NONE);
    }

    /**
     * Poll with method and client level parameters in endpoint.
     *
     * @param accountName Account Name. Pass in 'local' to pass test.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PollerFlux<PollResult<String>, String> beginPollWithParameterizedEndpointsAsync(
        String accountName, Context context) {
        context = this.client.mergeContext(context);
        Mono<Response<Flux<ByteBuffer>>> mono = pollWithParameterizedEndpointsWithResponseAsync(accountName, context);
        return this
            .client
            .<String, String>getLroResult(mono, this.client.getHttpPipeline(), String.class, String.class, context);
    }

    /**
     * Poll with method and client level parameters in endpoint.
     *
     * @param accountName Account Name. Pass in 'local' to pass test.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SyncPoller<PollResult<String>, String> beginPollWithParameterizedEndpoints(String accountName) {
        return beginPollWithParameterizedEndpointsAsync(accountName).getSyncPoller();
    }

    /**
     * Poll with method and client level parameters in endpoint.
     *
     * @param accountName Account Name. Pass in 'local' to pass test.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SyncPoller<PollResult<String>, String> beginPollWithParameterizedEndpoints(
        String accountName, Context context) {
        return beginPollWithParameterizedEndpointsAsync(accountName, context).getSyncPoller();
    }

    /**
     * Poll with method and client level parameters in endpoint.
     *
     * @param accountName Account Name. Pass in 'local' to pass test.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<String> pollWithParameterizedEndpointsAsync(String accountName) {
        return beginPollWithParameterizedEndpointsAsync(accountName)
            .last()
            .flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Poll with method and client level parameters in endpoint.
     *
     * @param accountName Account Name. Pass in 'local' to pass test.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<String> pollWithParameterizedEndpointsAsync(String accountName, Context context) {
        return beginPollWithParameterizedEndpointsAsync(accountName, context)
            .last()
            .flatMap(this.client::getLroFinalResultOrError);
    }

    /**
     * Poll with method and client level parameters in endpoint.
     *
     * @param accountName Account Name. Pass in 'local' to pass test.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public String pollWithParameterizedEndpoints(String accountName) {
        return pollWithParameterizedEndpointsAsync(accountName).block();
    }

    /**
     * Poll with method and client level parameters in endpoint.
     *
     * @param accountName Account Name. Pass in 'local' to pass test.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public String pollWithParameterizedEndpoints(String accountName, Context context) {
        return pollWithParameterizedEndpointsAsync(accountName, context).block();
    }
}
