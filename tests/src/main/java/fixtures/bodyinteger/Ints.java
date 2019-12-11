package fixtures.bodyinteger;

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
import fixtures.bodyinteger.models.ErrorException;
import java.time.OffsetDateTime;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in
 * Ints.
 */
public final class Ints {
    /**
     * The proxy service used to perform REST calls.
     */
    private IntsService service;

    /**
     * The service client containing this operation class.
     */
    private AutoRestIntegerTestService client;

    /**
     * Initializes an instance of Ints.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    public Ints(AutoRestIntegerTestService client) {
        this.service = RestProxy.create(IntsService.class, client.getHttpPipeline());
        this.client = client;
    }

    /**
     * The interface defining all the services for
     * AutoRestIntegerTestServiceInts to be used by the proxy service to
     * perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestIntegerTestServiceInts")
    private interface IntsService {
        @Get("/int/null")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Integer>> getNull(@HostParam("$host") String host);

        @Get("/int/invalid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Integer>> getInvalid(@HostParam("$host") String host);

        @Get("/int/overflowint32")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Integer>> getOverflowInt32(@HostParam("$host") String host);

        @Get("/int/underflowint32")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Integer>> getUnderflowInt32(@HostParam("$host") String host);

        @Get("/int/overflowint64")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Long>> getOverflowInt64(@HostParam("$host") String host);

        @Get("/int/underflowint64")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Long>> getUnderflowInt64(@HostParam("$host") String host);

        @Put("/int/max/32")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putMax32(@HostParam("$host") String host, @BodyParam("application/json") int intBody);

        @Put("/int/max/64")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putMax64(@HostParam("$host") String host, @BodyParam("application/json") long intBody);

        @Put("/int/min/32")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putMin32(@HostParam("$host") String host, @BodyParam("application/json") int intBody);

        @Put("/int/min/64")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putMin64(@HostParam("$host") String host, @BodyParam("application/json") long intBody);

        @Get("/int/unixtime")
        @ExpectedResponses({200})
        @ReturnValueWireType(long.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<OffsetDateTime>> getUnixTime(@HostParam("$host") String host);

        @Put("/int/unixtime")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putUnixTimeDate(@HostParam("$host") String host, @BodyParam("application/json") long intBody);

        @Get("/int/invalidunixtime")
        @ExpectedResponses({200})
        @ReturnValueWireType(long.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<OffsetDateTime>> getInvalidUnixTime(@HostParam("$host") String host);

        @Get("/int/nullunixtime")
        @ExpectedResponses({200})
        @ReturnValueWireType(long.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<OffsetDateTime>> getNullUnixTime(@HostParam("$host") String host);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Integer>> getNullWithResponseAsync() {
        return service.getNull(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Integer> getNullAsync() {
        return getNullWithResponseAsync()
            .flatMap((SimpleResponse<Integer> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public int getNull() {
        return getNullAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Integer>> getInvalidWithResponseAsync() {
        return service.getInvalid(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Integer> getInvalidAsync() {
        return getInvalidWithResponseAsync()
            .flatMap((SimpleResponse<Integer> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public int getInvalid() {
        return getInvalidAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Integer>> getOverflowInt32WithResponseAsync() {
        return service.getOverflowInt32(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Integer> getOverflowInt32Async() {
        return getOverflowInt32WithResponseAsync()
            .flatMap((SimpleResponse<Integer> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public int getOverflowInt32() {
        return getOverflowInt32Async().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Integer>> getUnderflowInt32WithResponseAsync() {
        return service.getUnderflowInt32(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Integer> getUnderflowInt32Async() {
        return getUnderflowInt32WithResponseAsync()
            .flatMap((SimpleResponse<Integer> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public int getUnderflowInt32() {
        return getUnderflowInt32Async().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Long>> getOverflowInt64WithResponseAsync() {
        return service.getOverflowInt64(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Long> getOverflowInt64Async() {
        return getOverflowInt64WithResponseAsync()
            .flatMap((SimpleResponse<Long> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public long getOverflowInt64() {
        return getOverflowInt64Async().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Long>> getUnderflowInt64WithResponseAsync() {
        return service.getUnderflowInt64(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Long> getUnderflowInt64Async() {
        return getUnderflowInt64WithResponseAsync()
            .flatMap((SimpleResponse<Long> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public long getUnderflowInt64() {
        return getUnderflowInt64Async().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putMax32WithResponseAsync(int intBody) {
        return service.putMax32(this.client.getHost(), intBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putMax32Async(int intBody) {
        return putMax32WithResponseAsync(intBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putMax32(int intBody) {
        putMax32Async(intBody).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putMax64WithResponseAsync(long intBody) {
        return service.putMax64(this.client.getHost(), intBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putMax64Async(long intBody) {
        return putMax64WithResponseAsync(intBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putMax64(long intBody) {
        putMax64Async(intBody).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putMin32WithResponseAsync(int intBody) {
        return service.putMin32(this.client.getHost(), intBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putMin32Async(int intBody) {
        return putMin32WithResponseAsync(intBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putMin32(int intBody) {
        putMin32Async(intBody).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putMin64WithResponseAsync(long intBody) {
        return service.putMin64(this.client.getHost(), intBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putMin64Async(long intBody) {
        return putMin64WithResponseAsync(intBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putMin64(long intBody) {
        putMin64Async(intBody).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<OffsetDateTime>> getUnixTimeWithResponseAsync() {
        return service.getUnixTime(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<OffsetDateTime> getUnixTimeAsync() {
        return getUnixTimeWithResponseAsync()
            .flatMap((SimpleResponse<OffsetDateTime> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public OffsetDateTime getUnixTime() {
        return getUnixTimeAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putUnixTimeDateWithResponseAsync(OffsetDateTime intBody) {
        long intBodyConverted = intBody.toEpochSecond();
        return service.putUnixTimeDate(this.client.getHost(), intBodyConverted);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putUnixTimeDateAsync(OffsetDateTime intBody) {
        return putUnixTimeDateWithResponseAsync(intBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putUnixTimeDate(OffsetDateTime intBody) {
        putUnixTimeDateAsync(intBody).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<OffsetDateTime>> getInvalidUnixTimeWithResponseAsync() {
        return service.getInvalidUnixTime(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<OffsetDateTime> getInvalidUnixTimeAsync() {
        return getInvalidUnixTimeWithResponseAsync()
            .flatMap((SimpleResponse<OffsetDateTime> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public OffsetDateTime getInvalidUnixTime() {
        return getInvalidUnixTimeAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<OffsetDateTime>> getNullUnixTimeWithResponseAsync() {
        return service.getNullUnixTime(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<OffsetDateTime> getNullUnixTimeAsync() {
        return getNullUnixTimeWithResponseAsync()
            .flatMap((SimpleResponse<OffsetDateTime> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public OffsetDateTime getNullUnixTime() {
        return getNullUnixTimeAsync().block();
    }
}
