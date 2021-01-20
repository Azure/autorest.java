package fixtures.bodyduration;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.Put;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import fixtures.bodyduration.models.ErrorException;
import java.time.Duration;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in DurationOperations. */
public final class DurationOperations {
    /** The proxy service used to perform REST calls. */
    private final DurationOperationsService service;

    /** The service client containing this operation class. */
    private final AutoRestDurationTestService client;

    /**
     * Initializes an instance of DurationOperations.
     *
     * @param client the instance of the service client containing this operation class.
     */
    DurationOperations(AutoRestDurationTestService client) {
        this.service =
                RestProxy.create(
                        DurationOperationsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for AutoRestDurationTestServiceDurationOperations to be used by the proxy
     * service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestDurationTest")
    private interface DurationOperationsService {
        @Get("/duration/null")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Duration>> getNull(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/duration/positiveduration")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putPositiveDuration(
                @HostParam("$host") String host,
                @BodyParam("application/json") Duration durationBody,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/duration/positiveduration")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Duration>> getPositiveDuration(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Get("/duration/invalid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Duration>> getInvalid(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);
    }

    /**
     * Get null duration value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null duration value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Duration>> getNullWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getNull(this.client.getHost(), accept, context));
    }

    /**
     * Get null duration value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null duration value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Duration> getNullAsync() {
        return getNullWithResponseAsync()
                .flatMap(
                        (Response<Duration> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get null duration value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null duration value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Duration getNull() {
        return getNullAsync().block();
    }

    /**
     * Put a positive duration value.
     *
     * @param durationBody duration body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putPositiveDurationWithResponseAsync(Duration durationBody) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (durationBody == null) {
            return Mono.error(new IllegalArgumentException("Parameter durationBody is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.putPositiveDuration(this.client.getHost(), durationBody, accept, context));
    }

    /**
     * Put a positive duration value.
     *
     * @param durationBody duration body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putPositiveDurationAsync(Duration durationBody) {
        return putPositiveDurationWithResponseAsync(durationBody).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put a positive duration value.
     *
     * @param durationBody duration body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putPositiveDuration(Duration durationBody) {
        putPositiveDurationAsync(durationBody).block();
    }

    /**
     * Get a positive duration value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a positive duration value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Duration>> getPositiveDurationWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getPositiveDuration(this.client.getHost(), accept, context));
    }

    /**
     * Get a positive duration value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a positive duration value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Duration> getPositiveDurationAsync() {
        return getPositiveDurationWithResponseAsync()
                .flatMap(
                        (Response<Duration> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get a positive duration value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a positive duration value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Duration getPositiveDuration() {
        return getPositiveDurationAsync().block();
    }

    /**
     * Get an invalid duration value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an invalid duration value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Duration>> getInvalidWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getInvalid(this.client.getHost(), accept, context));
    }

    /**
     * Get an invalid duration value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an invalid duration value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Duration> getInvalidAsync() {
        return getInvalidWithResponseAsync()
                .flatMap(
                        (Response<Duration> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get an invalid duration value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an invalid duration value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Duration getInvalid() {
        return getInvalidAsync().block();
    }
}
