package fixtures.bodyduration;

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
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.SimpleResponse;
import com.azure.core.implementation.RestProxy;
import fixtures.bodyduration.models.ErrorException;
import java.time.Duration;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in
 * Durations.
 */
public final class Durations {
    /**
     * The proxy service used to perform REST calls.
     */
    private DurationsService service;

    /**
     * The service client containing this operation class.
     */
    private AutoRestDurationTestService client;

    /**
     * Initializes an instance of Durations.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    public Durations(AutoRestDurationTestService client) {
        this.service = RestProxy.create(DurationsService.class, client.getHttpPipeline());
        this.client = client;
    }

    /**
     * The interface defining all the services for
     * AutoRestDurationTestServiceDurations to be used by the proxy service to
     * perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestDurationTestServiceDurations")
    private interface DurationsService {
        @Get("/duration/null")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Duration>> getNull(@HostParam("$host") String Host);

        @Put("/duration/positiveduration")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putPositiveDuration(@HostParam("$host") String Host, @BodyParam("application/json") Duration DurationBody);

        @Get("/duration/positiveduration")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Duration>> getPositiveDuration(@HostParam("$host") String Host);

        @Get("/duration/invalid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Duration>> getInvalid(@HostParam("$host") String Host);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Duration>> getNullWithResponseAsync() {
        return service.getNull(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Duration> getNullAsync() {
        return getNullWithResponseAsync()
            .flatMap((SimpleResponse<Duration> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Duration getNull() {
        return getNullAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putPositiveDurationWithResponseAsync(Duration DurationBody) {
        return service.putPositiveDuration(this.client.getHost(), DurationBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putPositiveDurationAsync(Duration DurationBody) {
        return putPositiveDurationWithResponseAsync(DurationBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putPositiveDuration(Duration DurationBody) {
        putPositiveDurationAsync(DurationBody).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Duration>> getPositiveDurationWithResponseAsync() {
        return service.getPositiveDuration(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Duration> getPositiveDurationAsync() {
        return getPositiveDurationWithResponseAsync()
            .flatMap((SimpleResponse<Duration> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Duration getPositiveDuration() {
        return getPositiveDurationAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Duration>> getInvalidWithResponseAsync() {
        return service.getInvalid(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Duration> getInvalidAsync() {
        return getInvalidWithResponseAsync()
            .flatMap((SimpleResponse<Duration> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Duration getInvalid() {
        return getInvalidAsync().block();
    }
}
