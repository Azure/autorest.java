package fixtures.nonstringenum;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.Put;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import fixtures.nonstringenum.models.FloatEnum;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in FloatOperations. */
public final class FloatOperations {
    /** The proxy service used to perform REST calls. */
    private final FloatOperationsService service;

    /** The service client containing this operation class. */
    private final NonStringEnumsClient client;

    /**
     * Initializes an instance of FloatOperations.
     *
     * @param client the instance of the service client containing this operation class.
     */
    FloatOperations(NonStringEnumsClient client) {
        this.service =
                RestProxy.create(FloatOperationsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for NonStringEnumsClientFloatOperations to be used by the proxy service
     * to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "NonStringEnumsClient")
    private interface FloatOperationsService {
        @Put("/nonStringEnums/float/put")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<String>> put(
                @HostParam("$host") String host, @BodyParam("application/json") FloatEnum input, Context context);

        @Get("/nonStringEnums/float/get")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<FloatEnum>> get(@HostParam("$host") String host, Context context);
    }

    /**
     * Put a float enum.
     *
     * @param input Input float enum.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<String>> putWithResponseAsync(FloatEnum input) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.put(this.client.getHost(), input, context));
    }

    /**
     * Put a float enum.
     *
     * @param input Input float enum.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<String> putAsync(FloatEnum input) {
        return putWithResponseAsync(input)
                .flatMap(
                        (Response<String> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Put a float enum.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<String> putAsync() {
        final FloatEnum input = null;
        return putWithResponseAsync(input)
                .flatMap(
                        (Response<String> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Put a float enum.
     *
     * @param input Input float enum.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public String put(FloatEnum input) {
        return putAsync(input).block();
    }

    /**
     * Put a float enum.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public String put() {
        final FloatEnum input = null;
        return putAsync(input).block();
    }

    /**
     * Get a float enum.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a float enum.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<FloatEnum>> getWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.get(this.client.getHost(), context));
    }

    /**
     * Get a float enum.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a float enum.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<FloatEnum> getAsync() {
        return getWithResponseAsync()
                .flatMap(
                        (Response<FloatEnum> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get a float enum.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a float enum.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public FloatEnum get() {
        return getAsync().block();
    }
}
