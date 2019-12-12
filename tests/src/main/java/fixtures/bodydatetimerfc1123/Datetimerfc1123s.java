package fixtures.bodydatetimerfc1123;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.Put;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ReturnValueWireType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.http.rest.SimpleResponse;
import com.azure.core.util.DateTimeRfc1123;
import fixtures.bodydatetimerfc1123.models.ErrorException;
import java.time.OffsetDateTime;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in
 * Datetimerfc1123s.
 */
public final class Datetimerfc1123s {
    /**
     * The proxy service used to perform REST calls.
     */
    private Datetimerfc1123sService service;

    /**
     * The service client containing this operation class.
     */
    private AutoRestRFC1123DateTimeTestService client;

    /**
     * Initializes an instance of Datetimerfc1123s.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    public Datetimerfc1123s(AutoRestRFC1123DateTimeTestService client) {
        this.service = RestProxy.create(Datetimerfc1123sService.class, client.getHttpPipeline());
        this.client = client;
    }

    /**
     * The interface defining all the services for
     * AutoRestRFC1123DateTimeTestServiceDatetimerfc1123s to be used by the
     * proxy service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestRFC1123DateTimeTestServiceDatetimerfc1123s")
    private interface Datetimerfc1123sService {
        @Get("/datetimerfc1123/null")
        @ExpectedResponses({200})
        @ReturnValueWireType(DateTimeRfc1123.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<OffsetDateTime>> getNull(@HostParam("$host") String host);

        @Get("/datetimerfc1123/invalid")
        @ExpectedResponses({200})
        @ReturnValueWireType(DateTimeRfc1123.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<OffsetDateTime>> getInvalid(@HostParam("$host") String host);

        @Get("/datetimerfc1123/overflow")
        @ExpectedResponses({200})
        @ReturnValueWireType(DateTimeRfc1123.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<OffsetDateTime>> getOverflow(@HostParam("$host") String host);

        @Get("/datetimerfc1123/underflow")
        @ExpectedResponses({200})
        @ReturnValueWireType(DateTimeRfc1123.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<OffsetDateTime>> getUnderflow(@HostParam("$host") String host);

        @Put("/datetimerfc1123/max")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putUtcMaxDateTime(@HostParam("$host") String host, @BodyParam("application/json") DateTimeRfc1123 datetimeBody);

        @Get("/datetimerfc1123/max/lowercase")
        @ExpectedResponses({200})
        @ReturnValueWireType(DateTimeRfc1123.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<OffsetDateTime>> getUtcLowercaseMaxDateTime(@HostParam("$host") String host);

        @Get("/datetimerfc1123/max/uppercase")
        @ExpectedResponses({200})
        @ReturnValueWireType(DateTimeRfc1123.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<OffsetDateTime>> getUtcUppercaseMaxDateTime(@HostParam("$host") String host);

        @Put("/datetimerfc1123/min")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putUtcMinDateTime(@HostParam("$host") String host, @BodyParam("application/json") DateTimeRfc1123 datetimeBody);

        @Get("/datetimerfc1123/min")
        @ExpectedResponses({200})
        @ReturnValueWireType(DateTimeRfc1123.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<OffsetDateTime>> getUtcMinDateTime(@HostParam("$host") String host);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<OffsetDateTime>> getNullWithResponseAsync() {
        return service.getNull(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<OffsetDateTime> getNullAsync() {
        return getNullWithResponseAsync()
            .flatMap((SimpleResponse<OffsetDateTime> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public OffsetDateTime getNull() {
        return getNullAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<OffsetDateTime>> getInvalidWithResponseAsync() {
        return service.getInvalid(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<OffsetDateTime> getInvalidAsync() {
        return getInvalidWithResponseAsync()
            .flatMap((SimpleResponse<OffsetDateTime> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public OffsetDateTime getInvalid() {
        return getInvalidAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<OffsetDateTime>> getOverflowWithResponseAsync() {
        return service.getOverflow(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<OffsetDateTime> getOverflowAsync() {
        return getOverflowWithResponseAsync()
            .flatMap((SimpleResponse<OffsetDateTime> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public OffsetDateTime getOverflow() {
        return getOverflowAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<OffsetDateTime>> getUnderflowWithResponseAsync() {
        return service.getUnderflow(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<OffsetDateTime> getUnderflowAsync() {
        return getUnderflowWithResponseAsync()
            .flatMap((SimpleResponse<OffsetDateTime> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public OffsetDateTime getUnderflow() {
        return getUnderflowAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putUtcMaxDateTimeWithResponseAsync(OffsetDateTime datetimeBody) {
        DateTimeRfc1123 datetimeBodyConverted = new DateTimeRfc1123(datetimeBody);
        return service.putUtcMaxDateTime(this.client.getHost(), datetimeBodyConverted);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putUtcMaxDateTimeAsync(OffsetDateTime datetimeBody) {
        return putUtcMaxDateTimeWithResponseAsync(datetimeBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putUtcMaxDateTime(OffsetDateTime datetimeBody) {
        putUtcMaxDateTimeAsync(datetimeBody).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<OffsetDateTime>> getUtcLowercaseMaxDateTimeWithResponseAsync() {
        return service.getUtcLowercaseMaxDateTime(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<OffsetDateTime> getUtcLowercaseMaxDateTimeAsync() {
        return getUtcLowercaseMaxDateTimeWithResponseAsync()
            .flatMap((SimpleResponse<OffsetDateTime> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public OffsetDateTime getUtcLowercaseMaxDateTime() {
        return getUtcLowercaseMaxDateTimeAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<OffsetDateTime>> getUtcUppercaseMaxDateTimeWithResponseAsync() {
        return service.getUtcUppercaseMaxDateTime(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<OffsetDateTime> getUtcUppercaseMaxDateTimeAsync() {
        return getUtcUppercaseMaxDateTimeWithResponseAsync()
            .flatMap((SimpleResponse<OffsetDateTime> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public OffsetDateTime getUtcUppercaseMaxDateTime() {
        return getUtcUppercaseMaxDateTimeAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putUtcMinDateTimeWithResponseAsync(OffsetDateTime datetimeBody) {
        DateTimeRfc1123 datetimeBodyConverted = new DateTimeRfc1123(datetimeBody);
        return service.putUtcMinDateTime(this.client.getHost(), datetimeBodyConverted);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putUtcMinDateTimeAsync(OffsetDateTime datetimeBody) {
        return putUtcMinDateTimeWithResponseAsync(datetimeBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putUtcMinDateTime(OffsetDateTime datetimeBody) {
        putUtcMinDateTimeAsync(datetimeBody).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<OffsetDateTime>> getUtcMinDateTimeWithResponseAsync() {
        return service.getUtcMinDateTime(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<OffsetDateTime> getUtcMinDateTimeAsync() {
        return getUtcMinDateTimeWithResponseAsync()
            .flatMap((SimpleResponse<OffsetDateTime> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public OffsetDateTime getUtcMinDateTime() {
        return getUtcMinDateTimeAsync().block();
    }
}
